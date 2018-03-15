package cloud.cases;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;

import HttpClientDemo.SqlSessionUntil;
import cloud.bean.Goods;

import com.alibaba.fastjson.JSONObject;

public class CloudAction {

	public  HttpResponse getLoginResponseToJumpTwoTimes(HttpClient httpClient,List<Header> headerList,String url) throws ClientProtocolException, IOException {

		HttpClientContext httpClientContext = HttpClientContext.create();	
		String urlparamet=url+"";
		System.out.println(urlparamet);
		HttpUriRequest httpUriRequest = RequestBuilder.post().setUri(urlparamet).build();		
		httpClient.execute(httpUriRequest, httpClientContext);
		System.out.println(httpClientContext.getCookieStore());
		System.out.println(httpUriRequest);
		HttpResponse httpResponse = httpClient.execute(httpUriRequest);
		HttpEntity entity = httpResponse.getEntity();	
		System.out.println(EntityUtils.toString(entity));				
		return httpResponse;				
	}


	// 登录获得第一次跳转地址
	public  HttpResponse getLoginResponseToJumpOne(HttpClient httpClient,List<Header> headerList,String url,LinkedHashMap<String,JSONObject> logindetail) throws ClientProtocolException, IOException {

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



	// 最后一次跳转，直接传入httpClientContext不要在执行
	public  HttpResponse getLoginResponseJumpLast(HttpClient httpClient,List<Header> headerList,String url) throws ClientProtocolException, IOException {

		HttpClientContext httpClientContext = HttpClientContext.create();	
		String urlparamet=url+"";
		System.out.println(urlparamet);
		HttpUriRequest httpUriRequest = RequestBuilder.post().setUri(urlparamet).build();		
		HttpResponse httpResponse=httpClient.execute(httpUriRequest, httpClientContext);
		System.out.println(httpClientContext.getCookieStore());
		System.out.println(httpUriRequest);
		System.out.println("--------------------------------");
		HttpEntity entity = httpResponse.getEntity();	
		System.out.println(EntityUtils.toString(entity));				
		return httpResponse;				
	}


	public  HttpResponse getAddGoodsResponse(HttpClient httpClient,List<Header> headerList,String url,LinkedHashMap<String,JSONObject> goodsdetail) throws ClientProtocolException, IOException {

		String salestatement = "cloud.bean.GoodsMapper.getGoods";
		SqlSession sqlSession=SqlSessionUntil.SqlSession("conf.xml");
		Set<String>  keys = goodsdetail.keySet();
		HttpClientContext httpClientContext = HttpClientContext.create();
		HttpResponse httpResponse = null;
		for(String key:keys){
			//的获得单个商品的属性key
			Set<String>  goodskeys = goodsdetail.get(key).keySet();

			String goodsname=goodsdetail.get(key).get("name").toString();
			System.out.println(goodsname);
			Goods goodselement = sqlSession.selectOne(salestatement, goodsname);
			System.out.println(goodselement);
			if(goodselement!=null){
				String goodsid=goodselement.getId();
				String cid=goodselement.getCid();
				String paramet = goodsdetail.get(key).toString().toString().replace(",", "&").replace("{", "?").replace("}", "").replace(" ", "").replace(":", "=").replace("|", "%7c").replace("\"", "").replace("#id", goodsid).replace("#cid",cid);
				System.out.println(paramet);
				String urlparamet = url+paramet;
				System.out.println("Stringurl"+urlparamet);
				HttpUriRequest httpUriRequest = RequestBuilder.post().setUri(urlparamet).build();
				httpResponse = httpClient.execute(httpUriRequest, httpClientContext);
				System.out.println(httpClientContext.getCookieStore());
				HttpEntity entity = httpResponse.getEntity();	
				System.out.println(EntityUtils.toString(entity));
			}else{
				System.out.println(goodsname+"没查到相应商品");
			}

		}

		return httpResponse;

	}


}

