<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>

<jsp:include page="/WEB-INF/jsp/common/head.jsp">
	<jsp:param value="${isLogin}" name="title"/>
</jsp:include>

<body>
	<input type="hidden" id="contextPath" value="${contextPath}">
	
	<jsp:include page="/WEB-INF/jsp/common/modal.jsp"/>
	<jsp:include page="/WEB-INF/jsp/common/toast.jsp"/>
	<jsp:include page="/WEB-INF/jsp/common/balloon.jsp"/>
	<jsp:include page="/WEB-INF/jsp/common/imagePreview.jsp"/>
	<jsp:include page="/WEB-INF/jsp/common/loading.jsp"/>
	
	<c:choose>
		<c:when test="${isLogin eq true}">
			<div class="layout-container container-fruid d-flex">
		
				<c:import url="/menu">
					<c:param name="subPath" value="${subPath}"></c:param>
				</c:import>
				
				<div class="content-wrapper">
					<div class="top-menu-name-bar d-flex align-items-center justify-content-between">
						<div>${currentMenuTitle}</div>
						<c:if test="${sessionScope.signinType eq 'normal'}">
							<div><i class="bi bi-box-arrow-right" title="Log out" onclick="goToPage('/login/logout');"></i></div>
						</c:if>
					</div>
					<c:out value="${pageContent}" escapeXml="false"/>
				</div>
			</div>
		</c:when>
		<c:when test="${isLogin eq false}">
			<c:out value="${pageContent}" escapeXml="false"/>
		</c:when>
	</c:choose>
</body>
</html>