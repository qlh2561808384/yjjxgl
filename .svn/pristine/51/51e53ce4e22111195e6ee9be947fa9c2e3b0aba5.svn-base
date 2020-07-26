(function ($) {
    $.extend({
        globalValid: $.extend({}, {
            email: {
                reg: /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/,
                text: '邮箱格式错误'
            },
            idcard: {
                reg: /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/,
                text: '身份证格式错误'
            },
            phone: {
                reg: /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/,
                text: '手机号码格式错误'
            }
        }, $.globalValidConfig),
        textBoxDefault: $.extend({}, {
            width: '100%',//显示宽度
            height: 'auto',
            thousandsNum: false,//数字按“1,234,567”格式显示
            multiline: false,//是否是多行文本
            disabled: false,//是否禁用
            placeholder: '',//提示文字
            //validText: '',//
            required: false,//是否必填
            defaultValue: '',
            //boxFormater:function(value){},
            //validate: function (value) {//表单验证
            //    return {result:true,msg:''}
            //},
            validFail: function ($el) {//验证失败 事件回调
                return false;
            },
            validSuccess: function ($el) {//验证成功 事件回调
                return false;
            },
            onChange: function () {
            }
        }, $.textBoxConfig),
        dateBoxDefault: $.extend({}, {
            language: 'zh-CN',  //设置时间控件为中文
            removeIcon: true, //重置按钮，默认显示
            disabled: false, //是否禁用时间控件
            format: 'yyyy-mm-dd',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            minView: 2,
            todayHighlight: 1,
            startView: 4,
            forceParse: 0,
            showMeridian: 1,
            onChange: function (newValue, oldValue) {

            }
        }, $.dateBoxConfig),
        comboBoxDefault: $.extend({}, {
            valueField: 'id',//定义取值字段名
            textField: 'text',//定义显示文本 字段名
            localdata: [],//本地数据 用于加载本地数据
            url: '',//异步请求url
            selected: '',//初始化选中项 参数项为option index
            queryParam: {},//使用异步请求时需要的额外参数
            panelWidth: 'auto',//下拉面板宽度
            panelHeight: 'auto',//下拉面板高度
            multiple: false,//是否支持多选
            separator: ',',//如支持多选  数值和文本间的分隔符
            disabled: false,//是否禁用
            hasDownArrow: true,//是否显示下拉按钮
            value: '',
            onShowPanel: function () {//当显示下拉面板的时候触发。
                return false;
            },
            onHidePanel: function () {//当隐藏下拉面板的时候触发
                return false;
            },
            onChange: function (newValue, oldValue) {//当组合框的值发生改变时触发

            },
            onClick: function () {
                return false;
            }
        }, $.comboBoxConfig),
        comboTreeDefault: $.extend({}, {
            onlyLeaf: false,//是否只能选择叶子节点 true 时 单选和多选模式下 父节点无法被进行选中赋值
            checkType: 'nocheck', //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
            rootElement: false, //是否添加“全部”节点
            check: {},
            data: {
                simpleData: {
                    enable: true,//是否启用简单数据模式
                    idKey: "id", // id编号命名 默认
                    pIdKey: "pId", // 父id编号命名 默认
                    rootPId: "0" // 用于修正根节点父节点数据，即 pIdKey 指定的属性值
                },
                key: {
                    checked: "checked",//zTree 节点数据中保存 check 状态的属性名称。默认值："checked"请勿与 zTree 节点数据的其他参数冲突，例如：checkedOld
                    children: "children",//zTree 节点数据中保存子节点数据的属性名称。
                    name: "name",//zTree 节点数据保存节点名称的属性名称。
                    title: "",//zTree 节点数据保存节点提示信息的属性名称。[setting.view.showTitle = true 时生效]如果设置为 "" ，则自动与 setting.data.key.name 保持一致，避免用户反复设置
                    url: ""//zTree 节点数据保存节点链接的目标 URL 的属性名称。特殊用途：当后台数据只能生成 url 属性，又不想实现点击节点跳转的功能时，可以直接修改此属性为其他不存在的属性名称
                }
            },
            callback: {}
        }, $.comboTreeConfig),
        searchTreeDefault: $.extend({}, {
            openSearch: true,
            dblEnterSearch: false, //搜索框，查询结果若为一条，二次回车选中节点并确认
            modalWidth: 'auto',
            modalHeight: 'auto',
            modalTitle: '',
            onAckCallback: function () {
                return false
            },//点击模态框确认按钮后触发
            onChange: function (newValue, oldValue) {
                return false
            },
            onLoaded: function () {
                return false
            },//组件加载完毕回调函数
            callback: {},
            view: {}
        }, $.searchTreeConfig),
        treeDefault: $.extend({}, {
            async: {
                dataType: "text",
                autoParam: [],
                enable: false,
                otherParam: [],
                url: ''
            },
            url: '',
            fixedTreeHeight: $(window).height() - 40,//有搜索框时，固定树的高度
            rootElement: false, //是否添加根节点“全部”
            //onlyLeaf:false,//是否只能选择叶子节点 true 时 单选和多选模式下 父节点无法被进行选中赋值
            ISLEAF: false, //checkType为checkbox或radio时，非叶子节点是否有选择框，false为不可选
            checkType: 'radio', //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
            queryParam: {},//请求的额外参数
            check: {},
            openSearch: false,
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id", // id编号命名 默认
                    pIdKey: "pId", // 父id编号命名 默认
                    rootPId: "0" // 用于修正根节点父节点数据，即 pIdKey 指定的属性值
                },
                key: {
                    checked: "checked",//zTree 节点数据中保存 check 状态的属性名称。默认值："checked"请勿与 zTree 节点数据的其他参数冲突，例如：checkedOld
                    children: "children",//zTree 节点数据中保存子节点数据的属性名称。
                    name: "name",//zTree 节点数据保存节点名称的属性名称。
                    title: "",//zTree 节点数据保存节点提示信息的属性名称。[setting.view.showTitle = true 时生效]如果设置为 "" ，则自动与 setting.data.key.name 保持一致，避免用户反复设置
                    url: ""//zTree 节点数据保存节点链接的目标 URL 的属性名称。特殊用途：当后台数据只能生成 url 属性，又不想实现点击节点跳转的功能时，可以直接修改此属性为其他不存在的属性名称
                }
            },
            view: {
                //fontCss: getFontCss
            },
            callback: {
                clickNode: function (e, treeId, treeNode, clickFlag) {
                    return false
                }//由于点击事件被用于封装  外部使用clickNode 进行额外的点击事件
            },
            //加载完毕
            onLoaded: function (treeObj) {
            }
        }, $.treeConfig),
        decimalDefault: $.extend({}, {
            width: '100%',//显示宽度
            height: 'auto',
            placeholder: '',//提示文字
            required: false,//是否必填
            defaultValue: '',
            decimalPlaces: 2
        }, $.decimalConfig)
    });

    /**
     * 文本框 作为所有input组件的基类
     */
    var textBox = jClass({
        init: function (el, options) {
            this.$textBox = $(el);
            this.$valueBox;
            this.$outerBox;
            this.options = options;
            this.initBox();
        }
    });
    $.fn.textBox = function (options) {
        var $this = $(this), obj = null;
        if (typeof options == 'object') {
            obj = $this.data("textBox");
            if (obj != null) {
                obj.options = $.extend({}, obj.options, options);
                return;
            }
            options = $.extend({}, $.textBoxDefault, options);
            //如果为多行文本 初始化时对input元素  this对象进行替换操作
            if (options.multiline) {
                $this = $.replaceTag($(this), "TEXTAREA");
            } else {
                $this = $.replaceTag($(this), "INPUT")
            }
            $this.data("textBox", new textBox($this, options));
            $this.data("type", "textBox");
            return;
        }
        if (typeof options == 'string') {
            var value,
                param = Array.prototype.slice.call(arguments, 1),
                obj = $this.data("textBox");
            value = obj[options].apply(obj, param);
            return typeof value === 'undefined' ? this : value;
        }
    };
    /**
     * 初始化textBox（作为内部调用）
     * @private
     */
    textBox.prototype.initBox = function () {
        var $textBox = this.$textBox, options = this.options;
        //多行文本  进行属性拷贝 删除原input
        if (options.multiline) {
            //$textBox = this.$textBox.prev("input");
            //this.$textBox.attr("name",$textBox.attr("name")).attr("id",$textBox.attr("id")).attr("class",$textBox.attr("class")).attr("style",$textBox.attr("style"));
            //$textBox.remove();
            //$textBox=$textarea;
            if (options.height == 'auto') {
                $textBox.css({"height": "80px", "resize": "none"});
            }
        }
        this.$textBox.wrap('<span class="ztreeBox_inp ztreeBox_down "></span>').after("<input type='hidden' class='valueBox'/>").addClass("form-control input-sm").attr({'placeholder': this.options.placeholder});
        this.$valueBox = $textBox.next(".valueBox");//设置验证值的input
        this.$outerBox = $textBox.parent().css({"width": options.width, height: options.height});
        this.$valueBox = this.$outerBox.find(".valueBox").attr("boxType", "textBox");
        if (options.id) {
            this.$textBox.attr("id", options.id);
        }
        if (options.hasOwnProperty("dname")) {
            this.$valueBox.attr("dname", options.dname);
        }
        if (this.$textBox.attr("dname") != undefined) {
            this.$valueBox.attr("dname", this.$textBox.attr("dname"));
            this.$textBox.removeAttr("dname");
        }
        if (options.hasOwnProperty("name")) {
            this.$valueBox.attr("name", options.name);
        }
        if (this.$textBox.attr("name") != undefined) {
            this.$valueBox.attr("name", this.$textBox.attr("name"));
            this.$textBox.removeAttr("name");
        }
        //扩展类  在调用init时 可能会使用到扩展类 中的覆盖方法 引起错误
        try {
            if (options.defaultValue != '') {
                this.setValue(options.defaultValue)
            }
        } catch (e) {
        }

        this.$textBox.addClass("bgwhite");
        this.onTrigger();
    };
    /**
     * 表单失焦触发验证  验证失败回调验证失败函数
     * 2017.06.02 补完textbox onchange 事件处理
     */
    textBox.prototype.onTrigger = function () {
        var that = this, $textBox = this.$textBox, $valueBox = this.$valueBox;//options = this.options,该变量申明会导致 无法动态修改配置参数
        $textBox.off("blur").on("blur", function (e) {
            that.$outerBox.removeClass("has-error")
            /*.removeClass("has-success");*/
            $valueBox.val($textBox.val());
            if (that.options.required || that.options.validate) {
                var flag = that.validate();
                if (!flag) {
                    that.options['validFail'].apply(this, [that.$textBox])
                } else {
                    that.options['validSuccess'].apply(this, [that.$textBox])
                }
            }
        });
        $textBox.off("keydown").on("keydown", function (e) {
            $valueBox.val($textBox.val());
        });
        $textBox.off("change").on("change", function (e) {
            that.changeValue(that.$valueBox.val(), that.$textBox.val(), that.options)
        });
        //数字按“1,234,567”格式显示
        if (this.options.thousandsNum) {
            //当按下按键时触发keyup事件
            $textBox.on('keyup', function (event) {
                //响应鼠标事件，允许左右方向键移动
                event = window.event || event;
                if (event.keyCode == 37 | event.keyCode == 39) {
                    return;
                }
                //非数字的都替换掉
                var temp = $textBox.val().replace(/[^\d.]/g, "");
                //添加分隔符
                $textBox.val(toThousands(temp)).on("blur", function () {
                    $valueBox.val(temp);
                });
            });

            //千分位的分隔符
            function toThousands(num) {//此时传入的是字符串,若传入double类型则会indexOf报错
                //若没有任何数据则直接返回
                if (num == "" || num == null) {
                    return num;
                } else {
                    //判断是否有小数点
                    var s = num.indexOf(".");
                    if (s == -1) {//是整数
                        return (num || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
                    } else {
                        var arr = num.split(".");
                        return (arr[0] || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,') + "." + arr[1];
                    }
                }
            }
        }
    };
    /**
     * 销毁方法
     */
    textBox.prototype.destroy = function () {
        this.$textBox.removeData("textBox");
        this.$outerBox.remove();
    };
    /**
     * 表单验证 弹出tips
     * @param text
     * @param el
     */
    textBox.prototype.tips = function (text, el) {
        layer.tips(text, el, {
            tipsMore: true,
            time: 2000
        });
    };
    /**
     * 改变组件value 触发onchange 事件(内部调用方法 原则上不作为api使用)
     * 保存oldvalue 采用  jquery data() 方法  保存数据到$对象内
     * @param newValue
     * @param newText
     */
    textBox.prototype.changeValue = function (newValue, newText, optionObj) {
        var $textBox = this.$textBox,
            $valueBox = this.$valueBox,
            oldValue = this.$textBox.data("oldValue") == undefined ? "" : this.$textBox.data("oldValue");
        //验证 新旧数值
        if (newValue == oldValue) {
            return;
        }
        //对元素赋值
        $textBox.val(newText);
        $valueBox.val(newValue);
        //刷新oldValue
        $textBox.data("oldValue", newValue);
        //执行onChange 回调函数
        this.options["onChange"].apply(this, [newValue, oldValue, optionObj])
    };
    /**-
     * 表单验证方法 根据required 以及 validate 预设规则 或自定义验证方法 进行表单验证 返回Boolean
     * options.validate 为字符串时 根据预设正则 进行验证 支持多种预设 以‘|’进行分割
     *                  为方法时   根据自定义方法进行验证 （自定义验证方法必须返回boolean）
     * @returns {*}
     */
    textBox.prototype.validate = function () {//表单验证方法
        var flag = true,
            value = $.trim(this.$valueBox.val()),
            that = this,
            options = this.options,
            $textBox = this.$textBox;
        if (that.$textBox.is(":disabled")) {
            return flag;
        }
        if (options.required && value == '') {
            this.tips("该选项为必填", this.$outerBox);
            //this.$outerBox.removeClass("has-success");
            this.$outerBox.addClass("has-error");
            return false;
        }
        if (typeof options['validate'] == 'function') {
            //自定义表单验证方法 提供表单value参数 必须返回boolean类型 以及 validText 验证提示内容
            var obj = options['validate'].apply(options, [$textBox.val(), value]);//andrew 20180122 解决combotree 验证无法获取value 的问题
            // flag=obj.result;
            if (obj && obj.result == false) {
                this.tips(obj.msg, that.$outerBox);
                this.$outerBox.addClass("has-error");
                return obj.result;
            }
        } else if (typeof options['validate'] == 'string') {
            var validType = options['validate'].split("|"), result;
            $.each(validType, function (i, p) {
                flag = $.globalValid[p].reg.test(value);
                result = $.globalValid[p].text;
                if (!flag) {
                    return false;
                }
            });
            if (!flag) {
                that.tips(result, that.$outerBox);
            }
        }
        if (flag) {
            this.$outerBox.removeClass("has-error");
        } else {
            this.$outerBox.addClass("has-error");
        }
        return flag;
    };
    /*
     * 清除组件内容
     */
    textBox.prototype.clear = function () {
        this.$valueBox.val("");
        this.$textBox.val("");
    };
    textBox.prototype.getValue = function () {
        return this.$valueBox.val();
    };
    textBox.prototype.setValue = function (value) {
        this.$valueBox.val(value);
        this.$textBox.val(value);
    };
    textBox.prototype.disable = function () {
        this.$textBox.attr("disabled", "disabled").removeClass("bgwhite");
    };
    textBox.prototype.enable = function () {
        this.$textBox.removeAttr("disabled").addClass("bgwhite");
    };
    /**
     * 获取显示文本
     * @returns {*}
     */
    textBox.prototype.getText = function () {
        return this.$textBox.val();
    };
    /**
     * 设置显示文本
     * @param text
     */
    textBox.prototype.setText = function (text) {
        this.$textBox.val(text);
    };
    /*****************textbox结束*********************/


    /*****************日期框组件开始***************************/
    var dateBox = jClass(textBox, {
        init: function (el, options) {
            this.base(el, options);
            this.initDateBox();
        }
    });

    $.fn.dateBox = function (options) {
        var $this = $.replaceTag($(this), "INPUT"), obj = null;
        if (typeof options == 'object') {
            options = $.extend({}, $.extend({}, $.textBoxDefault, $.dateBoxDefault), options);
            $.include(['bootstrap/css/bootstrap.min.css',
                'bootstrap/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css',
                'bootstrap/plugins/datetimepicker/js/bootstrap-datetimepicker.min.js',
                'bootstrap/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.' + options.language + '.js',
                'bootstrap/plugins/datetimepicker/css/bootstrap-datetimepicker-custom.css']);
            obj = $this.data("dateBox");
            if (obj != null) {
                obj.options = $.extend({}, obj.options, options);
                return;
            }
            $this.data("dateBox", new dateBox($this, options));
            return;
        }
        if (typeof options == 'string') {
            var value,
                param = Array.prototype.slice.call(arguments, 1),
                obj = $this.data("dateBox");
            value = obj[options].apply(obj, param);
            return typeof value === 'undefined' ? this : value;
        }
    };
    /**
     * 初始化  日期框对象
     * @returns {*}
     */
    dateBox.prototype.initDateBox = function () {
        var $textBox = this.$textBox, options = this.options, that = this;
        $textBox.css("border-right", "none").attr("readonly", "readonly");
        this.$outerBox.addClass("input-group date").css({
            "display": "table",
            width: options.width == '100%' ? '' : options.width
        });
        var html = "";
        if (options.removeIcon) {
            html += '<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>'
        }
        html += '<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>';
        this.$valueBox.after(html);
        var returnobj = this.$outerBox.datetimepicker(options);
        $textBox.off("blur");
        returnobj.on("hide", function () {
            that.validate()
        });
        if (options.disabled) {
            $textBox.attr("disabled", "disabled");
            this.$outerBox.find(".input-group-addon").off("click");
        }
        this.$valueBox.attr("boxType", "dateBox");
        return returnobj;
    };
    dateBox.prototype.setValue = function (value) {
        this.$textBox.val(value);
        this.$valueBox.val(value);
        this.$valueBox.datetimepicker('update');
    };
    dateBox.prototype.disable = function () {
        var $removeBtn = this.$outerBox.find(".glyphicon-remove").parent();
        var $togleBtn = this.$outerBox.parent().find(".glyphicon-th").parent();
        $removeBtn.off("click");
        $togleBtn.off("click");
        this.$textBox.attr("disabled", "disabled").removeClass("bgwhite");
    };
    dateBox.prototype.enable = function () {
        var $textBox = this.$textBox,
            $removeBtn = this.$outerBox.find(".glyphicon-remove").parent(),
            $togleBtn = this.$outerBox.find(".glyphicon-th").parent();
        $removeBtn.on("click", function () {
            $textBox.val("");
        });
        $togleBtn.on("click", function () {
            $textBox.trigger("focus")
        });
        this.$textBox.removeAttr("disabled").addClass("bgwhite");
    };
    /*****************日期框组件结束***************************/


    /*******************组合框*******************/
        //继承自 textBox
    var comboBox = jClass(textBox, {
            init: function (el, options) {
                this.base(el, options);
                this.initPanel();
                this.initPanelContent();
                this.datas;
                this.$panel;
                this.$arrows;
            }
        });

    /**
     * comboBox 组件调用入口
     *  当参数为 对象时 创建组件对象并将该对象保存至fn对象中
     *  当参数为 字符串 获取fn中的对象 并调用同名方法
     *  初始化参数继承 textBox.default —— comboBox.default ——options
     * @param options
     * @returns {$.fn}
     */
    $.fn.comboBox = function (options) {
        var $this = $.replaceTag($(this), "INPUT"), obj = null;
        if (typeof options == 'object') {
            $.include(['bootstrap/plugins/tree/css/zTreeStyle/zTreeStyle.css',
                'bootstrap/plugins/tree/css/zTreeStyle/bootstrap-zTree-custom.css',
                'bootstrap/plugins/tree/js/jquery.ztree.all.min.js']);
            $.include(['bootstrap/css/font-awesome.min.css']);
            obj = $this.data("comboBox");
            if (obj != null) {
                obj.options = $.extend({}, obj.options, options);
                return;
            }
            options = $.extend({}, $.extend({}, $.textBoxDefault, $.comboBoxDefault), options);
            $this.data("comboBox", new comboBox($this, options));
            $this.data("type", "comboBox");
            return;
        }
        if (typeof options == 'string') {
            var value,
                param = Array.prototype.slice.call(arguments, 1),
                obj = $(this).data("comboBox");
            value = obj[options].apply(obj, param);
            return typeof value === 'undefined' ? this : value;
        }
    };
    /**
     * 初始化下拉面板
     */
    comboBox.prototype.initPanel = function () {
        var $textBox = this.$textBox,
            that = this,
            options = this.options;

        $textBox.after('<i class="fa fa-angle-down combo-box-arrows"></i>').after('<div  class="combo-panel"></div>');
        this.$valueBox.attr("boxType", 'comboBox');
        this.$arrows = this.$outerBox.find(".fa.fa-angle-down");
        if (!options.hasDownArrow) {
            this.$arrows.hide();
        }
        this.$panel = this.$outerBox.find(".combo-panel");
        var panelWidth = this.options.panelWidth;
        var panelHeight = this.options.panelHeight;
        this.$panel.css({
            width: panelWidth == 'auto' ? '100%' : panelWidth,
            height: panelHeight
        });

        $textBox.removeAttr("style").css({"width": '100%'}).attr({readonly: "true"}).data("oldValue", '');

        this.$valueBox.on("focus", function () {
            that.clickToggle();
        });
        this.$arrows.off("click").on('click', function () {
            that.clickToggle();
        });
        this.$textBox.off("click").on('click', function () {
            that.clickToggle();
        });
        this.$valueBox.off("focus").on("focus", function () {
            that.$textBox.trigger("click");
        });
    };


    comboBox.prototype.clickToggle = function () {
        this.options["onClick"].apply(this, []);// 触发onClick参数事件 根据需求添加参数
        if (this.$panel.is(":hidden")) {
            this.showPanel();
        } else {
            this.hidePanel();
        }
    };

    comboBox.prototype.onTrigger = function () {
    };

    /**
     * 重新加载数据
     * @param data  请求对象{url:'',queryParam:{}}  或 数据对象 []
     */
    comboBox.prototype.reload = function (data) {
        //清空现在赋值数据
        this.clear();
        //清除下拉面板内的选项节点
        this.$panel.children().remove();
        //修改option url 或 localdata
        if (data.hasOwnProperty('url')) {
            this.options.url = data.url;
        } else {
            this.options.localdata = data;
        }
        if (data.hasOwnProperty('queryParam')) {
            this.options.queryParam = data.queryParam;
        }
        this.initPanelContent();
    };

    //显示下拉面板
    //触发onShowPanel事件
    comboBox.prototype.showPanel = function () {
        var that = this;
        this.$panel.show();
        this.options['onShowPanel'].apply(this, [this.$textBox]);
        $(document).on("mousedown", function (e) {
            that.onBodyDown(e);
        });
    };

    comboBox.prototype.onBodyDown = function (event) {
        var $target = $(event.target), classname = "";
        if ($target.parent()) {
            classname = $target.parent().attr('class') + "";
        }
        if (!($target.parent().hasClass("combo-panel") ||
                $target.hasClass("comboBox_option") ||
                $target.hasClass("node_name") ||
                classname.indexOf('level') != -1 ||
                classname.indexOf('ztreeBox_down') != -1) ||
            ($target.is(":input") && $target.attr("id") != this.$textBox.attr("id"))) {
            this.hidePanel();
        }
    };

    //隐藏下拉面板
    comboBox.prototype.hidePanel = function () {
        var options = this.options;
        if (this.$panel.is(':hidden')) {
            return;
        }
        this.$panel.hide();
        if (options.required == true || options.validate != null) {
            this.validate();//进行表单验证
        }
        this.options['onHidePanel'].apply(this);//调用 onHidePanel方法
    };


    /**
     * 初始化下拉面板内容  扩展下拉树 可继承comboBox 该需重写该方法
     */
    comboBox.prototype.initPanelContent = function () {
        var that = this,
            options = this.options,
            text = options.textField,
            value = options.valueField;

        if (options.localdata.length) {
            this.datas = options.localdata;
            appendOption()
        } else {
            $.ajax({
                type: "POST",
                url: options.url,
                dataType: 'json',
                data: options.queryParam,
                success: function (data) {
                    that.datas = data;
                    appendOption()
                },
                error: function () {

                }
            })
        }

        /**
         * 组合框  添加 选项元素
         * searchOption true 添加首选项‘全部’  false 添加首选项 ‘空’  无searchOption参数  则不做添加
         * 添加完毕 后 根据 selected参数 默认选中对应index 选项
         */
        function appendOption() {
            var optionObj = new Object();
            if (options.searchOption == true) {
                optionObj[text] = '全部';
                optionObj[value] = 'ALL';
                that.datas.unshift(optionObj);
            } else if (options.searchOption == false) {
                optionObj[text] = '';
                optionObj[value] = '';
                that.datas.unshift(optionObj);
            }
            $.each(that.datas, function (i, data) {
                var op = $('<div/>').addClass("comboBox_option").text(this[text]).attr("value", this[value]);
                that.$panel.append(op);
                $(op).data("optionObj", data);
                op.off("click").on("click", function () {
                    var $this = $(this), textArr = '', valueArr = '', oldValue = that.$textBox.data("oldValue"),
                        optionObj = [];
                    if (!options.multiple) {
                        that.$panel.find(".comboBox_option").removeClass("comboBox_option_check");
                        $this.addClass("comboBox_option_check");
                        that.$textBox.val($(this).text());
                    } else {
                        $this.toggleClass("comboBox_option_check");
                    }
                    $.each(that.$panel.find(".comboBox_option_check"), function (i, op) {
                        optionObj.push($(op).data("optionObj"));
                        textArr += $(op).text() + options.separator;
                        valueArr += $(op).attr("value") + options.separator;
                    });
                    if (textArr.length > 0) {
                        textArr = textArr.substring(0, textArr.length - 1);
                        valueArr = valueArr.substring(0, valueArr.length - 1);
                    }
                    that.changeValue(valueArr, textArr, optionObj.length == 1 ? optionObj[0] : optionObj);
                    if (!options.multiple) {
                        that.hidePanel();
                    }
                })
            });
            //that.$panel.find(".comboBox_option").off("click").on("click", function () {
            //    var $this = $(this), textArr = '', valueArr = '', oldValue = that.$textBox.data("oldValue");
            //    if (!options.multiple) {
            //        that.$panel.find(".comboBox_option").removeClass("comboBox_option_check");
            //        $this.addClass("comboBox_option_check");
            //        that.$textBox.val($(this).text());
            //    } else {
            //        $this.toggleClass("comboBox_option_check");
            //    }
            //    $.each(that.$panel.find(".comboBox_option_check"), function (i, op) {
            //        textArr += $(op).text() + options.separator;
            //        valueArr += $(op).attr("value") + options.separator;
            //    });
            //    if (textArr.length > 0) {
            //        textArr = textArr.substring(0, textArr.length - 1);
            //        valueArr = valueArr.substring(0, valueArr.length - 1);
            //    }
            //    that.changeValue(valueArr, textArr,);
            //    if (!options.multiple) {
            //        that.hidePanel();
            //    }
            //});
            //根据selected参数  初始化 默认选中对应index的 选项

            if (options.defaultValue != '') {
                that.setValue(options.defaultValue);
            }

            if (options.selected) {
                var $options = that.$panel.find(".comboBox_option");
                that.setValue($options.eq((options.selected) - 1).attr("value"))
            }
        }
    };

    //清除组件内容
    comboBox.prototype.clear = function () {
        this.$panel.find(".comboBox_option_check").removeClass("comboBox_option_check");
        this.$valueBox.val("");
        this.$textBox.val("");
    };
    comboBox.prototype.disable = function () {
        this.$textBox.attr("disabled", "disabled").removeClass("bgwhite");
        this.$arrows.off("click");
    };
    comboBox.prototype.enable = function () {
        var that = this;
        this.$textBox.removeAttr("disabled").addClass("bgwhite");
        this.$arrows.off("click").on('click', function () {
            that.clickToggle();
        });
    };


    /**
     * 单选模式下 设置 组件value 用于数据加载回写
     * @param value
     */
    comboBox.prototype.setValue = function (values) {
        var that = this, selector = '', values = values + '', valArr = [], options = this.options;
        if (options.multiple) {
            if (values.indexOf(",") == -1) {
                valArr[0] = values;
            } else {
                valArr = values.split(",");
            }
            for (k in valArr) {
                selector += "[value='" + valArr[k] + "']" + options.separator
            }
            selector = selector.substring(0, selector.length - 1);
            var ops = this.$panel.find(selector);
            var text = '';
            $.each(ops, function (i, op) {
                text += $(op).text() + options.separator;
                $(op).addClass("comboBox_option_check");
            });
            text = text.substring(0, text.length - 1);
            this.$textBox.val(text);
            this.$valueBox.val(values);
            this.$valueBox.val(values);
        } else {
            this.$panel.find("[value='" + values + "']").each(function () {
                $(this).addClass("comboBox_option_check").siblings().removeClass("comboBox_option_check");
                that.$valueBox.val(values);
                that.$textBox.val($(this).text());
            })
        }
    };

    /**
     * 组合树 继承comboBox
     * 初始化参数继承 comboBox.defluat
     *
     */
    var comboTree = jClass(comboBox, {
        init: function (el, options) {
            this.base(el, options);
            this.$tree;
            this.ztree;
        }
    });

    /**
     * comboTree 组件调用入口
     *  当参数为 对象时 创建组件对象并将该对象保存至fn对象中
     *  当参数为 字符串 获取fn中的对象 并调用同名方法
     *  初始化参数继承 textBox.default —— comboBox.default —— comboTree.default —— options   内部树 初始化参数  tree.default —— options
     * @param options
     * @returns {$.fn}
     */
    $.fn.comboTree = function (options) {
        var $this = $.replaceTag($(this), "INPUT"), obj = null;
        if (typeof options == 'object') {
            $.include(['bootstrap/plugins/tree/css/zTreeStyle/zTreeStyle.css',
                'bootstrap/plugins/tree/css/zTreeStyle/bootstrap-zTree-custom.css',
                'bootstrap/plugins/tree/js/jquery.ztree.all.min.js']);
            $.include(['bootstrap/css/font-awesome.min.css']);
            obj = $this.data("comboTree");
            if (obj != null) {
                obj.options = $.extend({}, obj.options, options);
                return;
            }
            options = $.extend(true, {}, $.extend(true, {}, $.extend({}, $.textBoxDefault, $.comboBoxDefault), $.treeDefault), options);
            $this.data("comboTree", new comboTree($this, options));
            $this.data("type", "comboTree");
            return;
        }
        if (typeof options == 'string') {
            var value,
                param = Array.prototype.slice.call(arguments, 1),
                obj = $this.data("comboTree");
            value = obj[options].apply(obj, param);
            return typeof value === 'undefined' ? this : value;
        }
    };

    /**
     * 初始化下拉面板内容
     */
    comboTree.prototype.initPanelContent = function () {
        var that = this,
            options = this.options;
        this.$valueBox.attr("boxType", "comboTree")
        that.$tree = $("<ul />").addClass("ztree");
        that.$panel.append(that.$tree);
        this.$valueBox.attr("boxType", 'comboTree');
        this.$textBox.data("oldValue", "");//初始化
        /*if (options.checkType == "radio") {
            options.check.enable = "true";
            options.check.chkStyle = "radio";
            options.check.radioType = "all";
            //多选模式
        } else if (options.checkType == "checkbox") {
            options.check.enable = "true";
        }*/
        //配置为 单选模式时 点击节点 进行 text value 赋值
        if (!options.multiple) {
            //options.checkType = "radio";
            options.callback["onClick"] = function (event, treeId, treeNode) {
                if (treeNode.ISLEAF == 0 && options.onlyLeaf) {
                    return;
                }
                value = treeNode[options.data.simpleData.idKey];
                text = treeNode[options.data.key.name];
                that.changeValue(value, text, treeNode);
                that.hidePanel();
            };
        } else {
            //多选模式  初始化参数 添加 多选框选项
            options.checkType = "checkbox";
            options.check.enable = "true";
            //节点选中事件 ：获取选中节点 进行字符串拼接  赋值
            options.callback["onCheck"] = function (e, treeId, treeNode) {
                nodes = that.ztree.getCheckedNodes(true),
                    v = "", dv = "";
                $.each(nodes, function (i, node) {
                    if (node.ISLEAF == 0 && options.onlyLeaf) {
                        node.nocheck = true;
                        return true;
                    }
                    v += node[options.data.key.name] + options.separator;
                    dv += node[options.data.simpleData.idKey] + options.separator;
                });
                if (v.length > 0) {
                    v = v.substring(0, v.length - 1);
                    dv = dv.substring(0, dv.length - 1);
                }
                that.changeValue(dv, v, nodes);
            };
            //节点点击事件  如开启只选叶子节点 当选择非叶子节点时 直接返回
            options.callback["onClick"] = function (event, treeId, treeNode) {
                if (treeNode.ISLEAF == 0 && options.onlyLeaf) {
                    return;
                }
                //对该节点进行选中操作
                that.ztree.checkNode(treeNode, !treeNode.checked, null, true);
            };
        }
        // that.$tree.tree(options)

        // 远程加载数据 根据控制参数 对数据进行预处理 然后初始化
        if (options.url != '') {
            $.ajax({
                type: "POST",
                dataType: 'json',
                url: options.url,
                data: options.queryParam,
                success: function (data) {
                    that.datas = data;
                    that.processDate();
                    that.ztree = $.fn.zTree.init(that.$tree, options, that.datas);
                    if (options.defaultValue != '') {
                        that.setValue(options.defaultValue)
                    }
                }
            });
            //使用原生异步加载初始化树
        } else if (options.async != null && options.async.enable) {
            that.ztree = $.fn.zTree.init(that.$tree, options);
            if (options.defaultValue != '') {
                that.setValue(options.defaultValue)
            }
        } else {
            //使用本地数据 根据控制参数 对数据节点进行预处理 然后初始化
            that.datas = options.localdata;
            that.processDate();
            that.ztree = $.fn.zTree.init(that.$tree, options, that.datas);
            if (options.defaultValue != '') {
                that.setValue(options.defaultValue)
            }
        }

    };

    /**
     * 重写 单选回写组件value
     * @param value
     */
    comboTree.prototype.setValue = function (values) {
        var that = this, text = '', valueArr = [], value = '', options = this.options, values = values + '',
            nodes = this.ztree.getNodesByParam(options.data.simpleData.idKey, values);
        if (options.multiple) {
            if (values.indexOf(",") == -1) {
                valueArr[0] = values;
            } else {
                valueArr = values.split(",")
            }
            if (valueArr.length == 0) {
                return;
            }
            nodes = this.ztree.getNodesByFilter(function (node) {
                return $.inArray(node[options.data.simpleData.idKey] + '', valueArr) == -1 ? false : true;
            });
            $.each(nodes, function (i, node) {
                that.ztree.checkNode(node);
                text += node[options.data.key.name] + options.separator;
                value += node[options.data.simpleData.idKey] + options.separator;
            });
            if (text.length > 0) {
                text = text.substring(0, text.length - 1);
                value = value.substring(0, value.length - 1);
            }
            this.$textBox.val(text);
            this.$valueBox.val(value);
        } else {
            if (nodes.length > 0) {
                this.ztree.selectNode(nodes[0]);
                this.$valueBox.val(nodes[0][options.data.simpleData.idKey]);
                this.$textBox.val(nodes[0][options.data.key.name]);
            }
        }
    };

    /**
     *  远程加载的数据预处理(该方法 内部调用 )
     * @param data
     */
    comboTree.prototype.processDate = function () {
        var that = this, options = this.options;
        if (options.rootElement) {
            var obj = {};
            obj[options.data.simpleData.idKey] = options.data.simpleData.rootPId;
            obj[options.data.simpleData.pId] = '';
            obj['open'] = true;
            if (options.data.key.name) {
                obj[options.data.key.name] = '全部';
                obj['ISLEAF'] = 0
            }
            this.datas.push(obj);
        }
        $.each(this.datas, function (i, node) {
            if (node.ISLEAF == 0 && options.onlyLeaf) {
                node.nocheck = true;
                return true;
            }
        });
    };
    /**
     * 组合树 清除内容
     */
    comboTree.prototype.clear = function () {
        var ztree = this.ztree;
        //清除勾选
        $.each(ztree.getCheckedNodes(true), function (i, node) {
            ztree.checkNode(node, !node.checked, null, true);
        });
        //清除选择
        $.each(ztree.getSelectedNodes(), function (i, node) {
            ztree.cancelSelectedNode(node, true);
        });
        //清除文本 值 表单数据
        this.$valueBox.val("");
        this.$textBox.val("");
    };

    /**
     * 返回树对象  获得对象后可在前端 直接使用ztree api
     * api  http://www.treejs.cn/v3/api.php
     * @returns {*}
     */
    comboTree.prototype.getTree = function () {
        return this.ztree;
    };
    /***********************组合树结束**********************************/

    /**
     * 模态查询树
     */
    var searchTree = jClass(textBox, {
        init: function (el, options) {
            this.base(el, options);
            this.that_id = $(el).attr("id") != null ? $(el).attr("id") : $(el).attr("id", "id" + (new Date().getTime())).attr("id");
            this.initModal();
            this.initModalContent();
            this.datas;
            this.ztree;
            this.$tree;
            this.$modal;
            this.lastNode;
        }
    });


    /**
     * 初始化 模态框 包括
     * 添加模态框 添加隐藏表单等外部
     */
    searchTree.prototype.initModal = function () {
        var that = this,
            options = this.options,
            $textBox = this.$textBox,
            that_id = this.that_id,
            html = '<div class="modal fade" id="' + that_id + "_modal" + '" tabindex="-1" role="dialog" aria-labelledby="' + that_id + "_label" + '" aria-hidden="true">'
                + '<div class="modal-dialog">'
                + '<div class="modal-content">'
                + '<div class="modal-header">'
                + '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
                + '<h4 class="modal-title" id="' + that_id + "_label" + '">' + options.modalTitle + '</h4>'
                + '</div>'
                + '<div class="modal-body">'
                + '</div>'
                + '<div class="modal-footer">'
                + '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>'
                + '<button type="button" class="btn btn-primary" id="' + that_id + "_modalok" + '">确定</button>'
                + '</div></div></div></div>';

        //  判断模态框是否存在  如存在在当前页面  进行remove
        if ($("#" + that_id + "_modal")) {
            $("#" + that_id + "_modal").remove();
        }
        $("body").append(html);
        this.$valueBox.attr("boxType", 'searchTree');
        $textBox.removeAttr('name').removeAttr("dname").after('<i class="fa fa-angle-down combo-box-arrows"></i>');

        this.$modal = $("#" + that_id + "_modal").css("z-index", 999999999);
        this.$modal.on("hidden.bs.modal", function () {
            if (options.required || options.validate) {
                that.validate();
            }
            that.$tree.data("tree").searchNodes('');
            that.$modal.find(":text").val("");
        });
        $textBox.parent().attr({"data-target": "#" + that_id + "_modal", "data-toggle": "modal"});
        $textBox.attr({readonly: "true"}).removeAttr('name');
        $textBox.parent().attr("style", this.$textBox.attr("style"));
        $textBox.removeAttr("style").css({"width": '100%'}).addClass("empty form-control").data("oldValue", '');
        $("#" + that_id + "_modalok").on("click", function () {
            that.doclick()
        })
    };
    /**
     * 查询树 模态框点击确认后所执行方法
     * 进行表单赋值，触发change事件  触发onackcallback事件，进行表单验证操作
     *
     */
    searchTree.prototype.doclick = function () {
        var that = this,
            options = this.options,
            newValue = that.$tree.tree("getValue"),
            oldValue = that.$textBox.data("oldValue"),
            $textBox = this.$textBox;

        if (oldValue != newValue) {
            that.$valueBox.val(newValue);
            $textBox.data("oldValue", newValue).val(that.$tree.tree("getText"));
            that.options.onChange.call(that, newValue, oldValue);
            if (options.required || options.validate) {
                that.validate();
            }
        }
        that.options.onAckCallback.call(that, that.$tree.tree('getTree').getCheckedNodes());
        that.$modal.modal('hide');
    };

    /**
     * 初始化模态框内容
     * 包括 添加searchInput 以及相关事件
     * 额外添加ztree 初始化参数 ：关闭双击展开  修改为单击展开  双击 触发确认方法
     */
    searchTree.prototype.initModalContent = function () {
        var that = this,
            that_id = this.that_id,
            content = this.$modal.find(".modal-body"),
            options = this.options;
        content.append('<ul id="' + that_id + "_mdTree" + '" class="ztree searchTree"></ul>');
        this.$tree = $("#" + that_id + "_mdTree");
        options.view['dblClickExpand'] = false;
        options["callback"]['beforeClick'] = function (treeId, treeNode) {
            that.lastNode = treeNode;
            if (treeNode.ISLEAF == 0) {
                that.ztree.expandNode(treeNode);
            }
            return true
        };
        options["callback"]['beforeDblClick'] = function (treeId, treeNode) {
            if (!treeNode) {
                return false
            }
            that.ztree.checkNode(treeNode, true, true, true);
            that.doclick();
            return false
        };
        this.$tree.tree(options);
        this.ztree = this.$tree.tree("getTree");
        var treewrap = this.$modal.find(".treeWrap");
        treewrap.css({maxHeight: $(window).height() / 2, overflowY: "auto"});
        //打开模态框，input获得焦点
        this.$modal.on("shown.bs.modal", function () {
            treewrap.prev().focus();
        })
    };
    /**
     * 查询树禁用方法
     * 移除外层容器  data-target 属性
     * */
    searchTree.prototype.disable = function () {
        this.$textBox.attr('disabled', 'disabled').removeClass("bgwhite");
        this.$textBox.parent().removeAttr("data-target")
    };

    searchTree.prototype.destroy = function () {
        this.$modal.remove();
        this.$outerBox.remove();
    };

    /**
     * 查询树 启用方法
     * 外层容器添加data-target 并指定 模态框id
     */
    searchTree.prototype.enable = function () {
        this.$textBox.removeAttr("disabled").addClass("bgwhite");
        this.$textBox.parent().attr("data-target", "#" + this.that_id + "_modal")
    };

    searchTree.prototype.getText = function () {
        return this.$textBox.val();
    };

    searchTree.prototype.setValue = function (value) {
        this.$tree.tree("setValue", value);
        this.$textBox.val(this.$tree.tree("getText"));
        this.$valueBox.val(this.$tree.tree("getValue"))
    };

    searchTree.prototype.clear = function () {
        this.$tree.tree('clearTree');
        this.$textBox.data("oldValue", "") //修复 清除value 未清oldvalue 导致 clear操作后 选择相同节点无法被选中的问题
        this.$textBox.val('');
        this.$valueBox.val('');
    };

    searchTree.prototype.reload = function (data) {
        this.$tree.tree("reload", data);
    };
    searchTree.prototype.getTree = function () {
        return this.ztree;
    };
    //父类 textbox 使用该方法进行失焦触发表单验证  暂时解决方案  后续进行完善
    searchTree.prototype.onTrigger = function () {

    };

    /**
     * 查询树调用入口
     * @param options
     * @returns {$.fn}
     */
    $.fn.searchTree = function (options) {
        var $this = $.replaceTag($(this), "INPUT"), obj = null;
        if (typeof options == 'object') {
            $.include(['bootstrap/plugins/tree/css/zTreeStyle/zTreeStyle.css',
                'bootstrap/plugins/tree/css/zTreeStyle/bootstrap-zTree-custom.css',
                'bootstrap/plugins/tree/js/jquery.ztree.all.min.js'])
            $.include(['bootstrap/css/font-awesome.min.css'])
            obj = $this.data("searchTree")
            if (obj != null) {
                obj.options = $.extend({}, obj.options, options);
                return;
            }
            options = $.extend({}, $.extend({}, $.textBoxDefault, $.searchTreeDefault), options);
            $this.data("searchTree", new searchTree($this, options));
            $this.data("type", "searchTree");
            return;
        }
        if (typeof options == 'string') {
            var value,
                param = Array.prototype.slice.call(arguments, 1),
                obj = $this.data("searchTree");
            value = obj[options].apply(obj, param);
            return typeof value === 'undefined' ? this : value;
        }
    };

    /**
     * 树组件
     * @param $el
     * @param options
     */
    var tree = function ($el, options) {
        this.$tree = $el;
        this.options = options;
        this.$searchBox;
        this.$outerBox;
        this.datas;
        this.ztree =null;
        this.initTree($el, options);
        this.initsearchBox();
    };

    /**
     * 树组件调用入口
     * @param options
     * @returns {$.fn}
     */
    $.fn.tree = function (options) {
        var $this = $.replaceTag($(this), "UL");
        if (typeof options == 'object') {
            $.include(['bootstrap/plugins/tree/css/zTreeStyle/zTreeStyle.css',
                'bootstrap/plugins/tree/css/zTreeStyle/bootstrap-zTree-custom.css',
                'bootstrap/plugins/tree/js/jquery.ztree.all.min.js',
                'bootstrap/plugins/tree/js/jquery.ztree.exhide.min.js']);
            options = $.extend(true, {}, $.treeDefault, options);//由于初始化参数为复杂对象
            var TreeObj = new tree($this, options);
            $this.data("tree", TreeObj);
            options.onLoaded.call(this, TreeObj.ztree);//执行  加载完毕 回调函数  参数为  树对象本身
            return;
        }
        if (typeof options == 'string') {
            var value,
                param = Array.prototype.slice.call(arguments, 1),
                obj = $this.data("tree");
            value = obj[options].apply(obj, param);
            return typeof value === 'undefined' ? this : value;
        }
    };
    /**
     * 返回ztree 对象
     * @returns {*}
     */
    tree.prototype.getTree = function () {
        return this.ztree;
    };
    /**
     * 获取节点数据
     */
    tree.prototype.getValue = function () {
        var nodes = this.ztree.getCheckedNodes(),
            that = this,
            value = '';
        $.each(nodes, function (i, node) {
            value += node[that.options.data.simpleData.idKey] + ",";
        });
        if (value.length > 0) {
            value = value.substring(0, value.length - 1);
        }
        return value;
    };

    tree.prototype.getText = function () {
        var nodes = this.ztree.getCheckedNodes(),
            that = this,
            text = '';
        $.each(nodes, function (i, node) {
            text += node[that.options.data.key.name] + ','
        });
        if (text.length > 0) {
            text = text.substring(0, text.length - 1);
        }
        return text;
    };

    /**
     * 初始化Ztree
     * @returns {*}
     */
    tree.prototype.initTree = function () {
        var ztree = null,
            $tree = this.$tree,
            options = this.options,
            that = this;

        $tree.addClass("ztree");//单选模式
        if (options.checkType == "radio") {
            options.check.enable = "true";
            options.check.chkStyle = "radio";
            options.check.radioType = "all";
            //多选模式
        } else if (options.checkType == "checkbox") {
            options.check.enable = "true";
        }
        options.callback['onClick'] = function (e, treeId, treeNode, clickFlag) {
            //that.clearTree();
            ztree.checkNode(treeNode, null, false, false);
            // 自定义节点  点击事件
            that.options.callback["clickNode"].call(that, e, treeId, treeNode, clickFlag);
        };
        // 2017.11.24 扩展自定义树节点文字颜色
        var view = {
            fontCss: setFontCss
        }
        options.view = $.extend({}, view, options.view)

        function setFontCss(treeId, treeNode) {
            return treeNode.color != null ? {color: treeNode.color} : {}
        };

        //初始化树
        if (this.options.url != '') {
            $.dajax({
                url: options.url,
                data: options.queryParam,
                async: true,
                success: function (resultData) {
                    that.datas = resultData;
                    processDate();
                    ztree = $.fn.zTree.init($tree, options, resultData)
                    that.ztree =ztree
                }
            });
        } else if (this.options.localdata != null) {
            that.datas = this.options.localdata;
            processDate();
            this.ztree = $.fn.zTree.init($tree, options, options.localdata);
        } /*else {
            this.ztree  = $.fn.zTree.init($tree, options);
        }*/

        /**
         * 节点数据 加工  包括 是否添加‘全部节点’ 是否只显示叶子节点的选择框
         */
        function processDate() {
            if (options.rootElement) {
                var obj = {};
                obj[options.data.simpleData.idKey] = options.data.simpleData.rootPId;
                obj[options.data.simpleData.pId] = '';
                obj['open'] = true;
                if (options.data.key.name) {
                    obj[options.data.key.name] = '全部';
                    obj['ISLEAF'] = 0
                }
                that.datas.push(obj);
            }
            $.each(that.datas, function (i, node) {
                node.chkDisabledInherit = true
                if (node.ISLEAF == 0 && !options.ISLEAF) {
                    node['nocheck'] = true;
                    return true;
                }
            });
        }
    };

    /**
     * 初始化查询栏目
     *
     */
    tree.prototype.initsearchBox = function () {
        var options = this.options,
            $tree = this.$tree,
            that = this;
        if (options.openSearch == true) {
            this.$searchBox = $("<input/>").attr({
                "type": "text",
                "placeholder": options.searchPlaceholder ? options.searchPlaceholder : '请输入关键字,按回车确认',
                "id": $tree.attr("id") + "_searchBox"
            }).addClass("form-control input-sm tree_search").css("width", "100%");
            $tree.before(this.$searchBox);

            this.$searchBox = $tree.prev(".tree_search");
            $tree.wrap('<div class="treeWrap">');
            this.$outerBox = $tree.parent();
            //固定搜索框，树滚动
            this.$outerBox.css({height: this.options.fixedTreeHeight, overflowY: "auto"});
            this.$searchBox.on("focus", function () {
                var modalBox = that.$searchBox.parents(".modal");
                var nodeList = [], check_nodeList = [];
                var treeObj = that.ztree;
                var newSearchVal = "", oldSearchVal = "";
                that.$searchBox.on("keydown", function (e) {
                    if (e.which == 13) {

                        //弹出树searchTree搜索框，查询结果只有一条数据时，再次回车，选中节点并确定
                        if (that.options.openSearch && that.options.dblEnterSearch) {
                            newSearchVal = that.$searchBox.val();
                            if (newSearchVal != oldSearchVal) {
                                nodeList = that.searchNodes($.trim(that.$searchBox.val()));
                                //获取搜索结果中可勾选的节点集合
                                if (nodeList.length > 0) {
                                    check_nodeList.splice(0, check_nodeList.length);//清空数组
                                    $.each(nodeList, function (i) {
                                        if (!nodeList[i].nocheck) {
                                            check_nodeList.push(nodeList[i]);
                                        }
                                    })
                                }
                                oldSearchVal = newSearchVal;
                            } else {
                                //若可勾选的节点长度为1，则回车自动勾选并触发确定按钮事件
                                if (check_nodeList.length == 1) {
                                    treeObj.checkNode(check_nodeList[0], true, true);
                                    modalBox.find("[id$='_modalok']").click();
                                }
                            }
                        } else {
                            that.searchNodes($.trim(that.$searchBox.val()))
                        }
                    }
                })
            });
            this.$searchBox.on("blur", function () {
                that.$searchBox.off("keydown")
            });
        }
    }

    /**
     * 查询树 检索节点方法
     * @param keyWord
     */
    tree.prototype.searchNodes = function (keyWord) {
        var treeOption = this.options,
            zTree = this.ztree,
            nodeList = [],
            nodes = zTree.transformToArray(zTree.getNodes());
        if (keyWord == '') {
            zTree.showNodes(nodes);
            return nodeList;
        }
        updateNodes(false);
        nodeList = zTree.getNodesByParamFuzzy(treeOption.data.key.name, keyWord, null);
        updateNodes(true);

        return nodeList;

        function updateNodes(highlight) {
            zTree.hideNodes(nodes);
            for (var n in nodeList) {
                findParent(zTree, nodeList[n]);
                nodeList[n].highlight = highlight;
                zTree.updateNode(nodeList[n]);
            }
            zTree.showNodes(nodeList);
        }

        function findParent(zTree, node) {
            zTree.expandNode(node, true, false, false);
            var pNode = node.getParentNode();
            if (pNode != null) {
                nodeList.push(pNode);
                findParent(zTree, pNode);
            }
        }
    };

    /**
     * 清除树所有节点 选中  和 勾选状态
     */
    tree.prototype.clearTree = function () {
        var ztree = this.ztree,
            checkNodes = ztree.getCheckedNodes();
        ztree.cancelSelectedNode();
        $.each(checkNodes, function (i, node) {
            ztree.checkNode(node, false, false, false);
        })
    };

    /**
     * 赋值 回写
     * @param  单个 或多个
     */
    tree.prototype.setValue = function (value) {
        this.clearTree();
        var that = this,
            value = value + '',
            ids = [],
            nodes = that.ztree.transformToArray(that.ztree.getNodes());
        if (value.indexOf(",") == -1) {
            ids[0] = value
        } else {
            ids = value.split(",")
        }
        $.each(ids, function (i, id) {
            $.each(nodes, function (j, node) {
                if (id == node[that.options.data.simpleData.idKey]) {
                    that.ztree.checkNode(node, null, false, false);
                    that.ztree.selectNode(node, true)
                    return true
                }
            })
        })
    };
    /**
     * 树组件重载
     * @param dataOption url参数 或本地数据对象   {localdata:{}} OR {url:XXXX,queryParam:{}}
     */
    tree.prototype.reload = function (dataOption) {
        this.ztree.destroy();
        this.options = $.extend({}, this.options, dataOption);
        this.ztree = this.initTree(this.$tree, this.options);
    };
    /**
     * 销毁树组件
     */
    tree.prototype.destroy = function () {
        var $tree = this.$tree;
        this.ztree.destroy();
        if (this.$searchBox != undefined) {
            /*$tree.unwrap();
            $tree.prev().remove();*/
            this.$outerBox.remove();
        } else {
            $tree.remove();
        }
    };
    /**
     * 继承构造器
     */
    var initializing = false;

    function jClass(baseClass, prop) {
        if (typeof (baseClass) === "object") {
            prop = baseClass;
            baseClass = null;
        }

        function F() {
            if (!initializing) {
                if (baseClass) {
                    this.baseprototype = baseClass.prototype;
                }
                this.init.apply(this, arguments);
            }
        }

        if (baseClass) {
            initializing = true;
            F.prototype = new baseClass();
            F.prototype.constructor = F;
            initializing = false;
        }

        for (var name in prop) {
            if (prop.hasOwnProperty(name)) {
                if (baseClass &&
                    typeof (prop[name]) === "function" &&
                    typeof (F.prototype[name]) === "function") {
                    F.prototype[name] = (function (name, fn) {
                        return function () {
                            this.base = baseClass.prototype[name];
                            return fn.apply(this, arguments);
                        };
                    })(name, prop[name]);
                } else {
                    F.prototype[name] = prop[name];
                }
            }
        }
        return F;
    }

    /***************** 小数位 ***************************/
    var decimal = jClass(textBox, {
        init: function (el, options) {
            this.base(el, options);
            this.initDecimal();
        }
    });
    $.fn.decimal = function (options) {
        var $this = $.replaceTag($(this), "INPUT"), obj = null;
        if (typeof options == 'object') {
            obj = $this.data("decimal");
            if (obj != null) {
                obj.options = $.extend({}, obj.options, options);
                return;
            }
            options = $.extend({}, $.textBoxDefault, options);
            $this.data("decimal", new decimal($this, options));
            $this.data("type", "decimal");
            return;
        }
        if (typeof options == 'string') {
            var value,
                param = Array.prototype.slice.call(arguments, 1),
                obj = $this.data("decimal");
            value = obj[options].apply(obj, param);
            return typeof value === 'undefined' ? this : value;
        }
    };
    /**
     * 初始化decimal（作为内部调用）
     */
    decimal.prototype.initDecimal = function () {
        var $textBox = this.$textBox,
            options = this.options;
        var stepnum = 1;
        for (var j = 0; j < options.decimalPlaces; j++) {
            stepnum = stepnum / 10;
        }
        $textBox.attr({"step": stepnum}).val(stepnum);

        if (options.decimalPlaces > 0) {
            $textBox.on("blur", function () {
                var digit = parseInt(options.decimalPlaces) + 2;
                var val = parseFloat($textBox.val()).toFixed(digit);//获取input的值转换成string型，并指定小数点后(比指定位数多一位)位数
                var newval = val.substring(0, val.lastIndexOf(".") + (digit - 1));//截取字符串
                $textBox.val(newval);
            })
        }
    };

})(jQuery);