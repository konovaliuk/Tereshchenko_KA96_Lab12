<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Adding new order</title>
    </head>
    <body>
        <a style = "float: right; display: inline; margin: 0 10px; text-decoration: none; padding: 10px; background-color: #ffad33; border-radius: 3px; color: black;" href = "/userOrders">Back</a>
        <div style="width: 200; margin: 0 auto; text-align: center;">
            <h1 style="text-align: center; margin: 50px auto">Add order</h1>
            <form style="text-align: left;" action="/addOrder" method="POST">
                Capacity: <label><br>
                <input type="number" min="1" step="1" style="width: 200;" name="capacity"/>
                </label> <br><br>
                Class: <label><br>
                <input type="text" style="width: 200;" name="class_"/>
                </label> <br><br>
                Start date: <label><br>
                <input type="date" style="width: 200;" min="2022-06-23" max="9999-12-31" name="start_date"/>
                </label> <br><br>
                End date: <label><br>
                <input type="date" style="width: 200;" min="2022-06-23" max="9999-12-31" name="end_date"/>
                </label> <br><br>
              <input type="submit" class="form-submit-button" value="Add"/>
            </form>
        </div>
    </body>
</html>