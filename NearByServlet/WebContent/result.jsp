<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
	<form action = "NearServlet" method = "get">
	輸入經度
	<input type = text name = "longitude"/>
	輸入緯度
	<input type = text name = "latitude"/>
	<input type = submit value = "Go"/>
	</form>
	<p>
	基隆火車站經度：121.738367   緯度：25.131674
	</p>
	<p>
	祥豐校門經度：121.772429   緯度：25.150904
	</p>
   <!-- 	
	<p>
		左上經緯度<%= (String)request.getAttribute("LTLN") %>，<%= (String)request.getAttribute("LTLA") %>-----------右上經緯度<%= (String)request.getAttribute("RTLN") %>，<%= (String)request.getAttribute("RTLA") %>
    </p>
    <p>
    	左下經緯度<%= (String)request.getAttribute("LBLN") %>，<%= (String)request.getAttribute("LBLA") %>-----------右下經緯度<%= (String)request.getAttribute("RBLN") %>，<%= (String)request.getAttribute("RBLA") %>		
	</p>
	 -->
</body>
</html>