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
			%><tr>
				<td><%=rs.getString(1)%></td>
				<td><%=rs.getString(2)%></td>
				<td><%=rs.getString(3)%></td>
				<td><%=rs.getString(6)%></td>
				<td><%=rs.getString(7)%></td>
				<td><%=rs.getInt(8)%></td>
				<td><%=rs.getInt(9)%></td>
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