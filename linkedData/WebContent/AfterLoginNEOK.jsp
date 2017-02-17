<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dao.LoginDAONEOK"%>
<%@page import='vo.MemberNEOK' %>    
    
<%
	String NEOKid = ((MemberNEOK)request.getSession().getAttribute("NEOKid")).getNEOKId();
	String NEOKname = ((MemberNEOK)request.getSession().getAttribute("NEOKid")).getNEOKName();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

NECA 승인처 로그인 성공
<a href = "sessionLogoutNEOK.jsp">로그아웃</a>
      <script type="text/javascript">  
    var ws = new WebSocket("ws://210.115.182.222:8080/linkedData/websocket");
	
       if ("WebSocket" in window)
       {
          // Let us open a web socket
      
          ws.onopen = function()
          {
             // Web Socket is connected, send data using send()
             ws.send("<%=NEOKname%>님(NECA승인처) 로그인..."+"\n");
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
 
<a href = "DataSend.jsp">데이터 요청</a>


</body>
</html>