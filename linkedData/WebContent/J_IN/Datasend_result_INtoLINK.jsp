<%@page import="vo.MemberIN"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	String INid = ((MemberIN)request.getSession().getAttribute("INid")).getINId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>연계기관 대조표 전송 완료</h1><br><br>

	<input type="button" name="back" value="뒤로가기" onclick="javascript:location.href='./Datalist_ORGtoINDEXER.jsp';"/>



<script type="text/javascript">  
    var ws = new WebSocket("ws:/localhost:8080/linkedData/websocket");
	
       if ("WebSocket" in window)
       {
          // Let us open a web socket
      
          ws.onopen = function()
          {
             // Web Socket is connected, send data using send()
             ws.send("NECA INDEXER(<%=INid%>) : 연계기관 대조표 전송" + "\n");
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