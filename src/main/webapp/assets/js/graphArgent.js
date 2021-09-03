// Fixer les dimensions et les marges du graphique
const margin_ligne = {top: 30, right: 30, bottom: 30, left: 100},
    width_ligne = 1.5*460 - margin_ligne.left - margin_ligne.right,
    height_ligne = 1.5*400 - margin_ligne.top - margin_ligne.bottom;

// Append l'objet svg au body
const svg_ligne = d3.select("#statistiques-area2")
    .append("svg")
    .attr("width", width_ligne + margin_ligne.left + margin_ligne.right)
    .attr("height", height_ligne + margin_ligne.top + margin_ligne.bottom)
    .append("g")
    .attr("transform",
        `translate(${margin_ligne.left},${margin_ligne.top})`);

/*// Fixer les dimensions et les marges du graphique
const margin_legend = {top: 5, right: 5, bottom: 5, left: 5},
    width_legend = 200 - margin_legend.left - margin_legend.right,
    height_legend = 80 - margin_legend.top - margin_legend.bottom;

// Append l'objet svg au body
const svg_legend = d3.select("#sidebar")
    .append("svg")
    .attr("width", width_legend + margin_legend.left + margin_legend.right)
    .attr("height", height_legend + margin_legend.top + margin_legend.bottom)
    .append("g")
    .attr("transform",
        `translate(${margin_legend.left},${margin_legend.top})`);*/

var fileURL = "assets/data/argentsomme2.csv";

// Collecter les donnees
d3.csv(fileURL,

    // Lisant le csv, formater les variables :
    function(d){
        return { date : d3.timeParse("%Y-%m-%d")(d.date), value : d.value, value2 : d.value2, value3 : d.value3 }
    }
).then(function(data) {
	
	// Liste de groupes
	const keys = data.columns.slice(1)
	
    // Ajouter axe X --> c'est en format date
    const x = d3.scaleTime()
        .domain(d3.extent(data, function(d) { return d.date; }))
        .range([ 0, width_ligne ]);
    svg_ligne.append("g")
        .attr("transform", `translate(0, ${height_ligne})`)
        .call(d3.axisBottom(x));
    svg_ligne.append("text")
        .attr("class", "x label")
        .attr("text-anchor", "end")
        .attr("x", width_ligne + 20)
        .attr("y", height_ligne - 6)
        .text("t");

    // Ajouter axe Y
    const y = d3.scaleLinear()
        .domain([0, d3.max(data, function(d) { return +d.value3; })])
        .range([ height_ligne, 0 ]);
    svg_ligne.append("g")
        .call(d3.axisLeft(y));

    // Ajouter la courbe
    svg_ligne
		.append("path")
		.datum(data)
        .attr("fill", "none")
        .attr("stroke", "steelblue")
        .attr("stroke-width", 1.5)
        .attr("d", d3.line()
            .x(function(d) { return x(d.date) })
            .y(function(d) { return y(d.value) })
        )

    svg_ligne
		.append("path")
		.datum(data)
        .attr("fill", "none")
        .attr("stroke", "red")
        .attr("stroke-width", 1.5)
        .attr("d", d3.line()
            .x(function(d) { return x(d.date) })
            .y(function(d) { return y(d.value2) })
        )

    svg_ligne
		.append("path")
		.datum(data)
        .attr("fill", "none")
        .attr("stroke", "lightgreen")
        .attr("stroke-width", 1.5)
        .attr("d", d3.line()
            .x(function(d) { return x(d.date) })
            .y(function(d) { return y(d.value3) })
        )

    svg_ligne.append("text")
        .attr("class", "y label")
        .attr("text-anchor", "end")
        .attr("x", -10)
        .attr("y", -20)
        .attr("dy", ".75em")
        //.attr("transform", "rotate(-90)")
        .text("argent");

	svg_ligne.append("circle")
		.attr("cx",360)
		.attr("cy",30)
		.attr("r", 6)
		.style("fill", "steelblue")
	svg_ligne.append("circle")
		.attr("cx",360)
		.attr("cy",60)
		.attr("r", 6)
		.style("fill", "red")
	svg_ligne.append("circle")
		.attr("cx",360)
		.attr("cy",90)
		.attr("r", 6)
		.style("fill", "lightgreen")
	svg_ligne.append("text")
		.attr("x", 380)
		.attr("y", 30)
		.text("argent des non proprietaires")
		.style("font-size", "15px")
		.attr("alignment-baseline","middle")
	svg_ligne.append("text")
		.attr("x", 380)
		.attr("y", 60)
		.text("argent des proprietaires")
		.style("font-size", "15px")
		.attr("alignment-baseline","middle")
	svg_ligne.append("text")
		.attr("x", 380)
		.attr("y", 90)
		.text("argent total")
		.style("font-size", "15px")
		.attr("alignment-baseline","middle")

});

/*	svg_legend.append("circle")
		.attr("cx",10)
		.attr("cy",20)
		.attr("r", 6)
		.style("fill", "steelblue")
	svg_legend.append("circle")
		.attr("cx",10)
		.attr("cy",40)
		.attr("r", 6)
		.style("fill", "red")
	svg_legend.append("circle")
		.attr("cx",10)
		.attr("cy",60)
		.attr("r", 6)
		.style("fill", "lightgreen")
	svg_legend.append("text")
		.attr("x", 30)
		.attr("y", 20)
		.text("argent des non proprietaires")
		.style("font-size", "15px")
		.attr("alignment-baseline","middle")
	svg_legend.append("text")
		.attr("x", 30)
		.attr("y", 40)
		.text("argent des proprietaires")
		.style("font-size", "15px")
		.attr("alignment-baseline","middle")
	svg_legend.append("text")
		.attr("x", 30)
		.attr("y", 60)
		.text("argent total")
		.style("font-size", "15px")
		.attr("alignment-baseline","middle")*/
