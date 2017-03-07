package com.lovetravel.mws.util;

import org.apache.http.HttpResponse;

public class HttpResponseWrap {
	private HttpResponse httpResponse;
	
	private String responseBody;

	public HttpResponseWrap(HttpResponse httpResponse, String responseBody) {
		this.httpResponse = httpResponse;
		this.responseBody = responseBody;
	}
	
	public HttpResponse getHttpResponse() {
		return httpResponse;
	}

	public String getResponseBody() {
		return responseBody;
	}
	
}
