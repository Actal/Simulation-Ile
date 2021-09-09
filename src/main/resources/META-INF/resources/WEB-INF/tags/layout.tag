<%@ attribute name="title"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Simulation</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="assets/css/nav.css">

</head>
<body>
	<div id="main">
		<nav class="navbar navbar-expand-md navbar-light bg-light fixed-top">
			<div class="container-fluid">
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="simulation">Main page</a></li>
						<li class="nav-item"><a class="nav-link" href="statistiques">Statistiques</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="editionMenu"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">
								Edition </a>
							<ul class="dropdown-menu" aria-labelledby="editionMenu">
							<li><a class="dropdown-item" href="liste-biomes">Biome</a></li>
								<li><a class="dropdown-item" href="liste-citoyens">Citoyen</a></li>
								<li><a class="dropdown-item" href="liste-habitations">Habitation</a></li>
								<li><a class="dropdown-item" href="liste-metiers">Metier</a></li>
								<li><a class="dropdown-item" href="liste-postes">Poste</a></li>
								<li><a class="dropdown-item" href="liste-prestations">Prestation</a></li>
								<li><a class="dropdown-item" href="liste-proprietaires">Propri�taire</a></li>
								<li><a class="dropdown-item" href="liste-workplaces">Workplace</a></li>
							</ul>
						</li>
					</ul>
					<div>
						<span class="navbar-text" id="dateTimeSim"></span>
					</div>
				</div>
			</div>
		</nav>
		<div id="content">
			<jsp:doBody></jsp:doBody>
		</div>
	</div>
</body>
<script src="assets/js/navbar.js"></script>
</html>