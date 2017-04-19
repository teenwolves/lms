<%-- 
    Document   : selectadmintable
    Created on : Apr 19, 2017, 5:23:30 PM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <section class="col-xs-12">
            <table class="table table-hover">
                <tr>
                    <th>id</th>
                    <th>admin</th>
                    <th>email</th>
                </tr>
                <c:forEach var="admin" items="${admins}">
                    <tr>
                        <td><a href="admins?action=update&AMP;id=${admin.id}">${admin.id}</a></td>
                        <td><a href="admins?action=update&AMP;id=${admin.id}">${admin.name}</a></td>
                        <td><a href="admins?action=update&AMP;id=${admin.id}">${admin.email}</a></td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </div>
</div>
