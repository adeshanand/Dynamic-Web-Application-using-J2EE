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
<%
HttpSession session_issueBook = request.getSession();
Integer State = Integer.parseInt( session_issueBook.getAttribute("State").toString());
if(State == 1){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;">Incorrect Student ID!</div>
	<%
}
if(State == 2){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;">Incorrect Book ID </div>
	<%
}
if(State == 3){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;">This Book is not available in Library.<br />It would have been issued.</div>
	<%
}
if(State == 4){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> You have typed an Incorrect Password.<br />If you forgot your password, consult to <br />the Administrator-Group-Member to reset it. </div>
	<%
}
if(State == 5){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> This Student already has taken 2 books.</div>
	<%
}
if(State == 6){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> This Student may has a similar book issued</div>
	<%
}
if(State == 7){
	%>
	<div style="margin-right:20px; text-align:right; font-size:20px;font-weight: 600;padding-bottom: 25px;"> Book Issued Successfully!</div>
	<%
}
%>
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
</body>
</html>