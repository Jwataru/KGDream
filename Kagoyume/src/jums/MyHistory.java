package jums;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyHistory
 */
@WebServlet("/MyHistory")
public class MyHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyHistory() {
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

				UserDataDTO udd =(UserDataDTO)session.getAttribute("LoginUser");

				ArrayList<UserDataDTO> box=(ArrayList<UserDataDTO>)UserDataDAO.getInstance().BuyInf(udd);
				ArrayList<ProductDataBeans> box2=new ArrayList<ProductDataBeans>();

				for(UserDataDTO DTO:box) {

					ProductDataBeans pd = new ProductDataBeans();
					pd.setCode(DTO.getCode());
					ProductDataBeans Detail = Api.getDetail(pd);
					Detail.setBuyDate(DTO.getBuyDate());
					box2.add(Detail);
				}

				session.setAttribute("History", box2);
				request.getRequestDispatcher("/myhistory.jsp").forward(request, response);
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
