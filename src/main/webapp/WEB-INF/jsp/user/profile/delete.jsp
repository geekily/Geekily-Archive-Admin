<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>

<c:set var="pageContent">
<script>
	$(function(){
		let $profileImage 	= $('.profile-picture-wrapper img');
		let $file 			= $('.profile-picture-wrapper input[type="file"]');
		let $hidden 		= $file.prev();
		
		function fnReset(){
			$file.val('');$hidden.val('');
		}
		
		$profileImage.on('click', function(){
			$file.trigger('click');
		})
		
		$file.on('change', function(){
			if(this.files.length > 0){
				let type = this.files[0].type;
				if(!type.includes('image')){
					fnReset();
					openModal({
						type 		: 'alert'
						, title		: 'article'
						, message	: 'only image file is allowed.'
					})
					return
				}

				pixelarity.open(this.files[0], false , function(succ, res){
					if(succ){
						if(isEmpty(res)){
							openModal({
								type 		: 'alert'
								, title		: 'preview'
								, message	: 'Failed to load preview image.'
							})
							return
						}
						$profileImage.attr('src', res);
						$hidden.val(res);
					}else{
						fnReset();
					}
				}, 'jpg', 1)
			}else{
				fnReset();
				return
			}
		})
		
		let $userName 						= $('input[name="userName"]');
		let $archiveName 					= $('input[name="archiveName"]');
		let $flagArchiveNameChanged 		= $('input[name="flagArchiveNameChanged"]');
		let $password 						= $('input[name="password"]');
		let $passwordCheck 					= $('#passwordCheck');
		let $btnSave 						= $('#btn-save')
		
		$userName.on('input', function(){
			$(this).val($(this).val().replace(/[^^\p{L}\s]/gu, '').replace(/\s{2,}/g, ' '));
		})
		
		$archiveName.on('input', function(){
			$(this).val($(this).val().replace(/[^a-zA-Z0-9]/g, '').toLowerCase());
		})
		
		$password.on('input', function(){
			fnPasswordRegularExpression($(this));
		})
		
		$passwordCheck.on('input', function(){
			fnPasswordRegularExpression($(this));
		})
		
		function fnPasswordRegularExpression($this){
			$this.val($this.val().replace(/[^A-Za-z0-9!@#$%^&*(),.?:{}|<>_-]/g, ''));
		}
		
		$btnSave.on('click', async function(){
			await fnSave();
			$btnSave.prop('disabled', false);
	    });
		
		async function fnSave(){
			$btnSave.prop('disabled', true);
			
	        if(isEmpty($userName.val())){
	        	openModal({
                    type		: 'alert'
                    , title		: 'profile'
                    , message	: 'Fill in user name.'
                    , close		: function(){
                    	$userName.focus();
                    }
                });
	        	return
	        }
	        
	        let archiveName = $.trim($archiveName.val());
	        if(isEmpty(archiveName)){
	        	openModal({
                    type		: 'alert'
                    , title		: 'Profile'
                    , message	: 'Fill in archive name.'
                    , close		: function(){
                    	$archiveName.focus();
                    }
                });
	        	return
	        }
	        
	        let checkResponse = await $.ajax({
				type			: 'post'
				, url			: getFullPath('/user/check-archive-name-existence')
				, contentType	: 'application/json'
				, data			: JSON.stringify({archiveName : archiveName})
	        })
	        if(checkResponse.resultCode == 0){
	        	openModal({
        			type: 'alert'
        			, title: 'Profile'
        			, message : checkResponse.resultMessage
        			, close : function(){
        				$archiveName.focus();
        			}
	        	});
	    		return
	    	}
	        let initialArchiveName = '<c:out value="${userMap.archiveName}"/>';
	        if(initialArchiveName != archiveName){
	        	let archiveNameResponse = await $.ajax({
					type			: 'post'
					, url			: getFullPath('/user/check-archive-name-update-available')
					, contentType	: 'application/json'
					, data			: JSON.stringify({archiveName : archiveName})
		        })
		        if(archiveNameResponse.resultCode == 0){
		        	openModal({
	        			type: 'alert'
	        			, title: 'Profile'
	        			, message : archiveNameResponse.resultMessage
	        			, close : function(){
	        				$archiveName.val(initialArchiveName).focus();
	        				$flagArchiveNameChanged.val('');
	        			}
		        	});
		    		return
		    	}
	        	$flagArchiveNameChanged.val('Y');
	        }else{
	        	$flagArchiveNameChanged.val('');
	        }
	        
	        let password 		= $password.val();
	        let passwordCheck 	= $passwordCheck.val();
	        if(isNotEmpty(password) || isNotEmpty(passwordCheck)){
	        	if(password != passwordCheck){
	        		openModal({
	                    type		: 'alert'
	                    , title		: 'Profile'
	                    , message	: 'Password do not match.'
	                    , close		: function(){
	                    	$password.focus();
	                    }
	                });
	        	}
	        	return
	        }
	        
	        async function fnUpdate(){
	        	let updateResponse = await $.ajax({
		            type			: 'post'
		            , url			: getFullPath('/user/save-profile')
		            , contentType 	: false
					, processData 	: false
		            , data			: $('#form').getFormData()
		        });
		        let modalParam = {
	                type 		: 'alert'
	                , title 	: 'Profile'
	                , message 	: updateResponse.resultMessage
	                , close 	: () => {
	                	if(updateResponse.resultCode == 1){
	                		location.reload();
	                	}
	                }
				}
		        openModal(modalParam);
	        }
	        
	        if(isNotEmpty($flagArchiveNameUpdate.val())){
	    		openModal({
					type 		: 'confirm'
					, title		: 'Profile'
					, message	: 'You seem to have changed the archive name. Once the archive name changes, you cannot change it for 1 week. Are you sure to save?'
					, button	: 'Save'
					, confirm	: function(){
						fnUpdate();
					}
				})
	        }else{
	        	fnUpdate();
	        }
		}
		
		let $btnDelete = $('#btn-delete');
		$btnDelete.on('click', function(){
			openModal({
				type 		: 'confirm'
				, title		: 'Profile'
				, message	: 'You seem to have changed the archive name. Once the archive name changes, you cannot change it for 1 week. Are you sure to save?'
				, button	: 'Save'
				, confirm	: function(){
					fnUpdate();
				}
			})
		})
	})
</script>
	<form id="form">
		
		<input type="hidden" name="flagArchiveNameChanged">
	
		<div class="profile-picture-wrapper d-flex align-items-center justify-content-center">
			<img class="rounded-circle" src="${userMap.thumbnailUrlPath}" onerror="getErrorImageUrl(this, 'profile')" width="200">
			<input type="hidden" name="thumbnail">
			<input type="file" class="hide" name="thumbnail" accept="image/*">
		</div>

		<div class="profile-opt-wrapper">
			<c:import url="/option/text">
				<c:param name="label">Email</c:param>
				<c:param name="maxlength">100</c:param>
				<c:param name="readonly">true</c:param>
				<c:param name="data">${userMap.email}</c:param>
			</c:import>
			
			<c:import url="/option/text">
				<c:param name="label">Name</c:param>
				<c:param name="name">userName</c:param>
				<c:param name="maxlength">20</c:param>
				<c:param name="data">${userMap.userName}</c:param>
			</c:import>
			
			<c:import url="/option/text">
				<c:param name="label">Archive</c:param>
				<c:param name="name">archiveName</c:param>
				<c:param name="maxlength">10</c:param>
				<c:param name="data">${userMap.archiveName}</c:param>
			</c:import>
			
			<c:import url="/option/password">
				<c:param name="label">Password</c:param>
				<c:param name="name">password</c:param>
			</c:import>
			
			<c:import url="/option/password">
				<c:param name="label">Confirm</c:param>
				<c:param name="id">passwordCheck</c:param>
				<c:param name="placeholder">Confirm password</c:param>
			</c:import>
		</div>
		
		<div class="opt-wrapper row">
			<div class="col-xxl-12 text-end">
				<button type="button" id="btn-save" class="btn btn-outline-dark">Save</button>
				<button type="button" id="btn-delete" class="btn btn-outline-danger">Delete account</button>
			</div>
		</div>
	</form>
	<script>
		var rwc = new ResponsiveWebController();
		rwc.addAndRemoveDflexAt(810);
	</script>
</c:set>
<%@ include file="/WEB-INF/jsp/common/layout.jsp" %>
