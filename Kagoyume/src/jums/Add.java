package jums;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Add() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// セッションのインスタンスを生成
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("UTF-8");
			
			if(session.getAttribute("cart")==null) {
				HashMap<String, ProductDataBeans> cart = new HashMap<String, ProductDataBeans>();
				String code=request.getParameter("code");
				
				ProductDataBeans pdb = new ProductDataBeans();
				pdb.setCode(code);
				
				ProductDataBeans data = Api.getDetail(pdb);
				cart.put(data.getCode(), data);
				
				session.setAttribute("cart", cart);
				request.setAttribute("code",code);
			}else {
				@SuppressWarnings("unchecked")
				HashMap<String, ProductDataBeans> cart = (HashMap<String,ProductDataBeans>) session.getAttribute("cart");
				String code=request.getParameter("code");
				
				ProductDataBeans pdb = new ProductDataBeans();
				pdb.setCode(code);
				
				ProductDataBeans data = Api.getDetail(pdb);
				cart.put(data.getCode(), data);
				
				request.setAttribute("code",code);
			}

			request.getRequestDispatcher(response.encodeURL("/add.jsp")).forward(request, response);
		} catch(NullPointerException e) {    
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
