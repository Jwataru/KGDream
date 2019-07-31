<%@page import="java.util.HashMap"%>
<%@page import="jums.ProductDataBeans"%>
<%@page import="jums.JumsHelper"%>
<%@page import="java.util.Objects" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
	boolean flag=false;
	
	String code="";
	if(!(Objects.equals(request.getAttribute("code"),null))){		
		code=(String)request.getAttribute("code");
		flag=true;
	}
	
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    
  <jsp:include page="/loginsupport.jsp" />
 <%=jh.cart()%><br> 
 <% if(flag){%>
    <%	@SuppressWarnings("unchecked")
	    HashMap<String, ProductDataBeans> cart = (HashMap<String, ProductDataBeans>)session.getAttribute("cart");%>
        <h1>カートに追加しました</h1>
        <table border=1>
            <tr>
                <th>画像</th>
                <th>商品名</th>
                <th>値段</th>
            </tr>
            <tr>
                <th width="250"><img src="<%= cart.get(code).getImageURL()%>"></th>
                <th><%= cart.get(code).getProductName() %></th>
                <th><%= cart.get(code).getPrice()%>円</th>
            </tr>
        </table> <br>
  <%}%>

        <%=jh.top()%><br>
        
    </body>
</html>