package jums;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyDeleteResult
 */
@WebServlet("/MyDeleteResult")
public class MyDeleteResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyDeleteResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		try{
			if(Objects.equals(session.getAttribute("LoginCheck"),null)) {
				request.getRequestDispatcher("/Login").forward(request, response);
			}else {   
				request.setCharacterEncoding("UTF-8");

				// フォームからの各パラメータを取得して、JavaBeansに格納
				UserDataDTO udd = (UserDataDTO)session.getAttribute("LoginUser");
				UserDataDAO.getInstance().UserDelete(udd);
				
				session.removeAttribute("LoginCheck");

				request.getRequestDispatcher("/mydeleteresult.jsp").forward(request, response);
			}
		}catch(Exception e){
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
