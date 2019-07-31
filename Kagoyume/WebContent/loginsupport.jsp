<%@page import="jums.UserDataDTO" %>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.util.Objects" %>

<%  
    UserDataDTO udd= new UserDataDTO();
    boolean flag=false;
    if(!(Objects.equals(session.getAttribute("LoginCheck"), null))) {
    	flag=true;
    	udd= (UserDataDTO)session.getAttribute("LoginUser");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% if(flag) { %>
	ようこそ<a href="MyData"><%= udd.getName() %></a>さん<br><br>
    <a href="Login">ログアウト</a>
<% } else { %>
	ようこそ ゲスト さん
    <a href="Login">ログイン</a>
<% } %>