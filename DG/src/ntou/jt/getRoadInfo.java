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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;


public class getRoadInfo 
{
	public static ArrayList<RoadInfo[]> getItem(String url) throws Exception 
	{
		String Result = null;
		//URL url = new URL(http);
		String url2 = new String();
		ArrayList<RoadInfo[]> totalRoads = new ArrayList<RoadInfo[]>();
		for (int j = 0; j < url.length(); j++)
		{
			if (url.substring(j, j + 1).matches("[\\u4e00-\\u9fa5]+"))
			{
				try 
				{
					url2 = url2 + URLEncoder.encode(url.substring(j, j + 1),"UTF-8");
				}
				catch (UnsupportedEncodingException e) 
				{
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
		} 
		catch (MalformedURLException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //避免中文亂碼問題  
        finally 
        {
			is.close();
		}
		JSONObject jsonObj = new JSONObject(Result);//取得全部的資料
		JSONArray jsonArray = jsonObj.getJSONArray("routes");//把routes抓出來

		//System.out.println("總共有" + jsonArray.length() + "條路線");
		for(int top = 0;top < jsonArray.length();top++)
		{
			//System.out.println("=====路線" + (top+1) + "=====");
			JSONObject jsonItem = jsonArray.getJSONObject(top);//選第top個Object(路線資訊所在處)
			JSONArray legs = jsonItem.getJSONArray("legs");//最後抓到我們要用的legs
			JSONObject leginfo = legs.getJSONObject(0);//legs裡面的第一個Object
			String start = leginfo.getString("start_address");
			//System.out.println("出發：" + start);
			String end = leginfo.getString("end_address");
			//System.out.println("目的地：" + end);
			JSONArray steps = leginfo.getJSONArray("steps");//抓路線
			//System.out.println("總共有" + steps.length() + "筆路線資訊");
			//Object object = jsonObj.getJSONObject("routes");
			RoadInfo[] temp = new RoadInfo[steps.length()];
			for(int count = 0;count < steps.length();count++)
			{
				String distance = steps.getJSONObject(count).getJSONObject("distance").getString("text").toString();
				String duration = steps.getJSONObject(count).getJSONObject("duration").getString("text").toString();
				
				JSONObject tempString = steps.getJSONObject(count).getJSONObject("end_location");
				location end_location = new location(tempString.getDouble("lng"),tempString.getDouble("lat"));
				tempString = steps.getJSONObject(count).getJSONObject("start_location");
				location start_location = new location(tempString.getDouble("lng"),tempString.getDouble("lat"));
			    String temp_html = steps.getJSONObject(count).getString("html_instructions").toString();
			    String html_instructions = delHTMLTag(temp_html);
			    System.out.println("修改前路線:"+html_instructions);
			    if(html_instructions.contains("/"))
			    	html_instructions = simplify(html_instructions);
			    System.out.println("修改後路線:"+html_instructions);
			    
			    String polyline = steps.getJSONObject(count).getJSONObject("polyline").getString("points").toString();
			    String travel_mode = steps.getJSONObject(count).getString("travel_mode").toString();
			    temp[count] = new RoadInfo(distance,duration,end_location,start_location,html_instructions,polyline,travel_mode);
			}
			totalRoads.add(temp);
		}
		return totalRoads;
	}
	
	public static String delHTMLTag(String htmlStr)//用來消除html裡面的tag
	{ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //Script的TAG們
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //CSS的TAG們
        String regEx_html="<[^>]+>"; //HTML的TAG們
        
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //過濾Script 
        
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //過濾css
        
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //過濾html

       return htmlStr.trim(); //傳回去～
	} 
	public static String simplify(String a) {
        int idxFirstSlashPosition = a.indexOf("/");//找第一個斜線的位置
        int idxSecondSlashPosition;//存放第二個斜線的位置（索引值）
        int idxFirstBoundary;//存放第一個邊界關鍵字
        String sub1 = a.substring(0, idxFirstSlashPosition);//取得第一個字到斜線的字串
        String sub2;//存放該字串第二部分
        String instruction;//最終簡化過的字串
        int aLength = a.length();//取得這個字串的長度
        if (a.matches("^((?!靠左行駛).)*前進$")) {//規則1
            instruction = sub1 + "前進";
        } else if (a.matches("^((?!靠左行駛)(?!靠右行駛)(?!向右轉)(?!向左轉)(?!繼續開在).)*$") || a.matches("^((?!靠左行駛)(?!靠右行駛).).*((道|線|路|走|橋|前進)繼續開在).*$") || a.matches("^(靠右行駛|靠左行駛).*")) {//規則2、4、8
            instruction = sub1;
        } else if (a.matches("^.*(向左轉|向右轉)$")) {//規則3
            sub2 = a.substring(aLength - 3, aLength);
            instruction = sub1 + sub2;
        } else if (a.matches("^.*(靠右行駛|向左轉|向右轉|向右急轉)繼續開在.*")) {//規則5
            if (a.contains("向左轉") || a.contains("向右轉")) {//如果是向右轉或向左轉
                idxFirstBoundary = a.indexOf("向");//找到邊界關鍵字
                sub2 = a.substring(idxFirstBoundary, idxFirstBoundary + 3);
                instruction = sub1 + sub2;
            } else if (a.contains("靠右行駛")) {//如果是靠右行駛
                idxFirstBoundary = a.indexOf("靠");//找到邊界關鍵字
                sub2 = a.substring(idxFirstBoundary, idxFirstBoundary + 4);
                instruction = sub1 + sub2;
            } else {//如果是其他，如：向右急轉
                idxFirstBoundary = a.indexOf("向");//找到邊界關鍵字
                sub2 = a.substring(idxFirstBoundary, idxFirstBoundary + 4);
                instruction = sub1 + sub2;
            }
        } else if (a.matches("^.*靠右行駛$")) {//規則6
            instruction = sub1 + "靠右行駛";
        } else if (a.matches("^.*靠右行駛走.*$")) {//規則7
            idxFirstBoundary = a.indexOf("靠");//找到邊界關鍵字
            sub2 = a.substring(idxFirstBoundary, aLength);
            instruction = sub1 + sub2;
        } else if (a.matches("^.*((靠右行駛|靠左行駛)，).*$")) {//規則9
            idxFirstBoundary = a.indexOf("靠");//找到邊界關鍵字的索引值
            idxSecondSlashPosition = a.indexOf("/", idxFirstSlashPosition + 1);//找到第二個斜線
            sub2 = a.substring(idxFirstBoundary, idxSecondSlashPosition);
            instruction = sub1 + sub2;
        } else if (a.matches("^.*(向左轉|向右轉)目的地(在左邊|在右邊)$")) {//規則10
            int k = a.indexOf("向", idxFirstSlashPosition + 1);//找到第一個斜線後面，第一個「向」的位置
            sub2 = a.substring(k, aLength);
            instruction = sub1 + sub2;
        } else {
            instruction = a;
        }
        return instruction;
    }
}
