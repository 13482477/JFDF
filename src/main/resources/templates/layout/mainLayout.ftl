<#assign ctx=request.contextPath />
<#compress>
<#macro base baseTitle baseKeywords="" baseJs=[] baseCss=[]>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${baseTitle}</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

<#list baseCss as css>
<link rel="stylesheet" href="${ctx}${css}">
</#list>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="${ctx}/lib/html5shiv/dist/html5shiv.min.js"></script>
<script src="${ctx}/lib/respond/dest/respond.min.js"></script>
<![endif]-->
<!-- Google Font -->
<link rel="stylesheet" href="${ctx}/css/font.css">
</head>
<body class="hold-transition skin-blue-light sidebar-mini">
	<input type="hidden" id="__ctx" name="__ctx" value="${ctx}" />
	<div class="wrapper">
		<#include "/layout/header.ftl" />

		<#include "/layout/menu.ftl" />
		
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<section class="content-header">
		    	<h1>${baseTitle}</h1>
		    </section>
			
			<section class="content">
				<#nested />
			</section>
		</div>
		<!-- /.content-wrapper -->
		
		<#include "/layout/foot.ftl" />

		<#include "/layout/controlBar.ftl" />
		
	</div>

	<#list baseJs as js >
	<script src="${ctx}${js}"></script>
	<#if js=="/lib/jquery-ui/jquery-ui.min.js">
	<script>
  		$.widget.bridge('uibutton', $.ui.button);
	</script>
	</#if>
	</#list>
</body>
</html>
</#macro>
</#compress>
