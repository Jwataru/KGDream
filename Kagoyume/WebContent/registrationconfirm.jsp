<%@page import="java.util.ArrayList"
        import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"
        import="jums.UserDataDTO" 
        import="java.util.Objects"
        %>
<%
    JumsHelper jh = JumsHelper.getInstance();
	boolean flag1=false; 
	boolean flag2=false;
	if(!(Objects.equals(session.getAttribute("RegistrationData"),null))){
		flag1=true;
	} 
    if(!(Objects.equals(request.getAttribute("flag2"),null))){
    	flag2=(boolean)request.getAttribute("flag2");
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
  <%if(flag1){ %>
  	<% UserDataDTO udd = (UserDataDTO)session.getAttribute("RegistrationData");
       ArrayList<String> chkList = udd.chkproperties();
     %>
    <% if(chkList.size()==0){ %>
    	<%if(flag2){ %>
	        <h1>登録確認</h1>
		        ユーザー名:<%= udd.getName()%><br>
		        パスワード:<%= udd.getPassword()%><br>
		        メールアドレス:<%= udd.getMail()%><br>
		        住所:<%= udd.getAddress()%><br>
		        上記の内容で登録します。よろしいですか？
	        <form action="RegistrationComplete" method="POST">
	            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
	            <input type="submit" name="yes" value="はい">
	        </form>
	        <form action="Registration" method="POST">
	            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
	            <input type="hidden" name="mode" value="REINPUT">
	            <input type="submit" name="no" value="登録画面に戻る">
	        </form><br>
        <%}else{ %>
        	<h1><%= udd.getName()%>は既に使用されています。</h1>
        	<form action="Registration" method="POST">
           	    <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
            	<input type="hidden" name="mode" value="REINPUT">
                <input type="submit" name="no" value="登録画面に戻る">
      	    </form><br>           
        <% }%>
    <% }else{ %>
        <h1>入力が不完全です</h1>
        <%=jh.chkinput(chkList) %><br>
        <form action="Registration" method="POST">
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
            <input type="hidden" name="mode" value="REINPUT">
            <input type="submit" name="back" value="登録画面に戻る">
        </form>
    <% } %>
   <% }%>
       <br>
        <%=jh.top()%>
</body>
</html>