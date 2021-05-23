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
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">CHANGE PASSWORD</div>
<form action="changeSelfPassword2.jsp"  method="post" >
<table align="center">
<tr><td>Current Password</td><td><input type="password" name="cpswd" /></td></tr>
<tr><td><br/></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Continue" /></td></tr>
</table>    
</form>
<jsp:include page="footerPage.jsp" />
<%
Integer State=0;
HttpSession session_changeSelfPassword = request.getSession();
session_changeSelfPassword.setAttribute("State", State);
%>
</body>
</html>