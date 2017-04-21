<%-- 
    Document   : signup
    Created on : Apr 21, 2017, 9:10:59 PM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up to the LMS</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="includes/csslinks.jsp" %>
    </head>
    <body>
        <div class="container">
            <div class="row"><h1 class="col-xs-12">Sign up to Teenwolves LMS</h1></div>
        </div>
        <div class="container">
            <div class="row"><p class="col-xs-12">${message}</p></div>
        </div>
        <div class="container">
            <div class="row">
                <section class="col-xs-12">
                    <form class="form-horizontal">
                        <%@include file="includes/adduserform.jsp" %>
                    </form>
                </section>
            </div>
        </div>
        <%@include file="includes/jsscripts.jsp" %>
    </body>
</html>
