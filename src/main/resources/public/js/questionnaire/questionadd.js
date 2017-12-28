var Controller={
    //初始化

    init:function(){
        var _this = this;
        _this.bindEvents();
        _this.getList();
    },
    bindEvents:function(){
        var _this=this;
        //单选题添加编辑
        $("#RadioTm").click(function () {
            _this.TmDxAddEdit();

        });
        //多选题添加编辑
        $("#CheckBoxTm").click(function () {
            _this.TmDuoxAddEdit($(this).val());

        });
        //问答题添加编辑
        $("#AnswerTm").click(function () {
            _this.TmHdAddEdit($(this).val());

        });
    },
    TmDuoxAddEdit:function (n) {
        var index=n;
        doT.exec("question/topicduoxadd.tpl.html",function (templateFun) {
            var innerText=templateFun();
            easyDialog.open({
                container: {
                    id: "selectContact",
                    header: '題目编辑',
                    content: innerText,
                    callback: function () {
                    }
                }
            });
            //增加选项
            $(".zjxx").click(function() {
                var zjxx_html = $(this).prev(".title_itram").children(".kzjxx_iteam").html();
                $(this).prev(".title_itram").append("<div class='kzjxx_iteam'>" + zjxx_html + "</div>");
            });

            //删除一行
            $(document).on("click", ".del_xm",function(){
                //获取编辑题目的个数
                var zuxxs_num = $(this).parent(".kzjxx_iteam").parent(".title_itram").children(".kzjxx_iteam").length;
                if(zuxxs_num > 1) {
                    $(this).parent(".kzjxx_iteam").remove();
                } else {
                    alert("不可删除！");
                }
            });

        });

    },
    TmHdAddEdit:function (n) {
        var index=n;
        doT.exec("question/topichdadd.tpl.html",function (templateFun) {
            var innerText=templateFun();
            easyDialog.open({
                container: {
                    id: "selectContact",
                    header: '題目编辑',
                    content: innerText,
                    callback: function () {
                    }
                }
            });

        });

    },
    TmDxAddEdit:function (n) {
        var index=n;
        /*doT.exec("question/topicdxadd.tpl.html",function (templateFun) {
            var innerText=templateFun();*/
            easyDialog.open({
                container: {
                    id: "selectContact",
                    header: '題目编辑',
                    content: '<div class="xxk_box">\n' +
                    '    <div class="xxk_conn ">\n' +
                    '       <div class="xxk_xzqh_box dxuan ">\n' +
                    '            <input type="text" class="input_gtxh" placeholder="该题序号">\n' +
                    '            <textarea name="" cols="" rows="" class="input_wenbk btwen_text btwen_text_dx" placeholder="单选题目"></textarea>\n' +
                    '            <div class="title_itram">\n' +
                    '                <div class="kzjxx_iteam">\n' +
                    '                    <input name="" type="radio" value="" class="dxk">\n' +
                    '                    <input name="" type="text" class="input_wenbk" value="" placeholder="选项">\n' +
                    '                    <label>\n' +
                    '                        <input name="" type="checkbox" value="" class="fxk"> <span>可填空</span>\n' +
                    '                    </label> <a href="javascript:void(0);" class="del_xm">删除</a>\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '            <a href="javascript:void(0)" class="zjxx">增加选项</a>\n' +
                    '            <!--完成编辑-->\n' +
                    '            <div class="bjqxwc_box">\n' +
                    '                <a href="javascript:void(0);" class="swcbj_but"> 完成编辑</a>\n' +
                    '            </div>\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '</div>',
                    callback: function () {
                    }
                }
            });
                //增加选项
                $(".zjxx").click(function() {
                    var zjxx_html = $(this).prev(".title_itram").children(".kzjxx_iteam").html();
                    $(this).prev(".title_itram").append("<div class='kzjxx_iteam'>" + zjxx_html + "</div>");
                });

                //删除一行
                $(document).on("click", ".del_xm",function(){
                    //获取编辑题目的个数
                    var zuxxs_num = $(this).parent(".kzjxx_iteam").parent(".title_itram").children(".kzjxx_iteam").length;
                    if(zuxxs_num > 1) {
                        $(this).parent(".kzjxx_iteam").remove();
                    } else {
                        alert("不可删除！");
                    }
                });

/*
        });
*/

    },
    getList:function(){
        var _self=this;
      /*  doT.exec("question/questionadd.tpl.html",function (templateFun) {
            var innerText=templateFun();
            $(".con").empty();
            $(".con").html(innerText);
        });*/

    },
};



$(function(){
    Controller.init();
})


