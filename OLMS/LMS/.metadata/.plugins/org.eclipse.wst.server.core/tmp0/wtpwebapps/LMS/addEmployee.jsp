<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
<title>ADD EMPLOYEE</title>
<script type="text/javascript" language="javascript">
<!-- 
function openWinPosts(){
	w1=window.open('posts.html',height=120,'Posts',"toolbar=no, menubar=no, width=250px, height=551px, resizable=no");
}
// -->
</script>
<script type="text/javascript" language="javascript">
<!-- 
function openWinDids(){
	w1=window.open('dids.html',height=120,'Dids',"toolbar=no, menubar=no, width=250px, height=551px, resizable=no");
}
// -->
</script>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="adminMenu.jsp" />
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">ADD EMPLOYEE</div>
<form action="addEmployee" method="post" name="empFrm" >
<table align="center">
<tr><td>ID*</td><td><input type="text" name="eid" /></td></tr>
<tr><td>Name*</td><td><input type="text" name="ename" /></td></tr>
<tr><td>Date of Birth*</td><td><input type="date" name="dob" placeholder="yyyy-mm-dd" /></td></tr>
<tr><td>Date of Joining*</td><td><input type="date" name="doj" placeholder="yyyy-mm-dd" /></td></tr>
<tr><td>Post*</td><td><input type="text" name="position" ondblclick="openWinPosts()"/></td></tr>
<tr><td>Department ID*</td><td><input type="text" name="deptid" ondblclick="openWinDids()" /></td></tr>
<tr><td>Salary*</td><td><input type="number" name="salary"/></td></tr>
<tr><td>Contact Number</td><td><input type="text" name="contact"/></td></tr>
<tr><td>E-mail</td><td><input type="text" name="email"/></td></tr>
<tr><td>Address*</td><td><input type="text" name="address"/></td></tr>
<tr><td><br/></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Add" /></td></tr>
</table>
</form>
<span>* mandatory</span>
<jsp:include page="footerPage.jsp" />
<%
Integer State=0;
HttpSession session_addEmployee = request.getSession();
session_addEmployee.setAttribute("State", State);
%>
</body>
</html>