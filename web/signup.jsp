<%-- 
    Document   : signup
    Created on : Apr 21, 2017, 9:10:59 PM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up to the LMS</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="includes/csslinks.jsp" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
        <div class="container">
            <div class="row"><h1 class="col-xs-12">Sign up to Teenwolves LMS</h1></div>
        </div>
        <div class="container">
            <div class="row"><p class="col-xs-12">${message}</p></div>
        </div>
            <div class="container">
                <div class="row">
                    <section class="col-xs-12">
                        <form class="form-horizontal" action="signup?action=signme" method="post">
                            <%@include file="includes/adduserform.jsp" %>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="course">Courses</label>
                                <div class="col-sm-10">
                                    <select name="course" class="form-control" id="course">
                                        <c:forEach var="course" items="${courses}">
                                            <option value="<c:out value="${course.id}"/>">
                                            <c:out value="${course.courseName}"/>
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="batch">Batch</label>
                                <div class="col-sm-10">
                                    <select name="batch" class="form-control" id="batch">
                                        <option value="15.1"/>15.1</option>
                                        <option value="15.2"/>15.2</option>
                                        <option value="16.1"/>16.1</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <input class="btn btn-default" type="submit" value="Sign up">
                                </div>
                            </div>
                        </div>
                        </form>
                    </section>
                </div>
        </div>
        <%@include file="includes/jsscripts.jsp" %>
    </body>
</html>
