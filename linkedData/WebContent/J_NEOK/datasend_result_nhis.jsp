<%@page import="vo.MemberNEOK"%>
<%@page import="java.sql.Statement"%>
<%@page import="db.JdbcUtilUpload"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Ǻ����� ������ ��û ���</title>
</head>
<body>

<%

String NEOKid = ((MemberNEOK)request.getSession().getAttribute("NEOKid")).getNEOKId();



String filename = request.getParameter("filename");
String uploadername = request.getParameter("uploadername");
String str_nhis_send = request.getParameter("nhis_send");
int nhis_send = Integer.parseInt(str_nhis_send);

if(nhis_send == 0){
%>

<h1>�ǰ������������ <%=uploadername %> �����ڰ� ��û�� ������ ���� ����<br/></h1>

<script type="text/javascript">  
    var ws = new WebSocket("ws://localhost:8080/linkedData/websocket");
	
       if ("WebSocket" in window)
       {
          // Let us open a web socket
      
          ws.onopen = function()
          {
             // Web Socket is connected, send data using send()
        	  ws.send("NECA����ó(<%=NEOKid%>) : <%=uploadername%>������ ��û�� �ĺ��� �ǰ�������� ����"+"\n");
          };
			
          ws.onmessage = function (evt) 
          { 
             //var received_msg = evt.data;
             
          };
			
          ws.onclose = function()
          { 
             // websocket is closed.
             alert("Connection is closed..."); 
          };
       }
       
       else
       {
          // The browser doesn't support WebSocket
          alert("WebSocket NOT supported by your Browser!");
       }
    function send(){
    	console.log(123)
    	ws.send("123");
    }
 
    </script>


<%}else{ %>

�̹� ������ ������ �Դϴ�.<br><br>

<%} %>





  
    
    	<input type="button" name="back" value="�ڷΰ���" onclick="javascript:location.href='./Datalist_RtoNEOK.jsp';"/>
    
    
</body>
</html>