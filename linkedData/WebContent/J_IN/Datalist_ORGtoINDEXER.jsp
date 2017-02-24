<%@page import="vo.MemberIN"%>
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
	String INid = ((MemberIN)request.getSession().getAttribute("INid")).getINId();
	String INname = ((MemberIN)request.getSession().getAttribute("INid")).getINName();
	
	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	
		Connection conn1 = JdbcUtil.getConnection();
		Statement stmt1 = null;
		ResultSet rs1 = null;

		try {
			stmt1 = conn1.createStatement();

			String query = "select * from stat_take_data.stat_take_data_info";
			rs1 = stmt1.executeQuery(query);
	%>
		<table border="0">
		<tr>
		
		<!-- ---------- NHIS 데이터 info ----------- -->
		<td>
		
<table border="1" cellspacing="0">
			<tr>
				<th>요청 시간</th>
				<th>NHIS요청자 ID</th>
				<th>NHIS요청자 이름</th>
				<th>NHIS</th>
				<th>연계 플랫폼</th>
			</tr>
			<%
				while (rs1.next()) { //rs 를 통해 테이블 객체들의 필드값을 넘겨볼 수 있다.
					String time = rs1.getString(1);
					Date date_time = format.parse(time);
					String format_time = (String)format.format(date_time);
					String neokID = rs1.getString(3);
					String neokName = rs1.getString(4);
					String tableName = rs1.getString(2);
					
					int send_indexer = rs1.getInt(7);
					int receive_indexer = rs1.getInt(8);
					int send_link = rs1.getInt(9);
			%>

			<tr>
				<td><%=format_time %></td>
				<td><%=neokID%></td>
				<td><%=neokName%></td>
				<td><%=rs1.getInt(5) %></td>
				<td><%=rs1.getInt(6)%></td>
				
				<form action="datasend_STATtoIN" method="POST">
				<input type="hidden" name="tableName" value="<%=tableName %>"/>
				<input type="hidden" name="neokID" value="<%=neokID %>"/>
				<input type="hidden" name="STATid" value="<%=STATid %>"/>
				<input type="hidden" name="STATname" value="<%=STATname %>"/>
				<input type="hidden" name="send_indexer" value="<%=send_indexer %>"/>

				<%if(send_indexer == 0){ %>
				<td><input type="submit" value="전송"/></td>
				<%}else{ %>
				<td><input type="submit" value="전송완료" disabled="disabled"/></td>
				<%} %>				
				</form>
				
				<!-- indexer에게 연결번호 받고 liker에게 보내지 않았을때만 활성화 -->
				<form action="datasend_STATtoLINK" method="POST">
				
				
				
				<%if(receive_indexer == 0 && send_link == 0){ %>
				<td><input type = "submit" value ="연결번호 받지않음" disabled="disabled"/></td>
				<%}else if(receive_indexer == 1 && send_link == 0){ %>
				<td><input type = "submit" value = "데이터 전송" /></td>
				<%}else{ %>
				<td><input type="submit" value = "전송완료" disabled="disabled"/></td>
				<%} %>
				</form>
				
			<%
				} // end while
			%>
			
			
			</tr>
		</table>
		
	
	<%
		
		} catch (Exception e) {
			out.println("err:" + e.toString());
		}finally{
			if(rs1 != null){rs1.close();} // ResultSet exit
			if(stmt1 != null){stmt1.close();} // Statement exit
			if(conn1 != null){conn1.close();} // Connection exit
		}
	%>
	

	</td>

		<!-- ---------- STAT 데이터 info ----------- -->
	<td>
	
	<%	
	
		Connection conn2 = JdbcUtil.getConnection();
		Statement stmt2 = null;
		ResultSet rs2 = null;
	
		try {
		stmt2 = conn2.createStatement();

		String query = "select * from stat_take_data.stat_take_data_info";
		rs2 = stmt2.executeQuery(query); %>
	
	
	<table border="1" cellspacing="0">
			<tr>
				<th>요청 시간</th>
				<th>NECA요청자 ID</th>
				<th>NECA요청자 이름</th>
				<th>요청 데이터 수</th>
				<th>전송 가능 데이터 수</th>
				<th>NECA INDEXER</th>
				<th>연계 플랫폼</th>
			</tr>
			<%
				while (rs2.next()) { //rs 를 통해 테이블 객체들의 필드값을 넘겨볼 수 있다.
					String time = rs2.getString(1);
					Date date_time = format.parse(time);
					String format_time = (String)format.format(date_time);
					String neokID = rs2.getString(3);
					String neokName = rs2.getString(4);
					String tableName = rs2.getString(2);
					
					int send_indexer = rs2.getInt(7);
					int receive_indexer = rs2.getInt(8);
					int send_link = rs2.getInt(9);
			%>

			<tr>
				<td><%=format_time %></td>
				<td><%=neokID%></td>
				<td><%=neokName%></td>
				<td><%=rs2.getInt(5) %></td>
				<td><%=rs2.getInt(6)%></td>
				
				<form action="datasend_STATtoIN" method="POST">
				<input type="hidden" name="tableName" value="<%=tableName %>"/>
				<input type="hidden" name="neokID" value="<%=neokID %>"/>
				<input type="hidden" name="STATid" value="<%=STATid %>"/>
				<input type="hidden" name="STATname" value="<%=STATname %>"/>
				<input type="hidden" name="send_indexer" value="<%=send_indexer %>"/>

				<%if(send_indexer == 0){ %>
				<td><input type="submit" value="전송"/></td>
				<%}else{ %>
				<td><input type="submit" value="전송완료" disabled="disabled"/></td>
				<%} %>				
				</form>
				
				<!-- indexer에게 연결번호 받고 liker에게 보내지 않았을때만 활성화 -->
				<form action="datasend_STATtoLINK" method="POST">
				
				
				
				<%if(receive_indexer == 0 && send_link == 0){ %>
				<td><input type = "submit" value ="연결번호 받지않음" disabled="disabled"/></td>
				<%}else if(receive_indexer == 1 && send_link == 0){ %>
				<td><input type = "submit" value = "데이터 전송" /></td>
				<%}else{ %>
				<td><input type="submit" value = "전송완료" disabled="disabled"/></td>
				<%} %>
				</form>
				
			<%
				} // end while
			%>
			
			
			</tr>
		</table>
		
	
	<%
		
		} catch (Exception e) {
			out.println("err:" + e.toString());
		}finally{
			if(rs2 != null){rs2.close();} // ResultSet exit
			if(stmt2 != null){stmt2.close();} // Statement exit
			if(conn2 != null){conn2.close();} // Connection exit
		}
	%>
	
	
	</td>
	</tr>
	</table>

</body>
</html>