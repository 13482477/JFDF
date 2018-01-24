function start(taskId) {
	$.ajax({
		async : true,
		type : 'POST',
		url : '/task/' + taskId + '/start' ,
		headers : {
			'X-CSRF-TOKEN' : $('#_csrf').val()
		},
		data : {
			
		},
		beforeSend : function(XHR, settings) {
			$('body').loading('start');
			return true;
		},
		success : function(data, textStatus, XHR) {
			swal("任务已启动", '', "success");
		},
		error : function(XHR, status , errorThrown) {
			swal("请求错误", XHR.responseJSON.message, "error");
		},
		complete : function(XHR, TS) {
			$('body').loading('stop');
		}
	});
}

function pause(taskId) {
	$.ajax({
		async : true,
		type : 'POST',
		url : '/task/' + taskId + '/pause' ,
		headers : {
			'X-CSRF-TOKEN' : $('#_csrf').val()
		},
		data : {
			
		},
		beforeSend : function(XHR, settings) {
			$('body').loading('start');
			return true;
		},
		success : function(data, textStatus, XHR) {
			swal("任务已暂停", '', "success");
		},
		error : function(XHR, status , errorThrown) {
			swal("请求错误", XHR.responseJSON.message, "error");
		},
		complete : function(XHR, TS) {
			$('body').loading('stop');
		}
	});
}

function stop(taskId) {
	alert(taskId);
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
        sortOrder : 'asc',
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
        url : '/tasks',
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
        		cron : $('#searchBar #cron').val(),
        		taskStatus : $('#searchBar #taskStatus').val(),
        		taskName : $('#searchBar #taskName').val(),
        		taskGroup : $('#searchBar #taskGroup').val()
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
	    	field : 'cron',
	    	title : '时间表达式',
	    	sortable : false
	    },{
	    	field : 'taskStatus',
	    	title : '任务状态',
	    	sortable : true
	    },{
	    	field : 'taskName',
	    	title : '任务名称',
	    	sortable : true
	    },{
	    	field : 'taskGroup',
	    	title : '任务组',
	    	sortable : true
	    }, {
	    	field : 'operate',
	    	title : '操作',
	    	align : 'center',
	    	clickToSelect : false,
	    	formatter : function(value, row, index) {
	    		var strArray = [
	    			'<a href="javascript:void(0);" onclick="start(' + row.id + ');">',
	    			'<i class="fa fa-play"></i>',
	    			'</a>&nbsp;&nbsp;&nbsp;',
	    			'<a href="javascript:void(0);" onclick="pause(' + row.id + ');" class="">',
	    			'<i class="fa fa-pause"></i>',
	    			'</a>&nbsp;&nbsp;&nbsp;',
	    			'<a href="javascript:void(0);" onclick="stop(' + row.id + ');" class="">',
	    			'<i class="fa fa-stop"></i>',
	    			'</a>&nbsp;&nbsp;&nbsp;',
	    		]
	    		return strArray.join('');
	    	}
	    }]
	});
	
	$('start').bind('click', function(){
		alert(1);
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
			cron : {
				validators : {
					notEmpty : {}
				}
			},
			taskStatus : {
				validators : {
					notEmpty : {}
				}
			},
			taskName : {
				validators : {
					notEmpty : {}
				}
			},
			taskGroup : {
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
			url : '/task',
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
				$('#dataForm #cron').val(data.cron);
				$('#dataForm #taskName').val(data.taskName);
				$('#dataForm #taskGroup').val(data.taskGroup);
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
			url : '/task',
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
			url : '/task',
			contentType : 'application/json',
			headers : {
				'X-CSRF-TOKEN' : $('#_csrf').val()
			},
			data : JSON.stringify({
				id : $('#dataForm #id').val(),
				cron : $('#dataForm #cron').val(),
				taskStatus : $('#dataForm #taskStatus').val(),
				taskName : $('#dataForm #taskName').val(),
				taskGroup : $('#dataForm #taskGroup').val(),
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