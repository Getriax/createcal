<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		
		<form id="my-form">
			<input type="text" id="name">
			<input type="submit" value="display">
		</form>
		<div id="result">
		
		</div>
	</div>
	<script type="text/javascript" src="webjars/jquery/3.2.1/jquery.min.js"></script>
	<script>
		jQuery(document).ready(function($) {

			$("#my-form").submit(function(event) {

				// Disble the search button
				enableSearchButton(false);

				// Prevent the form from submitting via the browser.
				event.preventDefault();

				searchViaAjax();

			});

		});

		function searchViaAjax() {
			
			var n = $("#name").val();
			$.ajax({
				type : "POST",
				url : "/rest/greet",
				data : {
					name : n
					},
				success : function(data) {
					console.log("SUCCESS: ", data);
					display(data);
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
	</script>

</body>
</html>