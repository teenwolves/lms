<%-- 
    Document   : selectmodules
    Created on : Apr 21, 2017, 12:22:57 PM
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
                    <th>title</th>
                </tr>
                <c:forEach var="module" items="${modules}">
                    <tr>
                        <td><a href="modules?action=update&AMP;id=${module.id}">${module.id}</a></td>
                        <td><a href="modules?action=update&AMP;id=${module.id}">${module.title}</a></td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </div>
</div>