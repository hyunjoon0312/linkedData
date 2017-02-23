<%@page import="db.JdbcUtil"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Time"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>


<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		//String NEOKid = ((MemberNEOK)request.getSession().getAttribute("NEOKid")).getNEOKId();
		//String NEOKname = ((MemberNEOK)request.getSession().getAttribute("NEOKid")).getNEOKName();

	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	
		Connection conn = JdbcUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();

			String query = "select * from nhis_take_data.nhis_take_data_info";
			rs = stmt.executeQuery(query);
	%>

		<table border="1" cellspacing="0">
			<tr>
				<th>��û �ð�</th>
				<th>NECA��û�� ID</th>
				<th>NECA��û�� �̸�</th>
				<th>��û ������ ��</th>
				<th>���� ���� ������ ��</th>
				<th>NECA INDEXER</th>
				<th>������ ���� ����</th>
			</tr>
			<%
				while (rs.next()) { //rs �� ���� ���̺� ��ü���� �ʵ尪�� �Ѱܺ� �� �ִ�.
					String time = rs.getString(1);
					Date date_time = format.parse(time);
					String format_time = (String)format.format(date_time);
					String neokID = rs.getString(3);
					String neokName = rs.getString(4);
					String tableName = rs.getString(2);
					
					int send_indexer = rs.getInt(7);
			%>

			<tr>
				<td><%=format_time %></td>
				<td><%=neokID%></td>
				<td><%=neokName%></td>
				<td><%=rs.getInt(5) %></td>
				<td><%=rs.getInt(6)%></td>
				
				<form action="datasend_NHIStoIN" method="POST">
				<input type="hidden" name="tableName" value="<%=tableName %>"/>
				<input type="hidden" name="send_indexer" value="<%=send_indexer %>"/>

				<td><input type="submit" value="����"/></td>
				</form>
				
			
			<%
				if(send_indexer == 1){
			%>
			<td>���� �Ϸ�</td>
			
			<%}else{ %>
			<td>���� �ʿ�</td>
			<%}
				} // end while
			%>
			
			
			</tr>
		</table>
	<%
		
		} catch (Exception e) {
			out.println("err:" + e.toString());
		}finally{
			if(rs != null){rs.close();} // ResultSet exit
			if(stmt != null){stmt.close();} // Statement exit
			if(conn != null){conn.close();} // Connection exit
		}
	%>




</body>
</html>