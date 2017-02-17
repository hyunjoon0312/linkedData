<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dao.LoginDAOR"%>
<%@page import='vo.MemberR' %>    
    
<%
	String Rid = ((MemberR)request.getSession().getAttribute("Rid")).getRId();
	String Rname = ((MemberR)request.getSession().getAttribute("Rid")).getRName();
	
/* 	String Ropen = (String)session.getAttribute("Ropen");
	int IntRopen = Integer.parseInt(Ropen);
	IntRopen += 1;
	
	System.out.println(IntRopen); */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

연구자 로그인 성공<br/>

<script type="text/javascript">  
    var ws = new WebSocket("ws://210.115.182.222:8080/linkedData/websocket");
	
       if ("WebSocket" in window)
       {
          // Let us open a web socket
      	
          ws.onopen = function()
          {
         
             // Web Socket is connected, send data using send()
             ws.send("<%=Rname%>님(연구자) 로그인"+"\n"); 
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

    </script>
 
<a href = "DataRequestForm.jsp">데이터 요청</a><br/>
<a href = "sessionLogoutR.jsp">로그아웃</a>


</body>
</html>