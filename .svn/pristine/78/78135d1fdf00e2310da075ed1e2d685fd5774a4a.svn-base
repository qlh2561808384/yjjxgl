<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page language="java" import="com.datanew.model.BaseOperator" %>
<%@ page language="java" import="com.datanew.util.StaticData" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    BaseOperator operator = (BaseOperator) session.getAttribute(StaticData.LOGINUSER);
    Integer userguid = operator.getUserid();
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <title>报表设置</title>
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

        var reportTree;
        $(function () {
            $("body").css({width: $(window).width(), overflowX: "hidden"});
            var zNodes = [
                {id: "0", name: "更新报表数据"},
                {id: "1", name: "发布新的任务"}
            ];

            reload();
            $('#informant').searchTree({
          	    checkType:'checkbox',
   	          	url : 'common/findInformantTree.do',
   	          	 onAckCallback:function(nodes){
                   	var informantids ="";
                   	var informantnames="";
                   	for(var i=0;i<nodes.length;i++){
                   		if(!(nodes[i].id+"").indexOf("tbr") <= 0){
                   			if(i==nodes.length-1){
                       			informantids+=nodes[i].id;
                       			informantnames+=nodes[i].name;
                       		}else{
                       			informantids+=nodes[i].id+",";
                       			informantnames+=nodes[i].name+",";
                       		}
                   		}
                   	}
                   	$('#informant').searchTree("setValue",informantids);
                   	$('#informant').searchTree("setText",informantnames);
                }
           });
            $('#isMax').searchTree({
          	    checkType:'radio',
          	    localdata:zNodes,
   	          	 onAckCallback:function(nodes){
   	          	$('#isMax').searchTree("setValue",nodes[0].id);
                }
           });
            $('#module').searchTree({
          	    checkType:'radio',
   	          	url : 'common/findModuleTree.do',
   	          	onAckCallback:function(nodes){
   	          		$('#module').searchTree("setValue",nodes[0].id);
   	          	  }
           });
            $("#fm").dform("disabled");
        });
        $(document).ready(function() {
            $(document).keydown(function() {
                if (event.keyCode == 13) {
                    event.keyCode = '9';
                }
            })
        });
        var curReportId;
        function getMenu(event, treeId, treeNode) {
        	if(treeNode.checked){
        		curReportId = treeNode.id;
        		reloadInfo();
        	}else{
        		curReportId=null;
        	}
        }
        function reload(){
        	reportTree = $("#reportTree").dtree({
                checkType: "radio", //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
                isOpen: true, //节点是否全部展开，默认为不展开
                ISLEAF: true,
                rootElement: false,  //是否添加根节点“全部”
                url: "common/findReportTree.do",
                callback: {
                    onCheck: getMenu
                }
            });
        }
        function reloadInfo(){
        	$.dajax({
                data : {reportId : curReportId},
                url : "report/findReportInfo.do",
                success : function(data) {
                	var z = data.info.enable==1?"是":"否";
                	data.info.enable = z;
                	var t = data.info.tbenable==1?"是":"否";
                	data.info.tbenable = t;
                	var u = data.info.isupload==1?"是":"否";
                	data.info.isupload = u;
                	var s = data.info.isSelect==1?"是":"否";
                	data.info.isSelect = s;
                    $.dloadformdata($("#fm"), data.info, "dname");
                }
            });
        }
		function add(){
			$("#name").attr("disabled",false);
			$("#tableName").attr("disabled",false);
			$("#enable").attr("disabled",false);
	        $('#fm').dform('clear');
		}
		function cancel(){
			$("#name").attr("disabled",true);
			$("#tableName").attr("disabled",true);
			$("#enable").attr("disabled",true);
		}
		function edit(){
			if (curReportId == null) {
                $.dalert({text: "未选择要修改的数据", icon: 7});
            } else {
            	$("#name").attr("disabled",false);
    			$("#tableName").attr("disabled",false);
    			$("#enable").attr("disabled",false);
            }
	        	
		}
        function save() {
        	 var flag = $("#fm").dform("validate");
 			if (flag) {
 				var tableName = $("#tableName").val();
 			  	var patrn=/[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi; 
 				if(patrn.exec(tableName)){ 
 					$.dalert({
						text :"表名不能包含中文！",
							icon : 1
				     });
 					return;
 			  	}
 			  	
 			  	var patrn1=/^[a-zA-Z]{1}/;
 				if(!patrn1.exec(tableName)){
 					$.dalert({
						text :"表名首字符必须是字母！",
							icon : 1
				     });
 					return;
 			  	}
 				var reg= /\s+/;
 				if(reg.test($("#name").val())){
 					$.dalert({
						text :"报表名称不能包含有空格",
 							icon : 1
				     });
 					return;
 				}
 				if(reg.test(tableName)){
 					$.dalert({
						text :"表名不能包含有空格",
						icon : 1
					});
 					return;
 				}
 				$.dajax({
					cache : true,
					type : "POST",
					url : "report/saveReportInfo.do",
					data : "guid="+$("#guid").val()+"&"+$('#fm').serialize(),
					async : true,
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							$.dalert({
								text : data.msg,
								icon : 1
							});
							reload();
							$('#fm').dform('clear');
							$("#fm").dform("disabled");
						} else {
							$.dalert({
								text : data.msg,
								icon : 2,
							});
						}
					}
				});
 			}else{
 				$.dalert({text :"验证不通过，有部分数据格式错误或有必填数据未填",icon:7});
 			}
        }
        function del() {
            if (curReportId == null) {
                $.dalert({text: "未选择要删除的数据", icon: 7});
            } else {
                $.dconfirm({
                    text: "确认继续？", btn: ["确定", "取消"], funs: [function () {
                        $.dajax({
                            url: "report/deleteRopert.do",
                            data: { reportid: curReportId },
                            success: function (data) {
                                if (data.success) {
                                    $.dalert({text: data.content});
                                    reload();
                                    $('#fm').dform('clear');
                                    $("#fm").dform("disabled");
                                }
                            }
                        });
                    }, function () {
                        $.dalert({text: "取消操作", icon : 7});
                    }]
                });
            }
        }
        function design(){
        	 if (curReportId == null) {
                 $.dalert({text: "未选择要设计的报表", icon: 7});
             } else {
		        	var obj = new Object();
		    		obj.name = $("#designName").val();
		    		obj.parentGuId = "";
		    		obj.userid = <%=userguid%>;
		    		obj.tableName = $("#tableName").val();
		    		obj.title = $("#name").val();
		    		obj.arrs = "YHM,XZQH".split(",");
					obj.arrsCN = "YHM,XZQH".split(",");
					obj.arrsDataType = "VARCHAR2,VARCHAR2".split(",");
					obj.arrsDataLength = "10,6".split(",");
		    		var returnValue = window.showModalDialog('reportDesigner.htm',obj,'dialogwidth:1000px; dialogheight:1000px; status:no; help:no;minimize:yes;maximize:yes');
		    		if(returnValue!=null){
		    			var designName = returnValue.name;
		    			$.dajax({
		                    url: "report/updateRopert.do",
		                    data: { guid: $("#guid").val(),designName:designName },
		                    success: function (data) {
		                        if (data.success) {
		                            $.dalert({text: data.content});
		                            $("#designName").val(designName);
		                        }
		                    }
		                });
		    		}
             }
        }
        var obj=new Object();
        function sendMessage(){
	        	if (curReportId == null) {
	               return $.dalert({text: "未选择要发布任务的报表", icon: 7});
	            }
        	   if($("#enable").val()=="否"){
        		   return $.dalert({text :"请先启用报表再进行发布操作",icon:7});
        	   }
        	   if($("#isSelect").val()=="否"){
        		   return $.dalert({text :"该报表不需要选择填报人进行发布",icon:7});
        	   }
        	   $('#dfm').dform('clear');
               $("#itemModel").modal('show');
        }
        function doPublishReport(){
        	var flag = $("#dfm").dform("validate");
    	    if(!flag){
    	        $.dalert({text :"验证不通过，有部分数据格式错误或有必填数据未填",icon:7});
    	        return;
    	    }if (curReportId == null) {
                $.dalert({text: "未选择要发布任务的报表", icon: 7});
            } else {
		   	    $.dajax({
		             url: "report/sendTask.do",
		             data: {userid:$('#informant').searchTree("getValue"),module:$("#module").searchTree("getValue"),guid:curReportId,isMax: $("#isMax").searchTree("getValue"),remark: $("#remark").val()},
		             success: function (data) {
		             	if (data.success) {
		             		$('#itemModel').modal('hide');
		             		 $.dalert({text : data.content, icon : 1});
		                 }else{
		                 	$('#itemModel').modal('hide');
		                 	 $.dalert({text : data.content, icon : 2});
		                 }
		     	    }
		         });
            }
        }
        function fjeable(){
        	if (curReportId == null) {
                return $.dalert({text: "未选择需要操作的报表", icon: 7});
            }
		    $.dajax({
		          url: "report/isuploadReport.do",
		          data: {guid:curReportId},
		          success: function (data) {
		          	if (data.success) {
		          		$('#itemModel').modal('hide');
		          		 $.dalert({text : data.content, icon : 1});
		          		reloadInfo();
		              }else{
		              	$('#itemModel').modal('hide');
		              	 $.dalert({text : data.content, icon : 2});
		              }
		  	    }
		      });
        }
        function tbrenable(){
        	if (curReportId == null) {
                return $.dalert({text: "未选择需要操作的报表", icon: 7});
            }
		    $.dajax({
		          url: "report/isSelectReport.do",
		          data: {guid:curReportId},
		          success: function (data) {
		          	if (data.success) {
		          		$('#itemModel').modal('hide');
		          		 $.dalert({text : data.content, icon : 1});
		          		reloadInfo();
		              }else{
		              	$('#itemModel').modal('hide');
		              	 $.dalert({text : data.content, icon : 2});
		              }
		  	    }
		      });
        }
        function enable(){
        	if (curReportId == null) {
                return $.dalert({text: "未选择需要操作的报表", icon: 7});
            }
		     $.dajax({
		          url: "report/enableReport.do",
		          data: {guid:curReportId},
		          success: function (data) {
		          	if (data.success) {
		          		$('#itemModel').modal('hide');
		          		 $.dalert({text : data.content, icon : 1});
		          		reloadInfo();
		              }else{
		              	$('#itemModel').modal('hide');
		              	 $.dalert({text : data.content, icon : 2});
		              }
		  	    }
		      });
        }
        function tbenable(){
        	if (curReportId == null) {
                return $.dalert({text: "未选择需要操作的报表", icon: 7});
            }
		     $.dajax({
		          url: "report/tbenableReport.do",
		          data: {guid:curReportId},
		          success: function (data) {
		          	if (data.success) {
		          		$('#itemModel').modal('hide');
		          		 $.dalert({text : data.content, icon : 1});
		          		reloadInfo();
		              }else{
		              	$('#itemModel').modal('hide');
		              	 $.dalert({text : data.content, icon : 2});
		              }
		  	    }
		      });
        }
        function docancel(){
        	$('#itemModel').modal('hide');
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <button class="bootstrap-table-add" type="button" onclick="sendMessage()">任务发布</button>
    </div>
    <div class="row">
        <div class="col-md-4 demo">
            <div class="row"><div class="demo-tit">报表信息</div></div>
            <div class="row">
                <ul id="reportTree" class="demo-tree"></ul>
            </div>
        </div>
        <div class="col-md-8 demo">
            <div class="row"><div class="demo-tit">报表发布信息</div></div>
            <div class="row">
	            <div class="tab-pane fade in active" id="itemRecords" style="margin-top:10px" >
					<form class="form-horizontal" role="form" method="post" id="fm">
						<div class="form-group">
						    <div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="guid" class="col-sm-4 control-label">内部编号：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="guid" name="guid" dname="guid" />
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="name" class="col-sm-4 control-label">报表名称：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="name" name="name" dname="name" proving="notEmpty"/>
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="enable" class="col-sm-4 control-label">是否启用报表：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="enable" name="enable" dname="enable" proving="notEmpty"/>
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="isTb" class="col-sm-4 control-label">是否启用填报功能：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="tbenable" name="tbenable" dname="tbenable" proving="notEmpty"/>
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="isupload" class="col-sm-4 control-label">是否需要上传附件：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="isupload" name="isupload" dname="isupload" proving="notEmpty"/>
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="isSelect" class="col-sm-4 control-label">是否需要选择填报人：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="isSelect" name="isSelect" dname="isSelect" proving="notEmpty"/>
								</div>
							</div>
						</div>
					</form>
					<div class="well well-sm" align="right">
					     <button class="bootstrap-table-submit" type="button" onclick="enable()">启用/停用</button>
					     <button class="bootstrap-table-submit" type="button" onclick="tbenable()">用户填报：启用/停用</button>
					     <button class="bootstrap-table-submit" type="button" onclick="fjeable()">附件上传:是/否</button>
					     <button class="bootstrap-table-submit" type="button" onclick="tbrenable()">选择填报人发布:是/否</button>
			        </div>
				</div>
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
					<h4 class="modal-title" id="">确认信息</h4>
				</div>

				<div class="modal-body">
					<div class="tab-content">
						<!-- 用户信息 tab -->
						<div class="tab-pane fade in active" id="itemRecords">
							<form class="form-horizontal" role="form" method="post" id="dfm">
								<div class="form-group">
									<div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="informant" class="col-sm-4 control-label">选择填报人：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="informant" name="informant" proving="notEmpty" />
										</div>
									</div>
									<div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="isMax" class="col-sm-4 control-label">报表发布原因：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" name="isMax" id="isMax" />
										</div>
									</div>
									<div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="module" class="col-sm-4 control-label">选择通知短信模板：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="module" name="module"  />
										</div>
									</div>
									<div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="remark" class="col-sm-4 control-label">备注：</label>
										<div class="col-sm-6">
											<textarea class="form-control" id="remark" name="remark"
		                                          dname="remark" rows="4"></textarea>
										</div>
									</div>
								</div>
							</form>
							<div class="well well-sm" align="right">
								<button class="btn btn-info btn-sm"
									onclick="doPublishReport()">确定</button>
								<button class="btn btn-default btn-sm"
									onclick="docancel()">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
