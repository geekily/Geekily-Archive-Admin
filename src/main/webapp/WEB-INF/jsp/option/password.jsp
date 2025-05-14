<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>

<div class="opt-wrapper row ${hide eq true ? 'hide' : ''}">
	<div class="opt-label col-1 d-flex align-items-center justify-content-center">${optionLabel}</div>
	<div class="opt-content col d-flex align-items-center">
		<input type="password" class="opt-txt form-control" id="${id}" name="${name}" placeholder="${placeholder}" ${readonly eq true ? 'readonly' : ''} value="${data}" maxlength="${maxlength}">
	</div>
</div>