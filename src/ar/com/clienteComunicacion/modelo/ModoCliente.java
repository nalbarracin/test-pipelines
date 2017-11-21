package ar.com.clienteComunicacion.modelo;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;

public interface ModoCliente {	
	public abstract HttpResponse executeRequest(String url, HttpUriRequest request) throws ClientProtocolException, IOException;
}
