<%-- 
    Document   : generalnav
    Created on : Apr 22, 2017, 11:04:37 AM
    Author     : Sudarshana Panditha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default">
  <div class="container">
    <ul class="nav navbar-nav">
      <li><a href="home.jsp">Home</a></li>
      <li>
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Modules <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">My Modules</a></li>
          <li><a href="#">All Modules</a></li>
        </ul>
      </li>
      <li>
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Schedules <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">My Schedule</a></li>
          <li><a href="#">All Schedule</a></li>
        </ul>
      </li>
      <li><a href="#">Settings</a></li>
    </ul>
      <form action="logout" method="get">
          <input class="btn btn-default navbar-btn pull-right" type="submit" value="Log out">
      </form>
      
  </div>
</nav>
