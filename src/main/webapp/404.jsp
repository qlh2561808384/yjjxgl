<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>页面没找到</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<script>
	var bar = 0
	var line = "||"
	var amount = "||"
	count()
	function count() {
		bar = bar + 2
		amount = amount + line
		document.loading.chart.value = amount
		document.loading.percent.value = bar + "%";
		if (bar < 99) {
			setTimeout("count()", 25);
		}//这里修改载入时间
		else {
			//window.location = "index.jsp";//这里改成你的网站地址
		}
	}
</script>
</head>

<body>
	<table border=0 cellpadding=0 cellspacing=0 width="100%" height="100%">
		<tr>
			<td align="center" style="padding-top: 60px;"><img
				src="images/404.jpg" width="100%" /></td>
		</tr>
		<tr>
			<form name=loading>
				<td align=center>
					<p>
						<font color=gray>555，你找的页面不见了，正在载入首页，请稍候.......</font>
					</p>
					<p>
						<input type=text name=chart size=46
							style="font-family: Arial; font-weight: bolder; color: gray; background-color: white; padding: 0px; border-style: none;">
						<br> <input type=text name=percent size=46
							style="font-family: Arial; color: gray; text-align: center; border-width: medium; border-style: none;">

					</p>
				</td>
			</form>
		</tr>
	</table>
</body>
</html>
