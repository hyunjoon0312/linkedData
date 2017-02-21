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
<title>Insert title here</title>
</head>
<body>

기관 데이터 전송 ok<br/>
<%
String filename = request.getParameter("filename");
String uploadername = request.getParameter("uploadername");

Connection conn = JdbcUtilUpload.getUploadConnection();
Statement stmt = null;
ResultSet rs = null;

	
/* 	Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql://112.72.158.187:3306/uploadFile", "hyunjoon",
			"hyunjoon"); */
	System.out.println("UploadDB connect success");

try{
	
	stmt = conn.createStatement();
	String sql = "SELECT * FROM "+uploadername+"_"+filename;
	rs = stmt.executeQuery(sql);

	
	while(rs.next()){
		
	}
	

}catch(Exception e){
	e.printStackTrace();
}




%>















  <script type="text/javascript">  
    var ws = new WebSocket("ws://localhost:8080/linkedData/websocket");
	
       if ("WebSocket" in window)
       {
          // Let us open a web socket
      
          ws.onopen = function()
          {
             // Web Socket is connected, send data using send()
             ws.send("<%=uploadername%>연구자 <%=filename%>파일 식별자 건강보험공단 전송..."+"\n");
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
</body>
</html>