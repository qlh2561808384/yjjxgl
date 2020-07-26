
var arr_nodeid = new Array(), arr_nodename = new Array(), arr_nodeurl = new Array();

$.fn.dtree = function(options) {
	var treeobj = $(this);
	var tree;
	var defaultsetting = {
		data:{ // 必须使用data  
			simpleData : {
				enable : true,
				idKey : "id", // id编号命名 默认
				pIdKey : "pId", // 父id编号命名 默认
				rootPId : "0" // 用于修正根节点父节点数据，即 pIdKey 指定的属性值
			},
			key:{
				checked:"checked",//zTree 节点数据中保存 check 状态的属性名称。默认值："checked"请勿与 zTree 节点数据的其他参数冲突，例如：checkedOld
				children:"children",//zTree 节点数据中保存子节点数据的属性名称。
				name:"name",//zTree 节点数据保存节点名称的属性名称。
				title:"",//zTree 节点数据保存节点提示信息的属性名称。[setting.view.showTitle = true 时生效]如果设置为 "" ，则自动与 setting.data.key.name 保持一致，避免用户反复设置
				url:""//zTree 节点数据保存节点链接的目标 URL 的属性名称。特殊用途：当后台数据只能生成 url 属性，又不想实现点击节点跳转的功能时，可以直接修改此属性为其他不存在的属性名称
			}
		}
	}
	options = $.extend({}, defaultsetting, options);

	if(options.localdata!=null){
		tree= $.fn.zTree.init(treeobj,options,options.localdata);

	}else if(options.url!=null){
		$.dajax({
			url: options.url,
			async:false,
			success: function(data1) {
				tree=$.fn.zTree.init(treeobj, options, data1);
			}
		});
	}else{
		tree=$.fn.zTree.init(treeobj,options);
	}

	//获取所有节点信息
	var tobj = $.fn.zTree.getZTreeObj("treeSide");
	var node = tobj.getNodes();
	var nodes = tobj.transformToArray(node);

	for(var i=0;i<nodes.length;i++){
		if(nodes[i][tobj.setting.data.key.url]){
			//console.log(nodes[i])
			//console.log(tobj.setting.data.simpleData.idKey)
			//console.log(nodes[i][tobj.setting.data.simpleData.idKey])
			arr_nodeid.push(nodes[i][tobj.setting.data.simpleData.idKey]);
			arr_nodename.push(nodes[i][tobj.setting.data.key.name]);
			arr_nodeurl.push(nodes[i][tobj.setting.data.key.url]);
		}
	}

	//单个展开
	var nodes = tree.transformToArray(tree.getNodes());
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].isOpen){
			tree.expandNode(nodes[i], true, true, true);
		}
	}

	return tree;
}

var curExpandNode = null;
function beforeExpand(treeId, treeNode) {
	var pNode = curExpandNode ? curExpandNode.getParentNode():null;
	var treeNodeP = treeNode.parentTId ? treeNode.getParentNode():null;
	var zTree = $.fn.zTree.getZTreeObj("treeSide");
	for(var i=0, l=!treeNodeP ? 0:treeNodeP.children.length; i<l; i++ ) {
		if (treeNode !== treeNodeP.children[i]) {
			zTree.expandNode(treeNodeP.children[i], false);
		}
	}
	while (pNode) {
		if (pNode === treeNode) {
			break;
		}
		pNode = pNode.getParentNode();
	}
	if (!pNode) {
		singlePath(treeNode);
	}

}
function singlePath(newNode) {
	if (newNode === curExpandNode) return;
	var zTree = $.fn.zTree.getZTreeObj("treeSide"),
		rootNodes, tmpRoot, tmpTId, i, j, n;

	if (!curExpandNode) {
		tmpRoot = newNode;
		while (tmpRoot) {
			tmpTId = tmpRoot.tId;
			tmpRoot = tmpRoot.getParentNode();
		}
		rootNodes = zTree.getNodes();
		for (i=0, j=rootNodes.length; i<j; i++) {
			n = rootNodes[i];
			if (n.tId != tmpTId) {
				zTree.expandNode(n, false);
			}
		}
	} else if (curExpandNode && curExpandNode.open) {
		if (newNode.parentTId === curExpandNode.parentTId) {
			zTree.expandNode(curExpandNode, false);
		} else {
			var newParents = [];
			while (newNode) {
				newNode = newNode.getParentNode();
				if (newNode === curExpandNode) {
					newParents = null;
					break;
				} else if (newNode) {
					newParents.push(newNode);
				}
			}
			if (newParents!=null) {
				var oldNode = curExpandNode;
				var oldParents = [];
				while (oldNode) {
					oldNode = oldNode.getParentNode();
					if (oldNode) {
						oldParents.push(oldNode);
					}
				}
				if (newParents.length>0) {
					zTree.expandNode(oldParents[Math.abs(oldParents.length-newParents.length)-1], false);
				} else {
					zTree.expandNode(oldParents[oldParents.length-1], false);
				}
			}
		}
	}
	curExpandNode = newNode;
}

function onExpand(event, treeId, treeNode) {
	curExpandNode = treeNode;
}

function onClick(e,treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeSide");
	zTree.expandNode(treeNode, null, null, null, true);

	var ohref = treeNode[zTree.setting.data.key.url];
	var nodename = treeNode[zTree.setting.data.key.name];
	var nodeid= treeNode[zTree.setting.data.simpleData.idKey];

	var myTab = $("#myTab");
	var myTabContent = $("#myTabContent");
	var myTabBtn = $(".tab_btn");
	var myTabWrap = $(".tab_wrap");
	var visibleWid; //可见tab区域的宽度
    var tabBoxWid = $(".tabBox").outerWidth();

	//插入标签页
	if(ohref){
		mytabWid = myTab.outerWidth(true);
		if($("#tabli-"+nodeid).length == 0){ //myTab中还没有该标签页
			//插入tab
			var tabli_html = '<li class="active" id="tabli-'+nodeid+'"><a href="#tab-'+ nodeid +'" id="tabclose-'+nodeid+'" data-toggle="tab">'+ nodename +' <i class="fa fa-times-circle"></i></a></li>';
			var tabpane_html = "";
			tabpane_html += '<div class="tab-pane fade in active" id="tab-'+ nodeid +'">';
			tabpane_html += '<iframe src="'+ ohref +'" frameborder="0" class="iframeCon" id="iframe-'+ nodeid +'" width="100%" height="'+ iframeheight +'"></iframe>';
			tabpane_html += '</div>';
			myTab.find("li").removeClass("active");
			myTabContent.find(".tab-pane").removeClass("in active");
			myTab.append(tabli_html);
			myTabContent.append(tabpane_html);

			myTabBtn.find(".closeAll").removeClass("hide"); //显示“关闭所有”按钮
			visibleWid = tabBoxWid - myTabBtn.outerWidth(true); //可见tab区域的宽度
			mytabWid += $("#tabli-"+nodeid).width(); //插入tab后mytab的宽度

			if(mytabWid > visibleWid){ //mytab总长 > 可见tab区域
				myTabBtn.find(".showtab").removeClass("hide"); //显示“向左向右”按钮
				visibleWid = tabBoxWid - myTabBtn.outerWidth(true); //此时按钮区域宽度变大
				myTab.width(mytabWid); //mytab宽度
			}else{
				myTab.width("auto");
			}
			myTabWrap.width(visibleWid); //设置可见tab区域的宽度
			myTab.animate({left : "-" + (mytabWid - visibleWid) + "px"}, 200);

		}else{ //该tab已打开
			$("#tabli-"+nodeid).addClass("active").siblings().removeClass("active");
			$("#tab-"+nodeid).addClass("in active").siblings().removeClass("in active");
			//当该标签页不在tab可见区域内时，滑动到该标签
			visibleWid = tabBoxWid - myTabBtn.outerWidth(true); //可见tab区域的宽度
			if(mytabWid > visibleWid){
				var lileft = $("#tabli-"+nodeid).position().left;//当前标签页相对父元素的left偏移量
				var diff = mytabWid - lileft;
				if(diff <= visibleWid){
					myTab.animate({left : "-" + (mytabWid - visibleWid) +"px"}, 500);
				}else{
					myTab.animate({left : "-" + lileft +"px"}, 500);
				}
			}
		}
        //当窗口大小变化时
        $(window).resize(function(){
            visibleWid = $(".tabBox").outerWidth() - myTabBtn.outerWidth(true); //可见tab区域的宽度
            myTabWrap.width(visibleWid); //设置可见tab区域的宽度
        });
	};
	//关闭标签页
	$("#tabclose-"+nodeid+" .fa-times-circle").on("click",function(){
		//设置mytab宽度
		mytabWid -= $("#tabli-"+nodeid).width(); //关闭tab后mytab的宽度
		myTab.width(mytabWid); //mytab宽度
		//mytab只剩下首页标签
		if(myTab.find("li").length == 2){
			myTabBtn.find(".closeAll").addClass("hide");
			myTabWrap.width("auto");
			myTab.removeAttr("style");
		}
		visibleWid = tabBoxWid - myTabBtn.outerWidth(true);
		var nowleft = Math.abs(parseInt(myTab.css("left")));//当前myTab的left值
		var diff = Math.abs(mytabWid - visibleWid);
		if((mytabWid > visibleWid) && (diff != nowleft)){
			myTab.animate({left : "-" + diff +"px"}, 500);
		}else if(mytabWid <= visibleWid){
			//去掉关闭标签等按钮
			myTabBtn.find(".showtab").addClass("hide");
			visibleWid = $(".tabBox").outerWidth() - myTabBtn.outerWidth(true);
			myTabWrap.width(visibleWid);
			myTab.removeAttr("style").animate({left:0}, 500);
		}

		//关闭标签页，关闭当前页则前一页active
		var tab_li = $(this).parents("li");
		var tabpane_id = tab_li.find("a").attr("href");
		if(tab_li.hasClass("active")){
			tab_li.prev().addClass("active");
			$(tabpane_id).prev().addClass("in active");
		}
		tab_li.remove();
		$(tabpane_id).remove();
	});
}

var iframeheight;
var mytabWid;
var windowHeight = $(window).height();

$(document).ready(function(){
	mytabWid = $("#myTab li:eq(0)").outerWidth(true) + 5;//实际myTab的宽度

	//设定content，iframe的高度
	$(".main-content").css({height: windowHeight});

	iframeheight = windowHeight - $(".navbar").outerHeight() - $(".tab_wrap").outerHeight(true) - 10;
	$(".iframeCon").height(iframeheight);

	//sideTree高度
	var sidetree_h = windowHeight - $(".userInfo").outerHeight() - $(".sidebar-toggle").outerHeight();
	$(".tree_side").css({maxHeight: sidetree_h});

	//关闭所有标签页
	$(".closeAll").click(function(){
		$("#myTab li:not(:eq(0))").remove();
		$("#myTab li:eq(0)").addClass("active");
		$("#myTabContent .tab-pane:not(:eq(0))").remove();
		$("#myTabContent .tab-pane:eq(0)").addClass("in active");

		mytabWid = $("#myTab li:eq(0)").outerWidth(true) + 5;
		$("#myTab").removeAttr("style");
		$(".tab_btn a").addClass("hide");
		$(".tab_wrap").width("auto");
	});

	//导航栏tab左右滚动
	var scrollWid = $(".tab_wrap").outerWidth()/2;//每次向前（后）滑动的宽度
	$(".showRightTabs").on("click", function(){
		var mytab = $("#myTab");
		var visibleWid = $(".tab_wrap").width();//可见tab区域的宽度
		var nowleft = Math.abs(parseInt(mytab.css("left")));//当前myTab的left值
		var scrollLen = 0;//myTab已滑动的距离
		mytab.find("li").each(function(){
			scrollLen += $(this).outerWidth(true);//myTab的宽度（=每个tab相加的和）
		});
		var scrollDiff = scrollLen - visibleWid;//myTab总共能滚动的距离
		if(!mytab.is(":animated")){
			if(nowleft >= scrollDiff){
				mytab.stop();
			}else{
				var diff = Math.abs(scrollDiff - nowleft);//myTab剩余能滚动的距离
				var lastscroll = 0;
				if(diff < scrollWid){//滚动到最后一屏时，剩余滚动的距离是否小于scrollWid
					lastscroll = diff;
				}else{
					lastscroll = scrollWid;
				}
				mytab.animate({left : "-=" + lastscroll +"px"},500);
			}
		}
	});

	$(".showLeftTabs").click(function(){
		var mytab = $("#myTab");
		var nowleft = Math.abs(parseInt(mytab.css("left")));
		if(!mytab.is(":animated")){
			if(nowleft == 0){
				mytab.stop();
			}else{
				var lastscroll = 0;
				if(nowleft < scrollWid){
					lastscroll = nowleft;
				}else{
					lastscroll = scrollWid;
				}
				mytab.animate({left : "+=" + lastscroll +"px"}, 500);
			}
		}
	});

	//点击显示/隐藏左右侧边栏
	$("#sidebar-collapse").on('click', function(){
		var sidebar = $("#sidebar");
		var scollapse = $(this);
		var scollapse_ico = scollapse.find("i");
		var class1 = scollapse_ico.attr("data-icon1");
		var class2 = scollapse_ico.attr("data-icon2");
		if(sidebar.hasClass("menu-min")){
			sidebar.removeClass("menu-min");
			scollapse_ico.removeClass().addClass(class1);
		}else{
			sidebar.addClass("menu-min");
			scollapse_ico.removeClass().addClass(class2);
		}
		var tabwid;
		if($("#myTab li").length == 1){
			tabwid = 0;
		}else{
			tabwid = $(".tab_btn").outerWidth(true);
		}
		$(".tab_wrap").width($(".tabBox").outerWidth() - tabwid);
        $(window).resize(function(){
            $(".tab_wrap").width($(".tabBox").outerWidth() - tabwid);
        })
	});

	$("#rightSide_ico").on('click', function(){
		var hidepart = $(".rightSide");
		if(hidepart.hasClass("menu-min")){
			hidepart.removeClass("menu-min");
		}else{
			hidepart.addClass("menu-min");
		}
	})
});
