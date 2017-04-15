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
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="../css/styles.css">
    </head>
    <body>
        
<div class="container">
    <div class="row">
        <h1 class="col-xs-12">Admin Portal</h1>
     </div>
    <div class="row">
        <div class="col-xs-12"><p>${message}</p></div>
     </div>
    <div class="row">
    <section class="col-xs-12">
        <form action="login" method="post" class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-2 control-label" for="username">User</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="password" type="text" name="username" placeholder="username">
                        </div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="password">Password</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="password" type="password" name="password" placeholder="password">
                        </div>
		</div>
                <div class="form-group">
                <div class="checkbox">
			<div class="col-sm-10 col-sm-offset-2">
			<label>
				<input type="checkbox" name="rememberme">Remember me
			</label>
			</div>
		</div>
		</div>
		<div class="form-group">
			<div class="col-sm-10 col-sm-offset-2">
				<input class="btn btn-default" type="submit" value="Login">
			</div>
		</div>
	</form>
    </section>
    </div>
</div>
        
        <script src="../js/jquery-2.1.4.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/script.js"></script>
    </body>
</html>
