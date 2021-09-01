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
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
	integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp"
	crossorigin="anonymous"></script>
	<link rel="stylesheet" href="nav.css">

</head>
<body>
    <div id="main">
        <nav id="navbar" class="navbar-nav">
            <div class="container-fluid">
                <a class="navbar-brand" href="Simulation-Ile/simulation.html">Main page</a>
                <a class="navbar-brand" href="Simulation-Ile/statistiques.html">Stats</a>
                <a class="navbar-brand" href="Simulation-Ile/entityEditor.html">Edition</a>
            </div>
        </nav>
        <div id="content">
            <jsp:doBody></jsp:doBody>
        </div>
    </div>
    <script src="simulation.js"></script>
</body>
</html>