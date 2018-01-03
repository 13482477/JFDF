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
        url : '/users',
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
        		username : $('#searchBar #username').val(),
        		email : $('#searchBar #email').val(),
        		mobile : $('#searchBar #mobile').val(),
        		nickname : $('#searchBar #nickname').val(),
        		nickname : $('#searchBar #nickname').val(),
        	}
        	
        	return $.extend({}, pageable, params);
        },		
	    columns: [{
	    	checkbox : true
	    },{
	    	field : 'id',
	    	title : 'ID',
	    	sortable : true
	    }, {
	    	field : 'username',
	    	title : '用户名称',
	    	sortable : true
	    },{
	    	field : 'email',
	    	title : 'email',
	    	sortable : true
	    },{
	    	field : 'mobile',
	    	title : '手机'
	    },{
	    	field : 'nickname',
	    	title : '昵称'
	    },{
	    	field : 'active',
	    	title : '是否可用'
	    }]
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
			roleName : {
				validators : {
					notEmpty : {}
				}
			},
			username : {
				validators : {
					notEmpty : {},
					remote : {
						type : 'GET',
						url : '/user/validation',
						data : function(validator, $field, value){
							var result = {
							};
							result[$field.attr('id')] = value;
							$.extend(result, $('#dataForm #id').val() != '' ? {id : $('#dataForm #id').val()} : {});
							return result;
						}
					}
				}
			},
			email : {
				validators : {
					notEmpty : {}
				}
			},
			mobile : {
				validators : {
					notEmpty : {}
				}
			},
			nickname : {
				validators : {
					notEmpty : {}
				}
			}
		}
	});
	
	$('#createButton').bind('click', function(){
		$('#dataForm').formValidation('resetForm', true);
		$('#dataForm #id').val('');
		$('#formModal').modal('show');
	});
	
	$('#updateButton').bind('click', function(){
		$.ajax({
			async : true,
			type : 'GET',
			url : '/user',
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
				$('#dataForm').formValidation('resetForm', true);
				$('#dataForm #id').val(data.id);
				$('#dataForm #roleName').val(data.roleName);
				$('#dataForm #roleCode').val(data.roleCode);
				$('#dataForm #description').val(data.description);
				$('#formModal').modal('show');
			},
			error : function(XHR, status , errorThrown) {
				swal("请求错误", XHR.responseJSON, "error");
			}
		});
	});
	
	$('#deleteButton').bind('click', function(){
		$.ajax({
			async : true,
			type : 'DELETE',
			url : '/user',
			contentType : 'application/json',
			headers : {
				'X-CSRF-TOKEN' : $('#_csrf').val()
			},
			beforeSend : function(XHR, settings) {
				if ($('#table').bootstrapTable('getSelections').length == 0) {
					swal("请选择一条记录", '否则无法进行删除', "warning");
					return false;
				}
				else {
					settings.url += '/' + $('#table').bootstrapTable('getSelections')[0].id;
					$('#formModal').loading('start');
					return true;
				}
			},
			success : function(data, textStatus, XHR) {
				$('#table').bootstrapTable('refresh');
			},
			error : function(XHR, status , errorThrown) {
				swal("请求错误", XHR.responseJSON.errors.join(","), "error");
			},
			complete : function(XHR, TS) {
				$('#formModal').loading('stop');
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
			url : '/role',
			contentType : 'application/json',
			headers : {
				'X-CSRF-TOKEN' : $('#_csrf').val()
			},
			data : JSON.stringify({
				id : $('#dataForm #id').val(),
				roleName : $('#dataForm #roleName').val(),
				roleCode : $('#dataForm #roleCode').val(),
				description : $('#dataForm #description').val()
			}),
			beforeSend : function(XHR, settings) {
				if (!$('#dataForm').data('formValidation').validate().isValid()) {
					return false;
				}
				else {
					if ($('#dataForm #id').val() != '') {
						settings.url += '/' + $('#dataForm #id').val();
					}
					$('#formModal').loading('start');
					return true;
				}
			},
			success : function(data, textStatus, XHR) {
				$('#formModal').modal('hide');
				$('#dataForm').formValidation('resetForm', true);
				$('#table').bootstrapTable('refresh');
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