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
    <title>用户管理</title>
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

        var userTree;
        $(function () {
            $("body").css({width: $(window).width(), overflowX: "hidden"});
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
        var userid;
        function getUserInfo(e, treeId, treeNode) {
			userid = treeNode.id;
			$.dajax({
				data : {userid : userid},
				url : "common/getUserInfo.do",
				success : function(data) {
					$.dloadformdata($("#fm"), data.info, "dname");
				}
			});
        }
        function reload(){

        	userTree = $("#userTree").dtree({
                rootElement: false,  //是否添加根节点“全部”
                url: "common/getUserTree.do",
				checkType: "nocheck",
				//isOpen: true, //节点是否全部展开，默认为不展开
				ISLEAF: true,
                callback: {
					onClick: getUserInfo
                }
            });
        }


        function synUser() {
			$.dajax({
				cache : true,
				type : "POST",
				url : "common/updateUserInfo.do",
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.dalert({
							text : data.msg,
							icon : 1
						});
						reload();
					} else {
						$.dalert({
							text : data.msg,
							icon : 2,
						});
					}
				}
			});
        }


    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <button class="bootstrap-table-add" type="button" onclick="synUser()">更新用户</button>
    </div>

    <div class="row">
        <div class="col-md-4 demo">
            <div class="row"><div class="demo-tit">用户列表</div></div>
            <div class="row">
                <ul id="userTree" class="demo-tree"></ul>
            </div>
        </div>
        <div class="col-md-7 demo">
            <div class="row"><div class="demo-tit">用户信息</div></div>
            <div class="row">
	            <div class="tab-pane fade in active" id="itemRecords" style="margin-top:10px" >
					<form class="form-horizontal" role="form" method="post" id="fm">
						<div class="form-group">
						    <div class="col-sm-12" style="margin-bottom: 10px;">
								<div class="col-sm-3">
									<input type="hidden" class="form-control" id="userid" name="userid" dname="userid" />
								</div>
								<div class="col-sm-3">
									<input type="hidden" class="form-control" id="jczl_userid" name="jczl_userid" dname="jczl_userid"/>
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
								<div class="col-sm-5">
									<input type="hidden" class="form-control" id="parent_guid" name="parent_guid" dname="parent_guid"/>
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="loginname" class="col-sm-2 control-label">登录名：</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="loginname" name="loginname" dname="loginname" proving="notEmpty"/>
								</div>
								<label for="username" class="col-sm-2 control-label">用户名称：</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="username" name="username" dname="username" />
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
							    <label for="password" class="col-sm-2 control-label">密码：</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="password" name="password" dname="password" />
								</div>
								<label for="idcard" class="col-sm-2 control-label">身份证：</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="idcard" name="idcard" dname="idcard"/>
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="moblephonenum" class="col-sm-2 control-label">手机号：</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="moblephonenum" name="moblephonenum" dname="moblephonenum"/>
								</div>
								<label for="telephone" class="col-sm-2 control-label">座机号：</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="telephone" name="telephone" dname="telephone"/>
								</div>
							</div>
							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="enable" class="col-sm-2 control-label">启用状态：</label>
								<div class="col-sm-10">
									<select id="enable" name="enable" dname="enable" proving="notEmpty"
											class="selectpicker show-tick form-control" data-live-search="false" >
										<option value="0">停用</option>
										<option value="1">启用</option>
									</select>
								</div>
							</div>

							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="remark" class="col-sm-2 control-label">备注：</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="remark" name="remark" dname="remark" />
								</div>
							</div>
						</div>
					</form>
				</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
