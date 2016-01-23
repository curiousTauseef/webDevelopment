<%--
  This file is part of guestbook
  Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 13, 2016.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Guidings"/>
    <title>video upload test</title>

    <link rel="shortcut icon" href="http://junjunguo.com/junjunguofiles/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="modal-header text-center">
    <h1 class="wow flipInY">Guidings</h1>
    <h2 class="wow flipInY">Video upload test</h2>
</div>

<div class="container-fluid pagination-centered text-center">
    <form action="<%= blobstoreService.createUploadUrl("/videoservice") %>" method="post" enctype="multipart/form-data">
        <input type="text" name="foo">
        <input type="file" name="myFile">
        <input type="submit" value="Submit">
    </form>
</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>