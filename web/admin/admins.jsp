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
        <title>Lecturers</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="../includes/admincsslinks.jsp" %>
    </head>
    <body>
        <%@include file="../includes/adminnav.jsp" %>
        <div class="container">
            <div class="row">
                <h1 class="col-xs-12">All Admins</h1>
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
                            <th>admin</th>
                            <th>email</th>
                        </tr>
                        <c:forEach var="admin" items="${admins}">
                            <tr>
                                <td>${admin.id}</td>
                                <td>${admin.name}</td>
                                <td>${admin.email}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </section>
            </div>
        </div>
        <%@include file="../includes/adminjsscripts.jsp" %>
    </body>
</html>
