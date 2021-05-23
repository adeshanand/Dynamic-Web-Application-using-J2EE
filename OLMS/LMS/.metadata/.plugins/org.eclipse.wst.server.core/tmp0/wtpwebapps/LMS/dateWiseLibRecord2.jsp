<%@ page language="java" import="java.sql.*" import="java.text.SimpleDateFormat" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="tableCss.css"/>
<title>BOOK EXCHANGE RECORD</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="staffLibMenu.jsp" />
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">DAILY LIBRARY RECORD</div>
<table cellspacing="0px" class="spclTable">
<%
Connection con = null;
		try {
			String dateInString = request.getParameter("date");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(formatter.parse(dateInString).getTime());
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con= DriverManager.getConnection("jdbc:odbc:dsn_lmsavs");
				
				String query="select * from tbl_libraryIssueReturnBookRecord where issue_date=? or return_date=?";
				PreparedStatement pst=con.prepareStatement(query);
				pst.setDate(1, date);
				pst.setDate(2, date);
				ResultSet rs=pst.executeQuery();
				if(rs.next()){
			    	%>
					<tr><th>Student ID</th><th>Book ID</th><th>Issued By</th><th>Issue Date</th><th>Return Date</th><th>Fine</th></tr>
					<%
					do{
					%>
					<tr><td><%=rs.getString("sid") %></td><td><%=rs.getString("bid") %></td><td><%=rs.getString("issued_by") %></td><td><%=rs.getDate("issue_date") %></td><td><%=rs.getDate("return_date") %></td><td><%=rs.getInt("fine") %></td></tr>
					<%
					}while(rs.next());
			    }else{
			    	HttpSession session_dateWiseLibRecord = request.getSession();
					Integer State = Integer.parseInt( session_dateWiseLibRecord.getAttribute("State").toString());
					State = 1;
					session_dateWiseLibRecord.setAttribute("State", State);
					request.getRequestDispatcher("dateWiseLibRecord1.jsp").forward(request, response);
			    }
		}catch(Exception e){
	        e.printStackTrace();
	        System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET dateWiseLibRecordServlet");
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