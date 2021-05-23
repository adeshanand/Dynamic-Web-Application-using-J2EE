<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
<title>ADD BOOK</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="headLibMenu.jsp" />
<%
HttpSession session_addBook = request.getSession();
Integer State = Integer.parseInt( session_addBook.getAttribute("State").toString());
if(State == 1){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> Fill all mandatory fields first. </div>
	<%
}
if(State == 2){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> Book ID you entered is not available. </div>
	<%
}
if(State == 3){
	%>
	<div style="margin-right:20px; text-align:right; font-size:20px;font-weight: 600;padding-bottom: 25px;"> Book Added Successfully! </div>
	<%
}
if(State == 4){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> Price can never less than 0. </div>
	<%
}
if(State == 5){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> After Choosing Yes you have to enter a valid number. </div>
	<%
}
if(State == 6){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> Number of book(s) must be greater than or equal to 1. </div>
	<%
}
%>
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">ADD BOOK</div>
<form action="addBook" method="post" >
<table align="center">
<tr><td>Book ID*</td><td><input type="text" name="bid" /></td></tr>
<tr><td>Subject*</td><td><input type="text" name="subject" /></td></tr>
<tr><td>Book Name*</td><td><input type="text" name="bname" /></td></tr>
<tr><td>Publication*</td><td><input type="text" name="publication"/></td></tr>
<tr><td>Edition*</td><td><input type="text" name="edition"/></td></tr>
<tr><td>Author*</td><td><input type="text" name="author"/></td></tr>
<tr><td>Price*</td><td><input type="number" name="price"/></td></tr>
<tr><td>Publishing Year*</td><td><input type="text" name="publishingyear" placeholder="yyyy" /></td></tr>
<tr><td>Vendor Name</td><td><input type="text" name="vname"/></td></tr>
<tr><td>Vendor Address</td><td><input type="text" name="vaddress"/></td></tr>
<tr><td>Bill No</td><td><input type="text" name="billno"/></td></tr>
<tr><td>Bill Date*</td><td><input type="date" name="billdate" placeholder="yyyy-mm-dd" /></td></tr>
<tr><td><label for="choiceYN">Choose <b>Yes</b> for adding multiple books at a time.<select id="choiceYN" name="choiceYN"><option value="No" selected="selected">No</option><option value="Yes" >Yes</option></select></label></td><td><input type="number" name="qty" placeholder="No of Books" /></td></tr>
<tr><td><br/></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Add" /></td></tr>
</table>
</form>
<span style="margin-left: 20px;">* mandatory</span>
<jsp:include page="footerPage.jsp" />
</body>
</html>