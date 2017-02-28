<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상태 확인창</title>

</head>
<body>
<h3>상태확인창</h3>
<!--     <form>
        송신 메시지 작성하는 창
        <input id="textMessage" type="text">
        송신 버튼
        <input onclick="sendMessage()" value="Send" type="button">
        종료 버튼
        <input onclick="disconnect()" value="Disconnect" type="button">
    </form> -->
    <br />
    <!-- 결과 메시지 보여주는 창 -->
    <textarea id="messageTextArea" rows="30" cols="80"></textarea>
     
    <script type="text/javascript">  
    var ws = new WebSocket("ws://localhost:8080/linkedData/websocket");
	
   function chage(){
	   
	   var element = document.getElementById("span1");
	   element.style.fontSize = "48pt";
	   element.style.color = "#f00";
	   element.style.fontWeight = "bold";

   }
    
 
    
       if ("WebSocket" in window)
       {
          
          
          // Let us open a web socket
      
          ws.onopen = function()
          {
			
        	  // Web Socket is connected, send data using send()
             ws.send("대기중..."+"\n");
             
          };
			
          ws.onmessage = function (evt) 
          { console.log(evt)
             //var received_msg = evt.data;
             
             messageTextArea.value=messageTextArea.value+evt.data;
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
</body>
</html>


