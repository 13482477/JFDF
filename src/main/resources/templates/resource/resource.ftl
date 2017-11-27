<#include "/layout/mainLayout.ftl" encoding="utf8">
<@base baseTitle="资源管理" 
		baseCss=[
		"/lib/bootstrap/dist/css/bootstrap.min.css",
		"/lib/select2/dist/css/select2.min.css",
		"/lib/font-awesome/css/font-awesome.min.css",
		"/lib/Ionicons/css/ionicons.min.css",
		"/lib/admin-lte/dist/css/AdminLTE.min.css",
		"/lib/admin-lte/dist/css/skins/_all-skins.min.css",
		"/lib/morris.js/morris.css",
		"/lib/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css",
		"/lib/bootstrap-daterangepicker/daterangepicker.css",
		"/lib/bootstrap-wysihtml5/dist/bootstrap-wysihtml5-0.0.2.css",
		"/lib/formvalidation.io/dist/css/formValidation.min.css",
		"/lib/bootstrap-table/dist/bootstrap-table.min.css"
		]
		baseJs=[
		"/lib/jquery/dist/jquery.min.js",
		"/lib/jquery-ui/jquery-ui.min.js",
		"/lib/bootstrap/dist/js/bootstrap.min.js",
		"/lib/raphael/raphael.min.js",
		"/lib/morris.js/morris.min.js",
		"/lib/jquery-sparkline/dist/jquery.sparkline.min.js",
		"/lib/jquery-knob/dist/jquery.knob.min.js",
		"/lib/moment/min/moment.min.js",
		"/lib/bootstrap-daterangepicker/daterangepicker.js",
		"/lib/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js",
		"/lib/bootstrap-wysihtml5/dist/bootstrap-wysihtml5-0.0.2.min.js",
		"/lib/select2/dist/js/select2.full.min.js",
		"/lib/jquery-slimscroll/jquery.slimscroll.min.js",
		"/lib/fastclick/lib/fastclick.js",
		"/lib/admin-lte/dist/js/adminlte.min.js",
		"/lib/admin-lte/dist/js/demo.js",
		"/lib/admin-lte/dist/js/pages/dashboard.js",
		"/lib/formvalidation.io/dist/js/formValidation.min.js",
		"/lib/formvalidation.io/dist/js/framework/bootstrap.min.js",
		"/lib/formvalidation.io/dist/js/language/zh_CN.js",
		"/lib/bootstrap-table/dist/bootstrap-table.min.js",
		"/lib/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js",
		"/js/resource/resource.js"
		] 
		>
<style type="text/css">
.fixed-table-container thead th .th-inner {
	line-height : 20px;
}

.table>thead>tr>td {
	line-height : 15px;
}

.table>thead>tr {
	background: #39cccc !important;
	color: #ffffff;
}

.table>thead:first-child>tr:first-child>th {
	border-top-left-radius :0px;
}

.table>tbody>tr>td {
	line-height: 15px;
}

.table>tbody>tr>td {
	line-height: 15px;
}

.fixed-table-toolbar .btn-group>.btn-group:last-child>.btn {
	border-top-right-radius: 0px;
	border-bottom-right-radius: 0px;
}

#searchBar>.form-group {
	line-height: 36px;
}
</style>
<div class="row">
	<div class="col-md-12">
		<div id="toolbar">
			<div class="form-inline" role="form">
				<div class="form-group">
					<a class="btn btn-success btn-flat btn-sm" title="新增">
						<i class="fa fa-plus"></i>
					</a>
				</div>
				<div class="form-group">
					<a class="btn btn-info btn-flat btn-sm" title="修改">
						<i class="fa fa-edit"></i>
					</a>
				</div>
				<div class="form-group">
					<a class="btn btn-danger btn-flat btn-sm" title="删除">
						<i class="fa fa-remove"></i>
					</a>
				</div>
			</div>
		</div>
		<div class="box box-primary">
			<div class="box-header with-border">资源列表</div>
			<div class="box-body">
				<div id="searchBar" class="form-inline" role="form">
					<div class="form-group">
						<span>资源名称: </span> <input id="resourceName" name="resourceName" class="form-control input-sm" type="text">
					</div>
					<div class="form-group">
						<span>资源代码: </span> <input id="resourceCode" name="resourceCode" class="form-control input-sm" type="text">
					</div>
					<div class="form-group">
						<span>url: </span> <input id="url" name="url" class="form-control input-sm" type="text">
					</div>
					<div class="form-group">
						<span>请求方法: </span><input id="httpMethod" name="httpMethod" class="form-control input-sm" type="text">
					</div>
					<div class="form-group">
						<button id="ok" type="button" class="btn btn-primary btn-sm btn-flat">Let's GO</button>
					</div>
				</div>
				<table id="table"
		               data-toggle="table"
		               data-pagination="true"
		               data-striped="true"
		               data-show-export="true"
		               data-classes="table table-no-bordered"
		               data-pagination="true"
		               data-page-number="1"
		               data-page-size="25"
		               data-page-list=[5,10,25,50,100]
		               data-search="true"
		               data-search-on-enter-key="true"
		               data-show-columns="true"
		               data-show-refresh="true"
		               data-show-toggle="true"
		               data-show-pagination-switch="true"
		               data-detail-view="true"
		               data-click-to-select="true"
		               data-single-select="true"
		               data-toolbar="#toolbar"
		               data-url="${ctx}/resources"
		               data-method="get"
		               data-locale="zh_CN"
		               data-pagination-v-align="both"
		               data-detail-formatter="detailFormatter"
		               data-buttons-class="btn btn-flat bg-teal-active color-palette"
		               data-undefined-text=""
		               data-icon-size="sm"
		               >
		            <thead>
			            <tr>
			                <th data-checkbox="true"></th>
			                <th data-field="id">ID</th>
			                <th data-field="resourceName" data-editable="true">资源名称</th>
			                <th data-field="resourceCode" data-editable="true">资源代码</th>
			                <th data-field="url" data-editable="true">url</th>
			                <th data-field="httpMethod" data-editable="true">请求方法</th>
			                <th data-field="description" data-editable="true">描述</th>
			            </tr>
		            </thead>
		            <tbody>
		            </tbody>
		        </table>
			</div>
			<div class="box-footer"></div>
		</div>
	</div>
</div>
<script>
function detailFormatter(index, row) {
    var html = [];
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
    });
    return html.join('');
}
</script>


	</@base>