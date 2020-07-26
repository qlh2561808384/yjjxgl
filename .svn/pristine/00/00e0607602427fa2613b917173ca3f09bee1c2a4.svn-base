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
<script language=javascript src="js/bdd.js"></script>
<script src="bootstrap/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
<link href="bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<script type="text/javascript">
    var now = new Date();
    var year = now.getFullYear();
    var month =(now.getMonth() + 1).toString();
    var day = (now.getDate()).toString();
    var cudate =  year+"-"+month+"-"+day;
    var businessno = "";
    $(function () {
        $("body").css({
            width: $(window).width(),
            overflowX: "hidden"
        });
        var zNodes = [
                      {id: "0", pId: null, name: "未处理"},
                      {id: "1", pId: null, name: "已处理"}
                  ];
        $("#datatable").dtable({
			columns : [ [ {
				checkbox : true
			}, {
				field : 'guid',
				title : 'guid',
				width : 100,
				align : 'center',
                visible: false
			}, {
				field : 'businessno',
				title : '批次号 ',
				width : 100,
				align : 'center'
			}, {
				field : 'isrecord',
				title : '是否处理',
				width : 100,
				align : 'center' ,
				formatter: function(value) {
					if(value=='1'){
						return '是';
					}else if(value=='0'){
						return '否';
					}
				}
			},  {
				field : 'businessdate',
				title : '创建时间',
				width : 100,
				align : 'center',
				formatter:function(value){
                    var indate = new Date(value).toLocaleString().replace(/:\d{1,2}$/,' ');
                    return indate.substring(0,indate.indexOf(' ')).replace(/年|月/g, "-").replace(/日/g, " ");
                }
			},  {
				field : 'remark',
				title : '备注',
				width : 150,
				align : 'center'
			}] ],
			searchbar: {
                rownum: 3,//搜索栏表单列数  最大支持3
                labelwidth:"100px",
                inputs: [//搜索栏表单参数
                         {
                         	title: '起始日期',//表单lable显示名
                             name: 'startdate',//表单name属性
                             type: 'text',//表单类型：目前支持 select/text
                             placeholder: '输入起始日期'//text提示文字
                         }, {
                         	title: '结束日期',//表单lable显示名
                             name: 'enddate',//表单name属性
                             type: 'text',//表单类型：目前支持 select/text
                             placeholder: '输入结束日期'//text提示文字
                         },{
	                         title: '状态',//表单lable显示名
	                         name: 'isrecord',//表单name属性
	                         type: 'dsearchtree',//表单类型：目前支持 select/text
	                         placeholder: '请输入处理状态',
	                         searchOption:true,
	                         localdata : zNodes,
	                         checkType: 'radio',
	                       	onAckCallback:function(nodes){
	                       	    $('#isrecord').searchTree("setValue",nodes[0].id);
	                       	 }
                     }
                ]
            },
			url : 'report/getExcelInfo.do',//请求地址
			//              data:[],//前端加载数据
			pageNumber : 1,//起始页
			pageSize : 20,//页面大小
			showRefresh:false,
			uniqueId : 'guid',
			queryParams : queryParams,//查询参数方法
			paginationHAlign : 'left',//分页按钮位置  left right
			sidePagination : 'server',//分页方式(真分页)
			toolbar : '#bt',//工具栏 对应dom元素
			clickToSelect : false,
			onDblClickRow : function (row, $element) {
				window.parent.businessno = row.businessno;
		    	 var url="<%=basePath%>";
		  	     url = url+"/base/excelMxManage.jsp";
		    	$.dopen({
			    	  parent:true,
			    	  type: 2,
			    	  title :"导入明细查询",
			    	  area: ['100%', '100%'],
			    	  fix: false, //不固定
			    	  maxmin: true,
			    	  content: url
			    	});
            }
		});
        $('#startdate,#enddate').datetimepicker({
          	 language: 'zh-CN',//显示中文
          	 format: 'yyyy-mm-dd',//显示格式
          	 minView: "month",//设置只显示到月份
          	 initialDate: cudate,//初始化当前日期
          	 autoclose: true,//选中自动关闭
          	 todayBtn: true//显示今日按钮
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
   function searchFile(){
	   $('#dfm').dform('clear');
		$('#itemModel').modal('show');
   }
   function updateFile(){
   	var flag = $("#dfm").dform("validate");
		if (!flag) {
			return $.dalert({text :"验证不通过，有部分数据格式错误或有必填数据未填",icon:7});
		}
   	$("#dfm").dform('upload',function(data){
           data=eval("("+data+")");
           if(data.success){
           	$.dalert({
					text : data.content,
					icon : 1
				});
           	$('#itemModel').modal('hide');
			$("#datatable").bootstrapTable("refresh");
           }else{
           	$.dalert({
					text : data.content,
					icon : 2
				});
           }
	    });
   }
   function manageFile(){
	   var dataArr = $("#datatable").bootstrapTable("getSelections");
		if (dataArr.length == 0) {
			$.dalert({
				text : "未选择要处理的数据",
				icon : 7
			});
		} else {
           $.dconfirm({
               text: "确认处理该笔数据么？", btn: ["确定", "取消"], funs: [function () {
                   $.dajax({
                       url: "report/infoRecord.do",
                       data: { businessno: dataArr[0].businessno },
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
   function deleteExcelFile(){
	   var dataArr = $("#datatable").bootstrapTable("getSelections");
		if (dataArr.length == 0) {
			$.dalert({
				text : "未选择要删除的数据",
				icon : 7
			});
		} else {
           $.dconfirm({
               text: "确认删除该笔数据么？", btn: ["确定", "取消"], funs: [function () {
                   $.dajax({
                       url: "report/deleteExcelFile.do",
                       data: { businessno: dataArr[0].businessno },
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
</script>
</head>
<body>
	<div class="container-fluid" style="padding: 0 5px; width: 99%;">
	    <table id="datatable"></table>
		<div id="bt">
		     <button class="bootstrap-table-upload" type="button" onclick="searchFile()">上传</button>
		     <button class="bootstrap-table-submit" type="button" onclick="manageFile()">处理数据</button>
		     <button class="bootstrap-table-submit" type="button" onclick="deleteExcelFile()">删除数据</button>
		</div>
	</div>
	<div class="modal fade" id="itemModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document"
			style="width: 600px; margin:0 auto;">
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
							<form class="form-horizontal" role="form" method="post" enctype="multipart/form-data" target="submitFrame" onsubmit="" action="login/excelFile.do"id="dfm">
								<div class="form-group">
									<div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="fileUpLoad" class="col-sm-4 control-label">上传excel文件</label>
										<div class="col-sm-6">
									        <input type="file" id="fileUpLoad" name="fileUpLoad" class="form-control"  proving="notEmpty">
									    </div>
									    <label class="col-sm-4 control-label">说明：</label>
	                                    <div class="col-sm-8">
	                                        <textarea class="form-control" id="remark" name="remark" rows="4"></textarea>
	                                    </div>
									</div>
								</div>
							</form>
							<div class="well well-sm" align="center">
								<button class="bootstrap-table-upload"
									onclick="updateFile()">确定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
