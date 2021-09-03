
let caseColor = {"Default":"red", "Prairie": "lightgreen", "Desert":"gold", "Montagne":"lightgray"}

//Store coordinates and associated informations
let coordonnees = [];
for (let y=0; y<100; y++){
    let tmpTab = [];
    for (let x=0; x<100; x++){
        tmpTab.push({nom:"null"});
    }
    coordonnees.push(tmpTab);
}

//Create graphical interface as table for simulation
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

//Add icon on the map with its file name
let area = document.querySelector("#icons");
let addMapIcon = (object) => {

    //Check if image exists. XMLHTTP deprecated
    var http = new XMLHttpRequest();
    http.open('HEAD', `assets/img/${object.nom}.svg`, false);
    http.send();
    let img = "";
    if (http.status != 404){
        img = `assets/img/${object.nom}.svg`;
    }
    else {
        img = `assets/img/BatimentPlaceholder.svg`;
    }

    area.innerHTML += `<img src="${ img }" alt="${object.nom}" id="icon-${object.x}-${object.y}" class="map-icon"/>`;
    let newIcon = document.querySelector(`#icon-${object.x}-${object.y}`);
    newIcon.width = object.longueur*10;
    newIcon.height = object.longueur*10;
    newIcon.style.bottom = `${1000 - object.y*10 - object.longueur*10}px`;
    newIcon.style.left = `${object.x*10}px`;
}

//Initialise objects (buildings, biomes) to add in coordinates and on map
let addCase = (objects) => {
    for (o of objects){
        for (let w=0; w<o.longueur; w++){
            for (let h=0; h<o.longueur; h++){
                let color = "black";
                if (o.nom in caseColor) color = caseColor[o.nom];
                else color = caseColor["Default"];
                document.querySelector(`#coordonnee-${o.x+w}-${o.y+h}`).style.background = color;
                coordonnees[o.x+w][o.y+h] = o;
            }
        } 
        if (objects == batiments) addMapIcon(o);
    }
}
addCase(biomes);
addCase(batiments);

//Display informations in sidebar on mouseover
let eventDisplay = (event) => {
    let sideBar = document.querySelector("#sidebar");
    let coord = event.target.id;
    coord = coord.split("-");
    //Id of td elements are of the form "name-x-y" so we can split coordinates into an array
    let data = coordonnees[parseInt(coord[1])][parseInt(coord[2])];
    let elems = "";
    elems += `<p>x: ${coord[1]}</p>`;
    elems += `<p>y: ${coord[2]}</p>`;
    elems += `<p>nom: ${data.nom}</p>`;
    /*for (attribute in data){
        elems += (`<p>${attribute}: ${data[attribute]}</p>`);
    }*/
    sideBar.innerHTML = elems;
}

//Display for biomes
let tbody = document.querySelector("#simulation-area tbody");
tbody.addEventListener('mouseover', eventDisplay);

//Display for buildings
let icons = document.querySelector("#icons");
icons.addEventListener('mouseover', eventDisplay);

//Display informations when clicking on building
let eventShowBuilding = (event) => {
    let tooltip  = document.querySelector("#tooltip-content");
    let coord = event.target.id;
    coord = coord.split("-");
    //Id of td elements are of the form "name-x-y" so we can split coordinates into an array
    let data = coordonnees[parseInt(coord[1])][parseInt(coord[2])];
    let elems = "";
    for (attribute in data){
        elems += (`<p>${attribute}: ${data[attribute]}</p>`);
    }
    tooltip.innerHTML = elems;
    tooltip.style.top = `${data.y*10 - 1000 + data.longueur*10}px`;
    tooltip.style.left = `${data.x*10}px`;
    tooltip.style.visibility = "visible";
    setTimeout(() => {
        tooltip.style.visibility = "hidden";
    }, 3000);
}
icons.addEventListener("click", eventShowBuilding);