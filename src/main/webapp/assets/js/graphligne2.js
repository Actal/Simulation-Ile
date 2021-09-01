// Fixer les dimensions et les marges du graphique
const margin_ligne2 = {top: 30, right: 30, bottom: 30, left: 100},
    width_ligne2 = 460 - margin_ligne2.left - margin_ligne2.right,
    height_ligne2 = 400 - margin_ligne2.top - margin_ligne2.bottom;

// Append l'objet svg au body
const svg_ligne2 = d3.select("#statistiques-area3")
    .append("svg")
    .attr("width", width_ligne2 + margin_ligne2.left + margin_ligne2.right)
    .attr("height", height_ligne2 + margin_ligne2.top + margin_ligne2.bottom)
    .append("g")
    .attr("transform",
        `translate(${margin_ligne2.left},${margin_ligne2.top})`);

// Collecter les donnees
d3.csv("TwoNumOrdered2.csv",

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
        .range([ 0, width_ligne2 ]);
    svg_ligne2.append("g")
        .attr("transform", `translate(0, ${height_ligne2})`)
        .call(d3.axisBottom(x));
    svg_ligne2.append("text")
        .attr("class", "x label")
        .attr("text-anchor", "end")
        .attr("x", width_ligne2 + 20)
        .attr("y", height_ligne2 - 6)
        .text("t");

    // Ajouter axe Y
    const y = d3.scaleLinear()
        .domain([0, d3.max(data, function(d) { return +d.value; })])
        .range([ height_ligne2, 0 ]);
    svg_ligne2.append("g")
        .call(d3.axisLeft(y));

    // Ajouter la courbe
    svg_ligne2.append("path")
        .datum(data)
        .attr("fill", "none")
        .attr("stroke", "steelblue")
        .attr("stroke-width", 1.5)
        .attr("d", d3.line()
            .x(function(d) { return x(d.date) })
            .y(function(d) { return y(d.value) })
        )
    svg_ligne2.append("text")
        .attr("class", "y label")
        .attr("text-anchor", "end")
        .attr("x", -10)
        .attr("y", -20)
        .attr("dy", ".75em")
        //.attr("transform", "rotate(-90)")
        .text("quantité");

});