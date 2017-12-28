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
	<div class="col-md-11">
		<div class="form-inline" role="form">
			<div class="form-group">
				<a id="singleSelection" class="btn btn-primary btn-flat">
					单选题
				</a> 
				<a id="multipleSelection" class="btn btn-primary btn-flat">
					复选题
				</a>
				<a id="essayQuestion" class="btn btn-primary btn-flat">
					问答题
				</a>
			</div>
		</div>
	</div>
	<div class="col-md-1">
		<div class="form-inline" role="form">
			<div class="form-group">
				<a id="singleSelection" class="btn btn-primary btn-flat" style="float: right;">
					完成编辑
				</a> 
			</div>
		</div>
	</div>
</div>
<div class="row">
 &nbsp;
</div>

<div class="box box-primary">
	<div class="box-header with-border">
		<form id="dataForm" class="form-horizontal" role="form" data-toggle="validator">
			<div class="form-group">
				<label class="col-sm-2 control-label">请输入问卷名称</label>
				<div class="col-sm-10">
					<input id="name" name="name" class="form-control" placeholder="请输入...">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">请填写问卷说明</label>
				<div class="col-sm-10">
					<textarea rows="5" class="form-control"></textarea>
				</div>
			</div>
		</form>
	</div>
	<div class="box-body">
		<div class="form-group">
			<div class="row">
				<label class="col-sm-1"></label>
				<label class="control-label col-sm-11">1、食堂伙食品种丰富、口味适中，就餐环境卫生整洁 </label>
			</div>
			<div class="row">
				<div class="checkbox" style="padding-left: 200px;">
					<label>
						<input type="checkbox">非常赞同
					</label>
				</div>
				<div class="checkbox" style="padding-left: 200px;">
					<label>
						<input type="checkbox">非常赞同
					</label>
				</div>
				<div class="checkbox" style="padding-left: 200px;">
					<label>
						<input type="checkbox">非常赞同
					</label>
				</div>
				<div class="checkbox" style="padding-left: 200px;">
					<label>
						<input type="checkbox">非常赞同
					</label>
				</div>
			</div>
		</div>
	</div>
	<div class="box-footer"></div>
</div>

</@base>
<script>

</script>