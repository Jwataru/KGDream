<%@page import="jums.JumsHelper" %>
<%@page import="java.util.Objects" %>
<% JumsHelper jh = JumsHelper.getInstance();
	boolean flag=false;
	if(!(Objects.equals(session.getAttribute("LoginCheck"),null))){
    flag=true;}%>
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
<h1>アカウントを削除しました。</h1>
<%}else{%>
   <h1>ログインしてください</h1>
  <%}%>
<%=jh.top()%><br>
</body>
</html>