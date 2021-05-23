<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
<link rel="stylesheet" type="text/css" href="tableCss.css"/>
<title>SEARCH STUDENT</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="adminMenu.jsp" />
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 25px;">SEARCH STUDENT</div>
<form action="studentFound1.jsp" method="post" >
<table align="center">
<tr><td><select name="choice"><option value="sid" >Student ID</option><option value="sname" >Student Name</option><option value="course" >Course</option><option value="session" >Session</option></select></td><td><input type="text" name="value" /></td><td><input type="submit" value="Search" /></td></tr>
</table>
</form>
<br />
<table cellspacing="0px" class="spclTable">
<%
Connection con = null;
try {
	String choice=request.getParameter("choice");
	String value=request.getParameter("value");
	
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    con = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");
    
    String query="select * from tbl_student where "+ choice +" = ?";
    PreparedStatement pst=con.prepareStatement(query);
    String valuetm = value.trim();
    pst.setString(1, valuetm);
    ResultSet rs=pst.executeQuery();
    
    if(rs.next()){
    	%>
    	<tr><th colspan="12">SEARCH RESULTS</th></tr>
		<tr><th>ID</th><th>Name</th><th>Date Of Birth</th><th>Date Of Admission</th><th>Guardian's Name</th><th>Course</th><th>Session</th><th>Contact No</th><th>E-mail</th><th>Address</th></tr>
		<%
		do{
		%>
		<tr><td><%=rs.getString("sid") %></td><td><%=rs.getString("sname") %></td><td><%=rs.getDate("dob") %></td><td><%=rs.getDate("doa") %></td><td><%=rs.getString("guardian_name") %></td><td><%=rs.getString("course") %></td><td><%=rs.getString("session") %></td><td><%=rs.getString("contact_no") %></td><td><%=rs.getString("email") %></td><td><%=rs.getString("address") %></td></tr>
		<%
		}while(rs.next());
			
    }else{
    	HttpSession session_searchStudent = request.getSession();
		Integer State = Integer.parseInt( session_searchStudent.getAttribute("State").toString());
		State = 1;
		session_searchStudent.setAttribute("State", State);
    	request.getRequestDispatcher("searchStudent1.jsp").forward(request, response);
    }
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("ERROR OCCURED WHILE PROCESSING studentFound.jsp");
		request.getRequestDispatcher("unExpectedError.jsp").forward(request, response);
    }finally{
    	try{
    		if(con!=null) con.close();
    	}catch(Exception e){
	        e.printStackTrace();
	        System.out.println("CONNECTION TO THE DATABASE COULD NOT BE TERMINATED.");
			request.getRequestDispatcher("unExpectedError.jsp").forward(request, response);
    	}
    }
%>
</table>
<br />
<form action="studentFound2.jsp" method="post" >
<table align="center">
<tr><td>Type Student ID</td><td><input type="text" name="sids" placeholder="Case Sensitive" title="Choose Student ID from the above table and type its ID here to show it" /></td><td><input type="submit" value="Go" /></td></tr>
</table>
</form>
<jsp:include page="footerPage.jsp" />
</body>
</html>