package DG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
public class GetRestaurantJson {
	
	public static NearBy[] getRest() throws Exception{
    	 String url = "http://gis.taiwan.net.tw/XMLReleaseALL_public/restaurant_C_f.json"; 
    	 String Result = null; 
    	 InputStream is = null; 
    	 try  
    	 { 
    	  is = new URL(url).openStream(); 
    	  StringBuilder sb = new StringBuilder(); 
    	           int cp; 
    	  BufferedReader rd = null; 
    	  rd = new BufferedReader(new InputStreamReader(is,"utf-8")); 
    	  while ((cp = rd.read()) != -1) { 
    	        sb.append((char) cp); 
    	    } 
    	  Result = sb.toString(); 
    	 } catch (MalformedURLException e1) { 
    	  // TODO Auto-generated catch block 
    	  e1.printStackTrace(); 
    	 } catch (IOException e1) { 
    	  // TODO Auto-generated catch block 
    	  e1.printStackTrace(); 
    	 }  
    	       finally  
    	       { 
    	  is.close(); 
    	 } 
    	 StringBuffer sb = new StringBuffer(Result); 
    	 sb.deleteCharAt(0); 
    	 Result = new String(sb); 
    	 JSONObject jsonObj = new JSONObject(Result);
    	 JSONObject jsonHead = jsonObj.getJSONObject("XML_Head");
    	 JSONObject jsonInfos = jsonHead.getJSONObject("Infos"); 
    	 JSONArray jsonArray = jsonInfos.getJSONArray("Info");  
    	  
    	 
    	 NearBy[] temp = new NearBy[jsonArray.length()]; 
    	 for(int count = 0;count < jsonArray.length();count++) 
    	 { 
    	 JSONObject jsonItem = jsonArray.getJSONObject(count);//���count��Ƨ�X�� 
    	  String add = jsonItem.getString("Add");
    	  String kind = jsonItem.getString("Class");
    	  String des = jsonItem.getString("Description"); 
    	  String name = jsonItem.getString("Name");
    	  String openTime = jsonItem.getString("Opentime");
    	  String px = jsonItem.getString("Px");
    	  String py = jsonItem.getString("Py");
    	  String tel = jsonItem.getString("Tel");
    	  String web = jsonItem.getString("Website");
    	  temp[count] = new NearBy(add,kind,des,name,openTime,px,py,tel,web); 
    	 } 

    	  
    	 return temp;
    }


}