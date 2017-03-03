<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="db.JdbcUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



<%
		System.err.println("/J_LINK/Data_nhis.jsp");


		String nhisID = request.getParameter("data_nhisID");
		String tableName = request.getParameter("nhisTableName");
		String str_requestTime = request.getParameter("requestTime");

		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date_time = format.parse(str_requestTime);
		String requestTime = (String)format.format(date_time);


		
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
	
		
		
		
		try {
			
			con = JdbcUtil.getConnection();
			
			stmt = con.createStatement();

			String query = "select * from link_take_nhis."+nhisID+"_"+tableName;
			rs = stmt.executeQuery(query);
			
			System.out.println("(1)stmt");

			
			
	%>
	
	<h2>데이터 전송 시간 : <%=requestTime %></h2><br>
	<h2>데이터 전송자 ID: <%=nhisID %></h2><br>
	
	<table border="0">
		<tr>

			<!-- ---------- link가 받은 nhis 데이터 ----------- -->
			<td>

				<table border="1" cellspacing="0">
					<tr>
						<th>연결 번호</th>
						<th>STND_Y</th>
						<th>YKIHO_ID</th>
						<th>YKIHO_GUBUN_CD</th>
						<th>ORG_TYPE</th>
						<th>YKIHO_SIDO</th>
						<th>SICKBED_CNT</th>
						<th>DR_CNT</th>
						<th>CY_YN</th>
						<th>MRI_YN</th>
						<th>PER_YN</th>
					</tr>
					<%
						while (rs.next()) { //rs 를 통해 테이블 객체들의 필드값을 넘겨볼 수 있다.
								
								
							
							
					%>

					<tr>
						<td><%=rs.getInt(1) %></td>
						<td><%=rs.getInt(2) %></td>
						<td><%=rs.getInt(3) %></td>
						<td><%=rs.getInt(4) %></td>
						<td><%=rs.getInt(5) %></td>
						<td><%=rs.getInt(6) %></td>
						<td><%=rs.getInt(7) %></td>
						<td><%=rs.getInt(8) %></td>
						<td><%=rs.getInt(9) %></td>
						<td><%=rs.getInt(10) %></td>
						<td><%=rs.getInt(11) %></td>
					




						<%
							} // end while
						%>


					</tr>
				</table> <%
 	} catch (Exception e) {
 		out.println("err:" + e.toString());
 	} finally {
 		if (rs != null) {
 			rs.close();
 		} // ResultSet exit
 		if (stmt != null) {
 			stmt.close();
 		} // Statement exit
 		if (con != null) {
 			con.close();
 		} // Connection exit
 	}
		
%>
	<input type="button" name="back" value="뒤로가기" onclick="javascript:location.href='./Datalist_INDEXERtoLINK.jsp';"/>


</body>
</html>