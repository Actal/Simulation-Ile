<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<link rel="stylesheet" href="assets/css/entityEditor.css">
<link rel="stylesheet" href="assets/css/icon.css">

<t:layout>
	<h1>Workplace</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Longueur</th>
				<th>Prix</th>
				<th>Cout d'entretien base</th>
				<th># places</th>
				<th>Heure ouverture</th>
				<th>Heure fermeture</th>
				<th>X</th>
				<th>Y</th>
				<th>Proprietaire</th>
				<th />
				<th />
			</tr>
		</thead>
		<tbody>
			<tr>
				<td></td>
				<td><input type="text"		name="nom"					form="0" class="form-control"/></td>
				<td><input type="number"	name="longueur"				form="0" class="form-control"/></td>
				<td><input type="number"	name="prix"					form="0" class="form-control"/></td>
				<td><input type="number"	name="coutEntretienBase"	form="0" class="form-control"/></td>
				<td><input type="number"	name="nbPlace"				form="0" class="form-control"/></td>
				<td><input type="time"		name="heureOuverture"		form="0" class="form-control"/></td>
				<td><input type="time"		name="heureFermeture"		form="0" class="form-control"/></td>
				<td><input type="number"	name="x"					form="0" class="form-control"/></td>
				<td><input type="number"	name="y"					form="0" class="form-control"/></td>
				<td>
					<select name="proprietaire.id" form="0" class="form-select">
						<c:forEach items="${ proprietaires }" var="proprietaire">
							<option value="${ proprietaire.id }">${ proprietaire.nom } ${ proprietaire.prenom }</option>
						</c:forEach>
					</select>
				</td>
				<td><button type="submit" class="btn btn-primary" form="0"> <i class="icon icon-save"></i> </button></td>
				<td></td>
			</tr>
			<form method="POST" id=0></form>
			
			<c:forEach items="${ workplaces }" var="workplace">
				<form method="POST" id="${ workplace.id }"></form>
				<tr>
					<td>${ workplace.id }<input type="hidden" name="id" form="${ workplace.id }" value="${ workplace.id }"					class="form-control"/></td>
					<td><input type="text"   name="nom"					form="${ workplace.id }" value="${ workplace.nom }"					class="form-control"/></td>
					<td><input type="number" name="longueur"			form="${ workplace.id }" value="${ workplace.longueur }"			class="form-control"/></td>
					<td><input type="number" name="prix"				form="${ workplace.id }" value="${ workplace.prix }"				class="form-control"/></td>
					<td><input type="number" name="coutEntretienBase"	form="${ workplace.id }" value="${ workplace.coutEntretienBase }"	class="form-control"/></td>
					<td><input type="number" name="nbPlace"				form="${ workplace.id }" value="${ workplace.nbPlace }"				class="form-control"/></td>
					<td><input type="time"   name="heureOuverture"		form="${ workplace.id }" value="${ workplace.heureOuverture }"		class="form-control"/></td>
					<td><input type="time"   name="heureFermeture"		form="${ workplace.id }" value="${ workplace.heureFermeture }"		class="form-control"/></td>
					<td><input type="number" name="x"					form="${ workplace.id }" value="${ workplace.coordonnees.x }"		class="form-control"/></td>
					<td><input type="number" name="y"					form="${ workplace.id }" value="${ workplace.coordonnees.y }"		class="form-control"/></td>
					<td>
						<select name="proprietaire.id" form="${ workplace.id }" class="form-select">
							<c:forEach items="${ proprietaires }" var="proprietaire">
								<c:if test="${ proprietaire.id != workplace.proprietaire.id }">
									<option value="${ proprietaire.id }">${ proprietaire.nom } ${ proprietaire.prenom }</option>
								</c:if>
							
								<c:if test="${ proprietaire.id == workplace.proprietaire.id }">
									<option value="${ proprietaire.id }" selected>${ proprietaire.nom } ${ proprietaire.prenom }</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td><button type="submit" class="btn btn-primary"	form="${ workplace.id }"> <i class="icon icon-save"></i> </button></td>
					<td><a href="supprimer-workplace?id=${ workplace.id }" class="btn btn-danger"> <i class="icon icon-delete"></i> </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</t:layout>