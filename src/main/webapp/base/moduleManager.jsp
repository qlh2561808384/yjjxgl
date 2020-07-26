<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <title>短信模板管理</title>
    <link rel="stylesheet" href="css/common.css"/>
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.datanew.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#datatable").dtable({
                columns: [[{
                    checkbox: true
                }, {
                    field: 'GUID',
                    title: '模板ID',
                    width: 50,
                    align: 'center',
                    visible: false
                }, {
                    field: 'CODE',
                    title: '模板编码',
                    width: 50,
                    align: 'center'
                }, {
                    field: 'NAME',
                    title: '模板名称',
                    width: 50,
                    align: 'center'
                }, {
                    field: 'CONTENT',
                    title: '模板内容',
                    width: 200,
                    align: 'center',
                }, {
                    field: 'WRITETIME',
                    title: '创建时间',
                    width: 50,
                    align: 'center',
                }]],
                url: 'common/getModule.do',
                pageNumber: 1,//起始页
                showRefresh:false,
                pageSize: 20,//页面大小
                uniqueId: 'guid',
                queryParams: queryParams,//查询参数方法
                paginationHAlign: 'left',//分页按钮位置  left right
                sidePagination: 'server',//分页方式(真分页)
                toolbar: '#bt',//工具栏 对应dom元素
                singleSelect: true,//单选
                clickToSelect: true//点击选中
              
            })
            $("body").css({
                width: $(window).width(),
                overflowX: "hidden"
            });

        });
        $(document).ready(function() {
            $(document).keydown(function() {
                if (event.keyCode == 13) {
                    event.keyCode = '9';
                }
            })
        });
        function queryParams(params) {
            return {
                //如果需要后端进行分页 limit 和offset是必须参数
                limit: params.limit,
                offset: params.offset,
            }
        }

        function save() {
        	/*$("#fm").dform("submit", {
                url : "common/saveModule.do",
                success : function(data) {
                    if (data.success) {
                        $.dalert({text : data.msg, icon : 1});
                        $('#itemModel').modal('hide');
                        $("#datatable").bootstrapTable("refresh");
                    } else {
                        $.dalert({text : data.msg, icon : 2});
                        $('#itemModel').modal('hide');
                    }
                }
            });*/
            $.dajax({
                type : "POST",
                url : "common/saveModule.do",
                data : {
                    "guId" : $("#guId").val(),
                    "writeTime" : $('#writeTime').val(),
                    "code" : $('#code').val(),
                    "name" : $('#name').val(),
                    "content" : $('#content').val()
                },
                async : true,
                dataType : 'json',
                success : function(data) {
                    if (data.success) {
                        $.dalert({text : data.content, icon : 1});
                        $('#itemModel').modal('hide');
                        $("#datatable").bootstrapTable("refresh");
                    } else {
                        $.dalert({text : data.content, icon : 2});
                        $('#itemModel').modal('hide');
                    }

                }
            });

        }

        function add() {
        	$('#writeTime').removeAttr('readonly');
            $('#fm').dform('clear');
            $("#itemModel").modal('show');
        }

        function edit() {
        	$('#writeTime').attr('readonly','readonly');
            var dataArr = $("#datatable").bootstrapTable("getSelections");
            if (dataArr.length == 0) {
                $.dalert({text: "请选择要修改的短信模板", icon: 7});
            } else {
                $("#itemModel").modal('show');
                $.dloadformdata($("#fm"), dataArr[0], "dname");
            }
        }

        function del() {
            var dataArr = $("#datatable").bootstrapTable("getSelections");
            if (dataArr.length == 0) {
                $.dalert({text: "未选择要删除的短信模板", icon: 7});
            } else {
                $.dconfirm({
                    text: "确认继续？", btn: ["确定", "取消"], funs: [function () {
                        var guid = dataArr[0].GUID;
                        $.dajax({
                            url: "common/deleteModule.do",
                            data: { guid: guid },
                            success: function (data) {
                                if (data.success) {
                                	 $.dalert({text : data.content, icon : 1});
                                    $("#datatable").bootstrapTable("refresh");
                                }
                            }
                        });
                    }, function () {
                        $.dalert({text: "取消操作", icon : 7});
                    }]
                });
            }
        }

        function cancel() {
            $('#itemModel').modal('hide');
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <table id="datatable"></table>
    <div id="bt">
        <button class="bootstrap-table-add" type="button" onclick="add()">新增</button>
        <button class="bootstrap-table-edit" type="button" onclick="edit()">修改</button>
        <button class="bootstrap-table-delete" type="button" onclick="del()">删除</button>
    </div>
</div>
<div class="modal fade" id="itemModel" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" style="display: none;" data-backdrop="static">
    <div class="modal-dialog modal-lg" role="document" style=" width:800px;height: 500px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="">短信模板信息</h4>
            </div>

            <div class="modal-body" >
                <div class="tab-content">
                    <!-- 模板信息 tab -->
                    <div class="tab-pane fade in active" id="itemRecords" >
                        <form class="form-horizontal" role="form" method="post" id="fm" >
                            <div class="form-group">
                                <input type="text" class="form-control" id="guId" name="guId" dname="GUID" style="display: none;"/>
                                <input type="text" class="form-control" id="writeTime" name="writeTime" dname="WRITETIME" style="display: none;"/>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label for="code" class="col-sm-3 control-label">模板编码：</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="code"
                                               name="code" dname="CODE"/>
                                    </div>
                                </div>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label for="name" class="col-sm-3 control-label">模板名称：</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="name"
                                               name="name" dname="NAME"/>
                                    </div>
                                </div>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label for="content" class="col-sm-3 control-label">模板内容：</label>
                                    <div class="col-sm-8">
                                <textarea class="form-control" id="content" name="content"
                                          dname="CONTENT" rows="8"></textarea>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="well well-sm" align="right">
                            <button class="btn btn-info btn-sm" id="saveItemRecords"
                                    onclick="save()">保存
                            </button>
                            <button class="btn btn-default btn-sm" id="cancelItemRecords"
                                    onclick="cancel()">取消
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
