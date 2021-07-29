<%-- 
    Document   : home
    Created on : Jun 18, 2021, 10:35:04 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <%@include file="header.jsp" %>

            <div class="content">
                <div class="left">

                    <div class="contact-image">
                        <img src="${contact.image}"/><br/>
                        <p>Lorem ipsum dolor sit amet</p>
                    </div>

                    <!--noPage is invalid/page has no gallery => error message-->
                    <c:if test="${galleriesOnPage.size() ==0}">
                        <div class="error">Not found!</div>
                    </c:if>
                    <!--else-->
                    <c:if test="${galleriesOnPage.size() !=0}">
                        <div class="list-gallery">
                            <ul>
                                <c:forEach items="${galleriesOnPage}" var="gallery">
                                    <li class="span4">
                                        <img src="${gallery.firstImg}"/>
                                        <a href="gallery-detail?id=${gallery.id}">
                                            ${gallery.title}
                                        </a>
                                        <p>${gallery.galleryContent}</p>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>

                        <div class="page">
                            <c:forEach begin="1" end="${numOfPage}" var="i">
                                <a class="${i==page?"clicked":""}" 
                                   href="home?page=${i}">${i}</a>
                            </c:forEach>
                        </div>
                    </c:if>

                    <div class="my-infor">
                        <div class="title">About me</div>
                        <p class="infor-content">${contact.description}</p>
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
