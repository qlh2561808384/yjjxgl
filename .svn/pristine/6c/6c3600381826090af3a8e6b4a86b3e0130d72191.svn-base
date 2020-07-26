<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.datanew.util.ConfigureParser"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>绩效监控基本信息展示</title>
<link rel="stylesheet" href="css/common.css"/>
<script src="bootstrap2/js/jquery.js"></script>
<script src="bootstrap2/js/bootstrap.datanew.js"></script>
<script type="text/javascript">

	var date = new Date;
	var searchyear = date.getFullYear();
	var enteryear = searchyear;
	var userKey = parent.currkey;
	var enReport = "<%= ConfigureParser.getPropert("JXGZID")%>";
	$(function() {
		$("#datatable").dtable({
			columns : [ [ {
				checkbox : true
			},{
				field : 'YEAR',
				title : '年 ',
				width : 80,
				align : 'center'
			}, {
				field : 'EID',
				title : '单位代码',
				width : 100,
				align : 'center',
				visible: false
			}, {
				field : 'ENAME',
				title : '单位名称',
				width : 150,
				align : 'center'
			},{
				field : 'IID',
				title : '预算项目编码',
				width : 100,
				align : 'center',
				visible: false
			},{
				field : 'INAME',
				title : '预算项目名称',
				width : 150,
				align : 'center'
			}, {
				field : 'BATCH',
				title : '批次',
				width : 80,
				align : 'center'
			}, {
				field : 'STEPCODE',
				title : '当前环节编码',
				width : 100,
				align : 'center',
				visible: false
			}, {
				field : 'NODENAME',
				title : '当前环节',
				width : 100,
				align : 'center'
			}, {
				field : 'CDATE',
				title : '监控开始时间',
				width : 100,
				align : 'center'
			}, {
				field : 'EDATE',
				title : '监控结束时间',
				width : 100,
				align : 'center'
			}, {
				field : 'IDATE',
				title : '填报日期',
				width : 100,
				align : 'center'
			}, {
				field : 'FIRSTMONEY',
				title : '年初预算',
				width : 100,
				align : 'center'
			}, {
				field : 'ADJUSTMONEY',
				title : '指标调整',
				width : 100,
				align : 'center'
			}, {
				field : 'REALMONEY',
				title : '实际到位资金',
				width : 100,
				align : 'center'
			}, {
				field : 'EXPENDINGMONEY',
				title : '实际支出',
				width : 100,
				align : 'center'
			}, {
				field : 'INPUTER',
				title : '填报人',
				width : 100,
				align : 'center'
			}, {
				field : 'CONTACT',
				title : '单位联系方式',
				width : 100,
				align : 'center'
			}, {
				field : 'STATUS',
				title : '状态',
				width : 100,
				align : 'center',
				formatter: function (value, row, index) {
					if(value=='1'){
						return "正常";
					}else{
						return "作废";
					}
				}
			}, {
				field : 'DETAIL',
				title : '实际支出明细情况',
				width : 100,
				align : 'center'
			}] ],
			url : 'pro/getProBaseAllStep.do',//请求地址
			searchbar: {
				 rownum: 4,//搜索栏表单列数  最大支持3
				 labelwidth:"100px",
				 inputs: [//搜索栏表单参数
					 {
						 id:'search_year',
						 title: "年",
						 type: "dateBox",
						 //name: 'search_year',
						 minView: 4,
						 format: "yyyy",
						 placeholder: '请输入年份',
						 searchOption:true,
						 required:true,
						 defaultvalue : searchyear,
						 onChange:function(newValue,oldValue){
							 console.log(newValue+","+oldValue);
							 $("#enteguid").searchTree("reload",{
							 	url:"pro/getJczlEnteTree.do",
								 queryParam:{
									 year : newValue,
									 userKey : userKey
								 }
							 });


						 }
					 },{
						 title: '单位',//表单lable显示名
						 //name: 'enteguid',//表单name属性
						 type: 'dsearchtree',//表单类型：目前支持 select/text
						 placeholder: '请选择单位',
						 searchOption:true,
						 id:'enteguid',
						 checkType:'radio',
						 url : 'pro/getJczlEnteTree.do?year='+enteryear+"&userKey="+userKey,
						 onAckCallback:function(nodes){
							 if(nodes.length>0){
								 $('#enteguid').searchTree("setValue",nodes[0].id);
							 }
						 }
					 },{
						 title: '环节',//表单lable显示名
						 //name: 'nodenum',//表单name属性
						 type: 'dsearchtree',//表单类型：目前支持 select/text
						 placeholder: '请选择环节',
						 searchOption:true,
						 id:'nodenum',
						 checkType:'radio',
						 ISLEAF: false,
						 url : 'pro/getStepcodeTree.do',
						 onAckCallback:function(nodes){
							 if(nodes.length>0){
								 $('#nodenum').searchTree("setValue",nodes[0].id);
							 }
						 }
					 },{
						 title: '批次',//表单lable显示名
						 type: 'dsearchtree',//表单类型：目前支持 select/text
						 placeholder: '请选择批次号',
						 searchOption:true,
						 id:'batch',
						 checkType:'radio',
						 url : 'pro/getDateTree.do?reportid='+enReport+'',
						 onAckCallback:function(nodes){
							 if(nodes.length>0){
								 $('#batch').searchTree("setValue",nodes[0].id);
							 }
						 }
					 }
				 ]
			 },
			pageNumber : 1,//起始页
			pageSize : 20,//页面大小
			showRefresh:false,
			resizable :true,
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

		$('#search_year').dateBox("setValue",searchyear);

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
			search_year : $('#search_year').val(),
			enterguid : $('#enteguid').searchTree("getValue"),
			nodenum : $('#nodenum').searchTree("getValue"),
			batch : $('#batch').val(),
			userKey : userKey

		}
	}


	function readReport() {
		var dataArr = $("#datatable").bootstrapTable("getSelections");
		if (dataArr.length == 0) {
			$.dalert({
				text : "未选择要查看的数据",
				icon : 7
			});
		} else {
			var url = "<%=ConfigureParser.getPropert("JXGLBBURL")%>";
			var read_filename = "<%=ConfigureParser.getPropert("READ_JXGZID")%>";
			var targetVolume = "<%=ConfigureParser.getPropert("TARGETVOLUME")%>";
			var bburl="http://"+url+"/HappyServer/hrServlet?fileName="+read_filename+"&targetVolume="+targetVolume
					+ "&authId=anonymous_admin&variants=year="+dataArr[0].YEAR
					+";eid="+dataArr[0].EID+";iid="+dataArr[0].IID+";batch="+dataArr[0].BATCH+";stepcode="+dataArr[0].STEPCODE+";";
			$.dopen({
				parent:true,
				type: 2,
				title :"报表查看",
				area: ['1100px', '600px'],
				content: bburl,
				end: function(){//层被彻底关闭后执行的回调函数。
					$("#datatable").bootstrapTable("refresh");
				}
			});
		}

	}







</script>
</head>
<body>
	<div class="container-fluid" style="padding: 0 5px; width: 99%;">
		<table id="datatable"></table>
		<div id="bt">
			<button class="bootstrap-table-edit" type="button" onclick="readReport()">查看报表</button>
		</div>
	</div>

</body>
</html>
