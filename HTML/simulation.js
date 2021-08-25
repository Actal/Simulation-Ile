
//Stocke les coordonnees avec les informations associees
let coordonnees = [];
for (let y=0; y<100; y++){
    let tmpTab = [];
    for (let x=0; x<100; x++){
        tmpTab.push({nom:"null"});
    }
    coordonnees.push(tmpTab);
}

let batiments = [
    {
        id: 1,
        nom: "Usine",
        x: 5,
        y: 5
    },
    {
        id: 2,
        nom: "Ecole",
        x: 20,
        y: 37
    },
    {
        id: 3,
        nom: "Caserne",
        x: 51,
        y: 60
    }
]

//Cree le tableau pour la simulation
let table = document.querySelector("#simulation-area table");
let stringTab = "";
for (y in coordonnees){
    let classLine = y%2;
    stringTab += `<tr class="line-${classLine}">`;
    for (x in coordonnees){
        let classColumn = x%2;
        stringTab += `<td id="coordonnee-${x}-${y}"></td>`;
    }
    stringTab += "</tr>";
}
table.innerHTML = stringTab;

//Initialise les batiments dans les donnees de la page
for (b of batiments){
    document.querySelector(`#coordonnee-${b.x}-${b.y}`).style.backgroundColor = "red";
    coordonnees[b.x][b.y] = b;
}

//Affiche information en passant avec la souris
let tbody = document.querySelector("#simulation-area tbody");
tbody.addEventListener('mouseover', (event) => {
    let elem = document.querySelector("#sidebar");
    let coord = event.target.id;
    coord = coord.split("-");
    elem.textContent = coordonnees[parseInt(coord[1])][parseInt(coord[2])].nom;
    //event.target.style.backgroundColor = "blue";
});

