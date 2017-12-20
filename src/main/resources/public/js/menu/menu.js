$(function() {
	"use strict";

	// ***************************************tree set***********************************************/
	function cleanForm() {
		$('#resourceForm input').val('');
		$('#resourceForm select').val('');
		$('#url option').remove();
		$('#url').append('<option value="">--请选择--</option>');
	}

	function initForm(id, parentId) {
		$('#resourceForm #id').val(id == null ? '' : id);
		$('#resourceForm #parentId').val(parentId == null ? '' : parentId);
	}
	
	$.fn.zTree.init($("#menuTree"), {
		async : {
			enable : true,
			url : $('#__ctx').val() + '/menu/children',
			type : 'GET',
			autoParam : [ "id=parentId" ]
		},
		view : {
			addHoverDom : function(treeId, treeNode) {
				var sObj = $("#" + treeNode.tId + "_span");
				if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0)
					return;
				var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='add node' onfocus='this.blur();'></span>";
				sObj.after(addStr);
				var btn = $("#addBtn_" + treeNode.tId);
				if (btn)
					btn.bind("click", function() {
						$('#dataPanel').hide();
						$('#resourcePanel').hide();
						$('#dataForm #iconPath').val('');
						
						$('#dataPanel').fadeIn("slow");
						$('#dataForm').formValidation('resetForm', true);
						$('#dataForm #parentId').val(treeNode.id);

						return false;
					});
				
			},
			removeHoverDom : function(treeId, treeNode) {
				$("#addBtn_" + treeNode.tId).unbind().remove();
				$("#resourceBtn_" + treeNode.tId).unbind().remove();
			},
			selectedMulti : false
		},
		check : {
			enable : true
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		edit : {
			enable : true
		},
		callback : {
			beforeEditName : function(treeId, treeNode) {
				$.ajax({
					url : $('#__ctx').val() + '/menu/' + treeNode.id,
					async : true,
					type : 'GET',
					success : function(data) {
						$('#dataPanel').hide();
						$('#resourcePanel').hide();
						
						$('#dataForm').formValidation('resetForm', true);
						$('#dataPanel').fadeIn("slow");
						$('#dataForm #id').val(data.id);
						$('#dataForm #parentId').val(data.parentId);
						$('#dataForm #name').val(data.name);
						$('#dataForm #menuCode').val(data.menuCode);
						$('#dataForm #iconPath').val(data.iconPath);
					},
					error : function(XHR, textStatus, errorThrown ) {
						var errors = XHR.responseJSON.errors;
						swal(XHR.responseJSON.message, typeof(errors) != "undefined" ? errors.join(",") : XHR.responseJSON.message, "error");
					}
				});
				
				return false;
			},
			beforeRemove : function(treeId, treeNode) {
				$.ajax({
					url : $('#__ctx').val() + '/menu/' + treeNode.id,
					async : true,
					headers : {
						'X-CSRF-TOKEN' : $('#_csrf').val()
					},
					type : 'DELETE',
					success : function(data) {
						swal('删除成功', '', 'success');
						$.fn.zTree.getZTreeObj('menuTree').reAsyncChildNodes(treeNode.getParentNode(), 'refresh');
					},
					error : function(XHR, textStatus, errorThrown ) {
						var errors = XHR.responseJSON.errors;
						swal(XHR.responseJSON.message, typeof(errors) != "undefined" ? errors.join(",") : XHR.responseJSON.message, "error");
					}
				});
				return false;
			},
			onClick : function(event, treeId, treeNode) {
				$('#dataPanel').hide();
				$('#resourcePanel').hide();
				$('#resourcePanel').fadeIn('slow');
				
				$.ajax({
					async : true,
					type : 'GET',
					url : '/menu/' + treeNode.id + '/selectedMenus',
					success : function(data, textStatus, XHR) {
						var strs = new Array();
						$.each(data, function(i, obj){
							var str =[
								'<div class="form-group col-xs-3">',
								'	<div class="checkbox">',
								'		<p>',
								'			<input name="resourceIds" type="checkbox" value="' + obj.resourceId + '" ' + (obj.selected ? 'checked="checked"' : '') +' />',
								'			<label>' + obj.resourceName + '</label>',
								'		</p>',
								'	</div>',
								'</div>',
							].join('');
							strs.push(str);
						});
						
						$('#resourceList').empty();
						$('#resourceList').append(strs.join(''));
					},
					error : function(XHR, status , errorThrown) {
						var errors = XHR.responseJSON.errors;
						swal(XHR.responseJSON.message, typeof(errors) != "undefined" ? errors.join(",") : XHR.responseJSON.message, "error");
					},
				});
				
			},
			onExpand : function(event, treeId, treeNode) {
				$.fn.zTree.getZTreeObj('menuTree').reAsyncChildNodes(treeNode, 'refresh');
			}
		}
	});
	// ***************************************tree set***********************************************/
	
	$('#dataForm').formValidation({
		framework : 'bootstrap',
		excluded: ':disabled',
		icon : {
			valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
		},
		locale: 'zh_CN',
		fields : {
			name : {
				validators : {
					notEmpty : {}
				}
			},
			menuCode : {
				validators : {
					notEmpty : {}
				}
			}
		}
	});
	
	$('#iconModal #confirmButton').bind('click', function(){
		$('#dataForm #iconPath').val($('input[name="optionsRadios"]:checked').val());
		$('#dataForm #iconPath').change(function(){});
		$('#iconModal').modal('hide');
	});
	
	$('#saveButton').bind('click', function(){
		$.ajax({
			async : true,
			type : $('#dataForm #id').val() == '' ? 'POST' : 'PUT',
			url : '/menu',
			contentType : 'application/json',
			headers : {
				'X-CSRF-TOKEN' : $('#_csrf').val()
			},
			data : JSON.stringify({
				parentId : $('#dataForm #parentId').val(),
				id : $('#dataForm #id').val(),
				name : $('#dataForm #name').val(),
				menuCode : $('#dataForm #menuCode').val(),
				iconPath : $('#dataForm #iconPath').val()
			}),
			beforeSend : function(XHR, settings) {
				if (!$('#dataForm').data('formValidation').validate().isValid()) {
					return false;
				}
				else {
					if ($('#dataForm #id').val() != '') {
						settings.url += '/' + $('#dataForm #id').val();
					}
					$('#dataPanel').loading('start');
					return true;
				}
			},
			success : function(data, textStatus, XHR) {
				$('#dataForm').formValidation('resetForm', true);
				$('#dataForm #iconPath').val('');
				$('#dataPanel').fadeOut("slow");
				var treeNode = $.fn.zTree.getZTreeObj('menuTree').getNodeByParam('id', $('#dataForm #parentId').val());
				$.fn.zTree.getZTreeObj('menuTree').reAsyncChildNodes(treeNode, 'refresh');
				
				swal($('#dataForm #id').val() == '' ? "创建陈成功" : '修改成功', "", "success");
			},
			error : function(XHR, status , errorThrown) {
				var errors = XHR.responseJSON.errors;
				swal(XHR.responseJSON.message, typeof(errors) != "undefined" ? errors.join(",") : XHR.responseJSON.message, "error");
			},
			complete : function(XHR, TS) {
				$('#dataPanel').loading('stop');
			}
		});
	});
	
	$('#saveResourceButton').bind('click', function(){
		
		var resourceIds = $('#resourceList :checked').map(function(){
			return $(this).val();
		}).get();
		
		$.ajax({
			async : true,
			type : 'PUT',
			url : '/menu',
			headers : {
				'X-CSRF-TOKEN' : $('#_csrf').val()
			},
			data : {
				resourceIds : $('#resourceList :checked').map(function(){
					return $(this).val();
				}).get()
			},
			beforeSend : function(XHR, settings) {
				$.loading('start');
			},
			success : function(data, textStatus, XHR) {
				swal('保存成功', '', "success");
			},
			error : function(XHR, status , errorThrown) {
				var errors = XHR.responseJSON.errors;
				swal(XHR.responseJSON.message, typeof(errors) != "undefined" ? errors.join(",") : XHR.responseJSON.message, "error");
			},
			complete : function(XHR, TS) {
				$.loading('stop');
			}
		});
		
		
	});
	
});