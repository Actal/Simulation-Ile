<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<link rel="stylesheet" href="assets/css/entityEditor.css">
<link rel="stylesheet" href="assets/css/icon.css">

<t:layout>
	<h1>Poste</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Salaire</th>
				<th>Métier</th>
				<th>Workplace</th>
				<th />
				<th />
			</tr>
		</thead>
		<tbody>
			<tr>
				<td></td>
				<td><input type="number"	name="salaire"		form="0" class="form-control"/></td>
				<td><select					name="metier.id"	form="0" class="form-select">
					<c:forEach items="${ metiers }" var="metier">
						<option value="${ metier.id }">${ metier.intitule }</option>
					</c:forEach>
				</select></td>
				<td><select 				name="workplace.id"	form="0" class="form-select">
					<c:forEach items="${ workplaces }" var="workplace">
						<option value="${ workplace.id }">${ workplace.nom }</option>
					</c:forEach>
				</select></td>
				<td><button type="submit" class="btn btn-primary" form="0"> <i class="icon icon-save"></i> </button></td>
				<td></td>
			</tr>
			<form method="POST" id=0><input name="${ _csrf.parameterName }" type="hidden" value="${ _csrf.token }" /></form>
			
			<c:forEach items="${ postes }" var="poste">
				<form method="POST" id="${ poste.id }"><input name="${ _csrf.parameterName }" type="hidden" value="${ _csrf.token }" /></form>
				<tr>
					<td>${ poste.id }<input type="hidden" name="id" form="${ poste.id }" value="${ poste.id }" /></td>
					<td><input type="number"	name="salaire"		form="${ poste.id }" value="${ poste.salaire }" class="form-control"/></td>
					<td><select					name="metier.id"	form="${ poste.id }" 							class="form-select">
						<c:forEach items="${ metiers }" var="metier">
							<c:if test="${ metier.id != poste.metier.id }">
								<option value="${ metier.id }">${ metier.intitule }</option>
							</c:if>
							<c:if test="${ metier.id == poste.metier.id }">
								<option value="${ metier.id }" selected>${ metier.intitule }</option>
							</c:if>
						</c:forEach>
					</select></td>
					<td><select					name="workplace.id"	form="${ poste.id }"							class="form-select">
						<c:forEach items="${ workplaces }" var="workplace">
							<c:if test="${ workplace.id != poste.workplace.id }">
								<option value="${ workplace.id }">${ workplace.nom }</option>
							</c:if>
							<c:if test="${ workplace.id == poste.workplace.id }">
								<option value="${ workplace.id }" selected>${ workplace.nom }</option>
							</c:if>
						</c:forEach>
					</select></td>
					<td><button type="submit" class="btn btn-primary"	form="${ poste.id }"> <i class="icon icon-save"></i> </button></td>
					<td><a href="supprimer-poste?id=${ poste.id }" class="btn btn-danger"> <i class="icon icon-delete"></i> </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</t:layout>