package saas.cases;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import HttpClientDemo.JsonData;

//模拟手机发送请求
public class PushUserPositionCase {
	
	public static void main(String[] args) throws IOException{
		
		 HttpClient httpClient = HttpClients.createDefault();
		 
		 HttpPost httpPost = new HttpPost("http://192.168.1.212/saas/OpenApi");
		 httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
		 httpPost.setHeader("Connection", "Keep-Alive");
		 JsonData jsonData = new JsonData();
		 String parameter = jsonData.getJsonString("/src/main/java/saas/cases/pushUserPosition.json");
		 System.out.println(parameter);
		 
		 StringEntity se = new StringEntity(parameter);
	     se.setContentType("text/json");
	     httpPost.setEntity(se);
	     HttpResponse response = httpClient.execute(httpPost);
	     HttpEntity entity = response.getEntity();
	     String result = EntityUtils.toString(entity, "UTF-8");
	     System.out.println(result);
	        
	      

	}

}
