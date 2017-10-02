package DG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class InsertHotelData {
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	public static void main(String[] args) throws Exception {
		System.out.println("準備連線到資料庫");
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&charaterEncoding=utf-8", "dguser", "ian1024");
		System.out.println("連線完成");
		stmt = con.createStatement();
		stmt.execute("DELETE FROM `hotel` WHERE flag = 0");
		stmt.execute("ALTER TABLE hotel drop ID");
		stmt.execute("ALTER TABLE `hotel` ADD `ID` INT(6) NOT NULL AUTO_INCREMENT Primary key FIRST");
		NearByHotel[] temp = GetHotelJson.getRest();
		int i=0;
		while(i < temp.length)
		{		
			try{
				System.out.println(temp[i].toString());
				stmt.executeUpdate(temp[i].toString());
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					
				}
			i++;
			System.out.println("第" + i + "筆資料");
		}
		          try
		           {
		            if (con!=null)  
		              con.close();//中斷與database連線
		            System.out.println("結束了!中斷連線~");
		           }
		           catch (Exception vv) {
		        	   con=null; //把物件資源釋放
		           }
		        }
	}
