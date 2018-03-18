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


<h1><c:out value="${userName }" />'s Insagram Photos</h1>
<h3>Showing all <c:out value="${images.size() }" />  Photos</h3>
<h5>Select an image below to convert to low poly.</h5>
<div class="" style="display:flex; flex-wrap:wrap; justify-content:center">
  <c:forEach items="${images }" var="image" >
		<c:url var="link" value="/displayLowPoly/${image.index }" />
		<a href="${link }">
			<div class="image__container" style="padding:10px;">
				<c:url var="thumb" value="${image.thumbnailURL }" />
      			<img src="${thumb }" alt="" class="thumb" style="width:100%" />
				<div style="display:none;">
      				<span class="date"><c:out value="${image.createdDate }" /></span><br />
	  				<span class="time"><c:out value="${image.createdTime }" /></span>
				</div>
    			</div>
			</a>
	</c:forEach>
</div>

		

<%@include file="includes/footer.jspf"%>