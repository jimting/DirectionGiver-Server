package DG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class insertData {
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	public static void main(String[] args) throws Exception {
		System.out.println("準備連線到資料庫");
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/keelungdg?useUnicode=true&charaterEncoding=utf-8", "dguser", "ian1024");
		System.out.println("連線完成");
		stmt = con.createStatement();
		stmt.execute("DELETE FROM `restaurant` WHERE flag = 0");
		stmt.execute("ALTER TABLE restaurant drop ID");
		stmt.execute("ALTER TABLE `restaurant` ADD `ID` INT(6) NOT NULL AUTO_INCREMENT Primary key FIRST");
		NearBy[] temp = GetRestaurantJson.getRest();
		int i=0;
		try{
		while(i < temp.length)
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

		/*String id, name, address, des, kind, opt, px, py, tel, web;
		Scanner scanner = new Scanner(System.in);
		System.out.println("查詢或輸入?? 1 = 查詢, 2 = 輸入");
		int option = scanner.nextInt();
		switch (option) {
		case 2:
			System.out.println("輸入店名");
			name = scanner.next();
			System.out.println("輸入地址");
			address = scanner.next();
			System.out.println("輸入敘述");
			des = scanner.next();
			System.out.println("輸入類別");
			kind = scanner.next();
			System.out.println("輸入營業時間");
			opt = scanner.next();
			System.out.println("輸入經度");
			px = scanner.next();
			System.out.println("輸入緯度");
			py = scanner.next();
			System.out.println("輸入電話");
			tel = scanner.next();
			System.out.println("輸入網址");
			web = scanner.next();

			String insertquery = "INSERT INTO `restaurant` (`NAME`, `ADDRESS`, `DESCRIPTION`, `CLASS`, `OPENTIME`, `PX`, `PY`, `TEL`, `WEBSITE`) VALUES"
					+ "('" + name + "','" + address + "','" + des + "'," + kind + ",'" + opt + "','" + px + "','" + py
					+ "','" + tel + "','" + web + "')";
			System.out.println("你輸入的字串為\n" + insertquery);
			stmt.executeUpdate(insertquery);
			break;
		case 1:
			rs = stmt.executeQuery("SELECT * FROM restaurant");
			while(rs.next())
			{
				System.out.println("編號 : " + rs.getString("id"));
				System.out.println("店名 : " + rs.getString("name"));
				System.out.println("地址 : " + rs.getString("address"));
				System.out.println("敘述 : " + rs.getString("description"));
				System.out.println("經度 : " + rs.getString("px"));
				System.out.println("緯度 : " + rs.getString("py"));
				System.out.println("營業時間 : " + rs.getString("opentime"));
				System.out.println("電話 : " + rs.getString("tel"));
				System.out.println("網址 : " + rs.getString("website"));
				System.out.println("類別 : " + rs.getString("class"));
				System.out.println("");	
			}
			break;
		case 3:
			stmt.execute("ALTER TABLE restaurant drop ID");
			stmt.execute("ALTER TABLE `restaurant` ADD `ID` INT(6) NOT NULL AUTO_INCREMENT Primary key FIRST");
			break;
		}*/

	}
}
