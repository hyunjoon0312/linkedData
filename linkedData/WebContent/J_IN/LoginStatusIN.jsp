<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dao.LoginDAOIN"%>
<%@page import='vo.MemberIN' %>    

<%
String INid = ((MemberIN)request.getSession().getAttribute("INid")).getINId();
String INname = ((MemberIN)request.getSession().getAttribute("INid")).getINName();
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
NECA INDEXER 로그인 성공<br/>

<script type="text/javascript">  
    var ws = new WebSocket("ws://localhost:8080/linkedData/websocket");
	
       if ("WebSocket" in window)
       {
          // Let us open a web socket
      	
          ws.onopen = function()
          {
         
             // Web Socket is connected, send data using send()
             ws.send("<%=INname%>님(NECA INDEXER) 로그인"+"\n"); 
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
 
<a href = "/linkedData/J_IN/sessionLogoutIN.jsp">로그아웃</a>
</body>
</html>