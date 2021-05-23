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
<% 
String post_from_session = session.getAttribute("post").toString();
if(post_from_session.equals("Head of Library")){
%>
<jsp:include page="headLibMenu.jsp" />
<%	
}else if(post_from_session.equals("Staff")){
%>
<jsp:include page="staffLibMenu.jsp" />
<%	
}else if(post_from_session.equals("Administrator")){
%>
<jsp:include page="adminMenu.jsp" />
<%	
}
%>
<%
HttpSession session_changeSelfPassword = request.getSession();
Integer State = Integer.parseInt( session_changeSelfPassword.getAttribute("State").toString());
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
<form action="changeSelfPassword" method="post" >
<table align="center">
<tr><td>New Password</td><td><input type="password" name="npswd" /></td></tr>
<tr><td><br/></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Change Password" /></td></tr>
</table>
</form>
<jsp:include page="footerPage.jsp" />
</body>
</html>