<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
<title>SEARCH EMPLOYEE</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="adminMenu.jsp" />
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">EMPLOYEE DETAILS</div>
<%
Connection con = null;
try {
	String eids=request.getParameter("eids");
    
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    con = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");
    
    String query="select * from tbl_employee where eid = ?";
    PreparedStatement pst=con.prepareStatement(query);
    String eidstm = eids.trim();
    pst.setString(1, eidstm);
    ResultSet rs=pst.executeQuery();
    
    if(rs.next()){
    	%>
    	<table cellspacing="0px" class="spclTable2">
    	<tr><td>ID</td><td><%=rs.getString("eid") %> </td></tr>
		<tr><td>Name</td><td><%=rs.getString("ename") %> </td></tr>
		<tr><td>Date of Birth</td><td><%=rs.getDate("dob") %> </td></tr>
		<tr><td>Date of Joining</td><td><%=rs.getDate("doj") %> </td></tr>
		<tr><td>Post</td><td><%=rs.getString("post") %> </td></tr>
		<tr><td>Department ID</td><td><%=rs.getString("dept_id") %> </td></tr>
		<tr><td>Salary</td><td><%=rs.getInt("salary") %> </td></tr>
		<tr><td>Contact Number</td><td><%=rs.getString("contact_no") %> </td></tr>
		<tr><td>E-mail</td><td><%=rs.getString("email") %></td></tr>
		<tr><td>Address</td><td><%=rs.getString("address") %></td></tr>
		</table>
	<%	
    }else{
    	HttpSession session_searchEmployee = request.getSession();
		Integer State = Integer.parseInt(session_searchEmployee.getAttribute("State").toString());
		State = 3;
		session_searchEmployee.setAttribute("State", State);
    	request.getRequestDispatcher("employeeFound11.jsp").forward(request, response);
    }
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("ERROR OCCURED WHILE PROCESSING employeeFound2.jsp");
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
<jsp:include page="footerPage.jsp" />
</body>
</html>