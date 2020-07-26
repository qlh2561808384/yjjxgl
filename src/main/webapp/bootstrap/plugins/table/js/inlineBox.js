function thousands(num){
    num = num.toString();   //将输入的数字转换为字符串
    if(/^-?\d+(\.\d+)?$/.test(num)){  //判断输入内容是否为整数或小数
        if(/^-?\d+$/.test(num)){    //判断输入内容是否为整数
            num =num + ",00";   //将整数转为精度为2的小数，并将小数点换成逗号
        }else{
            var len=num.indexOf(".");
            if(num.length-len>3){
                num=num.substr(0,len+3);
            }else if(num.length-len<3){
                num+='0';
            }
            num = num.replace(/\./,',');    //将小数的小数点换成逗号
        }

        while(/\d{4}/.test(num)){ //
            /***
             *判断是否有4个相连的数字，如果有则需要继续拆分，否则结束循环；
             *将4个相连以上的数字分成两组，第一组$1是前面所有的数字（负数则有符号），
             *第二组第一个逗号及其前面3个相连的数字；
             * 将第二组内容替换为“,3个相连的数字，”
             ***/
            num = num.replace(/(\d+)(\d{3}\,)/,'$1,$2');
        }
        num = num.replace(/\,(\d*)$/,'.$1');   //将最后一个逗号换成小数点
        return num;
    }
}

(function($){
    var inlineBoxMethod={
        cancel:function(obj){
            var oldData=$(this).data('oldData');
            $(obj).bootstrapTable("load",oldData);
            $(obj).inlineBox("init");

        },
        getNewData:function(){
            return $(this).data("newData");
        },
        submit:function(){

        },
        init:function(){
            var table = $(this).data('bootstrap.table'),
                columns=table.columns,//列属性
                trs=table.$tableBody.find("tr"),
                data=table.data,//原数据
                oldData=$.extend(true, [], data);
            $(this).data('oldData',oldData);
            //遍历列参数
            $.each(columns,function(colNum,column){
                //如果带有 inlineBox 属性
                if(column.inlineBox){
                    //字段名
                    var field=column.field+"";
                    //遍历表格行
                    $.each(trs,function(index,tr){
                        if($(tr).attr("data-index")!=null){
                            // 根据字段名 和行index 获取表格数据中对应 字段数据
                            var textValue=data[index-1][field]==null?"":data[index-1][field],
                            //根据列参数index 获取 表格单元格
                                $td= $(tr).find("td").eq(colNum);
                            $td.attr({"oldValue":textValue,"colNum":colNum});
                            //可编辑数据 单元格背景色   区分单双行
                            if(Number($td.parent().attr("data-index"))%2==0){
                                $td.css("background-color","#D8E3F2");//初始化 oldValue
                            }else{
                                $td.css("background-color","#EDF2F5");
                            }

                            // 点击单元格事件  1.清除原有html 2插入input标签 3.初始化textbox
                            $td.off("click").on("click",function(e){
                                //点击目标为 input
                                if($(e.target).hasClass("form-control")){
                                    return
                                }
                                var $inlineInput = $("<input/>");
                                $inlineInput.appendTo($(this).empty()).textBox(/*{required:true}*/column.inlineBox);
                                var textBox=$inlineInput.data("textBox");
                                textBox.setValue($td.attr("oldValue"));
                                textBox.$textBox.select();
                                //textbox 失焦 ～验证 ？ 1.数据赋值  2.元素赋值 3.隐藏或销毁 4.修改样式
                                textBox.$textBox.off("blur").on("blur",function(){
                                    textBox.setValue(textBox.$textBox.val());
                                    if($inlineInput.textBox("validate")){
                                        var newValue=textBox.$textBox.val(),
                                            htmlText='';
                                        if(column.valformatter){
                                            newValue=column.valformatter.call(this,newValue);
                                        }
                                        data[index-1][field]=newValue;//
                                        if(column.formatter){
                                            htmlText=column.formatter.call(this,newValue);
                                        }else{
                                            htmlText=newValue
                                        }
                                        $td.attr("oldValue",newValue);
                                        //修改后背景色  区分单双行
                                        if(Number($td.parent().attr("data-index"))%2==0){
                                            $td.css( 'background-color','#FCE8CD');//2
                                        }else{
                                            $td.css( 'background-color','#FFF2E1');//
                                        }

                                        table.updateRow(index,data[index-1]);

                                        $td.html(htmlText);
                                        if(column.edited){
                                            column.edited.call(this,newValue)
                                        }
                                    }
                                });
                                textBox.$textBox.off("keydown").on("keydown",function(e){
                                    var $thistd=textBox.$textBox.parent().parent();
                                    if(e.keyCode==13){
                                        findNextTd($thistd,-1,"N");
                                        this.blur()
                                    }else if(e.keyCode==40){
                                        findNextTr($thistd.parent(),$thistd.attr("colnum"),"N")
                                    }else if(e.keyCode==39){
                                        findNextTd($thistd,-1,"N");
                                    }else if(e.keyCode==37){
                                        findNextTd($thistd,-1,"P");
                                    }else  if(e.keyCode==38){
                                        findNextTr($thistd.parent(),$thistd.attr("colnum"),"P")
                                    }
                                });
                                /**
                                 * @param $td 单元格$对象
                                 * @param colNum 列index  -1表空
                                 * @param direction 选择方向  P：上一行 N：下一行
                                 */
                                function findNextTd($td,colNum,direction){
                                    if(direction=="P"){
                                        $nextTd= $td.prevAll("[oldvalue]").last();
                                    }else{
                                        $nextTd= $td.nextAll("[oldvalue]").eq(0);
                                    }
                                    if($nextTd.length==0){
                                        findNextTr($td.parent(),colNum,direction)
                                    }else{
                                        $nextTd.click()
                                    }
                                }

                                /**
                                 * @param $tr 表格行$对象
                                 * @param colNum 列index  -1
                                 * @param direction 选择方向  P：上一行 N：下一行
                                 */
                                function findNextTr($tr,colNum,direction){
                                    colNum=Number(colNum);
                                    if(direction=="P"){
                                        $nextTr= $tr.prev("tr");
                                    }else{
                                        $nextTr= $tr.next("tr");
                                    }
                                    if($nextTr.length==0){
                                        return
                                    }
                                    if(direction=="P"){
                                        if(colNum==-1){
                                            $nextLineTd=$nextTr.find("[oldvalue] :last");
                                        }else{
                                            $nextLineTd=$nextTr.find("td").eq(colNum);
                                        }
                                    }else{
                                        if(colNum==-1){
                                            $nextLineTd=$nextTr.find("[oldvalue] :eq(0)");
                                        }else{
                                            $nextLineTd=$nextTr.find("td").eq(colNum);
                                        }
                                    }
                                    if($nextLineTd.length==0){
                                        findNextTr($nextTr,colNum,direction)
                                    }else{
                                        $nextLineTd.click()
                                    }
                                }
                            });
                        }
                    });
                }
            });
        }
    };

    $.fn.inlineBox=function(method){
        var args = Array.prototype.slice.call(arguments, 1);
        args.unshift(this);
        return inlineBoxMethod[method].apply(this,args);
    }

})(jQuery);