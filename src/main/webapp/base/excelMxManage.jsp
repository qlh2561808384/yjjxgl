<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.datanew.model.BaseOperator"%>
<%@ page language="java" import="com.datanew.util.StaticData"%>
<%@page import="com.datanew.util.ConfigureParser"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    BaseOperator operator = (BaseOperator) session.getAttribute(StaticData.LOGINUSER);
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>报表汇总查询</title>
<style>
body,div {
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

body div.container-fluid {
	border: 1px solid #DDDDDD;
	background: #F5F5F5;
	box-sizing: border-box;
	padding: 20px 15px 10px 25px;
	margin: 0 10px 0 15px;
}

.demo .demo-tit {
	margin: 10px 10px 0 0;
	border-radius: 4px 4px 0 0;
}

.demo .demo-tree {
	margin-right: 10px;
}
</style>
<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.datanew.js"></script>
<script type="text/javascript">
$(function () {
    $("body").css({
        width: $(window).width(),
        overflowX: "hidden"
    });
    $("#datatable").dtable({
		columns : [ [ {
			field : 'pc',
			title : '批次号',
			align : 'center'
		}, {
			field : 'year',
			title : '年份',
			align : 'center'
		}, {
			field : 'dwcode',
			title : '单位代码',
			align : 'center'
		}, {
			field : 'dwname',
			title : '单位名称',
			align : 'center'
		}, {
			field : 'billno',
			title : '支付单号',
			align : 'center'
		}, {
			field : 'fcode',
			title : '功能科目代码',
			align : 'center'
		}, {
			field : 'fname',
			title : '功能科目名称',
			align : 'center'
		}, {
			field : 'ecode',
			title : '经济科目代码',
			align : 'center'
		}, {
			field : 'ename',
			title : '经济科目名称',
			align : 'center'
		}, {
			field : 'ecode',
			title : '资金来源名称',
			align : 'center'
		}, {
			field : 'ename',
			title : '金额',
			align : 'center'
		}] ],
		url : 'report/getExcelMxInfo.do',//请求地址
		//              data:[],//前端加载数据
		pageNumber : 1,//起始页
		pageSize : 20,//页面大小
		showRefresh:false,
		queryParams : queryFile,//查询参数方法
		paginationHAlign : 'left',//分页按钮位置  left right
		sidePagination : 'server',//分页方式(真分页)
		clickToSelect : true
	});
});
function queryFile(params) {
	return {
		//如果需要后端进行分页 limit 和offset是必须参数
		guid : window.parent.businessno,
		limit : params.limit,
		offset : params.offset,
	}
}
</script>
</head>
<body>
	<div class="container-fluid" style="padding: 0 5px; width: 99%;">
	    <table id="datatable"></table>
	</div>
</body>
</html>
