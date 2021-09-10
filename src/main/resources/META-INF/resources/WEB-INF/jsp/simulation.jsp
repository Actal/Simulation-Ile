<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
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