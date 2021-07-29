<%-- 
    Document   : contact
    Created on : Jul 14, 2021, 2:14:16 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/contact.css">
    </head>
    <body>
        <div class="container">
            <%@include file="header.jsp" %>
            <div class="content">
                <div class="left">
                    <div class="contact">
                        <h1 class="font-title">Contact</h1>
                    </div>
                    <div class="contact-infor">
                        <div class="title">
                            <h4>PHOTOGRAPHER</h4>
                        </div>
                        <table>
                            <tr>
                                <td>Address:</td>
                                <td>${contact.address}</td>
                            </tr>
                            <tr>
                                <td>City:</td>
                                <td>${contact.city}</td>
                            </tr>
                            <tr>
                                <td>Country:</td>
                                <td>${contact.country}</td>
                            </tr>
                            <tr>
                                <td>Tel:</td>
                                <td><a href="#">(+84) ${contact.phoneNumber}</a></td>
                            </tr>
                            <tr>
                                <td>Email:</td>
                                <td><a href="#">${contact.email}</a></td>
                            </tr>
                        </table>
                    </div>
                    <div class="map">
                        <iframe  src="${contact.mapUrl}">
                        </iframe>
                    </div>
                </div>
                <div class="right">
                    <%@include file="right.jsp" %>
                </div>
            </div>
            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>
