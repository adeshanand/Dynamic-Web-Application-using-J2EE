<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
<link rel="stylesheet" type="text/css" href="tableCss.css"/>
<title>MODIFY EMPLOYEE</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="adminMenu.jsp" />
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 25px;">SEARCH EMPLOYEE</div>
<form method="post" >
<table align="center">
<tr><td><select name="choice"><option value="eid" >Employee ID</option><option value="ename" >Employee Name</option><option value="post" >Post</option><option value="dept_id" >Department ID</option></select></td><td><input type="text" name="value" /></td><td><input type="submit" value="Search" /></td></tr>
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
    
    String query="select * from tbl_employee where "+ choice +" = ?";
    PreparedStatement pst=con.prepareStatement(query);
    String valuetm = value.trim();
    pst.setString(1, valuetm);
    ResultSet rs=pst.executeQuery();
    
    if(rs.next()){
    	%>
    	<tr><th colspan="12">SEARCH RESULTS</th></tr>
		<tr><th>ID</th><th>Name</th><th>Date Of Birth</th><th>Date Of Joining</th><th>Post</th><th>Department ID</th><th>Salary</th><th>Contact No</th><th>E-mail</th><th>Address</th></tr>
		<%
		do{
		%>
		<tr><td><%=rs.getString("eid") %></td><td><%=rs.getString("ename") %></td><td><%=rs.getDate("dob") %></td><td><%=rs.getDate("doj") %></td><td><%=rs.getString("post") %></td><td><%=rs.getString("dept_id") %></td><td><%=rs.getInt("salary") %></td><td><%=rs.getString("contact_no") %></td><td><%=rs.getString("email") %></td><td><%=rs.getString("address") %></td></tr>
		<%
		}while(rs.next());
		%>   
    <%	
    }else{
    	HttpSession session_modifyEmployee = request.getSession();
		Integer State = Integer.parseInt( session_modifyEmployee.getAttribute("State").toString());
		State = 2;
		session_modifyEmployee.setAttribute("State", State);
    	request.getRequestDispatcher("modifyEmployee22.jsp").forward(request, response);
    }
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("ERROR OCCURED WHILE PROCESSING modifyEmployee222.jsp");
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
<form action="modifyEmployee3.jsp" method="post" >
<table align="center">
<tr><td>Type Employee ID</td><td><input type="text" name="eidm" placeholder="Case Sensitive" title="Choose Employee ID from the above table and type its ID here to modify record" /></td><td><input type="submit" value="Go" /></td></tr>
</table>
</form>
<jsp:include page="footerPage.jsp" />
</body>
</html>