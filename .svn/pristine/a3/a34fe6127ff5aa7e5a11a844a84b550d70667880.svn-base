﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>报表设置</title>
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
<script type="text/javascript">
    var fillname = "";
    var collectname = "";
    var myTab = $("#myTab", window.parent.document);
    var myTabContent = $("#myTabContent", window.parent.document);
    var myTabBtn = $(".tab_btn", window.parent.document);
    var myTabWrap = $(".tab_wrap", window.parent.document);
    var myTabBox = $(".tabBox", window.parent.document);
    var mytabWid;
    var m = new Map();
    $(function () {
        $("body").css({
            width: $(window).width(),
            overflowX: "hidden"
        });
        var zNodes = [
                      {id: "0", pId: null, name: "未审核"},
                      {id: "1", pId: null, name: "已审核"}
                  ];
        $("#datatable").dtable({
			columns : [ [ {
				checkbox : true
			}, {
				field : 'USERID',
				title : '填报人ID ',
				width : 100,
				align : 'center',
                visible: false
			}, {
				field : 'REPORTID',
				title : '报表ID ',
				width : 100,
				align : 'center',
                visible: false
			}, {
				field : 'REPORTNAME',
				title : '报表名称',
				width : 100,
				align : 'center'
			},  {
				field : 'SJ',
				title : '报表发布时间',
				width : 100,
				align : 'center'
			},/*  {
				field : 'DIVISIONNAME',
				title : '归口处室',
				width : 150,
				align : 'center'
			},  {
				field : 'ENTENAME',
				title : '单位名称',
				width : 150,
				align : 'center'
			},*/ {
				field : 'USERNAME',
				title : '填报人',
				width : 150,
				align : 'center'
			}, {
				field : 'ISFJ',
				title : '是否上传附件',
				width : 100,
				align : 'center'
			}, {
				field : 'FILLSTATUS',
				title : '填制状态',
				width : 100,
				align : 'center'
			},{
				field : 'AUDITSTATUS',
				title : '审核状态',
				width : 100,
				align : 'center'
			},{
				field : 'REMARK',
				title : '备注',
				width : 100,
				align : 'center'
			}] ],
			searchbar: {
                rownum: 3,//搜索栏表单列数  最大支持3
                labelwidth:"100px",
                inputs: [//搜索栏表单参数
                    {
                        title: '报表',//表单lable显示名
                        name: 'reportid',//表单name属性
                        type: 'dsearchtree',//表单类型：目前支持 select/text
                        placeholder: '输入填报报表',
                        searchOption:true,
                        url : 'common/findReportFillNameTree.do',
                        checkType: 'radio',
                      	onAckCallback:function(nodes){
                      		fillname = nodes[0].code;
                      		collectname = nodes[0].collectcode;
                      	    $('#reportid').searchTree("setValue",nodes[0].id);
                      	  $('#sj').searchTree("reload",{
                       		url:"common/findReportSJTree.do",
                       		queryParam:{
                       			reportid:nodes[0].id
                          }});
                      		$('#sj').val("");
                      		$("[name='sj']").val("");
                      	 },
                         id:'reportid'
                    },{
                        title: '发布时间',//表单lable显示名
                        name: 'sj',//表单name属性
                        type: 'dsearchtree',//表单类型：目前支持 select/text
                        placeholder: '输入填报报表',
                        searchOption:true,
                        url : 'common/findReportSJTree.do',
                        checkType: 'radio',
                      	onAckCallback:function(nodes){
                      	    $('#sj').searchTree("setValue",nodes[0].id);
                      	 },
                      	onLoaded:function(tree){
                      	    //console.log(tree.getNodes());
                      	},
                         id:'sj'
                    },/*{
                        title: '归口处室',//表单lable显示名
                        name: 'divisionguid',//表单name属性
                        type: 'dsearchtree',//表单类型：目前支持 select/text
                        placeholder: '请输入归口处室',
                        searchOption:true,
                        url : 'login/findDivisionTree.do',
                        checkType: 'checkbox',
                      	onAckCallback:function(nodes){
                      		 var dinformant="";
	                      	 for(var i=0;i<nodes.length;i++){
	                  	    	if(i<nodes.length-1){
	                  	    		dinformant += nodes[i].id+",";
	                  	    	}else{
	                  	    	    dinformant += nodes[i].id;
	                  	    	}
	                  	     }
	                      	 $('#divisionguid').searchTree("setValue",dinformant);
                      	 },
                         id:'divisionguid'
                    },{
                        title: '单位名称',//表单lable显示名
                        name: 'entename',//表单name属性
                        type: 'text',//表单类型：目前支持 select/text
                        placeholder: '请输入单位名称',
                        searchOption:true,
                         id:'entename'
                    },*/{
                        title: '状态',//表单lable显示名
                        name: 'auditstatus',//表单name属性
                        type: 'dsearchtree',//表单类型：目前支持 select/text
                        placeholder: '请输入审核状态',
                        searchOption:true,
                        localdata : zNodes,
                        checkType: 'radio',
                      	onAckCallback:function(nodes){
                      	    $('#auditstatus').searchTree("setValue",nodes[0].id);
                      	 },
                         id:'auditstatus'
                    }
                ]
            },
			url : 'report/getFillInfo.do',//请求地址
			//              data:[],//前端加载数据
			pageNumber : 1,//起始页
			pageSize : 20,//页面大小
			showRefresh:false,
			uniqueId : 'guid',
			queryParams : queryParams,//查询参数方法
			paginationHAlign : 'left',//分页按钮位置  left right
			sidePagination : 'server',//分页方式(真分页)
			toolbar : '#bt',//工具栏 对应dom元素
			clickToSelect : true
		});

        $('#module').searchTree({
      	    checkType:'radio',
          	url : 'common/findModuleTree.do',
  	          	onAckCallback:function(nodes){
  	          		$('#module').searchTree("setValue",nodes[0].id);
  	          	  }
       });
        $("#fileForm").dtable({
			columns : [ [ {
				field : 'guid',
				title : '填报人ID ',
				align : 'center',
               visible: false
			}, {
				field : 'guidFileName',
				title : '附件名称 ',
				align : 'center',
				formatter:formateFile
			}, {
				field : 'uploaddate',
				title : '上传时间',
				align : 'center'
			}] ],
			url : 'report/getFileInfo.do',//请求地址
			//              data:[],//前端加载数据
			pageNumber : 1,//起始页
			pageSize : 20,//页面大小
			showRefresh:false,
			queryParams : queryFile,//查询参数方法
			paginationHAlign : 'left',//分页按钮位置  left right
			sidePagination : 'server',//分页方式(真分页)
			clickToSelect : true
		});

    	$("#collapse_datatable").addClass("in").siblings(".panel-heading").addClass("collapsed");
    });
    $(document).ready(function() {
        $(document).keydown(function() {
            if (event.keyCode == 13) {
                event.keyCode = '9';
            }
        })
    });
    function queryParams(params) {
    	var userids_="";
    	if(!m.isEmpty()){
    		userids_=m.get($('#reportid').searchTree("getValue")).toString();
    	}
		return {
			//如果需要后端进行分页 limit 和offset是必须参数
			userids:userids_,
			limit : params.limit,
			offset : params.offset,
		}
	}
    function search(){
 	   var dataArr = $("#datatable").bootstrapTable("getSelections");
 	   //var dinformant = "";
 	   //var changestr = "";
 	  var authId = "<%=ConfigureParser.getPropert("authId","UTF-8")%>";
	   	var targetVolumn = "<%=ConfigureParser.getPropert("targetVolume")%>";
	   	var url = "<%=ConfigureParser.getPropert("BB_URL")%>";
	    for(var i=0;i<dataArr.length;i++){
    		var bburl = url+"hrServlet?fileName="
    		+fillname+ "&targetVolume=" + targetVolumn
    		+ "&authId=" + authId + "&variants=YHM="+dataArr[i].USERID+";XZQH=;GKCS=;DW=;SJ="+dataArr[i].SJ+";REMARK="+dataArr[i].REMARK;
    		var fun = 'locationTo("'+bburl+'",'+dataArr[i].USERID+')'+"";
    		setTimeout(fun,i*1000);
	    }
	   
	}
   function design(){
	   var dataArr = $("#datatable").bootstrapTable("getSelections");
	   for(var i=0;i<dataArr.length;i++){
           if(dataArr[i].FILLSTATUS=="填制中"){
          	 return $.dalert({text : "存在数据未填制完成", icon : 7});
           }
	    }
	   $('#dfm').dform('clear');
	   $("#itemModel").modal('show');
   }
   function sendMessage(){
	   var flag = $("#dfm").dform("validate");
	    if(!flag){
	        $.dalert({text :"验证不通过，有部分数据格式错误或有必填数据未填",icon:7});
	        return;
	    }
	    var dataArr = $("#datatable").bootstrapTable("getSelections");
	    var reportcode = dataArr[0].REPORTID;
	    var reportsj = dataArr[0].SJ;
	    var dinformant = "";
	    for(var i=0;i<dataArr.length;i++){
	    	if(i<dataArr.length-1){
	    		dinformant += dataArr[i].USERID+",";
	    	}else{
	    	    dinformant += dataArr[i].USERID;
	    	}
	    }
	    $.dajax({
          url: "report/sendMessage.do",
          data: {userid:dinformant,module:$("#module").searchTree("getValue"),reportid:reportcode,reportsj:reportsj },
          success: function (data) {
          	if (data.success) {
          		$('#itemModel').modal('hide');
          		 $.dalert({text : data.content, icon : 1});
          		$("#datatable").bootstrapTable("refresh");
              }else{
              	$('#itemModel').modal('hide');
              	 $.dalert({text : data.content, icon : 2});
              }
  	    }
      });
   }
   function downloadFile(guid){
	   form2.fileguid.value = guid;
		$("#form2").submit();
		$('#_downloadForm').load(function(){
			var text = $(this).contents().find("body").text();
			if (text == 1) {
				$.dalert({text : "下载失败", icon : 2});
			}
		});
   }
   function docancel(){
   	   $('#itemModel').modal('hide');
   }
   function viewPass(){
	   var dataArr = $("#datatable").bootstrapTable("getSelections");
	   var dinformant = "";
	    for(var i=0;i<dataArr.length;i++){
	    	if(i<dataArr.length-1){
	    		dinformant += dataArr[i].USERID+",";
	    	}else{
	    	    dinformant += dataArr[i].USERID;
	    	}
	    }
	    for(var i=0;i<dataArr.length;i++){
             if(dataArr[i].FILLSTATUS=="填制中"){
            	 return $.dalert({text : "存在数据未填制完成", icon : 7});
             }
	    }
	   var reportcode = dataArr[0].REPORTID;
	   var reportsj = dataArr[0].SJ;
		if (dinformant=="") {
			$.dalert({
				text : "请选择至少一条数据",
				icon : 7
			});
		}else{
			   $.dajax({
			          url: "report/viewPass.do",
			          data: {userid:dinformant,reportid:reportcode,reportsj:reportsj },
			          success: function (data) {
			          	if (data.success) {
			          		$('#itemModel').modal('hide');
			          		 $.dalert({text : data.content, icon : 1});
			          		$("#datatable").bootstrapTable("refresh");
			              }else{
			              	$('#itemModel').modal('hide');
			              	 $.dalert({text : data.content, icon : 2});
			              }
			  	    }
			      });
		}
   }
   function searchSummery(){
        if(collectname==""||collectname==null){
         	 return $.dalert({text : "请选择一张报表进行查询", icon : 7});
        }
    	var authId = "<%=ConfigureParser.getPropert("authId","UTF-8")%>";
    	var targetVolumn = "<%=ConfigureParser.getPropert("targetVolume")%>";
    	var url = "<%=ConfigureParser.getPropert("BB_URL")%>";
    	var dataArr = $("#datatable").bootstrapTable("getSelections");
    	var bburl=url+"hrServlet?fileName="
			+collectname+ "&targetVolume=" + targetVolumn
			+ "&authId=" + authId + "&variants=YHM=;SJ="+dataArr[0].SJ+";REMARK="+dataArr[0].REMARK;
    	locationTo(bburl,collectname);
   }
   function searchFile(){
	   var dataArr = $("#datatable").bootstrapTable("getSelections");
 	   var dinformant = dataArr[0].USERID;
 	  if(dinformant==""||dataArr.length>1){
			return $.dalert({
				text : "请选择一条数据进行查询",
				icon : 7
			});
 	  }
	   /* var modalObj = $("#modal").dmodal({
		    title:'附件列表',  //模态框标题
		    content:'<table id="fileForm"></table>',
			width:"600",	//模态框宽度
			height:"350"
		}); */
		$('#fileitemModel').modal('show');
		$("#fileForm").bootstrapTable("refresh");
   }
   function queryFile(params) {
	   var dataArr = $("#datatable").bootstrapTable("getSelections");
	   if(dataArr.length==0){
		   return;
	   }
 	   var dinformant = dataArr[0].USERID;
		return {
			userid:dinformant,
			reportid:dataArr[0].REPORTID,
			reportsj:dataArr[0].SJ,
			limit : params.limit,
			offset : params.offset,
		}
	}
   function formateFile(value) {
	   var data = value.split(",");
       return "<a onclick='javascript:downloadFile("+data[0]+");'>"+data[1]+"</a>"; 
   }
   var windowHeight = $(window).height();
   function locationTo(url,yhm, treeId, treeNode){
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
       nodeid = "bb_tb"+yhm;
       nodename = "填报查询";

       if($("#tabli-"+nodeid, window.parent.document).length == 0){
           //插入tab
           var tabli_html = '<li class="active" id="tabli-'+nodeid+'"><a href="#tab-'+ nodeid +'" id="tabclose-'+nodeid+'" data-toggle="tab">'+ nodename +' <i class="fa fa-times-circle"></i></a></li>';
           var tabpane_html = "";
           tabpane_html += '<div class="tab-pane fade in active" id="tab-'+ nodeid +'">';
           tabpane_html += '<iframe src="'+ ohref +'" frameborder="0" class="iframeCon" id="iframe-'+ nodeid +'" width="100%" height="'+ windowHeight +'"></iframe>';
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
   var arr = new Array();
   function addundw(){
	   var dataArr = $("#datatable").bootstrapTable("getSelections");
	   var dinformant = new Array();
	    for(var i=0;i<dataArr.length;i++){
	    	if(i<dataArr.length-1){
	    		dinformant[i] = dataArr[i].USERID;
	    	}else{
	    	    dinformant[i] = dataArr[i].USERID;
	    	}
	    }
	   var reportcode = dataArr[0].REPORTID;
		if (dinformant=="") {
			$.dalert({
				text : "请选择至少一条数据",
				icon : 7
			});
		}else{
			if(m.isEmpty()){
				m.put(reportcode,dinformant);
			}else{
				arr=m.get(reportcode).concat(dinformant);
	            var arr_ = new Array();  //定义一个临时数组  
	            for(var i = 0; i < arr.length; i++){    //循环遍历当前数组  
	                //判断当前数组下标为i的元素是否已经保存到临时数组  
	                //如果已保存，则跳过，否则将此元素保存到临时数组中  
	                if(arr_.toString().indexOf(arr[i]) == -1){  
	                	arr_.push(arr[i]);  
	                };
	            }   
			    m.put(reportcode,arr_);
			}
		}
		return $.dalert({text : "添加成功", icon : 1});
   }
   function Map() {     
	    /** 存放键的数组(遍历用到) */    
	    this.keys = new Array();     
	    /** 存放数据 */    
	    this.data = new Object();     
	         
	    /**   
	     * 放入一个键值对   
	     * @param {String} key   
	     * @param {Object} value   
	     */    
	    this.put = function(key, value) {     
	        if(this.data[key] == null){     
	            this.keys.push(key);     
	        }     
	        this.data[key] = value;     
	    };     
	         
	    /**   
	     * 获取某键对应的值   
	     * @param {String} key   
	     * @return {Object} value   
	     */    
	    this.get = function(key) {     
	        return this.data[key];     
	    };     
	         
	    /**   
	     * 删除一个键值对   
	     * @param {String} key   
	     */    
	    this.remove = function(key) {     
	        this.keys.remove(key);     
	        this.data[key] = null;     
	    };     
	         
	    /**   
	     * 遍历Map,执行处理函数   
	     *    
	     * @param {Function} 回调函数 function(key,value,index){..}   
	     */    
	    this.each = function(fn){     
	        if(typeof fn != 'function'){     
	            return;     
	        }     
	        var len = this.keys.length;     
	        for(var i=0;i<len;i++){     
	            var k = this.keys[i];     
	            fn(k,this.data[k],i);     
	        }     
	    };     
	         
	    /**   
	     * 获取键值数组(类似Java的entrySet())   
	     * @return 键值对象{key,value}的数组   
	     */    
	    this.entrys = function() {     
	        var len = this.keys.length;     
	        var entrys = new Array(len);     
	        for (var i = 0; i < len; i++) {     
	            entrys[i] = {     
	                key : this.keys[i],     
	                value : this.data[i]     
	            };     
	        }     
	        return entrys;     
	    };     
	         
	    /**   
	     * 判断Map是否为空   
	     */    
	    this.isEmpty = function() {     
	        return this.keys.length == 0;     
	    };     
	         
	    /**   
	     * 获取键值对数量   
	     */    
	    this.size = function(){     
	        return this.keys.length;     
	    };     
	         
	    /**   
	     * 重写toString    
	     */    
	    this.toString = function(){     
	        var s = "{";     
	        for(var i=0;i<this.keys.length;i++,s+=','){     
	            var k = this.keys[i];     
	            s += k+"="+this.data[k];     
	        }     
	        s+="}";     
	        return s;     
	    };     
	}  
   function queryundw(){
	   $("#datatable").bootstrapTable("refresh");
   }
   function exportReport(){
	   var dataArr = $("#datatable").bootstrapTable("getSelections");
 	   //var dinformant = "";
 	   //var changestr = "";
 	  var authId = "<%=ConfigureParser.getPropert("authId","UTF-8")%>";
	   	var targetVolumn = "<%=ConfigureParser.getPropert("targetVolume")%>";
	   	var url = "<%=ConfigureParser.getPropert("BB_URL")%>";
	   	var indata = "";
	   	var bburl = "";
	    for(var i=0;i<dataArr.length;i++){
    		bburl = url+"hrServlet?fileName="
    		+fillname+ "&targetVolume=" + targetVolumn
    		+ "&authId=" + authId + "&variants=reportWriteable=false;YHM="+dataArr[i].USERID+";XZQH=;";
    		indata+=","+dataArr[i].USERID+"|"+dataArr[i].SJ;
	    }
	    reportform.fileUrl.value = bburl;
		reportform.fileName.value = indata;
		$("#reportform").submit();
		$('#_reportForm').load(function(){   
			var text = $(this).contents().find("body").text();
			if (text == 1) {
				$.dalert({text : "下载失败", icon : 2});
			}
		});
   }
</script>
</head>
<body>
	<div class="container-fluid" style="padding: 0 5px; width: 99%;">
	    <table id="datatable"></table>
		<div id="bt">
		     <button class="bootstrap-table-submit" type="button" onclick="search()">查看填报内容</button>
		     <button class="bootstrap-table-download" type="button" onclick="searchFile()">查看附件</button>
			 <button class="bootstrap-table-edit" type="button" onclick="design()">退回并通知</button>
			 <button class="bootstrap-table-submit" type="button" onclick="viewPass()">审核通过</button>
			 <button class="bootstrap-table-submit" type="button" onclick="searchSummery()">报表汇总查询</button>
			 <button class="bootstrap-table-download" type="button" onclick="exportReport()">导出报表</button>
			 <!-- <button class="bootstrap-table-add" type="button" onclick="addundw()">添加不汇总单位</button>
			 <button class="bootstrap-table-submit" type="button" onclick="queryundw()">查询不汇总单位</button> -->
		</div>
		<div style="display: none">
	        <FORM id="form2" METHOD=POST ACTION="login/downloadFile.do" target="_downloadForm" name="downloadForm" >
	            <input type="text" id="fileguid" name="fileguid" style="display:none"/>
	        </FORM>
	        <iframe name="_downloadForm" id="_downloadForm" style="display:none;"></iframe>
    	</div>
    	<div style="display: none">
	        <FORM id="reportform" METHOD=POST ACTION="login/exportReport.do" target="_reportForm" name="downloadForm" >
	            <input type="text" id="fileName" name="fileName" style="display:none"/>
	            <input type="text" id="fileUrl" name="fileUrl" style="display:none"/>
	        </FORM>
	        <iframe name="_reportForm" id="_reportForm" style="display:none;"></iframe>
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
							<form class="form-horizontal" role="form" method="post" id="dfm">
								<div class="form-group">
									<div class="col-sm-12" style="margin-bottom: 10px;">
										<label for="module" class="col-sm-4 control-label">选择通知短信模板：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="module"
												name="module" proving="notEmpty" />
										</div>
									</div>
								</div>
							</form>
							<div class="well well-sm" align="right">
								<button class="btn btn-info btn-sm" onclick="sendMessage()">确定</button>
								<button class="btn btn-default btn-sm" onclick="cancel()">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="fileitemModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document"
			style="width: 600px; margin:0 auto;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="">附件信息</h4>
				</div>

				<div class="modal-body">
					<div class="tab-content">
                         <table id="fileForm"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
