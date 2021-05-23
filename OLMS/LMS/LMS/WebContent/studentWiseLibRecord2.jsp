<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="tableCss.css"/>
<title>BOOK EXCHANGE RECORD</title>
<style type="text/css">
td{width: 150px; text-align: center;}
</style>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="staffLibMenu.jsp" />
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">STUDENT'S LIBRARY RECORD</div>
<table cellspacing="0px" class="spclTable">
<%
Connection con = null;
Connection con1 = null;
		try {
			String sid=request.getParameter("sid");
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con= DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");
			
			String query1="select * from tbl_student where sid= ?";
			PreparedStatement pst1=con.prepareStatement(query1);
			String sidtm = sid.trim();
			pst1.setString(1, sidtm);
			ResultSet rs1=pst1.executeQuery();
			
			if(rs1.next()){
				con1= DriverManager.getConnection("jdbc:odbc:dsn_lmsavs");
				
				String query2="select * from tbl_libraryIssueReturnBookRecord where sid= ?";
				PreparedStatement pst2=con1.prepareStatement(query2);
				sidtm = sid.trim();
				pst2.setString(1, sidtm);
				ResultSet rs2=pst2.executeQuery();
				if(rs2.next()){
			    	%>
					<tr><th>Book ID</th><th>Issued By</th><th>Issue Date</th><th>Return Date</th><th>Fine</th></tr>
					<%
					do{
					%>
					<tr><td><%=rs2.getString("bid") %></td><td><%=rs2.getString("issued_by") %></td><td><%=rs2.getDate("issue_date") %></td><td><%=rs2.getDate("return_date") %></td><td><%=rs2.getInt("fine") %></td></tr>
					<%
					}while(rs2.next());
			    }
			}else{
				HttpSession session_studWiseLibRecord = request.getSession();
				Integer State = Integer.parseInt( session_studWiseLibRecord.getAttribute("State").toString());
				State = 1;
				session_studWiseLibRecord.setAttribute("State", State);
				request.getRequestDispatcher("studentWiseLibRecord1.jsp").forward(request, response);
			}
		}catch(Exception e){
	        e.printStackTrace();
	        System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET studentWiseLibRecordServlet");
	        request.getRequestDispatcher("unExpectedError.jsp").forward(request, response);
	    }finally{
	    	try{
	    		if(con!=null) con.close();
	    		if(con1!=null) con1.close();
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