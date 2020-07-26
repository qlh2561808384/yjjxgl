
$(document).ready(function(){
    //桌面图标排列
    var deskItem = $("#deskMain .deskItem");
    var hasLiNum = deskItem.length;
    var leftLen = 100, topLen = 100;
    var colNum = Math.round($("#deskMain").height()/topLen),
        rowNum = 0,
        totalRowNum = Math.ceil(hasLiNum/colNum);
    $.each(deskItem, function(i){
        $(this).css({left:leftLen*rowNum, top:topLen*(i%colNum)});
        if((i+1)%colNum == 0 && i!=0) rowNum++;
    });
    //底部栏 点击tab
    $("#deskBtmTab").on("click", "li", function(){
        var layerId = $(this).attr("data-layerId");
        var layero = $("#"+layerId);
        var max = arrSort()+1;
        if(layero.is(":hidden")){ //该窗口隐藏状态时，显示
            layero.show()
                .css({zIndex: max+1})
                .attr("data-zindex", max+1);
            $(this).addClass("tabActive").siblings().removeClass("tabActive");
        }else if(!$(this).hasClass("tabActive")){ //该窗口不隐藏且不是置顶状态时，置顶
            if(max != parseInt($(this).attr("data-zindex"))){
                layero.css({zIndex: max+1})
                    .attr("data-zindex", max+1);
                $(this).addClass("tabActive").siblings().removeClass("tabActive");
            }
        }else if($(this).hasClass("tabActive")){ //该窗口置顶时，隐藏
            layero.hide();
            $(this).removeClass("tabActive");
        }
    });
    //点击桌面图标
    $("#deskMain").on("dblclick", ".deskItem", function(){
        openWindow($(this), $(this).attr("data-url"));
    }).on("click", ".deskItem", function(){
        $(this).addClass("deskItemAcyive").siblings().removeClass("deskItemAcyive");
    });
    $(".deskMain").click(function(e){
        var targetClass = $(e.target).attr("class");
        if(targetClass.indexOf("deskItem") == -1 && !$(e.target).parent().is(".deskItem")){
            $(".deskItem").removeClass("deskItemAcyive");
        }
    });

    //点击左下角图标，显示桌面
    $("#showDesk").on("click", function(){
        $(".layui-layer").hide();
        $("#deskBtmTab li").removeClass("tabActive");
    })
});

//打开窗口
function openWindow(obj, url){
    var objDataid = obj.attr("data-id"),
        winImg = obj.find(".item-img").attr("src"),
        winTitle = obj.find(".item-txt").text(),
        imgTxtHtml = "<img src='"+ winImg +"' />" + winTitle,
        deskBtmTab = $("#deskBtmTab"),
        layerId;
    //若该窗口不存在
    if($(".layui-layer[data-id='"+objDataid+"']").length==0){
        $.dopen({
            type: 2,
            title: "<div class='windowTit'>" + imgTxtHtml+ "</div>",
            area: ['700px', '400px'],
            content: url,
            shade: 0,
            success: function(layero, index){
                layerId = layero.attr("id");

                //插入layer弹窗并置顶显示
                var tabli = "<li class='desk_icon tabActive' data-layerId='"+ layerId +"'>"+ imgTxtHtml +"</li>";
                deskBtmTab.find("li").removeClass("tabActive");
                deskBtmTab.append(tabli);
                var max = arrSort()+1;
                layero.css({zIndex: max}).attr({"data-zindex":layero.css("z-index"), "data-id":objDataid});

                var ulMaxWid = $(".deskBottom").width()-$("#showDesk").outerWidth();//底部tab最大宽度
                var ulCurWid = 0;
                $.each(deskBtmTab.find("li"), function(){
                    ulCurWid += $(this).outerWidth();
                });
                //如果tab标签打开过多，减小标签宽度
                if(ulCurWid >= ulMaxWid){
                    deskBtmTab.css("max-width", ulMaxWid);
                    var len = deskBtmTab.find("li").length;
                    deskBtmTab.find("li").width(Math.floor(ulMaxWid/len)-30);
                }
            },
            min: function(layero){
                layero.hide();
                layer.restore();//隐藏时还原弹窗大小
                deskBtmTab.find("[data-layerId="+ layerId +"]").removeClass("tabActive");
            },
            full: function(layero){
                //修改最大化时弹窗的高度，只占满deskMain的高度
                var layerMaxH = $("#deskMain").outerHeight(true);
                var iframeMaxH = layerMaxH - $(".layui-layer-title").outerHeight();
                layero.css({height:layerMaxH, top: $(".deskTop").outerHeight()});
                layero.find("iframe").css("height", iframeMaxH);
            },
            end: function(){
                //底部tab标签删除
                deskBtmTab.find("[data-layerId="+ layerId +"]").remove();
            }
        });
    }else{ //已存在该窗口，则置顶
        var layero = $(".layui-layer[data-id='"+objDataid+"']");
        var max = arrSort()+1;
        layero.show().css({zIndex: max}).attr({"data-zindex":layero.css("z-index")});
    }
}
//zindex值组成数组，排序后返回最大值
function arrSort(){
    var zindexArr = [];
    $(".layui-layer").each(function(){
        zindexArr.push(parseInt($(this).attr("data-zindex")));
    });
    zindexArr.sort(function(a,b){return b-a});//数组倒序
    return zindexArr[0];
}