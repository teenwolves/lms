<%-- 
    Document   : updatelecturers
    Created on : Apr 17, 2017, 7:37:35 PM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form-horizontal" action="deletelecturer" method="post">
    <table class="table table-hover">
        <tr>
            <th>id</th>
            <th>lecturer</th>
            <th>email</th>
            <th>select</th>
        </tr>
    <c:forEach var="lecturer" items="${lecturers}">
        <tr>
            <td>${lecturer.id}</td>
            <td>${lecturer.name}</td>
            <td>${lecturer.email}</td>
            <td>
                <div class="form-group">
                    <div class="checkbox">
                        <div class="col-sm-10 col-sm-offset-2">
                <label>
                    <input type="checkbox" name="deletinglecturers" value="${lecturer.id}">
                </label>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
    </c:forEach>
    </table>
    <div class="form-group">
        <div class="col-sm-10 col-sm-offset-10">
            <input class="btn btn-default" type="submit" value="Delete">
        </div>
    </div>
</form>