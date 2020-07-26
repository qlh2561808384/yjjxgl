(function($){
    $.fn.tableEditor=function(options){
        var args = Array.prototype.slice.call(arguments, 1);
        args.unshift(this);
        return editLineMethod[options].apply(this,args);
    }
    var editLineMethod={
        //todo 初始化
        init:function(tableObj,index){
            var table = $(this).data('bootstrap.table'),
                $table=$(tableObj),
                columns=table.columns,
                datas = table.data,
                $trs=table.$tableBody.find("tr"),
                data = datas[index],
                copyData= $.extend({},data);
            $.each(columns,function(colNum,column){
                if(column.editor){
                    var field=column.field+"",
                        selector="[data-index='"+index+"']",
                        $tr=$table.find(selector),
                        textValue= column.editor.mappingValue==undefined ? (data[field]==null?"":data[field]) :data[column.editor.mappingValue],
                        $td= $tr.find("td").eq(colNum).css("overflow", "auto");
                    $tr.find("td").css({'padding':'2px 0px','vertical-align':'middle'});
                    $td.attr({"oldValue":textValue,"colNum":colNum,"type":column.editor.type});
                    var $inlineInput = $("<input/>");
                    $inlineInput.appendTo($td.empty())[column.editor.type](column.editor);
                    $inlineInput[column.editor.type]("setValue",textValue);
                }
            })
        },
        // todo 隐藏
        cancel:function(tableObj,index){
            var table = $(this).data('bootstrap.table'),
                $table=$(tableObj),
                columns=table.columns,
                datas = table.data,
                $trs=table.$tableBody.find("tr"),
                data = datas[index],
                copyData= $.extend({},data);
            $.each(columns,function(colNum,column){
                if(column.editor){
                    var field=column.field+"",
                        selector="[data-index='"+index+"']",
                        $tr=$table.find(selector),
                        textValue=data[field]==null?"":data[field],
                        $td= $tr.find("td").eq(colNum),
                        type=$td.attr("type");

                        var t=$td.find(".form-control")
                    if(t.length>0){
                        t[type]("destroy");
                    }
                }
                $table.bootstrapTable("updateRow",index,copyData)
            })
        },
        //todo 提交
        update:function(tableObj,index){
            var table = $(this).data('bootstrap.table'),
                $table=$(tableObj),
                columns=table.columns,
                datas = table.data,
                data = datas[index],
                copyData= $.extend({},data);
            $.each(columns,function(colNum,column){
                if(column.editor){
                    var field=column.field+"",
                        selector="[data-index='"+index+"']",
                        $tr=$table.find(selector),
                        $td= $tr.find("td").eq(colNum),
                        type=$td.attr("type"),
                        value =$td.find(".form-control")[type]("getValue"),
                        text =$td.find(".form-control")[type]("getText");
                    data[field]=value;
                    if(column.editor.mappingText){
                        data[column.editor.mappingText]=text
                    }
                    if(column.editor.mappingValue){
                        data[column.editor.mappingValue]=value
                    }
                }

            });
            if(table.options.onEditLineSave){
                if(table.options.onEditLineSave.apply(this,[index,data,copyData])){
                    $.each(columns,function(colNum,column){
                        if(column.editor){
                            var field=column.field+"",
                                selector="[data-index='"+index+"']",
                                $tr=$table.find(selector),
                                $td= $tr.find("td").eq(colNum),
                                type=$td.attr("type");
                            $td.find(".form-control")[type]("destroy");
                        }
                    });
                    $table.bootstrapTable("updateRow",index,data)
                }
            }
        }
    }
})(jQuery);