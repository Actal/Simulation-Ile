
//Load objects
let batiments = [
    {
        id: 1,
        nom: "Usine",
        x: 5,
        y: 5,
        superficie: 2
    },
    {
        id: 2,
        nom: "Ecole",
        x: 20,
        y: 37,
        superficie: 1
    },
    {
        id: 3,
        nom: "Caserne",
        x: 51,
        y: 60,
        superficie: 3
    }
]

let biomes = [
    {
        id: 1,
        nom: "Prairie",
        x: 0,
        y: 0,
        superficie: 100
    },
    {
        id: 2,
        nom: "Desert",
        x: 10,
        y: 30,
        superficie: 30
    },
    {
        id: 3,
        nom: "Montagne",
        x: 40,
        y: 20,
        superficie: 20
    }
]
let caseColor = {"Default":"red", "Prairie": "lightgreen", "Desert":"lightyellow", "Montagne":"lightgray"}



//Store coordinates and associated informations
let coordonnees = [];
for (let y=0; y<100; y++){
    let tmpTab = [];
    for (let x=0; x<100; x++){
        tmpTab.push({nom:"null"});
    }
    coordonnees.push(tmpTab);
}

//Create graphical interface as array for simulation
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

//Initialise objects (buildings, biomes) to add in coordinates and on map
let addCase = (object) => {
    for (o of object){
        for (let w=0; w<o.superficie; w++){
            for (let h=0; h<o.superficie; h++){
                let color = "black";
                if (o.nom in caseColor) color = caseColor[o.nom];
                else color = caseColor["Default"];
                document.querySelector(`#coordonnee-${o.x+w}-${o.y+h}`).style.backgroundColor = color;
                coordonnees[o.x+w][o.y+h] = o;
            }
        }
        
    }
}
addCase(biomes);
addCase(batiments);
//Initialize biomes
/*
for (bi of biomes){
    for (let w=0; w<bi.width; w++){
        for (let h=0; h<bi.width; h++){
            document.querySelector(`#coordonnee-${bi.x+w}-${bi.y+h}`).style.backgroundColor = caseColor[bi.nom];
            coordonnees[bi.x+w][bi.y+h] = bi;
        }
    }
    
}

//Initialize buildings and add to coordinates when loading page
for (b of batiments){
    document.querySelector(`#coordonnee-${b.x}-${b.y}`).style.backgroundColor = "red";
    coordonnees[b.x][b.y] = b;
}
*/

//Display informations in sidebar on mouseover
let tbody = document.querySelector("#simulation-area tbody");
tbody.addEventListener('mouseover', (event) => {
    let sideBar = document.querySelector("#sidebar");
    let coord = event.target.id;
    coord = coord.split("-");
    //Id of td elements are of the form "coordonnees-x-y" so we can split coordinates into an array
    let data = coordonnees[parseInt(coord[1])][parseInt(coord[2])];
    let elems = "";
    elems += `<p>x: ${coord[1]}</p>`;
    elems += `<p>y: ${coord[2]}</p>`;
    elems += `<p>nom: ${data.nom}</p>`;
    /*for (attribute in data){
        elems += (`<p>${attribute}: ${data[attribute]}</p>`);
    }*/
    sideBar.innerHTML = elems;
});

