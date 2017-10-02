package DG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateUserLine {
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	
	public void updateUserLine(String userline,String history_ID) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&characterEncoding=utf-8", "dguser", "ian1024");
		System.out.println("連線完成");
		stmt = con.createStatement(); 
		String query = "UPDATE `history` SET userline = CONCAT(userline,\"" +userline+ "\") WHERE history_id =" + history_ID ;
		stmt.executeUpdate(query);
		System.out.println(query);
	}
}
