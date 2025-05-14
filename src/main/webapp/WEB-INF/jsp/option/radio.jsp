<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>

<div class="opt-wrapper row ${hide eq true ? 'hide' : ''}">
	<div class="opt-label col-1 d-flex align-items-center justify-content-center">${optionLabel}</div>
	<div class="opt-content col d-flex align-items-center">
		<div class="row">
			<c:forEach var="radio" items="${radioList}" varStatus="status">
				<div class="opt-radio col-1">
					<input class="form-check-input" type="radio" id="checkbox-${radio.radioKey}" key="${radio.radioKey}" name="${name}" value="${radio.radioValue}" ${(!empty data && (data eq radio.radioKey || data eq radio.radioValue)) || status.index eq 0 ? 'checked' : ''}> 
					<label class="form-check-label" for="checkbox-${radio.radioKey}">${radio.radioLabel}</label>
				</div>
			</c:forEach>
		</div>
	</div>
</div>