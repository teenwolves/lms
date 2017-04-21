<%-- 
    Document   : AddModule
    Created on : Apr 14, 2017, 9:24:30 PM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Module</title>
        <%@include file="../includes/admincsslinks.jsp" %>
    </head>
    <body>
        <%@include file="../includes/adminnav.jsp" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <div class="container">
            <div class="row"><h1>Add Module</h1></div>
        </div>
        <div class="container">
            <div class="row"><p>${message}</p></div>
        </div>
        <div class="container">
            <div class="row">
                <section class="col-xs-12">
                    <form class="form-horizontal" action="addmodule" method="post">
                        <input class="hidden" type="text" name="action" value="add">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="title">Module Title</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="title" type="text" name="title">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="lecturer">Lecturer</label>
                            <div class="col-sm-10">
                                <select name="lecturer" class="form-control" id="lecturer">
                                    <c:forEach var="lecturer" items="${lecturers}">
                                        <option value="<c:out value="${lecturer.id}"/>">
                                            <c:out value="${lecturer.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="faculty">Faculty</label>
                            <div class="col-sm-10">
                                <select name="faculty" class="form-control" id="faculty">
                                    <option value="1">Computing</option>
                                    <option value="2">Management</option>
                                    <option value="3">Engineering</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-10 col-sm-offset-2">
                                <input class="btn btn-default" type="submit" value="Add Module">
                            </div>
                        </div>
                    </form>

                </section>
            </div>
        </div>
        <%@include file="../includes/adminjsscripts.jsp" %>
    </body>
</html>
