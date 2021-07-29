<%-- 
    Document   : header
    Created on : Jun 18, 2021, 9:43:37 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>header</title>
        <link rel="stylesheet" href="css/header.css">
    </head>
    <body>
        <!--menu nav-->
        <div class="top-nav">
            <div class= "nav-inner">
                <ul>
                    <li> <a href="home" 
                            class="${activeNow=="0"?"activeMenu":""}">My front page</a></li>
                        <c:forEach items="${top3Galleries}" var="gallery" >
                            <li> <a class="${activeNow==gallery.id?"activeMenu":""}" href="gallery-detail?id=${gallery.id}">${gallery.galleryName}</a> </li>
                        </c:forEach>
                            <li> <a href="contact"
                                    class="${activeNow=="-1"?"activeMenu":""}">Contact</a> </li>
                </ul>
            </div>
        </div>

        <div class="title-block">
            <div class="title-block-inner">
                <div class="logo"></div>
                <div class="title">PHOTOGRAPHER</div>
                <div class="subtitle">Welcome to this website</div>
            </div>
        </div>

    </body>
</html>
