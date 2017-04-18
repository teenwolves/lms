<%-- 
    Document   : AddModule
    Created on : Apr 14, 2017, 9:24:30 PM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <title>Add Module</title>
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
			<label class="col-sm-2 control-label" for="email">Email</label>
			<div class="col-sm-10">
				<input class="form-control" id="email" type="text" name="email">
			</div>
		</div>
		<div class="form-group">
  			<label class="col-sm-2 control-label" for="jobstatus">Job</label>
  			<div class="col-sm-10">
  			<select name="jobstatus" class="form-control" id="jobstatus">
    			<option value="unemployeed">Unemployed</option>
    			<option value="employeed">Employeed</option>
    			<option value="freelancer">Freelancer</option>
  			</select>
  			</div>
		</div>
		<div class="form-group">
			<div class="checkbox">
			<div class="col-sm-10 col-sm-offset-2">
			<label>
				<input type="checkbox" name="subscribe">Subscribe to our newsletter
			</label>
			</div>
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
