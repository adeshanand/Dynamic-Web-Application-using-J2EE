<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
<title>CHANGE PASSWORD</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="staffLibMenu.jsp" />
<%
HttpSession session_studentPasswordChange = request.getSession();
Integer State = Integer.parseInt( session_studentPasswordChange.getAttribute("State").toString());
if(State == 1){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;">Incorrect Student ID!</div>
	<%
}
if(State == 2){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;">You have typed an Incorrect Password.<br />If you forgot your password, consult to <br />the Administrator-Group-Member to reset it.</div>
	<%
}
if(State == 3){
	%>
	<div style="margin-right:20px; text-align:right; font-size:20px;font-weight: 600;padding-bottom: 25px;">Password Changed Successfully!</div>
	<%
}
%>
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">CHANGE PASSWORD</div>
<form action="studentPasswordChange2.jsp"  method="post" >
<table align="center">
<tr><td>Student ID</td><td><input type="text" name="sid" /></td></tr>
<tr><td>Current Password</td><td><input type="password" name="cpswd" /></td></tr>
<tr><td><br/></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Continue" /></td></tr>
</table>    
</form>
<jsp:include page="footerPage.jsp" />
</body>
</html>