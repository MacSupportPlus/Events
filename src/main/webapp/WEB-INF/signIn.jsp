<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
</head>
<body>
    <h1>Register!</h1>
    
    <p style="color:red;"><form:errors path="user.*"/></p>
   		
   		<p>${firstName_error}</p>
    	<p>${lastName_error}</p>
    	<p>${location_error}</p>
    	<p>${email_error}</p>
    	<p>${passWord_error}</p>
    	<p>${confirmPassWord_error}</p>
    
    <form:form method="POST" action="/register" modelAttribute="user">
    <h1>Register</h1>
    	<p>
            <form:label path="firstName">First Name :</form:label>
            <form:input type="firstName" path="firstName"/>
        </p>
        <p>
            <form:label path="lastName">Last Name :</form:label>
            <form:input type="lastName" path="lastName" />
        </p>
        <p>
            <form:label path="location">Location :</form:label>
            <form:input type="location" path="location"/>
        </p>
        <p>
            <form:label path="email">Email:</form:label>
            <form:input type="email" path="email"/>
        </p>
        <p>
            <form:label path="passWord">Password:</form:label>
            <form:password path="passWord"/>
        </p>
        <p>
            <form:label path="confirmPassWord">Password Confirmation:</form:label>
            <form:password path="confirmPassWord"/>
        </p>
        <input type="submit" value="Register!"/>
    </form:form>
     <h1>Login</h1>
    <p><c:out value="${error}" /></p>
    <p>${login_errors}</p>
    <p>${success_error}</p>
    <form method="post" action="/login">
        <p>
            <label for="email">Email</label>
            <input type="text" id="email" name="email"/>
        </p>
        <p>
            <label for="passWord">Password</label>
            <input type="passWord" id="passWord" name="passWord"/>
        </p>
        <input type="submit" value="Login!"/>
    </form>    
    
</body>
</html>