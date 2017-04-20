<%-- 
    Document   : updateadminform
    Created on : Apr 19, 2017, 5:21:59 PM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <div class="row">
        <section class="col-xs-12">
            <form class="form-horizontal" action="updateadmin" method="post">
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="id">id</label>
                    <div class="col-sm-10">
                        <input class="form-control hidden" type="text" name="id" value="${updatingAdmin.id}">
                        <p class="form-control-static" id="id">${updatingAdmin.id}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="name">Name</label>
                    <div class="col-sm-10">
                        <p class="form-control-static" id="id">${updatingAdmin.name}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="email">Email</label>
                    <div class="col-sm-10">
                        <p class="form-control-static" id="id">${updatingAdmin.email}</p>
                    </div>
                </div>
                <div class="form-group">
                    <div class="checkbox">
                        <div class="col-sm-10 col-sm-offset-2">
                            <label>
                                <input type="checkbox" value="true" name="adminmanager">Admin Manager
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="checkbox">
                        <div class="col-sm-10 col-sm-offset-2">
                            <label>
                                <input type="checkbox" value="true" name="lecturermanager">Lecturer Manager
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="checkbox">
                        <div class="col-sm-10 col-sm-offset-2">
                            <label>
                                <input type="checkbox" value="true" name="modulemanager">Module Manager
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="checkbox">
                        <div class="col-sm-10 col-sm-offset-2">
                            <label>
                                <input type="checkbox" value="true" name="schedulemanager">Schedule Manager
                            </label>
                        </div>
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