(function($){
    $.extend({
        //debugModal:true,//是否使用debug模式  确保每次重新获取静态文件
        //globalValidConfig:{},
        //treeConfig:{},
        //textBoxConfig:{defaultValue:'测试'},
        //dateBoxConfig:{},
        //comboBoxConfig:{},
        //comboTreeConfig:{},
        //searchTreeConfig:{},
        dajaxConfig:{        //dajax 默认值
         sendbefore:function(req){    //请求前方法
             req.setRequestHeader("key",top.currkey);
             req.setRequestHeader("sessID",top.sessId);
         }

         }

    })
})(jQuery);