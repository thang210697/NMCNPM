<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body> 
 <%
    String info1 = request.getAttribute("info1").toString();
    String info2 = request.getAttribute("info2").toString();

    out.print("<br/>Info: " + info1);
    out.print("<br/>Info: " + info2);
  %>
</body>
</html>