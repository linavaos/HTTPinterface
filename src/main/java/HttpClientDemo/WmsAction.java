package HttpClientDemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.lf5.util.AdapterLogRecord;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import bean.wms.Accepstep;

import bean.wms.Goods;

public class WmsAction {

	public static int count=0;

	public  HttpResponse getLoginResponseToJumpTwoTimes(HttpClient httpClient,List<Header> headerList,String url) throws ClientProtocolException, IOException {


		HttpClientContext httpClientContext = HttpClientContext.create();	
		String urlparamet=url+"";
		System.out.println(urlparamet);
		HttpUriRequest httpUriRequest = RequestBuilder.get().setUri(urlparamet).build();		
		httpClient.execute(httpUriRequest, httpClientContext);
		System.out.println(httpUriRequest);
		HttpResponse httpResponse = httpClient.execute(httpUriRequest);
		System.out.println(httpClientContext.getCookieStore());
		HttpEntity entity = httpResponse.getEntity();	
		System.out.println(EntityUtils.toString(entity));
		return httpResponse;				
	}


	// 登录获得第一次跳转地址
	public  HttpResponse getLoginResponseToJumpOne(HttpClient httpClient,List<Header> headerList,String url,LinkedHashMap<String,JSONObject> logindetail) throws ClientProtocolException, IOException {

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



	// 最后一次跳转，直接传入httpClientContext不要在执行
	public  HttpResponse getLoginResponseJumpLast(HttpClient httpClient,List<Header> headerList,String url) throws ClientProtocolException, IOException {

		HttpClientContext httpClientContext = HttpClientContext.create();	
		String urlparamet=url+"";
		System.out.println(urlparamet);
		HttpUriRequest httpUriRequest = RequestBuilder.get().setUri(urlparamet).build();		
		HttpResponse httpResponse=httpClient.execute(httpUriRequest, httpClientContext);
		System.out.println(httpClientContext.getCookieStore());
		System.out.println(httpUriRequest);
		HttpEntity entity = httpResponse.getEntity();	
		System.out.println(EntityUtils.toString(entity));				
		return httpResponse;				
	}

	public  HttpResponse getAddGoodsResponse(HttpClient httpClient,List<Header> headerList,String url,LinkedHashMap<String,JSONArray> goodsdetail,String token) throws ClientProtocolException, IOException {

		String salestatement = "bean.wms.GoodsMapper.getGoods";
		CreateConnection  createConnection = new CreateConnection("conf2.xml");
		SqlSession sqlSession=createConnection.getSqlSession();
		Set<String>  keys = goodsdetail.keySet();
		HttpClientContext httpClientContext = HttpClientContext.create();
		HttpPut  httpPut  = new HttpPut(url);
		HttpResponse httpResponse = null;
		for(String key:keys){
			//因为一个list中的是一个商品所有直接取第一个商品的名字就可以了
			String goodsname=goodsdetail.get(key).getJSONObject(0).getString("name");
			System.out.println(goodsname);
			List<Goods> onegoodslist = sqlSession.selectList(salestatement,goodsname);
			int index=0;
			for(Goods goods:onegoodslist){
				System.out.println(goods.getId());
				System.out.println(goods.getSonId());
				goodsdetail.get(key).getJSONObject(index).put("productCode",goods.getProductCode());
				goodsdetail.get(key).getJSONObject(index).put("id",goods.getId());
				goodsdetail.get(key).getJSONObject(index).put("code",goods.getCode());
				goodsdetail.get(key).getJSONObject(index).put("sonId",goods.getId());
				index++;				
			}
			String parameter=goodsdetail.get(key).toString().replace("csrfvalue",token.replace("_csrf=", ""));
			System.out.println(token);
			System.out.println(parameter);
			System.out.println("--------------");
			StringEntity se = new StringEntity(parameter,"utf-8");
			se.setContentType("application/json");
			httpPut.setEntity(se);
			System.out.println(httpPut);
			httpResponse=httpClient.execute(httpPut, httpClientContext);
			HttpEntity entity = httpResponse.getEntity();	
			System.out.println(EntityUtils.toString(entity));	
		}	




		return httpResponse;

	}



	public  HttpResponse getAcceptancestepResponse(HttpClient httpClient,List<Header> headerList,String url,LinkedHashMap<String, Object> acceptancestepdetail,String token) throws ClientProtocolException, IOException {

		String salestatement = "bean.wms.AccepstepMapper.getAccepstep";
		CreateConnection  createConnection = new CreateConnection("conf2.xml");
		SqlSession sqlSession=createConnection.getSqlSession();
		String taskCode=acceptancestepdetail.get("taskCode").toString();
		String Acceptancestepdetail=acceptancestepdetail.get("detail").toString();
		JSONArray steplist=JSON.parseArray(Acceptancestepdetail);
		Accepstep accepstep = null;
		List<Accepstep> accepsteplist = new ArrayList<Accepstep>();
		for(int i=0;i<steplist.size();i++){
			String step = steplist.get(i).toString();
			JSONObject stepJSON=JSON.parseObject(step);
			accepstep = new Accepstep();
			accepstep.setTaskCode(taskCode);
			accepstep.setGoodsname(stepJSON.get("goodsname").toString());
			accepstep.setUnitName(stepJSON.get("unitName").toString());
			accepstep.setProductionDate(stepJSON.getString("productionDate").toString());
			accepstep.setExpireDate(stepJSON.getString("expireDate").toString());
			accepstep.setGoodsBatch(stepJSON.getString("goodsBatch").toString());
			accepstep.setStorageNumber(Double.parseDouble(stepJSON.getString("storageNumber").toString()));
			accepstep.setGoodsQuality(stepJSON.getString("goodsQuality").toString());
			Accepstep accepsteptemp = sqlSession.selectOne(salestatement , accepstep);
			accepstep.setTaskid(accepsteptemp.getTaskid());
			accepstep.setBillType(accepsteptemp.getBillType());
			accepstep.setGoodsId(accepsteptemp.getGoodsId());
			accepstep.setGoodsCode(accepsteptemp.getGoodsCode());
			accepstep.setSrcstorageNumber(accepsteptemp.getSrcstorageNumber());
			accepstep.setCsrf(token);
			accepstep.setPerStackNumber(accepsteptemp.getPerStackNumber());
			accepstep.setAcceDetailIdString(accepsteptemp.getAcceDetailIdString());
			accepstep.setAcceDetailId();
			accepsteplist.add(accepstep);
		}

		for(Accepstep accstep1:accepsteplist){
			System.out.println(accstep1.getTaskid());
			System.out.println(accstep1.getBillType());
			System.out.println(accstep1.getGoodsId());
			System.out.println(accstep1.getGoodsCode());
			System.out.println(accstep1.getProductionDate());
			System.out.println(accstep1.getExpireDate());
			System.out.println(accstep1.getGoodsBatch());
			System.out.println(accstep1.getPerStackNumber());
			System.out.println(accstep1.getGoodsQuality());
			System.out.println(accstep1.getStorageNumber());
			System.out.println(accstep1.getCsrf());
			System.out.println(accstep1.getSrcstorageNumber());
			System.out.println(accstep1.getAcceDetailId()[0]);
		}







		return null;

	}

}

