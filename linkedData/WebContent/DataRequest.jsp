<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="java.util.*"%>
<%
	String uploadPath = request.getRealPath("/upload");

	int size = 500 * 1024 * 1024; //500M
	String id = "";
	String name = "";
	String subject = "";
	String filename = "";
	String origfilename = "";

	try {
		MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "UTF-8",
				new DefaultFileRenamePolicy());
		id = multi.getParameter("id");
		name = multi.getParameter("name");
		subject = multi.getParameter("subject");

		Enumeration files = multi.getFileNames();

		String file = (String) files.nextElement();
		filename = multi.getFilesystemName(file);
		origfilename = multi.getOriginalFileName(file);

	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<html>
<body>
	<form name="filecheck" action="fileCheck.jsp" method="post">
		<input type="hidden" name="id" value="<%=id%>"> 
		<input type="hidden" name="name" value="<%=name%>"> 
		<input type="hidden" name="subject" value="<%=subject%>"> 
		<input type="hidden" name="filename" value="<%=filename%>"> 
		<input type="hidden" name="origfilename" value="<%=origfilename%>">
	</form>
	<a href="#" onclick="javascript:filecheck.submit()">업로드 확인 및 다운로드
		페이지 이동</a>
		
		<%-- <% 	String tom_path = System.getProperty("catalina.base");
			out.println("tomcat 위치 : " +tom_path);
		%> --%>
</body>
</html>
