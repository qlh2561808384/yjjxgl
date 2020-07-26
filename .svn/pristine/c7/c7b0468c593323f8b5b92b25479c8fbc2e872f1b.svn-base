
var _setID = 1;
var _sysID = 7;

var NON_ENTE = 0;
var ADMIN_ENTE = 1;
var IS_ENTE = 2;
var _BDD_SERVERADDR = "10.40.19.91";
var _BDD_SERVERPORT = "9080";

//辅助映射报表地址
var _YSBDD_SERVERADDR = "10.40.19.91";
var _YSBDD_SERVERPORT = "9080";

var _BDD_APPLYNAME = "lsbb";
var _BDD_USERID = "userid=";
var _BDD_YEAR = "year=";
var _BDD_SYSID = "sysid=";
var _BDD_SETID = "setid=";
var _BDD_AUTHID = "anonymous_admin";
var _BDD_VERSION = '6.4.1.0';
var _BDD_VIEW_VERSION = '6.4.1';
function tranVal(url, obj,iframename) {
	{
		var tempForm = document.createElement("form");
		tempForm.id = "tempForm1";
		tempForm.method = "post";
		tempForm.action = url;
		tempForm.target = iframename;
		var value ="";
		var hideInput1 = document.createElement("input");
		hideInput1.type = "hidden";
		hideInput1.name = "variants"
			
	   for (var prop in obj) {  
		  if (obj.hasOwnProperty(prop)) {  
			  value+=(encodeURI(prop)+"="+ encodeURI(obj[prop])+";")
		  }  
		}  
		hideInput1.value = value;
		tempForm.appendChild(hideInput1);
		document.body.appendChild(tempForm);
		tempForm.submit();
		document.body.removeChild(tempForm);
	}
}

