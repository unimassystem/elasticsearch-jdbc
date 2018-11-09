package com.elasticsearch.jdbc;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class QueryExecutor {
	
	private final OkHttpClient httpClient;
	private final URI uri;
	
	
	public QueryExecutor(URI uri, Properties info) {
		this.httpClient = new OkHttpClient.Builder().build();
		this.uri = uri;
	}
	
	public Response startQuery(String query) throws IOException
    {
		String url = "http://" + uri.getHost() + ":" + uri.getPort() + "/esql";		
		RequestBody body  = new FormBody.Builder().add("sql", query).build();
		Request request = new Request.Builder().url(url).post(body).build();
		return httpClient.newCall(request).execute(); 
    }
	
	
	
	public void close() {
		
	}

}
