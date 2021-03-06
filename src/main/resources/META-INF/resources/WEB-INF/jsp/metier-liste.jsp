<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<link rel="stylesheet" href="assets/css/entityEditor.css">
<link rel="stylesheet" href="assets/css/icon.css">

<t:layout>
	<h1>Métier</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Intitulé</th>
				<th />
				<th />
			</tr>
		</thead>
		<tbody>
			<tr>
				<td></td>
				<td><input  type="text"   name="intitule"			form="0" class="form-control"/></td>
				<td><button type="submit" class="btn btn-primary"	form="0"> <i class="icon icon-save"></i> </button></td>
				<td></td>
			</tr>
			<form method="POST" id=0><input name="${ _csrf.parameterName }" type="hidden" value="${ _csrf.token }" /></form>
			
			<c:forEach items="${ metiers }" var="metier">
				<form method="POST" id="${ metier.id }"><input name="${ _csrf.parameterName }" type="hidden" value="${ _csrf.token }" /></form>
				<tr>
					<td>${ metier.id }<input type="hidden" name="id" form="${ metier.id }" value="${ metier.id }" /></td>
					<td><input  type="text"   name="intitule"         form="${ metier.id }" value="${ metier.intitule }" class="form-control"/></td>
					<td><button type="submit" class="btn btn-primary" form="${ metier.id }"> <i class="icon icon-save"></i> </button></td>
					<td><a href="supprimer-metier?id=${ metier.id }" class="btn btn-danger"> <i class="icon icon-delete"></i> </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</t:layout>