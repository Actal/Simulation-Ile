<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<link rel="stylesheet" href="assets/css/entityEditor.css">
<link rel="stylesheet" href="assets/css/icon.css">

<t:layout>
	<h1>Habitation</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Longueur</th>
				<th>Prix</th>
				<th>Cout d'entretien base</th>
				<th>Loyer</th>
				<th># places</th>
				<th>X</th>
				<th>Y</th>
				<th>Propriétaire</th>
				<th />
				<th />
			</tr>
		</thead>
		<tbody>
			<tr>
				<td></td>
				<td><input type="text"		name="nom"					form="0"	class="form-control"/></td>
				<td><input type="number"	name="longueur"				form="0"	class="form-control"/></td>
				<td><input type="number"	name="prix"					form="0"	class="form-control"/></td>
				<td><input type="number"	name="coutEntretienBase"	form="0"	class="form-control"/></td>
				<td><input type="number"	name="loyer"				form="0"	class="form-control"/></td>
				<td><input type="number"	name="nbPlace"				form="0"	class="form-control"/></td>
				<td><input type="number"	name="x"					form="0"	class="form-control"/></td>
				<td><input type="number"	name="y"					form="0"	class="form-control"/></td>
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
			<form method="POST" id=0><input name="${ _csrf.parameterName }" type="hidden" value="${ _csrf.token }" /></form>
			
			<c:forEach items="${ habitations }" var="habitation">
				<form method="POST" id="${ habitation.id }"><input name="${ _csrf.parameterName }" type="hidden" value="${ _csrf.token }" /></form>
				<tr>
					<td>${ habitation.id }<input type="hidden" name="id" form="${ habitation.id }" value="${ habitation.id }"				class="form-control"/></td>
					<td><input type="text"   name="nom"					form="${ habitation.id }" value="${ habitation.nom }"				class="form-control"/></td>
					<td><input type="number" name="longueur"			form="${ habitation.id }" value="${ habitation.longueur }"			class="form-control"/></td>
					<td><input type="number" name="prix"				form="${ habitation.id }" value="${ habitation.prix }"				class="form-control"/></td>
					<td><input type="number" name="coutEntretienBase"	form="${ habitation.id }" value="${ habitation.coutEntretienBase }"	class="form-control"/></td>
					<td><input type="number" name="loyer"				form="${ habitation.id }" value="${ habitation.loyer }"				class="form-control"/></td>
					<td><input type="number" name="nbPlace"				form="${ habitation.id }" value="${ habitation.nbPlace }"			class="form-control"/></td>
					<td><input type="number" name="x"					form="${ habitation.id }" value="${ habitation.coordonnees.x }"		class="form-control"/></td>
					<td><input type="number" name="y"					form="${ habitation.id }" value="${ habitation.coordonnees.y }"		class="form-control"/></td>
					<td>
						<select name="proprietaire.id" form="${ habitation.id }" class="form-select">
							<c:forEach items="${ proprietaires }" var="proprietaire">
								<c:if test="${ proprietaire.id != habitation.proprietaire.id }">
									<option value="${ proprietaire.id }">${ proprietaire.nom } ${ proprietaire.prenom }</option>
								</c:if>
							
								<c:if test="${ proprietaire.id == habitation.proprietaire.id }">
									<option value="${ proprietaire.id }" selected>${ proprietaire.nom } ${ proprietaire.prenom }</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td><button type="submit" class="btn btn-primary"	form="${ habitation.id }"> <i class="icon icon-save"></i> </button></td>
					<td><a href="supprimer-habitation?id=${ habitation.id }" class="btn btn-danger"> <i class="icon icon-delete"></i> </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</t:layout>