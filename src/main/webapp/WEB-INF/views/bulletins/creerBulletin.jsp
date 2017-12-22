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
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/mvc/bulletins/lister">Bulletins</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container">

		<h1 class="text-center">Créer Bulletin de Salaire</h1>

		<form method="POST">
			<div class="form-group">
				<label for="exampleFormControlSelect1">Période</label> <select
					class="form-control" name="periode">
					<c:forEach var="periode" items="${periodes}">
						<option value="${ periode.id }">${ periode.getDateDebut() } - ${ periode.getDateFin() }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="exampleFormControlSelect2">Matricule</label> <select
					class="form-control" name="matricule">
					<c:forEach var="remunerationEmploye" items="${remunerationEmployes}">
						<option value="${ remunerationEmploye.id }">${ remunerationEmploye.getMatricule() }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="exampleFormControlInput1">Prime exceptionnelle</label> <input
					type="text" class="form-control" name="primeExceptionnelle" required>
			</div>
			<button type="submit" class="btn btn-primary float-right">Créer</button>
		</form>
	</div>

</body>
</html>