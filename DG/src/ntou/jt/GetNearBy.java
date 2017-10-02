package ntou.jt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetNearBy 
{
	public static NearBy[] getRest(double X,double Y,double userPosition) throws Exception
	{
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
		} //避免中文亂碼問題  
        finally 
        {
			is.close();
		}
		StringBuffer sb = new StringBuffer(Result);
		sb.deleteCharAt(0);
		Result = new String(sb);
		JSONObject jsonObj = new JSONObject(Result);//取得全部的資料
		System.out.println(jsonObj);
		JSONObject jsonInfos = jsonObj.getJSONObject("Infos");//把Infos抓出來
		JSONArray jsonArray = jsonInfos.getJSONArray("Info");//成功把Info抓出來了
		//ArrayList<NearBy[]> totalNearBy = new ArrayList<NearBy[]>();
		
		//找餐廳
		NearBy[] temp = new NearBy[jsonArray.length()];
		for(int count = 0;count < jsonArray.length();count++)
		{
			JSONObject jsonItem = jsonArray.getJSONObject(count);//把第count資料抓出來
			String add = jsonItem.getString("Add");
			String name = jsonItem.getString("Name");
			int kind = jsonItem.getInt("Class");
			String description = jsonItem.getString("Description");
			location position = new location(jsonItem.getDouble("Px"),jsonItem.getDouble("Py"));
			temp[count] = new NearBy(description,add,position,name,0,0,0);
		}
		//totalNearBy.add(temp);
		
		/*
		//找飯店
		temp = new NearBy[jsonArray.length()];
		for(int count = 0;count < jsonArray.length();count++)
		{
			JSONObject jsonItem = jsonArray.getJSONObject(count);//把第count資料抓出來
			double lat = jsonItem.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
			double lng = jsonItem.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
			location coordinates = new location(lat,lng);
			String name = jsonItem.getString("name");
			double distance = GetDistance(coordinates.X,coordinates.Y,X,Y);
			double position = GetJiaoDu(coordinates.X,coordinates.Y,X,Y) - userPosition;
			if(position < 0)
			{
				position = 360 + position;
			}
			double rating = 0;
			if(!(jsonItem.isNull("rating")))
			{
				rating = jsonItem.getDouble("rating");
			}
			temp[count] = new NearBy(coordinates,name,distance,position,rating);
		}
		totalNearBy.add(temp);*/
		
		return temp;
	}
	public static NearBy[] getNearBy(double X,double Y,double userPosition) throws Exception 
	{
		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+X+","+Y+"&radius=500&key=AIzaSyAZwIWM2TNzbIRK4AIka4UwMvf3tLvIkXY";
		String Result = null;
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
		System.out.println(Result);
		JSONObject jsonObj = new JSONObject(Result);//取得全部的資料
		JSONArray jsonArray = jsonObj.getJSONArray("results");//把feature抓出來
		ArrayList<NearBy[]> totalNearBy = new ArrayList<NearBy[]>();
		
		//找餐廳
		NearBy[] temp = new NearBy[jsonArray.length()];
		for(int count = 0;count < jsonArray.length();count++)
		{
			JSONObject jsonItem = jsonArray.getJSONObject(count);//把第count資料抓出來
			double lat = jsonItem.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
			double lng = jsonItem.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
			location coordinates = new location(lat,lng);
			String name = jsonItem.getString("name");
			double distance = GetDistance(coordinates.X,coordinates.Y,X,Y);
			double position = GetJiaoDu(coordinates.X,coordinates.Y,X,Y) - userPosition;
			if(position < 0)
			{
				position = 360 + position;
			}
			double rating = 0;
			if(!(jsonItem.isNull("rating")))
			{
				rating = jsonItem.getDouble("rating");
			}
			//temp[count] = new NearBy(coordinates,name,distance,position,rating);
		}
		totalNearBy.add(temp);
		
		//找飯店
		temp = new NearBy[jsonArray.length()];
		for(int count = 0;count < jsonArray.length();count++)
		{
			JSONObject jsonItem = jsonArray.getJSONObject(count);//把第count資料抓出來
			double lat = jsonItem.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
			double lng = jsonItem.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
			location coordinates = new location(lat,lng);
			String name = jsonItem.getString("name");
			double distance = GetDistance(coordinates.X,coordinates.Y,X,Y);
			double position = GetJiaoDu(coordinates.X,coordinates.Y,X,Y) - userPosition;
			if(position < 0)
			{
				position = 360 + position;
			}
			double rating = 0;
			if(!(jsonItem.isNull("rating")))
			{
				rating = jsonItem.getDouble("rating");
			}
			//temp[count] = new NearBy(coordinates,name,distance,position,rating);
		}
		totalNearBy.add(temp);
		
		return temp;
	}
	//距離計算(單位為公里、以經緯度計算)
	private static double rad(double d) 
	{
		return d * Math.PI / 180.0;
	}
	public static double GetDistance(double lat1, double lng1, double lat2, double lng2) 
	{
		double EARTH_RADIUS = 6378137;
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)+ Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
	
	public static double GetJiaoDu(double lat1, double lng1, double lat2, double lng2)
    {
        double x1 = lng1;
        double y1 = lat1;
        double x2 = lng2;
        double y2 = lat2;
        double pi = Math.PI;
        double w1 = y1 / 180 * pi;
        double j1 = x1 / 180 * pi;
        double w2 = y2 / 180 * pi;
        double j2 = x2 / 180 * pi;
        double ret;
        if (j1 == j2)
        {
            if (w1 > w2) return 270;
            else if (w1 < w2) return 90;
            else return -1;
        }
        ret = 4 * Math.pow(Math.sin((w1 - w2) / 2), 2) - Math.pow(Math.sin((j1 - j2) / 2) * (Math.cos(w1) - Math.cos(w2)), 2);
        ret = Math.sqrt(ret);
        double temp = (Math.sin(Math.abs(j1 - j2) / 2) * (Math.cos(w1) + Math.cos(w2)));
        ret = ret / temp;
        ret = Math.atan(ret) / pi * 180;
        if (j1 > j2)
        {
            if (w1 > w2) ret += 180;
            else ret = 180 - ret;
        }
        else if (w1 > w2) ret = 360 - ret;
        return ret;
    }
}
