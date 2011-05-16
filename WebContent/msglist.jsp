<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


   
<html>
<head>
<link href="css/basic.css" media="screen" rel="stylesheet" type="text/css" /> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MongoDB Sample Application</title>
</head>
<body>
<h2>MongoDB Messages</h2>


<table>
<c:forEach items="${actionBean.msgs}" var="msg" varStatus="loop">
<tr>
<td>${loop.index}. ${msg['msg']}<br/></td><td><a href="/mongo1/delete/${msg['_id']}">delete</a></td>
</tr>
</c:forEach>
</table>

<br/><a href="/mongo1/add">New message</a>
</body>
</html>