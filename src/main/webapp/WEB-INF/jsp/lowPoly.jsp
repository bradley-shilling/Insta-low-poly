<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="InstaLowPoly"/>
<%@include file="includes/headerIndex.jspf"%>



		
		<c:url var="instagramLogo" value="img/insta-logo-01.png" />
		<img src="${instagramLogo }" class="instagram__logo">
		<h1>InstaLowPoly</h1>
		<h3>The lowpoly Instagram Image Converter</h3>
		
		
		
		<form action="${addUrl }" method="POST">
		<div class="form-field">
		<label for="instagramAccount">Enter your instagram username</label> 
		</div>
		<div class="form-field">
		<input type="text" name="instagramAccount" id="instagramAccount" placeholder="Enter your username on instagram">
		</div>
		<div class="form-field">
		<input type="submit" value="Low Poly my Instgram">
		</div>
		</form>

<%@include file="includes/footerIndex.jspf"%>