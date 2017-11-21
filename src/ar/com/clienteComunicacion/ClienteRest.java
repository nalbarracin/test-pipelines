package ar.com.clienteComunicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ProtocolException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import ar.com.clienteComunicacion.modelo.ModoCliente;
import ar.com.clienteComunicacion.modelo.TrustAll;
import ar.com.clienteComunicacion.modelo.TrustCertificate;

public class ClienteRest {
	
	private ModoCliente modo;
	
	private ClienteRest(ModoCliente modo){
		this.modo = modo;
	}
	
	/**
	 * Constructor de clase
	 * Falla la comunicacion https en servicios que tienen un certificado no seguro
	 * 
	 * @return      Instancia de cliente
	 */
	public static ClienteRest getInstanceTrustCertificate(){
		ModoCliente modo = new TrustCertificate();
		return new ClienteRest(modo);
	}

	/**
	 * Constructor de clase
	 * Durante la comunicacion https se ignora la validacion de certificado
	 * 
	 * @return      Instancia de cliente
	 */
	public static ClienteRest getInstanceTrustAll() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException{
		ModoCliente modo = new TrustAll();
		return new ClienteRest(modo);
	}
	
	/**
	 * Metodo Get
	 * 
	 * @param	url			Url de servicio restful
	 * @param	header		Hashmap de header para la comunicacion, agrega o sobreescribe al default segun corresponda<br>
	 * 						Valores default:<br>
	 * 						Content-Type: application/json<br>
	 * 						Connection: close<br>
	 * 						Accept:  *&#47;* <br>
	 * @param	parametros  Hashmap de parametros
	 * @return	String respuesta de get
	 */
	public String get(String url, HashMap<String, String> header,
			HashMap<String, String> parametros) throws Exception {
		String output =  sendGet(getUrlConParametros(url, parametros), header);
		return new String(output.getBytes(), Charset.forName("UTF-8"));
	}
	
	/**
	 * Metodo Post
	 * 
	 * @param	url			Url de servicio restful
	 * @param	header		Hashmap de header para la comunicacion, agrega o sobreescribe al default segun corresponda<br>
	 * 						Valores default:<br>
	 * 						Content-Type: application/json<br>
	 * 						Connection: close<br>
	 * 						Accept:  *&#47;* <br>
	 * @param	parametros  Hashmap de parametros
	 * @return	String respuesta de post
	 */
	public String post(String url, HashMap<String, String> header,
			String data) throws Exception {
		return sendPost(url, header, data);
	}

	private static String getUrlConParametros(String unaUrl,
			HashMap<String, String> parametros) {
		String url = unaUrl;
		if (!parametros.isEmpty()) {
			url = url + "?";
			for (Entry<String, String> entry : parametros.entrySet()) {
				url = url + entry.getKey() + "=" + entry.getValue() + "&";
			}
		}
		return url;
	}

	private static void setHeader(HttpRequestBase request,
			HashMap<String, String> header) throws ProtocolException {
		
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json; charset=utf-8");
		headers.put("Accept-Encoding", "utf-8");
		headers.put("Connection", "close");
		headers.put("Accept", "*/*");
		
		for (Entry<String, String> entry : header.entrySet()) {
			headers.put(entry.getKey(), entry.getValue());
		}
		
		for (Entry<String, String> entry : headers.entrySet()) {
			request.addHeader(entry.getKey(), entry.getValue());
		}
	}

	private static String getInput(HttpResponse response)
			throws UnsupportedOperationException, IOException {		
		if (response.getStatusLine().getStatusCode() != 204) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent(), StandardCharsets.UTF_8));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			return result.toString();
		} else {
			return "";
		}
	}

	private String sendGet(String url, HashMap<String, String> header)
			throws Exception {

		HttpGet request = new HttpGet(url);
		setHeader(request, header);

		HttpResponse response = modo.executeRequest(url, request);
		return getInput(response);
	}

	private String sendPost(String url, HashMap<String, String> header,
			String data) throws Exception {

		HttpPost request = new HttpPost(url);
		setHeader(request, header);
		StringEntity input = new StringEntity(data, Charset.forName("utf-8"));
		input.setContentType("application/json; charset=utf-8");
		request.setEntity(input);
		HttpResponse response = modo.executeRequest(url, request);
		return getInput(response);
	}
}
