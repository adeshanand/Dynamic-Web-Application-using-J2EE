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
		try {
			String bid=request.getParameter("bid");
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con= DriverManager.getConnection("jdbc:odbc:dsn_lmsavs");
			
			String query1="select * from tbl_book where bid= ?";
			PreparedStatement pst1=con.prepareStatement(query1);
			String bidtm = bid.trim();
			pst1.setString(1, bidtm);
			ResultSet rs1=pst1.executeQuery();
			
			if(rs1.next()){
				String query2="select * from tbl_libraryIssueReturnBookRecord where bid= ?";
				PreparedStatement pst2=con.prepareStatement(query2);
				bidtm = bid.trim();
				pst2.setString(1, bidtm);
				ResultSet rs2=pst2.executeQuery();
				if(rs2.next()){
			    	%>
					<tr><th>Student ID</th><th>Issued By</th><th>Issue Date</th><th>Return Date</th><th>Fine</th></tr>
					<%
					do{
					%>
					<tr><td><%=rs2.getString("sid") %></td><td><%=rs2.getString("issued_by") %></td><td><%=rs2.getDate("issue_date") %></td><td><%=rs2.getDate("return_date") %></td><td><%=rs2.getInt("fine") %></td></tr>
					<%
					}while(rs2.next());
			    }
		}
			else{
				HttpSession session_bookWiseLibRecord = request.getSession();
				Integer State = Integer.parseInt( session_bookWiseLibRecord.getAttribute("State").toString());
				State = 1;
				session_bookWiseLibRecord.setAttribute("State", State);
				request.getRequestDispatcher("bookWiseLibRecord1.jsp").forward(request, response);
			}
		}catch(Exception e){
	        e.printStackTrace();
	        System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET bookWiseLibRecordServlet");
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