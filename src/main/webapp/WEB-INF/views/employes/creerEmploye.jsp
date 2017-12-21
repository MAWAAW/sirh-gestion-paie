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

		<h1 class="text-center">Ajouter un employé</h1>

		<form method="POST">
			<div class="form-group">
				<label for="exampleFormControlInput1">Matricule</label> <input
					type="text" class="form-control" name="matricule" required>
			</div>
			<div class="form-group">
				<label for="exampleFormControlSelect1">Entreprise</label> <select
					class="form-control" name="entreprise">
					<c:forEach var="entreprise" items="${entreprises}">
						<option value="${ entreprise.id }">${ entreprise.getDenomination() }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="exampleFormControlSelect2">Profil</label> <select
					class="form-control" name="profil">
					<c:forEach var="profil" items="${profils}">
						<option value="${ profil.id }">${ profil.getCode() }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="exampleFormControlSelect3">Grade</label> <select
					class="form-control" name="grade">
					<c:forEach var="grade" items="${grades}">
						<option value="${ grade.id }">${ grade.getCode() }</option>
					</c:forEach>
				</select>
			</div>
			<button type="submit" class="btn btn-primary float-right">Ajouter</button>
		</form>
	</div>

</body>
</html>