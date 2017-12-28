function resetForm() {
    $('#dataForm').formValidation('resetForm', true);
}

$(function () {
    "use strict";

    $('#table').bootstrapTable({
        toggle: 'table',
        pagination: true,
        striped: true,
        showExport: true,
        classes: 'table table-no-bordered',
        pagination: true,
        pageNumber: '1',
        pageSize: '25',
        pageList: [5, 10, 25, 50, 100],
        sidePagination: 'server',
        queryParamsType: '',
        dataField: 'content',
        totalField: 'totalElements',
        sortOrder: 'desc',
        sortable: true,
        search: true,
        searchOnEnterKey: true,
        showColumns: true,
        showRefresh: true,
        showToggle: true,
        showPaginationSwitch: true,
        detailView: true,
        clickToSelect: true,
        singleSelect: true,
        toolbar: '#toolbar',
        url: '/questionnaires',
        method: 'get',
        locale: 'zh_CN',
        paginationVAlign: 'both',
        buttonsClass: 'btn btn-flat bg-teal-active color-palette',
        undefinedText: '',
        iconSize: 'sm',
        detailFormatter: function detailFormatter(index, row) {
            var html = [];
            $.each(row, function (key, value) {
                html.push('<p><b>' + key + ':</b> ' + value + '</p>');
            });
            return html.join('');
        },
        queryParams: function queryParams(params) {
            var pageable = {
                page: params.pageNumber - 1,
                size: params.pageSize,
                sort: (params.sortName === undefined ? 'tid' : params.sortName) + ',' + params.sortOrder
            };

            var params = {
                title: $('#searchBar #title').val()

                // var params = {
                //        tid : "1"
                //
                //
            }
            console.log(params)
            // return $.extend({}, pageable, params);
            return $.extend({}, params);
        },
        columns: [{
            checkbox: true
        }, {
            field: 'tid',
            title: 'tid',
            sortable: true
        }, {
            field: 'title',
            title: '问卷名',
            sortable: true
        }, {
            field: 'remark',
            title: '问卷备注',
            sortable: true
        }, {
            field: 'starttime',
            title: '发布时间',
        }, {
            field: 'state',
            title: '状态'
        }]
    });

    $('#searchButton').bind('click', function () {
        $('#table').bootstrapTable('refresh');
    });

    $('#searchResetButton').bind('click', function () {
        $('#searchBar input,select').val('');
    });

    $('#dataForm').formValidation({
        framework: 'bootstrap',
        excluded: ':disabled',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        locale: 'zh_CN',
        fields: {
            resourceName: {
                validators: {
                    notEmpty: {}
                }
            },
            resourceCode: {
                validators: {
                    notEmpty: {},
                    remote: {
                        type: 'GET',
                        url: '/resource/validation',
                        data: function (validator, $field, value) {
                            var result = {
                                resourceCode: value
                            };
                            $.extend(result, $('#dataForm #id').val() != '' ? {id: $('#dataForm #id').val()} : {});
                            return result;
                        }
                    }
                }
            },
            httpMethod: {
                validators: {
                    notEmpty: {}
                }
            },
            url: {
                validators: {
                    notEmpty: {},
                    regexp: {
                        regexp: /^\/.*$/i,
                        message: 'url必须以/开头'
                    },
                    remote: {
                        type: 'GET',
                        url: '/resource/validation',
                        data: function (validator, $field, value) {
                            var result = {
                                url: value
                            };
                            $.extend(result, $('#dataForm #id').val() != '' ? {id: $('#dataForm #id').val()} : {});
                            return result;
                        }
                    }
                }
            },
            description: {
                validators: {
                    stringLength: {}
                }
            }
        }
    });

    $('#createButton').bind('click', function () {
        resetForm();
        $('#dataForm #id').val('');
        $('#formModal').modal('show');
    });

    $('#updateButton').bind('click', function () {
        $.ajax({
            async: true,
            type: 'GET',
            url: '/questionnaire',
            beforeSend: function (XHR, settings) {
                if ($('#table').bootstrapTable('getSelections').length == 0) {
                    swal("请选择一条记录", '否则无法进行编辑', "warning");
                    return false;
                }
                settings.url = settings.url + '/' + $('#table').bootstrapTable('getSelections')[0].tid;
                return true;
            },
            success: function (data, textStatus, XHR) {
                if (data == '') {
                    swal("数据不存在", '数据可能已被删除', "warning");
                    return;
                }
                resetForm();
                $('#dataForm1 #title').val(data.title);
                $('#dataForm1 #remark').val(data.remark);
                // $('#dataForm1 #title').val(data.title);
                // $('#dataForm1 #title').val(data.title);
                // $('#dataForm1 #title').val(data.title);
                // $('#dataForm1 #title').val(data.title);
                // $('#dataForm #resourceName').val(data.resourceName);
                // $('#dataForm #resourceCode').val(data.resourceCode);
                // $('#dataForm #httpMethod').val(data.httpMethod);
                // $('#dataForm #url').val(data.url);
                // $('#dataForm #description').val(data.description);
                // $('#formModal').modal('show');
            },
            error: function (XHR, status, errorThrown) {
                swal("请求错误", XHR.responseJSON, "error");
            }
        });
    });

    $('#deleteButton').bind('click', function () {
        $.ajax({
            async: true,
            type: 'DELETE',
            url: '/resource',
            contentType: 'application/json',
            headers: {
                'X-CSRF-TOKEN': $('#_csrf').val()
            },
            beforeSend: function (XHR, settings) {
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
            success: function (data, textStatus, XHR) {
                $('#table').bootstrapTable('refresh');
            },
            error: function (XHR, status, errorThrown) {
                swal("请求错误", XHR.responseJSON.errors.join(","), "error");
            },
            complete: function (XHR, TS) {
                $('#formModal').loading('stop');
            }
        });
    });

    $('#formModal').loading({
        message: '工作中...',
        start: false
    });

    $('#saveButton').bind('click', function () {
        $.ajax({
            async: true,
            type: $('#dataForm #id').val() == '' ? 'POST' : 'PUT',
            url: '/questionnaire',
            contentType: 'application/json',
            headers: {
                'X-CSRF-TOKEN': $('#_csrf').val()
            },
            data: JSON.stringify({
                // "tid": 1,
                "title": "2016员工满意度调查",
                "remark": "务必填写",
                "starttime": "2017-12-22",
                "state": "0",
                "problemInfoList": [
                    {
                        "qid": "1",
                        "qtitle": "食堂伙食品种丰富、口味适中，就餐环境卫生整洁",
                        "type": "1",
                        "optionInfoList": [
                            {
                                "oid": "1",
                                "option_name": "非常赞同",
                                "ornull": "0",
                                "orwrite": "0"
                            },
                            {
                                "oid": "2",
                                "option_name": "较赞同",
                                "ornull": "0",
                                "orwrite": "0"
                            },
                            {
                                "oid": "3",
                                "option_name": "基本赞同",
                                "ornull": "0",
                                "orwrite": "0"
                            },
                            {
                                "oid": "4",
                                "option_name": "不赞同",
                                "ornull": "0",
                                "orwrite": "0"
                            }
                        ]
                    },
                    {
                        "qid": "2",
                        "qtitle": "商店物品多",
                        "type": "1",
                        "optionInfoList": [
                            {
                                "oid": "1",
                                "option_name": "非常赞同",
                                "ornull": "0",
                                "orwrite": "0"
                            },
                            {
                                "oid": "2",
                                "option_name": "较赞同",
                                "ornull": "0",
                                "orwrite": "0"
                            },
                            {
                                "oid": "3",
                                "option_name": "基本赞同",
                                "ornull": "0",
                                "orwrite": "0"
                            },
                            {
                                "oid": "4",
                                "option_name": "不赞同",
                                "ornull": "0",
                                "orwrite": "0"
                            }
                        ]
                    },
                    {
                        "qid": "3",
                        "qtitle": "我认为  2017 年，组织效能还要关注 ________ 等方面的组织效能提升工作",
                        "type": "2",
                        "optionInfoList": [
                            {
                                "oid": "1",
                                "option_name": "市场与办事处",
                                "ornull": "0",
                                "orwrite": "0"
                            },
                            {
                                "oid": "2",
                                "option_name": "产品研发",
                                "ornull": "0",
                                "orwrite": "0"
                            },
                            {
                                "oid": "3",
                                "option_name": "生产组织",
                                "ornull": "0",
                                "orwrite": "0"
                            },
                            {
                                "oid": "4",
                                "option_name": "售后服务",
                                "ornull": "0",
                                "orwrite": "0"
                            },
                            {
                                "oid": "5",
                                "option_name": "质量管控",
                                "ornull": "0",
                                "orwrite": "0"
                            },
                            {
                                "oid": "6",
                                "option_name": "财务与信息化",
                                "ornull": "0",
                                "orwrite": "0"
                            }
                        ]
                    },
                    {
                        "qid": "4",
                        "qtitle": "我认为  2018 年，组织效能还要关注 ________ 等方面的组织效能提升工作",
                        "type": "2",
                        "optionInfoList": [
                            {
                                "oid": "1",
                                "option_name": "财务与信息化",
                                "ornull": "0",
                                "orwrite": "0"
                            },
                            {
                                "oid": "2",
                                "option_name": "产品研发",
                                "ornull": "0",
                                "orwrite": "0"
                            },
                            {
                                "oid": "3",
                                "option_name": "生产组织",
                                "ornull": "0",
                                "orwrite": "0"
                            },
                            {
                                "oid": "4",
                                "option_name": "售后服务",
                                "ornull": "0",
                                "orwrite": "0"
                            },
                            {
                                "oid": "5",
                                "option_name": "质量管控",
                                "ornull": "0",
                                "orwrite": "0"
                            }
                        ]
                    },
                    {
                        "qid": "5",
                        "qtitle": "我觉得目前我所在的单位（部门）还存在的问题有哪些？",
                        "type": "3"
                    }
                ]
            }),
            beforeSend: function (XHR, settings) {
                // if (!$('#dataForm').data('formValidation').validate().isValid()) {
                // 	return false;
                // }
                // else {
                // 	if ($('#dataForm #id').val() != '') {
                // 		settings.url += '/' + $('#dataForm #id').val();
                // 	}
                // 	$('#formModal').loading('start');
                // 	return true;
                // }
            },
            success: function (data, textStatus, XHR) {
                $('#formModal').modal('hide');
                resetForm();
                $('#table').bootstrapTable('refresh');
            },
            error: function (XHR, status, errorThrown) {
                swal("请求错误", XHR.responseJSON.errors.join(","), "error");
            },
            complete: function (XHR, TS) {
                $('#formModal').loading('stop');
            }
        });
    });
});