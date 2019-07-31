<%@page import="java.util.ArrayList"
        import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"
        import="java.util.Objects"
        import="jums.UserDataDTO" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    UserDataDTO ud = (UserDataDTO)request.getAttribute("UpdateData");
    ArrayList<String> chkList = ud.chkproperties();
    boolean flag2=false; 
    if(!(Objects.equals(request.getAttribute("flag2"),null))){
    	flag2=(boolean)request.getAttribute("flag2");
    }
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
    <% if(chkList.size()==0){ %>
    <%if(flag2){ %>
        <h1>以上の内容で更新しました。</h1>
        ユーザー名:<%= ud.getName()%><br>
        パスワード:<%= ud.getPassword()%><br>
        メールアドレス:<%= ud.getMail()%><br>
        住所:<%= ud.getAddress()%><br>

        <%}else{ %>
        	<h1><%= ud.getName()%>は既に使用されています。</h1>
        	<form action="MyUpdate" method="POST">
                <input type="submit" name="no" value="更新画面に戻る">
      	    </form><br>           
        <% }%>
    <% }else{ %>
        <h1>入力が不完全です</h1>
        <%=jh.chkinput(chkList) %><br>
        <form action="MyUpdate" method="POST">
            <input type="submit" name="back" value="更新画面に戻る">
        </form>
    <% } %>
   <%}else{%>
   <h1>ログインしてください</h1>
  <%}%>   
 <%=jh.top()%><br>

</body>
</html>