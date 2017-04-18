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
        <title>Lecturers</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="../includes/admincsslinks.jsp" %>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h1 class="col-xs-12">All Lecturers</h1>
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
                    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                    <table class="table table-hover">
                        <tr>
                            <th>id</th>
                            <th>lecturer</th>
                            <th>email</th>
                        </tr>
                        <c:forEach var="lecturer" items="${lecturers}">
                            <tr>
                                <td>${lecturer.id}</td>
                                <td>${lecturer.name}</td>
                                <td>${lecturer.email}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </section>
            </div>
        </div>
        <%@include file="../includes/adminjsscripts.jsp" %>
    </body>
</html>
