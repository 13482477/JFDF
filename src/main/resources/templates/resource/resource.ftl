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
									<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">请选择</button>
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 100%; height: 80%">
        <div class="modal-content">
            <div class="modal-body">
            	<section id="new">
                  <h4 class="page-header">66 New Icons in 4.4</h4>
                  <div class="row fontawesome-icon-list">
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue"  value="fa-500px" /><i class="fa fa-fw fa-500px"></i> fa-500px</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-amazon" /><i class="fa fa-fw fa-amazon"></i> fa-amazon</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-balance-scale" /><i class="fa fa-fw fa-balance-scale"></i> fa-balance-scale</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-0" /><i class="fa fa-fw fa-battery-0"></i> fa-battery-0<span class="text-muted">(alias)</span></div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-1" /><i class="fa fa-fw fa-battery-1"></i> fa-battery-1<span class="text-muted">(alias)</span></div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-2" /><i class="fa fa-fw fa-battery-2"></i> fa-battery-2<span class="text-muted">(alias)</span></div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-battery-3"></i> fa-battery-3<span class="text-muted">(alias)</span></div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-4" /><i class="fa fa-fw fa-battery-4"></i> fa-battery-4<span class="text-muted">(alias)</span></div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-empty" /><i class="fa fa-fw fa-battery-empty"></i> fa-battery-empty</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-full" /><i class="fa fa-fw fa-battery-full"></i> fa-battery-full</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-half" /><i class="fa fa-fw fa-battery-half"></i> fa-battery-half</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-quarter" /><i class="fa fa-fw fa-battery-quarter"></i> fa-battery-quarter</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-three-quarters" /><i class="fa fa-fw fa-battery-three-quarters"></i>fa-battery-three-quarters</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-black-tie" /><i class="fa fa-fw fa-black-tie"></i> fa-black-tie</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-calendar-check-o" /><i class="fa fa-fw fa-calendar-check-o"></i> fa-calendar-check-o</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-calendar-minus-o" /><i class="fa fa-fw fa-calendar-minus-o"></i> fa-calendar-minus-o</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-calendar-plus-o" /><i class="fa fa-fw fa-calendar-plus-o"></i> fa-calendar-plus-o</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-calendar-times-o"></i> fa-calendar-times-o</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-cc-diners-club"></i> fa-cc-diners-club</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-cc-jcb"></i> fa-cc-jcb</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-chrome"></i> fa-chrome</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-clone"></i> fa-clone</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-commenting"></i> fa-commenting</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-commenting-o"></i> fa-commenting-o</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-contao"></i> fa-contao</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-creative-commons"></i> fa-creative-commons</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-expeditedssl"></i> fa-expeditedssl</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-firefox"></i> fa-firefox</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-fonticons"></i> fa-fonticons</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-genderless"></i> fa-genderless</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-get-pocket"></i> fa-get-pocket</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-gg"></i> fa-gg</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-gg-circle"></i> fa-gg-circle</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hand-grab-o"></i> fa-hand-grab-o<span class="text-muted">(alias)</span></div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hand-lizard-o"></i> fa-hand-lizard-o</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hand-paper-o"></i> fa-hand-paper-o</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hand-peace-o"></i> fa-hand-peace-o</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hand-pointer-o"></i> fa-hand-pointer-o</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hand-rock-o"></i> fa-hand-rock-o</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hand-scissors-o"></i> fa-hand-scissors-o</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hand-spock-o"></i> fa-hand-spock-o</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hand-stop-o"></i> fa-hand-stop-o<span class="text-muted">(alias)</span></div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hourglass"></i> fa-hourglass</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hourglass-1"></i> fa-hourglass-1<span class="text-muted">(alias)</span></div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hourglass-2"></i> fa-hourglass-2<span class="text-muted">(alias)</span></div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hourglass-3"></i> fa-hourglass-3<span class="text-muted">(alias)</span></div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hourglass-end"></i> fa-hourglass-end</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hourglass-half"></i> fa-hourglass-half</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hourglass-o"></i> fa-hourglass-o</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-hourglass-start"></i> fa-hourglass-start</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-houzz"></i> fa-houzz</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-i-cursor"></i> fa-i-cursor</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-industry"></i> fa-industry</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-internet-explorer"></i> fa-internet-explorer</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-map"></i> fa-map</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-map-o"></i> fa-map-o</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-map-pin"></i> fa-map-pin</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-map-signs"></i> fa-map-signs</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-mouse-pointer"></i> fa-mouse-pointer</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-object-group"></i> fa-object-group</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-object-ungroup"></i> fa-object-ungroup</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-odnoklassniki"></i> fa-odnoklassniki</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-odnoklassniki-square"></i>fa-odnoklassniki-square</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-opencart"></i> fa-opencart</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-opera"></i> fa-opera</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-optin-monster"></i> fa-optin-monster</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-registered"></i> fa-registered</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-safari"></i> fa-safari</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-sticky-note"></i> fa-sticky-note</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-sticky-note-o"></i> fa-sticky-note-o</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-television"></i> fa-television</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-trademark"></i> fa-trademark</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-tripadvisor"></i> fa-tripadvisor</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-tv"></i> fa-tv<span class="text-muted">(alias)</span></div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-vimeo"></i> fa-vimeo</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-wikipedia-w"></i> fa-wikipedia-w</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-y-combinator"></i> fa-y-combinator</div>
                    <div class="col-md-3 col-sm-4"><input type="radio" name="iconValue" value="fa-battery-3" /><i class="fa fa-fw fa-yc"></i> fa-yc<span class="text-muted">(alias)</span></div>
                  </div>
                </section>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</@base>
