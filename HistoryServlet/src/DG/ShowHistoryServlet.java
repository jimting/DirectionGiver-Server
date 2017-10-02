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
 * Servlet implementation class ShowHistoryServlet
 */
@WebServlet("/ShowHistoryServlet")
public class ShowHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		ShowHistory historyRecord = new ShowHistory();
		
		JSONArray historyJsonArray = new JSONArray();
		String account = request.getParameter("account");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			historyJsonArray = historyRecord.show(account);	
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
		writer.println(historyJsonArray);
	}


}
