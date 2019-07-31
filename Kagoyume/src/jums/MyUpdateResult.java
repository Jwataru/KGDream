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
 * Servlet implementation class MyUpdateResult
 */
@WebServlet("/MyUpdateResult")
public class MyUpdateResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyUpdateResult() {
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
				UserDataDTO ud=(UserDataDTO)session.getAttribute("LoginUser");
				
				
	            String name = request.getParameter("name");
	            String password = request.getParameter("password");
	            String mail = request.getParameter("mail");
	            String address = request.getParameter("address");
	            
	            String TrimName =UserDataDTO.getInstance().trimWhitespace(name);
	            String TrimPass =UserDataDTO.getInstance().trimWhitespace(password);
	            String TrimMail =UserDataDTO.getInstance().trimWhitespace(mail);
	            String TrimAddress =UserDataDTO.getInstance().trimWhitespace(address);

				// フォームからの各パラメータを取得して、JavaBeansに格納
				UserDataDTO udd = new UserDataDTO();
				udd.setUserID(ud.getUserID());
				udd.setName(TrimName);
				udd.setPassword(TrimPass);
				udd.setMail(TrimMail);
				udd.setAddress(TrimAddress);
				
				
				boolean flag=false;
				if(!(request.getParameter("name").equals(ud.getName()))){
					 flag=UserDataDAO.getInstance().RegistrationCheck(udd);
				}else {
					 flag=true;
				}

				boolean flag2;
				ArrayList<String> chkList = udd.chkproperties();

				if(chkList.size()==0){
					if(flag){
						flag2=true;
						UserDataDAO .getInstance().UserUpdate(udd);
						System.out.println("updated!!");
						UserDataDTO u =UserDataDAO .getInstance().UserInf(udd);
						
						session.setAttribute("LoginUser",u);
					}else{ 
						flag2=false;
						System.out.println("UserName existed");   
						
					}
					request.setAttribute("flag2", flag2);
				}else{ 
					System.out.println("unfilled"); 
				}         

				request.setAttribute("UpdateData", udd);
				request.getRequestDispatcher("/myupdateresult.jsp").forward(request, response);
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
