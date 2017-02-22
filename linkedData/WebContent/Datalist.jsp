<%@page import="java.util.Date"%>
<%@page import="java.sql.Time"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="db.JdbcUtilUpload"%>
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	
		Connection conn = JdbcUtilUpload.getUploadConnection();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();

			String query = "select * from UploadFileInfo";
			rs = stmt.executeQuery(query);
	%>

		<table border="1" cellspacing="0">
			<tr>
				<td>업로드 시간</td>
				<td>제목</td>
				<td>파일 이름 </td>
				<td>연구자 ID</td>
				<td>연구자 이름</td>
				<th>건강보험공단</th>
				<th>통계청</th>
			</tr>
			<%
				while (rs.next()) { //rs 를 통해 테이블 객체들의 필드값을 넘겨볼 수 있다.
					String time = rs.getString(1);
					Date date_time = format.parse(time);
					String format_time = (String)format.format(date_time);
					String filename = rs.getString(3);
					String uploadername = rs.getString(7);
					int nhis = rs.getInt(8);
					int stat = rs.getInt(9);
					int nhis_send = rs.getInt(10);
					int stat_send = rs.getInt(11);
					System.out.println(nhis_send);
			%>

			<tr>
				<td><%=format_time %></td>
				<td><%=rs.getString(2)%></td>
				<td><%=filename%></td>
				<td><%=rs.getString(6)%></td>
				<td><%=uploadername%></td>
				
				<form action="datasend_nhis" method="POST">
				<input type="hidden" name="filename" value="<%=filename %>"/>
				<input type="hidden" name="uploadername" value="<%=uploadername %>"/>
				<input type="hidden" name="nhis_send" value="<%=nhis_send %>"/>
				<input type="hidden" name="stat_send" value="<%=stat_send %>"/>
				
				<%if(nhis == 1){ %>
				<td><input type="submit" value="식별자 전송"/></td>
				<%}else{ %>
				<td><input type="submit" value="연계 미신청" disabled="disabled"/></td><%} %>
				</form>
				
				<form action="datasend_stat" method="POST">
				<input type="hidden" name="filename" value="<%=filename %>"/>
				<input type="hidden" name="uploadername" value="<%=uploadername %>"/>
				<input type="hidden" name="nhis_send" value="<%=nhis_send %>"/>
				<input type="hidden" name="stat_send" value="<%=stat_send %>"/>
				
				<%if(stat == 1){ %>
				<td><input type="submit" value="식별자 전송"/></td>
				<%}else{ %>
				<td><input type="submit" value="연계 미신청" disabled="disabled"/></td><%} %>
				</form>
			</tr>
			<%
				} // end while
			%>
		</table>
	<%
		rs.close(); // ResultSet exit
			stmt.close(); // Statement exit
			conn.close(); // Connection exit
		} catch (Exception e) {
			out.println("err:" + e.toString());
		}
	%>

</body>
</html>