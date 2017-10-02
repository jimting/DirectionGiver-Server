package DG;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WriteInServlet")
public class WriteInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			String account = request.getParameter("account");//之後從程式傳過來
			String start = request.getParameter("start");
			String bl = request.getParameter("bestline");
			String destination = request.getParameter("destination");//之後從程式傳過來
			String url = "http://maps.googleapis.com/maps/api/geocode/json?address=" + destination + "&sensor=false";
			WriteInDB writeIn = new WriteInDB();
			GetID getID = new GetID();
			GetGeometry get = new GetGeometry();
			GetTime getTime = new GetTime();
			String nowTime = getTime.getNowTime();
			String px = String.valueOf(get.getlng(url));
			String py = String.valueOf(get.getlat(url));
			writeIn.WriteIn(account, start, destination, px, py, bl, nowTime);
			int hid = getID.getID(nowTime, account);
			writer.println(hid);
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
