package jums;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Registrationconfirm
 */
@WebServlet("/Registrationconfirm")
public class Registrationconfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrationconfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // セッションのインスタンスを生成
        HttpSession session = request.getSession();
        
        try{
            request.setCharacterEncoding("UTF-8");
            
//             アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }
            
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
            udd.setName(TrimName);
            udd.setPassword(TrimPass);
            udd.setMail(TrimMail);
            udd.setAddress(TrimAddress);


            //ユーザーデータをセッションに格納
            session.setAttribute("RegistrationData", udd);
            System.out.println("Session updated!!");
            
            
			boolean flag=UserDataDAO.getInstance().RegistrationCheck(udd);
			boolean flag2;
			ArrayList<String> chkList = udd.chkproperties();
			
			if(chkList.size()==0){
				if(flag){
					flag2=true;
					System.out.println("signup updated!!");
				}else{ 					
					System.out.println("UserID existed"); 
					flag2=false;
				}
				request.setAttribute("flag2", flag2);
			}else{ 				
				System.out.println("unfilled");				
			}         
		
			
            
            request.getRequestDispatcher("/registrationconfirm.jsp").forward(request, response);
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
