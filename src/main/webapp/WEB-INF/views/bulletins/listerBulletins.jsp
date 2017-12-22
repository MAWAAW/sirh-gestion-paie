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
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/mvc/employes/lister">Employés</a>
				</li>
				<li class="nav-item active"><a class="nav-link"
					href="<%=request.getContextPath()%>/mvc/bulletins/lister">Bulletins<span
						class="sr-only">(current)</span></a></li>
			</ul>
		</div>
	</nav>

	<div class="container">

		<h1 class="text-center">Lister des bulletins</h1>

		<a class="btn btn-primary float-right" href="<c:url value='creer'/>">Créer
			un nouveau bulletin</a>

		<table class="table">
			<thead>
				<tr>
					<th>Date/heure création</th>
					<th>Période</th>
					<th>Matricule</th>
					<th>Salaire brut</th>
					<th>Net Imposable</th>
					<th>Net à payer</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bulletinCalculer" items="${bulletinsCalculer}">
					<tr>
						<td>Non supporté</td>
						<td>${bulletinCalculer.key.periode.dateDebut} - ${bulletinCalculer.key.periode.dateFin}</td>
						<td>${bulletinCalculer.key.remunerationEmploye.matricule}</td>
						<td>${bulletinCalculer.value.salaireBrut}</td>
						<td>${bulletinCalculer.value.netImposable}</td>
						<td>${bulletinCalculer.value.netAPayer}</td>
						<td><a href="<c:url value='visualiser'/>">Visualiser</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>

</body>
</html>