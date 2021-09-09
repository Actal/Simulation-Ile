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

	<script src="assets/js/simulation.js"></script>
</t:layout>