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
 * Servlet implementation class GetShopInfoServlet
 */
@WebServlet("/GetShopInfoServlet")
public class GetShopInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetShopInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		String restaurant_ID = request.getParameter("ID");

		
		GetShopInfo get = new GetShopInfo();
		
		JSONArray info = new JSONArray();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			info = get.show(restaurant_ID);	
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
		writer.println(info);	
	}

}
