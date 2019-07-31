<%@page import="jums.JumsHelper"
        import="jums.ProductDataBeans"
        import="java.util.Objects" 
        import="java.util.ArrayList"        
        %>
<%@page import="java.util.Objects" %>
<%
JumsHelper jh = JumsHelper.getInstance();
@SuppressWarnings("unchecked")
ArrayList<ProductDataBeans> box =(ArrayList<ProductDataBeans>) session.getAttribute("History");
boolean flag=false;
if(!(Objects.equals(session.getAttribute("LoginCheck"),null))){
flag=true;}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/loginsupport.jsp" />
 <%=jh.cart()%><br>
 <%if(flag){%> 
        <h1>購入履歴</h1>
    <%if(box!=null){ %> 
    	 <table border=1>
            <tr>
                <th>画像</th>
                <th>商品名</th>
                <th>値段</th>
                <th>購入日時</th>
            </tr> 
           <% for(ProductDataBeans pdb:box){ %>          
            <tr>
            	<td><img src="<%=pdb.getImageURL()%>"></td>
            	<td><a href="Item?code=<%= pdb.getCode()%>"><%= pdb.getProductName() %></a></td>
                <td><%= pdb.getPrice()%></td>
                <td><%= pdb.getBuyDate()%></td>                
            </tr>
            <% } %>
        </table>
        
        <%}else{%>
         未購入です。
  <%}%>
  <%}else{%>
   <h1>ログインしてください</h1>
  <%}%>
    <br>	   
 <%=jh.top()%><br>
	
</body>
</html>