<%@page import="vo.MemberNHIS"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
NECA INDEXER에 NHIS 기관 식별자 전송완료

<%	String NHISid = ((MemberNHIS)request.getSession().getAttribute("NHISid")).getNHISId();
%>


	<script type="text/javascript">  
    var ws = new WebSocket("ws://localhost:8080/linkedData/websocket");
	
       if ("WebSocket" in window)
       {
          // Let us open a web socket
      
          ws.onopen = function()
          {
             // Web Socket is connected, send data using send()
             ws.send("NHIS("+NHISid+") NECA IDEXER로 기관 식별자 전송..." + "\n");
			};

			ws.onmessage = function(evt) {
				//var received_msg = evt.data;

			};

			ws.onclose = function() {
				// websocket is closed.
				alert("Connection is closed...");
			};
		}

		else {
			// The browser doesn't support WebSocket
			alert("WebSocket NOT supported by your Browser!");
		}
		function send() {
			console.log(123)
			ws.send("123");
		}
	</script>

</body>
</html>