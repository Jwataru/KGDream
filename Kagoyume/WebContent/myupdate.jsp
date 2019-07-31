<%@page import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"
        import="jums.UserDataDTO" %>
<%@page import="java.util.Objects" %>
<%  JumsHelper jh = JumsHelper.getInstance();
    UserDataDTO udd = (UserDataDTO)session.getAttribute("LoginUser");
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
    <form action="MyUpdateResult" method="POST">
        ユーザー名:
        <input type="text" name="name" value="<%= udd.getName()%>"><br>
        パスワード: 
        <input type="text" name="password" value="<%=udd.getPassword()%>"><br>
        メールアドレス: 
        <input type="text" name="mail" value="<%=udd.getMail()%>"><br>
        住所:
        <input type="text" name="address" value="<%= udd.getAddress()%>"><br>
        <input type="submit" name="btnSubmit" value="会員情報更新">
    </form>
	<form action="MyData" method="POST">
      <input type="submit" name="NO" value="会員情報に戻る"style="width:150px">
    </form>
  <%}else{%>
   <h1>ログインしてください</h1>
  <%}%>    
 <%=jh.top()%><br>
</body>
</html>