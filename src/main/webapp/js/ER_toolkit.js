/** 
* ExcelReport v6.4.1 (http://www.taoreport.com) 
* Copyright(c) 2010~2017 NetSun Datanew, Inc.
* Licensed under Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0) 
*/

/*
* 用于标识是否已经加载过了报表模态窗口所需要用到的jquery库和jqueryEasyui库,如果业务中的页面已经包含类似文件了，则可把对应项设置为true
* 例如,客户的页面也使用了jquery.js文件，那么就无需再加载了，请在客户页面加载完成事件中调用 "ER_load_jquery_js();",这样可以禁止报表页面向客户页面动态加载jquery.js文件。
* 同理，可应用到jqueryEasyui
*/
var er_loaded_jquery_js = false;
var er_loaded_layer_files = false;
var er_loaded_tinybox2_files = false;
var er_loaded_dialog_files = false;

/**
* 标识已经加载过jquery的js文件了
*/
function ER_load_jquery_js() {
	er_loaded_jquery_js = true;
}

/**
* 标识已经加载过layer相关的css/js文件了
*/
function ER_load_layer_files() {
	er_loaded_layer_files = true;
}

/**
* 标识已经加载过tinybox2的js文件了
*/
function ER_load_tinybox2_files() {
	er_loaded_tinybox2_files = true;
}

/**
* 标识已经加载过dialog相关的js文件了
*/
function ER_load_dialog_files() {
	er_loaded_dialog_files = true;
}

/**
 * 返回是否已经加载了jquery相关的js
 * @returns {Boolean}
 */
function isER_load_jquery_js() {
	return er_loaded_jquery_js;
}

/**
 * 返回是否已经加载了jquery easyui相关的文件
 * @returns {Boolean}
 */
function isER_load_layer_files() {
	return er_loaded_layer_files;
}

/**
 * 返回是否已经加载了tinybox2相关的文件
 * @returns {Boolean}
 */
function isER_load_tinybox2_files() {
	return er_loaded_tinybox2_files;
}

/**
 * 返回是否已经加载了弹出窗口相关的文件
 * @returns {Boolean}
 */
function isER_load_dialog_files() {
	return er_loaded_dialog_files;
}


/**
 * 获取document高度
 */
function ER_getTopWindowClientHeight(screen) {
	if (true == screen) {
		return window.top.screen.availHeight;
	} else {
		var de = window.top.document.documentElement;
		return window.top.self.innerHeight || (de && de.clientHeight)
				|| window.top.document.body.clientHeight;
	}
}

/**
 * 获取document宽度
 */
function ER_getTopWindowClientWidth(screen) {
	if (true == screen) {
		return window.top.screen.availWidth;
	} else {
		var de = window.top.document.documentElement;
		return window.top.self.innerWidth || (de && de.clientWidth)
				|| window.top.document.body.clientWidth;
	}
}

/**
* js和css文件动态加载类
*/
var ER_dynamicLoading = {
    css: function(doc,path){
    	if(!path || path.length === 0){
			throw new Error('argument "path" is required !');
		}
			
		if (doc) {
	    	var head = doc.getElementsByTagName('head')[0];
			if (head) {
		        var link = doc.createElement('link');
		        if (link) {
			        link.href = path;
			        link.rel = 'stylesheet';
			        link.type = 'text/css';
			        head.appendChild(link);
		        }
	        }
        }
    },

    js: function(doc,path){
		if(!path || path.length === 0){
			throw new Error('argument "path" is required !');
		}
		
		if (doc) {
			var head = doc.getElementsByTagName('head')[0];
			if (head) {
		        var script = doc.createElement('script');
		        if (script) {
			        script.src = path;
			        script.type = 'text/javascript';
			        head.appendChild(script);
		        }
	        }
        }
    },
 
	js1 : function (mywindow, sId, url) {
		if  (undefined == window || null == mywindow) {
			alert( 'window is invaild' ) ; 
	    	return;
		}
		
	    //var oXmlHttp = this.getHttpRequest(mywindow) ;
		var oXmlHttp = ER_getHttpRequest(mywindow) ;
	    if (null == oXmlHttp) {
	    	//alert( 'getHttpRequest error' ) ; 
	    	return;
	    }
	    
	    //var jsLoader = this;
	    oXmlHttp.onreadystatechange = function() { 
	        if ( oXmlHttp.readyState == 4 )  { 
	            if ( oXmlHttp.status == 200 || oXmlHttp.status == 304 ) { 
	            	//jsLoader.loadjs(mywindow, sId, url, oXmlHttp.responseText );
	            	ER_loadjs(mywindow, sId, url, oXmlHttp.responseText );
	            }else {
	            	//alert( 'XML request error: ' + oXmlHttp.statusText + ' (' + oXmlHttp.status + ')' ) ; 
		        }
		    }
		}
	
	    oXmlHttp.open('GET', url, true); 
	    oXmlHttp.send(null); 
	}
};

/**
 * 模态方式展示报表
 * param reportUrl 报表的完整Url路径 
 * param serverUrl http://ip:port/HappyServer/, 应用内请传空
 * param windowTitle 窗口显示标题 
 */
function ER_showReportByModal(reportUrl,serverUrl,windowTitle,showToTopWindow,winHeight,winWidth) {
	if (undefined != reportUrl && "" != reportUrl) {
		if (undefined == serverUrl) {
			serverUrl = "";
		}
		
		var fullScreen = false;
		if ((undefined == winHeight || 0 == winHeight || "0" == winHeight || "" == winHeight)
		 && (undefined == winWidth || 0 == winWidth || "0" == winWidth || "" == winWidth)) {
		 	fullScreen = true;
		}
		
		var maxWindowWidth = ER_getTopWindowClientWidth();
		var maxWindowHeight = ER_getTopWindowClientHeight();
	
		if (undefined == winHeight || 0 == winHeight || "0" == winHeight || "" == winHeight) {
			winHeight = maxWindowHeight - 2;
		}
		else if (winHeight > maxWindowHeight) {
			winHeight = maxWindowHeight;
		}
		
		if (undefined == winWidth || 0 == winWidth || "0" == winWidth || "" == winWidth) {
			winWidth = maxWindowWidth - 4;
		}
		else if (winWidth > maxWindowWidth) {
			winWidth = maxWindowWidth;
		}

		if (showToTopWindow) {
			//JS依赖于 jquery 			
			var load_jquery_js = false;
			if (undefined = window.top.$) {
				load_jquery_js = true;
			}
			if (false == load_jquery_js && window.top.isER_load_jquery_js) {
				load_jquery_js = window.top.isER_load_jquery_js();
			}
			
			if (true != load_jquery_js) {
				if (window.top.ER_load_jquery_js) {
					window.top.ER_load_jquery_js();
				}
		
				ER_dynamicLoading.js(window.top.document, serverUrl+"res/common/lib/jquery/jquery.min.js");
				
				setTimeout(function(){ ER_showReportByModal(reportUrl,serverUrl,windowTitle,showToTopWindow,winHeight,winWidth); }, 100);
				return;
			}
			
			var needRedo = false;
			
			//自定义窗口的标题栏
			var load_dialog_css = false;
			if (window.top.isER_load_dialog_files) {
				load_dialog_css = window.top.isER_load_dialog_files();
			}
			if (true != load_dialog_css) {
				needRedo = true;
				if (window.top.ER_load_dialog_files) {
					window.top.ER_load_dialog_files();
				}
				
				ER_dynamicLoading.css(window.top.document, serverUrl+"res/common/lib/layer/skin/ER_dialog.css");
			}
			
			//目前依赖于 layer 窗口展示控件
			var load_Layer = false;
			if (window.top.isER_load_layer_files) {
				load_Layer = window.top.isER_load_layer_files();
			}
			if (true != load_Layer) {
				needRedo = true;
				if (window.top.ER_load_layer_files) {
					window.top.ER_load_layer_files();
				}
				
				//ER_dynamicLoading.css(window.top.document, serverUrl+"res/common/lib/layer/skin/default/layer.css");
				//ER_dynamicLoading.js1(window.top, "layer_js",serverUrl+"res/common/lib/layer/layer.js");	
				ER_dynamicLoading.css(window.top.document, serverUrl+"res/common/lib/layer/skin/default/layer.css");
				ER_dynamicLoading.js(window.top.document, serverUrl+"res/common/lib/layer/layer.js");				
			}
			
			if (needRedo) {
				setTimeout(function(){ ER_showReportByModal(reportUrl,serverUrl,windowTitle,showToTopWindow,winHeight,winWidth); }, 300);
				return ;
			}
		}else {		
			//JS依赖于 jquery 			
			var load_jquery_js = false;
			if (isER_load_jquery_js) {
				load_jquery_js = isER_load_jquery_js();
			}
			if (true != load_jquery_js) {
				if (ER_load_jquery_js) {
					ER_load_jquery_js();
				}
				ER_dynamicLoading.js(document, serverUrl+"res/common/lib/jquery/jquery-1.8.0.min.js");
				
				setTimeout(function(){ ER_showReportByModal(reportUrl,serverUrl,windowTitle,showToTopWindow,winHeight,winWidth); }, 100);
				return;
			}
			
			var needRedo = false;
			//自定义窗口的标题栏
			var load_dialog_css = false;
			if (window.top.isER_load_dialog_files) {
				load_dialog_css = window.top.isER_load_dialog_files();
			}
			if (true != load_dialog_css) {
				needRedo = true;
				if (window.top.ER_load_dialog_files) {
					window.top.ER_load_dialog_files();
				}
				
				ER_dynamicLoading.css(document, serverUrl+"res/common/lib/layer/skin/ER_dialog.css");
			}
			
			//目前依赖于 layer
			var load_Layer = false;
			if (isER_load_layer_files) {
				load_Layer = isER_load_layer_files();
			}
			if (true != load_Layer) {
				needRedo = true;
				if (ER_load_layer_files) {
					ER_load_layer_files();
				}
				
				ER_dynamicLoading.js(document, serverUrl+"res/common/lib/layer/layer.js");
			}
			
			if (needRedo) {
				setTimeout(function(){ ER_showReportByModal(reportUrl,serverUrl,windowTitle,showToTopWindow,winHeight,winWidth); }, 100);
				return ;
			}
		}
		
		var ignoreModify = false;
		linkModelDialog = null;
		linkModelDialogFrame = null;
		var parentWindow = window;
		if (true === showToTopWindow) {
			parentWindow = window.top;
		}
		
		var htmlDlg = '<div id="ER_link_modalDialog" style="padding:1px;overflow:hidden;margin:0px;border:0px;"><iframe src="" style="width:100%;height:100%;border:0px;" border="0" frameborder="0" onload="focus();" ></iframe></div>';
		var dialogIndex = parentWindow.layer.open({
		    type: 1,
		    title: '加载中...',
		    content: htmlDlg,
		    area: [winWidth+'px', winHeight+'px'],
		    maxmin: true,
		    //anim : -1,
			success:function(){
				//窗口加载成功后
				linkModelDialog = parentWindow.$("body").find('#ER_link_modalDialog');
				if (linkModelDialog && linkModelDialog.length > 0) {
					linkModelDialogFrame = linkModelDialog.find("iframe:first");
					
					if (reportUrl.indexOf("hrServlet?") >= 0) {
						reportUrl += "&monitorModelWindow=true";
					}		
					
					if (linkModelDialogFrame && linkModelDialogFrame.length > 0) {
						var width = winWidth - 2;
						var height = winHeight - 45;
						
						linkModelDialog.height(height);
						
						linkModelDialogFrame.width(width);
						linkModelDialogFrame.height(height);
						
						linkModelDialogFrame.attr("src",reportUrl);
						//解决谷歌浏览器下弹出层不能自动获得焦点的情况
						//linkModelDialogFrame.attr("onload", "linkModelDialogFrame.get(0).focus();");
						
						linkModelDialogFrame.bind("load", function() {
							var execlReport = undefined;
							try {
								execlReport = this.contentWindow.ER;
							}catch(e) {}
							if (undefined != execlReport) {
								if (undefined == windowTitle || "" == windowTitle) {
									var iframeFn = execlReport.fn;
									if (iframeFn) {
										windowTitle = iframeFn.getCustomVar().windowTitle;
										
										iframeFn.resize(0,0);
									}												
								}
								
								var iframeEvents = execlReport.events;
								if (iframeEvents) {
									//绑定报表加载完成事件
									iframeEvents.loadReportAfter(function() {
										parentWindow.layer.title(windowTitle, dialogIndex);
									});
								}
							}else {
								if (isBlankString(windowTitle)) {
									windowTitle = this.contentWindow.window.document.title;
								}
								
								parentWindow.layer.title(windowTitle, dialogIndex);
							}
						});
					}
				}				
		    },
		    end: function(){
		    	//窗口关闭后
		    	
		    },
		    cancel:function(){
		    	//点击关闭按钮
		    	if (ignoreModify) {
					return true;
				}
		    	
		    	if (linkModelDialogFrame && linkModelDialogFrame.length > 0) {
					var iframeWindow = linkModelDialogFrame[0].contentWindow;
					var execlReport = undefined;
					try {
						execlReport = iframeWindow.contentWindow.ER;
					}catch(e) {}
					
					if(execlReport && execlReport.fn){
						if(iframeWindow.ER.fn.isReportWriteable()){
				       		//填报报表
				       		if(iframeWindow.ER.fn.isModified()) {
				       			iframeWindow.ER.fn.confirm('确认', '页面值已经修改，是否继续？', function(index){
				       				ignoreModify = true;
				       				iframeWindow.layer.close(index);							    	

				       				ER_closeLinkModelWindow(showToTopWindow, dialogIndex);
				       				return true;
				       			});
								
				       			return false;
				       		}
				       	}
				       	else if (iframeWindow.ER.fn.getCustomVar().sceneView) {
				       		if(iframeWindow.ER.fn.isModified()){				       			
				       			iframeWindow.ER.fn.confirm('确认', '页面值已经修改，是否继续？', function(index){
				       				ignoreModify = true;
				       				iframeWindow.layer.close(index);
							    	
				       				ER_closeLinkModelWindow(showToTopWindow, dialogIndex);
				       				return true;
				       			});
								
				       			return false;
				       		}
				       	}
		       		}									       		
				}
		    	
		    	ER_closeLinkModelWindow(showToTopWindow, dialogIndex);
		    },
//			full/min/restore -分别代表最大化、最小化、还原 后触发的回调
		    restore:function() {
		    	//从最大化回复到正常大小
		    	if (linkModelDialogFrame && linkModelDialogFrame.length > 0) {
		    		var width = linkModelDialog.width() - 2;
					var height = linkModelDialog.height() - 4;
				
					linkModelDialogFrame.width(width);
					linkModelDialogFrame.height(height);
					
					var iframeWindow = linkModelDialogFrame[0].contentWindow;
					if(iframeWindow.ER && iframeWindow.ER.fn){
			       		iframeWindow.ER.fn.resize(0,0);
		       		}
				}
		    },
		    full:function() {
		    	//最大化显示
		    	if (linkModelDialogFrame && linkModelDialogFrame.length > 0) {
					//linkModelDialogFrame = linkModelDialogFrame[0];
		    		var width = linkModelDialog.width() - 2;
					var height = linkModelDialog.height() - 4;
				
					linkModelDialogFrame.width(width);
					linkModelDialogFrame.height(height);
					
					var iframeWindow = linkModelDialogFrame[0].contentWindow;
					if(iframeWindow.ER && iframeWindow.ER.fn){
			       		iframeWindow.ER.fn.resize(0,0);
		       		}
				}
		    },
		    min:function(){
		    	//最小化显示
		      //alert('已点击最小化')
		    }
		});
	}
}

/**
 * @function 关闭填报模态窗口
 * @param {} obj
 */
function ER_closeLinkModelWindow(showToTopWindow, dialogIndex) {		
	var retValue = "";
	var retModifyCellsValue = "";
	var dialog = null;
	if (true === showToTopWindow) {
		dialog = window.top.$("body").find('#ER_link_modalDialog');
	}else {
		dialog = $("body").find('#ER_link_modalDialog');
	}
	if (dialog && dialog.length > 0) {
		var dialogFrame = dialog.find("iframe:first");
		if (dialogFrame && dialogFrame.length > 0) {
			var iframeWindow = dialogFrame[0].contentWindow;
			//var iframeDocument = iframeWindow.document;
			var execlReport = undefined;
			try {
				execlReport = iframeWindow.contentWindow.ER;
			}catch(e) {}
			
			if (execlReport) {
				execlReport.rpt.setKeepAlive(false);
				retValue = execlReport.rpt.getWriteResult().returnValue;
				retModifyCellsValue = execlReport.rpt.getWriteResult().modifyCellValues;
				
				//触发释放缓存
				execlReport.fn.getRptPage().releaseCache();
			}
			
			//触发释放缓存
			//$(dialogFrame).attr("src","");
		}

		setTimeout(function(){ 
//			if (dialog && dialog.length > 0) {	
//				try {
//					dialog.dialog('destroy');
//					dialog.remove();
//				}
//				catch(e) {
//				}
//			}
			
			var parentWindow = window;
			if (true === showToTopWindow) {
				parentWindow = window.top;
			}
			
			parentWindow.layer.close(dialogIndex);
		}, 500);
	}
}

function isBlankString(str) {
	if((null == str) || (undefined == str) || ($.trim(str) == "")) {    
        return true;    
    }    
    return false;
}

function ER_loadjs (mywindow, sId, fileUrl, source) { 
    if ( ( source != null ) && ( !mywindow.document.getElementById( sId ) ) ) { 
        var oHead = mywindow.document.getElementsByTagName('HEAD').item(0); 
        var oScript = mywindow.document.createElement( "script" ); 

        oScript.language = "javascript"; 
        oScript.type = "text/javascript"; 
        oScript.id = sId; 
        oScript.defer = true; 
        oScript.text = source; 

        oHead.appendChild( oScript ); 
    } 
}

function ER_getHttpRequest (mywindow) { 
    if ( mywindow.XMLHttpRequest ) {
    	// Gecko
    	return new XMLHttpRequest() ; 
    } else if ( mywindow.ActiveXObject ) {
    	// IE 
    	return new ActiveXObject("MsXml2.XmlHttp") ; 
    }
    return null;
}