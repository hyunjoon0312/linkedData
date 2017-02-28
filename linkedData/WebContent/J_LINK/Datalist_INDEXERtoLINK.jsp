<%@page import="vo.MemberLINK"%>
<%@page import="db.JdbcUtil"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Time"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
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
		String LINKid = ((MemberLINK) request.getSession().getAttribute("LINKid")).getLINKId();
		String LINKname = ((MemberLINK) request.getSession().getAttribute("LINKid")).getLINKName();

		
		Connection conn1 = JdbcUtil.getConnection();
		Statement stmt1 = null;
		ResultSet rs1 = null;
		
		
		try {
			stmt1 = conn1.createStatement();

			String query = "select * from link_take_checklist.link_take_checklist_info";
			rs1 = stmt1.executeQuery(query);
			
			System.out.println("(1)stmt1");

	%>
	<table border="0">
		<tr>

			<!-- ---------- link가 받은 데이터 info ----------- -->
			<td>

				<table border="1" cellspacing="0">
					<tr>
						<th>전송 시간</th>
						<th>대조표 전송자 ID</th>
						<th>대조표 전송자 이름</th>
						<th>NHIS 데이터</th>
						<th>STAT 데이터</th>
						<th>데이터 연계 및 저장</th>
					</tr>
					<%
						while (rs1.next()) { //rs 를 통해 테이블 객체들의 필드값을 넘겨볼 수 있다.
								String requestTime = rs1.getString(1);
								String indexerID = rs1.getString(2);
								String indexerName = rs1.getString(3);
								String tableName = rs1.getString(4);
								int nhis_receive = rs1.getInt(5);
								int stat_receive = rs1.getInt(6);
								int data_link = rs1.getInt(7);
								String nhisID = rs1.getString(8);
								String statID = rs1.getString(9);
								
					%>

					<tr>
						<td><%=requestTime%></td>
						<td><%=indexerID%></td>
						<td><%=indexerName%></td>

						<form action="./J_LINK/data_nhis.jsp_" method="POST">
							<input type="hidden" name="nhisID" value="<%=nhisID%>" />

							<%
								if (nhis_receive == 1) {
							%>
							<td><input type="submit" value="데이터보기" /></td>
							<%
								} else {
							%>
							<td><input type="submit" value="데이터 없음" disabled="disabled" /></td>
							<%
								}
							%>
						</form>



						<form action="./J_LINK/data_nhis.jsp_" method="POST">
							<input type="hidden" name="statID" value="<%=statID%>" />

							<%
								if (stat_receive == 1) {
							%>
							<td><input type="submit" value="데이터보기" /></td>
							<%
								} else {
							%>
							<td><input type="submit" value="데이터 없음" disabled="disabled" /></td>
							<%
								}
							%>
						</form>

					
						// 데이터 연계

						<form action="data_link" method="POST">
							<input type="hidden" name="tableName" value="<%=tableName%>" />

							<%
								if (nhis_receive == 1 && stat_receive == 1) {
							%>
							<td><input type="submit" value="데이터연계" /></td>
							<%
								} else if(nhis_receive == 1 && stat_receive == 0){
							%>
							<td><input type="submit" value="STAT 데이터 없음" disabled="disabled" /></td>
							<%
								} else if(nhis_receive == 0 && stat_receive == 1){
								%>
							<td><input type="submit" value="NHIS 데이터 없음" disabled="disabled" /></td>
								<%	
								}
							%>
						</form>


						<%
							} // end while
						%>


					</tr>
				</table> <%
 	} catch (Exception e) {
 		out.println("err:" + e.toString());
 	} finally {
 		if (rs1 != null) {
 			rs1.close();
 		} // ResultSet exit
 		if (stmt1 != null) {
 			stmt1.close();
 		} // Statement exit
 		if (conn1 != null) {
 			conn1.close();
 		} // Connection exit
 	}
 %>


			</td>
		</tr>
	</table>

</body>
</html>