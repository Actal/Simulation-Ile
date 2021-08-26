let entities_type = [
    { id: "0", type: "Citoyen" },
    { id: "1", type: "Habitation" },
    { id: "2", type: "Propriétaire" },
    { id: "3", type: "Service" },
    { id: "4", type: "Workplace" }
];

let entities = [
    [
        { Id: "1", Nom: "TEST", Prenom: "Test", DateNaissance: "2001-01-01", Sexe: "Homme", Argent: "100" },
        { Id: "2", Nom: "TRUC", Prenom: "Truc", DateNaissance: "1356-01-06", Sexe: "Femme", Argent: "10000000000000000" },
        { Id: "3", Nom: "sdf", Prenom: "Trusdfzsc", DateNaissance: "13456-01-06", Sexe: "Femme", Argent: "10000000000" }
    ],
    [],
    [],
    [],
    [],
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

    if (entity_type != undefined) { // dans le cas ou "Selectionner un type d'entite a éditer" est choisi dans la liste deroulante

        emptyTable();

        tableCitoyen();
    }
});

function emptyTable() {
    document.querySelector("tbody").innerHTML = "";
    document.querySelector("thead tr").innerHTML = "";
}

function createCell(value, editable = "true", type = "td") {
    let col = document.createElement(type);
    col.textContent = value;
    col.setAttribute("contenteditable", editable);
    return col;
}

function addDelBtn(row) {
    btn_del = document.createElement('button');
    btn_del.setAttribute("class", "btn btn-danger");
    btn_del.innerHTML = '<i class="icon-delete"/>';
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
        c = {Id: "0"};
    }

    let f = document.createElement("form");
    f.setAttribute("method", "POST");
    f.setAttribute("id", c.Id);

    let res = '<td>';
    if( cit_defined ) { res+=c.Id; }
    res += ` </td>
            <td>
                <input type="text" id="nom" name="nom" form="${c.Id}" `;
                if( cit_defined ) { res+=`value="${c.Nom}"`; }
    res +=      `/>
            </td>
            <td>
                <input type="text" id="prenom" name="prenom" form="${c.Id}" `;
   if( cit_defined ) { res+=`value="${c.Prenom}"`; }
   res +=       `/>
            </td>
            <td>
                <input type="date" id="date" name="date" form="${c.Id}" `;
    if( cit_defined ) { res += `value="${c.DateNaissance}"`; }
    res +=      `/>
            </td>
            <td>
                <input type="number" id="argent" name="argent" step="0.01" form="${c.Id}" `;
                if( cit_defined ) { res += `value="${c.Argent}"` }
    res +=      `/>
            </td>
            <td>
                <select id="sexe" name="sexe" form="${c.Id}">`;
    if ( cit_defined && c.Sexe === "Homme") {
        res +=      `<option value="homme" selected>Homme</option>
                    <option value="femme">Femme</option>`;
    } else {
        res +=      `<option value="homme">Homme</option>
                    <option value="femme" selected>Femme</option>`;
    }
    res +=      `</select>
            </td>

            <td>
                <button class="btn btn-primary" type="submit" form="${c.Id}"><i class="icon-save"></i></button>
            </td>`;
    let row = document.createElement('tr');
    row.innerHTML = res;
    
    row.append(f);

    if( cit_defined ) {
        addDelBtn(row);
    } else {
        row.innerHTML += '<td></td>';
    }

    return (row);
}

function tableCitoyen() {

    let idx = entities_type.find(element => element.type === "Citoyen").id;

    let row = document.querySelector("thead tr");

    row.append(createCell("ID", false, "th"));
    row.append(createCell("Nom", false, "th"));
    row.append(createCell("Prenom", false, "th"));
    row.append(createCell("Date de naissance", false, "th"));
    row.append(createCell("Argent (€)", false, "th"));
    row.append(createCell("Sexe", false, "th"));
    row.append(createCell("", false, "th"));
    row.append(createCell("", false, "th"));

    document.querySelector("table tbody").append(rowCitoyen());

    for (let c of entities[idx]) {      
        document.querySelector("table tbody").append(rowCitoyen(c));
    }
}

