<%-- 
    Document   : adminnav
    Created on : Apr 15, 2017, 10:46:05 PM
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
          <li><a href="#">Add Module</a></li>
          <li><a href="#">Update Module</a></li>
          <li><a href="#">Delete Module</a></li>
        </ul>
      </li>
      <li>
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Schedules <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Add Schedule</a></li>
          <li><a href="#">Update Schedule</a></li>
          <li><a href="#">Delete Schedule</a></li>
        </ul>
      </li>
      <li>
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Lecturers <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Add Lecturer</a></li>
          <li><a href="#">Update Lecturer</a></li>
          <li><a href="#">Delete Lecturer</a></li>
        </ul>
      </li>
      <li>
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Add Admin</a></li>
          <li><a href="#">Update Admin</a></li>
          <li><a href="#">Delete Admin</a></li>
        </ul>
      </li>
      <li><a href="#">Settings</a></li>
    </ul>    
  </div>
</nav>
