<%-- 
    Document   : AddLecturer
    Created on : Apr 14, 2017, 9:31:10 PM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Lecturer</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <div class="container">
		<div class="row">
			<section class="col-xs-12">

	<form class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-2 control-label" for="name">Name</label>
			<div class="col-sm-10">
				<input class="form-control" id="name" type="text" name="name">
			</div>
		</div>
                <div class="form-group">
			<label class="col-sm-2 control-label" for="username">Username</label>
			<div class="col-sm-10">
				<input class="form-control" id="username" type="text" name="username">
			</div>
		</div>
                <div class="form-group">
			<label class="col-sm-2 control-label" for="password">Password</label>
			<div class="col-sm-10">
				<input class="form-control" id="password" type="text" name="password">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="email">Email</label>
			<div class="col-sm-10">
				<input class="form-control" id="email" type="text" name="email">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-10 col-sm-offset-2">
				<input class="btn btn-default" type="submit" value="Submit">
			</div>
		</div>
	</form>

			</section>
		</div>
	</div>
        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/script.js"></script>
    </body>
</html>
