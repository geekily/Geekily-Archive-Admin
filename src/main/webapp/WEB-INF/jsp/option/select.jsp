<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>

<div class="opt-wrapper row ${hide eq true ? 'hide' : ''}">
	<div class="opt-label col-1 d-flex align-items-center justify-content-center">${optionLabel}</div>
	<div class="opt-content col d-flex align-items-center">
		<select class="form-select" id="${id}" name="${name}">
		  	<option selected>select parent menu</option>
		  	<option value="1">One</option>
		  	<option value="2">Two</option>
		  	<option value="3">Three</option>
		</select>
	</div>
</div>

