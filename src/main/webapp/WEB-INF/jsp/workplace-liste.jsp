<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<link rel="stylesheet" href="assets/css/entityEditor.css">
<link rel="stylesheet" href="assets/css/icon.css">

<t:layout>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Superficie</th>
				<th>Prix</th>
				<th>Cout d'entretien base</th>
				<th># places</th>
				<th>Heure ouverture</th>
				<th>Heure fermeture</th>
				<th />
				<th />
			</tr>
		</thead>
		<tbody>
			<tr>
				<td></td>
				<td><input type="text" name="nom" form="0"/></td>
				<td><input type="number" name="superficie" form="0"/></td>
				<td><input type="number" name="prix" form="0"/></td>
				<td><input type="number" name="coutEntretienBase" form="0"/></td>
				<td><input type="number" name="nbPlace" form="0"/></td>
				<td><input type="time"   name="heureOuverture" form="0"/></td>
				<td><input type="time"   name="heureFermeture" form="0"/></td>
				<td><button type="submit" class="btn btn-primary" form="0"> <i class="icon icon-save"></i> </button></td>
				<td></td>
			</tr>
			<form method="POST" id=0></form>
			
			<c:forEach items="${ workplaces }" var="workplace">
				<form method="POST" id="${ workplace.id }"></form>
				<tr>
					<td>${ workplace.id }<input type="hidden" name="id" form="${ workplace.id }" value="${ workplace.id }" /></td>
					<td><input type="text"   name="nom"					form="${ workplace.id }" value="${ workplace.superficie }" /></td>
					<td><input type="number" name="superficie"			form="${ workplace.id }" value="${ workplace.argent }" /></td>
					<td><input type="number" name="prix"				form="${ workplace.id }" value="${ workplace.prix }" /></td>
					<td><input type="number" name="coutEntretienBase"	form="${ workplace.id }" value="${ workplace.coutEntretienBase }" /></td>
					<td><input type="number" name="nbPlace"				form="${ workplace.id }" value="${ workplace.nbPlace }" /></td>
					<td><input type="time"   name="heureOuverture"		form="${ workplace.id }" value="${ workplace.heureOuverture }" /></td>
					<td><input type="time"   name="heureFermeture"		form="${ workplace.id }" value="${ workplace.heureFermeture }" /></td>
					<td><button type="submit" class="btn btn-primary"	form="${ workplace.id }"> <i class="icon icon-save"></i> </button></td>
					<td><a href="supprimer-workplace?id=${ workplace.id }" class="btn btn-danger"> <i class="icon icon-delete"></i> </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</t:layout>