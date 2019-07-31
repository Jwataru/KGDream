<%@page import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"
        import="jums.UserDataDTO" %>
<%  JumsHelper jh = JumsHelper.getInstance();
    UserDataDTO udd = null;
    boolean reinput = false;
    // 戻るボタンからフォームされた場合
    if(request.getParameter("mode") != null && request.getParameter("mode").equals("REINPUT")){
        reinput = true;
        udd = (UserDataDTO)session.getAttribute("RegistrationData"); 
   }%>
   
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
 <h1>新規登録</h1>
    <form action="Registrationconfirm" method="POST">
        ユーザー名:
        <input type="text" name="name" value="<% if(reinput){out.print(udd.getName());}%>"><br>
        パスワード: 
        <input type="text" name="password" value="<% if(reinput){out.print(udd.getPassword());}%>"><br>
        メールアドレス: 
        <input type="text" name="mail" value="<% if(reinput){out.print(udd.getMail());}%>"><br>
        住所:
        <input type="text" name="address" value="<% if(reinput){out.print(udd.getAddress());}%>"><br>
       
        <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
        <input type="submit" name="btnSubmit" value="確認画面へ">
    </form>
        <br>
        <%=jh.top()%>
</body>
</html>