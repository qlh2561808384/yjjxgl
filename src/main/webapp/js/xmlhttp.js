
	function HTTP_Request_Agent_Params(){
		this.url = "";
		this.method = "POST";											//默认请求方式POST
		this.async = true;												//默认异步
		this.content = {};												//存放提交数据各个字段
		this.header = {};												//存放http头信息
		this.split = false;												//对长字段值是否使用自动切割
		this.popup = null;												//默认根据返回信息自己确定是否弹出

		this.parserIsland = new ActiveXObject("MSXML.DOMDOCUMENT");		//用于存放解析数据，默认不使用数据岛
		this.parserIsland.async = false;

		this.relogin = function(){
			location.href = "login.htm";
		}
		this.refresh = function(){
			location.reload();
		}
	}
	HTTP_Request_Agent_Params.prototype.setContent = function(name,value){
		this.content[name] = value;
	}
	HTTP_Request_Agent_Params.prototype.clearContent = function(name){
		delete this.content[name];
	}
	HTTP_Request_Agent_Params.prototype.clearAllContent = function(){
		this.content = {};
	}
	HTTP_Request_Agent_Params.prototype.setHeader = function(name,value){
		this.header[name] = value;
	}



	function HTTP_Request_Agent(paramsInstance){
		
		/***** 从配置文件找是否需要转换虚拟数据文件地址 *****/
		var isMatched = false;
		var redirectURL = "";

		//action默认继承config的mode属性设置，如果冲突，action上的mode优先
		var configXML = [];
		configXML[configXML.length] = "<config mode=\"debug\">";
		configXML[configXML.length] = "	<action>";
		configXML[configXML.length] = "		<pattern>/^/JCZL/baseinfo/baseinfoMaintain!getTree.action/i</pattern>";
		configXML[configXML.length] = "		<debug>__data.xml</debug>";
		configXML[configXML.length] = "	</action>";
		configXML[configXML.length] = "	<action>";
		configXML[configXML.length] = "		<pattern>/^/JCZL/baseinfo/baseinfoMaintain!getInformation.action/i</pattern>";
		configXML[configXML.length] = "		<debug>__data1.xml</debug>";
		configXML[configXML.length] = "	</action>";
		configXML[configXML.length] = "	<action>";
		configXML[configXML.length] = "		<pattern>/^/JCZL/baseinfo/baseinfoMaintain!save.action/i</pattern>";
		configXML[configXML.length] = "		<debug>__data2.xml</debug>";
		configXML[configXML.length] = "	</action>";
		configXML[configXML.length] = "	<action>";
		configXML[configXML.length] = "		<pattern>/^/JCZL/baseinfo/baseinfoMaintain!update.action/i</pattern>";
		configXML[configXML.length] = "		<debug>__data3.xml</debug>";
		configXML[configXML.length] = "	</action>";
		configXML[configXML.length] = "	<action>";
		configXML[configXML.length] = "		<pattern>/^/baseinfoMaintain!test.action/i</pattern>";
		configXML[configXML.length] = "		<debug>__data.xml</debug>";
		configXML[configXML.length] = "	</action>";
		configXML[configXML.length] = "</config>";
		
		var configXmlDoc = new ActiveXObject("Msxml2.DOMDocument");
		configXmlDoc.async = false;
		configXmlDoc.loadXML(configXML.join("\r\n"));
		
		var actions = configXmlDoc.selectNodes("/config/action[(ancestor(config[@mode='debug']) and not(@mode)) or @mode='debug']");
		for(var i=0;i<actions.length;i++){
			var curPattern = actions[i].selectSingleNode("pattern").text;
			curPattern = "/" + curPattern.substring(1,curPattern.lastIndexOf("/")).replace(/([\/\.\:\+\-\?\!])/g,"\\$1") + curPattern.substring(curPattern.lastIndexOf("/"));
			var curReg = eval(curPattern);
			if(curReg.test(paramsInstance.url)==true){
				isMatched = true;
				redirectURL = actions[i].selectSingleNode("debug").text;
				break;
			}
		}
		
		if(isMatched==true){
			paramsInstance.url = redirectURL;
		}

		var obj = new XmlHttp(paramsInstance);
		obj.async = paramsInstance.async;
		obj.method = paramsInstance.method;

		return obj;
	}

	function XmlHttp(paramsInstance){
		this.value = "";									//string
		this.xmlhttp = new ActiveXObject("MSXML2.XMLHTTP");
		this.xmldoc = null;

		this.url = paramsInstance.url;
		this.content = paramsInstance.content;
		this.header = paramsInstance.header;

		this.method = paramsInstance.method;
		this.async = paramsInstance.async;
		this.split = paramsInstance.split;

		this.relogin = paramsInstance.relogin;
		this.refresh = paramsInstance.refresh;
		this.popup = paramsInstance.popup;

		this.parserIsland = paramsInstance.parserIsland;
	}
	
	XmlHttp.prototype.getResult = function(){
		return this.value;
	}
	XmlHttp.prototype.getXMLResult = function(){
		return this.xmldoc;
	}
	XmlHttp.prototype.showWaitingLayer = function(){
	
		var _waitingDiv = top.document.all("_waitingDiv");
		if(_waitingDiv == null){
			_waitingDiv = top.document.createElement('<div id="_waitingDiv" style="display:none;position:absolute;left:0px;top:0px;width:100%;height:100%;"></div>');
			_waitingDiv.innerHTML = "<TABLE width=\"100%\" height=\"100%\"><TR><TD align=\"center\"><object classid=\"clsid:d27cdb6e-ae6d-11cf-96b8-444553540000\" codebase=\"http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0\" width=\"140\" height=\"30\" id=\"loadingbar\" align=\"middle\"><param name=\"allowScriptAccess\" value=\"sameDomain\" /><param name=\"movie\" value=\"images/loadingbar.swf\" /><param name=\"quality\" value=\"high\" /><param name=\"wmode\" value=\"transparent\" /><param name=\"bgcolor\" value=\"#ffffff\" /><embed src=\"images/loadingbar.swf\" quality=\"high\" wmode=\"transparent\" bgcolor=\"#ffffff\" width=\"140\" height=\"30\" name=\"loadingbar\" align=\"middle\" allowScriptAccess=\"sameDomain\" type=\"application/x-shockwave-flash\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" /></object></TD></TR></TABLE><div style=\"background:black;filter:alpha(opacity=0);width:100%;height:100%;position:absolute;left:0;top:0;z-index:10000;\"></div>";
			top.document.body.appendChild(_waitingDiv);
			
		}
		if(_waitingDiv != null){
			_waitingDiv.style.cursor = "wait";
			_waitingDiv.style.display = "block";
			
		}
	}
	XmlHttp.prototype.hideWaitingLayer = function(){
		var _waitingDiv = top.document.all("_waitingDiv");
		if(_waitingDiv!=null){
			_waitingDiv.style.cursor = "default";
			_waitingDiv.style.display = "none";
		}
		
	}
	
	XmlHttp.prototype.send = function(){
		var oThis = this;

		try{
			//创建蒙板
			this.showWaitingLayer();
			window.status = "正在加载数据，请稍候……";

			this.xmlhttp.onreadystatechange = function() {
				if(oThis.xmlhttp.readyState != 4) return;

				oThis.hideWaitingLayer();
				window.status = "数据加载完成";

				oThis.value = oThis.xmlhttp.responseText;

				var responseParser = new Response_Parser(oThis.value,oThis.parserIsland,oThis.popup);
				
				//将通过解析后的responseXMLDom赋予xmlDoc
				oThis.xmldoc = responseParser.responseXMLDom;
				
				if(responseParser.result.dataType=="exception"){
					
					new Message_Exception(responseParser.result,oThis.relogin,oThis.refresh);
					oThis.onexception();
					oThis.returnValue = false;
				}else if(responseParser.result.dataType=="success"){
					new Message_Success(responseParser.result);
					oThis.onsuccess();
					oThis.returnValue = true;
				}else{
					oThis.onresult();				
					oThis.returnValue = true;			
				}

			}
            //2008-3-24 增加sessionId
	//            var sessId = "";
	//            var topWin = self;
	//            while(topWin!=top && /sessionId/gi.test(topWin.location.href)!=true){
	//                topWin = topWin.parent;
	//            }
	//            if(/sessionId/gi.test(topWin.location.href)==true){
	//                sessId = new Params(topWin.location.search.substring(1)).getParam("sessionId");
	//            }
            
            /*20110613龙涛修改*/
//			var str = topWin.location.search.substring(1);
//			if(null != str && "" != str && str.indexOf("&") > 0) {
//				var currkey = str.substring(0, str.indexOf("&"));
//				this.header["current_user_logon_key"] = currkey;
//				this.header["current_user_logon_session"] = sessId;
//			}
			/*20110613龙涛修改*/

            this.xmlhttp.open(this.method, this.url, this.async);
			
			//this.xmlhttp.open(this.method, this.url + ";jsessionid="+sessId, this.async);

			//拼接发送数据各个字段，并且进行编码，如果单个字段长度超出指定大小则切割成多个同名字段发送
			var contentStr = "";
			var contentArr = [];
			var contentXml = new ActiveXObject("MSXML.DOMDOCUMENT");
			contentXml.async = false;
			var contentXmlRoot = contentXml.createElement("Request");
			contentXml.appendChild(contentXmlRoot);

			var bufferSize = 10240;													//每10K大小为一个单位
			var bufferReg = new RegExp(".{1,"+bufferSize+"}","g");

			for(var item in this.content){

				var tempNameNode = contentXml.createElement("Name");
				var tempCDATANode = contentXml.createCDATASection(item);
				tempNameNode.appendChild(tempCDATANode);

				var tempValueNode = contentXml.createElement("Value");
				var tempCDATANode = contentXml.createCDATASection(String(this.content[item]));
				tempValueNode.appendChild(tempCDATANode);

				var tempParamNode = contentXml.createElement("Param");
				tempParamNode.appendChild(tempNameNode);
				tempParamNode.appendChild(tempValueNode);

				contentXmlRoot.appendChild(tempParamNode);

			}
			contentStr = contentXml.xml;

			/*
			if(this.split!=false && tempContentStr.length>bufferSize){				//使用自动切割的话，当超出指定大小，进行切割
				var tempContentArr = tempContentStr.match(bufferReg);
				for(var i=0;tempContentArr!=null && i<tempContentArr.length;i++){
					contentArr[contentArr.length] = item + "=" + tempContentArr[i];
				}
			}else{
				contentArr[contentArr.length] = item + "=" + tempContentStr;
			}
			contentStr = contentArr.join("&");
			*/



			this.xmlhttp.setRequestHeader("REQUEST-TYPE","xmlhttp");
			this.xmlhttp.setRequestHeader("REFERER", this.url);

			for(var item in this.header){											//设置自定义http头信息
				this.xmlhttp.setRequestHeader(item, String(this.header[item]));
			}

			if(this.split!=false){													//如果使用自动切割，则在requst header上加特定标志
				this.xmlhttp.setRequestHeader("REQUEST-CONTENT-SPLIT","true");
			}

			this.xmlhttp.setRequestHeader("CONTENT-TYPE","application/octet-stream");
			this.xmlhttp.setRequestHeader("Content-Length",contentStr.length);
			this.xmlhttp.send(contentXml);
		}
		catch(e){
			//throw e;
			var tempParserResult = {};
			tempParserResult.dataType = "exception";
			tempParserResult.msg = e.description;
			tempParserResult.description = e.description;
			tempParserResult.popup = oThis.popup||"1";

			oThis.hideWaitingLayer();
			window.status = "数据加载完成";

			new Message_Exception(tempParserResult);
			this.onexception();
		}
	}
	XmlHttp.prototype.onresult = XmlHttp.prototype.onsuccess = XmlHttp.prototype.onexception = function(){
	
	}



	/***** 返回数据分析处理对象 *****/

	function Response_Parser(responseXMLString,parserIsland,popup){
		this.responseXMLString = responseXMLString;
		this.responseXMLDom = parserIsland;
		this.popup = popup;

		this.init();	
	}
	Response_Parser.prototype.init = function(){
		this.responseXMLDom.loadXML(this.responseXMLString);
		this.result = {};
		if(this.responseXMLDom.parseError.errorCode!=0){
			this.result.dataType = "exception";
			this.result.msg = "数据出错在第" + this.responseXMLDom.parseError.line + "行第" + this.responseXMLDom.parseError.linepos + "字符";
			this.result.description = this.responseXMLDom.parseError.reason;
			this.result.popup = "1";
		}else{
			if(this.responseXMLDom.selectSingleNode("/exception")!=null){		//只要有exception节点就认为是异常信息
				this.result.dataType = "exception";

				var detailNodes = this.responseXMLDom.selectNodes("/*/*");
				for(var i=0;i<detailNodes.length;i++){
					var tempName = detailNodes[i].nodeName;
					var tempValue = detailNodes[i].text;
					this.result[tempName] = tempValue;
				}
			}else if(this.responseXMLDom.selectSingleNode("/success")!=null){	//只要有success就认为是成功信息
				this.result.dataType = "success";

				var detailNodes = this.responseXMLDom.selectNodes("/*/*");
				for(var i=0;i<detailNodes.length;i++){
					var tempName = detailNodes[i].nodeName;
					var tempValue = detailNodes[i].text;
					this.result[tempName] = tempValue;
				}
			}else{
				this.result.dataType = "data";
			}
		}
		if(this.popup!=null){
			this.result.popup = this.popup;
		}
	}



	function Message_Exception(param,relogin,refresh){
		//初始化默认值
		param.popup = param.popup||"1";		//默认弹出
		param.relogin = param.relogin||"0";	//默认不重新登录
		param.refresh = param.refresh||"0";	//默认不刷新

		if(param.popup=="1"){
			alert(param.msg,param.description);
		}
		if(param.relogin=="1"){
			if(typeof(relogin)=="function"){
				relogin();
			}
		}
		if(param.refresh=="1"){
			if(typeof(refresh)=="function"){
				refresh();
			}
		}
	}
	function Message_Success(param){
		//初始化默认值
		param.popup = param.popup||"0";		//默认不弹出

		if(param.popup=="1"){
			alert(param.msg,param.description);
		}	
	}

