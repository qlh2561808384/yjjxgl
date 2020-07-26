<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>用户管理</title>
<link rel="stylesheet" href="css/common.css"/>
<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.datanew.js"></script>
<script type="text/javascript">
var oldPassWord;
	$(function() {
		$("#datatable").dtable({
			columns : [ [ {
				checkbox : true
			}, {
				field : 'LOGINNAME',
				title : '登录名 ',
				width : 100,
				align : 'center'
			}, {
				field : 'USERNAME',
				title : '姓名',
				width : 150,
				align : 'center'
			}, {
				field : 'REGICODE',
				title : '行政区划GUID',
				width : 100,
				align : 'center',
                visible: false
			}, {
				field : 'REGINAME',
				title : '行政区划',
				width : 100,
				align : 'center'
			},{
				field : 'ENTECODE',
				title : '所属单位CODE',
				width : 100,
				align : 'center',
                visible: false
			},{
				field : 'ENTENAME',
				title : '所属单位',
				width : 100,
				align : 'center'
			},{
				field : 'MOBLEPHONENUM',
				title : '手机号码',
				width : 150,
				align : 'center'
			}, {
				field : 'TELEPHONE',
				title : '座机号码',
				width : 200,
				align : 'center'
			}] ],
			url : 'user/getUsersInformation.do',//请求地址
			searchbar: {
                rownum: 3,//搜索栏表单列数  最大支持3
                labelwidth:"100px",
                inputs: [//搜索栏表单参数
                    {
                        title: '行政区划',//表单lable显示名
                        name: 'user_region',//表单name属性
                        type: 'dsearchtree',//表单类型：目前支持 select/text
                        placeholder: '请输入行政区划',
                        searchOption:true,
                        url : 'login/findRegionTree.do',
                        checkType: 'radio',
                      	onAckCallback:function(nodes){
                      		$('#user_region').searchTree("setValue",nodes[0].id);
                      	 },
                         id:'user_region'
                    }, {
                        title: '单位',//表单lable显示名
                        name: 'user_entecode',//表单name属性
                        type: 'text',//表单类型：目前支持 select/text
                        placeholder: '请输入单位',
                        searchOption:true,
                         id:'user_entecode'
                    },{
                        title: '登录名',//表单lable显示名
                        name: 'user_loginname',//表单name属性
                        type: 'text',//表单类型：目前支持 select/text
                        placeholder: '输入登录名',
                        searchOption:true,
                        id:'user_loginname'
                    },{
                        title: '姓名',//表单lable显示名
                        name: 'user_name',//表单name属性
                        type: 'text',//表单类型：目前支持 select/text
                        placeholder: '输入姓名',
                        searchOption:true,
                        id:'user_name'
                    },{
	                   title: '手机号码',//表单lable显示名
	                   name: 'user_phone',//表单name属性
	                   type: 'text',//表单类型：目前支持 select/text
	                   placeholder: '输入手机号码',
	                   searchOption:true,
	                   id:'user_phone'
                    }
                ]
            },
			//              data:[],//前端加载数据
			pageNumber : 1,//起始页
			pageSize : 20,//页面大小
			showRefresh:false,
			uniqueId : 'guid',
			queryParams : queryParams,//查询参数方法
			paginationHAlign : 'left',//分页按钮位置  left right
			sidePagination : 'server',//分页方式(真分页)
			toolbar : '#bt',//工具栏 对应dom元素
			singleSelect : true,//单选
			clickToSelect : true//点击选中
		});
		$("body").css({
			width : $(window).width(),
			overflowX : "hidden"
		});
		 $('#regicode').searchTree({
      	    checkType:'radio',
	          	url : 'common/findRegionTree.do',
	          	onAckCallback:function(nodes){
	          	    $('#regicode').searchTree("setValue",nodes[0].id);
	          	}
       });
		 $('#entecode').searchTree({
	      	    checkType:'radio',
		          	url : 'login/findEnteTree.do',
		          	onAckCallback:function(nodes){
		          	    $('#entecode').searchTree("setValue",nodes[0].id);
		          	  }
	       });
		 $('#dregicode').searchTree({
	      	    checkType:'radio',
		        url : 'common/findRegionTree.do',
	          	onAckCallback:function(nodes){
	          	    $('#dregicode').searchTree("setValue",nodes[0].id);
	          	  }
	       });
		 $('#dentecode').searchTree({
	      	    checkType:'radio',
		        url : 'login/findEnteTree.do',
		        onAckCallback:function(nodes){
	          	    $('#dentecode').searchTree("setValue",nodes[0].id);
	          	 }
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
			limit : params.limit,
			offset : params.offset,
		}
	}


	function save() {
		var flag = $("#fm").dform("validate");
		if (flag) {
			$.dajax({
				cache : true,
				type : "POST",
				url : "user/saveUsers.do",
				data : $.param({
					"stuts" : stuts,
					"oldPassWord" : oldPassWord
				}) + '&' + $('#fm').serialize(),
				async : true,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.dalert({
							text : data.content,
							icon : 1
						});
						$('#itemModel').modal('hide');
						$("#datatable").bootstrapTable("refresh");
					} else {
						$.dalert({
							text : data.content,
							icon : 2,
						});
						$('#itemModel').modal('hide');
					}
				}
			});
		} else {
			$.dalert({text :"验证不通过，有部分数据格式错误或有必填数据未填",icon:7});
		}
	}

	var stuts = 0;
	function add() {
		stuts = -1;
		$("#loginname").attr("disabled",false);
		$("#pass_word").attr("disabled",false);
		$('#fm').dform('clear');
		$("#itemModel").modal('show');
	}
	function upload() {
		$('#upfm').dform('clear');
		$("#upitemModel").modal('show');
	}
	function edit() {
		stuts = 0;
		$('#fm').dform('clear');
		$("#loginname").attr("disabled",true);
		$("#pass_word").attr("disabled",true);
		var dataArr = $("#datatable").bootstrapTable("getSelections");
		if (dataArr.length == 0) {
			$.dalert({
				text : "未选择要修改的用户",
				icon : 7
			});
		} else {
			$("#itemModel").modal('show');
			$.dloadformdata($("#fm"), dataArr[0], "dname");
			oldPassWord=$("#password").val();
		}
	}
	function del() {
		var dataArr = $("#datatable").bootstrapTable("getSelections");
		if (dataArr.length == 0) {
			$.dalert({
				text : "未选择要删除的用户",
				icon : 7
			});
		} else {
			var guid = dataArr[0].USERID;
			$.dconfirm({
				text: "你确定要删除该用户么?", btn: ["确定", "取消"], funs: [function () {
					$.dajax({
						type : "POST",
						url : "user/deleteUsers.do",
						data : {
							id : guid
						},
						dataType : "json",
						success : function(data) {
							if (data.success) {
								$.dalert({
									text : data.content
								});
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
    function updateExcel() {
    	var flag = $("#upfm").dform("validate");
		if (!flag) {
			return $.dalert({text :"验证不通过，有部分数据格式错误或有必填数据未填",icon:7});
		}
    	$("#upfm").dform('upload',function(data){
            data=eval("("+data+")");
            if(data.success){
            	$.dalert({
					text : data.content,
					icon : 1
				});
            	$('#upitemModel').modal('hide');
				$("#datatable").bootstrapTable("refresh");
            }else{
            	$.dalert({
					text : data.content,
					icon : 2
				});
            }
	    });
    }
  	function cancel() {
        $('#itemModel').modal('hide');
    }
	function download (){
		 var elemIF = document.createElement("iframe");     
		    elemIF.src = "plugin/yh.xls";    
		    elemIF.style.display = "none";     
		    document.body.appendChild(elemIF);  
	}
	function resetPassword(){
		var dataArr = $("#datatable").bootstrapTable("getSelections");
		if (dataArr.length == 0) {
			$.dalert({
				text : "未选择要重置密码的用户",
				icon : 7
			});
		} else {
			$.dconfirm({
	            text: "你确定要重置该用户的密码么?", btn: ["确定", "取消"], funs: [function () {
	            	$.dajax({
	                    url: "user/resetPassword.do",
	                    data: {userid:dataArr[0].USERID},
	                    success: function (data) {
	                    	if (data.success) {
	                    		 $.dalert({text : data.content, icon : 1});
	                        }else{
	                        	 $.dalert({text : data.content, icon : 2});
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
	<div class="container-fluid" style="padding: 0 5px; width: 99%;">
		<table id="datatable"></table>
		<div id="bt">
			<button class="bootstrap-table-add" type="button" onclick="add()">新增</button>
			<%--<button class="bootstrap-table-add" type="button" onclick="upload()">上传</button>--%>
			<button class="bootstrap-table-edit" type="button" onclick="edit()">修改</button>
			<button class="bootstrap-table-delete" type="button" onclick="del()">删除</button>
			<button class="bootstrap-table-edit" type="button" onclick="resetPassword()">用户密码重置</button>
			<%--<button class="bootstrap-table-download" type="button" onclick="download()">用户导入模板下载</button>--%>
		</div>
	</div>
	<div class="modal fade" id="itemModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" style="display: none;">
		<div class="modal-dialog modal-lg" role="document" style="width:600px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="">用户信息</h4>
				</div>

				<div class="modal-body">
					<div class="tab-content">
						<!-- 用户信息 tab -->
						<div class="tab-pane fade in active" id="itemRecords">
							<form class="form-horizontal" role="form" method="post" id="fm">
								<div class="form-group">
									<input type="reset" name="reset" style="display: none;" /> <input
										class="from-control" id="userid" name="userid" dname="USERID"
										type="hidden" /> 
									<input class="from-control" id="password" name="password" dname="PASSWORD"type="hidden" /> 
									<div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="username" class="col-sm-4 control-label">姓名：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="username" name="username"
												dname="USERNAME" proving="notEmpty"/>
										</div>
									</div>
									
									<div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="loginname" class="col-sm-4 control-label">登录名：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="loginname"
												name="loginname" dname="LOGINNAME" proving="notEmpty" maxlength="10" onkeyup="value=value.replace(/[^(\-)\w\.\/]/ig,'')"/>
										</div>
									</div>
									<div class="col-sm-12" style="margin-bottom: 10px;">
									    <label for="password" class="col-sm-4 control-label">密码：</label>
										<div class="col-sm-6">
											<input type="password" class="form-control" id="pass_word"
												name="pass_word" dname="PASSWORD" proving="notEmpty"  maxlength="10" onkeyup="value=value.replace(/[^(\-)\w\.\/]/ig,'')" />
										</div>
									</div>
									<div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="regicode" class="col-sm-4 control-label">行政区划：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="regicode" name="regicode" dname="REGICODE" proving="notEmpty" />
										</div>
									</div>
									<div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="entecode" class="col-sm-4 control-label">所属单位：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="entecode" name="entecode" dname="ENTECODE" proving="notEmpty" />
										</div>
									</div>
									<%--<div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="divisionguid" class="col-sm-4 control-label">所属归口：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="divisionguid" name="divisionguid" dname="DIVISIONGUID" />
										</div>
									</div>--%>
									
									<!-- <div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="idcard" class="col-sm-4 control-label">身份证号：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="idcard"
												name="idcard" dname="IDCARD" proving="idcard" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
										</div>
									</div> -->
									<div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="moblephonenum" class="col-sm-4 control-label">手机号码：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="moblephonenum"
												name="moblephonenum" dname="MOBLEPHONENUM" proving="notEmpty" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
										</div>
									</div>
									<div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="telephone" class="col-sm-4 control-label">单位座机号码：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="telephone"
												name="telephone" dname="TELEPHONE"/>
										</div>
									</div>
									<!-- <div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="remark" class="col-sm-4 control-label">备注：</label>
										<div class="col-sm-8">
											<textarea class="form-control" id="remark" name="remark"
												dname="REMARK" rows="4"></textarea>
										</div>
									</div> -->
								</div>
							</form>
							<div class="well well-sm" align="right">
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
	</div>
	<div class="modal fade" id="upitemModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" style="display: none;">
		<div class="modal-dialog modal-lg" role="document" style="width:600px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" >上传用户信息</h4>
				</div>

				<div class="modal-body">
					<div class="tab-content">
						<div class="tab-pane fade in active" id="upitemRecords">
							<form class="form-horizontal" role="form" method="post" enctype="multipart/form-data" target="submitFrame" onsubmit="" action="login/uploadUserFile.do" id="upfm">
								<div class="form-group">
								   <input class="from-control" id="dentecode" name="dentecode" type="hidden" /> 
								    <div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="dregicode" class="col-sm-4 control-label">行政区划：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="dregicode" name="dregicode"  proving="notEmpty" />
										</div>
									</div>
									<%--<div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="ddivisionguid" class="col-sm-4 control-label">所属归口：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="ddivisionguid" name="ddivisionguid" />
										</div>
									</div>
									<div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="denteguid" class="col-sm-4 control-label">所属单位：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="denteguid" name="denteguid" />
										</div>
									</div>--%>
									<div class="col-sm-12" style="margin-bottom: 10px;">
								        <label class="col-sm-4 control-label" for="fileUpLoad" >选择文件</label>
								        <div class="col-sm-6">
								            <input class="form-control" type="file" id="fileUpLoad" name="fileUpLoad">
								        </div>
								    </div>
							    </div>
							</form>
							<div class="well well-sm" align="center">
								<button class="bootstrap-table-upload"
									onclick="updateExcel()">导入</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
