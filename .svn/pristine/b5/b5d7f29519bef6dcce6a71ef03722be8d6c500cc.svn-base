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
                {id: 1, pId: 0, name: "节点搜索演示 1"},
                {id: 1, pId: 0, name: "节点搜索演示 2"}
            ];

            reload();
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
	            $.dajax({
	                data : {reportId : curReportId},
	                url : "report/findReportInfo.do",
	                success : function(data) {
	                	data.info.createDate = new Date(data.info.createDate).toLocaleString().replace(/:\d{1,2}$/,' ');
	                    $.dloadformdata($("#fm"), data.info, "dname");
	                }
	            });
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
		function add(){
			$("#name").attr("disabled",false);
			//$("#tableName").attr("disabled",false);
			//$("#collectName").attr("disabled",false);
			//$("#fillName").attr("disabled",false);
			//$("#designName").attr("disabled",false);
	        $('#fm').dform('clear');
		}
		function cancel(){
			$("#name").attr("disabled",true);
			//$("#tableName").attr("disabled",true);
			//$("#collectName").attr("disabled",true);
			//$("#fillName").attr("disabled",true);
			//$("#designName").attr("disabled",true);
		}
		function edit(){
			if (curReportId == null) {
                $.dalert({text: "未选择要修改的数据", icon: 7});
            } else {
            	$("#name").attr("disabled",false);
    			$("#collectName").attr("disabled",false);
    			$("#fillName").attr("disabled",false);
    			$("#designName").attr("disabled",false);
            }
	        	
		}
        function save() {
        	 var flag = $("#fm").dform("validate");
 			if (flag) {
 				//var tableName = $("#tableName").val();
 			  	var patrn=/[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi; 
 				/* if(patrn.exec(tableName)){ 
 					$.dalert({
						text :"表名不能包含中文！",
							icon : 1
				     });
 					return;
 			  	} */
 			  	
 			  	var patrn1=/^[a-zA-Z]{1}/;
 				/* if(!patrn1.exec(tableName)){
 					$.dalert({
						text :"表名首字符必须是字母！",
							icon : 1
				     });
 					return;
 			  	} */
 				var reg= /\s+/;
 				if(reg.test($("#name").val())){
 					$.dalert({
						text :"报表名称不能包含有空格",
 							icon : 1
				     });
 					return;
 				}
 				/* if(reg.test(tableName)){
 					$.dalert({
						text :"表名不能包含有空格",
						icon : 1
					});
 					return;
 				} */
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
        function publishFile(){
        	if (curReportId == null) {
                $.dalert({text: "未选择要设计的报表", icon: 7});
            } else {
	        	$.dajax({
	                url: "report/findFillData.do",
	                data: $.param({tableName : $("#tableName").val(),fillName : $("#fillName").val()}),
	                success: function (data) {
						obj.guid = $("#guid").val();
						obj.flag = data.success;
						if(data.content!=null){
							$.dalert({
								text : data.content,
								icon : 2,
							});
						}else{
							$("#itemModel").modal('show');
							if(obj.flag){
								document.getElementById("isselect").style.display = "none";
							}
						}
	                }
	            });
            }
        }
        function doPublishReport(){
        	var mode = 0;
    	    if($("#designName").val()==""){
    	    	 return  $.dalert({text: "请先设计报表模板", icon: 7});
    	    }
    	    if($("#fillName").val()!=""){
   	    	     return  $.dalert({text: "该报表已经发布不能重复发布", icon: 7});
   	        }
    	    $.dajax({
                url: "report/savePublishReport.do",
                data: { guid:$("#guid").val(),mode:mode },
                success: function (data) {
                	if (data.success) {
                		$('#itemModel').modal('hide');
                		 $.dalert({text : data.content, icon : 1});
                		 $("#fillName").val(data.reportFileName);
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
        <button class="bootstrap-table-add" type="button" onclick="add()">新增</button>
        <button class="bootstrap-table-edit" type="button" onclick="edit()">修改</button>
        <button class="bootstrap-table-delete" type="button" onclick="del()">删除</button>
        <!-- <button class="bootstrap-table-edit" type="button" onclick="design()">设计报表模板</button>
        <button class="bootstrap-table-submit" type="button" onclick="doPublishReport()">发布</button> -->
        <!-- <button class="bootstrap-table-edit" type="button" onclick="designtb()">设计填报模板</button> -->
    </div>

    <div class="row">
        <div class="col-md-4 demo">
            <div class="row"><div class="demo-tit">报表信息</div></div>
            <div class="row">
                <ul id="reportTree" class="demo-tree"></ul>
            </div>
        </div>
        <div class="col-md-5 demo">
            <div class="row"><div class="demo-tit">报表配置信息</div></div>
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
								<label for="tableName" class="col-sm-4 control-label">储存表名：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="tableName" name="tableName" dname="tableName" />
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
							    <label for="userGuId" class="col-sm-4 control-label">创建人：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="userGuId" name="userGuId" dname="userGuId" />
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="userName" class="col-sm-4 control-label">创建人名称：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="userName" name="userName" dname="userName" />
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="createDate" class="col-sm-4 control-label">创建日期：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="createDate" name="createDate" dname="createDate" />
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="designName" class="col-sm-4 control-label">设计模板名称：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="designName" name="designName" dname="designName"/>
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="fillName" class="col-sm-4 control-label">填报模板名称：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="fillName"
										name="fillName" dname="fillName"/>
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="collectName" class="col-sm-4 control-label">汇总模板名称：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="collectName"
										name="collectName" dname="collectName"/>
								</div>
							</div>
						</div>
					</form>
					<div class="well well-sm">
						<button class="btn btn-info btn-sm"
							onclick="save()">保存</button>
						<button class="btn btn-default btn-sm"
							onclick="cancel()">取消</button>
					</div>
				</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
