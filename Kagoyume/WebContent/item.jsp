<%@page import="jums.JumsHelper"
        import="jums.ProductDataBeans" %>
<%@page import="java.util.Objects" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    ProductDataBeans pdb = new ProductDataBeans();
    if(!(Objects.equals(session.getAttribute("ItemDetail"),null))){
      pdb = (ProductDataBeans)session.getAttribute("ItemDetail");
    }
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<jsp:include page="/loginsupport.jsp" />
 <%=jh.cart()%><br>   
        <table border=1>
            <tr>
                <th>画像</th>
                <th>商品名</th>
                <th>値段</th>
                <th>説明</th>
                <th>レビュー</th>
                <th>カート</th>
            </tr>
            <tr>
                <td><img src="<%= pdb.getImageURL()%>"></td>
                <td width="200"><%= pdb.getProductName()%></td>
                <td width="60"><%= pdb.getPrice()%>円</td>
                <td width="300"><%= pdb.getDescrition()%></td>
                <td width="40"><%= pdb.getRate()%></td>
                <td width="90"><a href="Add?code=<%= pdb.getCode()%>">カートに追加</a></td>
            </tr>
        </table> <br>
        <%=jh.top()%><br>
      
    </body>
</html>