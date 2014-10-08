<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
			
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:query var="rs" dataSource="jdbc/TestDB">
select id, name, email, content from offers
</sql:query>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Offers</title>
</head>
<body>
		Daniel offers
		
		<p>Session: <%= session.getAttribute("sessionKey") %></p>
		Request: <p><%= request.getAttribute("name") %></p>
		<p>${name}</p>
		<p>${name2}</p>
		
		<spring:message code="label.test" />
		
		<p></p>
		
		<c:forEach var="row" items="${rs.rows}">
    		Name ${row.name}<br/>
    		Email ${row.email}<br/>
    		Content ${row.content}<br/>
    		Id ${row.id}<br/>
		</c:forEach>
		
		<p>These are the rows from the service:</p>
		<c:forEach var="rrow" items="${ores}">
    		Name ${rrow.name}<br/>
    		Email ${rrow.email}<br/>
    		Content ${rrow.content}<br/>
    		Id ${rrow.id}<br/>
    		<c:out value="${ores}"></c:out>
		</c:forEach>
		
		<p>${pageContext.request.contextPath}</p>
		
		<p>The JSON:</p>
		${json}
		
</body>
</html>