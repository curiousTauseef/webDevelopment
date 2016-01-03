<%--
  This file is part of guestbook
  Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 03, 2016.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/css/style.css"/>
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="/css/animate.css"/>
</head>

<body>
<div class="modal-header text-center">
    <h1 class="wow flipInY">Guest book</h1>
</div>

<div class="container-fluid text-center">
    <%
        String guestbookName = request.getParameter("guestbookName");
        if (guestbookName == null) {
            guestbookName = "default";
        }
        pageContext.setAttribute("guestbookName", guestbookName);
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user != null) {
            pageContext.setAttribute("user", user);
    %>

    <p class="wow rollIn">Hello, ${fn:escapeXml(user.nickname)}! (You can
        <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>
    <%
    } else {
    %>
    <p class="wow rollIn">Hello!
        <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
        to include your name with greetings you post.</p>
    <%
        }
    %>


    <form action="/guestbook.jsp" method="get">
        <div class="wow fadeInLeft form-inline">
            <input type="text" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/>
        </div>
        <div class="wow fadeInRight btn btn-block">
            <input type="submit" value="Switch Guestbook"/></div>
    </form>
</div>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/wow.min.js"></script>

</body>
</html>
