<%@page import ="javax.servlet.http.HttpSession" %>
<%@page import="java.util.HashMap"%>
<%@page import="jums.ProductDataBeans"%>
<%@page import="jums.JumsHelper"%>
<%@page import="jums.UserDataDTO" %>
<%@page import="java.util.Objects" %>

<%
    @SuppressWarnings("unchecked")
    HashMap<String,ProductDataBeans> cart = (HashMap<String,ProductDataBeans>)session.getAttribute("cart");
    int sum = 0;
    JumsHelper jh = new JumsHelper();
    UserDataDTO udd= (UserDataDTO)session.getAttribute("LoginUser");
	boolean flag=false;
	if(!(Objects.equals(session.getAttribute("LoginCheck"),null))){
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
 <%if(flag){%>
        <table border=1>
           <tr>
               <th>画像</th>
               <th>商品名</th>
               <th>値段</th>
              
           </tr>
          
           <tr>
 	<% for(String key:cart.keySet()){%>
 	    <td><img src="<%=cart.get(key).getImageURL()%>"></td>
 		<td><a href="Item?code=<%= cart.get(key).getCode()%>"><%=cart.get(key).getProductName()%><br></a></td> 
 	    <td width="60"><%=cart.get(key).getPrice()%>円</td> 

           
      </tr>
      
        <% sum += Integer.parseInt(cart.get(key).getPrice());%>   		
 	 <%}%>
	   </table>
       
            合計<%=sum%>円  
            <br><br>
            【配達方法】
            <form action ="BuyComplete" method ="POST">
             
               <select name="type">
                <% for(int i = 1; i<=3; i++){ %>
                    <option value="<%=i%>" ><%=jh.Type(i)%></option>
                    
                <%}%>
                </select>                
                <br>
                <input type="hidden" name="sum" value="<%=sum%>"><br>
                <input type="submit" value="上記の内容で購入する">
            </form>
             

           <br>
            <form action ="Cart" method ="POST">
                <input type="submit" value="カートに戻る">
            </form>
            
   <%}else{%>
   <h1>ログインしてください</h1>
    <%}%>        
<%=jh.top()%><br>

</body>
</html>