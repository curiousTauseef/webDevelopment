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
<%@ page import="com.junjunguo.guestbook.datamodel.Greeting" %>
<%@ page import="com.junjunguo.guestbook.datamodel.Guestbook" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="GuoJunjun's Personal Web"/>
    <title>my Guest book</title>

    <link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon"/>
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="/css/animate.css"/>
    <link type="text/css" rel="stylesheet" href="/css/main.css"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
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

    <%--Adding the greetings and the form--%>
    <%
        // Create the correct Ancestor key
        Key<Guestbook> theBook = Key.create(Guestbook.class, guestbookName);

        // Run an ancestor query to ensure we see the most up-to-date
        // view of the Greetings belonging to the selected Guestbook.
        List<Greeting> greetings = ObjectifyService.ofy()
                                                   .load()
                                                   .type(Greeting.class) // We want only Greetings
                                                   .ancestor(theBook)    // Anyone in this book
                                                   .order("-date")       // Most recent first - date is indexed.
                                                   .limit(5)             // Only show 5 of them.
                                                   .list();

        if (greetings.isEmpty()) {
    %>
    <p>Guestbook '${fn:escapeXml(guestbookName)}' has no messages.</p>
    <%
    } else {
    %>
    <p>Messages in Guestbook '${fn:escapeXml(guestbookName)}'.</p>
    <%
        // Look at all of our greetings
        for (Greeting greeting : greetings) {
            pageContext.setAttribute("greeting_content", greeting.content);
            String author;
            if (greeting.author_email == null) {
                author = "An anonymous person";
            } else {
                author = greeting.author_email;
                String author_id = greeting.author_id;
                if (user != null && user.getUserId().equals(author_id)) {
                    author += " (You)";
                }
            }
            pageContext.setAttribute("greeting_user", author);
    %>

    <blockquote class=" wow fadeInRight">
        <p>${fn:escapeXml(greeting_content)}</p>
        <footer>
            <cite>${fn:escapeXml(greeting_user)}</cite>
        </footer>
    </blockquote>

    <%
            }
        }
    %>

    <form action="/sign" method="post">
        <div class="form-group wow fadeInRight">
            <textarea class="form-control" name="content" rows="5"></textarea>
        </div>

        <div class="wow fadeInLeft">
            <input type="submit" value="Post Greeting"/>
        </div>
        <input type="hidden" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/>
    </form>
    <%--Adding the greetings and the form end--%>

    <form action="/jsp/guestbook.jsp" method="get">
        <div class="wow fadeInLeft form-inline">
            <input type="text" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/>
        </div>
        <div class="wow fadeInRight btn btn-block">
            <input type="submit" value="Switch Guestbook"/></div>
    </form>
</div>
<script src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/wow.min.js"></script>
<script src="../js/main.js"></script>
</body>
</html>
