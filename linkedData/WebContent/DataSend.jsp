
<%@page import="vo.MemberList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = dao.DataListDAO %>    

<%
	DataListDAO dataListDAO = new DataListDAO();
	MemberList memberList = dataListDAO.getMemberList();
	String filename = memberList.getFilename();
	

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>기관 데이터 전송</title>
</head>
<body>

<%=filename %>
</body>
</html>