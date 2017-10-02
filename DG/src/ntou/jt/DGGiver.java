package ntou.jt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class DGGiver
 */
@WebServlet(name="DGGiver",urlPatterns={"/DGGiver"})
public class DGGiver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DGGiver() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// TODO Auto-generated method stub	
	    response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		//String requestUri = request.getRequestURI();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ArrayList<RoadInfo[]> roadInfo = null;
		String url = "http://maps.google.com/maps/api/directions/json?origin="+start+"&destination="+end+"&mode=walking&language=zh-TW&sensor=true";
		try 
		{
			roadInfo = getRoadInfo.getItem(url);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(roadInfo);
		out.write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
