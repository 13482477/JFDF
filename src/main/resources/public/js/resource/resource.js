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
					notEmpty : {}
				}
			},
			httpMethod : {
				validators : {
					notEmpty : {}
				}
			},
			url : {
				validators : {
					notEmpty : {}
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
				return $('#dataForm').data('formValidation').validate().isValid();
			},
			success : function(data, textStatus, XHR) {
				$('#formModal').modal('hide');
				cleanForm();
				$('#table').bootstrapTable('refresh');
			},
			error : function(XHR, status , errorThrown) {
				alert(JSON.stringify(XHR));
				alert(status);
				alert(errorThrown);
				alert("服务调用失败");
			}
		});
	});
	
	
	
});