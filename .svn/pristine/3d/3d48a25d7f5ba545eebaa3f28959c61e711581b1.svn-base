<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>首页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="bootstrap/css/font-awesome.min.css"/>
    <script type="text/javascript" src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.datanew.js"></script>
	<link rel="stylesheet" href="css/gzpt_index.css">

</head>

<body>
<div class="container_fluid">
    <div class="welcomeInfo">
        <i class="ico_idx ico_1"></i><span id="username"></span>，下午好！欢迎使用预算绩效管理平台
        &emsp;<i class="ico_idx ico_2"></i>现在是<span id="nowdate"></span>&emsp;<br>
        <span style="font-size: 20px;font-weight: bolder">报表没有数据的三种可能：</span><br>
        <span style="color: red">
        一:权限不对，务必确认是用预算申报岗登陆的。<br>
        二:是浏览器不对，显示不正常，请下载登陆页面的浏览器。<br>
        三:是部分数据由于跨年需要在查询界面选择年度。(点击“查询条件”)<br>
        大家自己排查一下.
        </span>
    </div>
    <%--<div>
        <i class="ico_idx ico_1"></i>
        你好
    </div>--%>
    <div class="maincon clearfix">
        <div class="col-sm-12 todoLists">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne" data-toggle="collapse" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne"><i class="ico_idx ico_3"></i>待办事项<i class="fa fa-angle-up fa-lg"></i></div>
                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        <div id="item" nodeid="142" class="li li-1" style="display: none;"><a href="javascript:void(0)" class="clearfix" taregt="_blank" onclick="locationTo('../base/reported.jsp')" ><span class="pull-left"><b>报表数据填写</b><br>[<span class="numb">0</span>条]</span><i class="pull-right li-pic li-pic-1"></i></a></div>
                        <!-- <div id="itemAdjust" nodeid="145" class="li li-2" style="display: block;"><a href="javascript:void(0)" onclick="locationTo('pages/itemManager/audioItemBudgetAdjust.jsp')" class="clearfix" taregt="_blank"><span class="pull-left"><b>项目调整审核</b><br>[<span class="numb">0</span>条]</span><i class="pull-right li-pic li-pic-2"></i></a></div>-->                       
                        <!-- <div id="investPlan" nodeid="173" class="li li-3" style="display: block;"><a href="javascript:void(0)" onclick="locationTo('pages/investment/investPlanManagerscheck.jsp')" class="clearfix" taregt="_blank"><span class="pull-left"><b>投资计划申报审核</b><br>[<span class="numb">0</span>条]</span><i class="pull-right li-pic li-pic-3"></i></a></div> -->
                    </div>
                </div>
            </div>
        </div>
       <!--  <div class="col-sm-6 dataStatistics">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo" data-toggle="collapse" href="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo"><i class="ico_idx ico_4"></i>数据统计<i class="fa fa-angle-up fa-lg"></i></div>
                <div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                        <img src="img/chart.png" width="100%" alt="">
                    </div>
                </div>
            </div>
        </div> -->
    </div>
</div>
<script>
    var myTab = $("#myTab", window.parent.document);
    var myTabContent = $("#myTabContent", window.parent.document);
    var myTabBtn = $(".tab_btn", window.parent.document);
    var myTabWrap = $(".tab_wrap", window.parent.document);
    var myTabBox = $(".tabBox", window.parent.document);
    var visibleWid; //可见tab区域的宽度
    //var iframeheight = $(window).height();
    var mytabWid;

    $(document).ready(function(){
        mytabWid = myTab.find("li:eq(0)").outerWidth(true) + 5;//实际myTab的宽度
    })

    //console.log(parent.arr_nodeid);
    //console.log(parent.arr_nodename);
    //console.log(parent.arr_nodeurl)

    function locationTo(url, treeId, treeNode){
    	//alert($(window).height());
        var index;
        var ohref = url;
        var nodeid, nodename;
        //console.log(typeof parent.arr_nodeurl);
        for(var i=0; i<parent.arr_nodeurl.length; i++){
            if(ohref == parent.arr_nodeurl[i]){
                index = i;
            }
        }
        nodeid = parent.arr_nodeid[index];
        nodename = parent.arr_nodename[index];

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

    $(function() {
        //maincon高度
        var panelheight = $(window).height() - $(".welcomeInfo").outerHeight(true) - $(".panel-heading:eq(0)").outerHeight() - 15;
        $(".panel-body").css({height:panelheight});

        var loginUser; // 当前登录用户，从上一层页面中获得;
        var userwork;
        var timer = setInterval(function(){
            if($('#username').text() == ""){
                loginUser = parent.loginuser;
                //userwork = parent.userwork;
                if (loginUser!=null){
                	$('#username').html(loginUser.name); // 欢迎语
                }
                /*if(userwork!=null && userwork.iswork){
                	$('#item').css('display', 'block');
                	$('#item').find('.numb').text(userwork.count);
                }*/
            }else{
                clearInterval(timer);
            }
        }, 500);

        nowTime();
        setInterval(nowTime, 1000); // 显示当前时间
    });

    function nowTime() {
        //当前日期
        var dweek = ['日','一','二','三','四','五','六'];
        var today = new Date();
        var dday = today.getDay();
        $("#nowdate").html(today.toLocaleString() +" 星期" + dweek[dday]);
    }

</script>
</body>
</html>
