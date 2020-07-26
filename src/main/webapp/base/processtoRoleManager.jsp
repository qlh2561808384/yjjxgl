<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <title>流程对环节</title>
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

        var roleTree;
        var userKey = parent.currkey;
        $(function () {
            $("body").css({width: $(window).width(), overflowX: "hidden"});
            var zNodes = [
                {id: "START", pId: 0, name: "开始"},
                {id: "NOTAGREE", pId: 0, name: "不同意"},
                {id: "UNEDIT", pId: 0, name: "不可能编辑"},
                {id: "BACK", pId: 0, name: "退回"}
            ];
             reload();
             $('#authIds').searchTree({
            	    checkType:'checkbox',
            	    localdata: zNodes,
             	//url : 'common/findAuthTree.do',
                 onAckCallback:function(nodes){
                	 var authIds="";
                  	var authNames="";
                  	for(var i=0;i<nodes.length;i++){
                  		if(i==nodes.length-1){
                  			authIds+=nodes[i].id;
                  			authNames+=nodes[i].name;
                  		}else{
                  			authIds+=nodes[i].id+",";
                  			authNames+=nodes[i].name+",";
                  		}
                  	}
                  	$('#authIds').searchTree("setValue",authIds);
                  	$('#authIds').searchTree("setText",authNames);
                 }
             }); 
             $('#roleId').searchTree({
            	    checkType:'radio',
             	url : 'common/findJczlPostTree.do',
                 onAckCallback:function(nodes){
                 	var roleIds="";
                 	var rolenames="";
                 	for(var i=0;i<nodes.length;i++){
                 		if(i==nodes.length-1){
                 			roleIds+=nodes[i].id;
                     		rolenames+=nodes[i].name;
                 		}else{
                 			roleIds+=nodes[i].id+",";
                     		rolenames+=nodes[i].name+",";
                 		}
                 	}
                 	$('#roleId').searchTree("setValue",roleIds);
                 	$('#roleId').searchTree("setText",rolenames);
                 }
             }); 
             $("#datatable").dtable({
           	 height:500,
               columns: [[{
                   checkbox: true
               }, {
            	   title: '序号',//标题  可不加  
            	   width: 20,
                   formatter: function (value, row, index) {  
                       return index+1;  
                   } 
               }, {
                   field: 'NODEID',
                   title: '环节id',
                   width: 100,
                   align: 'center',
                   visible: false
               }, {
                   field: 'NODENUM',
                   title: '开始环节序号',
                   width: 100,
                   align: 'center'
               },{
                   field: 'AFTERNODENUM',
                   title: '后继环节序号',
                   width: 100,
                   align: 'center'
               } ,{
                   field: 'NODENAME',
                   title: '环节名称',
                   width: 100,
                   align: 'center'
               },{
                   field: 'ROLEID',
                   title: '关联角色',
                   width: 100,
                   align: 'center',
                   visible: false
               },{
                   field: 'ROLENAME',
                   title: '关联角色',
                   width: 100,
                   align: 'center',
                   formatter: function (value, row, index) {
                       if(value==''||value==null){
                           return "未关联角色";
                       }else{
                           return value;
                       }
                   }
               } ,{
                   field: 'AUTHIDS',
                   title: '操作权限',
                   width: 100,
                   align: 'center',
                   visible: false
               },{
                   field: 'AUTHNAMES',
                   title: '操作权限',
                   width: 100,
                   align: 'center'
               },{
                   field: 'ISROLESELECT',
                   title: '是否角色选择',
                   width: 100,
                   align: 'center',
   				   formatter : statusFormatter
               }]],
               url:'common/getProcessNode.do',
               pageNumber: 1,//起始页
               showRefresh:false,
               pageSize: 20,//页面大小
               queryParams: queryParams,
               paginationHAlign: 'left',//分页按钮位置  left right
               sidePagination: 'server',//分页方式(真分页)
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
        function statusFormatter(value) {
    		switch (value) {
    		case 0:
    			return '否';
    		case 1:
    			return "是";
    		}
    	}
        function reload(){
        	 roleTree = $("#processTree").dtree({
                 //localdata: zNodes,
                 checkType: "radio", //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
                 isOpen: true, //节点是否全部展开，默认为不展开
                 ISLEAF: true,
                 rootElement: false,  //是否添加根节点“全部”
                 url: "common/findProcessTree.do",
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
            	processId:curroleid,
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
					url : "common/saveProcess.do",
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
                 $.dalert({text: "未选择要修改的流程", icon: 7});
             } else {
	        	$('#fm').dform('clear');
	        	$.dajax({
	                data : {processId : curroleid},
	                url : "common/findProcessInfo.do",
	                success : function(data) {
	                	var obj=eval(data.process);
	                   	$('#processId').val(obj[0].processId);
	                   	$('#processCode').val(obj[0].processCode);
	                   	$('#processName').val(obj[0].processName);
	                   	$('#remark').val(obj[0].remark);
	                   	$('#processStatus').val(obj[0].processStatus);
	                   	$("#itemModel").modal('show');
	                   	$("#datatable").bootstrapTable("refresh");
	                }
	            });
             }
        }
        function del() {
            if (curroleid == null) {
                $.dalert({text: "未选择要删除的流程", icon: 7});
            } else {
                $.dconfirm({
                    text: "确认继续？", btn: ["确定", "取消"], funs: [function () {
                        $.dajax({
                            url: "common/deleteProcess.do",
                            data: { processId: curroleid },
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
        function delrow(){
        	var dataArr = $("#datatable").bootstrapTable("getSelections");
    		if (dataArr.length == 0) {
    			$.dalert({
    				text : "未选择要删除的行",
    				icon : 7
    			});
    		} else {
    			$.dconfirm({
                    text: "确认继续？", btn: ["确定", "取消"], funs: [function () {
                        $.dajax({
                            url: "common/deleteNode.do",
                            data: { nodeId : dataArr[0].NODEID },
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
        var datarow={};
        function addrow(){
        	if (curroleid == null) {
               return $.dalert({text: "未选择要配置的流程", icon: 7});
            }
        	$('#profm').dform('clear');
            $("#proitemModel").modal('show');
        }
        function editrow(){
        	var dataArr = $("#datatable").bootstrapTable("getSelections");
            if (dataArr.length == 0) {
                $.dalert({text: "请选择要修改的岗位", icon: 7});
            } else {
                $("#proitemModel").modal('show');
                $.dloadformdata($("#profm"), dataArr[0], "dname");
            }
        }
        function savepro(){
        	var flag = $("#profm").dform("validate");
    		if (!flag) {
    			return $.dalert({text: "验证不通过，有部分数据格式错误或有必填数据未填", icon: 7});
    		}
        	$.dajax({
				cache : true,
				type : "POST",
				url : "common/saveProcessNode.do",
				data : $.param({processId:curroleid,authNames:$('#authIds').searchTree("getText")}) + '&' + $('#profm').serialize(),
				async : true,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						 $.dalert({text : data.msg, icon : 1});
						$('#itemModel').modal('hide');
						//reload();
						//curroleid=null;
						$("#datatable").bootstrapTable("refresh");
					} else {
						 $.dalert({text : data.msg, icon : 2});
						$('#itemModel').modal('hide');
					}
				}
			});
        	$('#proitemModel').modal('hide');
        }
        function cancelpro() {
            $('#proitemModel').modal('hide');
        }
    </script>
</head>
<body>
<div id="bt">
    <button class="btn btn-primary bootstrap-table-add" type="button" onclick="addrow()">新增</button>
    <button class="btn btn-primary bootstrap-table-edit" type="button" onclick="editrow()">修改</button>
    <button class="btn btn-primary bootstrap-table-delete" type="button" onclick="delrow()">删除</button>
</div>
<div class="container-fluid">
    <div class="row">
        <button class="bootstrap-table-add" type="button" onclick="add()">新增</button>
		<button class="bootstrap-table-edit" type="button" onclick="edit()">修改</button>
		<button class="bootstrap-table-delete" type="button" onclick="del()">删除</button>
    </div>

    <div class="row">
        <div class="col-md-3 demo">
            <div class="row"><div class="demo-tit">流程信息</div></div>
            <div class="row">
                <ul id="processTree" class="demo-tree"></ul>
            </div>
        </div>
        <div class="col-md-9 demo">
            <div class="row"><div class="demo-tit">流程配置信息</div></div>
            <div class="row">
                <table id="datatable">
                </table>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="itemModel" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" style="display: none;">
    <div class="modal-dialog modal-lg" role="document" style="width:600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="">流程对应环节信息</h4>
            </div>

            <div class="modal-body">
                <div class="tab-content">
                    <!-- 用户信息 tab -->
                    <div class="tab-pane fade in active" id="itemRecords">
                       <form class="form-horizontal" role="form" action="" method="post" id="fm">
                            <div class="form-group">
                                <input type="text" class="form-control" id="processId" name="processId" style="display: none;" />
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">流程编码：</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="processCode" name="processCode" proving="notEmpty"/>
                                    </div>
                                </div>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">流程名称：</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="processName" name="processName" proving="notEmpty"/>
                                    </div>
                                </div>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">流程状态：</label>
                                    <div class="col-sm-4">
                                       <select id="processStatus" name="processStatus" proving="notEmpty" class="selectpicker show-tick form-control"	data-live-search="false" >
												<option value="1">启用</option>
												<option value="0">停用</option>
									 </select>
                                    </div>
                                </div>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">流程说明：</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" id="remark" name="remark" rows="4" proving="notEmpty"></textarea>
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
<div class="modal fade" id="proitemModel" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" style="display: none;">
    <div class="modal-dialog modal-lg" role="document" style="width:600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="">流程对应环节信息</h4>
            </div>

            <div class="modal-body">
                <div class="tab-content">
                    <!-- 用户信息 tab -->
                    <div class="tab-pane fade in active" id="proitemRecords">
                       <form class="form-horizontal" role="form" action="" method="post" id="profm">
                            <div class="form-group">
                                <input type="text" class="form-control" id="nodeId" name="nodeId" dname="NODEID" style="display: none;"/>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">开始环节：</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="nodeNum" name="nodeNum" dname="NODENUM" proving="notEmpty"/>
                                    </div>
                                </div>
                                <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">后继环节：</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="after_Node_Num" name="after_Node_Num" dname="AFTERNODENUM" proving="notEmpty"/>
                                    </div>
                                </div>
                                 <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">环节名称：</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="nodename" name="nodename" dname="NODENAME" proving="notEmpty"/>
                                    </div>
                                </div>
                                 <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">关联角色：</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="roleId" name="roleId" dname="ROLEID" proving="notEmpty"/>
                                    </div>
                                </div>
                                 <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">操作权限：</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="authIds" name="authIds" dname="AUTHIDS" proving="notEmpty"/>
                                    </div>
                                </div>
                                 <div class="col-sm-12" style="margin-bottom: 10px;">
                                    <label class="col-sm-4 control-label">是否角色选择：</label>
                                    <div class="col-sm-6">
                                        <select id="isRoleSelect" name="isRoleSelect" dname="ISROLESELECT" proving="notEmpty" class="selectpicker show-tick form-control" data-live-search="false" >
												<option value="1">是</option>
												<option value="0">否</option>
										</select>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="well well-sm" align="right">
                            <button class="btn btn-info btn-sm" 
                                    onclick="savepro()">确定
                            </button>
                            <button class="btn btn-default btn-sm"
                                    onclick="cancelpro()">取消
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