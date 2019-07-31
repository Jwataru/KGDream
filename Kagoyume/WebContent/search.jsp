<%@page import="jums.ProductDataBeans"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jums.JumsHelper" %>
<%@page import="java.util.Objects" %>

<%
    JumsHelper jh = JumsHelper.getInstance();
	boolean flag=false;
	if(!(Objects.equals(session.getAttribute("SearchName"),null))){
		flag=true;
	}
	
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
     <%if(flag){ %>
	     <% ProductDataBeans pdb = (ProductDataBeans)session.getAttribute("SearchName");
		    @SuppressWarnings("unchecked")
	        ArrayList<ProductDataBeans> itemData = (ArrayList<ProductDataBeans>)session.getAttribute("itemData");
		    int amount=(Integer)session.getAttribute("SarchAmount");
		  %>
	        検索キーワード: <%= pdb.getSearchName() %>
	        検索結果数: <%= amount %>
	        表示件数:20件
	        <br><br>
	            <table border=1>
	                <tr>
	                    <th>画像</th>
	                    <th>商品名</th>
	                    <th>値段</th>
	                </tr>
	                <tr>
                    <% for (int i = 0; i < itemData.size(); i++) {%>
                        <td><img src="<%= itemData.get(i).getImageURL()%>"></td>
                        <td width="250"><a href="Item?code=<%= itemData.get(i).getCode()%>"><%= itemData.get(i).getProductName()%></a></td>
                        <td><%= itemData.get(i).getPrice()%>円</td>
	                </tr>
                	<%}%>
                </table> <br><br>
       <%} %>
        <%=jh.top()%><br>
     
</body>
</html>