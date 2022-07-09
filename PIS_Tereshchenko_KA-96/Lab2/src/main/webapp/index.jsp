<%@ page import="com.things.retail.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Main page</title>
    </head>
    <body>
        <a style = "float: right; display: inline; margin: 0 10px; text-decoration: none; padding: 10px; background-color: #420101; border-radius: 3px; color: white;" href = "/signout">Sign out</a><br>
        <div style = "text-align: center; margin: 50px auto; width 500px">
            <h1 style = "display: inline-block; text-align: center; margin: 50px auto">You're signed in as <%=request.getAttribute("username")%>.</h1><br>
            <%
            if(((User) request.getSession().getAttribute("user")).getRole_id() == 2) {
                out.println("<a style = \"display: inline-block; text-align: center; margin: 20px auto; font-size: 25px; width: 250px; text-align: center; text-decoration: none; padding: 10px; background-color: #fffd8f; border-radius: 3px; color: black;\" href = \"/allOrders\">Process the orders</a><br>");
                                out.println("<a style = \"display: inline-block; text-align: center; margin: 20px auto; font-size: 25px; width: 250px; text-align: center; text-decoration: none; padding: 10px; background-color: #fffd8f; border-radius: 3px; color: black;\" href = \"/allProcessedOrders\">Processed orders</a>");
            }
            else{
                out.println("<a style = \"display: inline-block; text-align: center; margin: 20px auto; font-size: 25px; width: 250px; text-align: center; text-decoration: none; padding: 10px; background-color: #fffd8f; border-radius: 3px; color: black;\" href = \"/userOrders\">My Orders</a><br>");
                out.println("<a style = \"display: inline-block; text-align: center; margin: 20px auto; font-size: 25px; width: 250px; text-align: center; text-decoration: none; padding: 10px; background-color: #fffd8f; border-radius: 3px; color: black;\" href = \"/processedOrders\">My processed orders</a>");
            }%>
        </div>
    </body>
</html>