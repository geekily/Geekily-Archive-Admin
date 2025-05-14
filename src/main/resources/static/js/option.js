$(document).ready(function(){
	$('.chkbox-all').on('click', function(){
		let name 	= $(this).attr('attr-name');
		let $chkbox	= $('input[name="' + name + '"]');
		$chkbox.prop('checked', $(this).is(':checked'));
	})
	
	$('.chkbox').on('click', function(){
		let name			= $(this).attr('name');
		let $chkbox			= $('input[name="' + name + '"]');
		let flagAllChecked	= true;
		$chkbox.each(function(){
			if(!$(this).prop('checked')){
				flagAllChecked = false;
				return false;
			}
		})
		$('#checkbox-all-' + name).prop('checked', flagAllChecked);
	})
	
	$(document).on('submit', 'form', function(e){
      	$('.chkbox-all').each(function(){
			if($(this).is(':checked')){
				let name 	= $(this).attr('attr-name');
				let $chkbox	= $('input[name="' + name + '"]');
				$chkbox.prop('disabled', true);
			}
		})
    });
})

let fnResetOption = function(name){
	let $obj = $('[name="' + name + '"]');
	let type = $obj.attr('type');
	type = isEmpty(type) ? $obj.prop('tagName') : type;
	
	if(type == 'checkbox'){
		$obj.prop('checked', true);
		$('#checkbox-all-' + name).prop('checked', true);
	}else if(type == 'text'){
		$obj.val('');
	}else if(type == 'SELECT'){ // select
		$obj.find('option:first').prop('selected', true);
	}
}

function fnShowOptionWrapper($object){
	$object.closest('.opt-wrapper').removeClass('hide').show();
}

function fnHideOptionWrapper($object){
	$object.closest('.opt-wrapper').hide();
}