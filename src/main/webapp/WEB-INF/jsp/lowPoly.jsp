<%@ page import="org.jinstagram.auth.oauth.InstagramService" %>
<%@ page import="com.tecelevator.instagram.Constants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="InstaLowPoly"/>
<%@include file="includes/headerIndex.jspf"%>

<%
    // If an instagram object exists, then set url to first view
    Object objInstagram = session.getAttribute(Constants.INSTAGRAM_OBJECT);
	String authorizationUrl;
	String authorizationMessage;
    if (objInstagram != null) {
    		authorizationUrl = request.getContextPath() + "/displayLowPoly";
    		authorizationMessage = "Get Started!";
        
    } else { // if not set url to instagram api token
    InstagramService service = (InstagramService) session.getServletContext().getAttribute(Constants.INSTAGRAM_SERVICE);
	authorizationUrl = service.getAuthorizationUrl(); 
	authorizationMessage = "Connect to Instagram!";
    }
%>


		<div class="starter-message">
		<c:url var="instagramLogo" value="img/insta-logo-01.png" />
		<img src="${instagramLogo }" class="instagram__logo">
		<h1>InstaLowPoly</h1>
		<h3>The lowpoly Instagram Image Converter</h3>
		<a href="<%= authorizationUrl %>"><button class="button main-button"><%= authorizationMessage %></button></a>
		</div>
		
<%@include file="includes/footerIndex.jspf"%>