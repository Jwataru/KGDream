<%@page import="java.util.HashMap"%>
<%@page import="jums.ProductDataBeans"%>
<%@page import="jums.JumsHelper" %>
<%@page import="java.util.Objects" %>

<% 
	JumsHelper jh = JumsHelper.getInstance();
	@SuppressWarnings("unchecked")
	HashMap<String,ProductDataBeans> cart = (HashMap<String,ProductDataBeans>)session.getAttribute("cart");	
	int sum = 0;
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
<br><br> 
 <%if(flag){
   if(!(cart.size()==0)){%>
	  
       <table border=1>
           <tr>
               <th>画像</th>
               <th>商品名</th>
               <th>値段</th>
               <th>削除</th>
           </tr>
          
           <tr>
 	<% for(String key:cart.keySet()){%>
 	    <td><img src="<%=cart.get(key).getImageURL()%>"></td>
 		<td><a href="Item?code=<%= cart.get(key).getCode()%>"><%=cart.get(key).getProductName()%><br></a></td> 
 	    <td width="60"><%=cart.get(key).getPrice()%>円</td> 	    
        
       
 		<td><form action ="DeleteCart" method ="POST">
 		<input type="hidden" name="code"  value="<%=key%>">
        <input type="submit" name="delete" value="削除">
        </form></td>
      </tr>
      
        <% sum += Integer.parseInt(cart.get(key).getPrice());%>   		
 	 <%}%>
	   </table>
 	     合計<%=sum%>円
 	     
 	     <form action ="BuyConfirm" method ="POST">
         <input type="submit" name="buy" value="購入確認画面へ">
         </form>
    <%}else{%>
         カートに何も入っていません。
  <%}%>
   <%}else{%>
   <h1>ログインしてください</h1>
    <%}%>
<br>
<%=jh.top()%><br>

</body>
</html>