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
    <title>绩效管理工作考核表</title>
    <link rel="stylesheet" href="css/common.css"/>
    <script src="bootstrap2/js/jquery.js"></script>
    <script src="bootstrap2/js/bootstrap.datanew.js"></script>
    <script type="text/javascript">

        var date = new Date;
        var time = date.getTime();
        var searchyear = date.getFullYear();
        var enteryear = searchyear;
        var userKey = parent.currkey;
        var enReport = "<%= ConfigureParser.getPropert("JXKHID")%>";

        $(function() {
            $("#datatable").dtable({
                columns : [ [ {
                    checkbox : true
                },{
                    field : 'YEAR',
                    title : '年 ',
                    width : 80,
                    align : 'center'
                },{
                    field : 'EID',
                    title : '单位代码',
                    width : 100,
                    align : 'center',
                    visible: false
                },{
                    field : 'ENAME',
                    title : '单位名称',
                    width : 150,
                    align : 'center'
                },{
                    field : 'STEPCODE',
                    title : '当前环节编码',
                    width : 100,
                    align : 'center',
                    visible: false
                },{
                    field : 'BATCH',
                    title : '批次',
                    width : 150,
                    align : 'center'
                }, {
                    field : 'STEPCODE',
                    title : '当前环节',
                    width : 100,
                    align : 'center',
                    visible: false
                }, {
                    field : 'NODENAME',
                    title : '当前环节',
                    width : 100,
                    align : 'center'
                }, {
                    field : 'DATETIME',
                    title : '填写日期',
                    width : 100,
                    align : 'center',
                    visible: false
                }, {
                    field : 'TBR',
                    title : '填报人',
                    width : 100,
                    align : 'center'
                }, {
                    field : 'TBR_LXFS',
                    title : '填报人联系方式',
                    width : 150,
                    align : 'center'
                },{
                    field : 'ISOPEN',
                    title : '是否启用绩效',
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
                },{
                    field : 'DEADLINEDATE',
                    title : '截止时间',
                    width : 150,
                    align : 'center',
                    visible : true,
                    formatter: function (value, row, index) {
                        return changeDateFormat(value)
                    }
                }] ],
                url : 'pro/getBaseManagePerformance.do',//请求地址
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
                                //console.log(newValue+","+oldValue);
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
                            url : 'pro/getJczlEnteTree.do?year='+enteryear+'&userKey='+userKey,
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
                            url : 'pro/getManagePerformanceStepcodeTree.do?userKey='+userKey,
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
                singleSelect : false,//单选
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

        function editReport() {
            var dataArr = $("#datatable").bootstrapTable("getSelections");
            if (dataArr.length != 1) {
                $.dalert({
                    text : "请选择一条要查看或编辑的数据",
                    icon : 7
                });
            } else{
                if(dataArr[0].ISOPEN == 0 && time < dataArr[0].DEADLINEDATE){
                    var bburl;
                    var url = "<%=ConfigureParser.getPropert("JXGLBBURL")%>";
                    var edit_filename = "<%=ConfigureParser.getPropert("JXKHID")%>";
                    var read_filename = "<%=ConfigureParser.getPropert("READ_JXKHID")%>";
                    var targetVolume = "<%=ConfigureParser.getPropert("TARGETVOLUME")%>";
                    if(dataArr[0].STEPCODE==1){
                        bburl = "http://"+url+"/HappyServer/hrServlet?fileName="+edit_filename+"&targetVolume="+targetVolume
                                + "&authId=anonymous_admin&variants=year="+dataArr[0].YEAR
                                +";eid="+dataArr[0].EID+";batch="+dataArr[0].BATCH+";stepcode="+dataArr[0].STEPCODE+";";
                    }else{
                        bburl = "http://"+url+"/HappyServer/hrServlet?fileName="+read_filename+"&targetVolume="+targetVolume
                                + "&authId=anonymous_admin&variants=year="+dataArr[0].YEAR
                                +";eid="+dataArr[0].EID+";batch="+dataArr[0].BATCH+";stepcode="+dataArr[0].STEPCODE+";";
                    }
                    /* +";iid="+dataArr[0].IID*/
                    $.dopen({
                        parent:true,
                        type: 2,
                        title :"报表详情",
                        area: ['1100px', '600px'],
                        //data:{abc:'测试',eFg:'123'},
                        content: bburl,
                        end: function(){//层被彻底关闭后执行的回调函数。
                            $("#datatable").bootstrapTable("refresh");
                        }
                    });
                }else if(dataArr[0].ISOPEN == 0 && time > dataArr[0].DEADLINEDATE){
                    $.dalert({
                        text : "已超过填报截止时间不允许查看或编辑",
                        icon : 7
                    });
                }else if(dataArr[0].ISOPEN == 1) {
                    $.dalert({
                        text : "未开启绩效考核表填报不允许查看或编辑",
                        icon : 7
                    });
                }
            }
        }

        function submitBaseInfo() {
            var dataArr = $("#datatable").bootstrapTable("getSelections");
            if (dataArr.length == 0) {
                $.dalert({
                    text : "未选择要提交的数据",
                    icon : 7
                });
            } else {
                if(dataArr[0].ISOPEN == 0 && time < dataArr[0].DEADLINEDATE) {
                    if(dataArr[0].STEPCODE == 1){
                        if(null == dataArr[0].TBR || null == dataArr[0].TBR_LXFS || null == dataArr[0].ZF_P){
                            $.dalert({
                                text:"填写完整报表之后才能提交哦！",
                                icon:7
                            });
                        }else {
                            $.dajax({
                                cache: true,
                                type: "POST",
                                url: "pro/submitBaseManage.do",
                                /*data : {
                                 "year" : dataArr[0].YEAR,
                                 "eid" : dataArr[0].EID,
                                 "batch" : dataArr[0].BATCH
                                 },*/
                                data:{
                                    'basedata': JSON.stringify(dataArr)
                                },
                                async : true,
                                dataType : 'json', success : function(data) {
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
                    } else {
                        $.dajax({
                            cache: true,
                            type: "POST",
                            url: "pro/submitBaseManage.do",
                            /*data : {
                             "year" : dataArr[0].YEAR,
                             "eid" : dataArr[0].EID,
                             "batch" : dataArr[0].BATCH
                             },*/
                            data:{
                                'basedata': JSON.stringify(dataArr)
                            },
                            async : true,
                            dataType : 'json',
                            success : function(data) {
                                if (data
                                                .success) {
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
                } else if(dataArr[0].ISOPEN == 0 && time > dataArr[0].DEADLINEDATE){
                    $.dalert({
                        text : "已超过填报截止时间不允许提交",
                        icon : 7
                    });
                }else if(dataArr[0].ISOPEN == 1) {
                    $.dalert({
                        text : "未开启绩效考核表填报不允许提交",
                        icon : 7
                    });
                }
            }
        }

        function backBaseInfo() {
            var dataArr = $("#datatable").bootstrapTable("getSelections");
            if (dataArr.length == 0) {
                $.dalert({
                    text : "未选择要退回的数据",
                    icon : 7
                });
            }else if(dataArr.length != 1){
                $.dalert({
                    text:"请选择一条要退回的数据",
                    icon : 7
                });
            }  else {
                if(dataArr[0].STEPCODE == 1){
                    $.dalert({
                        text : "当前为第一步，无法退回",
                        icon : 7
                    });
                    return;
                }
                $.dajax({
                    cache : true,
                    type : "POST",
                    url : "pro/backBaseManage.do",
                    /*data : {
                     "year" : dataArr[0].YEAR,
                     "eid" : dataArr[0].EID,
                     "iid" : dataArr[0].IID,
                     "batch" : dataArr[0].BATCH,
                     "stepcode" : dataArr[0].STEPCODE
                     },*/
                    data:{
                        'baseinfodata' : JSON.stringify(dataArr)
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
                                text : "退回失败",
                                icon : 2
                            });
                        }

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
        <button class="bootstrap-table-edit" type="button" onclick="editReport()">查看或编辑</button>
        <button class="bootstrap-table-submit" type="button" onclick="submitBaseInfo()">提交</button>
        <button class="bootstrap-table-cancel" type="button" onclick="backBaseInfo()">退回</button>
    </div>
</div>

</body>
</html>
