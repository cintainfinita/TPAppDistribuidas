<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Muestro Mesa</title>
<link rel="stylesheet" href="css/style.css">
<%@include file="./menu.jsp"%> <br><br>
</head>

<body>
  <br><br><br><br><br>

  <center>
      <h1>Su mesa es la <%=request.getParameter("nuevamesa")%></h1><br>
      <center>
  <img src="img/salchi.png" alt="salchi" ></center>
      <h1>A sentarse que hay hambre!!!!</h1>
  
 </center>
 
</body>
</html>