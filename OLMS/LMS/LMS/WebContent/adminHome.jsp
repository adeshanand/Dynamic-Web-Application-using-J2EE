<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMINISTRATOR</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="adminMenu.jsp" />
<div style="height: 150px; vertical-align: middle; text-align: center;background-color:black; color: aqua;">
<h5 style="padding-top: 10px;">NOTICE BOARD</h5>
<form  action="msgPublish" style="vertical-align: middle;" method="post">
<textarea onclick="alert('Maximum 255 characters allowed.')" name="msg" style="height: 80px; width:1000px; text-align: left;background-color:black; color: aqua; border:0px; font-size: 21px; "></textarea>
<input style="background-color:black; color: aqua; border:0px;" type="submit" value="Publish" />
</form>
</div>
<jsp:include page="footerPage.jsp" />
</body>
</html>