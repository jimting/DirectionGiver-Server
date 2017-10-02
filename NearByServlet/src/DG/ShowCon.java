package DG;

import java.sql.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowCon {
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	public JSONArray show(String minLongitude,String minLatitude,String maxLongitude,String maxLatitude,String longitude,String latitude) throws SQLException, JSONException
	{
	
		 con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&characterEncoding=utf-8", "dguser", "ian1024");
		//String result = "";
		String query = "select * from store where PY >'" + minLatitude + "'AND PY <'" + maxLatitude + "'AND PX >'" + minLongitude + "'AND PX <'" + maxLongitude + "'";
		 stmt = con.createStatement();
		 rs = stmt.executeQuery(query);
		 double lng = Double.parseDouble(longitude);
		 double lat = Double.parseDouble(latitude);
		return ShowCon.convert(rs,lng,lat);
		
	}
	public static JSONArray convert(ResultSet rs,double lng,double lat)throws SQLException,JSONException
	{
		JSONArray jsonArray = new JSONArray();
		if(rs != null)
		{
			ResultSetMetaData metadata = rs.getMetaData();
			int numCol = metadata.getColumnCount();
			
			while(rs.next())
			{
				JSONObject jsonObject = new JSONObject();
				for(int i = 1; i<numCol + 1; i++)
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
				jsonObject.put("jiaoDu", ShowNearBy.GetJiaoDu(lat,lng,Double.parseDouble(rs.getString("py")),Double.parseDouble(rs.getString("px"))));
				jsonArray.put(jsonObject);
			}
		}
		return jsonArray;
	}

}
