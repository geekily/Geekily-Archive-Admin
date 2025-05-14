<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>

<div class="opt-wrapper row ${hide eq true ? 'hide' : ''}">
	<div class="opt-label col-1 d-flex align-items-center justify-content-center">${optionLabel}</div>
	<div class="opt-content col d-flex align-items-center">
		<input type="hidden">
		<input class="hide" type="file" id="${id}" name="${name}" accept="${accept}">
		<input type="text" class="${preview eq true ? 'opt-file-preview' : 'opt-txt'} form-control" readonly="readonly" placeholder="click for choosing file.">
		<c:if test="${preview}">
			<span class="file-img-preview">preview</span>
		</c:if>
	</div>
</div>
<script>
	$(function(){
		let name 	= '<c:out value="${name}"/>';
		let $file 	= $('input[name="' + name + '"]');
		let $text 	= $file.next();
		let $hidden	= $file.prev();
		
		$text.on('click', function(){
			$file.trigger('click');
		})
		
		$file.on('change', function(){
			let message = this.files.length > 0 ? this.files[0].name : '';
			$text.val(message);
			
			let type = this.files[0].type;
			if(type.includes('image')){
				alert('yes');
			}else{
				alert('no');
			}
			<c:if test="${crop}">
				pixelarity.open(this.files[0], true, function(res){
					$('#previewImg').attr('src', res);
				}, 'jpg', 1)
			</c:if>
		})
	})
</script>