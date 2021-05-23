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
if(State == 1){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;">You have entered an incorrect password!</div>
	<%
}
if(State == 2){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;">User Not Found!</div>
	<%
}
if(State == 3){
	%>
	<div style="margin-right:20px; text-align:right; font-size:20px;font-weight: 600;padding-bottom: 25px;">Password Changed Successfully!</div>
	<%
}
%>
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">CHANGE PASSWORD</div>
<form action="changeSelfPassword2.jsp"  method="post" >
<table align="center">
<tr><td>Current Password</td><td><input type="password" name="cpswd" /></td></tr>
<tr><td><br/></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Continue" /></td></tr>
</table>    
</form>
<jsp:include page="footerPage.jsp" />
</body>
</html>