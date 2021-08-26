let entities_type = [
    { id: "0", type: "Citoyen", funTable: tableCitoyen, funRow: rowCitoyen },
    { id: "1", type: "Habitation", funTable: tableHabitation, funRow: rowHabitation },
    { id: "2", type: "Proprietaire", funTable: tableProprietaire, funRow: rowCitoyen },
    { id: "3", type: "Service", funTable: tableService, funRow: rowService },
    { id: "4", type: "Workplace", funTable: tableWorkplace, funRow: rowWorkplace }
];

let entities = [
    [
        { Id: "1", Nom: "TEST", Prenom: "Test", DateNaissance: "2001-01-01", Sexe: "Homme", Argent: "100" },
        { Id: "2", Nom: "TRUC", Prenom: "Truc", DateNaissance: "1356-01-06", Sexe: "Femme", Argent: "1000000" },
        { Id: "3", Nom: "sdf", Prenom: "Trusdfzsc", DateNaissance: "13456-01-06", Sexe: "Femme", Argent: "100000000" }
    ],
    [
        {Id: "1", Nom: "Maison", Superficie: "5", Prix: "300", coutEntretienBase: "50", Loyer: "60", nbPlace: "4"}
    ],
    [
        { Id: "2", Nom: "DICTATEUR", Prenom: "Jean", DateNaissance: "1356-01-06", Sexe: "Femme", Argent: "10000000000000000" },
    ],
    [
        {Id: "1", Nom: "Bar", PrixEntree: "5", Superficie: "5", Prix: "300", coutEntretienBase: "50", nbPlace: "4", hOuv: "10:50", hFer: "19:54"}
    ],
    [
        {Id: "1", Nom: "Usine", Superficie: "5", Prix: "300", coutEntretienBase: "50", nbPlace: "4", hOuv: "10:50", hFer: "19:54"}
    ],
];

for (let e of entities_type) {
    let option = document.createElement('option');
    option.setAttribute('value', e.id);
    option.textContent = e.type;

    document.querySelector('#entity-type')
        .append(option);
}

document.querySelector('#entity-type').addEventListener('change', () => {
    select = document.querySelector('#entity-type').value;
    let entity_type = entities_type.find(element => element.id == select);

    emptyTable();
    if (entity_type != undefined) { // dans le cas ou "Selectionner un type d'entite a éditer" est choisi dans la liste deroulante
        entity_type.funTable(entity_type);
    }
});

function emptyTable() {
    document.querySelector("tbody").innerHTML = "";
    document.querySelector("thead tr").innerHTML = "";
}

function createHeaderCell(value) {
    let cell = document.createElement("th");
    cell.textContent = value;
    return cell;
}

function createSimpleCell(value) {
    let cell = document.createElement("td");
    cell.textContent = value;
    return (cell);
}

function createCellNum(name, idForm, value, step="0.01", min="0") {
    let cell = document.createElement("td");
    
    let inp = document.createElement("input");
    inp.setAttribute("type", "number");
    inp.setAttribute("id", name);
    inp.setAttribute("name", name);
    inp.setAttribute("step", step);
    inp.setAttribute("min", min);
    inp.setAttribute("form", idForm);
    inp.setAttribute("value", value);

    cell.append(inp);
    return(cell);
}

function createCellTxt(name, idForm, value) {
    let cell = document.createElement("td");
    
    let inp = document.createElement("input");
    inp.setAttribute("type", "text");
    inp.setAttribute("id", name);
    inp.setAttribute("name", name);
    inp.setAttribute("form", idForm);
    inp.setAttribute("value", value);

    cell.append(inp);
    return(cell);
}

function createCellDate(name, idForm, value) {
    let cell = document.createElement("td");
    
    let inp = document.createElement("input");
    inp.setAttribute("type", "date");
    inp.setAttribute("id", name);
    inp.setAttribute("name", name);
    inp.setAttribute("form", idForm);
    inp.setAttribute("value", value);

    cell.append(inp);
    return(cell);
}

function createCellTime(name, idForm, value) {
    let cell = document.createElement("td");
    
    let inp = document.createElement("input");
    inp.setAttribute("type", "time");
    inp.setAttribute("id", name);
    inp.setAttribute("name", name);
    inp.setAttribute("form", idForm);
    inp.setAttribute("value", value);

    cell.append(inp);
    return(cell);
}

function createCellSubmit(idForm, newRow=false) {
    let cell = document.createElement("td");
    
    let btn = document.createElement("button");
    btn.setAttribute("class", "btn btn-primary");
    btn.setAttribute("type", "submit")
    btn.setAttribute("form", idForm);
    btn.setAttribute("onclick", "return false;");
    btn.innerHTML = '<i class="icon icon-save"></i>';

    if(newRow) {
        btn.addEventListener('click', () => {
            select = document.querySelector('#entity-type').value;
            let entity_type = entities_type.find(element => element.id == select);
            document.querySelector("table tbody").append( entity_type.funRow({Id: "NEW"}));
            // console.log(document.getElementById("0").elements);
            return false;
        });
    }
    
    cell.append(btn);
    return(cell);
}

function addDelBtn(row) {
    btn_del = document.createElement('button');
    btn_del.setAttribute("class", "btn btn-danger");
    btn_del.innerHTML = '<i class="icon icon-delete"/>';
    btn_del.addEventListener('click', () => {
        row.parentNode.removeChild(row);
    });

    col_btn_del = document.createElement('td');
    col_btn_del.append(btn_del);
    row.append(col_btn_del);
}

function rowCitoyen(citoyen) {

    let cit_defined = citoyen != undefined;
    let c = citoyen;
    if( !cit_defined ) {
        c = {
            Id: "0",
            Nom: "",
            Prenom: "",
            Argent: "",
            Sexe: "Femme",
            DateNaissance: ""
        };
    }

    let f = document.createElement("form");
    f.setAttribute("method", "POST");
    f.setAttribute("id", c.Id);

    let row = document.createElement('tr');

    row.append(createSimpleCell((c.Id == "0")?"":c.Id));
    row.append(createCellTxt("nom", c.Id, c.Nom));
    row.append(createCellTxt("prenom", c.Id, c.Prenom));
    row.append(createCellDate("dateNaissance", c.Id, c.DateNaissance));
    row.append(createCellNum("argent", c.Id, c.Argent));

    let sel = document.createElement("select");
    sel.setAttribute("class", "form-select ");
    sel.setAttribute("id", "sexe");
    sel.setAttribute("name", "sexe");
    sel.setAttribute("form", c.Id);
    if ( cit_defined && c.Sexe === "Homme") {
        sel.innerHTML ='<option value="homme" selected>Homme</option><option value="femme">Femme</option>';
    } else {
        sel.innerHTML ='<option value="homme">Homme</option><option value="femme" selected>Femme</option>';
    }
    let cell = document.createElement("td");
    cell.append(sel);
    row.append(cell);
    
    if( cit_defined ) {
        row.append(createCellSubmit(c.Id, false));
    } else {
        row.append(createCellSubmit(c.Id, true));
    }
    
    row.append(f);

    if( cit_defined ) {
        addDelBtn(row);
    } else {
        row.append(createSimpleCell(""));
    }

    return (row);
}

function rowHabitation(habitation) {
    let hab_defined = habitation != undefined;
    let h = habitation;
    if( !hab_defined ) {
        h = {
            Id: "0",
            Nom: "",
            Superficie: "",
            Prix: "",
            coutEntretienBase: "",
            Loyer: "",
            nbPlace: ""
        };
    }

    let f = document.createElement("form");
    f.setAttribute("method", "POST");
    f.setAttribute("id", h.Id);

    let row = document.createElement('tr');

    row.append(createSimpleCell((h.Id == "0")?"":h.Id));
    row.append(createCellTxt("nom", h.Id, h.Nom));
    row.append(createCellNum("superficie", h.Id, h.Superficie, step=1));
    row.append(createCellNum("prix", h.Id, h.Prix));
    row.append(createCellNum("cout-entretien-base", h.Id, h.coutEntretienBase));
    row.append(createCellNum("loyer", h.Id, h.Loyer));
    row.append(createCellNum("nb-places", h.Id, h.nbPlace, step=1));

    if( hab_defined ) {
        row.append(createCellSubmit(h.Id, false));
    } else {
        row.append(createCellSubmit(h.Id, true));
    }
    
    row.append(f);

    if( hab_defined ) {
        addDelBtn(row);
    } else {
        row.append(createSimpleCell(""));
    }

    return (row);

}

function rowWorkplace(workplace) {
    let wor_defined = workplace != undefined;
    let w = workplace;
    if( !wor_defined ) {
        w = {
            Id: "0",
            Nom: "",
            Superficie: "",
            Prix: "",
            coutEntretienBase: "",
            nbPlace: "",
            hOuv: "",
            hFer: ""
        };
    }

    let f = document.createElement("form");
    f.setAttribute("method", "POST");
    f.setAttribute("id", w.Id);

    let row = document.createElement('tr');

    row.append(createSimpleCell((w.Id == "0")?"":w.Id));
    row.append(createCellTxt("nom", w.Id, w.Nom));
    row.append(createCellNum("superficie", w.Id, w.Superficie, step=1));
    row.append(createCellNum("prix", w.Id, w.Prix));
    row.append(createCellNum("cout-entretien-base", w.Id, w.coutEntretienBase));
    row.append(createCellNum("nb-places", w.Id, w.nbPlace, step=1));
    row.append(createCellTime("heure-ouv", w.Id, w.hOuv));
    row.append(createCellTime("heure-ouv", w.Id, w.hFer));
    
    if( wor_defined ) {
        row.append(createCellSubmit(w.Id, false));
    } else {
        row.append(createCellSubmit(w.Id, true));
    }

    row.append(f);

    if( wor_defined ) {
        addDelBtn(row);
    } else {
        row.append(createSimpleCell(""));
    }

    return (row);

}

function rowService(service) {
    let ser_defined = service != undefined;
    let s = service;
    if( !ser_defined ) {
        s = {
            Id: "0",
            Nom: "",
            PrixEntree: "",
            Superficie: "",
            Prix: "",
            coutEntretienBase: "",
            nbPlace: "",
            hOuv: "",
            hFer: ""
        };
    }

    let f = document.createElement("form");
    f.setAttribute("method", "POST");
    f.setAttribute("id", s.Id);

    let row = document.createElement('tr');

    row.append(createSimpleCell((s.Id == "0")?"":s.Id));
    row.append(createCellTxt("nom", s.Id, s.Nom));
    row.append(createCellNum("prix-entree", s.Id, s.PrixEntree));
    row.append(createCellNum("superficie", s.Id, s.Superficie, step=1));
    row.append(createCellNum("prix", s.Id, s.Prix));
    row.append(createCellNum("cout-entretien-base", s.Id, s.coutEntretienBase));
    row.append(createCellNum("nb-places", s.Id, s.nbPlace, step=1));
    row.append(createCellTime("heure-ouv", s.Id, s.hOuv));
    row.append(createCellTime("heure-ouv", s.Id, s.hFer));
    
    if( ser_defined ) {
        row.append(createCellSubmit(s.Id, false));
    } else {
        row.append(createCellSubmit(s.Id, true));
    }
    
    row.append(f);

    if( ser_defined ) {
        addDelBtn(row);
    } else {
        row.append(createSimpleCell(""));
    }

    return (row);

}

function tableCitoyen() {

    let entity_type = entities_type.find(element => element.type === "Citoyen");

    let row = document.querySelector("thead tr");

    row.append(createHeaderCell("ID"));
    row.append(createHeaderCell("Nom"));
    row.append(createHeaderCell("Prenom"));
    row.append(createHeaderCell("Date de naissance"));
    row.append(createHeaderCell("Argent (€)"));
    row.append(createHeaderCell("Sexe"));
    row.append(createHeaderCell(""));
    row.append(createHeaderCell(""));

    document.querySelector("table tbody").append(entity_type.funRow());

    for (let c of entities[entity_type.id]) {      
        document.querySelector("table tbody").append(entity_type.funRow(c));
    }
}

function tableHabitation() {
    let entity_type = entities_type.find(element => element.type === "Habitation");

    let row = document.querySelector("thead tr");

    row.append(createHeaderCell("ID"));
    row.append(createHeaderCell("Nom"));
    row.append(createHeaderCell("Superficie"));
    row.append(createHeaderCell("Prix"));
    row.append(createHeaderCell("Cout d'entretient base"));
    row.append(createHeaderCell("Loyer"));
    row.append(createHeaderCell("# places"));
    row.append(createHeaderCell(""));
    row.append(createHeaderCell(""));

    document.querySelector("table tbody").append(entity_type.funRow());

    for (let h of entities[entity_type.id]) {      
        document.querySelector("table tbody").append(entity_type.funRow(h));
    }
}
function tableProprietaire () {
    let entity_type = entities_type.find(element => element.type === "Proprietaire");

    let row = document.querySelector("thead tr");

    row.append(createHeaderCell("ID"));
    row.append(createHeaderCell("Nom"));
    row.append(createHeaderCell("Prenom"));
    row.append(createHeaderCell("Date de naissance"));
    row.append(createHeaderCell("Argent (€)"));
    row.append(createHeaderCell("Sexe"));
    row.append(createHeaderCell(""));
    row.append(createHeaderCell(""));

    document.querySelector("table tbody").append(entity_type.funRow());

    for (let p of entities[entity_type.id]) {      
        document.querySelector("table tbody").append(entity_type.funRow(p));
    }
}
function tableService      () {
    let entity_type = entities_type.find(element => element.type === "Service");

    let row = document.querySelector("thead tr");

    row.append(createHeaderCell("ID"));
    row.append(createHeaderCell("Nom"));
    row.append(createHeaderCell("Prix entrée"));
    row.append(createHeaderCell("Superficie"));
    row.append(createHeaderCell("Prix"));
    row.append(createHeaderCell("Cout d'entretient base"));
    row.append(createHeaderCell("# places"));
    row.append(createHeaderCell("Heure d'ouvreture"));
    row.append(createHeaderCell("Heure de fermeture"));
    row.append(createHeaderCell(""));
    row.append(createHeaderCell(""));

    document.querySelector("table tbody").append(entity_type.funRow());

    for (let w of entities[entity_type.id]) {      
        document.querySelector("table tbody").append(entity_type.funRow(w));
    }
}
function tableWorkplace    () {
    let entity_type = entities_type.find(element => element.type === "Workplace");

    let row = document.querySelector("thead tr");

    row.append(createHeaderCell("ID"));
    row.append(createHeaderCell("Nom"));
    row.append(createHeaderCell("Superficie"));
    row.append(createHeaderCell("Prix"));
    row.append(createHeaderCell("Cout d'entretient base"));
    row.append(createHeaderCell("# places"));
    row.append(createHeaderCell("Heure d'ouvreture"));
    row.append(createHeaderCell("Heure de fermeture"));
    row.append(createHeaderCell(""));
    row.append(createHeaderCell(""));

    document.querySelector("table tbody").append(entity_type.funRow());

    for (let c of entities[entity_type.id]) {      
        document.querySelector("table tbody").append(entity_type.funRow(c));
    }
}