package DG;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String des = request.getParameter("des");
		String opt = request.getParameter("opt");
		String kind = request.getParameter("kind");
		String px = request.getParameter("px");
		String py = request.getParameter("py");
		String tel = request.getParameter("tel");
		String web = request.getParameter("web");
		
		InsertData insertData = new InsertData();
		try {
			if(insertData.insert(name, address, des, kind, opt, px, py, tel, web))
			{
				RequestDispatcher view = request.getRequestDispatcher("success.jsp");
				view.forward(request, response);
			}else
			{

				RequestDispatcher view = request.getRequestDispatcher("fail.jsp");
				view.forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
