package DG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetID {
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs =  null;
	
	public int getID(String nowTime, String account) throws Exception{
		int hid = 0;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&characterEncoding=utf-8", "dguser", "ian1024");
		System.out.println("連線完成");
		stmt = con.createStatement(); 
		String query = "SELECT * FROM `history` WHERE `date` = \"" + nowTime + "\" AND `account` = \"" + account + "\"";
		rs = stmt.executeQuery(query);
		System.out.println(query);
		rs.next();
		
		System.out.println("hi");
		hid = rs.getInt(1);
		
		System.out.println(hid);
		return hid;
	}
}
