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
				<th>Type</th>
				<th>Longueur</th>
				<th>X</th>
				<th>Y</th>
				<th />
				<th />
			</tr>
		</thead>
		<tbody>
			<tr>
				<td></td>
				<td><input type="text" name="type" form="0"/></td>
				<td><input type="number" name="longueur" form="0"/></td>
				<td><input type="number" name="x" form="0"/></td>
				<td><input type="number" name="y" form="0"/></td>
				<td><button type="submit" class="btn btn-primary" form="0"> <i class="icon icon-save"></i> </button></td>
				<td></td>
			</tr>
			<form method="POST" id=0></form>
			
			<c:forEach items="${ biomes }" var="biome">
				<form method="POST" id="${ biome.id }"></form>
				<tr>
					<td>${ biome.id }<input type="hidden" name="id"		form="${ biome.id }" value="${ biome.id }" /></td>
					<td><input type="text"   name="type"				form="${ biome.id }" value="${ biome.type }" /></td>
					<td><input type="number" name="longueur"			form="${ biome.id }" value="${ biome.longueur }" /></td>
					<td><input type="number" name="x"					form="${ biome.id }" value="${ biome.coordonnees.x }" /></td>
					<td><input type="number" name="y"					form="${ biome.id }" value="${ biome.coordonnees.y }" /></td>
					<td><button type="submit" class="btn btn-primary"	form="${ biome.id }"> <i class="icon icon-save"></i> </button></td>
					<td><a href="supprimer-biome?id=${ biome.id }" class="btn btn-danger"> <i class="icon icon-delete"></i> </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</t:layout>