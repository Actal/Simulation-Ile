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
				<th>Longueur</th>
				<th>Prix</th>
				<th>Cout d'entretien base</th>
				<th>Loyer</th>
				<th># places</th>
				<th>Proprietaire</th>
				<th>X</th>
				<th>Y</th>
				<th />
				<th />
			</tr>
		</thead>
		<tbody>
			<tr>
				<td></td>
				<td><input type="text" name="nom" form="0"/></td>
				<td><input type="number" name="longueur" form="0"/></td>
				<td><input type="number" name="prix" form="0"/></td>
				<td><input type="number" name="coutEntretienBase" form="0"/></td>
				<td><input type="number" name="loyer" form="0"/></td>
				<td><input type="number" name="nbPlace" form="0"/></td>
				<td>
					<select name="proprietaireId" form="0">
						<c:forEach items="${ proprietaires }" var="proprietaire">
							<option value="${ proprietaire.id }">${ proprietaire.nom } ${ proprietaire.prenom }</option>
						</c:forEach>
					</select>
				</td>
				<td><input type="number" name="x" form="0"/></td>
				<td><input type="number" name="y" form="0"/></td>
				<td><button type="submit" class="btn btn-primary" form="0"> <i class="icon icon-save"></i> </button></td>
				<td></td>
			</tr>
			<form method="POST" id=0></form>
			
			<c:forEach items="${ habitations }" var="habitation">
				<form method="POST" id="${ habitation.id }"></form>
				<tr>
					<td>${ habitation.id }<input type="hidden" name="id" form="${ habitation.id }" value="${ habitation.id }" /></td>
					<td><input type="text"   name="nom"					form="${ habitation.id }" value="${ habitation.nom }" /></td>
					<td><input type="number" name="longueur"			form="${ habitation.id }" value="${ habitation.longueur }" /></td>
					<td><input type="number" name="prix"				form="${ habitation.id }" value="${ habitation.prix }" /></td>
					<td><input type="number" name="coutEntretienBase"	form="${ habitation.id }" value="${ habitation.coutEntretienBase }" /></td>
					<td><input type="number" name="loyer"				form="${ habitation.id }" value="${ habitation.loyer }" /></td>
					<td><input type="number" name="nbPlace"				form="${ habitation.id }" value="${ habitation.nbPlace }" /></td>
					<td>
						<select name="proprietaireId" form="${ habitation.id }">
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
					<td><input type="number" name="x"					form="${ habitation.id }" value="${ habitation.coordonnees.x }" /></td>
					<td><input type="number" name="y"					form="${ habitation.id }" value="${ habitation.coordonnees.y }" /></td>
					<td><button type="submit" class="btn btn-primary"	form="${ habitation.id }"> <i class="icon icon-save"></i> </button></td>
					<td><a href="supprimer-habitation?id=${ habitation.id }" class="btn btn-danger"> <i class="icon icon-delete"></i> </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</t:layout>