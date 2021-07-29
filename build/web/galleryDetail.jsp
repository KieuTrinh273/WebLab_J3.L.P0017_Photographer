<%-- 
    Document   : galleryDetail
    Created on : Jun 28, 2021, 9:41:06 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gallery Detail</title>
        <link rel="stylesheet" href="css/style.css">

    </head>
    <body>
        <div class="container">
            <%@include file="header.jsp" %>

            <div class="content">

                <div class="left">

                    <div class="content-left">
                        <c:if test="${empty gallery}">
                            <div class="error">Gallery not found!</div>
                        </c:if>

                        <c:if test="${gallery!=null}">

                            <div class="gallery-title"><h1>${gallery.galleryName}</h1></div>

                            <c:if test="${gallery.firstImg!=null && images.size()>0}">

                                <!-- Slideshow container -->
                                <div class="slideshow-container">
                                    <!-- Full-width images-->
                                    <c:forEach items="${imgOnPage}" var="i">
                                        <div class="mySlides">
                                            <img src="${i}">
                                        </div>
                                    </c:forEach>
                                    <!-- Next and previous buttons -->
                                    <a class="prev" id="btnprev" onclick="plusSlides(-1)">&#10094;</a>
                                    <a class="next" id="btnNext" onclick="plusSlides(1)">&#10095;</a>
                                </div>
                                <!--images of gallery, 8 images/page-->
                                <div class="all-images">
                                    <ul class="${imgOnPage.size()==8?"galley-images":"other-list-gallery"}">
                                        <c:forEach items="${imgOnPage}" var="image" varStatus="status">
                                            <li>
                                                <div class="each-image" >
                                                    <a onclick="currentSlide(${status.index})">
                                                        <img src="${image}" alt="image">
                                                    </a>

                                                </div>
                                            </li>
                                            <c:if test="${status.count%4==0 && status.count!=0}">
                                            </ul>
                                            <ul class="galley-images">
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </div>

                            </c:if>
                            <!--paging-->
                            <c:if test="${numOfPage>1}">
                                <div class="page">
                                    <c:forEach begin="1" end="${numOfPage}" var="i">
                                        <a class="${i==page?"clicked":""}" 
                                           href="gallery-detail?id=${id}&page=${i}">${i}</a>
                                    </c:forEach>
                                </div>
                            </c:if>
                        </c:if>

                    </div>
                </div>
                <div class="right">
                    <%@include file="right.jsp" %>
                </div>
            </div>

            <%@include file="footer.jsp" %>
        </div>
        <script src="js/slideshow.js" type="text/javascript"></script>
    </body>
</html>

