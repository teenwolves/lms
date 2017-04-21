<%-- 
    Document   : deletemodules
    Created on : Apr 21, 2017, 12:22:08 PM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <div class="row">
        <section class="col-xs-12">
            <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <form class="form-horizontal" action="deletemodule" method="post">
                <table class="table table-hover">
                    <tr>
                        <th>id</th>
                        <th>title</th>
                        <th>select</th>
                    </tr>
                    <c:forEach var="module" items="${modules}">
                        <tr>
                            <td>${module.id}</td>
                            <td>${module.title}</td>
                            <td>
                                <div class="form-group">
                                    <div class="checkbox">
                                        <div class="col-sm-10 col-sm-offset-2">
                                            <label>
                                                <input type="checkbox" name="deletingmodules" value="${module.id}">
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
        </section>
    </div>
</div>