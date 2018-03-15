package cloud.cases;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import HttpClientDemo.JsonData;

import com.alibaba.fastjson.JSONObject;

public class EditGoodsCase {
	
	public static void editgoods(String casename01,String casename02) throws IOException{
		
		
		//构造自定义Header信息
	    List<Header> headerList = new ArrayList<Header>();
	    headerList.add(new BasicHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
	    headerList.add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36"));
	    headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate"));
	    headerList.add(new BasicHeader(HttpHeaders.CACHE_CONTROL, "max-age=0"));
	    headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));
	    headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8"));	
	   //构造自定义的HttpClient对象
	    HttpClient httpClient = HttpClients.custom().setDefaultHeaders(headerList).build();
	    	    
	   
	    CloudAction action = new CloudAction();
	    JsonData jsonData = new JsonData();
	    LinkedHashMap<String, JSONObject> logindetail=jsonData.getJson(casename01);
	    HttpResponse loginResponse=action.getLoginResponseToJumpOne(httpClient, headerList,"http://192.168.1.204:8090/dologin?redirect_url=http%3A%2F%2F192.168.1.212%3A9201%2Foss",logindetail);
	    InputStream inputStream=loginResponse.getEntity().getContent();
	    StringBuffer out = new StringBuffer();
	    byte b[] = new  byte[4096];
	    int l;
	    while((l=inputStream.read(b))!=-1){
	    	out.append(new String(b, 0, l));
	    	System.out.println("while1");
	    }
	    System.out.println(out);
	    String realurl=out.substring(out.indexOf("http"),out.indexOf("\"}"));
	    String realurl2 = realurl.replace("oss", "oss/");
	    inputStream.close();
	    loginResponse=action.getLoginResponseToJumpTwoTimes(httpClient, headerList,realurl);
	    loginResponse=action.getLoginResponseToJumpTwoTimes(httpClient, headerList,realurl2);
	  
	    loginResponse=action.getLoginResponseJumpLast(httpClient, headerList,"http://192.168.1.212:9201/oss/");
	    
	    LinkedHashMap<String, JSONObject> goodsdetail=jsonData.getJson(casename02);
	    HttpResponse addGoodsResponse=action.getAddGoodsResponse(httpClient, headerList, "http://192.168.1.212:9201/oss/goods/save", goodsdetail);

	    
	    
	    
	       
		
	}
}
