<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="assets/css/simulation.css">

<t:layout>
	<div id="simulation">
		<div id="sidebar">Sidebar</div>
		<div id="simulation-area">
			<table>
				<tbody>

				</tbody>
			</table>
			<div id="icons"></div>
			<div id="tooltip">
				<div id="tooltip-content"></div>
			</div>
		</div>
	</div>
	
	
	<script>

	//Load objects
	let batiments = [
<c:forEach items="${ batiments }" var="batiment">
{
	id:${ batiment.id },
	nom:'${ batiment.nom }',
	x:${ batiment.coordonnees.x },
	y:${ batiment.coordonnees.y },
	longueur:'${ batiment.longueur }',
	prix:${ batiment.prix },
	coutEntretien:${ batiment.coutEntretienBase },
	places:${ batiment.nbPlace }
},
</c:forEach>
	]
	
	let biomes = [
<c:forEach items="${ biomes }" var="biome">
{
	id:${ biome.id },
	nom:'${ biome.type }',
	x:${ biome.coordonnees.x },
	y:${ biome.coordonnees.y },
	longueur:'${ biome.longueur }'
},
</c:forEach>
	]
	</script>

	<script src="assets/js/simulation.js"></script>
</t:layout>