<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2>Create your account</h2>
        <spring:bind path="username">
            <div ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" placeholder="Username"
                            autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="confirmPassword">
            <div ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="confirmPassword"
                            placeholder="Confirm your password"></form:input>
                <form:errors path="confirmPassword"></form:errors>
            </div>
        </spring:bind>

        <button type="submit">Submit</button>
    </form:form>
</body>
</html>