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
<title>연계플랫폼 로그인</title>
</head>
<body>

    <script type="text/javascript">  
    var ws = new WebSocket("ws://localhost:8080/linkedData/websocket");
	
       if ("WebSocket" in window)
       {
          // Let us open a web socket
      
          ws.onopen = function()
          {
             // Web Socket is connected, send data using send()
             ws.send("<%=LINKname%>님(데이터연계플랫폼) 로그인"+"\n");
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
 
 <h1>데이터 연계플랫폼 로그인 성공<br/></h1><br>
 
 <ul>
<li><h2><a href = "./Datalist_INDEXERtoLINK.jsp">대조표 목록</a></h2></li>
 </ul>
 
 
	<br>&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
	<input type="button" name="back" value="로그아웃"
		onclick="javascript:location.href='./sessionLogoutLINK.jsp';" />
 
</body>
</html>