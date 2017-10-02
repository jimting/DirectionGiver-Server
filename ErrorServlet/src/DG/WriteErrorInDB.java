package DG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class WriteErrorInDB {
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	
	public void WriteIn(String account,String error) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&characterEncoding=utf-8", "dguser", "ian1024");
		//System.out.println("連線完成");
		stmt = con.createStatement(); 
		String query = "INSERT INTO `error`(`account`, `error`)VALUES(\"" + account + "\",\"" + error + "\")";
		stmt.executeUpdate(query);
		System.out.println(query);
	}
	
}
