<%-- 
    Document   : addadmin
    Created on : Apr 18, 2017, 1:55:17 PM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Admin</title>
        <%@include file="../includes/admincsslinks.jsp" %>
    </head>
    <body>
        <%@include file="../includes/adminnav.jsp" %>
        <div class="container">
            <div class="row"><h1 class="col-xs-12">Add Admin</h1></div>
        </div>
        <div class="container">
            <div class="row"><span class="hidden col-xs-2"></span><span class="col-xs-10 text-warning">${message}</span></div>
        </div>
        <div class="container">
            <div class="row">
                <section class="col-xs-12">
                    <form action="addadmin" method="post" class="form-horizontal">
                        <%@include file="../includes/adduserform.jsp" %>
                        <div class="form-group">
                            <div class="checkbox">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <label>
                                        <input type="checkbox" name="adminmanager" value="true">Admin Manager
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="checkbox">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <label>
                                        <input type="checkbox" name="lecturermanager" value="true">Lecturer Manager
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="checkbox">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <label>
                                        <input type="checkbox" name="modulemanager" value="true">Module Manager
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="checkbox">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <label>
                                        <input type="checkbox" name="schedulemanager" value="true">Schedule Manager
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-10 col-sm-offset-2">
                                <input class="btn btn-default" type="submit" value="Add Admin">
                            </div>
                        </div>
                    </form>
                </section>
            </div>
        </div>
        <%@include file="../includes/adminjsscripts.jsp" %>
    </body>
</html>
