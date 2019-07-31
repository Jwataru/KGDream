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
 * Servlet implementation class LoginProcess
 */
@WebServlet("/LoginProcess")
public class LoginProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginProcess() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		try{
			request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更

			if(Objects.equals(request.getParameter("name"),"")||Objects.equals(request.getParameter("password"),"")){
				request.setAttribute("error","ユーザー名またはPWが未入力です");
				boolean flagin=true;
				request.setAttribute("flagin",flagin);
				request.getRequestDispatcher("/login.jsp").forward(request, response);			
			}else {

				//フォームからの入力を取得して、JavaBeansに格納
				UserDataDTO loginudb = new UserDataDTO();
				loginudb.setName(request.getParameter("name"));			
				loginudb.setPassword(request.getParameter("password"));

				boolean flag=false;
				boolean flag2=false;
				flag=UserDataDAO.getInstance().LoginChecker(loginudb);
				flag2=UserDataDAO.getInstance().DeleteCheck(loginudb);
				

				if(flag2) {
					request.setAttribute("error","このアカウントは削除されています。");
					boolean flagin=true;
					request.setAttribute("flagin",flagin);
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}else {
					if(flag) {		        
						session.setAttribute("LoginCheck", (int) (Math.random() * 1000));
						UserDataDTO LoginUser=UserDataDAO.getInstance().UserInf(loginudb);
						session.setAttribute("LoginUser",LoginUser);

						String url = (String)session.getAttribute("referer");
						String str1 = new String(url);
						String urlStr=str1.substring(str1.indexOf("/")+1);
						int LastUrlNum=urlStr.length();
						int UrlNum= url.length()-LastUrlNum-1;
						
						System.out.println(UrlNum);
						
						response.sendRedirect(url.substring(UrlNum));
//						response.sendRedirect(url.substring(31));
//						request.getRequestDispatcher(response.encodeURL(url).substring(UrlNum)).forward(request, response);
//						request.getRequestDispatcher(response.encodeURL(url).substring(31)).forward(request, response);
//						request.getRequestDispatcher(response.encodeURL(url)).forward(request, response);
					}else {	
						
						request.setAttribute("error","ユーザー名とPWが一致しません");
						boolean flagin=true;
						request.setAttribute("flagin",flagin);
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}	
				}

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
