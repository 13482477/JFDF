<#include "/layout/mainLayout.ftl" encoding="utf8">
<@base baseTitle="用户管理" 
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
		"/lib/admin-lte/dist/js/pages/dashboard.js",
		"/lib/formvalidation.io/dist/js/formValidation.min.js",
		"/lib/formvalidation.io/dist/js/framework/bootstrap.min.js",
		"/lib/formvalidation.io/dist/js/language/zh_CN.js",
		"/lib/bootstrap-table/dist/bootstrap-table.min.js",
		"/lib/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js",
		"/lib/jquery-loading/dist/jquery.loading.min.js",
		"/lib/sweetalert/docs/assets/sweetalert/sweetalert.min.js",
		"/js/user/user.js"
		] 
		>
<div class="row">
	<div class="col-md-12">
		<div class="box box-primary">
			<div class="box-header with-border">用户列表</div>
			<div class="box-body">
				<div id="searchBar" class="form-inline" role="form">
					<div class="form-group">
						<span><strong>用户名称: </strong></span><input id="username" name="username" class="form-control input-sm" type="text">
					</div>
					<div class="form-group">
						<span><strong>email: </strong></span><input id="email" name="email" class="form-control input-sm" type="text">
					</div>
					<div class="form-group">
						<span><strong>手机: </strong></span><input id="mobile" name="mobile" class="form-control input-sm" type="text">
					</div>
					<div class="form-group">
						<span><strong>昵称: </strong></span><input id="nickname" name="nickname" class="form-control input-sm" type="text">
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
<!-- /.modal -->
</div>
<div class="modal fade" id="formModal">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-purple color-palette">
				<h4>用户</h4>
			</div>
			<div id="body" class="modal-body">
				<form id="dataForm" role="form" data-toggle="validator">
					<input type="hidden" id="id" name="id" value=""> 
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>用户名称</label>
								<input type="text" id="username" name="username" placeholder="请输入..." class="form-control input-sm" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>email</label>
								<input type="text" id="email" name="email" placeholder="请输入..." class="form-control input-sm" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>手机</label>
								<input type="text" id="mobile" name="mobile" placeholder="请输入..." class="form-control input-sm" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>昵称</label>
								<input type="text" id="nickname" name="nickname" placeholder="请输入..." class="form-control input-sm" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="box box-primary">
								<div class="box-header with-border">已选择角色列表</div>
								<div class="box-body">fdsaf</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="box box-primary">
								<div class="box-header with-border">未选择角色列表</div>
								<div class="box-body">fdsaf</div>
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
</@base>