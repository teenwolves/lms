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
        <%@include file="../includes/admincsslinks.jsp" %>
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
            <%@include file="../includes/loginform.jsp" %>
	</form>
    </section>
    </div>
</div>
        
        <%@include file="../includes/adminjsscripts.jsp" %>
    </body>
</html>
