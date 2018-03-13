<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="InstaLowPoly"/>
<%@include file="includes/header.jspf"%>


		<h1>Hello!</h1>
		
		
		<c:url var="addUrl" value="/" />
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

<%@include file="includes/footer.jspf"%>