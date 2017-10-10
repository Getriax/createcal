<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	.event-1 {
		background-color:blue;
		color:white;
	}
	.event-2 {
		background-color:orange;
		color:white;	
	}
	.event-3 {
		background-color:red;	
		color:white;	
	}
	
</style>
</head>
<body>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
        
    </c:if>
	
	<form:form method="POST" modelAttribute="eventForm" id="event-form"> 
		<form:input path="description" type="text" placeholder="desc"/>
		<form:input path="date" type="date"/>
		<form:input path="timeString" type="time"/>
		<form:input path="important" type="number" min="1" max="3"/>
		<form:input path="tag" type="text" placeholder="tag"/>
		<input type="submit" value="Dodaj event"> 
	</form:form>
	<br/>
	<input type="text" id="word"/> <button onclick="redirect()">Znajdz</button>
	<c:forEach items="${events}" var="event">
		<div class="event-${event.important}">
			<h2>${event.description}</h3> <br/>
			${event.date} - ${event.time} <bt/>
			<h5>TAG: ${event.tag}</h5>
		</div> 
	</c:forEach>
	<script type="text/javascript" src="webjars/jquery/3.2.1/jquery.min.js"></script>
	<script>
		jQuery(document).ready(function($) {

			$("#event-form").submit(function(event) {

				// Disble the search button
				enableSearchButton(false);

				// Prevent the form from submitting via the browser.
				event.preventDefault();

				searchViaAjax();

			});

		});

		function searchViaAjax() {
			
			var model = $("#event-form").serialize();
			$.ajax({
				type : "POST",
				url : "/rest/addevent",
				data : model,
				async: false,
			    dataType: "json",
				success : function(data) {
					console.log("SUCCESS: ", data);
					alert(data['event']);
				},
				error : function(e) {
					console.log("ERROR: ", e);
					display(e);
				},
				done : function(e) {
					console.log("DONE");
					enableSearchButton(true);
				}
			});

		}

		function enableSearchButton(flag) {
			$("#btn-search").prop("disabled", flag);
		}

		function display(data) {
			var json = "<h4>Ajax Response</h4><pre>"
					+ JSON.stringify(data, null, 4) + "</pre>";
			$('#result').html(json);
		}
		function redirect() {
			var word = $("#word").val();
			location.href="/welcome?tag=" + word;
		}
	</script>
</body>
</html>