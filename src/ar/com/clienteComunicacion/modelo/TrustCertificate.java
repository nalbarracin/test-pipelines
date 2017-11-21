package ar.com.clienteComunicacion.modelo;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class TrustCertificate implements ModoCliente {
	
	private CloseableHttpClient httpClient;
	
	public TrustCertificate() {
		httpClient = HttpClients.custom().build();
	}
	
	public HttpResponse executeRequest(String url, HttpUriRequest request)
			throws ClientProtocolException, IOException {
		return httpClient.execute(request);
	}
}
