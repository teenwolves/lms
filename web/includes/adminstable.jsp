<%-- 
    Document   : adminstable
    Created on : Apr 19, 2017, 5:18:31 PM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
