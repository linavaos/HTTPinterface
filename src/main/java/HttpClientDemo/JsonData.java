package HttpClientDemo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;



public class JsonData {

	public LinkedHashMap<String, JSONObject> getJson(String files) throws IOException{

		//先读取json文本内容\\src\\main\\java\\bo\\data.json
	    String s=readJsonFile(files);
		//解决json解析成map顺序错乱问题
		JSONObject jsonObj = new JSONObject(true);  
		LinkedHashMap  m= jsonObj.parseObject(s,LinkedHashMap.class,Feature.OrderedField); 
		return m;
	 

	}
	
	public LinkedHashMap<String, JSONArray> getJsonArray(String files) throws IOException{

		//先读取json文本内容\\src\\main\\java\\bo\\data.json
	    String s=readJsonFile(files);
		//解决json解析成map顺序错乱问题
		JSONObject jsonObj = new JSONObject(true);  
		LinkedHashMap  m= jsonObj.parseObject(s,LinkedHashMap.class,Feature.OrderedField); 
		return m;
	 

	}

	
	public LinkedHashMap<String, String> getJsonValue(String files) throws IOException{

		//先读取json文本内容\\src\\main\\java\\bo\\data.json
	    String s=readJsonFile(files);
		//解决json解析成map顺序错乱问题
		JSONObject jsonObj = new JSONObject(true);  
		LinkedHashMap  m= jsonObj.parseObject(s,LinkedHashMap.class,Feature.OrderedField); 
		return m;
	 

	}
	
	public LinkedHashMap<String, Object> getObject(String files) throws IOException{

		//先读取json文本内容\\src\\main\\java\\bo\\data.json
	    String s=readJsonFile(files);
		//解决json解析成map顺序错乱问题
		JSONObject jsonObj = new JSONObject(true);  
		LinkedHashMap  m= jsonObj.parseObject(s,LinkedHashMap.class,Feature.OrderedField); 
		return m;
	 

	}
	
	
	//直接返回json字符串
	public String getJsonString(String files) throws IOException{

		//先读取json文本内容\\src\\main\\java\\bo\\data.json
	    String s=readJsonFile(files);
	    return s;
	}
	
	
	
   public static JSONArray ObjectTOJsonArray(Object object){
	   
	   String objectString = object.toString();
	   JSONArray jsonArrry=JSON.parseArray(objectString);
	   
	   return jsonArrry;
	      
   }


	public static String readJsonFile(String  jsonFilePath) throws IOException{

		String path = System.getProperty("user.dir") +  jsonFilePath;
		//同过二进制方式打开一个文件
		FileInputStream fileInputStream = new FileInputStream(path);
		//指定二进制的字符编码,这里fileInputStream是InputStream的子类1
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String s="";
		String temp = null;
		while((temp=reader.readLine())!=null){
			s=s+temp;
		}
		reader.close();
		return s;
	}
}
