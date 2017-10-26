package DG;

import java.io.IOException;
import java.sql.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetSpeech {
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	public JSONArray showSpeech(String ID) throws SQLException, JSONException, IOException
	{
		con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&characterEncoding=utf-8", "dguser", "ian1024");
		String query = "select * from `npc_speech` where npc_id='" + ID + "'";
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		return GetSpeech.convert(rs);
	}
	
	public static JSONArray convert(ResultSet rs)throws SQLException,JSONException, IOException
	{
		JSONArray jsonArray = new JSONArray();
		if(rs != null)
		{
			while(rs.next())
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("NPC_ID", rs.getString("npc_id"));
				jsonObject.put("STATUS", rs.getString("speech_status"));
				jsonObject.put("CONTENT", rs.getString("speech_content"));
				jsonObject.put("VOICE", rs.getString("speech_voice"));
				jsonArray.put(jsonObject);
			}
		}
		return jsonArray;
	}
}
