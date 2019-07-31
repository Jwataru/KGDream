<%@page import="jums.JumsHelper" %>
<%@page import="java.util.Objects" %>
<% JumsHelper jh = JumsHelper.getInstance(); %>
<% 
boolean flagin=false; 
if(!(Objects.equals(request.getAttribute("flagin"),null))){
	flagin=(boolean)request.getAttribute("flagin");
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

 <%=jh.cart()%><br>
 <%if(flagin){ %>
        <h1>ログイン画面</h1>
         <form action="LoginProcess" method="post">
            <%if (request.getAttribute("error") != null){out.println(request.getAttribute("error"));}%><br><br>
            ユーザー名：
            <input type="text" name="name" >
             <br>
            PASSWORD：
            <input type="text" name="password" >
             <br> 
            <input type="submit" name="btnSubmit" value="ログイン" >
            <br>          
         </form><br>
         <a href="Registration">
         <button type="button">新規登録</button>
         </a>
         <%}else{ %>
         <h1>ログアウトしました。</h1>
           <% } %>
         <br><br>
         <%= jh.top() %><br>
        
</body>
</html>