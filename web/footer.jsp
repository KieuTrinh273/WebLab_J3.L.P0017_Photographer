<%-- 
    Document   : footer
    Created on : Jun 23, 2021, 9:40:05 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/footer.css">
    </head>
    <body>
        <div class="footer">
            <div class="footer-content">
                <a href="#">Create with SimpleSite</a>
                <div class="footer-counter">
                    <c:forEach items="${view}" var="i">
                        <span class="footer-counter-item">${i}</span>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
