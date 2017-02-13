<%@page import="dao.LoginDAOR"%>
<%@page import='vo.MemberR' %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>



<%
	String Rid = ((MemberR)request.getSession().getAttribute("Rid")).getRId();
	String Rname = ((MemberR)request.getSession().getAttribute("Rid")).getRName();
	
%>
<html>
<head>
<title>연계 데이터 요청</title>
<style>
	#uploadFormArea{
		margin : auto;
		width : 350px;
		border : 1px solid black;
	}
	.td_title{
		font-size: xx-large;
		text-align: center;
	}
</style>
</head>
<body>
<section id = "uploadFormArea">
<form action="DataRequest.jsp" method="post" enctype="multipart/form-data">

<table>
	<tr>
		<td colspan="2" class = "td_title" >연계 데이터 요청</td></tr></br>
	<tr>
		<td>연구자 ID :</td><td><%=Rid %></td>
		<input type="hidden" name="id" value="<%=Rid %>"/>
	</tr>	
	<tr>
		<td>연구자 :</td><td> <%=Rname %></td>
		<input type="hidden" name="name" value="<%=Rname %>"/>
	</tr>
	<tr>
		<td><label for = "subject">제목 : </label></td><td><input type="text" name="subject" id = "subject"></td>
	</tr>
	<tr>
		<td><label for = "fileName">파일명1 : </label></td><td><input type="file" name="fileName" id = "fileName"></td>
	</tr>
	<tr>
		<td colspan=2 align=center><input type="submit" value="전송"></td>
	</tr>
</table>
</form>
</section>
</center>
</body>
</html>
