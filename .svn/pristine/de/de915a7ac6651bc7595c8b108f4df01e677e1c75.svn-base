function hasException(node){
	if(node==null){
		var msg=new Object();
		msg.userMsg="请求服务器失败";
		msg.description="请求服务器失败!\n原因\n\t服务器没有启动或者连接服务器超时";
		msg.type="系统错误";

		alert(msg.description);
		return true;
	}
	if(node.tagName=="exception"){
		var msg=new Object();
		msg.userMsg=node.getAttribute("msg");
		msg.description=node.getAttribute("description");
		msg.type=node.getAttribute("type");
		alert(msg.userMsg, msg.description);
		if(node.getAttribute("relogin") == "true" && window.dialogArguments == null){
			top.location.href = "login.htm";
		}
		return true;
	}
}