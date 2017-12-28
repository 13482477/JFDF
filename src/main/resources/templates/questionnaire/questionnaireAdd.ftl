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
"/css/basic.css",
"/lib/layui/css/layui.css",
"/lib/easydialog/easydialoge.css",
"/css/adapter.css",
"/css/question/questionadd.css"

]
baseJs=[
"/lib/jquery/jquery-1.11.1.js",
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
"/lib/doT.js",
"/lib/layui/layui.js",
"/lib/easydialog/easydialog.js",
<#--"/js/questionnaire/questionnairesss.js",-->
"/js/questionnaire/questionadd.js"

]
>


<div id="body" class="modal-body">
    <form id="dataForm1" role="form" data-toggle="validator">
        <#--<div class="layui-body">-->
            <#--<!-- 内容主体区域 &ndash;&gt;-->
            <div class="div-con">
                <div class="div-btn">
                    <button class="layui-btn layui-btn-normal" id="RadioTm">单选题</button>
                    <button class="layui-btn layui-btn-normal" id="CheckBoxTm">复选题</button>
                    <button class="layui-btn layui-btn-normal" id="AnswerTm">问答题</button>
                    <button class="layui-btn  Right">完成编辑</button>
                </div>
                <div class="con">
                    <div class="edit-div">
                        <span class="rowtitle">请输入问卷名称<span style="color:red">*</span></span>
                        <input id="title" name="title" type="text" name="" class="titlinput">
                    </div>
                    <div class="edit-div clearfix">
                        <span class="rowtitle">请填写问卷说明<span style="color:red">*</span></span>
                        <textarea id="remark" name="remark" rows="10" cols="30" class="txt-detail"></textarea>
                    </div>
                    <div class="tm-list">
                        <#list city.problemInfoList as citys>
                            <#if citys.type == "1">
                                <div class="tm-title">
                                    <span>${citys.tid}、${citys.qtitle}<span style="color:red"> (单选题）</span></span>
                                    <button class="layui-btn ">编辑</button>
                                    <button class="layui-btn">删除</button>
                                </div>
                                <#list citys.optionInfoList as cityss>
                                    <div class="tm-daan">
                                        <p><input type="radio" value="">${cityss.option_name}</p>
                                    </div>
                                </#list>
                            <#elseif citys.type=="2">
                                <div class="tm-title">
                                    <span> ${citys.qid}、${citys.qtitle}（最多可选三项）。<span style="color:red">（多选题）</span></span>
                                    <button class="layui-btn ">编辑</button>
                                    <button class="layui-btn">删除</button>
                                </div>

                                <div class="tm-daan">
                                    <#list citys.optionInfoList as cityss>

                                        <p><input type="checkbox" value="">${cityss.option_name}</p>
                                    </#list>

                                    <p><input type="checkbox" value=""> 其他 <input type="text" class="tm-qita"></p>
                                </div>
                            <#else>
                            <div class="tm-list">
                                <div class="tm-title">
                                    <span>${citys.qid}、${citys.qtitle}<span style="color:red">(问答题）※</span></span>
                                    <button class="layui-btn ">编辑</button>
                                    <button class="layui-btn">删除</button>
                                </div>
                                <#list citys.optionInfoList as cityss>
                                    <div class="tm-daan">

                                    </div>
                                </div>
                                </#list>

                            </#if>
                        </#list>
                    </div>
                </div>
            </div>
        <#--</div>-->



    </form>



</@base>
    <script>

    </script>
