package com.lovetravel.mws.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;

/**
 * Briefly describe what this class does.
 */
public class HttpParams {
    private String targetUrl;
    
    private Map<String, String> header;
    
    private String httpBody;
    
    private String contentType = "application/json";
    
    private HttpMethod httpMethod = HttpMethod.POST;
    
    private HttpEntity httpEntity;
    
    public HttpParams() {
        header = new HashMap<String, String>();
    }
    
    public void addHeader(String name, String value) {
        header.put(name, value);
    }
    
    public String removewHeader(String name) {
        return header.remove(name);
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public String getHttpBody() {
        return httpBody;
    }

    public void setHttpBody(String httpBody) {
        this.httpBody = httpBody;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }
    
    public HttpEntity getHttpEntity() {
        return httpEntity;
    }

    public void setHttpEntity(HttpEntity httpEntity) {
        this.httpEntity = httpEntity;
    }

    @Override
    public String toString() {
        return "HttpParams [targetUrl=" + targetUrl + ", header=" + header + ", httpBody=" + httpBody + ", contentType="
            + contentType + ", httpMethod=" + httpMethod + "]";
    }
    
}
