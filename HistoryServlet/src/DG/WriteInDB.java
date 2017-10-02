package DG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class WriteInDB {
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	
	public void WriteIn(String account, String start, String destination, String px, String py, String bestline,String nowTime) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&characterEncoding=utf-8", "dguser", "ian1024");
		System.out.println("連線完成");
		stmt = con.createStatement(); 
		String query = "INSERT INTO `history`(`account`, `start`, `destination`, `px`, `py`, `userline`, `bestline`,`date`)VALUES(\"" + account + "\",\"" + start + "\",\"" + destination + "\",\"" + px + "\",\"" + py + "\",\"\",\"" + bestline + "\",\"" + nowTime + "\")";
		stmt.executeUpdate(query);
		System.out.println(query);
	}
	
}
