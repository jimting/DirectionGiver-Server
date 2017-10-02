package DG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetGeometry {
	double lat,lng;
	public double getlat(String url) throws Exception{
		String Result = null;
		//URL url = new URL(http);
		String url2 = new String();
		for (int j = 0; j < url.length(); j++)
		{
			if (url.substring(j, j + 1).matches("[\\u4e00-\\u9fa5]+"))
			{
				try {
					url2 = url2 + URLEncoder.encode(url.substring(j, j + 1),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			else
			{
				url2 = url2 + url.substring(j, j + 1).toString();
			}
		}
		InputStream is = null;
		try 
		{
			is = new URL(url2).openStream();
			StringBuilder sb = new StringBuilder();
            int cp;
			BufferedReader rd = null;
			rd = new BufferedReader(new InputStreamReader(is,"utf-8"));
			while ((cp = rd.read()) != -1) {
				     sb.append((char) cp);
				 }
			Result = sb.toString();
            //System.out.println(sb.toString());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //避免中文亂碼問題  
        finally 
        {
			is.close();
		}
		JSONObject jsonObj = new JSONObject(Result);//取得全部的資料
		JSONArray jsonArray = jsonObj.getJSONArray("results");//把feature抓出來
			JSONObject jsonItem = jsonArray.getJSONObject(0);//把第count資料抓出來
			lat = jsonItem.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
		return  lat;
		
	}
	public double getlng(String url) throws Exception{
		String Result = null;
		//URL url = new URL(http);
		String url2 = new String();
		for (int j = 0; j < url.length(); j++)
		{
			if (url.substring(j, j + 1).matches("[\\u4e00-\\u9fa5]+"))
			{
				try {
					url2 = url2 + URLEncoder.encode(url.substring(j, j + 1),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			else
			{
				url2 = url2 + url.substring(j, j + 1).toString();
			}
		}
		InputStream is = null;
		try 
		{
			is = new URL(url2).openStream();
			StringBuilder sb = new StringBuilder();
            int cp;
			BufferedReader rd = null;
			rd = new BufferedReader(new InputStreamReader(is,"utf-8"));
			while ((cp = rd.read()) != -1) {
				     sb.append((char) cp);
				 }
			Result = sb.toString();
            //System.out.println(sb.toString());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //避免中文亂碼問題  
        finally 
        {
			is.close();
		}
		JSONObject jsonObj = new JSONObject(Result);//取得全部的資料
		JSONArray jsonArray = jsonObj.getJSONArray("results");//把feature抓出來
			JSONObject jsonItem = jsonArray.getJSONObject(0);//把第count資料抓出來
			lng = jsonItem.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
			return  lng;
		
	}
	
}
