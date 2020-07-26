<%@page import="com.datanew.util.ConfigureParser"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page language="java" import="com.datanew.model.BaseOperator" %>
<%@ page language="java" import="com.datanew.util.StaticData" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    BaseOperator operator = (BaseOperator) session.getAttribute(StaticData.LOGINUSER);
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <title>报表设置</title>
    <script language=javascript src="js/bdd.js"></script>
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
        .bootstrap-table{border:none !important; padding:0 !important;}
        .fixed-table-toolbar .bs-bars{margin-bottom:0 !important;}
    </style>
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.datanew.js"></script>
    <script language=javascript src="js/bdd.js"></script>
    <script type="text/javascript">
    var myTab = $("#myTab", window.parent.document);
    var myTabContent = $("#myTabContent", window.parent.document);
    var myTabBtn = $(".tab_btn", window.parent.document);
    var myTabWrap = $(".tab_wrap", window.parent.document);
    var myTabBox = $(".tabBox", window.parent.document);
    var mytabWid;
        var reportTree;
        $(function () {
            $("body").css({width: $(window).width(), overflowX: "hidden"});
            reportTree = $("#reportTree").dtree({
                checkType: "radio", //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
                isOpen: true, //节点是否全部展开，默认为不展开
                ISLEAF: true,
                rootElement: false,  //是否添加根节点“全部”
                url: "common/findUserReportTree.do",
                callback: {
                    onCheck: getMenu
                }
            });
            $("#datatable").dtable({
              	 height:500,
                  columns: [[ {
      				checkbox : true
      			}, {
      				title: '序号',//标题  可不加  
               	    width: 50,
                    formatter: function (value, row, index) {  
                        return index+1;  
                    } 
                  }, {
                      field: 'guid',
                      title: '上传文件id',
                      width: 100,
                      align: 'center',
                      visible: false
                  }, {
                      field: 'filename',
                      title: '上传文件名称',
                      width: 100,
                      align: 'center'
                  },{
                      field: 'uploaddate',
                      title: '上传时间',
                      width: 100,
                      align: 'center'
                  }]],
                  url:'report/getFileInfo.do',
                  pageNumber: 1,
                  pageSize: 20,
                  showRefresh:false,
                  queryParams: queryParams,
                  paginationHAlign: 'left',
                  sidePagination: 'server',
                  toolbar: '#bt',
                  clickToSelect: true
                
              }); 
            var zNodes = [
                {id: 1, pId: 0, name: "节点搜索演示 1"},
                {id: 1, pId: 0, name: "节点搜索演示 2"}
            ];
            
        });
        $(document).ready(function() {
            $(document).keydown(function() {
                if (event.keyCode == 13) {
                    event.keyCode = '9';
                }
            })
        });
        var curReportId;
        var reportdata;
        var reportsj;
        var isCompete;
        var istbenable;
        function getMenu(event, treeId, treeNode) {
        	if(treeNode.checked){
        		curReportId = treeNode.id;
        		tbsj();
	            reportdata = treeNode.fillName;
        	}else{
        		curReportId=null;
        	}
        }
        function tbsj(){
        	var userid = "<%=operator.getUserid()%>";
        	$.dajax({
                data : {reportId : curReportId,userid:userid},
                url : "report/getTbsj.do",
                success : function(data) {
                	reportsj = data.content;
                	$("#datatable").bootstrapTable("refresh");
                	progress();
            		tbenable();
                }
            });
        }
        function progress(){
        	var userid = "<%=operator.getUserid()%>";
        	$.dajax({
                data : {reportId : curReportId,userid:userid,reportsj:reportsj},
                url : "report/getProgress.do",
                success : function(data) {
                	isCompete = data.content;
                }
            });
        }
        function queryParams(params) {
            return {
            	reportid:curReportId,
            	userid:<%=operator.getUserid()%>,
            	reportsj:reportsj,
                limit: params.limit,
                offset: params.offset
            }
        }
        
        function design(){
       	 if (curReportId == null) {
                $.dalert({text: "未选择要填写的报表", icon: 7});
            } else {
            	var authId = "<%=ConfigureParser.getPropert("authId","UTF-8")%>";
            	var targetVolumn = "<%=ConfigureParser.getPropert("targetVolume")%>";
            	var url = "<%=ConfigureParser.getPropert("BB_URL")%>";
            	//alert(url);
            	var YHM = "<%=operator.getUserid()%>";
            	var XZQH;
            	/* if(istbenable=="0"){
            		$.dalert({text: "改报表已经停止填报", icon: 7});
            	} */
            	if(isCompete=="1"||istbenable=="0"){
            		XZQH = "";
            	}else{
            	    XZQH = "<%=operator.getRegicode()%>";
            	}
            	
            	var bburl=url+"hrServlet?fileName="+reportdata+"&targetVolume="+targetVolumn+"&authId="+authId+"&variants=YHM="+YHM+";XZQH="+XZQH+";DW="+DW+";GKCS="+GKCS+";SJ="+reportsj;
            	locationTo(bburl);
            }
       }
        function tbenable(){
        	$.dajax({
                data : {reportId : curReportId,userid:""},
                url : "report/getTbEnable.do",
                success : function(data) {
                	istbenable=data.content;
                	
                }
            });
        }
        function updateFile(){
        	var flag = $("#upfm").dform("validate");
    		if (!flag) {
    			return $.dalert({text :"验证不通过，有部分数据格式错误或有必填数据未填",icon:7});
    		}
        	$("#upfm").dform('upload',function(data){
                data=eval("("+data+")");
                if(data.success){
                	$.dalert({
    					text : data.content,
    					icon : 1
    				});
                	$('#upitemModel').modal('hide');
    				$("#datatable").bootstrapTable("refresh");
                }else{
                	$.dalert({
    					text : data.content,
    					icon : 2
    				});
                }
    	    });
        }
        function upload(){
        	if(istbenable=="0"){
        		return $.dalert({text: "改报表已经停止填报", icon: 7});
        	}
        	if(isCompete=="1"){
        		return $.dalert({text :"该报表已经完成填报,请等待审核结果",icon:7});
        	}
        	if (curReportId == null) {
                $.dalert({text: "未选择要上传附件的报表", icon: 7});
            } else {
            	$('#upfm').dform('clear');
        		$("#upitemModel").modal('show');
        		$("#reportid").val(curReportId);
        		$("#reportsj").val(reportsj);
        		var userid = "<%=operator.getUserid()%>";
        		$("#userid").val(userid);
            }
        }
        function complete(){
        	if(istbenable=="0"){
        		return $.dalert({text: "改报表已经停止填报", icon: 7});
        	}
        	if(isCompete=="1"){
        		return $.dalert({text :"该报表已经完成填报,请等待审核结果",icon:7});
        	}
        	var userid = "<%=operator.getUserid()%>";
        	$.dconfirm({
                text: "执行该操作后您所填写的数据将不能再更改,确定填报完成？", btn: ["确定", "取消"], funs: [function () {
                	$.dajax({
                        url: "report/complete.do",
                        data: {userid:userid,reportid:curReportId,reportsj:reportsj },
                        success: function (data) {
                        	if (data.success) {
                        		 $.dalert({text : data.content, icon : 1});
                        		progress();
                            }else{
                            	 $.dalert({text : data.content, icon : 2});
                            }
                	    }
                    });
                }, function () {
                    $.dalert({text: "取消操作", icon : 7});
                }]
            });
        }
        function locationTo(url, treeId, treeNode){
        	//alert($(window).height());
            var index;
            var ohref = url;
            var nodeid; 
            var nodename;
            //console.log(typeof parent.arr_nodeurl);
            for(var i=0; i<parent.arr_nodeurl.length; i++){
                if(ohref == parent.arr_nodeurl[i]){
                    index = i;
                }
            }
            nodeid = "bb_tb"+reportdata;
            nodename = "填报填制";

            if($("#tabli-"+nodeid, window.parent.document).length == 0){
                //插入tab
                var tabli_html = '<li class="active" id="tabli-'+nodeid+'"><a href="#tab-'+ nodeid +'" id="tabclose-'+nodeid+'" data-toggle="tab">'+ nodename +' <i class="fa fa-times-circle"></i></a></li>';
                var tabpane_html = "";
                tabpane_html += '<div class="tab-pane fade in active" id="tab-'+ nodeid +'">';
                tabpane_html += '<iframe src="'+ ohref +'" frameborder="0" class="iframeCon" id="iframe-'+ nodeid +'" width="100%" height="'+ $(window).height() +'"></iframe>';
                tabpane_html += '</div>';
                myTab.find("li").removeClass("active");
                myTabContent.find(".tab-pane").removeClass("in active");
                myTab.append(tabli_html);
                myTabContent.append(tabpane_html);

                myTabBtn.find(".closeAll").removeClass("hide"); //显示“关闭所有”按钮
                visibleWid = myTabBox.outerWidth() - myTabBtn.outerWidth(true); //可见tab区域的宽度
                mytabWid += $("#tabli-"+nodeid, window.parent.document).width(); //插入tab后mytab的宽度

                if(mytabWid > visibleWid){ //mytab总长 > 可见tab区域
                    myTabBtn.find(".showtab").removeClass("hide"); //显示“向左向右”按钮
                    visibleWid = myTabBox.outerWidth() - myTabBtn.outerWidth(true); //此时按钮区域宽度变大
                    myTab.width(mytabWid); //mytab宽度
                }else{
                    myTab.width("auto");
                }
                myTabWrap.width(visibleWid); //设置可见tab区域的宽度
                myTab.animate({left : "-" + (mytabWid - visibleWid) + "px"}, 200);
            }else{ //该tab已打开
                $("#tabli-"+nodeid, window.parent.document).addClass("active").siblings().removeClass("active");
                $("#tab-"+nodeid, window.parent.document).addClass("in active").siblings().removeClass("in active");
                //当该标签页不在tab可见区域内时，滑动到该标签
                visibleWid = myTabBox.outerWidth() - myTabBtn.outerWidth(true); //可见tab区域的宽度
                if(mytabWid > visibleWid){
                    var lileft = $("#tabli-"+nodeid, window.parent.document).position().left;//当前标签页相对父元素的left偏移量
                    var diff = mytabWid - lileft;
                    if(diff <= visibleWid){
                        myTab.animate({left : "-" + (mytabWid - visibleWid) +"px"}, 500);
                    }else{
                        myTab.animate({left : "-" + lileft +"px"}, 500);
                    }
                }
            }

            //关闭标签页
            $("#tabclose-"+nodeid+" .fa-times-circle", window.parent.document).on("click",function(){
                //设置mytab宽度
                mytabWid -= $("#tabli-"+nodeid, window.parent.document).width(); //关闭tab后mytab的宽度
                myTab.width(mytabWid); //mytab宽度
                //mytab只剩下首页标签
                if(myTab.find("li").length == 2){
                    myTabBtn.find(".closeAll").addClass("hide");
                    myTabWrap.width("auto");
                    myTab.removeAttr("style");
                }
                visibleWid = myTabBox.outerWidth() - myTabBtn.outerWidth(true);
                var nowleft = Math.abs(parseInt(myTab.css("left")));//当前myTab的left值
                var diff = Math.abs(mytabWid - visibleWid);
                if((mytabWid > visibleWid) && (diff != nowleft)){
                    myTab.animate({left : "-" + diff +"px"}, 500);
                }else if(mytabWid <= visibleWid){
                    //去掉关闭标签等按钮
                    myTabBtn.find(".showtab").addClass("hide");
                    visibleWid = myTabBox.outerWidth() - myTabBtn.outerWidth(true);
                    myTabWrap.width(visibleWid);
                    myTab.removeAttr("style").animate({left:0}, 500);
                }

                //关闭标签页，关闭当前页则前一页active
                var tab_li = $(this, window.parent.document).parents("li");
                var tabpane_id = tab_li.find("a").attr("href");
                if(tab_li.hasClass("active")){
                    tab_li.prev().addClass("active");
                    $(tabpane_id, window.parent.document).prev().addClass("in active");
                }
                tab_li.remove();
                $(tabpane_id, window.parent.document).remove();
            });
        }
        function searchFile(){
     	    var dataArr = $("#datatable").bootstrapTable("getSelections");
     	    var guid = dataArr[0].guid;
 			downloadForm.fileguid.value = guid;
 			$("#form2").submit();
 			$('#_downloadForm').load(function(){
 				var text = $(this).contents().find("body").text();
 				if (text == 1) {
 					$.dalert({text : "下载失败", icon : 2});
 				}
 			});
        }
        function deleteFile(){
        	if(isCompete=="1"){
        		return $.dalert({text :"该报表已经完成填报,请等待审核结果",icon:7});
        	}
        	var dataArr = $("#datatable").bootstrapTable("getSelections");
     	   var fileguid = "";
     	    for(var i=0;i<dataArr.length;i++){
     	    	if(i<dataArr.length-1){
     	    		fileguid += dataArr[i].guid+",";
     	    	}else{
     	    	   fileguid += dataArr[i].guid;
     	    	}
     	    	
     	    }
        	 $.dajax({
                 url: "report/deleteRopertFile.do",
                 data: {fileguid:fileguid },
                 success: function (data) {
                 	if (data.success) {
                 		 $.dalert({text : data.content, icon : 1});
                 		$("#datatable").bootstrapTable("refresh");
                     }else{
                     	 $.dalert({text : data.content, icon : 2});
                     }
         	    }
             });
        }
        function designmodel(){
        	$("#sjselect").comboBox({
        	      width:200,
        	      required:true,
        	      multiple:false,
        	      url:"common/findReportSJTree.do"
        	    });
        	$('#sjitemModel').modal('show');
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 demo">
	        <button class="bootstrap-table-add" type="button" onclick="design()">填报</button>
	        <button id="sczzfj" class="bootstrap-table-upload" type="button" onclick="upload()">上传纸质附件</button>
	        <button class="bootstrap-table-submit" type="button" onclick="complete()">确认填报完成</button>
        </div>
        <div class="col-md-6 demo">
	        <button class="bootstrap-table-add" type="button" onclick="searchFile()">查看附件</button>
	        <button class="bootstrap-table-upload" type="button" onclick="deleteFile()">删除附件</button>
        </div>
        <div style="display: none">
	        <FORM id="form2" METHOD=POST ACTION="login/downloadFile.do" target="_downloadForm" name="downloadForm" >
	            <input type="text" id="fileguid" name="fileguid" style="display:none"/>
	        </FORM>
	        <iframe name="_downloadForm" id="_downloadForm" style="display:none;"></iframe>
    	</div>
    </div>
    <div class="row">
        <div class="col-md-4 demo">
           <div class="row">
            <div class="demo-tit">报表信息</div>
            <ul id="reportTree" class="demo-tree"></ul>
            </div>
        </div>
        <div class="col-md-6 demo">
        <div class="row">
            <table id="datatable"></table>
            </div>
        </div>
    </div>
    	<div class="modal fade" id="upitemModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" style="display: none;">
		<div class="modal-dialog modal-lg" role="document" style="width:600px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="">上传用户信息</h4>
				</div>

				<div class="modal-body">
					<div class="tab-content">
						<div class="tab-pane fade in active" id="upitemRecords">
							<form class="form-horizontal" role="form" method="post" enctype="multipart/form-data" target="submitFrame" onsubmit="" action="login/upReportFile.do" id="upfm">
								<div class="form-group">
								   <input class="from-control" id="reportid" name="reportid" type="hidden" /> 
								   <input class="from-control" id="userid" name="userid" type="hidden" /> 
								   <input class="from-control" id="reportsj" name="reportsj" type="hidden" /> 
									<div class="col-sm-12" style="margin-bottom: 10px;">
								        <label class="col-sm-4 control-label" for="fileUpLoad" >选择附件</label>
								        <div class="col-sm-6">
								            <input class="form-control" type="file" id="fileUpLoad" name="fileUpLoad" proving="notEmpty">
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
</div>
</body>
</html>
