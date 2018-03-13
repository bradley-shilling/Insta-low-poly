<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="InstaLowPoly"/>
<%@include file="includes/header.jspf"%>

<div class="wrapper">
	  
	  
  <svg class="sidebar" viewBox="0 0 300 100%">
    <path class="s-path" fill="#fff" d="M0,0 50,0 a0,250 0 1,1 0,1000 L0,1000" />
  </svg>
  <div class="static">
  
  		
		
		
		<c:url var="mainImage" value="${currentMedia.displayUrl }" />
		<img src="${mainImage }" style="max-width: 5%;" id="mainImage"/>
		<script type="text/javascript">
    			var mainImage = "${mainImage }";
		</script>
		
		 <div id="container">
			<div id="message"></div>
    			<img id="output" src="#" alt="" />
		</div>
  
    <div class="menu__prompt">Pull white sidebar select a different image from your instagram account</div>
  </div>
  
  
  
  
  <div class="sidebar-content">
    
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
    		
    <div class="search">
      <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/142996/elastic-srch.png" alt="" class="search__img" />
      <input type="text" class="search__input" placeholder="Search" />
    </div>
  </div>
  
</div>

		

<%@include file="includes/footer.jspf"%>