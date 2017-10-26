<#assign ctx=request.contextPath />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AdminLTE 2 | Log in</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="${ctx}/lib/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="${ctx}/lib/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="${ctx}/lib/Ionicons/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${ctx}/lib/admin-lte/dist/css/AdminLTE.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="${ctx}/lib/iCheck/skins/square/blue.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="${ctx}/lib/html5shiv/dist/html5shiv.min.js"></script>
<script src="${ctx}/lib/respond/dest/respond.min.js"></script>
<![endif]-->

<!-- Google Font -->
<link rel="stylesheet" href="${ctx}/css/font.css">
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="${ctx}/index"><b></b>JAVA快速开发框架</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">登陆后开始你的系统体验！</p>

			<form action="${ctx}/login" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<div class="form-group has-feedback">
					<input name="username" type="username" class="form-control" placeholder="Username" value="${username?if_exists }"> <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input name="password" type="password" class="form-control" placeholder="Password"> <span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<#if SPRING_SECURITY_LAST_EXCEPTION??>
				<div class="form-group has-error" >
					<label class="control-label" for="inputError">
					${SPRING_SECURITY_LAST_EXCEPTION.message}
					</label>
				</div>
				</#if>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox"> 记住密码
							</label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
					</div>
					<!-- /.col -->
				</div>
			</form>

			<!-- 
			<div class="social-auth-links text-center">
				<p>- OR -</p>
				<a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using Facebook</a> <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i
					class="fa fa-google-plus"></i> Sign in using Google+</a>
			</div>
			 -->
			<!-- /.social-auth-links -->
			<a href="#">忘记密码</a><br> <a href="register.html" class="text-center">新用户注册</a>

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<!-- jQuery 3 -->
	<script src="${ctx}/lib/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script src="${ctx}/lib/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="${ctx}/lib/iCheck/icheck.min.js"></script>
	<script>
		$(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});
		});
	</script>
</body>
</html>
