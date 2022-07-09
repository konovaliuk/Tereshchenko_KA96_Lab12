<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Sign up</title>
    </head>
    <body>
        <main>
            <div style="width: 200; margin: 0 auto; text-align: center;">
                <h1 style="margin: 50px auto;">Sign up</h1>
                <form style="text-align: left;" action="" method="POST">
                    <%String message = (String) request.getAttribute("message");
                        if (message == null) {
                            message="";
                        }%>
                    Username: <br>
                    <input type="text" style="width: 200;" name="username"/> <br><br>
                    Password: <br>
                    <input type="password" style="width: 200;" name="password"/> <br>
                    <p  style="color: red; font-size: 14px">
                        <%=message%>
                    </p>
                    <input type="submit" class="form-submit-button" value="Submit"/>
                </form>
            </div>
        </main>
    </body>
</html>