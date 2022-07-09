<%@ page import="com.things.retail.entities.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.things.retail.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Your orders</title>
    </head>
    <body>
        <a style = "float: right; display: inline; margin: 0 10px; text-decoration: none; padding: 10px; background-color: #420101; border-radius: 3px; color: white;" href = "/signout">Sign out</a>
        <a style = "float: right; display: inline; margin: 0 10px; text-decoration: none; padding: 10px; background-color: #ffad33; border-radius: 3px; color: black;" href = "/index">Main Menu</a>
        <a style = "float: right; display: inline; margin: 0 10px; text-decoration: none; padding: 10px; background-color: #56c45e; border-radius: 3px; color: black;" href = "/addOrder">New order</a><br>
        <div>
        <h1 style = "display: inline-block; text-align: center; margin: 20px 10px">All of your orders</h1><br>
        <%
            List<Order> orders = (List<Order>) request.getAttribute("orders");
            if (orders == null) {
                out.println("<p>No orders created.</p>");
                return;
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            for (Order order : orders) {
                out.println("<div style =\"width: 240px; background-color: #fffd8f; text-align: left; display: inline-block; padding: 10px 20px; margin: 10px\">");
                out.println("<p><b>Ordered item capacity: </b> " + order.getCapacity() + "</p>" +
                            "<p><b>Ordered item class: </b> " + order.getClass_() + "</p>" +
                            "<p><b>Starting date of stay: </b> " + format.format(order.getStart_date()) + "</p>" +
                            "<p><b>Ending date of stay: </b> " + format.format(order.getEnd_date()) + "</p>" +
                            "<p><b>Order status: </b> " + order.getStatus() + "</p><br>");
                out.println("<p style = \"margin: 10px auto; text-align: center; width: 100px; background-color: #6edaeb; padding: 5px 10px; border-radius: 3px;\"><a style = \"text-align: center; color: black; text-decoration: none;\" href = \"/editOrder?order_id="+ order.getOrder_id() +"\" >EDIT</a></p>");
                out.println("<p style = \"margin: 10px auto; text-align: center; width: 100px; background-color: #db4444; padding: 5px 10px; border-radius: 3px;\"><a style = \"text-align: center; color: black; text-decoration: none;\" href = \"/deleteOrder?order_id=" + order.getOrder_id() +"\">DELETE</a></p>");
                out.println("</div>");
            }
        %>
        </div>
    </body>
</html>