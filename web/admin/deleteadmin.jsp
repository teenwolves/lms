<%-- 
    Document   : deleteadmin
    Created on : Apr 19, 2017, 8:09:51 AM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Lecturer</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="../includes/admincsslinks.jsp" %>
    </head>
    <body>
        <%@include file="../includes/adminnav.jsp" %>
        <div class="container">
            <div class="row">
                <h1 class="col-xs-12">Delete Admins</h1>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <p class="col-xs-12">${message}</p>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <section class="col-xs-12">
                    <%@include file="../includes/deleteadminsform.jsp" %>
                </section>
            </div>
        </div>
        <%@include file="../includes/adminjsscripts.jsp" %>
    </body>
</html>
