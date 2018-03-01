package HttpClientDemo;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class Action {

	
	public  HttpResponse getLoginResponse(HttpClient httpClient,List<Header> headerList,String url,LinkedHashMap<String,JSONObject> logindetail) throws ClientProtocolException, IOException {

		HttpClientContext httpClientContext = HttpClientContext.create();
		String paramet = logindetail.toString().replace("{", "?").replace("}", "").replace(",", "&").replace(" ", "");
		String urlparamet=url+paramet;
		System.out.println(urlparamet);
		HttpUriRequest httpUriRequest = RequestBuilder.post().setUri(urlparamet).build();		
		httpClient.execute(httpUriRequest, httpClientContext);
		
		
		HttpResponse httpResponse = httpClient.execute(httpUriRequest);
		HttpEntity entity = httpResponse.getEntity();	
		System.out.println(EntityUtils.toString(entity));				
		return httpResponse;				
	}

	public  HttpResponse getAddGoods(HttpClient httpClient,List<Header> headerList,String url,LinkedHashMap<String,JSONObject> goodsdetail) throws ClientProtocolException, IOException {

		Set<String>  keys = goodsdetail.keySet();
		HttpClientContext httpClientContext = HttpClientContext.create();
		HttpResponse httpResponse = null;
		for(String key:keys){
			String paramet = goodsdetail.get(key).toString().toString().replace(",", "&").replace("{", "?").replace("}", "").replace(" ", "").replace(":", "=").replace("|", "%7c").replace("\"", "");
			String urlparamet = url+paramet;
			System.out.println("Stringurl"+urlparamet);
			HttpUriRequest httpUriRequest = RequestBuilder.post().setUri(urlparamet).build();
			httpResponse = httpClient.execute(httpUriRequest, httpClientContext);
			System.out.println(httpClientContext.getCookieStore());
			HttpEntity entity = httpResponse.getEntity();	
			System.out.println(EntityUtils.toString(entity));
		}

		return httpResponse;

	}
	
	
	public  HttpResponse getLoginResponseNo(HttpClient httpClient,List<Header> headerList,String url,LinkedHashMap<String,JSONObject> logindetail) throws ClientProtocolException, IOException {

		HttpClientContext httpClientContext = HttpClientContext.create();
		String paramet = logindetail.toString().replace("{", "&").replace("}", "").replace(",", "&").replace(" ", "");
		String urlparamet=url+paramet;
		System.out.println(urlparamet);
		HttpUriRequest httpUriRequest = RequestBuilder.post().setUri(urlparamet).build();
		httpClient.execute(httpUriRequest, httpClientContext);
		System.out.println(httpClientContext.getCookieStore());   
		HttpResponse httpResponse = httpClient.execute(httpUriRequest);				
		return httpResponse;				
	}
	
}

