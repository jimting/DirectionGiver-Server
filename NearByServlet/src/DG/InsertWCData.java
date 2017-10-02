package DG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class InsertWCData {
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	public static void main(String[] args) throws Exception {
		System.out.println("準備連線到資料庫");
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&characterEncoding=utf-8", "dguser", "ian1024");
		System.out.println("連線完成");
		stmt = con.createStatement();
		stmt.execute("TRUNCATE TABLE `wc`");
		stmt.execute("ALTER TABLE wc CONVERT TO CHARACTER SET utf8");
		System.out.println("utf8");
		//stmt.execute("ALTER TABLE `wc` ADD `ID` INT(6) NOT NULL AUTO_INCREMENT Primary key FIRST");
		NearByWC[] temp = GetWCJson.getWC();
		int i=0;
		try{
		while(i < 517)
		{
				System.out.println(temp[i].toString());
				stmt.executeUpdate(temp[i].toString());
				i++;
		}
		
		}catch (Exception e) {
			 //TODO Auto-generated catch block	
			e.printStackTrace();
		}finally{
			System.out.println("共有" + i + "筆資料");
			{
		          try
		           {
		            if (con!=null)  
		              con.close();//中斷與database連線
		            System.out.println("中斷連線");
		           }
		           catch (Exception vv) {
		      con=null; //把物件資源釋放
		           }
		        }
		}
	}
}