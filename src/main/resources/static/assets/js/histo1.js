// Fixer les dimensions et les marges du graphique
const margin = {top: 30, right: 30, bottom: 30, left: 100},
    width = 460 - margin.left - margin.right,
    height = 400 - margin.top - margin.bottom;

// Append l'objet svg au body
const svg = d3.select("#statistiques-area2")
    .append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
    .append("g")
    .attr("transform",
          `translate(${margin.left},${margin.top})`);

// Collecter les donnees
d3.csv("assets/data/OneNum.csv").then( function(data) {

    // Axe X : scale et tracage
    const x = d3.scaleLinear()
        .domain([0, d3.max(data, function(d) { return +d.price; })])     // Au lieu de 1000 on peut utiliser ceci pour avoir le max de donnees : d3.max(data, function(d) { return +d.price })
        .range([0, width]);
    svg.append("g")
        .attr("transform", `translate(0, ${height})`)
        .call(d3.axisBottom(x));
    svg.append("text").attr("class", "x label")
        .attr("text-anchor", "end")
        .attr("x", width + 20)
        .attr("y", height - 6)
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
        .range([height, 0]);
    y.domain([0, d3.max(bins, function(d) { return d.length; })]);      // d3.hist doit etre appele avant l'axe Y
    svg.append("g")
        .call(d3.axisLeft(y));
    svg.append("text")
        .attr("class", "y label")
        .attr("text-anchor", "end")
        .attr("x", -10)
        .attr("y", -20)
        .attr("dy", ".75em")
        //.attr("transform", "rotate(-90)")
        .text("quantite");

    // Append les rectangles a l'element svg
    svg.selectAll("rect")
        .data(bins)
        .join("rect")
        .attr("x", 1)
        .attr("transform", function(d) { return `translate(${x(d.x0)} , ${y(d.length)})`})
        .attr("width", function(d) { return x(d.x1) - x(d.x0) -1})
        .attr("height", function(d) { return height - y(d.length); })
        .style("fill", "#69b3a2")

});