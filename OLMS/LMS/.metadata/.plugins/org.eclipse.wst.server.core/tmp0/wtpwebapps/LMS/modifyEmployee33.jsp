<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
<title>MODIFY EMPLOYEE</title>
<script type="text/javascript" language="javascript">
<!-- 
function popup(){
	alert('IF EMPLOYEE ID IS MODIFIED, IT WILL BE MODIFIED EVERYWHERE! ')
}
// -->
</script>
<script type="text/javascript" language="javascript">
<!-- 
function openWinPosts(){
	w1=window.open('posts.html',height=120,'Posts',"toolbar=no, menubar=no, width=250px, height=551px, resizable=no");
}
// -->
</script>
<script type="text/javascript" language="javascript">
<!-- 
function openWinDids(){
	w1=window.open('dids.html',height=120,'Dids',"toolbar=no, menubar=no, width=250px, height=551px, resizable=no");
}
// -->
</script>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="adminMenu.jsp" />
<%
HttpSession session_modifyEmployee = request.getSession();
Integer State = Integer.parseInt( session_modifyEmployee.getAttribute("State").toString());
if(State == 7){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> Fill all mandatory fields first. </div>
	<%
}
if(State == 8){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> Employee ID you entered is not available. </div>
	<%
}
if(State == 6){
	%>
	<div style="margin-right:20px; text-align:right; font-size:20px;font-weight: 600;padding-bottom: 25px;"> Employee Modified Successfully! </div>
	<%
	request.getRequestDispatcher("modifyEmployee11.jsp").forward(request, response);
}
if(State == 5){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> Salary can never less than 0. </div>
	<%
}
%>
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">MODIFY EMPLOYEE</div>
<form action="modifyEmployee" method="post" name="empFrm">
<table align="center">
<%
Connection con = null;
try {
	HttpSession session2=request.getSession();
	String eidm = session2.getAttribute("eidm").toString();
    
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    con = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");
    
    String query="select * from tbl_employee where eid = ?";
    PreparedStatement pst=con.prepareStatement(query);
    String eidmtm = eidm.trim();
    pst.setString(1, eidmtm);
    ResultSet rs=pst.executeQuery();
    
    if(rs.next()){
    	
    	%>
		<tr><td>ID*</td><td><input onclick="popup()" type="text" name="eid" value="<%=rs.getString("eid") %>" /></td></tr>
		<tr><td>Name*</td><td><input type="text" name="ename" value="<%=rs.getString("ename") %>" /></td></tr>
		<tr><td>Date of Birth*</td><td><input type="date" name="dob" value="<%=rs.getDate("dob") %>" /></td></tr>
		<tr><td>Date of Joining*</td><td><input type="date" name="doj" value="<%=rs.getDate("doj") %>" /></td></tr>
		<tr><td>Post*</td><td><input type="text" name="position" value="<%=rs.getString("post") %>" ondblclick="openWinPosts()" /></td></tr>
		<tr><td>Department ID*</td><td><input type="text" name="deptid" value="<%=rs.getString("dept_id") %>" ondblclick="openWinDids()" /></td></tr>
		<tr><td>Salary*</td><td><input type="text" name="salary" value=<%=rs.getString("salary") %> /></td></tr>
		<tr><td>Contact Number</td><td><input type="text" name="contact" value="<%=rs.getString("contact_no") %>" /></td></tr>
		<tr><td>E-mail</td><td><input type="text" name="email" value="<%=rs.getString("email") %>" /></td></tr>
		<tr><td>Address*</td><td><input type="text" name="address" value="<%=rs.getString("address") %>" /></td></tr>  
    <%	
    }else{
    	session_modifyEmployee = request.getSession();
		State = Integer.parseInt( session_modifyEmployee.getAttribute("State").toString());
		State = 3;
		session_modifyEmployee.setAttribute("State", State);
    	request.getRequestDispatcher("modifyEmployee22.jsp").forward(request, response);
    }
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET modifyEmployee33");
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