<%-- 
    Document   : loginform
    Created on : Apr 16, 2017, 7:16:19 AM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="form-group">
<label class="col-sm-2 control-label" for="username">User</label>
<div class="col-sm-10">
        <input class="form-control" id="username" type="text" name="username" placeholder="username">
</div>
</div>
<div class="form-group">
<label class="col-sm-2 control-label" for="password">Password</label>
<div class="col-sm-10">
    <input class="form-control" id="password" type="password" name="password" placeholder="password">
</div>
</div>
<div class="form-group">
<div class="checkbox">
    <div class="col-sm-10 col-sm-offset-2">
    <label>
	<input type="checkbox" name="rememberme">Remember me
    </label>
    </div>
</div>
</div>
<div class="form-group">
    <div class="col-sm-10 col-sm-offset-2">
	<input class="btn btn-default" type="submit" value="Login">
    </div>
</div>
