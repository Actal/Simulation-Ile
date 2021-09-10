<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<link rel="stylesheet" href="assets/css/entityEditor.css">
<link rel="stylesheet" href="assets/css/icon.css">


<t:layout>
	<h1>${ nameEntity }</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Date de naissance</th>
				<th>Argent</th>
				<th>Sexe</th>
				<th />
				<th />
			</tr>
		</thead>
		<tbody>
			<tr>
				<td></td>
				<td><input type="text"		name="nom"				form="0" class="form-control"/></td>
				<td><input type="text"		name="prenom"			form="0" class="form-control"/></td>
				<td><input type="date"		name="dateNaissance"	form="0" class="form-control"/></td>
				<td><input type="number"	name="argent"			form="0" class="form-control"/></td>
				<td><select 				name="sexe"				form="0" class="form-select">
						<option value="Homme">Homme</option>
						<option value="Femme" selected>Femme</option>
					</select>
				</td>
				<td><button type="submit" class="btn btn-primary" form="0"> <i class="icon icon-save"></i> </button></td>
				<td></td>
			</tr>
			<form method="POST" id=0><input name="${ _csrf.parameterName }" type="hidden" value="${ _csrf.token }" /></form>
			
			<c:forEach items="${ citoyens }" var="citoyen">
				<form method="POST" id="${ citoyen.id }"><input name="${ _csrf.parameterName }" type="hidden" value="${ _csrf.token }" /></form>
				<tr>
					<td>${ citoyen.id }<input type="hidden" name="id" form="${ citoyen.id }" value="${ citoyen.id }" /></td>
					<td><input type="text"   name="nom"				form="${ citoyen.id }" value="${ citoyen.nom }" 			class="form-control"/></td>
					<td><input type="text"   name="prenom"			form="${ citoyen.id }" value="${ citoyen.prenom }"			class="form-control"/></td>
					<td><input type="date"   name="dateNaissance"	form="${ citoyen.id }" value="${ citoyen.dateNaissance }"	class="form-control"/></td>
					<td><input type="number" name="argent"			form="${ citoyen.id }" value="${ citoyen.argent }" 			class="form-control"/></td>
					<td><select 			 name="sexe"			form="${ citoyen.id }" 										class="form-select">
							<c:if test="${ citoyen.sexe.isHomme()}">
								<option value="Homme" selected>Homme</option>
								<option value="Femme">Femme</option>
							</c:if>
							<c:if test="${ citoyen.sexe.isFemme() }">
								<option value="Homme">Homme</option>
								<option value="Femme" selected>Femme</option>
							</c:if>
						</select>
					</td>
					<td><button type="submit" class="btn btn-primary" form="${ citoyen.id }"> <i class="icon icon-save"></i> </button></td>

					<c:if test="${ nameEntity == 'Citoyen' }">
						<td><a href="supprimer-citoyen?id=${ citoyen.id }" class="btn btn-danger"> <i class="icon icon-delete"></i> </a></td>
					</c:if>
					<c:if test="${ nameEntity != 'Citoyen' }">
						<td><a href="supprimer-proprietaire?id=${ citoyen.id }" class="btn btn-danger"> <i class="icon icon-delete"></i> </a></td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</t:layout>