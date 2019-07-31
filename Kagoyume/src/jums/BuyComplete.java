package jums;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BuyComplete
 */
@WebServlet("/BuyComplete")
public class BuyComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyComplete() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		try {
			if(Objects.equals(session.getAttribute("LoginCheck"),null)) {
				request.getRequestDispatcher("/Login").forward(request, response);
			}else {
				UserDataDTO User =new UserDataDTO();			
				UserDataDTO LoginUser=(UserDataDTO)session.getAttribute("LoginUser");


				@SuppressWarnings("unchecked")
				HashMap<String,ProductDataBeans> cart = (HashMap<String,ProductDataBeans>)session.getAttribute("cart");

				int UserID=LoginUser.getUserID();
				String name=LoginUser.getName();
				int type=Integer.parseInt(request.getParameter("type"));
				int sum=Integer.parseInt(request.getParameter("sum"));

				User.setName(name);
				UserDataDTO udd=UserDataDAO.getInstance().UserInf(User);
				int GetTotal=udd.getTotal();
				
				int total=sum+GetTotal;
				
				for(String key : cart.keySet()){
					UserDataDTO BuyData=new UserDataDTO();

					BuyData.setUserID(UserID);
					BuyData.setType(type);
					BuyData.setCode(key);
					UserDataDAO.getInstance().insertProduct(BuyData);
				}
				cart.clear();

				User.setUserID(UserID);
				User.setTotal(total);
				UserDataDAO.getInstance().UserTotal(User);


				request.getRequestDispatcher("/buycomplete.jsp").forward(request, response);     
			}
		} catch(Exception e){
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
