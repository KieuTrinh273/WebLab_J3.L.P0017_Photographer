<%-- 
    Document   : right
    Created on : Jun 30, 2021, 8:36:44 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/social.css"/>
        <script src="https://kit.fontawesome.com/8672701ea7.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="right-header">
            <h4>Share this page</h4>
        </div>

        <div class="right-content">
            <ul>
                <li><i class="fab fa-facebook-square"></i><a href="${contact.facebook}">Share on Facebook</a></li>
                <li><i class="fab fa-twitter-square"></i><a href="${contact.twitter}"><span>Share on Twitter</span></a></li>
                <li><i class="fab fa-google-plus-square"></i><a href="${contact.google}"><span>Share on Google</span></a></li>
            </ul>
        </div>
    </body>
</html>
