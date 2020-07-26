<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <title>单位号码维护</title>
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
    <script src="bootstrap2/js/jquery.js"></script>
    <script src="bootstrap2/js/bootstrap.datanew.js"></script>
    <script type="text/javascript">

        var islock = false; // 控制 新增或编辑时点击树节点 是否加载表单数据
        var enterTree;
        var date = new Date;
        var searchyear = date.getFullYear();
        var treeid;
        $(function () {
            $("body").css({width: $(window).width(), overflowX: "hidden"});

            $("#yearform").dform({
                rownum:1,
                labelwidth: "140px",
                inputs: [
                     {
                         id:'year',
                         title: "年度",
                        type: "dateBox",
                        name: "dateBox4",
                        minView: 4,
                        format: "yyyy",
                        required:true,
                        defaultValue: searchyear,
                         onChange:function(newValue,oldValue){
                             $("#enterTree").tree("reload",{
                                 url:"pro/getAllJczlEnteTree.do",
                                 checkType: "nocheck",
                                 queryParam:{
                                     year : newValue
                                 }
                             });
                         }
                    }
                ]
            });

            $('#year').dateBox("setValue",searchyear);



            enterTree = $("#enterTree").tree({
                checkType: "nocheck", //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
                isOpen: false, //节点是否全部展开，默认为不展开
                rootElement: false,  //是否添加根节点“全部”
                ISLEAF: true,
                url: "pro/getAllJczlEnteTree.do",
                queryParam : {
                    year : $('#year').val(),
                },
                callback: {
                    clickNode: getEnterPhone
                }
            });

            //右侧表单
            $("#phoneform").dform({
                rownum:1,
                labelwidth:"100px",
                inputs: [
                    {id:"guid", name:"guid", type:"hidden"},
                    {id:"eguid", name:"eguid", type:"hidden" },
                    {id:"ename", name:"ename", type:"textBox", title:"单位名称"},
                    {id:"phoneno", name:"phoneno", type:"textBox", title:"电话",required:true}
                ]
            });
            $("#phoneform").dform("disabled");
        });
        $(document).ready(function() {
            $(document).keydown(function() {
                if (event.keyCode == 13) {
                    event.keyCode = '9';
                }
            })
        });

        function enableBtn() {
            islock = true;
            $('button[type="button"]').css('display', 'none');
            if (arguments) {
                for (var i in arguments) {
                    $('#' + arguments[i]).css('display', 'inline');
                }
            }
        }

        function resetbtn() {
            islock = false;
            $('button[type="button"]').css('display', 'inline');
            $('#cancelbtn').css('display', 'none');
            $('#savebtn').css('display', 'none');
        }

        function cancelbtn() {
            resetbtn();
            $("#phoneform").dform("disabled");
            $("#phoneform").dform("clear");
        }

        function getEnterPhone(e, treeId, treeNode) {
            treeid = treeNode.id;
            if(treeid==undefined){
                return false;
            }
            if (!islock) {
                $.dajax({
                    data : {enterguid : treeid},
                    url : "pro/findEnterPhone.do",
                    success : function(data) {
                        //加载单位号码
                        $("#guid").val(data.guid);
                        $("#eguid").val(data.enterguid);
                        $("#ename").val(data.entername);
                        $("#phoneno").val(data.phoneno);
                    }
                });
            }

        }

        function save() {
            var selectedNodes = $("#enterTree").tree("getTree").getSelectedNodes();
            $.dajax({
                data : {
                    guid : $("#guid").val(),
                    enterguid : selectedNodes[0].id,
                    entername : selectedNodes[0].name,
                    phoneno : $("#phoneno").val()
                },
                url : "pro/saveEnterPhone.do",
                success : function(data) {
                    if (data.success) {
                        $.dalert({text : data.content, icon : 1});
                    } else {
                        $.dalert({text : data.content, icon : 2});
                    }
                }

            });
            resetbtn();
            $("#phoneform").dform("disabled");
        }

        function edit() {
            var selectedNodes = $("#enterTree").tree("getTree").getSelectedNodes();
            if (selectedNodes.length == 0) {
                return $.dalert({text: '请选择要编辑的单位', icon: 2});
            } else {
                enableBtn('savebtn', 'cancelbtn');
                if($("#guid").val()==""){
                    $("#eguid").val(selectedNodes[0].id);
                    $("#ename").val(selectedNodes[0].name);
                }

                $("#phoneno").textBox("enable").removeAttr("readonly");

            }
        }


    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <button id="editbtn" class="bootstrap-table-edit" type="button" onclick="edit()">编辑</button>
        <button id="savebtn" type="submit" class="bootstrap-table-submit" onclick="save()" style="display: none">保存</button>
        <button id="cancelbtn" class="bootstrap-table-delete" type="button" onclick="cancelbtn()" style="display: none">取消</button>
    </div>

    <div class="row">
        <div class="col-md-5 demo">
            <div class="row"><div class="demo-tit">单位</div></div>
            <div class="row" style="position:absolute; height:500px; width:98%; overflow:auto">
                <form id="yearform"></form>
                <ul id="enterTree" class="demo-tree"></ul>
            </div>
        </div>
        <div class="col-md-7 demo">
            <div class="row"><div class="demo-tit">单位号码</div></div>
            <div class="row">
                <form id="phoneform"></form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
