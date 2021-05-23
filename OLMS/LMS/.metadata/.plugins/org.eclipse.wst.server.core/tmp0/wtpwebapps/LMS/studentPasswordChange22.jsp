<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
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
if(State == 4){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;">Password could not be changed.</div>
	<%
}
if(State == 5){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;">Password can not be left empty.</div>
	<%
}
%>
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">CHANGE PASSWORD</div>
<form action="studentPasswordChange" method="post" >
<table align="center">
<tr><td>New Password</td><td><input type="password" name="npswd" /></td></tr>
<tr><td><br/></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Change Password" /></td></tr>
</table>
</form>
<jsp:include page="footerPage.jsp" />
</body>
</html>