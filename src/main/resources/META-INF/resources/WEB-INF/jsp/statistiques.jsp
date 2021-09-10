<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<link rel="stylesheet" href="assets/css/statistiques.css">

<t:layout>
	<div id="sidebar"></div>
	<div id="statistiques" class="row">
		<div id="statistiques-area">
			<h2>Statistiques de l'île</h2>
			<p>Nombre d'habitants : ${ nbhabitants }</p>
			<p>Nombre de bâtiments : ${ nbbatiments }</p>
			<p>Quantité d'argent totale : ${ argenttotal }</p>
			<p>Quantité d'argent des propriétaires : ${ argentproprietaires }</p>
			<p>Quantité d'argent des non propriétaires : ${ argenttotal.subtract(argentproprietaires) }</p>
		</div>
		<div>
			<h2>Affichage des courbes</h2>
		</div>
		<div id="statistiques-area2">
		</div>
		<!-- <div id="statistiques-area3">
			<h2>Affichage des histogrammes</h2>
		</div> -->
	</div>
	
    <script src="https://d3js.org/d3.v6.js"></script>
	<script src="assets/js/graphArgent.js"></script>
<!--     <script src="assets/js/graphligne2.js"></script> -->
<!--     <script src="assets/js/histo1.js"></script>
    <script src="assets/js/histo2.js"></script> -->
<!--     <script src="assets/js/graphligne1.js"></script> -->
    <!-- <script src="https://d3js.org/d3.v7.min.js"></script> -->
</t:layout>