<%@page import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"
        import="jums.UserDataDTO" %>
<%@page import="java.util.Objects" %>

<%
    JumsHelper jh = JumsHelper.getInstance();
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
        <h1>会員情報</h1><br>
        名前: <%= udd.getName()%><br>
        パスワード: <%= udd.getPassword()%><br>
        メールアドレス: <%= udd.getMail()%><br>
        住所: <%= udd.getAddress()%><br><br>
        <form action="MyHistory" method="POST">
            <input type="submit" name="yes" value="購入履歴">
        </form>        
        <form action="MyUpdate" method="POST">
            <input type="submit" name="yes" value="登録情報を更新する">
        </form>
        <form action="MyDelete" method="POST">
            <input type="submit" name="no" value="登録情報を削除する">
        </form><br>        
  <%}else{%>
   <h1>ログインしてください</h1>
  <%}%>               
     
 <%=jh.top()%><br>


</body>
</html>