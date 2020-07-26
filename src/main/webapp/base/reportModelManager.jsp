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
<title>报表模型管理</title>
<link rel="stylesheet" href="css/common.css"/>
<script src="bootstrap2/js/jquery.js"></script>
<script src="bootstrap2/js/bootstrap.datanew.js"></script>
<script type="text/javascript">
	$(function() {
		$("#datatable").dtable({
			columns : [ [ {
				checkbox : true
			}, {
				field : 'GUID',
				title : 'guid ',
				visible: false
			}, {
				field : 'REPORTCODE',
				title : '报表编码',
				width : 100,
				align : 'center'
			}, {
				field : 'REPORTNAME',
				title : '报表名称',
				width : 150,
				align : 'center'
			}, {
				field : 'REPORTID',
				title : '报表id（HAPPYSERVER）',
				width : 150,
				align : 'center'
			},{
				field : 'ISEDIT',
				title : '是否允许审核环节修改',
				width : 100,
				align : 'center',
				formatter: function (value, row, index) {
					if(value=='1'){
						return "是";
					}else{
						return "否";
					}
				}
			}, {
				field : 'PROCESSID',
				title : '流程id',
				width : 100,
				align : 'center',
				visible: false
			}, {
				field : 'PROCESSNAME',
				title : '使用流程名称',
				width : 100,
				align : 'center'
			}] ],
			url : 'pro/getReportModel.do',//请求地址
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


	function save(index) {
		var flag = $("#addform").dform("validate");
		if (flag) {
			$.dajax({
				cache : true,
				type : "POST",
				url : "pro/saveReportModel.do",
				data : $.param({

				}) + '&' + $('#addform').serialize(),
				async : true,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.dalert({
							text : data.content,
							icon : 1
						});
						layer.close(index);
						$("#datatable").bootstrapTable("refresh");
					} else {
						$.dalert({
							text : data.content,
							icon : 2,
						});
						layer.close(index);
					}
				}
			});
		} else {
			$.dalert({text :"验证不通过，有部分数据格式错误或有必填数据未填",icon:7});
		}
	}

	function addOrUpdate(type) {
		var dataArr = $("#datatable").bootstrapTable("getSelections");
		if(type!=-1){//修改
			if (dataArr.length == 0) {
				$.dalert({
					text : "未选择要修改的模型",
					icon : 7
				});
				return;
			}
		}
		$.dopen({
			title: "报表模板信息",
			content: '<form id="addform" />',
			area: ['600px','350px'],
			btn: ['保存', '取消'],
			btn1:function(index){
				save(index);
			},
			btn2:function(index){
				layer.close(index);
			}
		});

		$("#addform").dform({
			labelwidth:"200px",
			rownum:1,   //每行控件数目
			inputs:[
				{
					title: "GUID",
					name: "GUID",
					type: "hidden"
				},
				{
					title: "报表编码",
					id: "REPORTCODE",
					name: "REPORTCODE",
					type: "text",
					required:true
				},
				{
					title: "报表名称",
					id: "REPORTNAME",
					name: "REPORTNAME",
					type: "text",
					required:true
				},
				{
					title: "报表id（HAPPYSERVER）",
					id: "REPORTID",
					name: "REPORTID",
					type: "text",
					required:true
				},{
					title: '是否允许审核环节修改',
					type: 'comboBox',
					name: 'ISEDIT',
					id: 'ISEDIT',
					selected: 1,
					textField: 'comboTxt',
					valueField: 'comboId',
					required: true,
					localdata: [{comboId: 0, comboTxt: "否"}, {comboId: 1, comboTxt: "是"}]

				},{
					title: '使用流程',//表单lable显示名
					name: 'PROCESSID',//表单name属性
					type: 'dsearchtree',//表单类型：目前支持 select/text
					id:'processid',
					dname: "PROCESSID",
					checkType:'radio',
					url : 'pro/getProcessInfos.do',
					onAckCallback:function(nodes){
						$('#processid').searchTree("setValue",nodes[0].id);
					}
				}

			]
		});

		if(type!=-1){
			$.dloadformdata($("#addform"), dataArr[0], "dname");

		}

	}

	function del() {
		var dataArr = $("#datatable").bootstrapTable("getSelections");
		if (dataArr.length == 0) {
			$.dalert({
				text : "未选择要删除的模型",
				icon : 7
			});
		} else {
			$.dconfirm({
				text: "你确定要删除该模型吗?", btn: ["确定", "取消"], funs: [function () {
					$.dajax({
						type : "POST",
						url : "pro/deleteReportModel.do",
						data : {
							guid : dataArr[0].GUID
						},
						dataType : "json",
						success : function(data) {
							if (data.success) {
								$.dalert({
									text : data.content
								});
								$("#datatable").bootstrapTable("refresh");
							}else{
								$.dalert({
									text : "失败，"+data.content,
									icon : 2,
								});
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
			<button class="bootstrap-table-add" type="button" onclick="addOrUpdate(-1)">新增</button>
			<button class="bootstrap-table-edit" type="button" onclick="addOrUpdate(0)">修改</button>
			<button class="bootstrap-table-delete" type="button" onclick="del()">删除</button>
		</div>
	</div>
</body>
</html>
