<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dao.LoginDAOLINK"%>
<%@page import='vo.MemberLINK' %>    

<%
String LINKid = ((MemberLINK)request.getSession().getAttribute("LINKid")).getLINKId();
String LINKname = ((MemberLINK)request.getSession().getAttribute("LINKid")).getLINKName();
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
연계 데이터 플랫폼 로그인 성공<br/>

    <script type="text/javascript">  
    var ws = new WebSocket("ws://localhost:8080/linkedData/websocket");
	
       if ("WebSocket" in window)
       {
          // Let us open a web socket
      
          ws.onopen = function()
          {
             // Web Socket is connected, send data using send()
             ws.send("<%=LINKname%>님(데이터연계플랫폼) 로그인..."+"\n");
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
    <!-- <input type="submit" value="send" onclick="send()" /> -->
 
<a href = "/linkedData/J_LINK/sessionLogoutLINK.jsp">로그아웃</a>
</body>
</html>