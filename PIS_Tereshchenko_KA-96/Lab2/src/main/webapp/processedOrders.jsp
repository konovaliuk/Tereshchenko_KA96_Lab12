<%@ page import="com.things.retail.entities.Order" %>
<%@ page import="com.things.retail.service.ItemService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.things.retail.entities.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>My processed orders</title>
    </head>
    <body>
        <a style = "float: right; display: inline; margin: 0 10px; text-decoration: none; padding: 10px; background-color: #420101; border-radius: 3px; color: white;" href = "/signout">Sign out</a>
        <a style = "float: right; display: inline; margin: 0 10px; text-decoration: none; padding: 10px; background-color: #ffad33; border-radius: 3px; color: black;" href = "/index">Main Menu</a><br>
        <div>
        <h1 style = "display: inline-block; text-align: center; margin: 20px 10px">My processed orders</h1><br>
        <%
            List<Order> orders = (List<Order>) request.getAttribute("orders");
            if (orders == null) {
                out.println("<p>No orders created.</p>");
                return;
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            for (Order order : orders) {
                if (order.getItem_id() == 0) {
                    out.println("<div style =\"vertical-align: top; width: 240px; height: 400px; background-color: #fffd8f; text-align: left; display: inline-block; padding: 10px 20px; margin: 10px\">");
                    out.println("<p><b>Ordered item capacity: </b> " + order.getCapacity() + "</p>" +
                                "<p><b>Ordered item class: </b> " + order.getClass_() + "</p>" +
                                "<p><b>Starting date of stay: </b> " + format.format(order.getStart_date()) + "</p>" +
                                "<p><b>Ending date of stay: </b> " + format.format(order.getEnd_date()) + "</p>" +
                                "<p><b>Order status: </b> " + order.getStatus() + "</p><br>");
                    out.println("</div>");
                }
                else {
                    ItemService itemService = new ItemService();
                    long item_id = order.getItem_id();
                    Item item = itemService.findItemById(item_id);

                    long price = 0;
                    Date start_date = order.getStart_date();
                    Date end_date = order.getEnd_date();
                    long diffInMillies = Math.abs(end_date.getTime() - start_date.getTime());
                    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    price = item.getPrice_per_night() * diff;

                    out.println("<div style =\"vertical-align: top; width: 240px; height: 400px; background-color: #fffd8f; text-align: left; display: inline-block; padding: 10px 20px; margin: 10px\">");
                    out.println("<p><b>Ordered item capacity: </b> " + order.getCapacity() + "</p>" +
                                "<p><b>Ordered item class: </b> " + order.getClass_() + "</p>" +
                                "<p><b>Starting date of stay: </b> " + format.format(order.getStart_date()) + "</p>" +
                                "<p><b>Ending date of stay: </b> " + format.format(order.getEnd_date()) + "</p>" +
                                "<p><b>Order status: </b> " + order.getStatus() + "</p>" +
                                "<p><b>Offered item capacity: </b> " + item.getCapacity() + "</p>" +
                                "<p><b>Offered item class: </b> " + item.getClass_() + "</p>" +
                                "<p><b>Overall price: </b> " + price + "</p><br>");
                    out.println("</div>");
                }
            }
        %>
        </div>
    </body>
</html>