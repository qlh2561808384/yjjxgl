
(function ($) {

    $.fn.layoutPanel = function(options){
        var body = $(this);
        var settings = {
            region:"top,left,middle,right,bottom", //可显示的区域
            leftWidth: 200, //fixed布局时，左侧栏宽度
            rightWidth: 200, //fixed布局时，右侧栏宽度
            leftMinWidth: 40, //收缩后左侧最小宽度
            rightMinWidth: 50, //收缩后右侧最小宽度
            leftToggle: false, //左侧是否有可以展开收缩
            rightToggle: false //右侧是否有可以展开收缩
        };
        options = $.extend({}, settings, options);

        if(options.region.indexOf("top") != -1){
            body.append('<div class="layoutPanelTop"></div>');
        }
        if(options.region.indexOf("left") != -1){
            body.append('<div class="layoutPanelLeft"></div>');
        }
        if(options.region.indexOf("middle") != -1){
            body.append('<div class="layoutPanelMid"></div>');
        }
        if(options.region.indexOf("right") != -1){
            body.append('<div class="layoutPanelRight"></div>');
        }
        if(options.region.indexOf("bottom") != -1){
            body.append('<div class="layoutPanelBottom"></div>');
        }

        var panelTop = $(".layoutPanelTop");
        var panelLeft = $(".layoutPanelLeft");
        var panelMid = $(".layoutPanelMid");
        var panelRight = $(".layoutPanelRight");
        var panelBottom = $(".layoutPanelBottom");

        //主体main(除top、bottom外的部分)高度
        var mainHeight = $(window).height() - panelTop.outerHeight(true) - panelBottom.outerHeight(true);
        panelLeft.height(mainHeight).css({top: panelTop.outerHeight(true)});
        panelMid.height(mainHeight).css({top: panelTop.outerHeight(true)});
        panelRight.height(mainHeight).css({top: panelTop.outerHeight(true)});

        //主体main内部左中右各部分宽度
        var leftWidth = 0, rightWidth = 0;
        if(panelLeft.length>0){
            panelLeft.width(options.leftWidth);
            leftWidth = options.leftWidth;
        }
        if(panelRight.length>0){
            panelRight.width(options.rightWidth);
            rightWidth = options.rightWidth;
        }
        panelMid.width($(window).width() - leftWidth - rightWidth).css({left: leftWidth});

        //左/右侧边是否可以点击收缩展开
        if(panelLeft.length>0 && options.leftToggle){
            panelLeft.append('<div class="sideToggle leftToggle" id="leftToggle"></div>')
                .children(".sideToggle")
                .append('<i class="fa fa-angle-double-left"></i>');
            sideToggle("left");
        }
        if(panelRight.length>0 && options.rightToggle){
            panelRight.append('<div class="sideToggle RightToggle" id="RightToggle"></div>')
                .children(".sideToggle")
                .append('<i class="fa fa-angle-double-right"></i>');
            sideToggle("right");
        }

        //左/右侧边栏展开收缩
        function sideToggle(direction){
            var target;
            var panelMid = $(".layoutPanelMid");
            var nowClass, toggleClass, minWidth;
            nowClass = "fa-angle-double-" + direction;
            if(direction == "left"){
                target = panelLeft;
                toggleClass = "fa-angle-double-right";
                minWidth = options.leftMinWidth;
            }else{
                target = panelRight;
                toggleClass = "fa-angle-double-left";
                minWidth = options.rightMinWidth;
            }
            var toggleSwitch = target.find(".sideToggle i.fa");
            var toggleWidth, maxWidth = target.outerWidth();
            var panelmid_ml, panelmid_mr;
            toggleSwitch.on("click", function(){
                if(toggleSwitch.hasClass("animated")){ //展开
                    toggleWidth = maxWidth;
                    toggleSwitch.removeClass("animated");
                    toggleSwitch.addClass(nowClass).removeClass(toggleClass);

                    if(direction == "left"){
                        panelmid_ml = maxWidth;
                    }else{
                        panelmid_mr = maxWidth;
                    }
                }else{ //收缩
                    toggleWidth = minWidth;
                    toggleSwitch.addClass("animated");
                    toggleSwitch.addClass(toggleClass).removeClass(nowClass);

                    if(direction == "left"){
                        panelmid_ml = minWidth;
                    }else{
                        panelmid_mr = minWidth;
                    }
                }
                target.animate({
                    width: toggleWidth
                }, 500);

                panelMid.animate({
                    left: panelmid_ml,
                    right: panelmid_mr
                }, 500);
            })
        }
    };
})(jQuery);


