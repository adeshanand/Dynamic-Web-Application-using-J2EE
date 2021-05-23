<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
<title>SEARCH STUDENT</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="adminMenu.jsp" />
<%
HttpSession session_searchStudent = request.getSession();
Integer State = Integer.parseInt( session_searchStudent.getAttribute("State").toString());
if(State == 2){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> No such student(s) found! </div>
	<%
}
%>
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 25px;">SEARCH STUDENT</div>
<form action="studentFound1.jsp" method="post" >
<table align="center">
<tr><td><select name="choice"><option value="sid" >Student ID</option><option value="sname" >Student Name</option><option value="course" >Course</option><option value="session" >Session</option></select></td><td><input type="text" name="value" /></td><td><input type="submit" value="Search" /></td></tr>
</table>
</form>
<br />
<br />
<form action="studentFound2.jsp" method="post" >
<table align="center">
<tr><td>Type Student ID</td><td><input type="text" name="sids" placeholder="Case Sensitive" title="Choose Student ID from the above table and type its ID here to show it" /></td><td><input type="submit" value="Go" /></td></tr>
</table>
</form>
<br />
<% 
if(State == 3){
	%> 
	<div style="font-style: italic; text-align: center; font-weight: 600;"> You have typed an incorrect Student ID. </div> 
	<% 
}
%> 

<jsp:include page="footerPage.jsp" />
</body>
</html>