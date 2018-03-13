<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="InstaLowPoly"/>
<%@include file="includes/header.jspf"%>

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
		<c:url var="mainImage" value="${currentMedia.displayUrl }" />
		<%-- lower right thumbnail image --%>
		<img src="${mainImage }" id="mainImage"/>
		<%-- hand image off to JS for processing --%>
		<script type="text/javascript">
    			var mainImage = "${mainImage }";
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
    <c:forEach items="${photos }" begin="${menuIndex - 1 }" end="${menuIndex + 6 }" var="media" >
		<c:url var="menuLink" value="/${account.username }/${media.shortcode }" />
		<a href="${menuLink }">
			<div class="menu__item">
				<c:url var="menuImage" value="${media.displayUrl }" />
      			<img src="${menuImage }" alt="" class="menu__photo" />
				<div>
					<fmt:parseDate value="${media.created }" 
					pattern="EEE MMM dd HH:mm:ss z yyyy" var="parsedDateTime" type="both" />
      				<span class="menu__title"><fmt:formatDate pattern="MM/dd/yyyy" value="${parsedDateTime}"/></span><br />
	  				<span class="menu__date"><fmt:formatDate pattern="h:mm a" value="${parsedDateTime}"/></span>
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
    		<form method="GET">
        		<input type="hidden" name="pagination" value="all">
        		<input type="submit" value="show all">
    		</form>	
    		
    		
    		<a href="#" id="downloader" onclick="saveImage()">Download!</a>
    		
        
        
    <div class="search">
      <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/142996/elastic-srch.png" alt="" class="search__img" />
      <input type="text" class="search__input" placeholder="Search" />
    </div>
  </div>
  
</div>

		

<%@include file="includes/footer.jspf"%>