<%@page import="java.util.Date"%>
<%@page import="java.sql.Time"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="db.JdbcUtilUpload"%>
<%@page import="java.sql.Connection"%>
<%@page import="vo.MemberR"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������ ��û ���</title>
</head>
<body>

	<%
		String Rid = ((MemberR) request.getSession().getAttribute("Rid")).getRId();
		String Rname = ((MemberR) request.getSession().getAttribute("Rid")).getRName();

		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	
		Connection conn = JdbcUtilUpload.getUploadConnection();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();

			String query = "select * from uploadFile.UploadFileInfo";
			rs = stmt.executeQuery(query);

	%>
<h1 align="center">������ ��û ������ ���</h1><br>
		<table id = "tb" border="1" cellspacing="0" style="margin:0 auto; text-align :center;">
	<tr>
	<td>

				<table border="1" cellspacing="0">
			<tr>
				<th>��û �ð�</th>
				<th>����</th>
				<th>���� �̸� </th>
				<th>������ ID</th>
				<th>������ �̸�</th>
				<th>���� ������</th>
			</tr>
			<%
				while (rs.next()) { //rs �� ���� ���̺� ��ü���� �ʵ尪�� �Ѱܺ� �� �ִ�.
					String time = rs.getString(1);
					Date date_time = format.parse(time);
					String format_time = (String)format.format(date_time);
					String filename = rs.getString(3);
					String uploaderid = rs.getString(6);
					String uploadername = rs.getString(7);
					int data_link = rs.getInt(13);
					String link_data_tablename = rs.getString(14);
				
			%>
			<tr>
				<td><%=format_time %></td>
				<td><%=rs.getString(2)%></td>
				<td><%=filename%></td>
				<td><%=uploaderid%></td>
				<td><%=uploadername%></td>
				
				
				<%
				
				if(data_link == 1){
				%>
				<form action="link_data_view.jsp" method="post">
				<input type="hidden" name="link_data_tablename" value="<%=link_data_tablename %>"/>
				<td><input type="submit" value="������ ����"/></td>
				<%}else{ %>
				<td><input type="submit" value="������ �̿���" disabled="disabled" /></td>
				<%}
				}
		} catch (Exception e) {
			out.println("err:" + e.toString());
		}finally{
			if(rs != null){rs.close();} // ResultSet exit
			if(stmt != null){stmt.close();} // Statement exit
			if(conn != null){conn.close();} // Connection exit
		}
			%>
				</form>
			</tr>
			</table>
			</td>
			</tr>
			</table>
			
				<br><center><input type="button" name="back" value="�ڷΰ���" onclick="javascript:location.href='./AfterLoginR.jsp';"/></center>
			
			
			
</body>
</html>