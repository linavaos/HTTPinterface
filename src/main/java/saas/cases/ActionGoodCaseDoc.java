package saas.cases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.junit.Test;

import HttpClientDemo.JsonData;
import Utils.EnvironmentUtil;

import com.alibaba.fastjson.JSONObject;

public class ActionGoodCaseDoc{


	private static List<Header> headerList;
	private static String url = EnvironmentUtil.webUrl;

	static{
		//构造自定义Header信息
		headerList = new ArrayList<Header>();
		headerList.add(new BasicHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
		headerList.add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36"));
		headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate"));
		headerList.add(new BasicHeader(HttpHeaders.CACHE_CONTROL, "max-age=0"));
		headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));
		headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8"));		
	}

	public static void addgoods(HttpClient httpClient,String casename01) throws IOException{

		//构造自定义的HttpClient对象
		httpClient = HttpClients.custom().setDefaultHeaders(headerList).build();
		SaasAction action = new SaasAction();
		JsonData jsonData = new JsonData();
		LinkedHashMap<String, JSONObject> goodsdetail=jsonData.getJson(casename01);
		action.getAddGoods(httpClient, headerList,url+"/erp/doc/goods",goodsdetail);	    	    	    
	}
}
