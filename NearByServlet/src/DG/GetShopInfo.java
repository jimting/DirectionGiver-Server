package DG;


import java.io.IOException;
import java.sql.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetShopInfo {
	static Connection con = null;
	static Statement stmt = null;
	static Statement stmt2 = null;
	static ResultSet rs = null;
	static ResultSet rs2 = null;
	public JSONArray show(String ID) throws SQLException, JSONException, IOException
	{
		con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&characterEncoding=utf-8", "dguser", "ian1024");
		String query = "select * from `restaurant` where ID='" + ID + "'";
		String ratingQuery = "select * from `rating` where RESTAURANT_ID='" + ID + "'";
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		stmt2 = con.createStatement();
		rs2 = stmt2.executeQuery(ratingQuery);
		return GetShopInfo.convert(rs,rs2);
	}
	
	public static JSONArray convert(ResultSet rs,ResultSet rs2)throws SQLException,JSONException, IOException
	{
		JSONArray jsonArray = new JSONArray();
		if(rs != null)
		{
			while(rs.next())
			{   rs2.next();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("ID", rs.getString("ID"));
				jsonObject.put("NAME", rs.getString("NAME"));
				jsonObject.put("ADDRESS", rs.getString("ADDRESS"));
				jsonObject.put("DESCRIPTION", rs.getString("DESCRIPTION"));
				jsonObject.put("CLASS", rs.getString("CLASS"));
				jsonObject.put("OPENTIME", rs.getString("OPENTIME"));
				jsonObject.put("TEL", rs.getString("TEL"));
				jsonObject.put("WEBSITE", rs.getString("WEBSITE"));
				jsonObject.put("RATING", rs2.getString("RESTAURANT_RATING"));
				jsonArray.put(jsonObject);
			}
		}
		return jsonArray;
	}
}
