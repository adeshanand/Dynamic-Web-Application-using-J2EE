<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="tableCss.css"/>
<title>SHOW ALL STUDENT</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="adminMenu.jsp" />
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">STUDENT RECORD</div>
<table cellspacing="0px" class="spclTable" >
<%
Connection con = null;
try {
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    con = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");
    String query="select * from tbl_student";
    PreparedStatement pst=con.prepareStatement(query);
    ResultSet rs=pst.executeQuery();
    if(rs.next()){
    	%>
		<tr><th>ID</th><th>Name</th><th>Date Of Birth</th><th>Date Of Admission</th><th>Guardian's Name</th><th>Course</th><th>Session</th><th>Contact No</th><th>E-mail</th><th>Address</th></tr>
		<%
		
		do{
		%>
		<tr><td><%=rs.getString("sid") %></td><td><%=rs.getString("sname") %></td><td><%=rs.getDate("dob") %></td><td><%=rs.getDate("doa") %></td><td><%=rs.getString("guardian_name") %></td><td><%=rs.getString("course") %></td><td><%=rs.getString("session") %></td><td><%=rs.getString("contact_no") %></td><td><%=rs.getString("email") %></td><td><%=rs.getString("address") %></td></tr>
		<%
		}while(rs.next());
		%>   
    <%	
    }
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("ERROR OCCURED WHILE PROCESSING showAllStudent.jsp");
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
<jsp:include page="footerPage.jsp" />
</body>
</html>