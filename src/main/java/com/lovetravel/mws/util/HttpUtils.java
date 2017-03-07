package com.lovetravel.mws.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpUtils Class.
 */
public class HttpUtils {
	private static HttpClient httpClient = null;
    /**
     * Set log4j
     */
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
    private static final String UTF_8 = "UTF-8";

    public static HttpResponseWrap sendHttpRequest(com.lovetravel.mws.util.HttpParams httpParams) throws IOException, ClientProtocolException {
        HttpRequestBase httpRequestMethod = null;
        switch (httpParams.getHttpMethod()) {
            case GET:
                httpRequestMethod = new HttpGet(httpParams.getTargetUrl());
                break;
            case POST:
                httpRequestMethod = new HttpPost(httpParams.getTargetUrl());
                setHttpBody((HttpPost) httpRequestMethod, httpParams);
                break;
            case PUT:
                httpRequestMethod = new HttpPut(httpParams.getTargetUrl());
                setHttpBody((HttpPut) httpRequestMethod, httpParams);
                break;
            case DELETE:
                httpRequestMethod = new HttpDelete(httpParams.getTargetUrl());
                break;
            case HEAD:
                httpRequestMethod = new HttpHead(httpParams.getTargetUrl());
                break;
            case OPTIONS:
                httpRequestMethod = new HttpOptions(httpParams.getTargetUrl());
                break;
            default:
                throw new RuntimeException("Illegal http method, http method:" + httpParams.getHttpMethod());
        }
        setHttpHeader(httpRequestMethod, httpParams);
        
        log.debug("Before send http request, httpParams:" + httpParams.toString());
        return execute(httpRequestMethod);
    }
    
    private static void setHttpHeader(HttpRequestBase httpRequestMethod, com.lovetravel.mws.util.HttpParams httpParams) {
        httpRequestMethod.addHeader(HTTP.CONTENT_ENCODING, UTF_8);
        //httpRequestMethod.addHeader(HTTP.CONTENT_TYPE, httpParams.getContentType());
        Map<String, String> httpHeader = httpParams.getHeader();
        if (httpHeader != null && httpHeader.size() > 0) {
            for (Entry<String, String> entry : httpHeader.entrySet()) {
                httpRequestMethod.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }
    
    private static void setHttpBody(HttpEntityEnclosingRequestBase reqBase, com.lovetravel.mws.util.HttpParams httpParams) {
        if (StringUtil.isNonEmpty(httpParams.getHttpBody())) {
            HttpEntity entity = prepareHttpRequestBody(httpParams.getHttpBody());
            reqBase.setEntity(entity);
            return;
        }
        if (httpParams.getHttpEntity() != null) {
            reqBase.setEntity(httpParams.getHttpEntity());
        }
    }
    
    private static HttpResponseWrap execute(HttpRequestBase httpRequestMethod) throws IOException, ClientProtocolException {
        HttpResponse resp = null;
        try {
            resp = sendRequest(httpRequestMethod);
            String responseBody = getResponseBody(resp);
            log.debug("response status:" + resp.getStatusLine().getStatusCode() 
            		+ ",responseBody:" + responseBody);
            
            return new HttpResponseWrap(resp, responseBody);
        } finally {
            if (httpRequestMethod != null) {
                httpRequestMethod.releaseConnection();
            }
        }
    }

    /**
     * HTTP GET Request
     * 
     * @param url
     * @return HttpResponse
     * @throws Exception
     */
    public static HttpResponse sendHttpGet(String url) throws IOException, ClientProtocolException {
        return sendHttpGet(url, null);
    }

    /**
     * HTTP GET Request
     * 
     * @param url
     * @param paramMap
     * @param reqBody
     * @return HttpResponse
     */
    public static HttpResponse sendHttpGet(String url, Map<String, String> paramMap) throws IOException, ClientProtocolException {
        HttpResponse resp = null;
        HttpGet httpGet = new HttpGet(url);
        try {
            if (paramMap != null) {
                HttpParams httpParams = setHttpParams(paramMap);
                httpGet.setParams(httpParams);
            }
            resp = sendRequest(httpGet);
        } finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
        }
        return resp;
    }

    /**
     * HTTP POST Request
     * 
     * @param url
     * @return
     * @throws Exception
     */
    public static HttpResponseWrap sendHttpPost(String url) throws IOException, ClientProtocolException {
        return sendHttpPost(url, null, null);
    }

    /**
     * HTTP POST Request
     * 
     * @param url
     * @param paramMap
     * @param httpBody
     * @return
     */
    public static HttpResponseWrap sendHttpPost(String url, Map<String, String> paramMap, String httpBody) 
        throws IOException, ClientProtocolException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_ENCODING, UTF_8);
        try {
            if (paramMap != null) {
            	List<NameValuePair> params = parseHttpParams(paramMap);
                httpPost.setEntity(new UrlEncodedFormEntity(params, UTF_8));
            }
            if (httpBody != null) {
                HttpEntity httpEntity = prepareHttpRequestBody(httpBody);
                httpPost.setEntity(httpEntity);
            }
            HttpResponse resp = sendRequest(httpPost);
            String responseBody = getResponseBody(resp);
            log.info("response status:" + resp.getStatusLine().getStatusCode() 
            		+ ",responseBody:" + responseBody);
            
            return new HttpResponseWrap(resp, responseBody);
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
    }

    /**
     * HTTP DELETE Request
     * 
     * @param url
     * @return
     */
    public static HttpResponse sendHttpDelete(String url) throws IOException, ClientProtocolException {
        return sendHttpDelete(url, null);
    }

    /**
     * HTTP DELETE Request
     * @param url
     * @param paramMap
     * @return
     */
    public static HttpResponse sendHttpDelete(String url, Map<String, String> paramMap) throws IOException, ClientProtocolException {
        HttpResponse resp = null;
        HttpDelete httpDelete = new HttpDelete(url);
        try {
            if (paramMap != null) {
                HttpParams httpParams = setHttpParams(paramMap);
                httpDelete.setParams(httpParams);
            }
            resp = sendRequest(httpDelete);
        } finally {
            if (httpDelete != null) {
                httpDelete.releaseConnection();
            }
        }
        return resp;
    }

    /**
     * Execute HTTP Request
     * 
     * @param httpMethod
     * @return HttpResponse
     */
    private static HttpResponse sendRequest(HttpRequestBase httpMethod) throws IOException, ClientProtocolException {
        HttpResponse resp = null;
        try {
            HttpClient client = getHttpsClient();
            resp = client.execute(httpMethod);
        } catch (ClientProtocolException e) {
            log.error("Client Protocol policy is error.", e);
            throw e;
        } catch (IOException e) {
            log.error("Client ", e);
            throw e;
        }
        return resp;
    }

    /**
     * Set HttpClient send https policy for Intel wake server
     * 
     * @return HttpClient
     */
    private static HttpClient getHttpsClient() {
    	if (httpClient != null) {
    		return httpClient;
    	}
    	synchronized (HttpUtils.class) {
    		if (httpClient != null) {
        		return httpClient;
        	}
	        PoolingClientConnectionManager cm = null;
	        try {
	            SchemeRegistry schemeRegistry = new SchemeRegistry();
	            schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
	            SSLContext sslContext;
	            sslContext = SSLContext.getInstance("TLS");
	            sslContext.init(null, null, null);
	            SSLSocketFactory sf = new SSLSocketFactory(sslContext);
	            schemeRegistry.register(new Scheme("https", 443, sf));
	            cm = new PoolingClientConnectionManager(schemeRegistry);
	        } catch (NoSuchAlgorithmException e) {
	            log.error("getHttpsClient error.", e);
	        } catch (KeyManagementException e) {
	            log.error("getHttpsClient error.", e);
	        }
	        httpClient = new DefaultHttpClient(cm);
	        return httpClient;
    	}
    }

    private static List<NameValuePair> parseHttpParams(Map<String, String> paramMap) {
    	if (paramMap == null) {
            return null;
        }
        List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
        	nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return nvps;
    }
    /**
     * Set HTTP Request Parameter
     * @param paramMap
     * @return
     */
    private static HttpParams setHttpParams(Map<String, String> paramMap) {
        if (paramMap == null) {
            return null;
        }
        HttpParams httpParams = new BasicHttpParams();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
        	httpParams.setParameter(entry.getKey(), entry.getValue());
        }
        return httpParams;
    }

    /**
     * Set HTTP Request Body
     * @param reqBody
     * @return
     */
    private static HttpEntity prepareHttpRequestBody(String reqBody) {
        StringEntity entity = null;
        try {
            entity = new StringEntity(reqBody);
        } catch (UnsupportedEncodingException e) {
            log.error("setHttpRequestBody error.", e);
        }
        return entity;
    }

    /**
     * Get HttpServletRequest Body
     * @param req
     * @return
     */
    public static String getHttpRequestBody(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
        } catch (Exception e) {
            log.error("getHttpRequestBody error.", e);
        }
        return sb.toString();
    }

    public static String getResponseBody(HttpResponse resp) {
        HttpEntity entity = resp.getEntity();
        log.debug("entity:" + entity);
        try {
            if (entity == null) {
                return "";
            }
            return EntityUtils.toString(entity);
        } catch (ParseException e) {
            log.error("parse response information faild.", e);
        } catch (IOException e) {
            log.error("read response information faild.", e);
        }
        return null;
    }
}