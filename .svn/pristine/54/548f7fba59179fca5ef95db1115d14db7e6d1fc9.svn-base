<%@ page language="java" import="com.datanew.util.ConfigureParser"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>预算绩效管理平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"><%--IE8以上都可以（支持IE）--%>
<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.datanew.js"></script>
<script language="Javascript" src="js/xmlhttp.js"></script><%--导包--%>
<script language="Javascript" src="js/requestException.js"></script><%--导包--%>
<link rel="stylesheet" href="css/newlogin.css" />
<link rel="stylesheet" href="css/common.css"/>
</head>
<body>
<object classid="clsid:707C7D52-85A8-4584-8954-573EFCE77488" id="JITDSignOcx" width="0" codebase="JITDSign.cab#version=2,0,24,19"></object>
	<div class="loginBox">
		<div class="login_hd">
			<b>预算绩效管理平台</b>
		</div>
		<div class="login_bd clearfix">
			<div class="login_lt lt">
				<div class="lg_title">公告</div>
				<ul class="lg_list">
				<%--<li>如您发现部分页面布局有问题</li>
				<li>请您使用chrome浏览器</li>--%>
				<li>点击下面按钮下载chrome</li>
				</ul>
				<p><a href="login/downloadChrome.do" target="_blank"><span style="color: dodgerblue">chrome32位下载</span></a></p>
				<p><a href="login/downloadChromeLitter.do" target="_blank"><span style="color: dodgerblue">chrome64位下载</span></a></p>
				<p><a href="login/downloadModelEssay.do" target="_blank"><span style="color: dodgerblue">监控表参考范本下载</span></a></p>
				<p><a href="login/downloadInstruction.do" target="_blank"><span style="color: dodgerblue">用户操作指南</span></a></p>
				<p><a href="login/downloadReport.do" target="_blank"><span style="color: dodgerblue">监控自评考核表查看</span></a></p>
				<!--<input value="下载"  style="height: 20px;" type="button" onclick="location.href = 'common/downloadChrome.do'" />-->
			</div>
			<form id="loginform">
				<div class="login_rt rt">
					<div class="lg_title">用户登录</div>
					<div class="lg_item">
						<input type="text" onBlur="getOperator()" id="userName" name="userName" placeholder="用户名" title="用户名"
							class="tb_input tb_inp_name" />
					</div>

					<div class="lg_item">
						<input type="passWord" name="passWord" proving="notEmpty"
							placeholder="密码" title="密码" class="tb_input tb_inp_pwd" />
					</div>
					<div class="lg_item clearfix">
						<div class="chkbox lt">
							<input type="checkbox" name="" id="remember" class="tb_chk" />记住密码
						</div>
						<div class="addFavorite rt">
							<a href="javascript:void(0);" onclick="javascript:addFavorite();">加入收藏夹</a>
						</div>

					</div>
					<div class="lg_item clearfix">
						<div class="lt">
							<button id="submitbtn" type="button" class="tb_btn tb_btn_login">登录</button>
						</div>
						<div class="rt">
							<button type="button" class="tb_btn tb_btn_reset" id="resetbtn">重置</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		var userCode;
		var userNa;
		//ca认证需要变量
        var DSign_Content = "";
        var temp_DSign_Result = "";
        var	loginType = "0";
        var userToken = "";
        var usbMode = "1";
        var caauth = "true";
        var caping = "true";
        var xmlNode;//add by zlj 2015-05-06
        var operatorName = "";//add by zlj 2015-05-06

		//新加 ：判断IE10以下判断ie浏览器
        function isIE(){
            //debugger;
            if (window.navigator.userAgent.indexOf("MSIE")>=0)
                return true;
            else
                return false;
        }
        //IE10以上判断是否ie浏览器方法
        /*function isIE() {
            debugger;
            if (!!window.ActiveXObject || "ActiveXObject" in window){
                return true;
            }else{
                return false;
            }
        }*/

		function bindEvent() {
			/* $(document).on("blur", "input[name='userName']", function() {
				if ($(this).val() != "" && userNa != $(this).val() != "") {
					getOperator(this);
					$("input[name='passWord']").val(getCookieValue(userCode));
				}
			}); */
			$("#submitbtn").click(function() {
				if (userCode == "" || !userCode) {
					return alert("用户名不能为空");
				}
				if ($("input[name='passWord']").val() == "") {
					return alert("密码不能为空");
				}
				if ($("#remember").is(":checked")) {
					rememberPassWord();
				}

				login();
			});
		}
		function getOperator() {
			userCode = $("#userName").val() + "";
			if(userCode == ""){
				return;
			}
			if (userNa != userCode) {
				$.ajax({
					url : "login/getJczlOperator.do",
					data : {
						userCode : userCode
					},
					async : true,
					dataType : "json",
					success : function(data) {
						if (!data || !data.name) {
							alert("不存在此用户");
							userNa = "";
							$("#userName").val("");
							return false;
						} else {
							userNa = data.name;
							$("#userName").val(userNa);
							$("input[name='passWord']").val(getCookieValue(userCode));
						}
					},
					error : function(data) {
						alert("系统错误");

					}

				});
			}

		}
		function login() {
			$.post(
					"login/logIn.do",
					{
						operatorCode : userCode,
						operatorName : $("input[name='userName']").val(),
						password : $("input[name='passWord']").val(),
						async : true,
						signed : "",
						ORIGINAL_DATA_KEY : "1111",
						dataType : "xml"

					},
					function(result) {
						var node = loadXMLString(result);
						try {
							var msg = $(node).find("errMsg").text();
							if (msg && msg != "") {
								return alert(msg);
							}
							var key = $(node).find("key").text();
							var sessId = $(node).find("sessionId")
									.text();
							window.location.href = "framework/index.htm?" + key
							+ "&sessionId="
							+ sessId,
					("win" + Math.round(Math
							.random() * 1000)),
					"menubar=0,toolbar=0,scrollbars=0,titlebar=0,location=no,status=1,resizable=1,top=0,left=0,width="
							+ (screen.availWidth)
							+ ",height="
							+ (screen.availHeight - 50);
							/* var newWindow = window
									.open(
											"pages/index.html?" + key
													+ "&sessionId="
													+ sessId,
											("win" + Math.round(Math
													.random() * 1000)),
											"menubar=0,toolbar=0,scrollbars=0,titlebar=0,location=no,status=1,resizable=1,top=0,left=0,width="
													+ (screen.availWidth)
													+ ",height="
													+ (screen.availHeight - 50));
							opener = null;
							top.window.opener = null;
							if (newWindow != null
									&& typeof (newWindow.document) == "object") {
								top.window.open('', '_self');
								top.window.close();
							} else {
								alert("浏览器屏蔽了弹出窗口，请重新设置，允许本系统打开弹出新窗口！");
							} */
						} catch (e) {
							alert("浏览器屏蔽了弹出窗口，请重新设置，允许本系统打开弹出新窗口！");
						}
					});
		}
		/* 字符串转dom对象 */
		function loadXMLString(txt) {
			try //Internet Explorer
			{
				xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
				xmlDoc.async = "false";
				xmlDoc.loadXML(txt);
				//alert('IE');
				return (xmlDoc);
			} catch (e) {
				try //Firefox, Mozilla, Opera, etc.
				{
					parser = new DOMParser();
					xmlDoc = parser.parseFromString(txt, "text/xml");
					//alert('FMO');
					return (xmlDoc);
				} catch (e) {/*alert(e.message)*/
				}
			}
			return (null);
		}
		$(function() {
			$("body").css({
				width : $(window).width(),
				overflowX : "hidden"
			});
                bindEvent();
			var Sys = {};
			var ua = navigator.userAgent.toLowerCase();
			var s;
			(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : (s = ua
					.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : (s = ua
					.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : (s = ua
					.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : (s = ua
					.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1]
					: 0;

			//以下进行测试
			/* if (Sys.ie) {
				if (Sys.ie <= 8.0) {
					alert('为了达到最佳浏览效果，建议使用更高版本的IE浏览器或其他浏览器')
				}
			} */

			var messageBrowser = appInfo();
			//ie8以下浏览器提示不兼容
			if (messageBrowser.appname == "ie" && messageBrowser.version < 8) {
				//$.messager.alert('提示','请在运行Web页之前检查兼容性设置','warning');
				topLeft();
			}
			//默认焦点在用户名的输入框上
			$("input[name='userName']").focus();
			//点击用户名框 选中内容
			$("input[name='userName']").click(function() {
				this.select();
			});

			// 用户名输入框获取焦点
			/* $(document).on("click", document, function() {
				if ($("input[name='userName']").val() == "") {
					$("input[name='userName']").focus();
				}
			}); */

			// 回车键登录
			$(document)
					.keydown(
							function(e) {
								if (e.keyCode == 13) {
									//$("input[name='userName']").blur();
									if ($("input[name='passWord']").val() == "") {
										$("input[name='passWord']").focus();
									} else if (userCode
											&& userCode != ""
											&& $("input[name='userName']")
													.val() != "") {
										//alert(userCode);
										$("#submitbtn").click();
									}
								}
							});
			//判断是IE浏览器就运行获取Ukey数据的方法
            $("#username").focus();
            if(isIE()){
                getInitApplication();
            }

			// 重置
			$("#resetbtn").click(function() {
				$("input[name='userName']").val("");
				$("input[name='passWord']").val("");
			});
		});

		//浏览器信息判断
		function appInfo() {
			var browser = {
				appname : 'unknown',
				version : 0
			}, userAgent = window.navigator.userAgent.toLowerCase();
			//IE,firefox,opera,chrome,netscape  
			if (/(ie|firefox|opera|chrome|netscape)\D+(\d[\d.]*)/
					.test(userAgent)) {
				browser.appname = RegExp.$1;
				browser.version = RegExp.$2;
			} else if (/version\D+(\d[\d.]*).*safari/.test(userAgent)) { // safari  
				browser.appname = 'safari';
				browser.version = RegExp.$2;
			}
			return browser;
		}

		function topLeft() {
			/*$.messager.alert({
				title:'温馨提示',
				msg:'为了获得更好的浏览效果，建议您使用Firefox,Chrome,IE11浏览器',
				//showType:'show',
				style:{
					right:'',
					left:3,
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				}
			});*/
		}

		/**
		 * 添加设置cookie
		 */
		function addCookie(name, value, days, path) {
			var name = encodeURIComponent(name);
			var value = value;
			if (name == 'passWord') {
				value = value;
			} else {
				value = encodeURIComponent(value);
			}

			var expires = new Date();
			expires.setTime(expires.getTime() + days * 3600000 * 24);
			//path=/，表示cookie能在整个网站下使用，path=/temp，表示cookie只能在temp目录下使用
			path = path == "" ? "" : ";path=" + path;
			//GMT(Greenwich Mean Time)是格林尼治平时，现在的标准时间，协调世界时是UTC
			//参数days只能是数字型
			var _expires = (typeof days) == "string" ? "" : ";expires="
					+ expires.toUTCString();
			document.cookie = name + "=" + value + _expires + path;
		}
		function rememberPassWord() {
			addCookie(userCode, $("input[name='passWord']").val(), 30, "/");
		}
		/**
		 * 获取cookie的值，根据cookie的键获取值
		 */
		function getCookieValue(name) {
			//用处理字符串的方式查找到key对应value
			//读cookie属性，这将返回文档的所有cookie
			var allcookies = document.cookie;
			//查找名为name的cookie的开始位置
			name += "=";
			var pos = allcookies.indexOf(name);
			//如果找到了具有该名字的cookie，那么提取并使用它的值
			if (pos != -1) {
				var start = pos + name.length;
				var end = allcookies.indexOf(";", start);
				if (end == -1)
					end = allcookies.length;
				var value = allcookies.substring(start, end);
				return (value);
			} else { //搜索失败，返回空字符串
				return "";
			}
		}

		/**
		 * 根据cookie的键，删除cookie，其实就是设置其失效
		 */
		function deleteCookie(name, path) {
			var expires = new Date(0);
			path = path == "" ? "" : ";path=" + path;
			document.cookie = name + "=" + ";expires=" + expires.toUTCString()
					+ path;
		}

		//加入收藏夹
		function isPad() {
			var ua = navigator.userAgent.toLowerCase();
			var isPad = ua.match(/iPad/i) == "ipad"
					|| ua.match(/iphone os/i) == "iphone os"
					|| ua.match(/android/i) == "android"
					|| ua.match(/ucweb/i) == "ucweb"
					|| ua.match(/rv:1.2.3.4/i) == "rv:1.2.3.4"
					|| ua.match(/midp/i) == "midp"
					|| ua.match(/windows ce/i) == "windows ce"
					|| ua.match(/windows mobile/i) == "windows mobile";
			return isPad;
		}
		function addFavorite() {
			var url = window.location;
			var title = document.title;
			try {
				//判断浏览器是否支持document.all
				if (document.all) {
					//如果支持则用external方式加入收藏夹
					window.external.addFavorite(url, title);
				} else if (window.sidebar) {
					//如果支持window.sidebar，则用下列方式加入收藏夹
					window.sidebar.addPanel(url, title, '');
				} else {
					if (isPad()) {
						alert("加入收藏夹失败,请手动添加操作!");
					} else {
						alert("加入收藏夹失败，请使用Ctrl+D快捷键进行添加操作!");
					}
				}
			}
			//处理异常
			catch (e) {
				if (isPad()) {
					alert("加入收藏夹失败，请手动添加操作!");
				} else {
					alert("加入收藏夹失败，请使用Ctrl+D快捷键进行添加操作!");
				}
			}
		}
		/*
			新加：
		 */
        function getInitApplication() {

            getSelfUSBChecknew();
        }
		/*
		新加：  获取随机数
		 */
        function getSelfUSBChecknew() {
            //DSign_Content = "111";
            //by bjf取随机数
            var url = "JCZL/random.in";//获取随机数接口
            var params = new HTTP_Request_Agent_Params();
            params.url = url;
            params.method = "POST";
            params.async = false;
            var xmlHttp = new HTTP_Request_Agent(params);
            xmlHttp.onresult = function(){
                var node = this.getXMLResult();
                if(!hasException(node)){
                    DSign_Content = node.documentElement.selectSingleNode("/result/randomNum").text;
                }
            }
            xmlHttp.send();
            var DSign_Subject = "";
            JITDSignOcx.SetCertChooseType(1);

            JITDSignOcx.SetCert("SC","",userToken.toUpperCase(),"","C=CN, O=MOF, CN=Private Certificate Authority Of MOF","");
            if(JITDSignOcx.GetErrorCode()!=0){
                //	alert("错误码1："+JITDSignOcx.GetErrorCode()+"　错误信息："+JITDSignOcx.GetErrorMessage(JITDSignOcx.GetErrorCode()));
                DSign_Subject = false;
                return false;
            }
            temp_DSign_Result = JITDSignOcx.DetachSignStr(DSign_Subject,DSign_Content);
            //	alert("",temp_DSign_Result);
            if(JITDSignOcx.GetErrorCode()!=0){
                alert("错误码2："+JITDSignOcx.GetErrorCode()+"　错误信息："+JITDSignOcx.GetErrorMessage(JITDSignOcx.GetErrorCode()));
                return false;
            }
            var temp_DSign_Check_Result = JITDSignOcx.verifyDetachedSignStr(temp_DSign_Result, DSign_Content);

            if(JITDSignOcx.GetErrorCode()!=0 && JITDSignOcx.GetErrorCode()!=-10101029){
                if(JITDSignOcx.GetErrorCode()!=0 && JITDSignOcx.GetErrorCode()!=-10101037){
                    alert("错误码3："+JITDSignOcx.GetErrorCode()+" 错误信息："+JITDSignOcx.GetErrorMessage(JITDSignOcx.GetErrorCode()));
                    return false;
                }
                else {
                    alert("错误码4："+JITDSignOcx.GetErrorCode()+" 错误信息："+JITDSignOcx.GetErrorMessage(JITDSignOcx.GetErrorCode()));
                    return false;
                }
            } else {
                if(temp_DSign_Check_Result==0) {
                    var serialNumber = JITDSignOcx.getCertInfo("VS",2,"");
                    var allName = JITDSignOcx.getCertInfo("VS",0,"");
                    var ans = allName.split(",");
                    var subName = allName.substring(3, allName.indexOf(","));
                    //by bjf 增加用户代码取值登录方式的验证，（邮件前缀与用户代码一致验证)
                    //获取用户信息
                    var userId =ans[1].substring(3, ans[1].indexOf("@"));
                    if(userId == null || userId == ""){
                        alert("证书中未取得用户账号信息,请联系系统管理员!");
                        return;
                    }
                    /*alert("signed:"+temp_DSign_Result);//K码
                    alert("ORIGINAL_DATA_KEY:"+DSign_Content);//原始随机数
                    alert("operatorName:"+operatorName);//用户名*/
                    $.dajax({
                        type:"post",
                        url:"Login/fLoginByCA.do",
                        data:{
                            signed:temp_DSign_Result,//uKey码
                            ORIGINAL_DATA_KEY: DSign_Content,//原始随机数
                            operatorName:operatorName,//用户名
                            //loginSign:loginType
                        },
                        dataType:'json',
                        success:function (data) {
                            if(data.success){
                                window.location.href="framework/index.htm";
                            }else{
                                $.dalert({text:data.content});
                            }
                        }
                    })


                }
            }
        }
	</script>
</body>
</html>
