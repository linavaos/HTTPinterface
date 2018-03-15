package wms.cases;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import HttpClientDemo.JsonData;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class WmsAcceptancestep {
	
	public static void main(String[] args) throws IOException{
		
		
		//构造自定义Header信息
	    List<Header> headerList = new ArrayList<Header>();
	    headerList.add(new BasicHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
	    headerList.add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36"));
	    headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate"));
	    headerList.add(new BasicHeader(HttpHeaders.CACHE_CONTROL, "max-age=0"));
	    headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));
	    headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8"));	
	   //构造自定义的HttpClient对象
	    CloseableHttpClient httpClient = HttpClients.custom().setDefaultHeaders(headerList).build();
	    	    
	   
	    WmsAction action = new WmsAction();
	    JsonData jsonData = new JsonData();
	    LinkedHashMap<String, JSONObject> logindetail=jsonData.getJson("/src/main/java/HttpClientDemo/wmslogin.json");
	    HttpResponse loginResponse=action.getLoginResponseToJumpOne(httpClient, headerList,"http://192.168.1.204:8090/dologin?redirect_url=http%3A%2F%2Fwms-test.zhoupudata.com%2Fwms",logindetail);
	    InputStream inputStream=loginResponse.getEntity().getContent();
	    StringBuffer out = new StringBuffer();
	    byte b[] = new  byte[4096];
	    int l;
	    while((l=inputStream.read(b))!=-1){
	    	out.append(new String(b, 0, l));
	    }
	    String realurl=out.substring(out.indexOf("http"),out.indexOf("\"}"));
	    String realurl2 = realurl.replace("wms?", "wms/?");
	    //关闭这个跳转即可
	    //loginResponse=action.getLoginResponseToJumpTwoTimes(httpClient, headerList,realurl);
	    loginResponse=action.getLoginResponseToJumpTwoTimes(httpClient, headerList,realurl2);
	    loginResponse=action.getLoginResponseJumpLast(httpClient, headerList,"http://wms-test.zhoupudata.com/wms/");
	    loginResponse=action.getLoginResponseJumpLast(httpClient, headerList,"http://wms-test.zhoupudata.com/wms/main");
	    String stepurl = "http://wms-test.zhoupudata.com/wms/stockin/acceptancebill/acceptancestep?";
	    String token=loginResponse.getFirstHeader("Set-Cookie").getValue().replace("XSRF-TOKEN", "_csrf").replace("; Path=/", "");
	    LinkedHashMap<String, Object> acceptancebilldetail=jsonData.getObject("/src/main/java/HttpClientDemo/acceptancebill.json");
	    action.getAcceptancestepResponse(httpClient, headerList, stepurl, acceptancebilldetail,token);
	    
	    
	  

	    
	    
	    
	       
		
	}
}
