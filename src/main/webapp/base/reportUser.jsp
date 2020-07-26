<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <title>填报人对应用户</title>
    <style>
        body, div {
            margin: 0;
            padding: 0;
        }

        .demo {
            display: inline-block;
            width: 48%;
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
        }

        .demo .demo-tit {
            height: 38px;
            line-height: 38px;
            font-size: 14px;
            /*border-radius: 4px 4px 0 0;*/
            border-right: 1px solid #4A68A5;
            background: #7892C7;
            color: #fff;
            margin: 0;
            padding: 0;
            text-indent: 10px;
        }

        .demo .demo-tree {
            background: #FBFBFB;
            border: 1px solid #D4DBE5;
            padding: 20px 30px;
        }

        .demo:nth-child(2) .demo-tree {
            background: #EDECFB;
            border: 1px solid #BBC6D6;
        }
        body div.container-fluid{
            border: 1px solid #DDDDDD;
            background: #F5F5F5;
            box-sizing: border-box;
            padding: 20px 15px 10px 25px;
            margin:0 10px 0 15px;
        }
        .demo .demo-tit {
            margin: 10px 10px 0 0;
            border-radius: 4px 4px 0 0;
        }
        .demo .demo-tree {margin-right:10px;}
    </style>
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.datanew.js"></script>
    <script type="text/javascript">

        var typeTree;
        var userTree;
        $(function () {
            $("body").css({width: $(window).width(), overflowX: "hidden"});
            var zNodes = [
                {id: 1, pId: 0, name: "节点搜索演示 1"},
                {id: 1, pId: 0, name: "节点搜索演示 2"}
            ]; 
            userTree = $("#userTree").dtree({
//              localdata: zNodes,
              checkType: "checkbox", //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
              isOpen: true, //节点是否全部展开，默认为不展开
              rootElement: false,  //是否添加根节点“全部”
              url: "report/findReportUserTree.do"
          });
           reload();
          
        });
        $(document).ready(function() {
            $(document).keydown(function() {
                if (event.keyCode == 13) {
                    event.keyCode = '9';
                }
            })
        });
        function reload(){
        	typeTree = $("#typeTree").dtree({
                 checkType: "radio", //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
                 isOpen: true, //节点是否全部展开，默认为不展开
                 ISLEAF: true,
                 rootElement: false,  //是否添加根节点“全部”
                 url: "common/findTypeTree.do",
                 callback: {
                     onCheck: getMenu
                 }
             }); 
        }
        var curtypeid;
        function getMenu(event, treeId, treeNode) {
        	if(treeNode.checked){
        		curtypeid = treeNode.id;
        	}else{
        		curtypeid=null;
        	}
        	$.dajax({
                data : {typeid : curtypeid},
                url : "common/getTypeUser.do",
                success : function(data) {
                    if (data.success) {
                    	userTree.checkAllNodes(false);
                        var userIds = data.content;
                        userIds = userIds.split(",");
                        for (var i = 0; i < userIds.length; i++) {
                            if (userIds[i] != null && userIds[i] != "") {
                                var checking = userTree.getNodeByParam('id', userIds[i], null);
                                if(checking!=null){
                                    userTree.checkNode(checking, true, false);
                                }

                            }
                        }
                    }
                }
            });
        }
        function queryParams(params) {
            return {
            	typeid:curtypeid,
                limit: params.limit,
                offset: params.offset
            }
        }
        //新增
        function add() {
            $('#fm').dform('clear');
            $("#itemModel").modal('show');
        }
        function save() {
        	var flag = $("#fm").dform("validate");
    		if (flag) {
	        	$.dajax({
					cache : true,
					type : "POST",
					url : "common/saveType.do",
					data : $.param({}) + '&' + $('#fm').serialize(),
					async : true,
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							 $.dalert({text : data.msg, icon : 1});
							$('#itemModel').modal('hide');
							reload();
							curtypeid=null;
						} else {
							 $.dalert({text : data.msg, icon : 2});
							$('#itemModel').modal('hide');
						}
					}
				});
    		} else {
    			$.dalert({text :"验证不通过，有部分数据格式错误或有必填数据未填",icon:7});
    		}
        }
        function cancel() {
            $('#itemModel').modal('hide');
        }
        function del() {
            if (curtypeid == null) {
                $.dalert({text: "未选择要删除的填报人类别", icon: 7});
            } else {
                $.dconfirm({
                    text: "确认继续？", btn: ["确定", "取消"], funs: [function () {
                        $.dajax({
                            url: "common/deleteType.do",
                            data: { typeid: curtypeid },
                            success: function (data) {
                                if (data.success) {
                                    $.dalert({text: data.content});
                                    reload();
        							curtypeid=null;
        							userTree.checkAllNodes(false);
                                }
                            }
                        });
                    }, function () {
                        $.dalert({text: "取消操作", icon : 7});
                    }]
                });
            }
        }
        function saveTypeToUser(){
        	var userIds="";
        	if (curtypeid == null) {
                $.dalert({text: "未选择要操作的填报人类别", icon: 7});
            } else {
            	var checkedNodes = userTree.getCheckedNodes();
                $.each(checkedNodes, function(i, node) {
                    userIds +=  this.id + ",";
                });
	        	$.dajax({
					url : "common/saveTypeToUser.do",
					data : {userIds : userIds, typeId : curtypeid},
					success : function(data) {
						if (data.success) {
							 $.dalert({text : data.msg, icon : 1});
						} else {
							 $.dalert({text : data.msg, icon : 2});
						}
					}
				});
            }
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3 demo">
	        <button class="bootstrap-table-add" type="button" onclick="add()">新增</button>
			<button class="bootstrap-table-delete" type="button" onclick="del()">删除</button>
		</div>
		<div class="col-md-7 demo">
	        <button class="bootstrap-table-submit" type="button" onclick="saveTypeToUser()">保存</button>
        </div>
    </div>
    <div class="row">
        <div class="col-md-3 demo">
            <div class="demo-tit">填报人类别</div>
            <ul id="typeTree" class="demo-tree"></ul>
        </div>
        <div class="col-md-7 demo">
            <div class="demo-tit">填报人信息</div>
            <ul id="userTree" class="demo-tree"></ul>
        </div>
    </div>
</div>
<div class="modal fade" id="itemModel" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" style="display: none;">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="">填报人类别对应用户信息</h4>
            </div>

            <div class="modal-body">
                <div class="tab-content">
                    <!-- 用户信息 tab -->
                    <div class="tab-pane fade in active" id="itemRecords">
                       <form class="form-horizontal" role="form" action="" method="post" id="fm">
                            <div class="form-group">
                                <input type="text" class="form-control" id="typeId" name="typeId" style="display: none;"/>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">填报人类别编码：</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="typeCode" name="typeCode" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" proving="notEmpty" />
                                    </div>
                                </div>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">填报人类别名称：</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="typeName" name="typeName" proving="notEmpty" />
                                    </div>
                                </div>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">类别说明：</label>
                                    <div class="col-sm-8">
                                        <textarea class="form-control" id="remark" name="remark" rows="4"></textarea>
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
