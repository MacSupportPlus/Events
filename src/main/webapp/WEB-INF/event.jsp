<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome to Events</title>
</head>
<body>
	<h1>Welcome Ninja to Events</h1>
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Date</th>
				<th>Location</th>
				<th>Host</th>
				<th>Action/Status</th>
			</tr>
		</thead>
			<tbody>
				<c:forEach items= "${allEvents}" var="event">
					<tr>
						<td>${event.name}</td>
						<td><a href="/questions/${question.id}">${question.question}</a></td>
						<td>${event.name}</td>
						<td>${event.name}</td>
						<td>${events.user.name}</td>
					</tr>
				</c:forEach>
			</tbody>
	</table>
	<a href= "/questions/new">Add Event</a>
	<a href= "/logout">Logout</a>
</body>
</html>