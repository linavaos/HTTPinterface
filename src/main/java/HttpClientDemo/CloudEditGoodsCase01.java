package HttpClientDemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElementIterator;

import com.alibaba.fastjson.JSONObject;

public class CloudEditGoodsCase01 {
	
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
	    HttpClient httpClient = HttpClients.custom().setDefaultHeaders(headerList).build();
	    	    
	   
	    Action action = new Action();
	    JsonData jsonData = new JsonData();
	    LinkedHashMap<String, JSONObject> logindetail=jsonData.getJson("/src/main/java/HttpClientDemo/cloudlogin.json");
	    HttpResponse loginResponse=action.getLoginResponseNo(httpClient, headerList,"http://192.168.1.204:8090/dologin?redirect_url=http%3A%2F%2Fwms-test.zhoupudata.com%2Foss",logindetail);
	    InputStream inputStream=loginResponse.getEntity().getContent();
	    StringBuffer out = new StringBuffer();
	    byte b[] = new  byte[4096];
	    int l;
	    while((l=inputStream.read(b))!=-1){
	    	out.append(new String(b, 0, l));
	    }
	    System.out.println(out);
	    String realurl=out.substring(out.indexOf("http"),out.indexOf("\"}"));
	    String realurl2 = realurl.replace("oss", "oss/");
	    loginResponse=action.getLoginResponse(httpClient, headerList,realurl,logindetail);
	    loginResponse=action.getLoginResponse(httpClient, headerList,realurl2,logindetail);
		
	}
}
