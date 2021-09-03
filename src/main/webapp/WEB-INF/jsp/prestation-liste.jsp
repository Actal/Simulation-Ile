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
				<th># places</th>
				<th>Heure ouverture</th>
				<th>Heure fermeture</th>
				<th>Prix d'entr�e</th>
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
				<td><input type="number" name="nbPlace" form="0"/></td>
				<td><input type="time"   name="heureOuverture" form="0"/></td>
				<td><input type="time"   name="heureFermeture" form="0"/></td>
				<td><input type="number" name="prixEntree" form="0"/></td>
				<td>
					<select name="proprietaire.id" form="0">
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
			
			<c:forEach items="${ prestations }" var="prestation">
				<form method="POST" id="${ prestation.id }"></form>
				<tr>
					<td>${ prestation.id }<input type="hidden" name="id" form="${ prestation.id }" value="${ prestation.id }" /></td>
					<td><input type="text"   name="nom"					form="${ prestation.id }" value="${ prestation.nom }" /></td>
					<td><input type="number" name="longueur"			form="${ prestation.id }" value="${ prestation.longueur }" /></td>
					<td><input type="number" name="prix"				form="${ prestation.id }" value="${ prestation.prix }" /></td>
					<td><input type="number" name="coutEntretienBase"	form="${ prestation.id }" value="${ prestation.coutEntretienBase }" /></td>
					<td><input type="number" name="nbPlace"				form="${ prestation.id }" value="${ prestation.nbPlace }" /></td>
					<td><input type="time"   name="heureOuverture"		form="${ prestation.id }" value="${ prestation.heureOuverture }" /></td>
					<td><input type="time"   name="heureFermeture"		form="${ prestation.id }" value="${ prestation.heureFermeture }" /></td>
					<td><input type="number" name="prixEntree"			form="${ prestation.id }" value="${ prestation.prixEntree }" /></td>
					<td>
						<select name="proprietaire.id" form="${ prestation.id }">
							<c:forEach items="${ proprietaires }" var="proprietaire">
								<c:if test="${ proprietaire.id != prestation.proprietaire.id }">
									<option value="${ proprietaire.id }">${ proprietaire.nom } ${ proprietaire.prenom }</option>
								</c:if>
							
								<c:if test="${ proprietaire.id == prestation.proprietaire.id }">
									<option value="${ proprietaire.id }" selected>${ proprietaire.nom } ${ proprietaire.prenom }</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td><input type="number" name="x"					form="${ prestation.id }" value="${ prestation.coordonnees.x }" /></td>
					<td><input type="number" name="y"					form="${ prestation.id }" value="${ prestation.coordonnees.y }" /></td>
					<td><button type="submit" class="btn btn-primary"	form="${ prestation.id }"> <i class="icon icon-save"></i> </button></td>
					<td><a href="supprimer-prestation?id=${ prestation.id }" class="btn btn-danger"> <i class="icon icon-delete"></i> </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</t:layout>