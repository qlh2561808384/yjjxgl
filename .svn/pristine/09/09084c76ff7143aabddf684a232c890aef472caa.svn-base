/**
 * 
 * 
 * @Title: bootstrap 功能包装类
 * @author hjz
 * @throws
 */
/**
 * 主要封装了以下的组件
 * 
 *       ui类
 * dtree    生成数
 * dgrid    生成表格数据
 * 
 * 
 *       工具类
 * include  动态导入数据
 * dajax    ajax请求
 * dloadformdata 自动根据dname 从传入的js对象中获取数据
 * ddisable   指定jquery对象中设置了dname的各项disabled设置为指定值
 */
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

(function ($) {
   
	
	
	
	var dn_methods = {
		getRootPath:  function() {
    		// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    		var curWwwPath = window.document.location.href;
    		// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    		var pathName = window.document.location.pathname;
    		var pos = curWwwPath.indexOf(pathName);
    		// 获取主机地址，如： http://localhost:8083
    		var localhostPaht = curWwwPath.substring(0, pos);
    		// 获取带"/"的项目名，如：/uimcardprj
    		var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    		if("file://"===localhostPaht){
				var lastpath =curWwwPath.substr(curWwwPath.lastIndexOf('/bootstrap/')+11,curWwwPath.length);
    			var str=lastpath.split("/");
				var returnstr="";
				for(var i=0;i<str.length;i++){
					if(i==str.length-1){
						returnstr+="..";
					}else{
						returnstr+="../";
					}
				}
				return  returnstr;
    		}else{
    			return (localhostPaht + projectName);
    		}
       }
	};
   
  



 $.extend({
	include : function(file) {
		var includePath = dn_methods["getRootPath"]();
		var files = typeof file == "string" ? [ file ] : file;
		for (var i = 0; i < files.length; i++) {
			var values = files[i].split("|");
			var name = values[0].replace(/^\s|\s$/g, "");
			var att = name.split('.');
			var ext = att[att.length - 1].toLowerCase();
			var isCSS = ext == "css";
			var tag = isCSS ? "link" : "script";
			var attr = isCSS ? " type='text/css' rel='stylesheet' "
					: " language='javascript' type='text/javascript' ";

			var link = (isCSS ? "href" : "src") + "='" + includePath + "/"
					+ name + "'";
			if ($(tag + "[" + link + "]").length == 0) {
				if(values.length==3){//判断是否需要在指定资源前加入数据
					
					var name2 = values[2].replace(/^\s|\s$/g, "");
					var att2 = name2.split('.');
					var ext2 = att2[att2.length - 1].toLowerCase();
					var isCSS2 = ext2 == "css";
					var tag2 = isCSS2 ? "link" : "script";
					
					
					
					if(values[1]=="before"){
						var link2 =  (isCSS2 ? "href" : "src") + "='" + includePath + "/"+ values[2] + "'";
						if ($(tag2 + "[" + link2 + "]").length != 0){
							$(tag2 + "[" + link2 + "]").before("<" + tag + attr + link + "></" + tag + ">");
						}else{
							$("head").append("<" + tag + attr + link + "></" + tag + ">");
						}
					}else if(values[1]=="after"){
						var link2 =  (isCSS2 ? "href" : "src") + "='" + includePath + "/"+ values[2] + "'";
						if ($(tag2 + "[" + link2 + "]").length != 0){
							$(tag2 + "[" + link2 + "]").after("<" + tag + attr + link + "></" + tag + ">");
						}else{
							$("head").append("<" + tag + attr + link + "></" + tag + ">");
						}
					}else if(values[1]=="likebefore"){
						var link2 =  (isCSS2 ? "href" : "src") + "*='" + values[2] + "'";
						
						if ($(tag2 + "[" + link2 + "]").length != 0){
							$(tag2 + "[" + link2 + "]").before("<" + tag + attr + link + "></" + tag + ">");
						}else{
							$("head").append("<" + tag + attr + link + "></" + tag + ">");
						}
					}else if(values[1]=="likeafter"){
                        var link2 =  (isCSS2 ? "href" : "src") + "*='" + values[2] + "'";
						if ($(tag2 + "[" + link2 + "]").length != 0){
							$(tag2 + "[" + link2 + "]").after("<" + tag + attr + link + "></" + tag + ">");
						}else{
							$("head").append("<" + tag + attr + link + "></" + tag + ">");
						}
					}else{
						$("head").append("<" + tag + attr + link + "></" + tag + ">");
					}
					
				}else{
					$("head").append("<" + tag + attr + link + "></" + tag + ">");
				}
			}
		}
	},
	 /**
	  *  用于替换标签
	  * @param $target 目标jquery 对象
	  * @param tagName 转换为指定标签 需为大写
	  * @returns {*}
	  */
	 replaceTag:function($target,tagName){
		 var target=$target.get(0),
			 attrs=target.attributes,
			 $newTag=$("<"+tagName+">");
		 if(target.tagName==tagName){
			 return $target;
		 }
		 //替换所有目标标签属性
		 $.each(attrs,function(i,attr){
			 $newTag.attr(attr.name,attr.value);
		 });
		 $target.after($newTag).remove();
		 return $newTag;
	 }
   });
	$.include('bootstrap/plugins/input/core1.0.js');

	var boxType={'textBox':'','dateBox':'','comboBox':'','comboTree':'','searchTree':''};
	var formMthod = {
		//注意 使用  自动生成  form   需要对 form 元素 指定id
		initForm: function (target, options) {
			var $this = $(target),
				colWidth = Math.floor(12 / options.colnum),
				objsetting = {
					colspan: 1,
					defaultvalue: "",
					disabled: false,
					title: ''
				};
			$this.addClass("form-inline clearfix form-sm");
			if (!options.inputs.length)return;
			$.each(options.inputs, function (i, inputObj) {
				inputObj = $.extend({}, objsetting, inputObj);
				var proving= inputObj.proving==null?"":"proving="+"'"+inputObj.proving+"'";
				var inputid = inputObj.id?inputObj.id:'form-' + $this.attr("id") + '-' + inputObj.name;
				var html = $("<div/>").addClass("dform-input form-group dis_flex col-sm-" + colWidth * inputObj.colspan);
				var label = $("<label/>").
					addClass("control-label").
					attr("for", inputid).
					css({'width': options.labelwidth, 'line-height': '30px'}).
					html(inputObj.title);
				var dname = inputObj.dname == null ? inputObj.name : inputObj.dname;
				//todo 表单组件 初始化参数 添加初始化时 是否禁用  disabled true false
				var inputhtml = $("<input/>").addClass("form-control ").attr({
					"id": inputid,
					'dname': dname,
					'name': inputObj.name
				});

				html.append(label).append(inputhtml).appendTo($this);
				var $inputEl = $this.find("#" + inputid);
				if(boxType[inputObj.type]==''){
					$inputEl[inputObj.type](inputObj);
					$inputEl.parent().addClass("flex_val_1")
				}else{
					var inputHtmlStr = "<div class='dform-input form-group dis_flex col-sm-" + colWidth * inputObj.colspan + "' >";
					if (inputObj.type == "select"||inputObj.type == "dselect") {
						inputHtmlStr += '<div><select class="form-control flex_val_1"  '+(inputObj.disabled?'disabled':'')+' id="form-'+inputObj.name+'" '+proving+' dname="'+dname+'"  name="' + inputObj.name + '"></select></div>';
						$this.append(inputHtmlStr);
						$this.find(".form-group select:last").dselect(inputObj);
						$("#form-"+inputObj.name) .parent().addClass("flex_val_1");
					} else if (inputObj.type == "ddate") {//日期选择
						inputHtmlStr += '<div><input id="form-'+inputObj.name+'" type="text"  '+(inputObj.disabled?'disabled':'')+' '+proving+' dname="'+dname+'" name="' + inputObj.name + '" class="form-control" /></div>'
						$this.append(inputHtmlStr);
						$this.find(".form-group input:last").ddate(inputObj);

						$("#form-"+inputObj.name).parent().addClass("flex_val_1");
					} else if (inputObj.type == "dsearchtree") {
						inputHtmlStr += '<div><input id="form-'+inputObj.name+'" type="text" '+(inputObj.disabled?'disabled':'')+' '+proving+' dname="'+dname+'" name="' + inputObj.name + '" class="form-control" style="width:100%;" /></div>';
						$this.append(inputHtmlStr);
						$this.find(".form-group input:last").searchTree(inputObj);
						$("#form-"+inputObj.name).parent().addClass("flex_val_1");
					} else if(inputObj.type == "ddecimal"){//小数输入框
						inputHtmlStr += '<div><input id="form-'+inputObj.name+'" type="number" '+(inputObj.disabled?'disabled':'')+' '+proving+' dname="'+dname+'" name="' + inputObj.name + '" class="form-control flex_val_1" /></div>';
						$this.append(inputHtmlStr);
						$this.find(".form-group input:last").ddecimal(inputObj);
					} else if(inputObj.type == "html"){//小数输入框
						inputHtmlStr +=inputObj.html;
						inputHtmlStr += "</div>";
						$this.append(inputHtmlStr);
					} else if(inputObj.type == "hidden"){
						inputHtmlStr += '<input id="form-'+inputObj.name+'"   dname="'+dname+'"  name="' + inputObj.name + '" type="' + inputObj.type + '"  class="form-control flex_val_1" '
						+  (inputObj.placeholder==true?('  placeholder="请输入' + inputObj.title):'')
						+ '" value="' + inputObj.defaultvalue + '"></div>';
						$this.append(inputHtmlStr);
						$this.find(".dform-input:last").hide();
					}else {
						inputHtmlStr += '<div><input id="form-'+inputObj.name+'"  '+proving+' dname="'+dname+'"  name="' + inputObj.name + '" type="' + inputObj.type + '" '+(inputObj.disabled?'disabled':'')+' class="form-control flex_val_1" '
						+  (inputObj.placeholder==true?('  placeholder="请输入' + inputObj.title):'')
						+ '" value="' + inputObj.defaultvalue + '"></div>';
						$this.append(inputHtmlStr);
					}
				}
			})
		},
		upload: function (target, options, param) {
	        jQuery.ajax(options);
			$(target).ajaxSubmit(param);
		},
		submit: function (target, options, param) {
			var data = formMthod.getData(target);
			if (param['data']) {
				var paramdata = param.data;
				param['data'] = $.extend({}, paramdata, data);
			} else {
				param['data'] = data;
			}
			$.dajax(param);
		},
		getData: function (target) {
			var data = $(target).serialize();
			data = decodeURIComponent(data, true);//防止中文乱码
			data = data.replace(/&/g, "\",\"");
			data = data.replace(/=/g, "\":\"");
			data = '{' + (data == "" ? "" : '"' + data + '"') + '}';
			data = eval("(" + data + ")");
			return data;
		},
		load:function(obj, options, param){
			$(obj).find("[name]").each(function(){
				if($(this).attr("boxType")!= undefined  && $(this).attr('name')!= undefined){
					$(this).parent().find(".form-control")[$(this).attr("boxType")]("setValue",param[ $(this).attr('name')])
				}
				var key = $(this).attr("name");
				if(param[key]!=null){
					$(this).val(param[key]);
				}else{
					$(this).val("");
				}
			});
			formMthod.executeInputMethod(obj, "setValue", param);
		},
		executeInputMethod: function (obj, method, param) {
			$("[name][boxType]", obj).each(function () {
				if ($(this).attr("name") != undefined && $(this).attr("boxType") != undefined) {
					$(this).parent().find(".form-control")[$(this).attr("boxType")](method, method == 'setValue' ? param[$(this).attr("name")] : param)
				}
			});
		}
	};


	$.fn.dform=function(options){
		$.include(['bootstrap/plugins/form/jquery.form.js',
			'bootstrap/plugins/form/form-common.css',
			'bootstrap/plugins/form/form-common-custom.css']);
		var target=$(this);
		if(typeof options == 'object') {
			var setting={
				rownum:3,   //每行控件数目
				labelwidth:"50px",
				showtitle: "left"   //标题的布局，默认为居左，    top为居上，hidden为隐藏

			};
			options = $.extend({}, setting, options);

			target.addClass("form-inline clearfix");
			target.addClass("form-sm");//todo 后续 根据 配置参数 设置input 尺寸

			$(this).html("");
			if (options.inputs != null) {
				var colWidth = Math.floor(12 / options.rownum);
				var objsetting = {
					id:null,
					colspan: 1,
					defaultvalue: "",
					disabled:false
				};
				for (var i = 0; i < options.inputs.length; i++) {
					var obj = options.inputs[i],
						inputId = obj.id==null ? "form-"+obj.name : obj.id;
					obj = $.extend({}, objsetting, obj);

					//行部分
					var html = "<div class='dform-input form-group dis_flex col-sm-" + colWidth * obj.colspan + "' >";//style='min-height: 50px; margin-bottom: 0;'

					//title部分
					if (obj.type != null && options.showtitle == "left") {
						html += "<label class='control-label' for='"+inputId+"' style='width:" + options.labelwidth + " ;line-height:30px'>" + obj.title + "</label>";
					}
					var proving= obj.proving==null?"":"proving="+"'"+obj.proving+"'";
					var dname=obj.dname==null?obj.name:obj.dname;

					//输入框部分
					if (obj.type == null) {
						html += "</div>";
						$(this).append(html);
					} else if (obj.type == "select"||obj.type == "dselect") {
						html += '<select class="form-control flex_val_1"  '+(obj.disabled?'disabled':'')+' id="'+inputId+'"  '+proving+' dname="'+dname+'"  name="' + obj.name + '"></select></div>';
						$(this).append(html);
						//$(this).find(".form-group input:last").comboBox(obj);
						$(this).find(".form-group select:last").dselect(obj);
						$("#"+inputId) .parent().addClass("flex_val_1");
					} else if (obj.type == "ddate") {//日期选择
						html += '<input id="'+inputId+'" type="text"  '+(obj.disabled?'disabled':'')+' '+proving+' dname="'+dname+'" name="' + obj.name + '" class="form-control" /></div>'
						$(this).append(html);
						$(this).find(".form-group input:last").ddate(obj);

						$("#"+inputId).parent().addClass("flex_val_1");
					} else if (obj.type == "dsearchtree") {
						html += '<input id="'+inputId+'" type="text" '+(obj.disabled?'disabled':'')+' '+proving+' dname="'+dname+'" name="' + obj.name + '" class="form-control" /></div>';
						$(this).append(html);
						$(this).find(".form-group input:last").searchTree(obj);
						$("#"+inputId).parent().addClass("flex_val_1");
					} else if(obj.type == "ddecimal"){//小数输入框
						html += '<input id="'+inputId+'" type="number" '+(obj.disabled?'disabled':'')+' '+proving+' dname="'+dname+'" name="' + obj.name + '" class="form-control flex_val_1" /></div>';
						$(this).append(html);
						$(this).find(".form-group input:last").ddecimal(obj);
					} else if(obj.type == "html"){//小数输入框
						html +=obj.html;
						html += "</div>";
						$(this).append(html);
					} else if(obj.type == "hidden"){//小数输入框
						html += '<input id="'+inputId+'"  '+proving+' dname="'+dname+'"  name="' + obj.name + '" type="' + obj.type + '" '+(obj.disabled?'disabled':'')+' class="form-control flex_val_1" '
						+  (obj.placeholder==true?('  placeholder="请输入' + obj.title):'')
						+ '" value="' + obj.defaultvalue + '"></div>';
						$(this).append(html);
						$(this).find(".dform-input:last").hide();
					}else if(obj.type == "comboTree"){
						html += '<input id="'+inputId+'"  '+proving+' dname="'+dname+'"  name="' + obj.name + '" type="' + obj.type + '" '+(obj.disabled?'disabled':'')+' class="form-control flex_val_1" '
						+  (obj.placeholder==true?('  placeholder="请输入' + obj.title):'')
						+ '" value="' + obj.defaultvalue + '"></div>';
						$(this).append(html);
						$(this).find(".form-group input:last").comboTree(obj);
						$("#"+inputId).parent().addClass("flex_val_1");
					} else if(obj.type == "textBox"){
						html += '<input id="'+inputId+'"  '+proving+' dname="'+dname+'"  name="' + obj.name + '" type="' + obj.type + '" '+(obj.disabled?'disabled':'')+' class="form-control flex_val_1" '
						+  (obj.placeholder==true?('  placeholder="请输入' + obj.title):'')
						+ '" value="' + obj.defaultvalue + '"></div>';
						$(this).append(html);
						$(this).find(".form-group input:last").textBox(obj);
						$("#"+inputId).parent().addClass("flex_val_1");
					} else if(obj.type=="comboBox"){
						html += '<input id="'+inputId+'"  '+proving+' dname="'+dname+'"  name="' + obj.name + '" type="' + obj.type + '" '+(obj.disabled?'disabled':'')+' class="form-control flex_val_1" '
						+  (obj.placeholder==true?('  placeholder="请输入' + obj.title):'')
						+ '" value="' + obj.defaultvalue + '"></div>';
						$(this).append(html);
						$(this).find(".form-group input:last").comboBox(obj);
						$("#"+inputId).parent().addClass("flex_val_1");
					}else if(obj.type=="dateBox"){
						html += '<input id="'+inputId+'"  '+proving+' dname="'+dname+'"  name="' + obj.name + '" type="' + obj.type + '" '+(obj.disabled?'disabled':'')+' class="form-control flex_val_1" '
						+  (obj.placeholder==true?('  placeholder="请输入' + obj.title):'')
						+ '" value="' + obj.defaultvalue + '"></div>';
						$(this).append(html);
						$(this).find(".form-group input:last").dateBox(obj);
						$("#"+inputId).parent().addClass("flex_val_1");
					}else if(obj.type=="checkbox") {
						if (!obj.options)return;
						$.each(obj.options, function (i, option) {
							html += '<lable style="margin-left: 8px;line-height: 30px;">' + option.lable + '</lable>';
							html += '<input  name="' + obj.name + '" type="checkbox" value="' + option.value + '" style="vertical-align: middle;margin-top: 8px;">';
						});
						html += '</div>';
						$(this).append(html);
					}else{
						html += '<input id="'+inputId+'"  '+proving+' dname="'+dname+'"  name="' + obj.name + '" type="' + obj.type + '" '+(obj.disabled?'disabled':'')+' class="form-control flex_val_1" '
						+  (obj.placeholder==true?('  placeholder="请输入' + obj.title):'')
						+ '" value="' + obj.defaultvalue + '"></div>';
						$(this).append(html);
					}
				}
			}
		}else{
			if(options=='upload'){
				var param=arguments[1];
				param.beforeSubmit=function (request, jqForm, option) {
					var _beforSend=option.beforeSend;
					option.beforeSend=function (req) {
			            req.setRequestHeader("key",top.currkey);
			            req.setRequestHeader("sessID",top.sessId);
			           if(_beforSend){
			               _beforSend(req);
			           }
			        }
		        };
				$(this).ajaxSubmit(param);
			}else if(options=='submit'){
				var param=arguments[1],datas=this.serializeArray(),data={};
				$.each(datas, function () {
					if (data[this.name] !== undefined) {
						if (!data[this.name].push) {
							data[this.name] = [data[this.name]];
						}
						data[this.name].push(this.value || '');
					} else {
						data[this.name] = this.value || '';
					}
				});
				if(param['data']){
					param['data']= $.extend({},param['data'],data);
				}else{
					param['data']= data;
				}
				$.dajax(param);
			}else if(options=='clear'){
				$(this).clearForm();
				formMthod.executeInputMethod(this,"clear");
			}else if(options=='reset'){
				$(this).reset();
			}else if(options=='disabled'){
				$(':input',this).each(function() {
					this.disabled=true;
				});
				formMthod.executeInputMethod(this,"disable");
			}else if(options=='enable'){
				$(':input',this).each(function() {
					this.disabled=false;
				});
				formMthod.executeInputMethod(this,"enable");
			}else if(options=='validate'){
				var flag =true;
				$('[proving]',this).each(function() {
					if(!$.dvalidate($(this),true)){
						flag=false;
						$(this).change();
					}
				});
				$("[name][boxType]", $(this)).each(function () {
					if ($(this).attr("name") != undefined && $(this).attr("boxType") != undefined) {
						if(!$(this).parent().find(".form-control")[$(this).attr("boxType")]("validate")) flag = false;
					}
				});
				return flag;
			}else if(options=='getData'){
				var datas=this.serializeArray(),o={};
				$.each(datas, function () {
					if (o[this.name] !== undefined) {
						if (!o[this.name].push) {
							o[this.name] = [o[this.name]];
						}
						o[this.name].push(this.value || '');
					} else {
						o[this.name] = this.value || '';
					}
				});
				return o;
			}else if(options=='destroy'){
				$(this).find("[name]").each(function(){
					if($(this).attr("boxType")!= undefined){
						$(this).parent().find(".form-control")[$(this).attr("boxType")]("destroy")
					}
				});
				$(this).remove();
			}
		}
		if(options.formtitle && options.formtitle!=""){
			target.wrapInner("<fieldset class='form-fieldset'></fieldset>");
			target.find("fieldset").prepend("<legend class='form-legend'>"+options.formtitle+"</legend>");
		}
	};

$.fn.ddecimal = function(options){
	var target=$(this);
	var setting = {
		title: "dectitle",
		name: "decname",
		type: "ddecimal", //type为ddecimal
		did: "decimalid",
		dname: "decimalname",
		decimalPlaces: 2 //小数位数
	};
	options= $.extend({},setting,options);
	
	var stepnum = 1;
	for(var j=0; j<options.decimalPlaces; j++){
		stepnum = stepnum / 10;
	}
	target.attr({"step":stepnum, "value":stepnum});
	target.addClass("form-control");
	
	if(options.decimalPlaces > 0){
		target.on("blur", function(){
			var digit = options.decimalPlaces + 1;
			var val = parseFloat(target.val()).toFixed(digit);//获取input的值转换成string型，并指定小数点后(比指定位数多一位)位数
			var newval = val.substring(0, val.lastIndexOf(".")+digit);//截取字符串
			target.val(newval);
		})
	}
}


$.fn.ddate=function(options){
	var target=$(this);
	var setting={
		language: 'zh-CN',  //设置时间控件为中文
		removeIcon: true, //重置按钮，默认显示
        disabled: false //是否禁用时间控件
	};
	options= $.extend({},setting,options);

	target.wrap('<div class="datepicker"></div>');
	target.attr({value:options.defaultValue==null?'':options.defaultValue, readonly:"readonly"});
	target.addClass("form-control");
	
	var tparent = target.parent(".datepicker");
	tparent.addClass("input-group date");

	var html = "";
	html += '<input type="hidden" name="'+(options.dname==null?'':options.dname)+'" value="'+(options.defaultValue==null?'':options.defaultValue)+'" />'
	if(options.removeIcon){
		html += '<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>'
	}
	html += '<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>';
	target.after(html);
	$.include(['bootstrap/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css',
		'bootstrap/plugins/datetimepicker/js/bootstrap-datetimepicker.min.js',
		'bootstrap/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.'+options.language+'.js',
		'bootstrap/plugins/datetimepicker/css/bootstrap-datetimepicker-custom.css']);

	var returnobj = tparent.datetimepicker(options);
    if(options.disabled){
        target.attr("disabled","disabled");
        tparent.find(".input-group-addon").unbind("click");
    }

    return returnobj;
};
   







$.extend({
	putObj : function(id,obj) {
		if($.fn.dobj==null){
			$.fn.dobj = new Object();
		}
		$.fn.dobj[id]=obj;
	 
	}
});

$.extend({
	getObj : function(id) {
		return $.fn.dobj[id];
	}
});


// 使用方法
// $.include(['json2.js', 'jquery.tree.js',
// 'jquery.tree.css|after|jquery.ui.css']);
// jquery.tree.css|after|jquery.ui.css表明是jquery.tree.css需要放在jquery.ui.css后面
/**
 * 树
 */
$.fn.dtree = function(options) {
	$.include(['bootstrap/css/font-awesome.min.css',
	           //'bootstrap/plugins/tree/css/ztree.css',
	           'bootstrap/plugins/tree/css/zTreeStyle/zTreeStyle.css',
			   'bootstrap/plugins/tree/css/zTreeStyle/bootstrap-zTree-custom.css',
	           'bootstrap/plugins/tree/js/jquery.ztree.all.min.js']);
	
	var target = $(this);
	var tree, treeobj, hideInp;
	var targetId; //目标对象的唯一标识
	if(target.attr("id")){
		targetId = target.attr("id");
	}else{
		targetId = "id" + (new Date().getTime());
		target.attr("id", targetId);
	}
	var settings = {
		checkType: 'nocheck', //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
		ISLEAF: false, //checkType为checkbox或radio时，非叶子节点是否有选择框，false无可选框
		isOpen: false, //节点是否全部展开
		isPopupTree: false, //是否为弹出树
		rootElement: false, //是否添加“全部”节点
		labelVal: "label",
		check: {},
		data: {
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
		},
		callback: {}
	}
	options = $.extend({}, settings, options);
	
	//给target套一个父级元素
	target.wrap('<div class="ztreeBox"></div>');

	//判断是否为弹出树
	if(options.isPopupTree){
		target.addClass("treeSel form-control");
		target.attr({readonly:"readonly"});

		//插入input框前的标签文字
		target.before("<lable>"+options.labelVal+"</label>");
		
		//target外部嵌套
		target.wrap('<div class="ztreeBox_inp ztreeBox_down"></div>');	

		//html插入树结构ul并获取
		target.after('<ul id="'+ targetId+"_tree" +'" class="ztree ztree_abs"></ul>');
		treeobj = $("#"+targetId+"_tree");

		//html插入小图标
		target.after('<i class="fa fa-angle-down"></i>');
		target.css({backgroundColor:"#FFF"});

		//html插入隐藏域并获取
		target.before('<input type="hidden" id="'+ targetId+"_hideInp" +'"name="'+ target.attr("name") +'" value="" />');
		target.removeAttr("name");
		hideInp = $("#"+targetId+"_hideInp");

		//点击input弹出树
		target.parent().on('click', function(){
			showMenu(target, treeobj);
		})
	}else{
		treeobj = target;
		target.addClass("ztree");
	}
	
	//选择框类型
	if(options.checkType == "radio"){
		options.check.enable = "true";
		options.check.chkStyle = "radio";
		options.check.radioType = "all";
	}else if(options.checkType == "checkbox"){
		options.check.enable = "true";
	}

	//回调函数
	var func_click = options.callback.onClick;
	var func_check = options.callback.onCheck;
	options.callback.onClick = function(e, treeId, treeNode){
		onClick(e, treeId, treeNode);
		if(func_click != null) func_click(e, treeId, treeNode);
	};
	options.callback.onCheck = function(e, treeId, treeNode){
		onCheck(e, treeId, treeNode);
		if(func_check != null) func_check(e, treeId, treeNode);
	};

    
	//输出树
	if(options.localdata!=null){
		//是否添加根节点“全部”
	    if(options.rootElement){
	    	var obj={}
	    	obj[options.data.simpleData.idKey]=options.data.simpleData.rootPId;
	    	obj[options.data.simpleData.pId]='';
	    	obj['open']=true;
	    	if(options.data.key.name){
	    		obj[options.data.key.name]='全部';
	    	}
	    	options.localdata.push(obj);
	    }
		tree = $(this).zTree.init(treeobj, options, options.localdata);
	}else if(options.url!=null){
		$.dajax({
			url: options.url,
			async:false,
			success: function(data1) {
				//是否添加根节点“全部”
			    if(options.rootElement){
			    	var obj={}
			    	obj[options.data.simpleData.idKey]=options.data.simpleData.rootPId;
			    	obj[options.data.simpleData.pId]='';
			    	obj['open']=true;
			    	if(options.data.key.name){
			    		obj[options.data.key.name]='全部';
			    	}
			    	data1.push(obj);
			    }
				tree = $(this).zTree.init(treeobj, options, data1);
			}
		});
	}else{
		tree = $(this).zTree.init(treeobj,options);
	}
	
	//是否展开
    if(options.isOpen){
		tree.expandAll(true);
    }
	//父节点是否有选择框
	if(!options.ISLEAF && options.checkType != "nocheck"){
		var node = tree.getNodes();
		var nodes = tree.transformToArray(node);
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].ISLEAF == 1){
				nodes[i].nocheck=false;
				tree.updateNode(nodes[i]);
			}else{
				nodes[i].nocheck=true;
				tree.updateNode(nodes[i]);
			}
		}
	}

	//有选择框
	function beforeClick(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId),
		nodes = zTree.getCheckedNodes(true),
		v = "", dv = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i][options.data.key.name] + ",";
			dv += nodes[i][options.data.simpleData.idKey] + ",";
		}
		if (v.length > 0 ){
			v = v.substring(0, v.length-1);
			dv = dv.substring(0, dv.length-1);
		}
		if(options.isPopupTree){
			hideInp.attr("value", dv);
			target.attr("value", v);
		}
	}
	//显示树菜单
	function showMenu(dtreeSel, dtreeObj) {
		var dtreeOffset = dtreeSel.position();
		dtreeObj.css({left:dtreeOffset.left , top:dtreeSel.outerHeight(), paddingRight:'20px'}).slideDown("fast");

		$(document).on('mouseup', function(e){
			if(!dtreeSel.is(e.target) && !dtreeObj.is(e.target) && dtreeObj.has(e.target).length === 0){
				dtreeObj.hide();
			}
		});
	}
    
	return tree;
    
}


$.fn.dtable=function(options){
	
	var setting={
		cache: false,//是否使用缓存
		//height: $(document).height()-60,
		cache:false,//禁用ajax缓存
		editable:false,//行内编辑 默认关闭
		showRefresh:true,//刷新按钮,默认显示
		pageNumber:1,//初始页
		pageSize:30,//初始每页大小
		method:'post',//请求方法 默认post
		pagination:true,//分页条, 默认true
		paginationHAlign: 'left',//分页按钮位置  left/right,默认居左
		pageList:[10, 20, 30],//分页大小选择列表
		striped:true,//单双行bgc区分
		queryParamsType:'limit',//请求参数类型  默认limit 请求参数为 limit offset等 设为其他 则为pageNumber pageSize等
		contentType: "application/x-www-form-urlencoded",//默认为json
		editable:false,   //是否可编辑，默认否
		mode: "inline", //编辑框的模式：支持popup和inline两种模式，默认是inline
        resizable: false //手动拉伸单元格
	};
	if(typeof options === 'object' && options){
		options= $.extend({},setting,options);
		$.include(['bootstrap/plugins/table/css/bootstrap-table.css',
			'bootstrap/plugins/table/js/bootstrap-table_v1.js',
			'bootstrap/plugins/table/js/bootstrap-table-zh-CN.js',
			'bootstrap/plugins/table/css/bootstrap-table-custom.css']);
		$.include(['bootstrap/plugins/table/js/table_editor.js'])
		if(options.editable){
			if(options.columns!=null){
                  var columndefault = {
					  mode: options.mode    //编辑时默认内联
				  }
				  for(var i=0;i<options.columns.length;i++){
					  if(options.columns[i].editable!=null){
						  options.columns[i].editable=$.extend({}, columndefault, options.columns[i].editable);
					  }
				  }
			}
			
			
			$.include([
				'bootstrap/plugins/table/js/bootstrap-editable.js',
				'bootstrap/plugins/table/js/bootstrap-table-editable.js',
				'bootstrap/plugins/table/css/bootstrap-editable.css'
			]);
		}
	}

    if(options.resizable){
        $.include(['bootstrap/plugins/table/js/colResizable-1.6.js',
            'bootstrap/plugins/table/js/bootstrap-table-resizable.js'])
    }

	return $(this).bootstrapTable(options);
}
$.extend({
	dinit : function(options) {
		var defaultsetting = {
				proving : true
		}
		options = $.extend({}, defaultsetting, options);
		if(options.proving){
			$.include([ 'bootstrap/plugins/layer/layer.js']);
			$.include([ 'bootstrap/plugins/layer/skin/layer.css']);
			  
			$(document).on("change",'[proving]',function(){
				   if(!$.dvalidate($(this))){
					   layer.close($(this).attr("tipsid")); 
					   var index = layer.tips($("this").attr("title")==null?"输入数据错误":$("this").attr("title"), $(this), {
						   tipsMore: true
//						   time:3
					   });
					  $(this).attr("tipsid",index);
				   }else{
					  layer.close($(this).attr("tipsid")); 
				   }
			})
		}
		
		
	}
})

$.extend({
	dvalidate : function(obj) {
		var provings  =obj.attr("proving").split("|");
	      for(var i=0;i<provings.length;i++){
	    	  var value = obj.val();
	    	  if(provings[i]=="notEmpty"){
	    		  if(value==""){
	    			  return false;
	    		  }
	    	  }else if(provings[i]=="email"){
	    		//对电子邮件的验证
	    		 var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	    		 if(value!=""&&!myreg.test(value)){
	    			 return false;
	    		 }
	    	  }else if(provings[i]=="idcard"){
	    		  if(value!=""&& !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(value)){
	    			  return false;
	              }
	    	  }else if(provings[i]=="phone"){
	    		  if(value!=""&& !/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value)) 
	    		  { 
	    			  return false;
	    		  } 
	    	  }else if(provings[i]=="url"){
	    		  var RegUrl = new RegExp(); 
	    		  RegUrl.compile("^[A-Za-z]+://[A-Za-z0-9-_]+\\.[A-Za-z0-9-_%&\?\/.=]+$");//jihua.cnblogs.com 
	    		  if (value!=""&& !RegUrl.test(value)) { 
	    			  return false;
	    		  } 
	    	  }else{
	    		  var f = eval(provings[i]);
	    		  if(!f){
	    			  return false;
	    		  }
	    	  }
	      }
		  return true;
		
	}
})


$.extend({
	dajax : function(options) {
		$.include([ 'bootstrap/plugins/layer/layer.js']);
		$.include([ 'bootstrap/plugins/layer/skin/layer.css']);
		var index ;
		var defaultsetting = {
			type : "POST",
			loadMsg:"加载中",
			dataType : "json",
			beforeSend: function(req){
			   index = layer.load(1);
				req.setRequestHeader("key",top.currkey);
				req.setRequestHeader("sessID",top.sessId);
			},
			complete: function(){
				layer.close(index);   
			}
		}
		options = $.extend({}, defaultsetting, options);
		$.ajax(options);
	}
});

$.extend({
	editTableCell : function(settings){
		var defaultSettings = {
			tableobj: $("#datatable"), //table对象
			rowNum: 1, //行
			columnNum: 1, //列
			editVal: { //可编辑的参数
				type:"text",
				title:"title"
			}
		}
		$.extend(defaultSettings, settings);
		
		var rowLen = settings.tableobj.find("tr").length;
		var columnLen = settings.tableobj.find("tr:eq(0)").children().length;
		
		var cell; //单元格
		drowNum = settings.rowNum;
		dcolumnNum = settings.columnNum -1;
		if(drowNum != null && dcolumnNum != null){
			cell = settings.tableobj.find("tr").eq(drowNum).find("td").eq(dcolumnNum);
		}
		var tabledata = settings.tableobj.data("bootstrap.table"); //表格数据
		var index = drowNum - 1;
		var datapk = tabledata.data[index].guid;
		
		var datafield = tabledata.columns[drowNum].field;
		var datavalue = cell.text();
		
		cell.wrapInner('<a href="javascript:void(0)" data-name="'+datafield+'" data-pk="'+datapk+'" data-value="'+datavalue+'"></a>');
		cell.find("a").editable(settings.editVal);
		return cell;
	}
})


$.extend({
	dalert : function(options) {
	   $.include([ 'bootstrap/plugins/layer/layer.js']);
	   $.include([ 'bootstrap/plugins/layer/skin/layer.css']);
       var defaultsetting = {
    		   text:"",
    		   icon:1
	   }
       
	   options = $.extend({}, defaultsetting, options);
       layer.alert(options.text,options);
	}
});
$.extend({
	dconfirm : function(options) {
	   $.include([ 'bootstrap/plugins/layer/layer.js']);
	   $.include([ 'bootstrap/plugins/layer/skin/layer.css']);
       var defaultsetting = {
    		 btn:["确定","取消"] 
	   }
       
	   options = $.extend({}, defaultsetting, options);
       var str ="layer.confirm(options.text,options";
       if(options.funs!=null){
    	   for(var i=0;i<options.funs.length;i++){
    		   str+=",options.funs["+i+"]";  
    	   }
       }
       str+=")";
       eval('(' + str + ')'); 
	}
});



/**
 * 
 * 指定jquery对象中指定属性（默认是dname）设置默认值,如有以下form表单,
 * <form id="forma">
 *   <input type="text" dname ="account"/>
 * </form>
 * 且你有以下的数据对象  
 * data = {account:"张三"}
 * 
 * 使用方式为
 *   $.dloadformdata($("#forma"),data);  此方法会自动根据dname去找对应的值为上面的input框赋值
 * 
 */
$.extend({
	dloadformdata : function(obj,data,name) {
		var dname="name";
		if(name!=null){
			dname=name;
		}
		obj.find("["+dname+"]").each(function(){
			if($(this).attr("boxType")!= undefined){
				$(this).parent().find(".form-control")[$(this).attr("boxType")]("setValue",data[ $(this).attr(dname)])
			}
		    var key = $(this).attr(dname);
		    if(data[key]!=null){
		    	$(this).val(data[key]);
		    }else{
		    	$(this).val("");
		    }
		});
	}
});

/**
 * 指定jquery对象中设置了指定属性（默认是dname）的各项disabled设置为指定值
 */
$.extend({
	ddisable : function(obj,flag,name) {
		var dname="name";
		if(name!=null){
			dname=name;
		}
		obj.find("["+dname+"]").each(function(){
		    $(this).attr("disabled",flag);
		});
	}
});



/**
 * 自定义数字输入框
 * @param numberType (*必填。暂为身份证：idcard,电话：tel)
 */
$.fn.dInputNumberAdd = function(numberType,defaultValue){
	var inputHtml='<input type="text" class="dnumberInputText" defaultvalue=""><input type="button" class="dnumberInputClear" value="清空">';
	$(this).append(inputHtml);
	var inputText=$(this).children("input").eq(0);
	var inputClear=$(this).children("input").eq(1);
	inputText.attr("onpaste","return false");
	inputText.attr("size","22");
	inputText.attr("style","ime-mode: disabled; margin: 0; padding: 0; text-indent: 5px;");
	if(numberType=="idcard"){
		inputText.attr("placeholder","请输入18位身份证号");
	}else if(numberType=="tel"){
		inputText.attr("placeholder","请输入手机号或区号+固话号");
	}else if(numberType=="number"){
		inputText.attr("placeholder","请输入...");
	}
	var idNo,telNo;
	if(defaultValue!=undefined&&defaultValue!=null&&$.trim(defaultValue)!="null"&&$.trim(defaultValue)!="undefined"&&$.trim(defaultValue)!=""){
		var defaultVal=$.trim(defaultValue);
		inputValChange(defaultVal);
		inputValNormal();
	}
	inputText.on("focus",function(){
		inputText.val(inputText.val().replace(/[\s\-]/g,''));
	});
	inputText.on("input propertychange",function(){
		inputValChange(inputText.val());
	});
	inputText.on("blur",function(){
		inputValNormal();
	});
	inputClear.on("click",function(){
		inputText.val("");
		idNo="";
		telNo="";
		inputText.focus();
	});
	function inputValChange(inputTextVal){
		if(numberType=="idcard"){
			if(inputTextVal.length<=17){ 
				inputText.val(inputTextVal.replace(/[^0-9]/g,''));
			}else if(inputTextVal.length==18){
				inputText.val(inputTextVal.replace(/[^0-9xX]/g,''));
				inputText.val(inputTextVal.replace(/[x]/g,'X'));
			}else{
				inputText.val(inputTextVal.substring(0,18));
			}
			idNo=inputText.val().substring(0,6)+" "+inputText.val().substring(6,14)+" "+inputText.val().substring(14);
		}else if(numberType=="tel"){
			if(inputTextVal.substring(0,1)=="1"){
				telChoose(inputTextVal,11);
				telNo=inputText.val().substring(0,3)+" "+inputText.val().substring(3,7)+" "+inputText.val().substring(7);
			}else if(inputTextVal.substring(0,1)=="8"){
				telChoose(inputTextVal,11);
				telNo=inputText.val().substring(0,3)+" - "+inputText.val().substring(3,7)+" "+inputText.val().substring(7);
			}else if(inputTextVal.substring(0,1)=="0"){
				if(inputTextVal.substring(1,2)=="1"||inputTextVal.substring(1,2)=="2"){
					telChoose(inputTextVal,11);
					telNo=inputText.val().substring(0,3)+" - "+inputText.val().substring(3,7)+" "+inputText.val().substring(7);
				}else{
					telChoose(inputTextVal,12);
					telNo=inputText.val().substring(0,4)+" - "+inputText.val().substring(4,8)+" "+inputText.val().substring(8);
				}
			}
			else{
				inputText.val("");
			}	
		}else if(numberType=="number"){
			inputText.val(inputTextVal.replace(/[^0-9]/g,''));
		}
	}
	function telChoose(inputTextVal,telCount){
		if(inputTextVal.length>=(telCount+1)){
			inputText.val(inputTextVal.substring(0,telCount));
		}
		if(inputTextVal.length<=telCount){ 
			inputText.val(inputTextVal.replace(/[^0-9]/g,''));
		}else{
			inputText.val(inputTextVal.substring(0,telCount));
		}
	}
	function inputValNormal(){
		if(numberType=="idcard"&&$.trim(idNo)!=""){
			inputText.val(idNo);
		}else if(numberType=="tel"&&$.trim(telNo)!=""){
			inputText.val(telNo);
		}
	}
}
/**
 * 清除自定义数字输入框的值
 * @param isDefaultValue
 */
$.fn.dInputNumberValClear = function(isDefaultValue){
	if(isDefaultValue==false){
		$(this).children("input").eq(0).val("");
	}else if(isDefaultValue==true){
		$(this).children("input").eq(0).val($(this).children("input").eq(0).attr("defaultValue"));
	}
}

/**
 * 简易版table
 */
$.fn.simpleTable = function (data, options) {
    var $this = $(this);
    var settings = {
        fixedHead: true,
        thWidth: function(){
            $.each($this.find("th"), function(i){
                var bodytable = $this.width();
                var bodyth = $(this).outerWidth();
                tablehead.width(bodytable);
                $this.width(bodytable);
                $.each(tablehead.find("th").eq(i), function(){
                    $(this).outerWidth(bodyth);
                })
            })
        }
    };
    options = $.extend({}, settings, options);

    if (options.columns) {
        var columns = options.columns;
        var h = '<tr>';
        for (var i = 0; i < columns.length; i++) {
            var st = 'style="white-space:nowrap;';
            if (columns[i].width) {
                st += 'min-width:' + cols[i].width + 'px;'
            }
            if (columns[i].hidden == true) {
                st += 'display:none;'
            }
            st += '"';
            h += '<th ' + st + '>' + columns[i].title + '</th>';
        }
        h += '</tr>';
        $this.append(h);

        if (options.fixedHead) {
            $this.addClass("treetable-body").before("<table class='treetable-head'></table>");
            var tableclass = $this.attr("class");
            var tablehead = $this.prev("table");
            tablehead.addClass(tableclass).append(h);
        }
    }

    addTr(data, options);
    if(options.thWidth && options.thWidth!=null) options.thWidth();
    if(options.loaded && options.loaded!=null) options.loaded();

    if (options.fixedHead) {
        var left = parseInt(tablehead.css("left"));
        $(window).scroll(function(){
            var scrollLeft = $(this).scrollLeft();
            tablehead.animate({
                left: left - scrollLeft
            }, 0);
        })
    }

    if (options.localdata) {
        addTr(options.localdata, options);
    } else if (options.url) {
        AM.ajax({
            url: options.url,
            method: "get",
            dataType: "json",
            success: function (data) {
                addTr(data, options);
                if(options.thWidth && options.thWidth!=null) options.thWidth();
                if(options.loaded && options.loaded!=null) options.loaded(data);
            }
        });
    }

    function addTr(data, options){
        var columns = options.columns;
        for(var i=0;i<data.length;i++){
            var tr="<tr>";
            for(var j=0;j<columns.length;j++){
                var td="<td>";
                if(data[i][columns[j].field]==undefined){
                    data[i][columns[j].field]="";
                }
                if(columns[j].field=='NAME'){
                    td+="<pre>"+data[i][columns[j].field]+"</pre>"
                }else{
                    td+=data[i][columns[j].field];
                }
                td+="</td>";
                tr+=td;
            }
            tr+="</tr>";
            $this.append(tr);
        }
    }
};

$.fn.dtreetable = function (options) {
    //$.include([ 'bootstrap/plugins/treegrid/css/jquery.treegrid.css',
    //	'bootstrap/plugins/treegrid/js/jquery.treegrid.js',
    //	'bootstrap/plugins/treegrid/js/jquery.treegrid.bootstrap3.js'
    //]);
    $this = $(this);
    var settings = {
        fixedHead: true,
        thWidth: function(){
            $.each($this.find("th"), function(i){
                var bodytable = $this.width();
                var bodyth = $(this).outerWidth();
                tablehead.width(bodytable);
                $this.width(bodytable);
                $.each(tablehead.find("th").eq(i), function(){
                    $(this).outerWidth(bodyth);
                })
            })
        }
    };
    options = $.extend({}, settings, options);

    if (options.columns) {
        var columns = options.columns;
        var h = '<tr>';
        for (var i = 0; i < columns.length; i++) {

            var st = 'style="';
            if (columns[i].width) {
                st += 'width: ' + columns[i].width + 'px;'
            }
            if (columns[i].hidden == true) {
                st += 'display:none;'
            }
            st += '"';
            h += '<th ' + st + '>' + columns[i].title + '</th>';
        }
        h += '</tr>';
        $(this).append(h);

        if(options.fixedHead){
            $this.addClass("treetable-body").before("<table class='treetable-head'></table>");
            var tableclass = $this.attr("class");
            var tablehead = $this.prev("table");
            tablehead.addClass(tableclass).append(h);
        }
    }
    if (options.fixedHead) {
        var left = parseInt(tablehead.css("left"));
        $(window).scroll(function(){
            var scrollLeft = $(this).scrollLeft();
            tablehead.animate({
                left: left - scrollLeft
            }, 0);
        })
    }

    if (options.localdata) {
        addtr(options.localdata, options);
        if(options.thWidth && options.thWidth!=null) options.thWidth();
        if(options.loaded && options.loaded!=null) options.loaded();
    } else if (options.url) {
        $.dajax({
            url: options.url,
            data: options.data,
            beforeSend:function (req) {
                req.setRequestHeader("key",top.currkey);
                req.setRequestHeader("sessID",top.sessId);
            },
            success: function (data) {
                addtr(data, options);
                if(options.thWidth && options.thWidth!=null) options.thWidth();
                if(options.loaded && options.loaded!=null) options.loaded();
            }
        })
    }

	var treelocation=0;
	var loadStep=40;
	function lodaLocal(datas, options){
		var parentfield = options.parentfield,
			selfidfield = options.selfidfield,
			isLeafField = options.isLeaffield,
			levelField = options.levelfield;
		var start=parseInt(treelocation);
		for(var i=start;i<start+loadStep&&i<datas.length;i++){
			var data=datas[i];
			$tr = $("<tr/>");
			$tr.attr("treegrid", data[selfidfield]);
			if (data[parentfield] != -1) {
				$tr.attr("treegrid-parent", data[parentfield]);
			}
			$.each(options.columns, function (y, column) {
				$td = $('<td/>');
				if (column.hidden) {
					$td.css({"display": 'none'});
				}
				if (y == 0) {//节点缩进
					for (var enptySpan = 0; enptySpan < data[levelField]; enptySpan++) {
						$td.append("<span style='width: 16px;height:16px;display:inline-block;'>")
					}
				}

				if (column.isCount == true) {
					//var formatedNum=addzero(data[column.field]);
					var formatedNum=thousands(data[column.field]);

					if (data[isLeafField] == 1) {
						$td.append(' <span class="show_text td_value" >' + formatedNum + '</span><input type="text" class="show_input" style="display: none; margin: 0px;"/>');
					} else if(data[selfidfield]!=0){
						$td.append('<span style="font-weight: bold;color:#0000ff" class="total td_value">' + formatedNum+ '</span>')
					}
				} else {
					$td.append("<span class='td_value'>" + data[column.field] + "</span>");
				}
				$tr.append($td)
			});
			$this.append($tr);
			treelocation++;
		}

	}
    function addtr(datas, options) {
        // //console.log(1);
        // var parentfield = options.parentfield,
        //     selfidfield = options.selfidfield,
        //     isLeafField = options.isLeaffield,
        //     levelField = options.levelfield;
		var ulHeight = $this.height();
		var treeHeight=$(this).height();
		while(ulHeight < treeHeight&&datas.length>treelocation){
			lodaLocal(datas,options);
			var ulHeight = $this.height();

		}
		$(document).scroll(function(){
			var scrollTop = $("body").scrollTop();
			var scrollHeight =  $("body").get(0).scrollHeight;
			var height =  $("body").height();
			// console.log(scrollTop);
			// console.log(scrollHeight);
			// console.log(height);

			if(scrollHeight-scrollTop-height<scrollHeight*0.9){
				lodaLocal(datas,options);
			}

		});
        // $.each(datas, function (i, data) {
        //     $tr = $("<tr/>");
        //     $tr.attr("treegrid", data[selfidfield]);
        //     if (data[parentfield] != -1) {
        //         $tr.attr("treegrid-parent", data[parentfield]);
        //     }
        //     $.each(options.columns, function (y, column) {
        //         $td = $('<td/>');
        //         if (column.hidden) {
        //             $td.css({"display": 'none'});
        //         }
        //         if (y == 0) {//节点缩进
        //             for (var enptySpan = 0; enptySpan < data[levelField]; enptySpan++) {
        //                 $td.append("<span style='width: 16px;height:16px;display:inline-block;'>")
        //             }
        //         }
        //
        //         if (column.isCount == true) {
        //             //var formatedNum=addzero(data[column.field]);
			// 		console.log(data[column.field]);
			// 		var formatedNum=thousands(data[column.field]);
        //
			// 		if (data[isLeafField] == 1) {
        //                 $td.append(' <span class="show_text td_value" >' + formatedNum + '</span><input type="text" class="show_input" style="display: none; margin: 0px;"/>');
        //             } else if(data[selfidfield]!=0){
        //                 $td.append('<span style="font-weight: bold;color:#0000ff" class="total td_value">' + formatedNum+ '</span>')
        //             }
        //         } else {
        //             $td.append("<span class='td_value'>" + data[column.field] + "</span>");
        //         }
        //         $tr.append($td)
        //     });
        //     $this.append($tr);
        // });
        //金额显示元素点击事件
        $(".show_text").parent().off('click');
		$this.on('click',"td:has('.show_text')", function () {
            var moneybox = $(this).children(".show_text");
            var show_input = moneybox.next(".show_input");
            show_input.val(moneybox.text());
            moneybox.hide();
            show_input.show().select();
            show_input.focus();
        });
        //金额编辑栏失焦事件
        $(".show_input").off('blur');
		$this.on('blur',".show_input", function () {
            var show_input = $(this);
            var moneybox = show_input.prev(".show_text");
            moneybox.text(addzero(show_input.val()));

            moneybox.show();
            show_input.hide();
            count(show_input.parent().parent());
            if(options.onblur&&options.onblur!=null){
                options.onblur(show_input);
            }
        });
        //金额编辑 按键事件
        $(".show_input").off('keypress');
		$this.on('keypress',".show_input", function (e) {
            if (e.keyCode == 13 || e.keyCode == 9) {
                //判断是否有下一行 有则显示input 并为焦点
                var nextrow = $(this).parent().parent().nextAll("tr").find(".show_input").eq(0).parent().parent();
                console.log(nextrow);
                if (nextrow.is("tr")) {
                    nextrow.find(".show_input").val(nextrow.find(".show_text").text());
                    nextrow.find(".show_input").show().select();
                    nextrow.find(".show_text").hide();
                } else {
                    $(this).blur();
                }
            }
        });
    }

    function count(node) {
        var pid = node.attr("treegrid-parent");
        selectorP = "[treegrid='" + pid + "']";
        var parentnode = $this.find(selectorP);
        selector = "[treegrid-parent='" + pid + "']";
        var nodes = $this.find(selector);


        if (nodes.length == 0) {
            return;
        }

        var t = 0;
        for (var i = 0; i < nodes.length; i++) {
            if ($(nodes[i]).find(".show_text").length != 0) {
                t += Number($(nodes[i]).find(".show_text").html())
            } else {
                t += Number($(nodes[i]).find(".total").html())
            }
        }
        $(parentnode).find(".total").html(addzero(t));
        count(parentnode)
    }

    /**
     * 金额补零
     */
    function addzero(value) {
        var value = Math.round(parseFloat(value) * 100) / 100;
        var xsd = value.toString().split(".");
        if (xsd.length == 1) {
            value = value.toString() + ".00";
            return value;
        }
        if (xsd.length > 1) {
            if (xsd[1].length < 2) {
                value = value.toString() + "0";
            }
            return value;
        }
    }
};


$.extend({
	dopen : function(options) {
	   $.include([ 'bootstrap/plugins/layer/layer.js']);
	   $.include([ 'bootstrap/plugins/layer/skin/layer.css']);
	   
	   
	   var defaultsetting = {
			   type: 1,
			   maxmin: true,
			   title: true,
			   shadeClose: true,
			   closeBtn: 1,
			   shadeClose: false, //点击遮罩关闭
			   parent:false,//是否显示在父级。
			   fix: false //不固定位置
	   }
	   
	   options = $.extend({}, defaultsetting, options);
	   if(options.parent&&self.location!=top.location){
		   parent.$.dopen(options);
	   }else{
		   layer.alert(options.text,options);
	   }
	   
	   
   }
});

$.fn.dmodal=function(options){
//	var obj = $(this);
	var setting={
		title: '标题',
		content: '<p>内容</p>',
		url:'',
		width:"768px",
		height:"300px",
		showCloseButton: true,
		father:false,
		otherButtons: [],
		otherButtonStyles: [],
		bootstrapModalOption: {
			backdrop:true,//指定一个静态的背景，当用户点击模态框外部时不会关闭模态框。
	    	keyboard:true,//当按下 escape 键时关闭模态框
			show:true,//当初始化时显示模态框
			remote:false
		},//modal默认的选项
		dialogShow: function() {},//在调用 show 方法后触发
		dialogShown: function() {},//当模态框对用户可见时触发（将等待 CSS 过渡效果完成）
		dialogHide: function() {},//当调用 hide 实例方法时触发
		dialogHidden: function() {},//当模态框完全对用户隐藏时触发
		clickButton: function(sender, index) {}
	};
	options= $.extend({},setting,options);
	
	var modalID = '';
	//生成一个惟一的ID
	function random(a, b) {
		return Math.random() > 0.5 ? -1 : 1;
	}
	function getModalID() {
		return "dmodal-" + ['1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'Q', 'q', 'W', 'w', 'E', 'e', 'R', 'r', 'T', 't', 'Y', 'y', 'U', 'u', 'I', 'i', 'O', 'o', 'P', 'p', 'A', 'a', 'S', 's', 'D', 'd', 'F', 'f', 'G', 'g', 'H', 'h', 'J', 'j', 'K', 'k', 'L', 'l', 'Z', 'z', 'X', 'x', 'C', 'c', 'V', 'v', 'B', 'b', 'N', 'n', 'M', 'm'].sort(random).join('').substring(5, 20);
	}
	
	var includePath = dn_methods["getRootPath"]();
	//判断弹出层是否要遮住父层
	var fatherBody = $(window.document.body);
	if (options.father){
		fatherBody = $(window.top.document.body);
		//obj.appendTo(fatherBody);
	}
	modalID = getModalID();
	var tmpHtml = '<div class="modal" id="{ID}" tabindex="-1" role="dialog" aria-hidden="true" style="display: block;"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button><h6 class="modal-title">{title}</h6></div><div class="modal-body">{body}</div><div class="modal-footer">{button}</div></div></div></div>';
	var buttonHtml = '<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>';
	if (!options.showCloseButton && options.otherButtons.length > 0) {
		buttonHtml = '';
	}
	//生成按钮
	var btnClass = 'cls-' + modalID;
	for (var i = 0; i < options.otherButtons.length; i++) {
		buttonHtml += '<button buttonIndex="' + i + '" class="' + btnClass + ' btn ' + options.otherButtonStyles[i] + '">' + options.otherButtons[i] + '</button>';
	}
	//替换模板标记
	tmpHtml = tmpHtml.replace(/{ID}/g, modalID).replace(/{title}/g, options.title).replace(/{body}/g, options.content).replace(/{button}/g, buttonHtml);
	fatherBody.append(tmpHtml);
	var modalObj = $('#' + modalID,fatherBody);
	//绑定按钮事件,不包括关闭按钮
	$('.' + btnClass,fatherBody).click(function() {
		var index = $(this).attr('buttonIndex');
		options.clickButton($(this), index);
	});
	//跳转指定url
	if (options.url != ''){
		//modalObj.load(options.url);
		$(".modal-body",fatherBody).html("");
		$(".modal-body",fatherBody).append("<iframe style='border: 0;width:100%;height:100%;' src="+(includePath+options.url)+" ></iframe>");
		$(".modal-body",fatherBody).css("overflow","visible");
	}
	$(".modal-dialog",fatherBody).width(options.width);
	$(".modal-body",fatherBody).height(options.height);
	//绑定本身的事件
	modalObj.on('show.bs.modal', function() {
		options.dialogShow();
	});
	modalObj.on('shown.bs.modal', function() {
		if (options.father){
			$(window.document.body).find(".modal-backdrop").remove();
			fatherBody.append("<div class='modal-backdrop fade in'></div>");
		}
		options.dialogShown();
	});
	modalObj.on('hide.bs.modal', function() {
		options.dialogHide();
	});
	modalObj.on('hidden.bs.modal', function() {
		options.dialogHidden();
		modalObj.remove();
		if (options.father){
			fatherBody.find(".modal-backdrop").remove();
		}
	});
	modalObj.modal(options.bootstrapModalOption);
	
	//封装方法
	$.fn.extend({
		toggleDialog: function() {
			modalObj.modal('toggle');
		},
		showDialog: function(){
			modalObj.modal('show');
		},
		closeDialog: function() {
			modalObj.modal('hide');
		}
	});
	
	return modalObj;
};



/**
 * 树的模糊搜索功能
 * 
 */
$.fn.dsearchtree=function(options){
	$.include(['bootstrap/css/font-awesome.min.css',
	           'bootstrap/plugins/tree/css/zTreeStyle/zTreeStyle.css',
	           //'bootstrap/plugins/tree/css/ztree.css',
	           'bootstrap/plugins/tree/js/jquery.ztree.all.min.js',
	           'bootstrap/plugins/tree/js/jquery.ztree.exhide.min.js']);
	var target = $(this);
	var tree;
	var targetId; //目标对象的唯一标识
	if(target.attr("id")){
		targetId = target.attr("id");
	}else{
		targetId = "id" + (new Date().getTime());
		target.attr("id", targetId);
	}
	var settings = {
		modalTitle: '查找名称', //模态框标题
		checkType: 'nocheck', //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
		ISLEAF: false, //checkType为checkbox或radio时，非叶子节点是否有选择框，false为不可选
		check: {},
		data: {
			simpleData: {
				enable: true,
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
		},
		view: {
			fontCss: getFontCss
		},
	    zindex:"999999",
		callback: {},
		onAckCallback:function(){return false}//点击模态框  确认后 触发事件
	}
	options = $.extend({}, settings, options);

	var html = '<div class="modal fade" id="'+ targetId+"_modal" +'" tabindex="-1" role="dialog" aria-labelledby="'+ targetId+"_label" +'" aria-hidden="true">'
			+'<div class="modal-dialog">'
			+'<div class="modal-content">'
			+'<div class="modal-header">'
			+'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
			+'<h4 class="modal-title" id="' + targetId+"_label" + '">'+ options.modalTitle +'</h4>'
			+'</div>'
			+'<div class="modal-body">'
			+'<input type="text" id="'+ targetId+"_mdInput" +'" class="form-control" placeholder="请输入关键字" value="" />'
			+'<div class="treeWrap"><ul id="'+ targetId+"_mdTree" +'" class="ztree searchTree"></ul></div>'
			+'</div>'
			+'<div class="modal-footer">'
			+'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>'
			+'<button type="button" class="btn btn-primary" id="'+ targetId+"_modalok" +'">确定</button>'
			+'</div></div></div></div>';
	$("body").append(html);
	
	//选择框类型
	if(options.checkType == "radio"){
		options.check.enable = "true";
		options.check.chkStyle = "radio";
		options.check.radioType = "all";
		options.callback.onClick = onClick;
		options.callback.onCheck = onCheck;
	}else if(options.checkType == "checkbox"){
		options.check.enable = "true";
		options.callback.beforeClick = beforeClick;
		options.callback.onCheck = onCheck;
	}
	//console.log(JSON.stringify(options))

	//输出模态框内的树
	var treeobj = $("#"+targetId+"_mdTree");
	if(options.localdata!=null){
    	tree = $.fn.zTree.init(treeobj, options, options.localdata);
    }else if(options.url!=null){
    	$.dajax({
            url: options.url,
            async:false,
            success: function(data1) {
            	tree = $.fn.zTree.init(treeobj, options, data1);
            }
        });
    }else{
    	tree = $.fn.zTree.init(treeobj,options);
    }
	
	//target外部嵌套
	target.wrap('<div class="ztreeBox_down"></div>');
	
	//在输入框之后插入隐藏域
	target.before('<input type="text" style="display:none;" id="'+ targetId+"_hideInp" +'"name="'+ target.attr("name") +'" value="" txtvalue="" />');
	target.removeAttr("name");

	//html插入小图标
	target.after('<i class="fa fa-angle-down"></i>');
	target.css({backgroundColor:"#FFF"});
	
	target.parent().attr({"data-target":"#"+targetId+"_modal", "data-toggle":"modal"});
	target.attr({readonly:"true"});
	target.addClass("empty form-control");

    //树ul设置最大高度
    var treewrap = $("#"+targetId+"_modal").find(".treeWrap");
    treewrap.css({maxHeight: $(window).height()/2, overflowY:"scroll"});

	//父节点是否有选择框
	if(!options.ISLEAF && options.checkType != "nocheck"){
		var node = tree.getNodes();
		var nodes = tree.transformToArray(node);
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].ISLEAF == 1){
				nodes[i].nocheck=false;
				tree.updateNode(nodes[i]);
			}else{
				nodes[i].nocheck=true;
				tree.updateNode(nodes[i]);
			}
		}
	}
	
	//模糊搜索
	var key, lastValue = "", nodeList = [], fontCss = {}, hideInp, checkedInpval, checkedInptxtval;
	hideInp = $("#"+targetId+"_hideInp");
	key = $("#"+targetId+"_mdInput"); //搜索输入框
	key.bind("focus", focusKey)
		.bind("blur", blurKey)
		.bind("propertychange", searchNode)
		.bind("input", searchNode);
	
	$("#"+targetId+"_modal").on("shown.bs.modal", function () {
		$("#"+targetId+"_modal").css("z-index",options.zindex);
		if(options.cancelAll){
			var zTree = $.fn.zTree.getZTreeObj(targetId+"_mdTree");
			zTree.checkAllNodes(false);
		}
	});
    //页面出现多个模态框时，避免[关闭其中一个时，页面垂直滚动条消失，导致另一个模态框无法滚动]
    $("#"+targetId+"_modal").on("hidden.bs.modal", function () {
        if($(".modal.fade.in").length > 0){
            $(".modal.fade.in").css({overflowY:"auto"});
        }
    });
	//点击确定后模态框隐藏，页面输入框赋值
	$("#"+targetId+"_modalok").on("click", function(){
		target.val(checkedInptxtval);
		target.attr("txtvalue",checkedInpval);
		hideInp.val(checkedInpval);
		$("#"+targetId+"_modal").modal('hide');
		target.change();
		target.data('noed',tree.getCheckedNodes());
		options.onAckCallback();
	})
	
	function focusKey(e) {
		if (key.hasClass("empty")) {
			key.removeClass("empty");
		}
	}
	function blurKey(e) {
		if (key.get(0).value === "") {
			key.addClass("empty");
		}
	}
	function searchNode(e) {
		var zTree = $.fn.zTree.getZTreeObj(targetId+"_mdTree");
		var value = $.trim(key.get(0).value);
		var keyType = options.data.key.name;
	   
		if (key.hasClass("empty")) {
			value = "";
		}
		if (lastValue === value) return;
		lastValue = value;
		if (value === "") {
			zTree.showNodes(zTree.transformToArray(zTree.getNodes())) ;
			return;
		}
		updateNodes(false);
		nodeList = zTree.getNodesByParamFuzzy(keyType, value);
		/**不查询父级
		for(var x = 0 ; x<nodeList.length ; x++){
			if(nodeList[x].isParent){
				nodeList.splice(x--,1);
			}
		}*/
		updateNodes(true);
	}
	function updateNodes(highlight) {
		var zTree = $.fn.zTree.getZTreeObj(targetId+"_mdTree");
		var allNode = zTree.transformToArray(zTree.getNodes());
		zTree.hideNodes(allNode);
		for(var n in nodeList){
			findParent(zTree,nodeList[n]);
			nodeList[n].highlight = highlight;
			zTree.updateNode(nodeList[n]);
		}
		zTree.showNodes(nodeList);
	}

	function findParent(zTree,node){
		zTree.expandNode(node,true,false,false);
		var pNode = node.getParentNode();
		if(pNode != null){
			nodeList.push(pNode);
			findParent(zTree,pNode);
		}
		
	}
	function getFontCss(treeId, treeNode) {
		return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"inherit", "font-weight":"inherit"};
	}
	//有选择框
	function beforeClick(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		
		return false;
	}
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId),
		nodes = zTree.getCheckedNodes(true),
		v = "", dv = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i][options.data.key.name] + ",";
			dv += nodes[i][options.data.simpleData.idKey] + ",";
		}
		if (v.length > 0 ){
			v = v.substring(0, v.length-1);
			dv = dv.substring(0, dv.length-1);
		}
		//hideInp.attr("value", dv);
		//hideInp.attr("txtvalue", v)
		checkedInpval = dv;
		checkedInptxtval = v;
	}
};


/**
 * 树节点的拖拽
 * 
 */
$.fn.ddragtree=function(options){
	$.include(['bootstrap/plugins/tree/css/zTreeStyle/zTreeStyle.css',
	           'bootstrap/plugins/tree/js/jquery.ztree.all.min.js']);
	var treeobj = $(this);
	var tree;
	var dragtreeId; //目标对象的唯一标识
	treeobj.addClass("ztree");
	if(treeobj.attr("id")){
		dragtreeId = treeobj.attr("id");
	}else{
		dragtreeId = "id" + (new Date().getTime());
		treeobj.attr("id", dragtreeId);
	}
	var settings = {
		checkType: 'nocheck', //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
		ISLEAF: false, //checkType为checkbox或radio时，非叶子节点是否有选择框，false为不可选
		check: {},
		edit: {
			drag: {
				autoExpandTrigger: true, //拖拽时父节点自动展开是否触发 onExpand 事件回调函数，true为触发
				prev: dropPrev, //拖拽到目标节点时，设置是否允许移动到目标节点前面的操作，true为允许
				inner: dropInner, //拖拽到目标节点时，设置是否允许成为目标节点的子节点，true为允许
				next: dropNext //拖拽到目标节点时，设置是否允许移动到目标节点后面的操作，true为允许
			},
			enable: true, //设置 zTree是否处于编辑状态，true为可以
			showRemoveBtn: false, //设置是否显示删除按钮
			showRenameBtn: false //设置是否显示编辑名称按钮
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		view: {
			dblClickExpand: false
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeDrop: beforeDrop,
			beforeDragOpen: beforeDragOpen,
			onDrag: onDrag,
			onDrop: onDrop,
			onExpand: onExpand,
			beforeClick: beforeClick
		}
	}
	options = $.extend({}, settings, options);
	
	//选择框类型
	if(options.checkType == "radio"){
		options.check.enable = "true";
		options.check.chkStyle = "radio";
		options.check.radioType = "all";
	}else if(options.checkType == "checkbox"){
		options.check.enable = "true";
	}

	//输出的树
	if(options.localdata!=null){
    	tree = $.fn.zTree.init(treeobj, options, options.localdata);
    }else if(options.url!=null){
    	$.dajax({
            url: options.url,
            async:false,
            success: function(data1) {
            	tree = $.fn.zTree.init(treeobj, options, data1);
            }
        });
    }else{
    	tree = $.fn.zTree.init(treeobj,options);
    }

	//父节点是否有选择框
	if(!options.ISLEAF && options.checkType != "nocheck"){
		var node = tree.getNodes();
		var nodes = tree.transformToArray(node);
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].ISLEAF == 1){
				nodes[i].nocheck=false;
				tree.updateNode(nodes[i]);
			}else{
				nodes[i].nocheck=true;
				tree.updateNode(nodes[i]);
			}
		}
	}
	
	//拖拽节点
	function dropPrev(treeId, nodes, targetNode) {
		var pNode = targetNode.getParentNode();
		if (pNode && pNode.dropInner === false) {
			return false;
		} else {
			for (var i=0,l=curDragNodes.length; i<l; i++) {
				var curPNode = curDragNodes[i].getParentNode();
				if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
					return false;
				}
			}
		}
		return true;
	}
	function dropInner(treeId, nodes, targetNode) {
		if (targetNode && targetNode.dropInner === false) {
			return false;
		} else {
			for (var i=0,l=curDragNodes.length; i<l; i++) {
				if (!targetNode && curDragNodes[i].dropRoot === false) {
					return false;
				} else if (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== targetNode && curDragNodes[i].getParentNode().childOuter === false) {
					return false;
				}
			}
		}
		return true;
	}
	function dropNext(treeId, nodes, targetNode) {
		var pNode = targetNode.getParentNode();
		if (pNode && pNode.dropInner === false) {
			return false;
		} else {
			for (var i=0,l=curDragNodes.length; i<l; i++) {
				var curPNode = curDragNodes[i].getParentNode();
				if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
					return false;
				}
			}
		}
		return true;
	}

	var className = "dark", curDragNodes, autoExpandNode;
	function beforeDrag(treeId, treeNodes) {
		className = (className === "dark" ? "":"dark");
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {
				curDragNodes = null;
				return false;
			} else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
				curDragNodes = null;
				return false;
			}
		}
		curDragNodes = treeNodes;
		return true;
	}
	function beforeDragOpen(treeId, treeNode) {
		autoExpandNode = treeNode;
		return true;
	}
	function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
		className = (className === "dark" ? "":"dark");
		return true;
	}
	function onDrag(event, treeId, treeNodes) {
		className = (className === "dark" ? "":"dark");
	}
	function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
		className = (className === "dark" ? "":"dark");
	}
	function onExpand(event, treeId, treeNode) {
		if (treeNode === autoExpandNode) {
			className = (className === "dark" ? "":"dark");
		}
	}
	
	//有选择框
	function beforeClick(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	
};


/**
 * 树节点编辑
 * 
 */
$.fn.dedittree=function(options){
	$.include(['bootstrap/plugins/tree/css/zTreeStyle/zTreeStyle.css',
	           'bootstrap/plugins/tree/js/jquery.ztree.all.min.js']);
	var treeobj = $(this);
	var tree, newtree;
	var edittreeId; //对象唯一标识
	if(treeobj.attr("id")){
		edittreeId = treeobj.attr("id");
	}else{
		edittreeId = "id" + (new Date().getTime());
		treeobj.attr("id", edittreeId);
	}
	var settings = {
		checkType: 'nocheck', //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
		ISLEAF: false, //checkType为checkbox或radio时，非叶子节点是否有选择框，false为不可选
		check: {},
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
		},
		edit: {
			enable: true,
			editNameSelectAll: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeEditName: beforeEditName,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onRemove: onRemove,
			onRename: onRename,
			beforeClick: beforeClick
		}
	}
	options = $.extend({}, settings, options);
	
	//选择框类型
	if(options.checkType == "radio"){
		options.check.enable = "true";
		options.check.chkStyle = "radio";
		options.check.radioType = "all";
	}else if(options.checkType == "checkbox"){
		options.check.enable = "true";
	}

	//输出树
	if(options.localdata!=null){
    	tree = $.fn.zTree.init(treeobj, options, options.localdata);
    }else if(options.url!=null){
    	$.dajax({
            url: options.url,
            async:false,
            success: function(data1) {
            	tree = $.fn.zTree.init(treeobj, options, data1);
            }
        });
    }else{
    	tree = $.fn.zTree.init(treeobj,options);
    }
	
	newtree = $.fn.zTree.getZTreeObj(edittreeId).getNodes();

	//父节点是否有选择框
	if(!options.ISLEAF && options.checkType != "nocheck"){
		var node = tree.getNodes();
		var nodes = tree.transformToArray(node);
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].ISLEAF == 1){
				nodes[i].nocheck=false;
				tree.updateNode(nodes[i]);
			}else{
				nodes[i].nocheck=true;
				tree.updateNode(nodes[i]);
			}
		}
	}
	
	//编辑节点
	var className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	function beforeEditName(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		
		var zTree = $.fn.zTree.getZTreeObj(edittreeId);
		zTree.selectNode(treeNode);
	}
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		
		var zTree = $.fn.zTree.getZTreeObj(edittreeId);
		zTree.selectNode(treeNode);
		return confirm("确定删除节点 " + treeNode.name + " 吗？");
	}
	function beforeRename(treeId, treeNode, newName, isCancel) {
		className = (className === "dark" ? "":"dark");
		
		if (newName.length == 0) {
			alert("节点名称不能为空");
			var zTree = $.fn.zTree.getZTreeObj(edittreeId);
			setTimeout(function(){zTree.editName(treeNode)}, 10);
			return false;
		}
		return true;
	}
	function onRemove(e, treeId, treeNode){
		var zTree = $.fn.zTree.getZTreeObj(edittreeId);
		newtree = zTree.getNodes();
	}
	function onRename(e, treeId, treeNode, isCancel){
		var zTree = $.fn.zTree.getZTreeObj(edittreeId);
		newtree = zTree.getNodes();
	}
	function showRemoveBtn(treeId, treeNode) {
		return !treeNode.isFirstNode;
	}
	function showRenameBtn(treeId, treeNode) {
		return !treeNode.isLastNode;
	}

	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			var zTree = $.fn.zTree.getZTreeObj(edittreeId);
			zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
			return false;
		});
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	
	//有选择框
	function beforeClick(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(edittreeId);
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	
	return newtree;
};

	///**
	// * 右键
	// * @param options
	// */
	//$.fn.dcontextmenu=function(options){
	//	var obj = $(this);
	//	var setting={
	//		bootstrapContextOption:{
	//			target: '#context-menu', //默认，不允许修改，不向外部提供
	//			before: function(context,e) {},
	//			onItem: function(context,e) {}
	//		},
	//		menuList:["插入","删除"],
	//		contextShow: function() {},//在调用 show 方法后触发
	//		contextShown: function() {},//当模态框对用户可见时触发（将等待 CSS 过渡效果完成）
	//		contextHide: function() {},//当调用 hide 实例方法时触发
	//		contextHidden: function() {}//当模态框完全对用户隐藏时触发
	//	};
	//	$.include(['bootstrap/plugins/contextmenu/js/bootstrap-contextmenu.js']);
	//	options= $.extend({},setting,options);
    //
	//	var fatherBody = $(window.document.body);
	//	var tmpHtml = '<div id="context-menu"><ul role="menu" class="dropdown-menu">{MENU}</ul></div>';
	//	var menuHtml = '';
	//	if (options.menuList.length <= 0) {
	//		menuHtml = '';
	//	}
	//	for (var i = 0; i < options.menuList.length; i++) {
	//		if (options.menuList[i]=="divider"){
	//			menuHtml += '<li class="'+options.menuList[i]+'"></li>';
	//		}else{
	//			menuHtml += '<li><a tabindex="-1">{NAME}</a></li>';
	//			menuHtml = menuHtml.replace(/{NAME}/g, options.menuList[i]);
	//		}
	//	}
	//	tmpHtml = tmpHtml.replace(/{MENU}/g, menuHtml);
	//	fatherBody.append(tmpHtml);
    //
	//	obj.contextmenu(options.bootstrapContextOption);
    //
	//};

	 $.include([ 'bootstrap/js/config.js|likeafter|js/jquery.js',
	             'bootstrap/js/bootstrap.min.js|likeafter|js/jquery.js',
	             'bootstrap/css/bootstrap.min.css|likebefore|js/bootstrap.datanew.js',
	             'bootstrap/css/bootstrap.dataenw.css|likeafter|css/bootstrap.min.css']);

    //ie6-8, 则加载js, 兼容
    if(navigator.userAgent.indexOf("MSIE")>0 && (navigator.userAgent.indexOf("MSIE 6.0")>0 || navigator.userAgent.indexOf("MSIE 7.0")>0 || navigator.userAgent.indexOf("MSIE 8.0")>0)){
        $.include(['bootstrap/js/respond.min.js','bootstrap/js/html5shiv.min.js']);
    }

	 $.dinit();

})(jQuery);


/**
 *  自定义select 数据加载
 * @param option
 * 			{
 * 				url:'',//远程数据请求地址
 * 				valueField:'' //作为select value的字段   默认‘id’
 * 				textField:'' //作为select 显示字段   默认 ‘text’
 * 				localdata:[] //本地数据加载 json
 * 				searchOption:true/false //是否作为查询条件用  true 会额外添加 ‘全部’option
 * 			}  method:"getValue"\"setValue"
 */
(function($){
	/**
	 * Dselect 构造函数
	 * @param el  dom元素 jquery对象
	 * @param options 参数
	 * @constructor
	 */
	var Dselect=function(el,options){
		this.options=options;
		this.$el = $(el);
		this.selectData=this.options.localdata;
		this.init();
	};
	/**
	 * select 默认初始化参数
	 * @type {{textField: string, valueField: string, url: string, localdata: {}, onChange: Function}}
	 */
	Dselect.defaults={
		//searchOption:false,
		textField:'text',
		valueField:'id',
		url:'',
		localdata:{},
		onChange:function(data){
			return false
		}
	};

	Dselect.prototype.appendOption=function (selectData,value,text){
		this.$el.find("option").remove();
		
		if(this.options.searchOption==true){
			this.$el.append("<option value=''>全部</option>");
		}else if(this.options.searchOption==false){
			this.$el.append("<option value=''></option>");
		}
		for(var i=0;i<selectData.length;i++){
			this.$el.append("<option value='"+selectData[i][value]+"'>"+selectData[i][text]+"</option>");
		}
		//初始化 oldValue 值
		$(this).data("oldValue",this.$el.val());
	};
	/**
	 * 初始化方法
	 */
	Dselect.prototype.init=function(){
		var value,text,url,that;
		that=this;
		value=this.options.valueField ;
		text=this.options.textField ;
		url=this.options.url;
		if(url != '' && url != null){
			$.dajax({
				url:url,
				async: false,
				success:function(data){
					if(data.length==0){
						return;
					}
					that.selectData=data;
					//this.appendOption(this.selectData,value,text);
				}
			})
		}
		this.appendOption(this.selectData,value,text);
		//onChange事件
		this.$el.off('change').on('change',function(){
			var index=that.$el.find('option:selected').index();
			//事件返回方法 以及参数
			that.options['onChange'].apply(that.options, [that.selectData[index],$(that).data("oldValue")]);{
			}
			//重设oldValue
			$(that).data("oldValue",that.$el.val())
		});
	};

	Dselect.prototype.getValue=function(){
		return this.$el.val();
	};
	Dselect.prototype.setValue=function(value){
		this.$el.val(value);
	};
	Dselect.prototype.disable= function () {
		this.$el.attr("disabled","disabled");
	};
	Dselect.prototype.enable= function () {
		this.$el.removeAttr("disabled");
	};
	Dselect.prototype.loadData=function(data){
		this.selectData=data;
		this.$el.children().remove();
		this.appendOption(data,this.options.valueField,this.options.textField)
	};

	$.fn.dselect=function(options){//调用入口
		var $this=$(this);
		if(typeof options=='object'){//参数为对象时 构建一个Dselect对象 参数为 当前fn和初始化参数
			options= $.extend({},Dselect.defaults,options);
			$this.data("Dselect",new Dselect($this,options));//将构建的Dselect对象存入dom
			return;
		}
		if(typeof options=='string'){//参数为字符串 调用相应的方法
			var value,
				param=Array.prototype.slice.call(arguments, 1),//将传入参数转为数组类型
				obj=$(this).data("Dselect");//从dom中取出Dselect对象
			value = obj[options].apply(obj, param);//调用相应方法
			return typeof value === 'undefined' ? this : value;
		}
	};
})(jQuery);

