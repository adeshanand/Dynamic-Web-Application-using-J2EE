<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="headerCss.css" />
</head>
<body>
<!--<div id="first">Online Library Management System</div><br />-->
<div id="head_container">
<div id="logo"><img src="logo.png"/></div>
<div id="banner">
  <div id="header21">Lalit Narayana Mishra College of Business Management</div>
  <div id="header22">(Affiliated and Approved by UGC & All India Council for Technical Education)</div>
  <div id="header23">An Autonomous College under B. R. Ambedkar Bihar University</div>
  <div id="header24">Muzaffarpur</div>
</div>
</div>
<div id="second"> 
<%=session.getAttribute("post").toString() %> <br />
<%=session.getAttribute("ename").toString() %><br /> <br />
<a href="logout" onclick="return confirm('Do you want to Logout?')">Logout</a>
</div>
</body>
</html>