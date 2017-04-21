<%-- 
    Document   : login
    Created on : Apr 9, 2017, 10:32:02 AM
    Author     : https://github.com/teenwolves
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h1 class="col-xs-12">Teenwolves LMS</h1>
            </div>
            <div class="row">
                <div class="col-xs-12"><p>${message}</p></div>
            </div>
            <div class="row">
                <section class="col-xs-12">
                    <form action="login" method="post" class="form-horizontal">
                        <%@include file="includes/loginform.jsp" %>
                        <div class="form-group">
                            <div class="col-sm-10 col-sm-offset-2">
                                <input class="btn btn-success" type="submit" value="Login">
                                <input class="btn btn-info" type="button" value="Sign up">
                            </div>
                        </div>
                    </form>
                </section>
            </div>
        </div>
        <script type="text/javascript" src="js/jquery-3.2.0.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
    </body>
</html>
