package DG;

import java.sql.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowWC {
	static Connection con = null;
	static Statement stmt = null;
	static Statement stmt2 = null;

	static ResultSet rs = null;
	static ResultSet rs2 = null;

	public JSONArray show(String minLongitude,String minLatitude,String maxLongitude,String maxLatitude,String longitude,String latitude) throws SQLException, JSONException
	{
		con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&characterEncoding=utf-8", "dguser", "ian1024");
		String query = "select * from wc where PY >'" + minLatitude + "'AND PY <'" + maxLatitude + "'AND PX >'" + minLongitude + "'AND PX <'" + maxLongitude + "'";
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		double lng = Double.parseDouble(longitude);
		double lat = Double.parseDouble(latitude);
		return ShowWC.convert(rs,lng,lat);
	}
	
	public static JSONArray convert(ResultSet rs,double lng,double lat)throws SQLException,JSONException
	{
		JSONArray jsonArray = new JSONArray();
		if(rs != null)
		{
			while(rs.next())
			{
				System.out.println(rs.getString("NAME"));
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("ID", rs.getString("ID"));
				jsonObject.put("NAME", rs.getString("NAME"));
				jsonObject.put("ADDRESS",rs.getString("ADDRESS"));
				jsonObject.put("DESCRIPTION","廁所");
				jsonObject.put("COUNTRY",rs.getString("COUNTRY"));
				jsonObject.put("CITY",rs.getString("CITY"));
				jsonObject.put("ADMINISTRATION",rs.getString("ADMINISTRATION"));
				jsonObject.put("PX", rs.getString("PX"));
				jsonObject.put("PY", rs.getString("PY"));
				jsonObject.put("GRADE", rs.getString("GRADE"));
				jsonObject.put("TYPE", rs.getString("TYPE"));
				jsonObject.put("jiaoDu", GetJiaoDu(lat,lng,Double.parseDouble(rs.getString("PY")),Double.parseDouble(rs.getString("PX"))));
				jsonArray.put(jsonObject);
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
