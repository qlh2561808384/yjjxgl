$(document).ready(function(){
	$(".sel_radio").attr("readonly","readonly");
	$(".ztreeBox").each(function(k){
		k++;
		var ztreeBox = $(this);
		var zNodes_r = [];
		var idVal = 0, pIdVal = 0, openVal = false, nocheckVal = false;
		var sel = ztreeBox.find(".sel_radio");
		var treedemo = ztreeBox.find(".treeDemo_radio");
		treedemo.attr("id", "treeDemo_radio_" + k);

		ztreeBox.find("li").each(function(i){
			var parentUlLen = $(this).parents("ul").length;

			//判断li下是否有ul子集
			if($(this).find("li").length > 0){
				openVal = true;
				if(ztreeBox.hasClass("yes")) nocheckVal = false;
				else if(ztreeBox.hasClass("no")) nocheckVal = true;
			}else{
				openVal = false;
				nocheckVal = false;
			}
			
			if(parentUlLen == 1){//第一级
				idVal = $(this).index() + 1;
				pIdVal = 0;
			}else{
				var parentUl = $(this).parent("ul");
				var temp = $(this).index() + 1;
				if(parentUlLen==2){
					pIdVal = parentUl.parent().index() + 1;
				}else if(parentUlLen==3){
					pIdVal = (parentUl.parent().parent().parent().index() + 1).toString() + (parentUl.parent().index() + 1);
				}else if(parentUlLen==4){
					pIdVal = (parentUl.parent().parent().parent().parent().parent().index() + 1).toString() + (parentUl.parent().parent().parent().index() + 1) + (parentUl.parent().index() + 1);
				};
				idVal = pIdVal + temp.toString();
			}
			zNodes_r.push({
				id : idVal,
				pId : pIdVal,
				name : $(this).find("a:eq(0)").text(),
				open : openVal,
				nocheck: nocheckVal
			});
		});
		var setting_r = {
			check: {
				enable: true,
				chkStyle: "radio",
				radioType: "all"
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: function(e, treeId, treeNode){
					var zTree = $.fn.zTree.getZTreeObj("treeDemo_radio_" + k);
					zTree.checkNode(treeNode, !treeNode.checked, null, true);
					return false;
				},
				onCheck: function(e, treeId, treeNode){
					var zTree = $.fn.zTree.getZTreeObj("treeDemo_radio_" + k),
					nodes = zTree.getCheckedNodes(true),
					v = "";
					for (var i=0, l=nodes.length; i<l; i++) {
						v += nodes[i].name + ",";
					}
					if (v.length > 0 ) v = v.substring(0, v.length-1);
					sel.attr("value", v);
				}
			}
		};
		$.fn.zTree.init(treedemo, setting_r, zNodes_r);
		sel.bind('click', function(){showMenu_r(sel, treedemo);});
	});
});

function showMenu_r(obj, showObj) {
	var cityOffset = obj.position();
	showObj.css({left:cityOffset.left, top:cityOffset.top + obj.outerHeight()}).slideDown("fast");
	
	$(document).bind("mouseup", function(e){
		if(!showObj.is(e.target) && !obj.is(e.target) && showObj.has(e.target).length === 0){
			showObj.hide();
		}
	});
};