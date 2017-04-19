<%-- 
    Document   : deleteadminsform
    Created on : Apr 19, 2017, 8:07:02 AM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form class="form-horizontal" action="deleteadmin" method="post">
    <table class="table table-hover">
        <tr>
            <th>id</th>
            <th>admin</th>
            <th>email</th>
            <th>select</th>
        </tr>
    <c:forEach var="admin" items="${admins}">
        <tr>
            <td>${admin.id}</td>
            <td>${admin.name}</td>
            <td>${admin.email}</td>
            <td>
                <div class="form-group">
                    <div class="checkbox">
                        <div class="col-sm-10 col-sm-offset-2">
                <label>
                    <input type="checkbox" name="deletingadmins" value="${admin.id}">
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
