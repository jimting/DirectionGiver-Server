package DG;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Servlet implementation class GetConServlet
 */
@WebServlet("/GetConServlet")
public class GetConServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		
		GetFourSpotWC test = new GetFourSpotWC();
		String rightTopLng = test.getRtLng(longitude, latitude);
		String rightTopLat = test.getRtLat(longitude, latitude);
		String leftBottomLng = test.getLbLng(longitude, latitude);
		String leftBottomLat = test.getLbLat(longitude, latitude);
		
		ShowCon showCon = new ShowCon();
		
		JSONArray near = new JSONArray();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			near = showCon.show(leftBottomLng,leftBottomLat,rightTopLng,rightTopLat,longitude,latitude);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.println(near);	
	}

}

