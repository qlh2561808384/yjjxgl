<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <title>角色对用户</title>
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
        
        .bootstrap-table{border:none !important; padding:0 !important;}
        .fixed-table-toolbar .bs-bars{margin-bottom:0 !important;}
    </style>
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.datanew.js"></script>
    <script type="text/javascript">

        var roleTree;
        var userKey = parent.currkey;
        $(function () {
            $("body").css({width: $(window).width(), overflowX: "hidden"});

            $('#userId').searchTree({
           	    checkType:'checkbox',
            	url : 'common/getUserTree.do',
                onAckCallback:function(nodes){
                	var userIds="";
                	var usernames="";
                	for(var i=0;i<nodes.length;i++){
                		if(i==nodes.length-1){
                			userIds+=nodes[i].id;
                    		usernames+=nodes[i].name;
                		}else{
                			userIds+=nodes[i].id+",";
                    		usernames+=nodes[i].name+",";
                		}
                	}
                	$('#userId').searchTree("setValue",userIds);
                	$('#userId').searchTree("setText",usernames);
                }
            }); 
           reload();
             $("#datatable").dtable({
           	 height:500,
               columns: [[{
            	   title: '序号',//标题  可不加  
            	   width: 50,
                   formatter: function (value, row, index) {  
                       return index+1;  
                   } 
               }, {
                   field: 'ROLENAME',
                   title: '角色名称',
                   width: 100,
                   align: 'center'
               },{
                   field: 'USERNAME',
                   title: '关联用户',
                   width: 100,
                   align: 'center'
               }]],
               url:'common/getRoleUser.do',
               pageNumber: 1,
               pageSize: 20,
               showRefresh:false,
               queryParams: queryParams,
               paginationHAlign: 'left',
               sidePagination: 'server',
               toolbar: '#bt',
               singleSelect: true,
               clickToSelect: true
             
           });  
          
        });
        $(document).ready(function() {
            $(document).keydown(function() {
                if (event.keyCode == 13) {
                    event.keyCode = '9';
                }
            })
        });
        function reload(){
        	 roleTree = $("#roleTree").dtree({
                 //localdata: zNodes,
                 checkType: "radio", //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
                 isOpen: true, //节点是否全部展开，默认为不展开
                 ISLEAF: true,
                 rootElement: false,  //是否添加根节点“全部”
                 url: "common/findRoleTree.do",
                 callback: {
                     onCheck: getMenu
                 }
             }); 
        }
        var curroleid;
        function getMenu(event, treeId, treeNode) {
        	if(treeNode.checked){
        		curroleid = treeNode.id;
        		$("#datatable").bootstrapTable("refresh");
        	}else{
        		curroleid=null;
        	}
        }
        function queryParams(params) {
            return {
            	roleid:curroleid,
                limit: params.limit,
                offset: params.offset,
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
					url : "common/saveRole.do",
					data : $.param({'userKey' : userKey}) + '&' + $('#fm').serialize(),
					async : true,
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							 $.dalert({text : data.msg, icon : 1});
							$('#itemModel').modal('hide');
							reload();
							curroleid=null;
							$("#datatable").bootstrapTable("refresh");
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
        function edit(){
        	 if (curroleid == null) {
                 $.dalert({text: "未选择要修改的角色", icon: 7});
             } else {
	        	$('#fm').dform('clear');
	        	$.dajax({
	                data : {roleid : curroleid},
	                url : "common/findRoleInfo.do",
	                success : function(data) {
	                	var obj=eval(data.role);
	                   	$('#roleId').val(obj[0].roleId);
	                   	$('#roleCode').val(obj[0].roleCode);
	                   	$('#roleName').val(obj[0].roleName);
	                   	$('#remark').val(obj[0].remark);
	                   	$('#userId').searchTree("setValue",data.user);
	                   	$("#itemModel").modal('show');
	                   	$("#datatable").bootstrapTable("refresh");
	                }
	            });
             }
        }
        function del() {
            if (curroleid == null) {
                $.dalert({text: "未选择要删除的角色", icon: 7});
            } else {
                $.dconfirm({
                    text: "确认继续？", btn: ["确定", "取消"], funs: [function () {
                        $.dajax({
                            url: "common/deleteRole.do",
                            data: { roleid: curroleid },
                            success: function (data) {
                                if (data.success) {
                                    $.dalert({text: data.content});
                                    reload();
        							curroleid=null;
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
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <button class="bootstrap-table-add" type="button" onclick="add()">新增</button>
		<button class="bootstrap-table-edit" type="button" onclick="edit()">修改</button>
		<button class="bootstrap-table-delete" type="button" onclick="del()">删除</button>
    </div>

    <div class="row">
        <div class="col-md-3 demo">
            <div class="row"><div class="demo-tit">角色信息</div></div>
            <div class="row">
                <ul id="roleTree" class="demo-tree"></ul>
            </div>
        </div>
        <div class="col-md-7 demo">
                <table id="datatable"></table>
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
                <h4 class="modal-title" id="">角色对应用户信息</h4>
            </div>

            <div class="modal-body">
                <div class="tab-content">
                    <!-- 用户信息 tab -->
                    <div class="tab-pane fade in active" id="itemRecords">
                       <form class="form-horizontal" role="form" action="" method="post" id="fm">
                            <div class="form-group">
                                <input type="text" class="form-control" id="roleId" name="roleId" style="display: none;"/>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">角色编码：</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="roleCode" name="roleCode"/>
                                    </div>
                                </div>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">角色名称：</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="roleName" name="roleName"/>
                                    </div>
                                </div>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">角色对应用户：</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="userId" name="userId"/>
                                    </div>
                                </div>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">角色说明：</label>
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
