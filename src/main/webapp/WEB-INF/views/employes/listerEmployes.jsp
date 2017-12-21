<%@ page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap-4.0.0-alpha.6-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap-4.0.0-alpha.6-dist/js/bootstrap.js">
<title>SGP - App</title>
</head>
<body>

	<nav class="navbar navbar-toggleable-md navbar-light bg-faded">
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand" href="#">WebSiteSpring</a>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="<%=request.getContextPath()%>/mvc/employes/lister">Employés<span
						class="sr-only">(current)</span></a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/mvc/bulletins/lister">Bulletins</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container">

		<h1 class="text-center">Lister des employés</h1>

		<a class="btn btn-primary float-right" href="<c:url value='creer'/>">Ajouter un employe</a>
		
		<table class="table">
			<thead>
				<tr>
					<th>Date/heure création</th>
					<th>Matricule</th>
					<th>Grade</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="remunerationEmploye" items="${remunerationEmployes}">
					<tr>
						<td>Non supporté</td>
						<td>${remunerationEmploye.getMatricule()}</td>
						<td>${remunerationEmploye.getGrade().getCode()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>

</body>
</html>