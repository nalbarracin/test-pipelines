package ar.com.clienteComunicacion.viejo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

@SuppressWarnings("deprecation")
public class ClienteSoapSocket {
	
	/**
	 * @param url
	 * @param xmlSoap
	 * @param SoapAction
	 * @param millisTimeout
	 * @param header
	 * @return Transaccion
	 * @throws ErrorTransaccionException
	 */
	public Transaccion enviar(String url, String xmlSoap, String urlMetodo, int millisTimeout, HashMap<String, String> header) throws ErrorTransaccionException {
		
		Date fechaEnvio, fechaRespuesta;
		
		try {
			MessageFactory mf = MessageFactory.newInstance();
			SOAPMessage msg = mf.createMessage();
			SOAPPart sp = msg.getSOAPPart();

			InputStream is = new ByteArrayInputStream(xmlSoap.getBytes());

			StreamSource prepMsg = new StreamSource(is);
			sp.setContent(prepMsg);

			msg.saveChanges();

			// Envio los certificados
			doTrustToCertificates();
			
			fechaEnvio = new Date();
			String line = enviarHttps(xmlSoap, url, urlMetodo, millisTimeout, header);
	        fechaRespuesta = new Date();
	        
	        StringBuffer respuesta = new StringBuffer();	        	
	        respuesta.append(line);
	        
	        return FactoryTransaccion.recuperarIntancia(respuesta.toString(), fechaEnvio, fechaRespuesta);
	        
		} catch (java.net.ConnectException ce) {
			throw new ErrorConectividadWS(ce);
		} catch (ErrorTimeoutInvocacionWS ce) {
			throw ce;
		} catch (ErrorComunicacionWS ce) {
			throw ce;
		} catch (ErrorCertificados ce) {
			throw ce;
		} catch (Exception e) {
			throw new ErrorEstablecerConexionWS(e);
		}
	}
	
	private String enviarHttps(String inputString, String urlString, String urlMetodo, int timeout, HashMap<String, String> header) throws Exception {
		
		try {
			HttpClient client = getNewHttpClient(timeout);
			
			HttpPost httpPost = new HttpPost(urlString);
			StringEntity entity = new StringEntity(inputString, ContentType.create("text/xml", "UTF-8"));
			httpPost.setEntity(entity);
			httpPost.addHeader("SOAPAction", urlMetodo);
			httpPost.addHeader("Connection", "close");
			httpPost.addHeader("Accept", "*/*");
			
			for (String clave : header.keySet())
				httpPost.setHeader(clave, header.get(clave));
			
			HttpResponse response = client.execute(httpPost);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String inputLine;
			StringBuilder output = new StringBuilder();
			
		    while ((inputLine = reader.readLine()) != null)
			    output.append(inputLine);
		    
		    return output.toString();

		} catch (SocketTimeoutException ste) {
			throw new ErrorTimeoutInvocacionWS(ste);
		} catch (Exception e) {
			throw new ErrorComunicacionWS(e);
		}
	}

	private static void doTrustToCertificates() throws Exception {
		try {
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
					return;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
					return;
				}
			} };

			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HostnameVerifier hv = new HostnameVerifier() {
				public boolean verify(String urlHostName, SSLSession session) {
					
					if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
						System.out.println("Warning: URL host '" + urlHostName
										 + "' is different to SSLSession host '"
										 + session.getPeerHost() + "'.");
					}
					
					return true;
				}
			};
			
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
		} catch (Exception e) {
			throw new ErrorCertificados(e);
		}
	}
	
	public HttpClient getNewHttpClient(int timeout) {
		try {
		    KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		    trustStore.load(null, null);
		
		    SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
		    sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		
		    HttpParams params = new BasicHttpParams();
		    HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		    HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
		    
		    HttpConnectionParams.setConnectionTimeout(params, timeout);
			HttpConnectionParams.setSoTimeout(params, timeout);
			
		    SchemeRegistry registry = new SchemeRegistry();
		    registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		    registry.register(new Scheme("https", sf, 443));
		
	        ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
	        
	        return new DefaultHttpClient(ccm, params);
	    } catch (Exception e) {
	        return new DefaultHttpClient();
	    }
	}
	
	public class MySSLSocketFactory extends SSLSocketFactory {
		SSLContext sslContext = SSLContext.getInstance("TLS");
		
		public MySSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
			super(truststore);

			TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};

			sslContext.init(null, new TrustManager[] { tm }, null);
		}

		@Override
		public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
			return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
		}

		@Override
		public Socket createSocket() throws IOException {
			return sslContext.getSocketFactory().createSocket();
		}
	}
}