package saas.cases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicHeader;
import HttpClientDemo.JsonData;
import Utils.EnvironmentUtil;
import saas.bean.PurchaseBill;


public class ActionPurchaseBill{

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


	public static PurchaseBill addpurchaseBill(String casename01,HttpClient httpClient) throws IOException, IllegalArgumentException, IllegalAccessException, NoSurchGoodsException{


		SaasAction action = new SaasAction();
		JsonData jsonData = new JsonData();

		LinkedHashMap<String, Object> purchasebill=jsonData.getObject(casename01);
		PurchaseBill purchaseBill=action.getSavepurchaseHttpResponse(httpClient, headerList, url+"/erp/bill/purchase/", purchasebill);
		return purchaseBill;   	    	    
	}

	
	//直接传对象审核，通常是保存后获得purchaseBill后
	public static void aprovepurchaseBill(HttpClient httpClient,PurchaseBill purchaseBill) throws ClientProtocolException, IOException, IllegalArgumentException, IllegalAccessException {
		SaasAction action = new SaasAction();
		action.getApprovepurchaseHttpResponse(httpClient,headerList,url+"/erp/bill/purchase/approve",purchaseBill);
	}
}
