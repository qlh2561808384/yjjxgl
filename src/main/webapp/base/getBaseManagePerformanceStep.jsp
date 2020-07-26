<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.datanew.util.ConfigureParser" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <title>绩效报表环节信息展示</title>
    <link rel="stylesheet" href="css/common.css"/>
    <script src="bootstrap2/js/jquery.js"></script>
    <script src="bootstrap2/js/bootstrap.datanew.js"></script>
    <script type="text/javascript">

        var date = new Date;
        var searchyear = date.getFullYear();
        var enteryear = searchyear;
        var userKey = parent.currkey;
        var enReport = "<%= ConfigureParser.getPropert("JXKHID")%>";

        $(function() {
            var nodenameArr = [];
            $("#datatable").dtable({
                columns : [ [ {
                    checkbox : true
                },{
                    field : 'YEAR',
                    title : '年 ',
                    width : 100,
                    align : 'center'
                }, {
                    field : 'EID',
                    title : '单位代码',
                    //width : 100,
                    align : 'center',
                    visible: false
                },{
                    field : 'GUID',
                    title : '单位guid',
                    //width : 100,
                    align : 'center',
                    visible: false
                }, {
                    field : 'ENAME',
                    title : '单位名称',
                    width : 200,
                    align : 'center'
                }, {
                    field : 'BATCH',
                    title : '批次',
                    width : 100,
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
                    width : 150,
                    align : 'center',
                    //单位录入变换颜色
                    formatter: function(value, row, index){
                        if(value=="单位上报"){
                            nodenameArr.push(index);
                        }
                        return value;
                    }
                },{
                    field : 'PHONENO',
                    title : '单位号码',
                    width : 80,
                    align : 'center',
                    //visible: false
                }] ],
                url : 'pro/getPerformanceManageStepInfo.do',//请求地址
                searchbar: {
                    rownum: 4,//搜索栏表单列数  最大支持3
                    labelwidth:"100px",
                    inputs: [//搜索栏表单参数
                        {
                            id:'search_year',
                            title: "年",
                            type: "dateBox",
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
                            type: 'dsearchtree',//表单类型：目前支持 select/text
                            placeholder: '请选择单位',
                            searchOption:true,
                            id:'enteguid',
                            checkType:'radio',
                            ISLEAF: false,
                            url : 'pro/getJczlEnteTree.do?year='+enteryear+"&userKey="+userKey,
                            onAckCallback:function(nodes){
                                if(nodes.length>0){
                                    $('#enteguid').searchTree("setValue",nodes[0].id);
                                }
                            }
                        },{
                            title: '环节',//表单lable显示名
                            type: 'dsearchtree',//表单类型：目前支持 select/text
                            placeholder: '请选择环节',
                            searchOption:true,
                            id:'nodenum',
                            checkType:'radio',
                            url : 'pro/getManagePerformanceStepcodeTree.do',
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
                //singleSelect : true,//单选
                clickToSelect : true,//点击选中
                //单位录入加颜色所写代码
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
                }
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

        function showStepDetail() {
            var dataArr = $("#datatable").bootstrapTable("getSelections");
            if (dataArr.length != 1) {
                $.dalert({
                    text : "请选择一条要查看的数据",
                    icon : 7
                });
            } else {
                $.dopen({
                    title :"环节详情展示",
                    content: '<table id="steptable"></table>',
                    area: ['1000px','550px'],
                    btn: ['取消'],
                    btn1:function(index){
                        layer.close(index);
                    }
                });

                function detailParams(params) {
                    return {
                        //如果需要后端进行分页 limit 和offset是必须参数
                        limit : params.limit,
                        offset : params.offset,
                        'year' : dataArr[0].YEAR,
                        'enterguid' : dataArr[0].GUID,
                        'batch' : dataArr[0].BATCH,
                        'userKey' : userKey
                    }
                }
                //环节详情列表
                $("#steptable").dtable({
                    height:450,
                    columns : [ [{
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
                        width : 200,
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
                    }] ],
                    url : 'pro/getManagePerformanceStepDetail.do',//请求地址
                    pageNumber : 1,//起始页
                    pageSize : 20,//页面大小
                    showRefresh:false,
                    uniqueId : 'guid',
                    queryParams : detailParams,//查询参数方法
                    paginationHAlign : 'left',//分页按钮位置  left right
                    sidePagination : 'server',//分页方式(真分页)
                    singleSelect : true,//单选
                    clickToSelect : true//点击选中
                });

            }

        }
        //复选框反选$('xxx').dtable('method',param)
        function reverse() {
            var $table = $("#datatable"),
                    tableData = $table.dtable("getData"),
                    selectData = $table.dtable("getSelections");
            $table.dtable("checkAll");
            $.each(selectData, function(k){
                var rowIndex = tableData.indexOf(selectData[k]);
                $table.dtable("uncheck", rowIndex);
            });
        }
        //发送短信
        function sendMassage() {
            var dataArr = $("#datatable").bootstrapTable("getSelections");
            if (dataArr.length == 0) {
                $.dalert({
                    text : "未选择要发送短信的数据",
                    icon : 7
                });
            } else {
                $.dajax({
                    cache : true,
                    type : "POST",
                    url : "pro/sendMassage.do",
                    /*data : {
                     "year" : dataArr[0].YEAR,
                     "eid" : dataArr[0].EID,
                     "iid" : dataArr[0].IID,
                     "batch" : dataArr[0].BATCH
                     },*/
                    data:{
                        'basedata' : JSON.stringify(dataArr)
                    },
                    async : true,
                    dataType : 'json',
                    success : function(data) {
                        if (data.success) {
                            $.dalert({
                                text : data.content,
                                icon : 1
                            });
                            $("#datatable").bootstrapTable("refresh");
                        } else {
                            $.dalert({
                                text : "提交失败",
                                icon : 2
                            });
                        }

                    }
                });
            }
        }
        /*function sendMassage(PHONENO){
         console.log(PHONENO);
         $.dajax({
         url:"pro/sendMassage.do",
         data:{
         'PHONENO':PHONENO
         },//方法里面的形参传到后台
         success:function(data){//这个data是后台数据传到前台页面显示给用户的
         $.dalert({
         text : data.content,
         icon : 7
         });
         },
         });
         }*/

    </script>
</head>
<body>
<div class="container-fluid" style="padding: 0 5px; width: 99%;">
    <table id="datatable"></table>
    <div id="bt">
        <%--<button class="bootstrap-table-review" type="button" onclick="showStepDetail()">查看环节详情</button>--%>
        <button class="bootstrap-table-edit" type="button" onclick="sendMassage()">发送短信</button>
        <button class="bootstrap-table-enable" type="button" onclick="reverse()">反选</button>
    </div>
</div>

</body>
</html>
