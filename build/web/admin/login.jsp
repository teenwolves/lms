<%-- 
    Document   : login
    Created on : Apr 14, 2017, 8:43:31 PM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="../css/styles.css">
    </head>
    <body>
        <h1>Admin Portal</h1>
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
			<label class="sr-only control-label" for="admin">Admin</label>
			<input id="password" type="text" name="admin" placeholder="username">
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
        
        <script src="../js/jquery-2.1.4.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/script.js"></script>
    </body>
</html>
