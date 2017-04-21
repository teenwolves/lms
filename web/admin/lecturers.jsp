<%-- 
    Document   : lecturers
    Created on : Apr 17, 2017, 7:35:14 PM
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
        <jsp:include page="${fileUrl}" flush="true"/>
        <%@include file="../includes/adminjsscripts.jsp" %>
    </body>
</html>
