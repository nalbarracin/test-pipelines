package ar.com.clienteComunicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ProtocolException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import ar.com.clienteComunicacion.modelo.ModoCliente;
import ar.com.clienteComunicacion.modelo.TrustAll;
import ar.com.clienteComunicacion.modelo.TrustCertificate;
import ar.com.clienteComunicacion.modelo.TrustHost;

public class ClienteSoap{
	
	private ModoCliente modo;
	
	private ClienteSoap(ModoCliente modo){
		this.modo = modo;
	}
	
	/**
	 * Constructor de clase
	 * Falla la comunicacion https en servicios que tienen un certificado no seguro
	 * 
	 * @return      Instancia de cliente
	 */
	public static ClienteSoap getInstanceTrustCertificate(){
		ModoCliente modo = new TrustCertificate();
		return new ClienteSoap(modo);
	}
	
	/**
	 * Constructor de clase
	 * Durante la comunicacion https se ignora la validacion de certificado
	 * 
	 * @return      Instancia de cliente
	 */
	public static ClienteSoap getInstanceTrustAll() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException{
		ModoCliente modo = new TrustAll();
		return new ClienteSoap(modo);
	}
	
	/**
	 * Constructor de clase
	 * Durante la comunicacion https se ignora la validacion de hostname en el certificado
	 * 
	 * @return      Instancia de cliente
	 */
	public static ClienteSoap getInstanceTrustHost() {
		ModoCliente modo = new TrustHost();
		return new ClienteSoap(modo);
	}
	
	/**
	 * Metodo Post
	 * 
	 * @param	url			Url de servicio restful
	 * @param	header		Hashmap de header para la comunicacion, agrega o sobreescribe al default segun corresponda<br>
	 * 						Valores default:<br>
	 * 						Content-Type: text/xml; charset="utf-8"<br>
	 *						SOAPAction: <br>
	 *						Connection: close<br>
	 * 						Accept:  *&#47;* <br>
	 * @param	parametros  Hashmap de parametros
	 * @return	String respuesta de post
	 */
	public String post(String url, HashMap<String, String> header,
			String data) throws Exception {
		return sendPost(url, header, data);
	}

	private void setHeader(HttpRequestBase request,
			HashMap<String, String> header) throws ProtocolException {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "text/xml; charset=\"utf-8\"");
		headers.put("SOAPAction", "");
		headers.put("Connection", "close");
		headers.put("Accept", "*/*");
		
		for (Entry<String, String> entry : header.entrySet()) {
			headers.put(entry.getKey(), entry.getValue());
		}
		
		for (Entry<String, String> entry : headers.entrySet()) {
			request.addHeader(entry.getKey(), entry.getValue());
		}
	}

	private String getInput(HttpResponse response)
			throws UnsupportedOperationException, IOException {
		if (response.getStatusLine().getStatusCode() != 204) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
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

	private String sendPost(String url, HashMap<String, String> header,
			String data) throws Exception {

		HttpPost request = new HttpPost(url);
		setHeader(request, header);
		StringEntity input = new StringEntity(data);
		input.setContentType("text/xml; charset=\"utf-8\"");
		request.setEntity(input);

		HttpResponse response = modo.executeRequest(url, request);

		return getInput(response);
	}
	
	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, Exception {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("SOAPAction", "qweqweq");
		ClienteSoap.getInstanceTrustAll().post("ASD", headers , "");
	}
}
