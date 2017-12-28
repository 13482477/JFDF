<#include "/layout/mainLayout.ftl" encoding="utf8">
<@base baseTitle="问卷管理"
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
		"/lib/bootstrap-table/dist/bootstrap-table.min.css",
		"/lib/jquery-loading/dist/jquery.loading.min.css",
		"/css/adapter.css"
		]
		baseJs=[
		"/lib/jquery/dist/jquery.min.js",
		"/js/app/jqueryHack.js",
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
		"/lib/formvalidation.io/dist/js/formValidation.min.js",
		"/lib/formvalidation.io/dist/js/framework/bootstrap.min.js",
		"/lib/formvalidation.io/dist/js/language/zh_CN.js",
		"/lib/bootstrap-table/dist/bootstrap-table.min.js",
		"/lib/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js",
		"/lib/jquery-loading/dist/jquery.loading.min.js",
		"/lib/sweetalert/docs/assets/sweetalert/sweetalert.min.js",
		"/js/questionnaire/questionnairesss.js"
		] 
		>
<div class="row">
	<div class="col-md-12">
		<div class="box box-primary">
			<div class="box-header with-border">资源列表</div>
			<div class="box-body">
				<div id="searchBar" class="form-inline" role="form">
					<div class="form-group">
						<input id="title" name="title" class="form-control input-sm" type="text">
					</div>
					<div class="form-group">
						<a id="searchButton" class="btn btn-primary btn-flat btn-sm" title="查询">
							<i class="glyphicon glyphicon-search"></i>
						</a>
						<a id="searchResetButton" class="btn btn-primary btn-flat btn-sm" title="重置">
							<i class="glyphicon glyphicon-refresh"></i>
						</a>
					</div>
				</div>
				<div id="toolbar">
					<div class="form-inline" role="form">
						<div class="form-group">
							<a href="javascript: void(0);" id="createButton" class="btn btn-success btn-flat btn-sm" title="新增">
								<i class="glyphicon glyphicon-file"></i>
							</a>
						</div>
						<div class="form-group">
							<a href="javascript: void(0);" id="updateButton" class="btn btn-warning btn-flat btn-sm" title="修改">
								<i class="glyphicon glyphicon-edit"></i>
							</a>
						</div>
						<div class="form-group">
							<a href="javascript: void(0);" id="deleteButton" class="btn btn-danger btn-flat btn-sm" title="删除">
								<i class="glyphicon glyphicon-trash"></i>
							</a>
						</div>
					</div>
				</div>
				<table id="table" >
		        </table>
			</div>
			<div class="box-footer"></div>
		</div>
	</div>
<div class="modal fade" id="formModal">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-purple color-palette">
				<h3><i class="icon fa fa-warning"></i>请填入正确的表单数据...</h3>
				<h4>在输入过程中，系统会进行有效性校验，如果输入错误将无法提交!!!</h4>
			</div>
			<div id="body" class="modal-body">
				<form id="dataForm" role="form" data-toggle="validator">
					<input type="hidden" id="id" name="id" value=""> 
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label>资源名称</label>
								<input type="text" id="resourceName" name="resourceName" placeholder="请输入..." class="form-control input-sm">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>资源代码</label>
								<input type="text" id="resourceCode" name="resourceCode" placeholder="请输入..." class="form-control input-sm">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>请求方法</label>
								<select id="httpMethod" name="httpMethod" class="form-control input-sm">
									<option value="">--请选择--</option>
									<option value="GET">GET</option>
									<option value="HEAD">HEAD</option>
									<option value="POST">POST</option>
									<option value="PUT">PUT</option>
									<option value="PATCH">PATCH</option>
									<option value="DELETE">DELETE</option>
									<option value="OPTIONS">OPTIONS</option>
									<option value="TRACE">TRACE</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label>url</label>
								<input type="text" id="url" name="url" placeholder="请输入..." class="form-control input-sm">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label>说明</label>
								<textarea id="description" name="description" rows="3" cols="" class="form-control"></textarea>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<a href="javascript: void(0);" id="saveButton" class="btn btn-success btn-flat btn-sm" title="保存">
					<i class="glyphicon glyphicon-saved"></i>
				</a>
				<a href="javascript: void(0);" id="cancelButton" class="btn btn-danger btn-flat btn-sm" title="关闭" data-dismiss="modal">
					<i class="glyphicon glyphicon-remove"></i>
				</a>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
</div>

</@base>
<script>

</script>