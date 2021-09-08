<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<link rel="stylesheet" href="assets/css/statistiques.css">

<t:layout>
	<div id="sidebar"></div>
	<div id="statistiques" class="row">
		<div id="statistiques-area">
			<h2>Statistiques de l'�le</h2>
			<p>Nombre d'habitants : ${ nbhabitants }</p>
			<p>Nombre de b�timents : ${ nbbatiments }</p>
			<p>Quantit� d'argent totale : ${ argenttotal }</p>
			<p>Quantit� d'argent des propri�taires : ${ argentproprietaires }</p>
			<p>Quantit� d'argent des non propri�taires : ${ argenttotal.subtract(argentproprietaires) }</p>
		</div>
		<div id="statistiques-area2">
			<h2>Affichage des courbes</h2>
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