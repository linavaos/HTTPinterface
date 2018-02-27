package HttpClientDemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

public class AddGoodCase01{
	
public static void main(String[] args) throws ClientProtocolException, IOException{
		
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
	    	    
	    //构造请求对象
	    HttpUriRequest httpUriRequest = RequestBuilder.post().setUri("https://www.zhoupu123.com/saas/login?username=18010001001&password=aA111111&veriryCode=").build();
	    
	    Action action = new Action();
	    action.getLoginResponse(httpClient, headerList, httpUriRequest);
	    
	    
	    httpUriRequest = RequestBuilder.post().setUri("https://www.zhoupu123.com/saas/erp/doc/brand?id=&name=测试商品&state=0").build();
	    action.getAddGoods(httpClient, headerList, httpUriRequest);	    	    	    
	}
}
