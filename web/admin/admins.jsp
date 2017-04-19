<%-- 
    Document   : admins
    Created on : Apr 19, 2017, 9:12:27 AM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${heading}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="../includes/admincsslinks.jsp" %>
    </head>
    <body>
        <%@include file="../includes/adminnav.jsp" %>
        <div class="container">
            <div class="row">
                <h1 class="col-xs-12">${heading}</h1>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <p class="col-xs-12">${message}</p>
            </div>
        </div>
        <jsp:include page="${fileUrl}" flush="true"/>
        <%@include file="../includes/adminjsscripts.jsp" %>
    </body>
</html>
