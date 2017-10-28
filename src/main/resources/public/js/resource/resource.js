$(function() {
	"use strict";
	
	function initForm(id, parentId) {
		$('#resourceForm input').val('');
		if (id != null) {
			$('#resourceForm #id').val(parentId);
		}
		if (parentId != null) {
			$('#resourceForm #parentId').val(parentId);
		}
	}
	
	var setting = {
		async : {
			enable : true,
			url : $('#__ctx').val() + '/resource/' ,
			type : 'GET',
			autoParam : ["id"]
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
						initForm(null, treeNode.id);
//						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
//						zTree.addNodes(treeNode, {
//							id : 10000,
//							pId : treeNode.id,
//							name : "new node"
//						});
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
			beforeEditName : function(){
				
			},
			beforeRemove : function() {
				
			},
			onExpand : function(event, treeId, treeNode) {
				$.fn.zTree.getZTreeObj('resourceTree').reAsyncChildNodes(treeNode, 'refresh');
			}
		}
	};
	
	$.fn.zTree.init($("#resourceTree"), setting);
});