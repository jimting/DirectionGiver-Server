package DG;

import java.io.IOException;
import java.sql.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ShowNearByNPC {
	static Connection con = null;
	static Statement stmt = null;

	static ResultSet rs = null;

	public JSONArray show(String minLongitude,String minLatitude,String maxLongitude,String maxLatitude,String longitude,String latitude) throws SQLException, JSONException, IOException
	{
		con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&characterEncoding=utf-8", "dguser", "ian1024");
		String query = "select * from npc_info where npc_lat >'" + minLatitude + "'AND npc_lat <'" + maxLatitude + "'AND npc_lng >'" + minLongitude + "'AND npc_lng <'" + maxLongitude + "'";
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		double lng = Double.parseDouble(longitude);
		double lat = Double.parseDouble(latitude);
		return ShowNearByNPC.convert(rs,lng,lat);
	}
	
	public static JSONArray convert(ResultSet rs,double lng,double lat)throws SQLException,JSONException, IOException
	{
		JSONArray jsonArray = new JSONArray();
		if(rs != null)
		{
			while(rs.next())
			{
				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("NPC_ID", rs.getString("npc_id"));
				jsonObject.put("NPC_NAME", rs.getString("npc_name"));
				jsonObject.put("NPC_PIC", rs.getString("npc_pic"));
				jsonObject.put("PX", rs.getString("npc_lng"));
				jsonObject.put("PY", rs.getString("npc_lat"));
				jsonObject.put("VOICE", rs.getString("npc_voice"));
				jsonObject.put("NPC_INTRO", rs.getString("npc_intro"));
				jsonObject.put("jiaoDu", GetJiaoDu(lat,lng,Double.parseDouble(rs.getString("npc_lat")),Double.parseDouble(rs.getString("npc_lng"))));
				jsonArray.put(jsonObject);
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

}

