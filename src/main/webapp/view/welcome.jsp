<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.event-1 {
	background-color: blue;
	color: white;
}

.event-2 {
	background-color: orange;
	color: white;
}

.event-3 {
	background-color: red;
	color: white;
}
</style>
</head>
<body>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<form id="logoutForm" method="POST" action="${contextPath}/logout">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>

		<h2>
			Welcome ${pageContext.request.userPrincipal.name} | <a
				onclick="document.forms['logoutForm'].submit()">Logout</a>
		</h2>

	</c:if>
	
	<div id="my_sub">
	<form:form method="POST" modelAttribute="eventForm" id="event-form">
		<form:input path="description" type="text" placeholder="desc" v-model="eventForm['description']"/>
		<form:input path="date" type="date" v-model="eventForm['date']"/>
		<form:input path="timeString" type="time" v-model="eventForm['timeString']"/>
		<form:input path="important" type="number" min="1" max="3" v-model="eventForm['important']"/>
		<form:input path="tag" type="text" placeholder="tag" v-model="eventForm['tag']"/>
	</form:form>
		<button v-on:click="somef()">value=Dodaj event></button>
		
		<div v-for="(e, i) in events">
       		<div v-bind:class="theClass(i)">
       			<h3>{{e['description']}}</h3> <br/>
       			<h4>{{e['date']}}</h4> <h4>{{e['time']}}</h4> <br/>
       			<h6>{{e['tag']}}</h6>
       		</div>
      	</div>
      
		<input type="text" v-model="word"/>
		<button v-on:click="getElements()">Znajdz</button>
	</div>
	
	<br />
	
	
	<script type="text/javascript" src="webjars/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://unpkg.com/vue"></script>
	<script src="https://unpkg.com/vue-resource@1.3.4"></script>



	<script>
	Vue.http.options.emulateJSON = true;
	new Vue({
	  el: '#my_sub',
	  data: {
		 eventForm: {
		  description: "",
		  date: "",
		  timeString: "",
		  important: "",
		  tag: "",
		  ${_csrf.parameterName}: "${_csrf.token}"
		 },
		 events: [],
		 word: ""
	  },
	  mounted: function() {
		
	     this.getElements();
	
	  },
	  methods : {
	    somef : function() {
	    	var data = this.eventForm;
	          this.$http.post('/rest/addevent', data).then(response => {
	          alert(response);
			  this.getElements();		
	      }, response => {
	        // error callback
	        alert(response);
	      });
	    },
	  	getElements : function() {
	  		url = '/rest/getevents?tag='+this.word;
	  		this.$http.get(url).then(response => {
	  			
	  			this.events = response.body;
	  		}, response => {
	  			
	  		});
	  	},
	  	theClass : function(index){
	  		return "event-"+this.events[index]['important'];
	  	}
	  }
	})
	</script>
</body>
</html>