<%@page import="java.util.Vector"%>
<%@page import="dao.DataListDAO"%>
<%@page import="vo.MemberList"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

DataListDAO dao = new DataListDAO();
ArrayList<MemberList> list = dao.getListAll();



for(int i = 0; i<list.size(); i++){
	MemberList dto = list.get(i);
	
}

%>

</body>
</html>