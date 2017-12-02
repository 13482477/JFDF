function detailFormatter(index, row) {
	var html = [];
	$.each(row, function(key, value) {
		html.push('<p><b>' + key + ':</b> ' + value + '</p>');
	});
	return html.join('');
}

function cleanForm() {
	$('#dataForm').formValidation('resetForm', true);
}

function queryParams(params) {
	return {
		page : params.pageNumber - 1,
		size : params.pageSize,
		sort : (params.sortName === undefined ? 'id' : params.sortName) + ',' + params.sortOrder
	}
}

//$.Deferred.success = originDeferred.done;
//$.Deferred.error = originDeferred.fail;
//$.Deferred.complete = originDeferred.always;

$(function() {
	"use strict";
	
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
		cleanForm();
		$('#formModal').modal('show');
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
				cleanForm();
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