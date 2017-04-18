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
        </div>
        <div class="container">
            <div class="row"><span class="hidden col-xs-2">lms</span><span class="col-xs-10 text-warning">${message}</span></div>
        </div>
        <div class="container">
            <div class="row">
			<section class="col-xs-12">

	<form action="addlecturer" method="post" class="form-horizontal">
            <%@include file="../includes/adduserform.jsp" %>
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
