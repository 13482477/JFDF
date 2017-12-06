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
						$('#dataPanel').fadeIn("slow");
						

						// var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						// zTree.addNodes(treeNode, {
						// id : 10000,
						// pId : treeNode.id,
						// name : "new node"
						// });
						return false;
					});
			},
			removeHoverDom : function(treeId, treeNode) {
				$("#addBtn_" + treeNode.tId).unbind().remove();
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
					url : $('#__ctx').val() + '/resource/' + treeNode.id,
					async : false,
					type : 'GET',
					success : function(data) {
						console.log(JSON.stringify(data));
						$('#dataPanel').show();
						
						$('#dataForm #id').val(data.id);
						$('#dataForm #parentId').val(data.parentId);
						
						$('#resourceForm #url option').remove;
						
						var urlStr = 'Url=' + data.url + ';RequestMethod=' + data.httpMethod;
						$('#resourceForm #url').append('<option selected value="' + data.url + '">' + urlStr + '</option>');
						
						$('#resourceForm #resourceType').val(data.resourceType);
						$('#resourceForm #resourceName').val(data.name);
						$('#resourceForm #httpMethod').val(data.httpMethod);
						$('#resourceForm #resourceCode').val(data.code);
						$('#resourceForm #resourceIconType').val(data.resourceIconType);
						if (data.resourceIconType == 'ICON') {
							$('#resourceForm #resourceIconType').next().show();
							$('#resourceForm #iconPath').val(data.iconPath);
						}
						else {
							$('#resourceForm #iconPath').val('');
						}
						
						
						
					},
					error : function(jqXHR, textStatus, errorThrown ) {
						alert("系统错误");
					}
				});
				
				
				return false;
			},
			beforeRemove : function() {

			},
			onExpand : function(event, treeId, treeNode) {
				$.fn.zTree.getZTreeObj('menuTree').reAsyncChildNodes(treeNode, 'refresh');
			}
		}
	});
	// ***************************************tree set***********************************************/
	
	
});