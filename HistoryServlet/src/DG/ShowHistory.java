package DG;

import java.sql.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowHistory {
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	public JSONArray show(String account) throws SQLException, JSONException
	{
	
		 con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&characterEncoding=utf-8", "dguser", "ian1024");
		//String result = "";
		String query = "select * from history where account ='" + account + "'";
		 stmt = con.createStatement();
		 rs = stmt.executeQuery(query);
		return ShowHistory.convert(rs);
		
	}
	public static JSONArray convert(ResultSet rs)throws SQLException,JSONException
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
				jsonArray.put(jsonObject);
			}
		}else System.out.println("No Results");
		return jsonArray;
	}

	
}