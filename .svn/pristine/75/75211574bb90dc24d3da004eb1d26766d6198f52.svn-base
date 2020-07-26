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
    <title>岗位管理</title>
    <link rel="stylesheet" href="css/common.css"/>
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.datanew.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#datatable").dtable({
                columns: [[{
                    checkbox: true
                }, {
                    field: 'POSTID',
                    title: '岗位ID',
                    width: 100,
                    align: 'center',
                    visible: false
                }, {
                    field: 'POSTNAME',
                    title: '岗位名称',
                    width: 100,
                    align: 'center'
                }, {
                    field: 'REMARK',
                    title: '备注',
                    width: 200,
                    align: 'center',
                }]],
                url: 'post/getPostInformation.do',//请求地址
                //              data:[],//前端加载数据
                pageNumber: 1,//起始页
                pageSize: 20,//页面大小
                showRefresh:false,
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
            var url = "post/savePost.do";
            $("#fm").dform("submit", {
                url : url,
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
            $('#fm').dform('clear');
            $("#itemModel").modal('show');
        }

        function edit() {
            var dataArr = $("#datatable").bootstrapTable("getSelections");
            if (dataArr.length == 0) {
                $.dalert({text: "请选择要修改的岗位", icon: 7});
            } else {
                $("#itemModel").modal('show');
                $.dloadformdata($("#fm"), dataArr[0], "dname");
            }
        }

        function del() {
            var dataArr = $("#datatable").bootstrapTable("getSelections");
            if (dataArr.length == 0) {
                $.dalert({text: "未选择要删除的岗位", icon: 7});
            } else {
                $.dconfirm({
                    text: "确认继续？", btn: ["确定", "取消"], funs: [function () {
                        var postid = dataArr[0].POSTID;
                        $.dajax({
                            url: "post/deletePost.do",
                            data: { id: postid },
                            success: function (data) {
                                if (data.success) {
                                    $.dalert({text: data.content});
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
     aria-labelledby="myModalLabel" style="display: none;">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="">岗位信息</h4>
            </div>

            <div class="modal-body">
                <div class="tab-content">
                    <!-- 用户信息 tab -->
                    <div class="tab-pane fade in active" id="itemRecords">
                        <form class="form-horizontal" role="form" method="post" id="fm">
                            <div class="form-group">
                                <input type="reset" name="reset" style="display: none;"/>
                                <%--<input class="from-control" id="postid" name="postid" dname="POSTID"--%>
                                       <%--type="text"/>--%>
                                <input type="text" class="form-control" id="postid" name="postid" dname="POSTID" style="display: none;"/>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label for="postname" class="col-sm-3 control-label">岗位名称：</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="postname"
                                               name="postname" dname="POSTNAME"/>
                                    </div>
                                </div>

                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label for="remark" class="col-sm-3 control-label">备注：</label>
                                    <div class="col-sm-8">
                                <textarea class="form-control" id="remark" name="remark"
                                          dname="REMARK" rows="4"></textarea>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="well well-sm" align="right">
                            <button class="btn btn-info btn-sm" id="saveItemRecords"
                                    onclick="save()">保存
                            </button>
                            <button class="btn btn-default btn-sm" id="saveItemRecords"
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
