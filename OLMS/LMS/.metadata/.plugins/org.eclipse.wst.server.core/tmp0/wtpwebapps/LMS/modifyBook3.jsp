<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
<title>MODIFY BOOK</title>
<script type="text/javascript" language="javascript">
<!-- 
function popup(){
	alert('IF BOOK ID IS MODIFIED, IT WILL BE MODIFIED EVERYWHERE! ')
}
// -->
</script>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="headLibMenu.jsp" />
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">MODIFY BOOK</div>
<form action="modifyBook" method="post" >
<table align="center">
<%
Connection con = null;
try {
	String bidm=request.getParameter("bidm");
    
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    con = DriverManager.getConnection("jdbc:odbc:dsn_lmsavs");
    
    String query="select * from tbl_book where bid = ?";
    PreparedStatement pst=con.prepareStatement(query);
    String bidmtm = bidm.trim();
    pst.setString(1, bidmtm);
    ResultSet rs=pst.executeQuery();
    
    if(rs.next()){
    	String query2="select * from tbl_libraryIssueReturnBookRecord where return_date is null AND bid= ?";
		PreparedStatement pst2=con.prepareStatement(query2);
		pst2.setString(1, bidmtm);
		ResultSet rs2=pst2.executeQuery();
		if(!(rs2.next())){
    		HttpSession session2=request.getSession();
			session2.setAttribute("bidm", bidmtm);
		%>
		<tr><td>Book ID*</td><td><input onclick="popup()" type="text" name="bid" value= "<%=rs.getString("bid") %>" /> </td></tr>
		<tr><td>Subject*</td><td><input type="text" name="subject" value= "<%=rs.getString("subject") %>" /></td></tr>
		<tr><td>Book Name*</td><td><input type="text" name="bname" value= "<%=rs.getString("bname") %>" /></td></tr>
		<tr><td>Publication*</td><td><input type="text" name="publication" value= "<%=rs.getString("publication") %>" /></td></tr>
		<tr><td>Edition*</td><td><input type="text" name="edition" value= "<%=rs.getString("edition") %>" /></td></tr>
		<tr><td>Author*</td><td><input type="text" name="author" value= "<%=rs.getString("author") %>" /></td></tr>
		<tr><td>Price*</td><td><input type="number" name="price" value= <%=rs.getInt("price") %> /></td></tr>
		<tr><td>Publishing Year*</td><td><input type="text" name="publishingyear" value= "<%=rs.getString("publishing_year") %>" /></td></tr>
		<tr><td>Vendor Name</td><td><input type="text" name="vname" value= "<%=rs.getString("vendor_name") %> "/></td></tr>
		<tr><td>Vendor Address</td><td><input type="text" name="vaddress" value= "<%=rs.getString("vendor_place") %>" /></td></tr>
		<tr><td>Bill No</td><td><input type="text" name="billno" value= "<%=rs.getString("bill_no") %>" /></td></tr>
		<tr><td>Bill Date*</td><td><input type="date" name="billdate" value= <%=rs.getDate("bill_date") %> /></td></tr>  
    <%	
		}else{
			HttpSession session_delBook = request.getSession();
			Integer State = Integer.parseInt( session_delBook.getAttribute("State").toString());
			State = 4;
			session_delBook.setAttribute("State", State);
			request.getRequestDispatcher("modifyBook22.jsp").forward(request, response);
		}
    }else{
    	HttpSession session_modifyBook = request.getSession();
		Integer State = Integer.parseInt( session_modifyBook.getAttribute("State").toString());
		State = 3;
		session_modifyBook.setAttribute("State", State);
    	request.getRequestDispatcher("modifyBook22.jsp").forward(request, response);
    }
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("ERROR OCCURED WHILE PROCESSING modifyBook3.jsp");
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
<tr><td><br/></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Modify" /></td></tr>
</table>
</form>
<span>* mandatory</span>
<jsp:include page="footerPage.jsp" />
</body>
</html>