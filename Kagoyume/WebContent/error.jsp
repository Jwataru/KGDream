<%@page import="jums.JumsHelper" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% JumsHelper jh = JumsHelper.getInstance(); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>error</title>
    </head>
    <body>
    <jsp:include page="/loginsupport.jsp" />
    <%=jh.cart()%><br>
        エラーが発生しました。以下の項目を確認してください。<br>
        <%=request.getAttribute("error")%><br><br>
        <%=jh.top()%>
    </body>
</html>