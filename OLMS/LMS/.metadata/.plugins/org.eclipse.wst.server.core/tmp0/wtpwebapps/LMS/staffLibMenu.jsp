<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="menuCss.css"/>
</head>
<body>
<div id="menu_temp">
<div id="menu_container">
     <ul id="menu">
     <li class="menu_item"><a class="a_menu_item" href="staffLibHome.jsp">Home</a></li>
     <li class="menu_item"><a class="a_menu_item" href="issueBook.jsp">Issue</a></li>
     <li class="menu_item"><a class="a_menu_item" href="submitBook.jsp">Submit</a></li>
     <li class="menu_item"><a class="a_menu_item" href="showLibraryRecord.jsp">Record</a>
     	<ul class="drop_list">
     		<li class="drop_list_item"><a class="a_menu_item_list" href="showLibraryRecord.jsp">All Record</a></li>
     		<li class="drop_list_item"><a class="a_menu_item_list" href="dateWiseLibRecord.jsp">Daily Record</a></li>
     		<li class="drop_list_item"><a class="a_menu_item_list" href="bookWiseLibRecord.jsp">Book Specific Record</a></li>
     		<li class="drop_list_item"><a class="a_menu_item_list" href="studentWiseLibRecord.jsp">Student Specific Record</a></li>
     	</ul>
     </li>
     <li class="menu_item"><a class="a_menu_item" href="changeSelfPassword1.jsp">Password</a>
     	<ul class="drop_list">
     		<li class="drop_list_item"><a class="a_menu_item_list" href="changeSelfPassword1.jsp">Self</a></li>
     		<li class="drop_list_item"><a class="a_menu_item_list" href="studentPasswordChange.jsp">Student</a></li>
     	</ul>
     </li>
     </ul>
</div>
</div>
</body>
</html>