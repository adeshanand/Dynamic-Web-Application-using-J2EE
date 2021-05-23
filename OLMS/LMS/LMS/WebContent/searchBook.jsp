<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
<title>SEARCH BOOK</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="headLibMenu.jsp" />
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">SEARCH BOOK</div>
<form action="bookFound.jsp" method="post" >
<table align="center">
<tr><td><select name="choice"><option value="bid" >Book ID</option><option value="subject" >Subject</option><option value="bname" >Book Name</option><option value="publication" >Publication</option><option value="author" >Author</option></select></td><td><input type="text" name="value" /></td></tr>
<tr><td><br/></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Search" /></td></tr>
</table>
</form>
<br />
<jsp:include page="footerPage.jsp" />
<%
Integer State=0;
HttpSession session_searchBook = request.getSession();
session_searchBook.setAttribute("State", State);
%>
</body>
</html>