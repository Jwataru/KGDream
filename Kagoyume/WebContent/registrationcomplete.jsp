<%@page import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"
        import="jums.UserDataDTO"
        import="java.util.Objects"
 %>
<%
    JumsHelper jh = JumsHelper.getInstance();
	boolean flag=false;
	if(!(Objects.equals(session.getAttribute("RegistrationData"),null))){
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
 <% UserDataDTO udd = (UserDataDTO)request.getAttribute("RegistrationData");%>
        <h1>登録結果</h1><br>
        名前: <%= udd.getName()%><br>
        パスワード: <%= udd.getPassword()%><br>
        メールアドレス: <%= udd.getMail()%><br>
        住所: <%= udd.getAddress()%><br>
        以上の内容で登録しました。<br>
        
  <%} %>
        <%=jh.top()%>
</body>
</html>