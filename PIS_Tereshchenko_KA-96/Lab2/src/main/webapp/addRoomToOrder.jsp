<%@ page import="com.things.retail.entities.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.things.retail.entities.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Making an offer</title>
    </head>
    <body>
        <a style = "float: right; display: inline; margin: 0 10px; text-decoration: none; padding: 10px; background-color: #ffad33; bitem-radius: 3px; color: black;" href = "/allOrders">Back</a>
        <div style="width: 200; margin: 0 auto; text-align: center;">
            <h1 style="text-align: center; margin: 50px auto">Choose item to offer</h1>
            <%
            Order order = (Order) request.getAttribute("order");
            out.println("<p><b>Ordered item capacity: </b> " + order.getCapacity() + "</p>" +
                        "<p><b>Ordered item class: </b> " + order.getClass_() + "</p><br>");
            %>
            <%long order_id = Long.valueOf(request.getParameter("order_id"));%>
            <form style="text-align: left;" action="/addItemToOrder" method="POST">
                <input type="hidden" name="order_id" value="<%=order_id%>"/>
                Item id: <label><br>
                <input type="number" min="1" step="1" style="width: 200;" name="item_id"/>
                </label> <br><br>
              <input type="submit" class="form-submit-button" value="Offer"/>
            </form>
        </div>
        <div>
            <h2 style = "display: inline-block; text-align: center; margin: 20px 10px">Existing items:</h2><br>
            <%
            List<Item> items = (List<Item>) request.getAttribute("items");
            if (items == null) {
                out.println("<p>No items created.</p>");
                return;
            }
            for (Item item : items) {
                out.println("<div style =\"width: 240px; background-color: #fffd8f; text-align: left; display: inline-block; padding: 10px 20px; margin: 10px\">");
                out.println("<p><b>Item id: </b> " + item.getItem_id() + "</p>" +
                            "<p><b>Item's price per night: </b> " + item.getPrice_per_night() + "</p>" +
                            "<p><b>Item's capacity: </b> " + item.getCapacity() + "</p>" +
                            "<p><b>Item's class: </b> " + item.getClass_() + "</p><br>");
                out.println("</div>");
            }%>
        </div>
    </body>
</html>