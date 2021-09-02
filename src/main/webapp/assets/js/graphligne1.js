// Fixer les dimensions et les marges du graphique
const margin_ligne = {top: 30, right: 30, bottom: 30, left: 100},
    width_ligne = 460 - margin_ligne.left - margin_ligne.right,
    height_ligne = 400 - margin_ligne.top - margin_ligne.bottom;

// Append l'objet svg au body
const svg_ligne = d3.select("#statistiques-area3")
    .append("svg")
    .attr("width", width_ligne + margin_ligne.left + margin_ligne.right)
    .attr("height", height_ligne + margin_ligne.top + margin_ligne.bottom)
    .append("g")
    .attr("transform",
        `translate(${margin_ligne.left},${margin_ligne.top})`);

var fileURL = "assets/data/TwoNumOrdered.csv";

// Collecter les donnees
d3.csv(fileURL,

    // Lisant le csv, formater les variables :
    function(d){
        return { date : d3.timeParse("%Y-%m-%d")(d.date), value : d.value }
    }
).then(

    // Maintenant on peut utiliser ce dataset :
    function(data) {

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
        .domain([0, d3.max(data, function(d) { return +d.value; })])
        .range([ height_ligne, 0 ]);
    svg_ligne.append("g")
        .call(d3.axisLeft(y));

    // Ajouter la courbe
    svg_ligne.append("path")
        .datum(data)
        .attr("fill", "none")
        .attr("stroke", "steelblue")
        .attr("stroke-width", 1.5)
        .attr("d", d3.line()
            .x(function(d) { return x(d.date) })
            .y(function(d) { return y(d.value) })
        )
    svg_ligne.append("text")
        .attr("class", "y label")
        .attr("text-anchor", "end")
        .attr("x", -10)
        .attr("y", -20)
        .attr("dy", ".75em")
        //.attr("transform", "rotate(-90)")
        .text("argent");

});

var request = new XMLHttpRequest(); 
request.open("GET", fileURL, false);   
request.send(null);  

var csvData = new Array();
var tab = request.responseText.split(/\r?\n|\r/);

// const fs = require('fs');
// var tab = fs.readFileSync(fileURL).toString().split('\r\n');

for (var i = 0; i < tab.length; i++) {
    csvData.push(tab[i].split(','));
}
var lastval = csvData[tab.length-1][1];

document.querySelector("#argent-total").innerHTML = lastval;