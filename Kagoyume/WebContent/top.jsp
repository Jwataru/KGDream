<%@page import="jums.JumsHelper"%>
<%@page import="java.util.Objects" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Kagoyume Top</title>
</head>
<body>

    <h1>かごゆめトップ</h1><br><br>
 <jsp:include page="/loginsupport.jsp" />
 <%=jh.cart()%><br>
    
    <br>
    <%if (request.getAttribute("error") != null){out.println(request.getAttribute("error"));}%><br><br>
    <form action="Search" method="GET">        
        <input type="text" name="name" value="">
        <input type="submit" name="btnSubmit" value="検索">
    </form><br>
  
   
</body>
</html>