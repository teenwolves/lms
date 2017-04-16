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
        <%@include file="../includes/admincsslinks.jsp" %>
    </head>
    <body>
        <%@include file="../includes/adminnav.jsp" %>
        <div class="container">
            <div class="row"><h1 class="col-xs-12">Add Lecturer</h1></div>
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
				<input class="btn btn-default" type="submit" value="Add Lecturer">
			</div>
		</div>
	</form>

			</section>
		</div>
	</div>
        <%@include file="../includes/adminjsscripts.jsp" %>
    </body>
</html>
