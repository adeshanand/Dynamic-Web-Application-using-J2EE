<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
<title>ISSUE BOOK</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="staffLibMenu.jsp" />
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">ISSUE BOOK</div>
<form action="issueBook"  method="post" >
<table align="center">
<tr><td>Student ID</td><td><input type="text" name="sid" /></td></tr>
<tr><td>Book ID</td><td><input type="text" name="bid" /></td></tr> 
<tr><td>Student Confirmation</td><td><input type="password" name="confpswd" /></td></tr>  
<tr><td><br/></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Issue" /></td></tr>
</table>    
</form>
<jsp:include page="footerPage.jsp" />
<%
Integer State=0;
HttpSession session_issueBook = request.getSession();
session_issueBook.setAttribute("State", State);
%>
</body>
</html>