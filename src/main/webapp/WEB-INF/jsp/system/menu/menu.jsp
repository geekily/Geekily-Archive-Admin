<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>

<c:set var="pageContent">
	<form id="form">
		<input type="hidden" name="menuUid">
		<input type="hidden" name="parentMenuUid">
		<input type="hidden" name="currentMode" value="add"> 
		
		<div class="sys-menu-wrapper row">
			<div class="menu-tree-wrapper col d-flex justify-content-center">
		 		<div id="jstree" class="menu-jstree"></div>
			</div>
			<div class="menu-option-wrapper col-xl-8">
				
				<c:import url="/option/radio">
					<c:param name="label">depth</c:param>
					<c:param name="name">depth</c:param>
					<c:param name="codeType">MENU_DEPTH</c:param>
				</c:import>
			
				<c:import url="/option/radio">
					<c:param name="label">activated</c:param>
					<c:param name="name">flagActivated</c:param>
					<c:param name="codeType">YES_NO</c:param>
				</c:import>
			
	 			<c:import url="/option/text">
					<c:param name="label">parent menu</c:param>
					<c:param name="name">parentMenu</c:param>
					<c:param name="placeholder">choose parent menu in the left section.</c:param>
					<c:param name="readonly">true</c:param>
					<c:param name="hide">true</c:param>
				</c:import>
				
				<c:import url="/option/text">
					<c:param name="label">menu name</c:param>
					<c:param name="name">title</c:param>
				</c:import>
				
				<c:import url="/option/text">
					<c:param name="label">url path</c:param>
					<c:param name="name">path</c:param>
				</c:import>
				
				<div class="btn-wrapper row">
					<div class="col-xxl-12 text-end">
						<button type="button" id="btn-mode" class="btn btn-outline-dark">edit mode</button>
						<button type="button" id="btn-save" class="btn btn-outline-dark">save</button>
						<button type="button" id="btn-reset" class="btn btn-outline-dark">reset</button>
						<button type="button" id="btn-delete" class="btn btn-outline-danger hide">delete</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		$(document).ready(function(){
			// objects used a lot
			let obj = {
					$jstree				: $('#jstree')
					, $depth 			: $('input[name="depth"]')
					, $flagActivated 	: $('input[name="flagActivated"]')
					, $parentMenu		: $('input[name="parentMenu"]')
					, $title 			: $('input[name="title"]')
					, $path 			: $('input[name="path"]')
					, $btnDelete		: $('#btn-delete')
					, $menuUid 			: $('input[name="menuUid"]')
					, $parentMenuUid 	: $('input[name="parentMenuUid"]')
					, $currentMode 		: $('input[name="currentMode"]')
			}
			
			/* jstree -------------------------------------- start */
			// draw initial jstree
    		$.ajax({
				type	: 'post',
				url		: './menu-list',
				success	: function(data){
					if(data.resultCode == 1){
						let menuList 	= data.menuList;
						let jstreeData	= new Array();
				
						for(let i = 0; i < menuList.length; i++){
							let menu		= menuList[i];
							let childList 	= menu.childList;
							jstreeData.push({
								'id' 		: menu.menuUid
								, 'parent'	: '#'
								, 'text' 	: menu.title
								, 'data' 	: {
									depth 			: menu.depth
									, path 			: menu.path
									, flagActivated : menu.flagActivated
									}
							})
							for(let j = 0; j < childList.length; j++){
								let child = childList[j];
								jstreeData.push({
									'id' 		: child.menuUid
									, 'parent'	: child.parentMenuUid
									, 'text' 	: child.title
									, 'data' 	: {
										depth 			: child.depth
										, path 			: child.path
										, flagActivated	: child.flagActivated
									}
								})
							}
						}
						
						fnDrawJstree(jstreeData);
					}else if(data.resultCode == 0){
						openModal({
							type 		: 'alert'
							, title		: 'login'
							, message	: data.resultMessage
						})
					}
				}
			});
			
			// function - draw jstree
    		function fnDrawJstree(jstreeData){
    			obj.$jstree.jstree({
					'core' : {
					    'data' 		: jstreeData
						, 'themes' 	: {
							'icons' : false 
						}
    					, 'multiple': false
					}
				}).bind("loaded.jstree", function(e, data){
				    $(this).jstree("open_all");
				}).on('select_node.jstree', function(e, data){
					let node 		= data.node;
					let parentNode 	= obj.$jstree.jstree(true).get_node(node.parent);
					fnDrawMenuData(node);
				})
    		}
			
			// function - put data
    		function fnDrawMenuData(node){
    			let currentMode = obj.$currentMode.val();
    		    let depth 		= node.data.depth;
	
    		    if(currentMode == 'add'){
    		    	if(depth == 1 && obj.$depth.filter(':checked').key() == 'MD-02'){
    		    		obj.$parentMenu.val(node.text);
        		 		obj.$parentMenuUid.val(node.id);
    		    	}else{
           		 		obj.$parentMenu.val('');
        		 		obj.$parentMenuUid.val('');
    		    	}
    		    }else if(currentMode == 'edit'){
    		    	obj.$menuUid.val(node.id);
  
    		    	// depth checkbox
        		    let $targetDepth = obj.$depth.filter('[value="' + depth + '"]');
        		    $targetDepth.prop('checked', true).prop('disabled', false);
        		    obj.$depth.not($targetDepth).prop('disabled', true);
        		    
        		    // flagActivated checkbox
        		    obj.$flagActivated.filter('[value="' + node.data.flagActivated + '"]')
        		    	.prop('checked', true);
        		 	
        		    if(depth == 1){
        		 		fnHideOptionWrapper(obj.$parentMenu);
        		 		obj.$parentMenu.val('');
        		 		obj.$parentMenuUid.val('');
        		 	}else{
        		 		fnShowOptionWrapper(obj.$parentMenu);
        		 		obj.$parentMenu.val(obj.$jstree.jstree(true).get_node(node.parent).text);
        		 		obj.$parentMenuUid.val(node.parent);
        		 	}
        		    obj.$title.val(node.text);
        		 	obj.$path.val(node.data.path);
    		    }
    		}
			/* jstree -------------------------------------- end */

			/* event --------------------------------------- start */
    		// mode button
			$('#btn-mode').on('click', function(){
    			let currentMode = obj.$currentMode.val();
    			let targetMode	= currentMode == 'add' ? 'edit' : 'add';
    			
    			// modify mode button
    			fnChangeModeButton(targetMode);
    			
    			if(targetMode == 'add'){	
    				fnReset();
    			}else if(targetMode == 'edit'){
    				// show delete button
    				fnShowObject(obj.$btnDelete);
    				
    				// select node
					let nodeId 	= obj.$jstree.jstree('get_selected')[0];
					if(nodeId == null){
						nodeId = obj.$jstree.jstree(true).get_node('#').children[0];
					}else{
						obj.$jstree.jstree(true).deselect_all(); 
					}
					obj.$jstree.jstree(true).select_node(nodeId);
    			}
    		})
    		
    		// function - change button's attribute and hidden data
    		function fnChangeModeButton(targetMode){
				// change currren mode value
    			obj.$currentMode.val(targetMode);
    			
    			// modify mode button
    			$('#btn-mode').attr('attr-current-mode', targetMode).html((targetMode == 'add' ? 'edit' : 'add') + ' mode');
			}
    		
			// save button
    		$('#btn-save').on('click', function(){
    			let currentMode = obj.$currentMode.val();
    			let key			= obj.$depth.filter(':checked').key();
    			if(currentMode == 'add' && key == 'MD-02'){
    				if(isEmpty(obj.$parentMenuUid.val())){
    					openModal({
    						type 		: 'alert'
    						, title		: 'menu'
    						, message	: 'choose parent menu.'
    					})
    					return
    				}
    			}
    			if(isEmpty(obj.$title.val())){
					openModal({
						type 		: 'alert'
						, title		: 'menu'
						, message	: 'fill in menu\'s name.'
						, close		: function(){
							obj.$title.focus();
						}
					})
					return
    			}
    			if(isEmpty(obj.$path.val())){
    				openModal({
						type 		: 'alert'
						, title		: 'menu'
						, message	: 'fill in url path.'
						, close		: function(){
							obj.$path.focus();
						}
					})
					return
    			}
    			
    			$.ajax({
					type	: 'post',
					url		: './save-menu',
					contentType : 'application/json',
					data	: $('#form').getStringifiedJSON(),
					success	: function(data){
						if(data.resultCode == 1){
							openModal({
								type 		: 'alert'
								, title		: 'menu'
								, message	: 'data has been successfully saved.'
								, close		: function(){
									location.reload();
								}
							})
						}else if(data.resultCode == 0){
							openModal({
								type 		: 'alert'
								, title		: 'menu'
								, message	: data.resultMessage
							})
						}
					}
				});
    		})
    		
    		// reset button
    		$('#btn-reset').on('click', function(){
    			fnReset();
    		})
    		function fnReset(){
    			fnHideOptionWrapper(obj.$parentMenu);
    			fnHideObject(obj.$btnDelete);
    			obj.$depth.prop('disabled', false);
    			obj.$depth.first().prop('checked', true);
    			obj.$flagActivated.first().prop('checked', true);
    			obj.$parentMenu.val('');
    			obj.$parentMenuUid.val('');
    			obj.$title.val('');
    			obj.$path.val('');
    			obj.$menuUid.val('');
    			obj.$jstree.jstree(true).deselect_all(); // deselect jstree
    			fnChangeModeButton('add');
    		}
			
			// delete button
			obj.$btnDelete.on('click', function(){
				let nodeId 	= obj.$jstree.jstree('get_selected');
				let node	= obj.$jstree.jstree('get_node', nodeId);
				if(node.children.length > 0){
					openModal({
						type 		: 'alert'
						, title		: 'menu'
						, message	: 'this menu has sub menu(s) so it\'s impossible to delete it.'
					})
					return
				}
				
				openModal({
					type 		: 'confirm'
					, title		: 'menu'
					, message	: 'are you sure to delete the menu?'
					, button	: 'delete'
					, confirm	: function(){
						$.ajax({
							type	: 'post',
							url		: './delete-menu',
							contentType : 'application/json',
							data	: $('#form').getStringifiedJSON(),
							success	: function(data){
								if(data.resultCode == 1){
									openModal({
										type 		: 'alert'
										, title		: 'menu'
										, message	: 'data has been successfully deleted.'
										, close		: function(){
											location.reload();
										}
									})
								
								}else if(data.resultCode == 0){
									openModal({
										type 		: 'alert'
										, title		: 'menu'
										, message	: data.resultMessage
									})
								}
							}
						});
					}
				})
			})
			
			// depth checkbox     		
			obj.$depth.on('change', function(){
    			let key = $(this).key();
    			if(key == 'MD-01'){
    				fnHideOptionWrapper(obj.$parentMenu);
    			}else if(key == 'MD-02'){
    				fnShowOptionWrapper(obj.$parentMenu);
    			}
    			obj.$jstree.jstree(true).deselect_all();	// deselect jstree
		 		obj.$parentMenu.val('');
		 		obj.$parentMenuUid.val('');
    		})
    		
    		// menu name input text
    		obj.$title.on('focusout', function(){
    			$(this).val($.trim($(this).val()));
    			let title = $(this).val();
    			if(isNotEmpty(title) && obj.$currentMode.val() == 'add'){
    				obj.$path.val('/' + title.replace(/\s+/g, '').trim());
    			}
    		});
			/* event --------------------------------------- end */
		})
	</script>
</c:set>
<%@ include file="/WEB-INF/jsp/common/layout.jsp" %>
