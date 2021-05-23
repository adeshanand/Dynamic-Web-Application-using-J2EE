<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
<title>MODIFY EMPLOYEE</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="adminMenu.jsp" />

<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">SEARCH EMPLOYEE</div>
<form action="modifyEmployee2.jsp" method="post" >
<table align="center">
<tr><td><select name="choice"><option value="eid" >Employee ID</option><option value="ename" >Employee Name</option><option value="post" >Post</option><option value="dept_id" >Department ID</option></select></td><td><input type="text" name="value" /></td></tr>
<tr><td><br/></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Search" /></td></tr>
</table>
</form>
<jsp:include page="footerPage.jsp" />
<%
Integer State=0;
HttpSession session_modifyEmployee = request.getSession();
session_modifyEmployee.setAttribute("State", State);
%>
</body>
</html>