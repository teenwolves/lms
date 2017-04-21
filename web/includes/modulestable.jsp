<%-- 
    Document   : modulestable
    Created on : Apr 21, 2017, 12:21:51 PM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <div class="row">
        <section class="col-xs-12">
            <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <table class="table table-hover">
                <tr>
                    <th>title</th>
                </tr>
                <c:forEach var="module" items="${modules}">
                    <tr>
                        <td>${module.title}</td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </div>
</div>
