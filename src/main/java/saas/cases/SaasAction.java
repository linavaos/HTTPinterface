package saas.cases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.lf5.util.AdapterLogRecord;

import saas.bean.Account;
import saas.bean.AccountMapper;
import saas.bean.Dept;
import saas.bean.DeptMapper;
import saas.bean.Detail;
import saas.bean.Goods;
import saas.bean.GoodsMapper;
import saas.bean.PurchaseBill;
import saas.bean.Supplier;
import saas.bean.SupplierMapper;
import saas.bean.User;
import saas.bean.UserMapper;
import saas.bean.Warehouse;
import saas.bean.WarehouseMapper;
import HttpClientDemo.JsonData;
import HttpClientDemo.SqlSessionUntil;

import com.alibaba.fastjson.JSON;
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


	public  PurchaseBill getSavepurchaseHttpResponse(HttpClient httpClient,List<Header> headerList,String url,LinkedHashMap<String,Object> purchasebill) throws ClientProtocolException, IOException, IllegalArgumentException, IllegalAccessException, NoSurchGoodsException {

		SqlSession sqlSession=SqlSessionUntil.SqlSession("saasmybatisconf.xml");
		SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
		WarehouseMapper warehouseMapper = sqlSession.getMapper(WarehouseMapper.class);
		AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
		GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);

		Supplier supplier = supplierMapper.getSupplier(purchasebill.get("supplierName").toString());
		User user = userMapper.getUser(purchasebill.get("workOperName").toString());
		Dept dept = deptMapper.getDept(purchasebill.get("deptid").toString());
		Warehouse warehouse = warehouseMapper.getWarehouse(purchasebill.get("warehouseId").toString());		
		Account account = null;
		Goods goods = null;
		Detail detail = null;
		List<Account> accountList = new ArrayList<Account>();
		List<Detail> detailList = new ArrayList<Detail>();
		PurchaseBill  purchaseBill2= new   PurchaseBill();




		//获得支付方式列表塞类型和id计算支付总金额以及支付明细
		JSONArray accountlist = JsonData.ObjectTOJsonArray(purchasebill.get("accounts").toString());
		Double nowPaidAmount=0.0;
		for(int i=0;i<accountlist.size();i++){
			String accountname=accountlist.getJSONObject(i).get("id").toString();
			account = accountMapper.getAccount(accountname);
			accountlist.getJSONObject(i).put("id", account.getId());
			accountlist.getJSONObject(i).put("type", account.getType());
			Double amount = Double.parseDouble(accountlist.getJSONObject(i).get("amount").toString());
			nowPaidAmount= nowPaidAmount+amount;
			account.setId(account.getId());
			account.setName(account.getName());
			account.setType(account.getType());
			account.setAmount(amount);
			account.setSeq(i);
			accountList.add(account);
		}
		
		

		JSONArray goodlist = JsonData.ObjectTOJsonArray(purchasebill.get("details").toString());
		for(int i=0;i<goodlist.size();i++){
			detail = new Detail();
			goods = goodsMapper.getGoods(goodlist.getJSONObject(i).getString("goodsname").toString());
			detail.setGoodsId(goods.getId());
			detail.setGoodsName(goods.getName());
			detail.setQuantity(goodlist.getJSONObject(i).getDouble("quantity"));
			detail.setRealPrice(goodlist.getJSONObject(i).getDouble("realPrice"));
			detail.setSubAmount(goodlist.getJSONObject(i).getDouble("quantity")*goodlist.getJSONObject(i).getDouble("realPrice"));
			detail.setSeq(i);
			//判断单位
			String unitname=goodlist.getJSONObject(i).getString("currUnitName").toString();
			if(unitname.equals(goods.getBaseUnitName())){
				detail.setBarcode(goods.getBaseBarCode());
				detail.setCurrUnitName(goods.getBaseUnitName());
				detail.setCurrUnitId(goods.getBaseUnitId());
			}else if(unitname.equals(goods.getMidUnitName())){
				detail.setBarcode(goods.getMidBarcode());
				detail.setCurrUnitName(goods.getMidUnitName());
				detail.setCurrUnitId(goods.getMidUnitId());

			}else if (unitname.equals(goods.getPkgUnitName())){
				detail.setBarcode(goods.getPkgBarcode());
				detail.setCurrUnitName(goods.getPkgUnitName());
				detail.setCurrUnitId(goods.getPkgUnitId());

			}else{
				System.out.println("商品单位不存在");
				throw new NoSurchGoodsException("商品单位不存在");
			}

			detailList.add(detail);

		}



		//计算totalAmount和塞入商品相关联数据
		JSONArray detaillist = JsonData.ObjectTOJsonArray(purchasebill.get("details").toString());
		Double totalAmount=0.0;
		for(int i=0;i<detaillist.size();i++){
			Double quantity=Double.parseDouble(detaillist.getJSONObject(i).get("quantity").toString());
			Double realPrice=Double.parseDouble(detaillist.getJSONObject(i).get("realPrice").toString());
			totalAmount=totalAmount+quantity*realPrice;

		}



		purchaseBill2.setType(purchasebill.get("type").toString());
		purchaseBill2.setDeliveryType("1");
		purchaseBill2.setSupplierId(supplier.getId());
		purchaseBill2.setSupplierName(supplier.getName());
		purchaseBill2.setWorkOperId(user.getId());
		purchaseBill2.setDeptId(dept.getId());
		purchaseBill2.setWarehouseId(warehouse.getId());
		purchaseBill2.setWorkTime(purchasebill.get("workTime").toString());
		purchaseBill2.setTotalAmount(totalAmount);
		purchaseBill2.setNowPaidAmount(nowPaidAmount);
		purchaseBill2.setNowDiscountAmount(Double.parseDouble(purchasebill.get("nowDiscountAmount").toString()));
		purchaseBill2.setAfterDiscountAmount(totalAmount-purchaseBill2.getNowDiscountAmount());
		purchaseBill2.setNowLeftAmount(purchaseBill2.getAfterDiscountAmount()-nowPaidAmount);
		purchaseBill2.setAccountlist(accountList);
		purchaseBill2.setDetaillist(detailList);
	


		HttpClientContext httpClientContext = HttpClientContext.create();




		String urlAndParam = url+purchaseBill2.tourlString();
		
	    System.out.println(urlAndParam);


		HttpUriRequest httpUriRequest = RequestBuilder.post().setUri(urlAndParam).build();	



		HttpResponse httpResponse = httpClient.execute(httpUriRequest,httpClientContext);
		System.out.println(httpUriRequest);
		JSONObject jsonObject=JSON.parseObject(EntityUtils.toString(httpResponse.getEntity()));
		Object map =jsonObject.get("map");
		Map<String,Object> billidjson = (Map<String, Object>) ((Map<String, Object>) (((Map<String, Object>) jsonObject.get("map"))).get("billId"));
		purchaseBill2.setId(billidjson.get("id").toString());
		purchaseBill2.setBillNo(billidjson.get("billNo").toString());
		return purchaseBill2;

	}





	public PurchaseBill getApprovepurchaseHttpResponse(HttpClient httpClient, List<Header> headerList, String url,PurchaseBill purchaseBill) throws ClientProtocolException, IOException, IllegalArgumentException, IllegalAccessException {

		String urlAndParam = url+purchaseBill.tourlString();


		HttpUriRequest httpUriRequest = RequestBuilder.post().setUri(urlAndParam).build();	

		HttpClientContext httpClientContext = HttpClientContext.create();

		HttpResponse httpResponse = httpClient.execute(httpUriRequest,httpClientContext);


		return purchaseBill;

	}



}

