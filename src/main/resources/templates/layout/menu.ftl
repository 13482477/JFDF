<#macro menuTree children>
	<#if children?? && children?size gt 0>
	<#list children as child>
		<#if child.children?? && child.children?size gt 0>
			<#assign hasSubMenu=true/> 
		<#else>
			<#assign hasSubMenu=false/> 
		</#if>
		<li <#if hasSubMenu>class="treeview"</#if>>
			<a href="<#if child.url??>${ctx}${child.url}<#else>#</#if>">
				<#if child.iconType?? && child.iconType == "ICON">
				<i class="fa ${child.iconPath}"></i>
				<#elseif child.iconType?? && child.iconType == "IMG">
				<i class="fa"><img src="${ctx}${child.iconPath}" width="14px" hidden="14px" /></i>
				</#if>
				<span>${child.menuName}</span>
				
				<#if hasSubMenu>
				<span class="pull-right-container"> 
					<i class="fa fa-angle-left pull-right"></i>
				</span>
				</#if>
			</a>
			<#if hasSubMenu>
				<ul class="treeview-menu">
					<@menuTree children=child.children/>
				</ul>
			</#if>
		</li>
	</#list>
	</#if>
</#macro>

		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- /.search form -->
				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="sidebar-menu" data-widget="tree">
					<@menuTree children=__SYSTEM_MENU.children />
				</ul>
			</section>
			<!-- /.sidebar -->
		</aside>