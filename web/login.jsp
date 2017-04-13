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
                <div class="col-xs-12"><p>${message}</p></div>
            </div>
        </div>
<div class="containers">
    <div class="row">
    <section class="col-xs-12">
        <form action="login" method="post" class="form-inline">
		<div class="form-group">
			<label class="sr-only control-label" for="password">Username</label>
			<input id="password" type="text" name="username" placeholder="username">
		</div>
		<div class="form-group">
			<label class="sr-only control-label" for="password">Password</label>
			<input id="password" type="password" name="password" placeholder="password">
		</div>
		<input class="btn btn-default" type="submit" value="Login">
	</form>
    </section>
    </div>
</div>
        <script type="text/javascript" src="js/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
    </body>
</html>
