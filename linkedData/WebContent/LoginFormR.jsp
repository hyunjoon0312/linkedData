<%@page import="vo.MemberR"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연구자 로그인</title>


<style>
	#loginFormArea{
		margin : auto;
		width : 400px;
		height : 200px;
		border : 2px double black; 
		border-radius : 10px;
		text-align: center;
	}
	fieldset{
		text-align: center;
		border : none;
	}
	#selectButton{
		margin-top : 10px;
	}
	table{
	    width : 380px;
		margin : auto;
	}
	.td_left{
		width : 180px
	}
	.td_right{
		width : 200px
	}
</style>
</head>
<body>

<% 
	MemberR Rid = ((MemberR)session.getAttribute("Rid"));
	String seRid = (String)session.getAttribute("Rid");


	if(Rid == null){
%>
    <section  id = "loginFormArea">
	<h1>보건의료 플랫폼(연구자)</h1>
	<form action="loginR"  method = "POST">
		<fieldset>
			<table>
				<tr>
					<td class = "td_left">
					<label for = "id">아이디 : </label>
					</td>
					<td class = "td_right">
					<input type = "text" name = "Rid" id = "Rid"/>
					</td>
				</tr>
				<tr>
					<td class = "td_left">
					<label for = "passwd">비밀번호 : </label>
					</td>
					<td class = "td_right">
					<input type = "password" name = "Rpasswd" id = "Rpasswd"/>
					</td>
				</tr>
			</table>
			
			
			
			<!-- <button id="login-button" class="btn btn-default" type="submit">LOGIN</button> -->
			<input type = "submit" value = "로그인" id = "selectButton" />
			
			
		</fieldset>
	</form>
	</section>
<%
	}else{
		response.sendRedirect("AfterLoginR.jsp");}
%>


<!--     <script type="text/javascript">
    var webSocket = new WebSocket("ws://localhost:8080/linkedData/websocket");
    var messageTextArea = document.getElementById("messageTextArea");

    function sendMessage(){
        var message = document.getElementById("Rid");
        //messageTextArea.value += "Send to Server => "+message.value+"\n";
        //웹소켓으로 textMessage객체의 값을 보낸다.
        webSocket.send(message.value);
        //textMessage객체의 값 초기화
        message.value = "";
    }
	</script> -->
</body>


</html>