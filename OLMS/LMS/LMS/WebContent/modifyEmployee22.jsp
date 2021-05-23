<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
<title>MODIFY BOOK</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="adminMenu.jsp" />
<%
HttpSession session_modifyEmployee = request.getSession();
Integer State = Integer.parseInt( session_modifyEmployee.getAttribute("State").toString());
if(State == 2){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> No Such Employee(s) Found! </div>
	<%
}
%>
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 25px;">SEARCH EMPLOYEE</div>
<form method="post" >
<table align="center">
<tr><td><select name="choice"><option value="eid" >Employee ID</option><option value="ename" >Employee Name</option><option value="post" >Post</option><option value="dept_id" >Department ID</option></select></td><td><input type="text" name="value" /></td><td><input type="submit" value="Search" /></td></tr>
</table>
</form>
<br />

<br />
<form action="modifyEmployee3.jsp" method="post" >
<table align="center">
<tr><td>Type Employee ID</td><td><input type="text" name="eidm" placeholder="Case Sensitive" title="Choose Employee ID from the above table and type its ID here to modify record" /></td><td><input type="submit" value="Go" /></td></tr>
</table>
</form>
<br />
<% 
if(State == 3){
	%> 
	<div style="font-style: italic; text-align: center; font-weight: 600;"> You have typed an incorrect Employee ID. </div> 
	<% 
}
%>
<jsp:include page="footerPage.jsp" />
</body>
</html>