<%-- 
    Document   : updatemoduleform
    Created on : Apr 21, 2017, 6:48:33 PM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <section class="col-xs-12">
            <form class="form-horizontal" action="updatemodule" method="post">
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="id">ID</label>
                    <div class="col-sm-10">
                        <input class="form-control hidden" type="text" name="id" value="${updatingModule.id}">
                        <p class="form-control-static" id="id">${updatingModule.id}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="name">Title</label>
                    <div class="col-sm-10">
                        <p class="form-control-static" id="id">${updatingModule.title}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="lecturer">Lecturer</label>
                    <div class="col-sm-10">
                        <select name="lecturer" class="form-control" id="lecturer">
                            <c:forEach var="lecturer" items="${lecturers}">
                                <option <c:if test="${selectedId == lecturer.id}">selected</c:if>
                                    value="<c:out value="${lecturer.id}"/>">
                                <c:out value="${lecturer.name}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10 col-sm-offset-2">
                        <input class="btn btn-default" type="submit" value="Update">
                    </div>
                </div> 
            </form>
        </section>
    </div>
</div>

