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
<title>发布管理</title>
<link rel="stylesheet" href="css/common.css"/>
<script src="bootstrap2/js/jquery.js"></script>
<script src="bootstrap2/js/bootstrap.datanew.js"></script>
<script type="text/javascript">
	var userKey = parent.currkey;
	var date = new Date;
    var deadlinedate ;
    var createdate;
    var time = date.getTime();
    $(function() {
		var nodenameArr = [];
		$("#datatable").dtable({
			columns : [ [ {
				checkbox : true
			}, {
				field : 'GUID',
				width : 100,
				align : 'center',
                visible: false
			}, {
				field : 'YEAR',
				title : '年',
				width : 100,
				align : 'center'
			}, {
				field : 'REPORTMODELID',
				title : '报表模板id',
				width : 100,
				align : 'center',
				visible: false
			}, {
				field : 'REPORTMODELNAME',
				title : '报表模板名称',
				width : 100,
				align : 'center'
			}, {
				field : 'BATCH',
				title : '批次',
				width : 200,
				align : 'center'
			}, {
				field : 'CREATEUSERID',
				title : '创建用户id',
				width : 100,
				align : 'center',
                visible: false
			}, {
				field : 'CREATEUSERNAME',
				title : '创建用户名称',
				width : 100,
				align : 'center'
			}, {
				field : 'CREATEDATE',
				title : '创建时间',
				width : 150,
				align : 'center',
                formatter: function (value, row, index) {
                    return changeDateFormat(value)
                }
			},{
				field : 'DEADLINEDATE',
				title : '报表提交截止时间',
				width : 150,
				align : 'center',
				formatter: function (value, row, index) {
					if(value < time){
						nodenameArr.push(index);
					}
					return changeDateFormat(value);
				}
			},{
				field : 'ISOPEN',
				title : '是否启用报表',
				width : 150,
				align : 'center',
				visible : true,
				formatter: function (value, row, index) {
					if(value=='0'){
						return "启用";
					}else if(value=='1'){
						return "禁用";
					}
				}
			}] ],
			url : 'pro/getProReleaseLog.do',//请求地址
			searchbar: {
                rownum: 1,//搜索栏表单列数  最大支持3
                labelwidth:"100px",
                inputs: [//搜索栏表单参数
					{
						title: '时间断',//表单lable显示名
						name: 'type',//表单name属性
						type: 'comboBox',//表单类型：目前支持 select/text
						placeholder: '请选择时间节点',
						searchOption:true,
						id:'CREATEDATE',
						localdata:[
								{
									id:"1",
									text:"三年以内"
								}, {
									id:"2",
									text:"三年以外"
								}
								]
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
			clickToSelect : true,//点击选中
			//绩效发布任务已过时间加颜色所写代码
			onPageChange:function(num, size){
				nodenameArr.splice(0, nodenameArr.length);
			},
            onPostBody: function(){
                $("#datatable").find("tr[data-index]").each(function(i){
                    var $tr = $(this),
                            dataIndex = $tr.data("index");
                    $.each(nodenameArr, function(k, v){
                        if(dataIndex == v){
                            $tr.css({color:"#F00"});
                            return false;
                        }
                    })
                })
            },
            onClickRow:function(row,$element,field){
			  //row包括table里面的所有数据
			    deadlinedate = changeDateFormat(row.DEADLINEDATE);
                createdate = row.CREATEDATE;
            }
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

    //转换日期格式(时间戳转换为datetime格式)
    function changeDateFormat(cellval) {
        var dateVal = cellval + "";
        if (cellval != null) {
            var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

            var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
            var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

            return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
        }
    }


	var stuts = 0;
	function add() {
		stuts = -1;
        $.dopen({
            title: "发布",
            content: '<form id="addform" />',
            area: ['500px','350px'],
            btn: ['发布', '取消'],
            btn1:function(index){
                save(index);
            },
            btn2:function(index){
                layer.close(index);
            }
        });

        $("#addform").dform({
            labelwidth:"100px",
            rownum:1,   //每行控件数目
            inputs:[
                {
                    id: "YEAR",
                    title: "年",
                    type: "dateBox",
                    name: "YEAR",
                    minView: 4,
                    required:true,
                    format: "yyyy",
                    pickerPosition: "bottom-left"
                },{
					title: '报表模型',//表单lable显示名
					name: 'reportid',//表单name属性
					type: 'dsearchtree',//表单类型：目前支持 select/text
					id:'reportid',
					checkType:'radio',
					url : 'pro/getReportModelTree.do',
					onAckCallback:function(nodes){
						$('#reportid').searchTree("setValue",nodes[0].id);
					}
				},{
                    id: "REMARKS",
                    title: "批次备注",
                    type: "textBox",
                    name: "REMARKS",
                    multiline:"true",
                    minView: 4,
                    required:true,
                    pickerPosition: "bottom-left"
                }

            ]
        });

	}

    function save(index) {
        var flag = $("#addform").dform("validate");
		/*console.log($('#reportid').searchTree("getValue"))
		console.log($('#reportid').val())*/
        if (flag) {
            if ($("#REMARKS").textBox("getValue").length >=20){
                $.dalert({
                    text:"批次备注字数不能超过20个汉字",icon:7
                });
                return false;
            }else {
                $.dajax({
                cache : true,
                type : "POST",
                url : "pro/saveBaseInfo.do",
                data : {
                    "year" : $("#YEAR").dateBox("getValue"),
					"reportid" : $('#reportid').searchTree("getValue"),
					"reportname" : $('#reportid').val(),
                    "Remarks" : $("#REMARKS").textBox("getValue"),
					"userKey" : userKey
                },
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
                            text : "发布失败,"+data.content,
                            icon : 2,
                        });
                        layer.close(index);
				}

                }
            });
        }
        }else {
            if("" == $("#YEAR").dateBox("getValue") || null == $("#YEAR").dateBox("getValue")){
                $.dalert({
                    text :"年不允许为空",icon:7
                });
            }else if ("" == $("#REMARKS").textBox("getValue") || null == $("#REMARKS").textBox("getValue")){
                $.dalert({
                    text :"批次备注不允许为空",icon:7
                });
            }
        }
    }



    function showDetail() {
        var dataArr = $("#datatable").bootstrapTable("getSelections");
        if (dataArr.length == 0) {
            $.dalert({
                text : "未选择要查看的年度数据",
                icon : 7
            });
        } else {
            $.dopen({
                title :"绩效监控基本信息",
                content: '<table id="base_table"></table>',
                area: ['1000px','500px'],
                /*btn: ['取消'],
                btn1:function(index){
                    layer.close(index);
                    $("#datatable").bootstrapTable("refresh");
                }*/
            });
            $("#base_table").dtable({
				height:450,
                columns : [ [ {
                    field : 'YEAR',
                    title : '年 ',
                    width : 100,
                    align : 'center'
                }, {
                    field : 'ENAME',
                    title : '单位名称',
                    width : 100,
                    align : 'center'
                }, {
                    field : 'IID',
                    title : '预算项目编码',
                    width : 100,
                    align : 'center',
                    visible: false
                }, {
                    field : 'INAME',
                    title : '预算项目名称',
                    width : 100,
                    align : 'center'
                }, {
                    field : 'BATCH',
                    title : '批次',
                    width : 100,
                    align : 'center'
                }, {
					field : 'STEPCODE',
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
                url : 'pro/getProBase.do',//请求地址
                queryParams : {
                    "search_year" : dataArr[0].YEAR
                },//查询参数方法
                pagination: false,
                showRefresh : false,
                sidePagination : 'server',//分页方式(真分页)
                singleSelect : true,//单选
                clickToSelect : true//点击选中
            });
        }
    }

    function save1(index){
//    	debugger;
		var flag = $("#addform1").dform("validate");
		if (flag) {
			$.dajax({
				cache : true,
				type : "POST",
				url : "pro/saveReleaseLog.do",
				data : $.param({

				}) + '&' + $('#addform1').serialize(),
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
			$.dalert({
				text :"验证不通过，有部分数据格式错误或有必填数据未填",
				icon:7
			});
		}
	}
	function Update(){
//		debugger;
		stuts = -1;
		var dataArr = $("#datatable").bootstrapTable("getSelections");
		if(dataArr.length == 0){
			$.dalert({
				text : "未选择要修改的发布记录",
				icon : 7
			});
			return;
		}
		$.dopen({
			title: "发布记录信息",
			content: '<form id="addform1" />',
			area: ['600px','350px'],
			btn: ['保存', '取消'],
			btn1:function(index){
				save1(index);
			},
			btn2:function(index){
				layer.close(index);
			},
		});
		$("#addform1").dform({
			labelwidth:"200px",
			rownum:1,
			inputs :[
				{
					title: "报表提交截止时间",
					proving: "notEmpty",
					name: "DEADLINEDATE",
					type: "dateBox",
					minView:0,
					startView:4,
					format:"yyyy-mm-dd hh:ii:ss",
					width:30,
					id:"DEADLINEDATE",
				},{
					title: '是否启用报表',
					type: 'comboBox',
					name: 'ISOPEN',
					id: 'ISOPEN',
					selected: 1,
					textField: 'comboTxt',
					valueField: 'comboId',
					required: true,
					localdata: [{
						comboId: 0,
						comboTxt: "启用绩效"
					}, {
						comboId: 1,
						comboTxt: "关闭绩效"
					}]
				},{
					title: '报表模型',//表单lable显示名
					name: 'reportid',//表单name属性
					type: 'dsearchtree',//表单类型：目前支持 select/text
					id:'reportid',
					checkType:'radio',
					url : 'pro/getReportModelTree.do',
					onAckCallback:function(nodes){
						$('#reportid').searchTree("setValue",nodes[0].id);
					}
				}
			]
		});
        $("#DEADLINEDATE").dateBox("setValue",deadlinedate);
	}

	function del(){
		debugger;
		var dataArr = $("#datatable").bootstrapTable("getSelections");
		if (dataArr.length == 0) {
			$.dalert({
				text : "未选择要删除的模型",
				icon : 7
			});
		}else {
			$.dconfirm({
				text: "你确定要删除该绩效表吗?", btn: ["确定", "取消"], funs: [function () {
					$.dajax({
						type : "POST",
						url : "pro/deleteReleaseLog.do",
						data : {
							reportid : dataArr[0].REPORTMODELID,
							year : dataArr[0].YEAR,
							batch : dataArr[0].BATCH
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

/*	function hid(){
//		debugger;
        $.dajax({
            cache : true,
            type : "POST",
            url : "pro/hidden.do?flag="+true,
            data : {
                seconds :time
            },
            async : true,
            dataType : 'json',
            success : function(da) {
				console.log(da);
				//alert(data);
//				$("#datatable").bootstrapTable("refresh");
//				$("#datatable").dtable("removeAll");
				$("#datatable").dtable("load",da);
//				console.log(da);
//				$("#datatable").inlineBox("init");
			}
        });
	}*/
/*	function show(){
		$.dajax({
			cache : true,
			type : "POST",
			url : "pro/hidden.do?flag="+false,
			data : {
				seconds :time
			},
			async : true,
			dataType : 'json',
			success : function(da) {
				if(da.length==0){
					$.dalert({
						text : "没有发布三年以上的报表",
						icon : 7
					});
				}else {
					$.dalert({
						text : "操作成功",
						icon : 7
					});
				}
				//alert(data);
				$("#datatable").dtable("load",da);
				$("#datatable").inlineBox("init");
			}
		});
	}*/

</script>
</head>
<body>
	<div class="container-fluid" style="padding: 0 5px; width: 99%;">
		<table id="datatable"></table>
		<div id="bt">
			<button class="bootstrap-table-add" type="button" onclick="add()">新增</button>
			<button class="bootstrap-table-edit" type="button" onclick="Update()">修改</button>
			<button class="bootstrap-table-delete" type="button" onclick="del()">删除</button>
			<%--<button class="bootstrap-table-edit" type="button" onclick="hid()">隐藏三年以上的报表</button>
			<button class="bootstrap-table-edit" type="button" onclick="show()">显示三年以上的报表</button>--%>
			<%--<button class="bootstrap-table-review" type="button" onclick="showDetail()">查看明细</button>--%>
		</div>
	</div>

</body>
</html>
