function resetForm() {
	$('#dataForm').formValidation('resetForm', true);
}

$(function() {
	"use strict";
	
	
	$('#table').bootstrapTable({
		toggle : 'table',
		pagination : true,
		striped : true,
		showExport : true,
		classes : 'table table-no-bordered',
        pagination : true,
        pageNumber : '1',
        pageSize : '25',
        pageList : [5,10,25,50,100],
        sidePagination : 'server',
        queryParamsType : '',
        dataField : 'content',
        totalField : 'totalElements',
        sortOrder : 'desc',
        sortable: true,
        search : true,
        searchOnEnterKey : true,
        showColumns : true,
        showRefresh : true,
        showToggle : true,
        showPaginationSwitch : true,
        detailView : true,
        clickToSelect : true,
        singleSelect : true,
        toolbar : '#toolbar',
        url : '/resources',
        method : 'get',
        locale : 'zh_CN',
        paginationVAlign : 'both',
        buttonsClass : 'btn btn-flat bg-teal-active color-palette',
        undefinedText : '',
        iconSize : 'sm',
        detailFormatter : function detailFormatter(index, row) {
        	var html = [];
        	$.each(row, function(key, value) {
        		html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        	});
        	return html.join('');
        },
        queryParams : function queryParams(params) {
        	var pageable = {
       			page : params.pageNumber - 1,
       			size : params.pageSize,
       			sort : (params.sortName === undefined ? 'id' : params.sortName) + ',' + params.sortOrder
            };
        	
        	var params = {
        		resourceName : $('#searchBar #resourceName').val(),
        		resourceCode : $('#searchBar #resourceCode').val(),
        		url : $('#searchBar #url').val(),
        		httpMethod : $('#httpMethod').val()
        	}
        	
        	return $.extend({}, pageable, params);
        },		
	    columns: [
	    	{
	    		checkbox : true
	    	},
	    	{
	    		field : 'id',
	    		title : 'ID',
	    		sortable : true
	    	}, 
	    	{
	    		field : 'resourceName',
	    		title : '资源名称',
	    		sortable : true
	    	}, 
	    	{
	    		field : 'resourceCode',
	    		title : '资源代码',
	    		sortable : true
	    	},
	    	{
	    		field : 'url',
	    		title : 'url',
	    	},
	    	{
	    		field : 'httpMethod',
	    		title : '请求方法'
	    	},
	    	{
	    		field : 'description',
	    		title : '描述'
	    	}
	    ]
	});
	
	$('#searchButton').bind('click', function(){
		$('#table').bootstrapTable('refresh');
	});
	
	$('#searchResetButton').bind('click', function(){
		$('#searchBar input,select').val('');
	});
	
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
			resourceName : {
				validators : {
					notEmpty : {}
				}
			},
			resourceCode : {
				validators : {
					notEmpty : {},
					remote : {
						type : 'GET',
						url : '/resource/validation'
					}
				}
			},
			httpMethod : {
				validators : {
					notEmpty : {}
				}
			},
			url : {
				validators : {
					notEmpty : {},
					regexp : {
						regexp: /^\/.*$/i,
                        message: 'url必须以为/开头'
					},
					remote : {
						type : 'GET',
						url : '/resource/validation'
					}
				}
			},
			description : {
				validators : {
					stringLength : {}
				}
			}
		}
	});
	
	$('#createButton').bind('click', function(){
		resetForm();
		$('#formModal').modal('show');
	});
	
	$('#updateButton').bind('click', function(){
		$.ajax({
			async : true,
			type : 'GET',
			url : '/resource',
			beforeSend : function(XHR, settings) {
				if ($('#table').bootstrapTable('getSelections').length == 0) {
					swal("请选择一条记录", '否则无法进行编辑', "warning");
					return false;
				}
				settings.url = settings.url + '/' +  $('#table').bootstrapTable('getSelections')[0].id;
				return true;
			},
			success : function(data, textStatus, XHR) {
				if (data == '') {
					swal("数据不存在", '数据可能已被删除', "warning");
					return;
				}
				
				resetForm();
				$('#dataForm #id').val(data.id);
				$('#dataForm #resourceName').val(data.resourceName);
				$('#dataForm #resourceCode').val(data.resourceCode);
				$('#dataForm #httpMethod').val(data.httpMethod);
				$('#dataForm #url').val(data.url);
				$('#dataForm #description').val(data.description);
				$('#formModal').modal('show');
			},
			error : function(XHR, status , errorThrown) {
				swal("请求错误", XHR.responseJSON, "error");
			}
		});
	});

	$('#formModal').loading({
		message : '工作中...',
		start : false
	});
	
	$('#saveButton').bind('click', function(){
		$.ajax({
			async : true,
			type : $('#dataForm #id').val() == '' ? 'POST' : 'PUT',
			url : '/resource',
			contentType : 'application/json',
			headers : {
				'X-CSRF-TOKEN' : $('#_csrf').val()
			},
			data : JSON.stringify({
				id : $('#dataForm #id').val(),
				resourceName : $('#dataForm #resourceName').val(),
				resourceCode : $('#dataForm #resourceCode').val(),
				httpMethod : $('#dataForm #httpMethod').val(),
				url : $('#dataForm #url').val(),
				description : $('#dataForm #description').val()
			}),
			beforeSend : function(XHR, settings) {
				if (!$('#dataForm').data('formValidation').validate().isValid()) {
					return false;
				}
				else {
					$('#formModal').loading('start');
					return true;
				}
			},
			success : function(data, textStatus, XHR) {
				$('#formModal').modal('hide');
				resetForm();
				$('#dataTable').bootstrapTable('refresh');
			},
			error : function(XHR, status , errorThrown) {
				swal("请求错误", XHR.responseJSON.errors.join(","), "error");
			},
			complete : function(XHR, TS) {
				$('#formModal').loading('stop');
			}
		});
	});
});