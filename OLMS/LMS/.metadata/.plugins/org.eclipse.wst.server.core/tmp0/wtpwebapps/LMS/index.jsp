<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="indexLoginCss.css" />
<title>WELCOME TO OLMS</title>
</head>
<body>
<div id="wrapper">
<div id="head_container">
<div id="logo"><img src="logo.png"/></div>
<div id="banner">
  <div id="header21">Lalit Narayana Mishra College of Business Management</div>
  <div id="header22">(Affiliated and Approved by UGC & All India Council for Technical Education)</div>
  <div id="header23">An Autonomous College under B. R. Ambedkar Bihar University</div>
  <div id="header24">Muzaffarpur</div>
</div>
</div>
<div id="body_container">
<div id="login_panel">
<form action="login" method="post">
<table align="center">
<tr><td class="lgt">User ID</td><td><input class="lgi" type="text" name="uid" /></td></tr>
<tr><td class="lgt">Password</td><td><input class="lgi" type="password" name="pswd" /></td></tr>
<tr><td><br /></td></tr>
<tr><td colspan="2" align="center"><input id="lgs" type="submit" value="Login" /></td></tr>
</table>
</form>
</div>
</div>
<div id="footer"><a href="mailto:lnmcbm@sify.com?subject=Feedback & body= L N Mishra Rocks!!!">&copy L N Mishra College of Business Management, Bhagwanpur, Muzaffarpur[BIHAR]</a> </div>
</div>
<%
Integer errorState=0;
HttpSession session_login = request.getSession();
session_login.setAttribute("errorState", errorState);
%>
</body>
</html>