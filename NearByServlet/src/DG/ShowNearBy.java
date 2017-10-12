package DG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.JsonArray;

import com.google.gson.JsonParser;

public class ShowNearBy {
	static Connection con = null;
	static Connection con2 = null;
	static Statement stmt = null;
	static Statement stmt2 = null;
	static Statement stmt3 = null;
	static Statement stmt4 = null;
	static Statement stmt5 = null;
	
	static ResultSet rs = null;
	static ResultSet rs2 = null;
	static ResultSet rs3 = null;
	static ResultSet rs4 = null;
	static String comments = null;
	static String time = null;
	static String finalComments = null;
	static String address = null;
	static String restaurantID = null;
	
	static String restaurantName = "";
	public JSONArray show(String minLongitude,String minLatitude,String maxLongitude,String maxLatitude,String longitude,String latitude) throws SQLException, JSONException, IOException
	{
		con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&characterEncoding=utf-8", "dguser", "ian1024");
		String query = "select * from restaurant where PY >'" + minLatitude + "'AND PY <'" + maxLatitude + "'AND PX >'" + minLongitude + "'AND PX <'" + maxLongitude + "'";
		String query2 = "select * from store where PY >'" + minLatitude + "'AND PY <'" + maxLatitude + "'AND PX >'" + minLongitude + "'AND PX <'" + maxLongitude + "'";
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		stmt2 = con.createStatement();
		rs2 = stmt2.executeQuery(query2);
		double lng = Double.parseDouble(longitude);
		double lat = Double.parseDouble(latitude);
		return ShowNearBy.convert(rs,rs2,lng,lat);
	}
	
	public static JSONArray convert(ResultSet rs,ResultSet rs2,double lng,double lat)throws SQLException,JSONException, IOException
	{
		JSONArray jsonArray = new JSONArray();
		if(rs != null)
		{
			//ResultSetMetaData metadata = rs.getMetaData();
			//int numCol = metadata.getColumnCount();
			while(rs.next())
			{
				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("ID", rs.getString("ID"));
				jsonObject.put("NAME", rs.getString("NAME"));
				jsonObject.put("ADDRESS", rs.getString("ADDRESS"));
				jsonObject.put("DESCRIPTION", rs.getString("DESCRIPTION"));
				jsonObject.put("CLASS", rs.getString("CLASS"));
				jsonObject.put("OPENTIME", rs.getString("OPENTIME"));
				jsonObject.put("PX", rs.getString("PX"));
				jsonObject.put("PY", rs.getString("PY"));
				jsonObject.put("TEL", rs.getString("TEL"));
				jsonObject.put("WEBSITE", rs.getString("WEBSITE"));
				jsonObject.put("jiaoDu", GetJiaoDu(lat,lng,Double.parseDouble(rs.getString("PY")),Double.parseDouble(rs.getString("PX"))));
				jsonArray.put(jsonObject);
				restaurantName = rs.getString("NAME");
				restaurantID = rs.getString("ID");
				address = rs.getString("ADDRESS");
				String placeID = getPlaceID(restaurantName);
				getCommentsAndTime(placeID);
				/*for(int i = 1; i<numCol + 1; i++)
				{
					String columnName = metadata.getColumnName(i);
					int columnType = metadata.getColumnType(i);
					
					if(columnType == Types.VARCHAR)
					{
						jsonObject.put(columnName, rs.getString(columnName));
					}else if(columnType == Types.BIGINT){
						jsonObject.put(columnName, rs.getInt(columnName));
					}else{
						jsonObject.put(columnName,rs.getObject(columnName));
					}
				}
				jsonObject.put("jiaoDu", GetJiaoDu(lat,lng,Double.parseDouble(rs.getString("PY")),Double.parseDouble(rs.getString("PX"))));
				jsonArray.put(jsonObject);*/
			}
		}
		if(rs2 != null)
		{
			while(rs2.next())
			{

				System.out.println(rs2.getString("NAME"));
				JSONObject jsonObject2 = new JSONObject();
				jsonObject2.put("ID", rs2.getString("ID"));
				jsonObject2.put("NAME", rs2.getString("NAME"));
				jsonObject2.put("ADDRESS",rs2.getString("ADDRESS"));
				jsonObject2.put("DESCRIPTION","便利商店");
				jsonObject2.put("CLASS","");
				jsonObject2.put("OPENTIME","");
				jsonObject2.put("PX", rs2.getString("PX"));
				jsonObject2.put("PY", rs2.getString("PY"));
				jsonObject2.put("TEL","");
				jsonObject2.put("WEBSITE","");
				jsonObject2.put("jiaoDu", GetJiaoDu(lat,lng,Double.parseDouble(rs2.getString("PY")),Double.parseDouble(rs2.getString("PX"))));
				jsonArray.put(jsonObject2);
			}
		}
		return jsonArray;
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
	public static String getPlaceID(String shopName) throws IOException
	{
		String placeID = null;
		String urlForPlaceID = "https://maps.googleapis.com/maps/api/geocode/json?address="+shopName;
		String placeContent = null;
		InputStream is = new URL(urlForPlaceID).openStream();
        try {
             BufferedReader rd = new BufferedReader(new InputStreamReader(is,"utf-8")); //避免中文亂碼問題
             StringBuilder sb = new StringBuilder();
             int cp;
             while ((cp = rd.read()) != -1) {
                 sb.append((char) cp);
             }
             placeContent = sb.toString();
             //System.out.println(sb.toString());
        } finally {
             is.close();
        }
        //開始分析內容，拿到placeID
        JSONObject jsonObjTotal;
		try {
			jsonObjTotal = new JSONObject(placeContent);
			String status = jsonObjTotal.getString("status");
			System.out.println(status);
			if(status.matches("OK"))
			{	
				  System.out.println(urlForPlaceID);
				 JSONArray jsonArrTmp = jsonObjTotal.getJSONArray("results");
			        JSONObject jsonObj2 = jsonArrTmp.getJSONObject(0);
			        placeID = jsonObj2.getString("place_id");
			}else{
				System.out.println("RETRY");
				return getPlaceID(shopName);
			}
       
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.exit(1);
		}
		return placeID;
	}
	public static void resent(String shopName) throws IOException
	{
		getPlaceID(shopName);
	}
	public static void getCommentsAndTime(String placeID) throws MalformedURLException, IOException, SQLException
	{
		System.out.println("成功");
		String urlForComment = "https://maps.googleapis.com/maps/api/place/details/json?placeid="+placeID+"&key=AIzaSyAZwIWM2TNzbIRK4AIka4UwMvf3tLvIkXY";
		String commentContent = null;
		InputStream is = new URL(urlForComment).openStream();
        try {
             BufferedReader rd = new BufferedReader(new InputStreamReader(is,"utf-8")); //避免中文亂碼問題
             StringBuilder sb = new StringBuilder();
             int cp;
             while ((cp = rd.read()) != -1) {
                 sb.append((char) cp);
             } 
             commentContent = sb.toString();
             //System.out.println(commentContent);
        } finally {
             is.close();
        }
        //開始分析內容，拿到placeID
        JSONObject jsonObjTotal;
		try {
			jsonObjTotal = new JSONObject(commentContent);
			System.out.println(urlForComment);
			JSONObject jsonObj2 = jsonObjTotal.getJSONObject("result");
			double averageRating = jsonObj2.getDouble("rating");
			String deleteQuery = "DELETE FROM `rating` WHERE `RESTAURANT_ID`=" + restaurantID;
			stmt3=con.createStatement();
			stmt3.executeUpdate(deleteQuery);
			String ratingQuery = "INSERT INTO `rating`(`RESTAURANT_ID`,`RESTAURANT_RATING`)VALUES(\"" + restaurantID + "\",\"" + averageRating + "\")";
			System.out.println(ratingQuery);
			stmt5=con.createStatement();
			stmt5.executeUpdate(ratingQuery);
			System.out.println(ratingQuery);
			//抓到評論了
			JSONArray jsonArr = jsonObj2.getJSONArray("reviews");
			comments = jsonArr.toString();
			comments = comments.replace("a week ago", "一個禮拜前");
			comments = comments.replace("2 weeks ago", "兩個禮拜前");
			comments = comments.replace("3 weeks ago", "三個禮拜前");
			comments = comments.replace("a month ago", "一個月前");
			comments = comments.replace("2 months ago", "兩個月前");
			comments = comments.replace("3 months ago", "三個月前");
			comments = comments.replace("4 months ago", "四個月前");
			comments = comments.replace("5 months ago", "五個月前");
			comments = comments.replace("6 months ago", "六個月前");
			comments = comments.replace("7 months ago", "七個月前");
			comments = comments.replace("8 months ago", "八個月前");
			comments = comments.replace("9 months ago", "九個月前");
			comments = comments.replace("10 months ago", "十個月前");
			comments = comments.replace("11 months ago", "十一個月前");
			comments = comments.replace("a year ago", "一年前");
			comments = comments.replace("2 years ago", "兩年前");
			comments = comments.replace("3 years ago", "三年前");
			comments = comments.replace("4 years ago", "四年前");
			comments = comments.replace("\n", "");
			JSONArray commentsArray = null;
			try
			{
				commentsArray = new JSONArray(comments);
				con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&characterEncoding=utf-8", "dguser", "ian1024");
				finalComments = commentsArray.toString();
				//抓到營業時間了
				JSONObject jsonObj3 = jsonObj2.getJSONObject("opening_hours");
				JSONArray jsonArr2 = jsonObj3.getJSONArray("weekday_text");
				time = jsonArr2.toString();
				JsonArray o = (JsonArray)new JsonParser().parse(finalComments);
				stmt4 = con.createStatement();
				for(int count = 0; count < 5; count++)
				{
				System.out.println("第" +  count + "位:");
				String cmt = o.get(count).getAsJsonObject().get("text").getAsString();
				String athr = o.get(count).getAsJsonObject().get("author_name").getAsString();
				String rating = o.get(count).getAsJsonObject().get("rating").getAsString();
				String tme = o.get(count).getAsJsonObject().get("relative_time_description").getAsString();
				cmt = cmt.replace("\n", "");
				
				System.out.println(cmt);
				System.out.println(restaurantName);
				System.out.println(athr);
				System.out.println(rating);
				System.out.println(tme);
				String checkQuery = "SELECT * FROM `comments` WHERE AUTHOR = \"" + athr + "\" AND COMMENT = \"" + cmt + "\"";
				System.out.println(checkQuery);
				rs4 = stmt4.executeQuery(checkQuery);
				if(rs4.next()){
					System.out.println("Hi");
				}else{
				String commentQuery = "INSERT INTO `comments`(`RESTAURANT_ID`,`RESTAURANTNAME`,`ADDRESS`,`AUTHOR`,`RATING`,`COMMENT`,`TIME`)VALUES(\"" +restaurantID + "\",\"" + restaurantName + "\",\""+ address + "\",\"" + athr +"\",\""+ rating +"\",\""+ cmt +"\",\""+ tme +"\")";
				System.out.println(commentQuery);
				stmt4.executeUpdate(commentQuery);
				}
				System.out.println("-----------");
				}
			}catch(JSONException e)
			{
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
