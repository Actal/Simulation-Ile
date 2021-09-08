// Fixer les dimensions et les marges du graphique
const margin2 = {top: 30, right: 30, bottom: 30, left: 100},
    width2 = 460 - margin2.left - margin2.right,
    height2 = 400 - margin2.top - margin2.bottom;

// Append l'objet svg au body
const svg2 = d3.select("#statistiques-area2")
    .append("svg")
    .attr("width", width2 + margin2.left + margin2.right)
    .attr("height", height2 + margin2.top + margin2.bottom)
    .append("g")
    .attr("transform",
          `translate(${margin2.left},${margin2.top})`);

// Collecter les donnees
d3.csv("assets/data/OneNum2.csv").then( function(data) {

    // Axe X : scale et tracage
    const x = d3.scaleLinear()
        .domain([0, d3.max(data, function(d) { return +d.price })])     // Au lieu de 1000 on peut utiliser ceci pour avoir le max de donnees : d3.max(data, function(d) { return +d.price })
        .range([0, width2]);
    svg2.append("g")
        .attr("transform", `translate(0, ${height2})`)
        .call(d3.axisBottom(x));
    svg2.append("text").attr("class", "x label")
        .attr("text-anchor", "end")
        .attr("x", width2 + 20)
        .attr("y", height2 - 6)
        .text("prix");

    // Fixe les parametres pour l'histogramme
    const histogram = d3.histogram()
        .value(function(d) { return d.price; })   // on doit renseigner le vecteur des valeurs
        .domain(x.domain())  // ensuite le domaine du graphique
        .thresholds(x.ticks(50)); // ensuite le nombre de bins

    // Et appliquer cette fonction aux donnees pour obtenir les bins
    const bins = histogram(data);

    // Axe Y : scale et tracage
    const y = d3.scaleLinear()
        .range([height2, 0]);
    y.domain([0, d3.max(bins, function(d) { return d.length; })]);      // d3.hist doit etre appele avant l'axe Y
    svg2.append("g")
        .call(d3.axisLeft(y));
    svg2.append("text")
        .attr("class", "y label")
        .attr("text-anchor", "end")
        .attr("x", -10)
        .attr("y", -20)
        .attr("dy", ".75em")
        //.attr("transform", "rotate(-90)")
        .text("quantite");

    // Append les rectangles a l'element svg
    svg2.selectAll("rect")
        .data(bins)
        .join("rect")
        .attr("x", 1)
        .attr("transform", function(d) { return `translate(${x(d.x0)} , ${y(d.length)})`})
        .attr("width", function(d) { return x(d.x1) - x(d.x0) -1})
        .attr("height", function(d) { return height - y(d.length); })
        .style("fill", "#69b3a2")

});