var newNodes = [
    {id:1, pId:0, name:"节点1", ISLEAF:0, open:true},
    {id:11, pId:1, name:"节点1-1", ISLEAF:1},
    {id:12, pId:1, name:"节点1-2", ISLEAF:1},
    {id:2, pId:0, name:"节点2", ISLEAF:0, open:true},
    {id:21, pId:2, name:"节点2-1", ISLEAF:1}
];

function openOptions(t){
    var plugObj = $(t).parent().siblings(".view").find("[dtype]"),
        plugObjId = plugObj.attr("id"),
        dtype = plugObj.attr("dtype"),
        $view = $(t).parent().siblings(".view"),
        $sideTree = $("#sideTree"),
        nodeArr = $sideTree.tree("getTree").getNodesByParam("dragId", plugObjId),
        optionsFormId = "optionsForm";
    $.dopen({
        btn: ['保存', '取消'],
        type: 1,
        area: ['900px', '500px'],
        colseBtn: 1,
        fix: true,
        title: '属性配置',
        content: '<form id="'+ optionsFormId +'"></form>',
        btn1: function (index) {
            var plugOptions = $("#"+ optionsFormId).dform("getData");

            $.each(plugOptions, function(key, val){
                if(val == ""){
                    delete plugOptions[key]; //删除没有修改过的属性
                }else if(val == "1" || val == "0"){// true/false
                    plugOptions[key] = parseInt(val);
                }
            });
            //解析localdata数据并验证
            $.each(plugOptions, function(i, n){
                if(n!==undefined && (i=="localdata" || i=="columns" || i=="data")){
                    try {
                        plugOptions[i] = eval("(" + plugOptions[i] + ")");
                    }catch(e){
                        throw new Error("对象字符串异常");
                    }
                }
            });
            //用于保存属性弹窗中已修改过的表单组件的值
            var plugOptionsClone = cloneObj(plugOptions);

            //tree的data属性
            if(dtype.toLowerCase().indexOf("tree") != -1){
                plugOptions["data"] = {};
                var keyArr = ["checked", "children", "name", "title", "url"];
                var simpleDataArr = ["enable", "idKey", "pIdKey", "rootPId"];
                var dataArr = [keyArr, simpleDataArr];
                var dataTypeArr = ["key", "simpleData"];
                $.each(dataArr, function(i, n){
                    var dataType = dataTypeArr[i];
                    $.each(dataArr[i], function(j, m){
                        var dataVal = dataArr[i][j];
                        if(plugOptions[dataVal] != undefined){
                            if(plugOptions.data[dataType] == undefined){
                                plugOptions.data[dataType] = {};
                            }
                            plugOptions.data[dataType][dataVal] = plugOptions[dataVal];
                            delete plugOptions[dataVal];
                        }
                    });
                });
                if(Object.keys(plugOptions["data"]).length == 0){
                    delete plugOptions.data;
                }
            }

            //将form表单内修改的数据添加到dragAll中
            $.each(plugOptionsClone, function(key, val){
                nodeArr[0].dragAll[key] = plugOptionsClone[key]
            });

            $sideTree.tree("getTree").updateNode(nodeArr[0]); //更新右侧树

            //销毁原始组件，添加新的组件标签
            if(dtype == "dtable"){
                $view.empty();
            }else if(dtype != "dform"){
                plugObj[dtype]("destroy");
            }
            $view.append('<div class="layoutClass" id="'+ plugObjId +'" dtype="'+ dtype +'"></div>');
            plugObj = $view.find("[dtype="+ dtype +"]");

            plugObj[dtype](plugOptions); //初始化组件

            layer.close(index);
        }, btn2: function(index){
            layer.close(index);
        }
    });

    //属性配置框内的表单
    var optionsFormObj = $("#"+ optionsFormId);
    if(dtype == "tree"){//树
        optionsFormObj.dform({
            rownum:2,
            labelwidth:"200px",
            inputs:[
                {
                    title: "是否加“全部”rootElement",
                    name: "rootElement",
                    type: "comboBox",
                    localdata: [{id:1,text:"true"},{id:0,text:"false"}],
                    defaultvalue: "false"
                }, {
                    title: "父节点是否可选ISLEAF",
                    name: "ISLEAF",
                    type: "comboBox",
                    localdata: [{id:1,text:"true"},{id:0,text:"false"}],
                    defaultvalue: "false"
                }, {
                    title: "选择框类型checkType",
                    name: "checkType",
                    type: "comboBox",
                    localdata: [{id:"radio",text:"radio"},{id:"checkbox",text:"checkbox"}],
                    defaultvalue: "radio"
                }, {
                    title: "是否显示搜索框openSearch",
                    name: "openSearch",
                    type: "comboBox",
                    localdata: [{id:1,text:"true"},{id:0,text:"false"}],
                    defaultvalue: "false"
                }, {
                    title: "data.key.children",
                    name: "children",
                    type: "textBox",
                    placeholder: "children"
                }, {
                    title: "data.key.name",
                    name: "name",
                    type: "textBox",
                    placeholder: "name"
                }, {
                    title: "data.key.title",
                    name: "title",
                    type: "textBox"
                }, {
                    title: "data.key.url",
                    name: "dataurl",
                    type: "textBox"
                }, {
                    title: "data.simpleData.idKey",
                    name: "idKey",
                    type: "textBox",
                    placeholder: "id"
                }, {
                    title: "data.simpleData.pIdKey",
                    name: "pIdKey",
                    type: "textBox",
                    placeholder: "pId"
                }, {
                    title: "data.simpleData.rootPId",
                    name: "rootPId",
                    type: "textBox",
                    placeholder: "0"
                }, {
                    title: "远程数据url",
                    name: "url",
                    type: "textBox"
                }, {
                    title: "本地数据localdata",
                    name: "localdata",
                    type: "textBox",
                    multiline: true, //多行文本
                    colspan:"2",
                    defaultValue: zNodesStr
                }
            ]
        });
    }else if(dtype == "comboTree"){//下拉树
        optionsFormObj.dform({
            rownum:2,
            labelwidth:"200px",
            inputs:[
                {
                    title: "下拉面板高度panelHeight",
                    name: "panelHeight",
                    type: "textBox"
                }, {
                    title: "是否多选multiple",
                    name: "multiple",
                    type: "comboBox",
                    localdata: [{id:1,text:"true"},{id:0,text:"false"}],
                    defaultvalue: "false"
                }, {
                    title: "父节点是否不可选onlyLeaf",
                    name: "onlyLeaf",
                    type: "comboBox",
                    localdata: [{id:1,text:"true"},{id:0,text:"false"}],
                    defaultvalue: "false"
                }, {
                    title: "是否加“全部”rootElement",
                    name: "rootElement",
                    type: "comboBox",
                    localdata: [{id:1,text:"true"},{id:0,text:"false"}],
                    defaultvalue: "false"
                }, /*{
                    title: "选择框类型checkType",
                    name: "checkType",
                    type: "comboBox",
                    localdata: [{id:"radio",text:"radio"},{id:"checkbox",text:"checkbox"}],
                    defaultvalue: "radio"
                },*/ {
                    title: "data.key.name",
                    name: "name",
                    type: "textBox",
                    placeholder: "name"
                }, {
                    title: "data.key.title",
                    name: "title",
                    type: "textBox"
                }, {
                    title: "data.key.url",
                    name: "dataurl",
                    type: "textBox"
                }, {
                    title: "data.simpleData.idKey",
                    name: "idKey",
                    type: "textBox",
                    placeholder: "id"
                }, {
                    title: "data.simpleData.pIdKey",
                    name: "pIdKey",
                    type: "textBox",
                    placeholder: "pId"
                }, {
                    title: "data.simpleData.rootPId",
                    name: "rootPId",
                    type: "textBox",
                    placeholder: "0"
                }, {
                    title: "远程数据url",
                    name: "url",
                    type: "textBox"
                }, {
                    title: "本地数据localdata",
                    name: "localdata",
                    type: "textBox",
                    multiline: true, //多行文本
                    colspan:"2",
                    defaultValue: zNodesStr
                }
            ]
        });
    }else if(dtype == "searchTree"){//带模态框的树
        optionsFormObj.dform({
            rownum:2,
            labelwidth:"200px",
            inputs:[
                {
                    title: "模态框标题modalTitle",
                    name: "modalTitle",
                    type: "textBox"
                }, {
                    title: "是否加“全部”rootElement",
                    name: "rootElement",
                    type: "comboBox",
                    localdata: [{id:1,text:"true"},{id:0,text:"false"}],
                    defaultvalue: "false"
                }, {
                    title: "父节点是否可选ISLEAF",
                    name: "ISLEAF",
                    type: "comboBox",
                    localdata: [{id:1,text:"true"},{id:0,text:"false"}],
                    defaultvalue: "false"
                }, {
                    title: "选择框类型checkType",
                    name: "checkType",
                    type: "comboBox",
                    localdata: [{id:"radio",text:"radio"},{id:"checkbox",text:"checkbox"}],
                    defaultvalue: "radio"
                }, {
                    title: "data.key.children",
                    name: "children",
                    type: "textBox",
                    placeholder: "children"
                }, {
                    title: "data.key.name",
                    name: "name",
                    type: "textBox",
                    placeholder: "name"
                }, {
                    title: "data.key.title",
                    name: "title",
                    type: "textBox"
                }, {
                    title: "data.key.url",
                    name: "dataurl",
                    type: "textBox"
                }, {
                    title: "data.simpleData.idKey",
                    name: "idKey",
                    type: "textBox",
                    placeholder: "id"
                }, {
                    title: "data.simpleData.pIdKey",
                    name: "pIdKey",
                    type: "textBox",
                    placeholder: "pId"
                }, {
                    title: "data.simpleData.rootPId",
                    name: "rootPId",
                    type: "textBox",
                    placeholder: "0"
                }, {
                    title: "远程数据url",
                    name: "url",
                    type: "textBox"
                }, {
                    title: "本地数据localdata",
                    name: "localdata",
                    type: "textBox",
                    multiline: true, //多行文本
                    colspan:"2",
                    defaultValue: zNodesStr
                }
            ]
        });
    }else if(dtype == "textBox"){//单行&多行文本框
        optionsFormObj.dform({
            rownum:2,
            labelwidth:"200px",
            inputs:[
                {
                    title: "宽度width",
                    name: "width",
                    type: "textBox",
                    placeholder: "100%"
                }, {
                    title: "默认值defaultValue",
                    name: "defaultValue",
                    type: "textBox"
                }, {
                    title: "提示信息placeholder",
                    name: "placeholder",
                    type: "textBox"
                }, {
                    title: "是否多选multiline",
                    name: "multiline",
                    type: "comboBox",
                    localdata: [{id:1,text:"true"},{id:0,text:"false"}],
                    defaultvalue: "false"
                }
            ]
        });
    }else if(dtype == "dateBox"){//日期框
        optionsFormObj.dform({
            rownum:2,
            labelwidth:"200px",
            inputs:[
                {
                    title: "宽度width",
                    name: "width",
                    type: "textBox",
                    placeholder: "100%"
                }, {
                    title: "日期格式format",
                    name: "format",
                    type: "comboBox",
                    localdata: [{id:"yyyy-mm-dd",text:"yyyy-mm-dd"}, {id:"mm-dd",text:"mm-dd"}],
                    defaultvalue: "yyyy-mm-dd"
                }
            ]
        });
    }else if(dtype == "comboBox"){//下拉框
        optionsFormObj.dform({
            rownum:2,
            labelwidth:"200px",
            inputs:[
                {
                    title: "input宽度width",
                    name: "width",
                    type: "textBox",
                    placeholder: "100%"
                }, {
                    title: "默认选中selected",
                    name: "selected",
                    type: "textBox",
                    placeholder: "0"
                }, {
                    title: "是否添加'全部'searchOption",
                    name: "searchOption",
                    type: "comboBox",
                    localdata: [{id:"2",text:"null"}, {id:"1",text:"true"}, {id:"0",text:"false"}],
                    defaultvalue: "null"
                }, {
                    title: "下拉面板宽度panelWidth",
                    name: "panelWidth",
                    type: "textBox",
                    placeholder: "auto"
                }, {
                    title: "下拉面板高度panelHeight",
                    name: "panelHeight",
                    type: "textBox",
                    placeholder: "auto"
                }, {
                    title: "分隔符separator",
                    name: "separator",
                    type: "textBox",
                    placeholder: ","
                }, {
                    title: "是否多选multiple",
                    name: "multiple",
                    type: "comboBox",
                    localdata: [{id:"1",text:"true"}, {id:"0",text:"false"}],
                    defaultvalue: "false"
                }, {
                    title: "远程数据url",
                    name: "url",
                    type: "textBox"
                }, {
                    title: "本地数据localdata",
                    name: "localdata",
                    type: "textBox",
                    multiline: true, //多行文本
                    colspan: "2",
                    defaultValue: comboBoxDataStr
                }
            ]
        });
    }else if(dtype == "decimal"){//小数框
        optionsFormObj.dform({
            rownum:2,
            labelwidth:"200px",
            inputs:[
                {
                    title: "小数位数decimalPlaces",
                    name: "decimalPlaces",
                    type: "textBox",
                    defaultValue: "2"
                }
            ]
        });
    }else if(dtype == "dtable"){//表格
        optionsFormObj.dform({
            rownum:2,
            labelwidth:"200px",
            inputs:[
                {
                    title: "表格高度height",
                    name: "height",
                    type: "textBox"
                }, {
                    title: "无数据显示undefinedText",
                    name: "undefinedText",
                    type: "textBox",
                    placeholder: "-"
                }, {
                    title: "是否隔行变色striped",
                    name: "striped",
                    type: "comboBox",
                    localdata: [{id:1, text:"true"}, {id:0, text:"flase"}],
                    defaultvalue: "true"
                }, {
                    title: "数据请求method",
                    name: "method",
                    type: "comboBox",
                    localdata: [{id:"get", text:"get"}, {id:"post", text:"post"}],
                    defaultvalue: "get"
                }, {
                    title: "远程数据url",
                    name: "url",
                    type: "textBox"
                }, {
                    title: "列配置项columns",
                    name: "columns",
                    type: "textBox",
                    multiline: true,
                    colspan: 2,
                    defaultValue: tableColStr
                }, {
                    title: "本地数据data",
                    name: "data",
                    type: "textBox",
                    multiline: true,
                    colspan: 2,
                    defaultValue: tableDataStr
                }
            ]
        });
    }

    //重新打开属性窗口后，加载已设置过的表单组件的值
    if(nodeArr[0] && nodeArr[0].dragAll){
        $.each(nodeArr[0].dragAll, function(i, n){
            if(i=="localdata" || i=="columns" || i=="data"){
                nodeArr[0].dragAll[i] = JSON.stringify(n);
            }
        });
        optionsFormObj.dform("load", nodeArr[0].dragAll);

        $.each(nodeArr[0].dragAll, function(i, n){
            if(i=="localdata" || i=="columns" || i=="data"){
                nodeArr[0].dragAll[i] = JSON.parse(n);
            }
        });
    }
}

function openFormOptions(t){
    var plugObj = $(t).parent().siblings(".view").children("[dtype]"),
        plugObjId = plugObj.attr("id"),
        dtype = plugObj.attr("dtype"),
        $view = $(t).parent().siblings(".view"),
        $sideTree = $("#sideTree"),
        nodeArr = $sideTree.tree("getTree").getNodesByParam("dragId", plugObjId),
        optionsFormId = "optionsForm",
        plugArr;
    $.dopen({
        btn: ['保存', '取消'],
        type: 1,
        area: ['900px', '500px'],
        colseBtn: 1,
        fix: true,
        title: '属性配置',
        content: '<form id="'+ optionsFormId +'"></form>',
        btn1: function (index) {
            var plugOptions = $("#"+ optionsFormId).dform("getData");

            $.each(plugOptions, function(key, val){
                if(val == ""){
                    delete plugOptions[key]; //删除没有修改过的属性
                }else if(val == "1" || val == "0"){// true/false
                    plugOptions[key] = parseInt(val);
                }
            });
            //解析localdata数据并验证
            $.each(plugOptions, function(i, n){
                if(n!==undefined && i=="inputs"){
                    try {
                        plugOptions[i] = eval("(" + plugOptions[i] + ")");
                    }catch(e){
                        throw new Error("对象字符串异常");
                    }
                }
            });
            nodeArr[0] = plugOptions;

            var colClass = plugObj.find(".column").eq(0).attr("class");
            var colLen = plugObj.find(".column").length; //原列数/原控件数
            var rowNum = plugOptions.rownum; //设置后每行列数
            var totalNum = plugOptions.totalnum; //form总控件数
            var lastCol = plugObj.find(".column").last().empty();

            var num = Math.floor(12 / plugOptions.rownum); //栅格布局样式名col-sm-'num'
            var inputstr = "";
            if(totalNum!=colLen || colLen!=rowNum){
                plugObj.empty();
                for(var i=0; i<totalNum; i++){
                    inputstr += num+" ";
                    plugObj.append("<div class='col-sm-"+ num +" column layoutClass ui-sortable' dtype='column'><\/div>");
                }
                /*var item = {colspan:num, dtype:"column"};
                nodeArr[0].dragAttr.inputs.push(item);*/
            }
            $("#formRows .preview input").val(inputstr);

            if(nodeArr[0].plug){
                nodeArr[0].dragAttr.inputs = cloneObj(nodeArr[0].plug);
                delete nodeArr[0].plug;
            }
            if(nodeArr[0].dragAttr.inputs){
                $.each(nodeArr[0].dragAttr.inputs, function(key, val){
                    for(var i in this){
                        if(i != "plug"){
                            delete this[i];
                        }else{
                            var plugs = this[i][0];
                            console.log(plugs)
                            console.log(plugs.length)
                            for(var j in plugs){
                                //this[j] = plugs[j];
                                console.log(j+", "+plugs[j])
                            }
                        }
                    }
                });
            }

            $sideTree.tree("getTree").updateNode(nodeArr[0]);
            console.log('nodeArr[0]')
            console.log(nodeArr[0])

            initContainer();//初始化可移动的组件

            //delJson(conJson2,"isHover");
            //delJson(conJson2,"dragId");
            var nodes = $sideTree.tree("getTree").getNodes();
            console.log(nodes)
            //processForm(nodes)
            /*var formJson = process(nodes);
            var formJson2 = cloneObj(formJson);
            $("#downloadModal textarea:eq(0)").val(formJson2);*/

            layer.close(index);
        }, btn2: function(index){
            layer.close(index);
        }
    });
    var optionsFormObj = $("#"+ optionsFormId);
    optionsFormObj.dform({
        rownum: 2,
        labelwidth: "200px",
        inputs: [
            {
                title: "表单标题formtitle",
                name: "formtitle",
                type: "textBox"
            }, {
                title: "标签宽度labelwidth",
                name: "labelwidth",
                type: "textBox",
                defaultValue: "50px"
            }, {
                title: "form总控件数totalnum",
                name: "totalnum",
                type: "textBox",
                defaultValue: 3
            }, {
                title: "每行控件数目rownum",
                name: "rownum",
                type: "textBox",
                defaultValue: 3
            }, {
                title: "组件数组inputs",
                name: "inputs",
                type: "textBox",
                multiline: true,
                colspan: 2,
                defaultValue: JSON.stringify(plugArr)
            }
        ]
    });
    //重新打开属性窗口后，加载已设置过的表单组件的值
    if(nodeArr[0] && nodeArr[0].dragAttr){
        nodeArr[0].dragAttr.inputs = JSON.stringify(nodeArr[0].dragAttr.inputs);
        optionsFormObj.dform("load", nodeArr[0].dragAttr);
        nodeArr[0].dragAttr.inputs = JSON.parse(nodeArr[0].dragAttr.inputs);
    }
}

function cloneObj(obj) {
    var o,i,j;
    if(typeof(obj)!="object" || obj===null) return obj;
    if(obj instanceof(Array)) {
        o=[];
        i=0;j=obj.length;
        for(;i<j;i++) {
            if(typeof(obj[i])=="object" && obj[i]!=null) {
                o[i]=arguments.callee(obj[i]);
            } else {
                o[i]=obj[i];
            }
        }
    } else {
        o={};
        for(i in obj) {
            if(typeof(obj[i])=="object" && obj[i]!=null) {
                o[i]=arguments.callee(obj[i]);
            } else {
                o[i]=obj[i];
            }
        }
    }
    return o;
}