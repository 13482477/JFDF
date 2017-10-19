<#assign ctx=request.contextPath />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AdminLTE 2 | Dashboard</title>
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
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="${ctx}/lib/admin-lte/dist/css/skins/_all-skins.min.css">
<!-- Morris chart -->
<link rel="stylesheet" href="${ctx}/lib/morris.js/morris.css">
<!-- jvectormap -->
<link rel="stylesheet" href="${ctx}/lib/jvectormap/jquery-jvectormap.css">
<!-- Date Picker -->
<link rel="stylesheet" href="${ctx}/lib/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
<!-- Daterange picker -->
<link rel="stylesheet" href="${ctx}/lib/bootstrap-daterangepicker/daterangepicker.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet" href="${ctx}/lib//bootstrap-wysihtml5/dist/bootstrap3-wysihtml5.min.css">

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
	<div class="wrapper">
		<header class="main-header">
			<a href="${ctx}/index" class="logo"> <!-- LOGO --> <span class="logo-mini"> JFDF </span> <span class="logo-lg"> JavaFastDF </span>
			</a>
			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top" role="navigation">
				<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button"> 
					<span class="sr-only">Toggle navigation</span>
				</a>
				<form class="navbar-form navbar-left" role="search">
	              <div class="form-group">
	                <input type="text" class="form-control" id="navbar-search-input" placeholder="Search">
	              </div>
	            </form>
				<!-- Navbar Right Menu -->
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- Messages: style can be found in dropdown.less-->
						<li class="dropdown messages-menu">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
								<i class="fa fa-envelope-o"></i> 
								<span class="label label-success">4</span>
							</a>
							<ul class="dropdown-menu">
								<li class="header">You have 4 messages</li>
								<li>
									<!-- inner menu: contains the actual data -->
									<ul class="menu">
										<li>
											<!-- start message --> <a href="#">
												<div class="pull-left">
													<img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
												</div>
												<h4>
													Sender Name <small><i class="fa fa-clock-o"></i> 5 mins</small>
												</h4>
												<p>Message Excerpt</p>
										</a>
										</li>
										<!-- end message -->
										...
									</ul>
								</li>
								<li class="footer"><a href="#">See All Messages</a></li>
							</ul>
						</li>
						<!-- Notifications: style can be found in dropdown.less -->
						<li class="dropdown notifications-menu">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
								<i class="fa fa-bell-o"></i> 
								<span class="label label-warning">10</span>
							</a>
							<ul class="dropdown-menu">
								<li class="header">You have 10 notifications</li>
								<li>
									<!-- inner menu: contains the actual data -->
									<ul class="menu">
										<li>
											<a href="#"> 
												<i class="ion ion-ios-people info"></i> 
												Notification title
											</a>
										</li> 
										...
									</ul>
								</li>
								<li class="footer">
									<a href="#">View all</a>
								</li>
							</ul>
						</li>
						<!-- Tasks: style can be found in dropdown.less -->
						<li class="dropdown tasks-menu">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<i class="fa fa-flag-o"></i> 
								<span class="label label-danger">9</span>
							</a>
							<ul class="dropdown-menu">
								<li class="header">You have 9 tasks</li>
								<li>
									<!-- inner menu: contains the actual data -->
									<ul class="menu">
										<li>
											<!-- Task item --> 
											<a href="#">
												<h3>
													Design some buttons <small class="pull-right">20%</small>
												</h3>
												<div class="progress xs">
													<div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
														<span class="sr-only">20% Complete</span>
													</div>
												</div>
											</a>
										</li>
										<!-- end task item -->
										...
									</ul>
								</li>
								<li class="footer">
									<a href="#">View all tasks</a>
								</li>
							</ul>
						</li>
						<!-- User Account: style can be found in dropdown.less -->
						<li class="dropdown user user-menu">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
								<span class="glyphicon glyphicon-user"></span>
								<span class="hidden-xs">Alexander Pierce</span>
							</a>
							<ul class="dropdown-menu">
								<!-- User image -->
								<li class="user-header">
									<img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
									<p>
										Alexander Pierce - Web Developer <small>Member since Nov. 2012</small>
									</p>
								</li>
								<!-- Menu Body -->
								<li class="user-body">
									<div class="col-xs-4 text-center">
										<a href="#">Followers</a>
									</div>
									<div class="col-xs-4 text-center">
										<a href="#">Sales</a>
									</div>
									<div class="col-xs-4 text-center">
										<a href="#">Friends</a>
									</div>
								</li>
								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a href="#" class="btn btn-default btn-flat">Profile</a>
									</div>
									<div class="pull-right">
										<a href="#" class="btn btn-default btn-flat">Sign out</a>
									</div>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</nav>
		</header>
		<aside class="main-sidebar control-sidebar-dark">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="sidebar-menu" data-widget="tree">
					<li class="header">导航栏</li>
					<li class="treeview menu-open active">
						<a href="#"> 
							<i class="fa fa-dashboard"></i> 
							<span>系统看板</span> 
							<span class="pull-right-container"> 
								<i class="fa fa-angle-left pull-right"></i>
							</span>
						</a>
						<ul class="treeview-menu">
							<li>
								<a href="${ctx}/index">
									<i class="fa fa-circle-o"></i> 我的面板 v1
								</a>
							</li>
							<li>
								<a href="${ctx}/index">
									<i class="fa fa-circle-o"></i> 我的面板 v2
								</a>
							</li>
						</ul>
					</li>
					<li class="treeview">
						<a href="#">
							<i class="fa fa-files-o"></i> 
							<span>Layout Options</span> 
							<span class="pull-right-container"> 
								<span class="label label-primary pull-right">4</span>
							</span>
						</a>
						<ul class="treeview-menu">
							<li><a href="pages/layout/top-nav.html"><i class="fa fa-circle-o"></i> Top Navigation</a></li>
							<li><a href="pages/layout/boxed.html"><i class="fa fa-circle-o"></i> Boxed</a></li>
							<li><a href="pages/layout/fixed.html"><i class="fa fa-circle-o"></i> Fixed</a></li>
							<li><a href="pages/layout/collapsed-sidebar.html"><i class="fa fa-circle-o"></i> Collapsed Sidebar</a></li>
						</ul>
					</li>
					<li>
						<a href="pages/widgets.html"> 
							<i class="fa fa-th"></i> 
							<span>Widgets</span> 
							<span class="pull-right-container"> 
								<small class="label pull-right bg-green">new</small>
							</span>
						</a>
					</li>
					<li class="treeview">
						<a href="#"> 
							<i class="fa fa-pie-chart"></i> 
							<span>Charts</span> 
							<span class="pull-right-container"> 
								<i class="fa fa-angle-left pull-right"></i>
							</span>
						</a>
						<ul class="treeview-menu">
							<li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> ChartJS</a></li>
							<li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> Morris</a></li>
							<li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> Flot</a></li>
							<li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> Inline charts</a></li>
						</ul>
					</li>
					<li class="treeview">
						<a href="#"> 
							<i class="fa fa-laptop"></i> 
							<span>UI Elements</span> 
							<span class="pull-right-container"> 
								<i class="fa fa-angle-left pull-right"></i>
							</span>
						</a>
						<ul class="treeview-menu">
							<li>
								<a href="pages/UI/general.html">
								<i class="fa fa-circle-o"></i> General</a>
							</li>
							<li>
								<a href="pages/UI/icons.html">
									<i class="fa fa-circle-o"></i> Icons</a>
							</li>
							<li><a href="pages/UI/buttons.html"><i class="fa fa-circle-o"></i> Buttons</a></li>
							<li><a href="pages/UI/sliders.html"><i class="fa fa-circle-o"></i> Sliders</a></li>
							<li><a href="pages/UI/timeline.html"><i class="fa fa-circle-o"></i> Timeline</a></li>
							<li><a href="pages/UI/modals.html"><i class="fa fa-circle-o"></i> Modals</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-edit"></i> <span>Forms</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a href="pages/forms/general.html"><i class="fa fa-circle-o"></i> General Elements</a></li>
							<li><a href="pages/forms/advanced.html"><i class="fa fa-circle-o"></i> Advanced Elements</a></li>
							<li><a href="pages/forms/editors.html"><i class="fa fa-circle-o"></i> Editors</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-table"></i> <span>Tables</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a href="pages/tables/simple.html"><i class="fa fa-circle-o"></i> Simple tables</a></li>
							<li><a href="pages/tables/data.html"><i class="fa fa-circle-o"></i> Data tables</a></li>
						</ul></li>
					<li><a href="pages/calendar.html"> <i class="fa fa-calendar"></i> <span>Calendar</span> <span class="pull-right-container"> <small class="label pull-right bg-red">3</small> <small
								class="label pull-right bg-blue">17</small>
						</span>
					</a></li>
					<li><a href="pages/mailbox/mailbox.html"> <i class="fa fa-envelope"></i> <span>Mailbox</span> <span class="pull-right-container"> <small class="label pull-right bg-yellow">12</small>
								<small class="label pull-right bg-green">16</small> <small class="label pull-right bg-red">5</small>
						</span>
					</a></li>
					<li class="treeview"><a href="#"> <i class="fa fa-folder"></i> <span>Examples</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a href="pages/examples/invoice.html"><i class="fa fa-circle-o"></i> Invoice</a></li>
							<li><a href="pages/examples/profile.html"><i class="fa fa-circle-o"></i> Profile</a></li>
							<li><a href="pages/examples/login.html"><i class="fa fa-circle-o"></i> Login</a></li>
							<li><a href="pages/examples/register.html"><i class="fa fa-circle-o"></i> Register</a></li>
							<li><a href="pages/examples/lockscreen.html"><i class="fa fa-circle-o"></i> Lockscreen</a></li>
							<li><a href="pages/examples/404.html"><i class="fa fa-circle-o"></i> 404 Error</a></li>
							<li><a href="pages/examples/500.html"><i class="fa fa-circle-o"></i> 500 Error</a></li>
							<li><a href="pages/examples/blank.html"><i class="fa fa-circle-o"></i> Blank Page</a></li>
							<li><a href="pages/examples/pace.html"><i class="fa fa-circle-o"></i> Pace Page</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-share"></i> <span>Multilevel</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
							<li class="treeview"><a href="#"><i class="fa fa-circle-o"></i> Level One <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i>
								</span> </a>
								<ul class="treeview-menu">
									<li><a href="#"><i class="fa fa-circle-o"></i> Level Two</a></li>
									<li class="treeview"><a href="#"><i class="fa fa-circle-o"></i> Level Two <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i>
										</span> </a>
										<ul class="treeview-menu">
											<li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
											<li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
										</ul></li>
								</ul></li>
							<li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
						</ul></li>
					<li><a href="https://adminlte.io/docs"><i class="fa fa-book"></i> <span>Documentation</span></a></li>
					<li class="header">LABELS</li>
					<li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
					<li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
					<li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>
				</ul>
			</section>
		</aside>
		<section class="content-wrapper"></section>
	</div>





	<!-- jQuery 3 -->
	<script src="${ctx}/lib/jquery/dist/jquery.min.js"></script>
	<!-- jQuery UI 1.11.4 -->
	<script src="${ctx}/lib/jquery-ui/jquery-ui.min.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
  		$.widget.bridge('uibutton', $.ui.button);
	</script>
	<!-- Bootstrap 3.3.7 -->
	<script src="${ctx}/lib/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Morris.js charts -->
	<script src="${ctx}/lib/raphael/raphael.min.js"></script>
	<script src="${ctx}/lib/morris.js/morris.min.js"></script>
	<!-- Sparkline -->
	<script src="${ctx}/lib/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
	<!-- jvectormap -->
	<script src="${ctx}/lib/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="${ctx}/lib/jvectormap/tests/assets/jquery-jvectormap-world-mill-en.js"></script>
	<!-- jQuery Knob Chart -->
	<script src="${ctx}/lib/jquery-knob/dist/jquery.knob.min.js"></script>
	<!-- daterangepicker -->
	<script src="${ctx}/lib/moment/min/moment.min.js"></script>
	<script src="${ctx}/lib/bootstrap-daterangepicker/daterangepicker.js"></script>
	<!-- datepicker -->
	<script src="${ctx}/lib/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
	<!-- Bootstrap WYSIHTML5 -->
	<script src="${ctx}/lib/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<!-- Slimscroll -->
	<script src="${ctx}/lib/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="${ctx}/lib/fastclick/lib/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="${ctx}/lib/admin-lte/dist/js/adminlte.min.js"></script>

	<script type="text/javascript">
	  //变量AdminLTEOptions用来修改app.js中的配置，所有需要在app.js之前编写
	  // （其实放在app.js之后也可以，看你代码结构是什么了，正常需要放在app.js之前）
	  var AdminLTEOptions = {
	    /*1、内菜单配置，就是消息、提醒、任务那三个的内部菜单那种*/
	    //添加slimscroll到导航栏菜单，（其实在app.js之后加载该插件也可以运行的）
	    // 这就需要你在每一个页面的app.js之前加载jquery.slimscroll.min.js插件，
	    navbarMenuSlimscroll: true,//是否为内菜单使用slimscroll插件
	    navbarMenuSlimscrollWidth: "10px", //内菜单的滚动条宽度
	    navbarMenuHeight: "200px",//内菜单高度
	    /*2、*/
	    //指定控件的折叠速度，例如box collapse/expand展开折叠 and sidebar treeview slide up/down上下滑动.。
	    //此选项接受整数为毫秒，‘fast‘, ‘normal‘, or ‘slow‘
	    animationSpeed: ‘fast‘,
	    /*3、指定侧边栏伸缩的控制按钮*/
	    sidebarToggleSelector: "[data-toggle=‘offcanvas‘]",
	    sidebarPushMenu: true,//侧边栏伸缩使能
	    sidebarSlimScroll: true,//fixed固定布局下侧边栏滚动条使能
	    sidebarExpandOnHover: true,//当侧边栏隐藏时，鼠标悬停侧边栏展开。当fixed和sidebar-mini一起使用时，不管true或false都会展开（即该配置无效）

	    /*box*/
	    enableBoxRefresh: false,
	    /*Bootstrap.js的提示信息*/
	    enableBSToppltip: true,//使能
	    BSTooltipSelector: "[data-toggle=‘tooltip‘]",//给元素指定tooltip事件，元素中的事件必须和该事件相同
	    /*给触摸设备开启快速点击体验，需要引用jquery.slimscroll.min.js*/
	    enableFastclick: false,
	    enableControlTreeView: true,//树视图控制，即侧边栏的点击动画效果
	    enableControlSidebar: true,//右侧边栏的控制选项
	    /*右侧边栏配置*/
	    controlSidebarOptions: {
	      //哪个按钮应该触发开/关事件
	      toggleBtnSelector: "[data-toggle=‘control-sidebar‘]",
	      //侧边栏选择器
	      selector: ".control-sidebar",
	      //运行划过内容
	      slide: false//true表示在内容上层覆盖，FALSE表示推挤
	    },
	    enableBoxWidget: true,//允许box折叠和删除
	    //框插件插件选项
	    boxWidgetOptions: {
	      boxWidgetIcons: {
	        //Collapse icon
	        collapse: ‘fa-minus‘,
	        //Open icon
	        open: ‘fa-plus‘,
	        //Remove icon
	        remove: ‘fa-times‘
	      },
	      boxWidgetSelectors: {
	        //Remove button selector
	        remove: ‘[data-widget="remove"]‘,
	        //折叠按钮的选择
	        collapse: ‘[data-widget="collapse"]‘
	      }
	    },
	    //Direct Chat plugin options
	    directChat: {
	      //默认启用直接聊天
	      enable: true,
	      //打开和关闭聊天联系人窗格的按钮
	      contactToggleSelector: ‘[data-widget="chat-pane-toggle"]‘
	    },
	    //在这里可以自己额外添加新的颜色
	    colors: {
	      lightBlue: "#3c8dbc",
	      red: "#f56954",
	      green: "#00a65a",
	      aqua: "#00c0ef",
	      yellow: "#f39c12",
	      blue: "#0073b7",
	      navy: "#001F3F",
	      teal: "#39CCCC",
	      olive: "#3D9970",
	      lime: "#01FF70",
	      orange: "#FF851B",
	      fuchsia: "#F012BE",
	      purple: "#8E24AA",
	      maroon: "#D81B60",
	      black: "#222222",
	      gray: "#d2d6de"
	    },
	    //这里是修改响应式的分界点
	    screenSizes: {
	      xs: 480,
	      sm: 768,
	      md: 992,
	      lg: 1200
	    }
	  };

	</script>
	<!-- AdminLTE App -->
	<script src="${ctx}/lib/admin-lte/dist/js/pages/dashboard.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="${ctx}/lib/admin-lte/dist/js/demo.js"></script>
</body>
</html>
