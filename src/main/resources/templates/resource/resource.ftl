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
		"/lib/zTree/css/metroStyle/metroStyle.css"
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
		"/lib/zTree/js/jquery.ztree.core.js",
		"/lib/zTree/js/jquery.ztree.excheck.js",
		"/lib/zTree/js/jquery.ztree.exedit.js",
		"/lib/admin-lte/dist/js/demo.js",
		"/lib/admin-lte/dist/js/pages/dashboard.js",
		"/js/resource/resource.js"
		] 
		>
<div class="row">
	<div class="col-md-6">
		<div class="box box-primary">
			<div class="box-header with-border">
				<h3 class="box-title">资源树</h3>
			</div>
			<div class="box-body">
				<ul id="resourceTree" class="ztree"></ul>
			</div>
			<div class="box-footer"></div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="box box-primary">
			<div class="box-header with-border">
				<h3 class="box-title">基本信息</h3>
			</div>
			<div class="box-body">
				<form id="resourceForm" class="form-horizontal">
					<input type="hidden" id="id" name="id" value="" />
					<input type="hidden" id="parentId" name="parentId" value="" />
					<div class="form-group">
						<label class="col-sm-2 control-label">资源类型</label>
						<div class="col-sm-10">
							<select id="resourceType" name="resourceType" class="form-control">
								<option value="" selected="selected">--请选择--</option>
								<option value="MENU">菜单</option>
								<option value="RESOURCE">资源</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">url</label>
						<div class="col-sm-10">
							<select id="url" name="url" class="form-control">
								<option value="">--请选择--</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">资源名称</label>
						<div class="col-sm-10">
							<input id="resourceName" type="text" name="resourceName" class="form-control" placeholder="资源名称">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">请求方法</label>
						<div class="col-sm-10">
							<select id="httpMethod" name="httpMethod" class="form-control">
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
					<div class="form-group">
						<label class="col-sm-2 control-label">资源代码</label>
						<div class="col-sm-10">
							<input id="resourceCode" type="text" name="resourceCode" class="form-control" placeholder="资源代码">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">图标类型</label>
						<div class="col-sm-10 form-inline">
							<select id="resourceIconType" name="resourceIconType" class="form-control">
								<option value="">--请选择--</option>
								<option value="ICON">ICON</option>
								<option value="IMG">IMG</option>
							</select>
							<div class="input-group">
								<input id="iconPath" type="text" name="iconPath" class="form-control" placeholder="资源代码" readonly="readonly">
								<div class="input-group-btn">
									<button type="button" class="btn btn-primary">请选择</button>
								</div>
								<!-- /btn-group -->
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="box-footer"></div>
		</div>
	</div>
</div>

</@base>
