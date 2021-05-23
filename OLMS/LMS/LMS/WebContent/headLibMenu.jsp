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
     <li class="menu_item"><a class="a_menu_item" href="headLibHome.jsp">Home</a></li>
     <li class="menu_item"><a class="a_menu_item" href="showAllBook.jsp">Book</a>
     	<ul class="drop_list">
     		<li class="drop_list_item"><a class="a_menu_item_list" href="addBook.jsp">Add</a></li>
     		<li class="drop_list_item"><a class="a_menu_item_list" href="deleteBook.jsp">Delete</a></li>
     		<li class="drop_list_item"><a class="a_menu_item_list" href="modifyBook1.jsp">Modify</a></li>
     		<li class="drop_list_item"><a class="a_menu_item_list" href="searchBook.jsp">Search</a></li>
     		<li class="drop_list_item"><a class="a_menu_item_list" href="showAllBook.jsp">Show All</a></li>
     	</ul>
     </li>
     <li class="menu_item"><a class="a_menu_item" href="changeSelfPassword1.jsp">Password</a></li>
     </ul>
</div>
</div>
</body>
</html>