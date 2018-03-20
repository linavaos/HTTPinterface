package Demo;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Demo2 {

	public static void main(String[] args) throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();  
		HttpGet httpget = new HttpGet("https://www.zhoupu123.com/saas/");  
		CloseableHttpResponse response =httpclient.execute(httpget);
		//获得返回的实体
		HttpEntity indexEntity = response.getEntity();
		InputStream inputStream = null;
		if(indexEntity!=null){
			//将返回一个实体的内容流
			inputStream = indexEntity.getContent();
			byte b[] = new byte[2048];
			int temp;
			int i=0;
			while((temp=inputStream.read())!=-1){
                 b[i] = (byte)temp;
                 i++;
			}
			inputStream.close();
			System.out.println(new String(b,0,i));
		}

	}
}

