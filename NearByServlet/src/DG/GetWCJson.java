package DG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
public class GetWCJson {
	
	public static NearByWC[] getWC() throws Exception{
    	 String url = "http://opendata.epa.gov.tw/ws/Data/OTH01092/?%24skip=0&%24top=1000&format=json"; 
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
    	 //sb.deleteCharAt(0); 
    	 Result = new String(sb); 
    	 //Result = "[" + Result;
    	 System.out.println(Result);
    	 JSONArray jsonArray = new JSONArray(Result);
    	  
    	 
    	 NearByWC[] temp = new NearByWC[jsonArray.length()]; 
    	 for(int count = 0;count < jsonArray.length();count++) 
    	 { 
    	 JSONObject jsonItem = jsonArray.getJSONObject(count);
    	  String country = jsonItem.getString("Country");
    	  String city = jsonItem.getString("City");
    	  String admin = jsonItem.getString("Administration"); 
    	  String name = jsonItem.getString("Name");
    	  String add = jsonItem.getString("Address");
    	  String type = jsonItem.getString("Type");
    	  String lng = jsonItem.getString("Longitude");
    	  String grade = jsonItem.getString("Grade");
    	  String lat = jsonItem.getString("Latitude");
    	  temp[count] = new NearByWC(country,city,admin,name,add,type,lng,grade,lat); 
    	 }
    	 
    	 return temp;
    }


}