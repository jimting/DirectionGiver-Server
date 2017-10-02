package ntou.jt;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class DGNearBy
 */
@WebServlet(name="DGNearBy",urlPatterns={"/DGNearBy"})
public class DGNearBy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DGNearBy() {
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
		String tempX = request.getParameter("X");
		String tempY = request.getParameter("Y");
		double X = Double.parseDouble(tempX);
		double Y = Double.parseDouble(tempY);
		//String requestUri = request.getRequestURI();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		NearBy[] nearBy = null;
		try 
		{
			nearBy = GetNearBy.getNearBy(X,Y,0);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(NearBy tmp:nearBy)
		{

			System.out.println(tmp);
		}
		Gson gson = new Gson();
		String json = gson.toJson(nearBy);
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
