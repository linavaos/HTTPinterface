package saas.cases;

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
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import saas.bean.Account;
import saas.bean.AccountMapper;
import saas.bean.Dept;
import saas.bean.DeptMapper;
import saas.bean.Supplier;
import saas.bean.SupplierMapper;
import saas.bean.User;
import saas.bean.UserMapper;
import saas.bean.Warehouse;
import saas.bean.WarehouseMapper;
import HttpClientDemo.JsonData;
import HttpClientDemo.SqlSessionUntil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SaasAction {


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


	public  HttpResponse getSavepurchaseHttpResponse(HttpClient httpClient,List<Header> headerList,String url,LinkedHashMap<String,Object> purchasebill) throws ClientProtocolException, IOException {

		SqlSession sqlSession=SqlSessionUntil.SqlSession("saasmybatisconf.xml");
		SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
		WarehouseMapper warehouseMapper = sqlSession.getMapper(WarehouseMapper.class);
		AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);

		Supplier supplier = supplierMapper.getSupplier(purchasebill.get("supplierName").toString());
		User user = userMapper.getUser(purchasebill.get("workOperId").toString());
		Dept dept = deptMapper.getDept(purchasebill.get("deptid").toString());
		Warehouse warehouse = warehouseMapper.getWarehouse(purchasebill.get("warehouseId").toString());
		Account account = null;



		//获得支付方式列表塞类型和id计算支付总金额
		JSONArray accountlist = JsonData.ObjectTOJsonArray(purchasebill.get("accounts").toString());
		Double nowPaidAmount=0.0;
		for(int i=0;i<accountlist.size();i++){
			String accountname=accountlist.getJSONObject(i).get("id").toString();
			account = accountMapper.getAccount(accountname);
			accountlist.getJSONObject(i).put("id", account.getId());
			accountlist.getJSONObject(i).put("type", account.getType());
			Double amount = Double.parseDouble(accountlist.getJSONObject(i).get("amount").toString());
			nowPaidAmount= nowPaidAmount+amount;
		}
		
		
		

		//计算totalAmount和塞入商品相关联数据
		JSONArray detaillist = JsonData.ObjectTOJsonArray(purchasebill.get("details").toString());
		Double totalAmount=0.0;
		for(int i=0;i<detaillist.size();i++){
			Double quantity=Double.parseDouble(detaillist.getJSONObject(i).get("quantity").toString());
			Double realPrice=Double.parseDouble(detaillist.getJSONObject(i).get("realPrice").toString());
			totalAmount=totalAmount+quantity*realPrice;

		}





		//构造完整的Json
		purchasebill.put("id", "");
		purchasebill.put("redFlag","");
		purchasebill.put("approveFlag","");
		purchasebill.put("orderBillId","");
		purchasebill.put("orderBillNo","");
		purchasebill.put("deliveryType","2");
		purchasebill.put("deliveryType","");
		purchasebill.put("mpState","");
		purchasebill.put("supplierId",supplier.getId());
		purchasebill.put("workOperId",user.getId());
		purchasebill.put("deptid",dept.getId());
		purchasebill.put("warehouseId",warehouse.getId());
		purchasebill.put("totalAmount",totalAmount);
		//直接塞，和采购没啥关系
		purchasebill.put("totalSaleamount","");
		//和税额有关暂时不管
		purchasebill.put("totalRateamount","");
		//优惠后金额
		Double afterDiscountAmount = totalAmount-Double.parseDouble(purchasebill.get("nowDiscountAmount").toString());
		purchasebill.put("afterDiscountAmount",afterDiscountAmount);
		//把封装好的list在塞回到单据json中
		purchasebill.put("accounts", accountlist);
		purchasebill.put("nowPaidAmount", nowPaidAmount);
		//欠款金额
		purchasebill.put("nowLeftAmount", afterDiscountAmount-nowPaidAmount);








		System.out.println(supplier);
		System.out.println(purchasebill);
		HttpClientContext httpClientContext = HttpClientContext.create();

		HttpResponse httpResponse = null;



		return httpResponse;

	}

}

