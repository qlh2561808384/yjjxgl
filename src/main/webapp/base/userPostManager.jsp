<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <title>用户授权</title>
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

        var postTree;
        var userTree
        $(function () {
            $("body").css({width: $(window).width(), overflowX: "hidden"});

            userTree = $("#userTree").dtree({
//                localdata: zNodes,
                checkType: "radio", //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
                isOpen: false, //节点是否全部展开，默认为不展开
                rootElement: false,  //是否添加根节点“全部”
                ISLEAF: true,
                url: "common/getUserTree.do",
                callback: {
                    onCheck: getPostInfo
                }
            });

            postTree = $("#postTree").dtree({
//                localdata: zNodes,
                checkType: "checkbox", //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
                isOpen: true, //节点是否全部展开，默认为不展开
                ISLEAF: true,
                rootElement: false,  //是否添加根节点“全部”
                url: "common/findPostTree.do"
            });
        });
        $(document).ready(function() {
            $(document).keydown(function() {
                if (event.keyCode == 13) {
                    event.keyCode = '9';
                }
            })
        });
        var curUserId=null;
        function getPostInfo(event, treeId, treeNode) {
        	if(treeNode.checked){
        		 curUserId = treeNode.id;
        	}else{
        		curUserId = null;
        	}
            $.dajax({
                data : {userId : curUserId},
                url : "user/findUserPost.do",
                success : function(data) {
                    if (data.success) {
                        postTree.checkAllNodes(false);
                        var postIds = data.content;
                        postIds = postIds.split(",");
                        for (var i = 0; i < postIds.length; i++) {
                            if (postIds[i] != null && postIds[i] != "") {
                                var checking = postTree.getNodeByParam('id', postIds[i], null);
                                if(checking!=null){
                                    postTree.checkNode(checking, true, false);
                                }

                            }
                        }
                    }
                }
            });
        }

        function save() {
            var postIds = "";
            var checkedNodes = postTree.getCheckedNodes();
            $.each(checkedNodes, function(i, node) {
                postIds +=  this.id + ",";
            });
            if(curUserId == null){
            	return;
            }
            $.dajax({
                data : {userId : curUserId, postIds : postIds},
                url : "user/saveUserPost.do",
                success : function(data) {
                    if (data.success) {
                        $.dalert({text : data.content, icon : 1});
                    } else {
                        $.dalert({text : data.content, icon : 2});
                    }
                }
            });
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <button type="submit" class="bootstrap-table-submit" onclick="save()">保存
        </button>
    </div>

    <div class="row">
        <div class="col-md-5 demo">
            <div class="row"><div class="demo-tit">用户信息</div></div>
            <div class="row">
                <ul id="userTree" class="demo-tree"></ul>
            </div>
        </div>
        <div class="col-md-7 demo">
            <div class="row"><div class="demo-tit">岗位信息</div></div>
            <div class="row">
                <ul id="postTree" class="demo-tree"></ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
