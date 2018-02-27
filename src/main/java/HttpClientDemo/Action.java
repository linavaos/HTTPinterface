package HttpClientDemo;

import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.util.EntityUtils;

public class Action {

	public  HttpResponse getLoginResponse(HttpClient httpClient,List<Header> headerList,HttpUriRequest httpUriRequest) throws ClientProtocolException, IOException {

		//创建一个HttpContext对象
		HttpClientContext httpClientContext = HttpClientContext.create();
		httpClient.execute(httpUriRequest, httpClientContext);
		System.out.println(httpClientContext.getCookieStore());   
		HttpResponse httpResponse = httpClient.execute(httpUriRequest);
		HttpEntity entity = httpResponse.getEntity();	
		System.out.println(EntityUtils.toString(entity));				
		return httpResponse;				
	}
	
	public  HttpResponse getAddGoods(HttpClient httpClient,List<Header> headerList,HttpUriRequest httpUriRequest) throws ClientProtocolException, IOException {			
		//创建一个HttpContext对象
		HttpClientContext httpClientContext = HttpClientContext.create();
		HttpResponse httpResponse = httpClient.execute(httpUriRequest, httpClientContext);
		System.out.println(httpClientContext.getCookieStore());
		HttpEntity entity = httpResponse.getEntity();	
		System.out.println(EntityUtils.toString(entity));
		return httpResponse;						
	}						
}

