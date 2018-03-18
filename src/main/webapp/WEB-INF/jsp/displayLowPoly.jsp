<%@ page import="org.jinstagram.auth.oauth.InstagramService" %>
<%@ page import="com.tecelevator.instagram.Constants" %>
<%@ page import="org.jinstagram.Instagram" %>
<%@ page import="org.jinstagram.entity.users.feed.MediaFeedData" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="InstaLowPoly"/>
<%@include file="includes/header.jspf"%>

<%
// check for existance of instagram token
    Object objInstagram = session.getAttribute(Constants.INSTAGRAM_OBJECT);
    Instagram instagram = null;
    if (objInstagram != null) {
        instagram = (Instagram) objInstagram;
    } else {
        response.sendRedirect(request.getContextPath() + "/");
        return;
    }
%>



<div class="wrapper">
	  
  <%-- set up svg element for menu --%>
  <svg class="sidebar" viewBox="0 0 300 100%">
    <path class="s-path" fill="#fff" d="M0,0 50,0 a0,250 0 1,1 0,1000 L0,1000" />
  </svg>
  
  <%-- main static content --%>
  <div class="static">
		<%-- output placeholder for image from canvas element
		This is generated clientside using JS to avoid excess load on server
		 --%>
		<div id="container">
    			<img id="output" src="#" alt="" />
		</div>
		<%-- image url set here --%>
		<c:url var="mainImageToProcess" value="${currentImage.largeImgURL }" />
		<c:url var="mainImageThumbnail" value="${currentImage.thumbnailURL }" />
		<%-- lower right thumbnail image --%>
		<img src="${mainImageThumbnail }" id="mainImage"/>
		<%-- hand image off to JS for processing --%>
		<script type="text/javascript">
    			var mainImage = "${mainImageToProcess }";
		</script>
  	<%-- loading spinner hidden by js when not needed --%>
    <div id="spinner">
		<div class="bounce1"></div>
		<div class="bounce2"></div>
		<div class="bounce3"></div>
	</div>
	<%-- prompt that appears along pull out menu --%>
    <div class="menu__prompt">Select a different photo</div>
  </div>
  
  
  
  <%-- sidebar menu content --%>
  <div class="sidebar-content">
    <%-- loop through instagram photos and build menu --%>
    <c:forEach items="${images }" begin="1" end="8" var="image" >
		<%-- <c:url var="menuLink" value="/${account.username }/${media.shortcode }" />--%>
		<c:url var="menuLink" value="/displayLowPoly/${image.index }" />
		<a href="${menuLink }">
			<div class="menu__item">
				<c:url var="menuImage" value="${image.thumbnailURL }" />
      			<img src="${menuImage }" alt="" class="menu__photo" />
				<div>
      				<span class="menu__title"><c:out value="${image.createdDate }" /></span><br />
	  				<span class="menu__date"><c:out value="${image.createdTime }" /></span>
				</div>
    			</div>
			</a>
	</c:forEach>
    <%-- change items listed in menu
    
    WORKING HERE --%>
    
    
    		<form method="GET">
        		<input type="hidden" name="pagination" value="-">
        		<input type="submit" value="previous">
    		</form>	
    		<form method="GET">
        		<input type="hidden" name="pagination" value="+">
        		<input type="submit" value="next">
    		</form>	
    		
    		
    		<c:url var="showAll" value="/showAll"></c:url>
    		<a href="${showAll }"><button class="button">Show all images</button></a>
    		
    		<a href="#" id="downloader" onclick="#">Download!</a>
    		
        
        
    <div class="search">
      <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/142996/elastic-srch.png" alt="" class="search__img" />
      <input type="text" class="search__input" placeholder="Search" />
    </div>
  </div>
  
</div>

		

<%@include file="includes/footer.jspf"%>