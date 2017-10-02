package DG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class InsertData {
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	public boolean insert(String name,String address,String des,String kind,String opt,String px,String py,String tel,String web) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&charaterEncoding=utf-8", "dguser", "ian1024");
		stmt = con.createStatement();
		String checkQuery = "SELECT * FROM `restaurant` WHERE PX = '" + px + "' AND py = '" + py + "'";
		ResultSet rs = stmt.executeQuery(checkQuery);
		if(rs.next())
		{
			con.close();//中斷與database連線
			return false;
		}
		String insertQuery = "INSERT INTO `restaurant` (`NAME`, `ADDRESS`, `DESCRIPTION`, `CLASS`, `OPENTIME`, `PX`, `PY`, `TEL`, `WEBSITE`, `flag`) VALUES"
				+ "('" + name + "','" + address + "','" + des + "','" + kind + "','" + opt + "','" + px + "','" + py
				+ "','" + tel + "','" + web + "','1')";
		stmt.executeUpdate(insertQuery);
		System.out.println(insertQuery);
		con.close();//中斷與database連線
		return true;
	}
}
