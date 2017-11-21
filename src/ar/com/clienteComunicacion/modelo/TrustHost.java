package ar.com.clienteComunicacion.modelo;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class TrustHost implements ModoCliente {
	
	private CloseableHttpClient httpClient;
	private CloseableHttpClient httpClients;

	public TrustHost() {
		httpClients = createClienteHttpsTrustAll();
		httpClient = HttpClients.custom().build();
	}

	@SuppressWarnings("deprecation")
	private CloseableHttpClient createClienteHttpsTrustAll() {
		return HttpClients.custom().setHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();
	}

	public HttpResponse executeRequest(String url, HttpUriRequest request) throws ClientProtocolException, IOException {
		if (url != null && url.toLowerCase().startsWith("https://")) {
			return httpClients.execute(request);
		} else {
			return httpClient.execute(request);
		}
	}
}
