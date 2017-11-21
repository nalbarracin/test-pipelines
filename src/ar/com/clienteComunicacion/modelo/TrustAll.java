package ar.com.clienteComunicacion.modelo;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

public class TrustAll implements ModoCliente{
	
	private CloseableHttpClient httpClient;
	private CloseableHttpClient httpClients;
	
	public TrustAll() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		httpClients = createClienteHttpsTrustAll();
		httpClient = HttpClients.custom().build();
	}
	
	@SuppressWarnings("deprecation")
	private CloseableHttpClient createClienteHttpsTrustAll()
			throws KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException {
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null,
				new TrustStrategy() {
					public boolean isTrusted(X509Certificate[] arg0, String arg1)
							throws CertificateException {
						return true;
					}
				}).build();

		return HttpClients
				.custom()
				.setSslcontext(sslContext)
				.setHostnameVerifier(
						SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
				.build();
	}
	
	public HttpResponse executeRequest(String url, HttpUriRequest request)
			throws ClientProtocolException, IOException {
		if (url != null && url.toLowerCase().startsWith("https://")) {
			return httpClients.execute(request);
		} else {
			return httpClient.execute(request);
		}
	}
}
