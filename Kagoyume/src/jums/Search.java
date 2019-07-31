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
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		// セッションのインスタンスを生成
		HttpSession session = request.getSession();
		ProductDataBeans pdb = new ProductDataBeans();
		Api api = new Api();
	
		try{

			if (!(request.getParameter("name").equals("")||Objects.equals(request.getParameter("name"), null))) {
				String SearchName = request.getParameter("name");
				
				pdb.setSearchName(SearchName);
				session.setAttribute("SearchName", pdb);
				

				ArrayList<ProductDataBeans> itemData = api.getResult(pdb);
				session.setAttribute("itemData", itemData);
				session.setAttribute("SarchAmount", api.getAmount(pdb));
				
				
				request.getRequestDispatcher("/search.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "検索語句が未入力です");
				request.getRequestDispatcher("/top.jsp").forward(request, response);
			}
		}catch(NullPointerException e){
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
