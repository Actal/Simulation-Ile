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

    console.log(select)

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

function rowCitoyen(row, c) {


    temp = `<form method="POST">
                        <td>
                            ${c.Id}
                        </td>
                        <td>
                            <input type="text" id="nom" name="nom" value="${c.Nom}"/>
                        </td>
                        <td>
                            <input type="text" id="prenom" name="prenom" value="${c.Prenom}"/>
                        </td>
                        <td>
                            <input type="date" id="date" name="date" value="${c.DateNaissance}"/>
                        </td>
                        <td>
                            <input type="number" id="argent" name="argent" step="0.01" value="${c.Argent}"/>
                        </td>
                        <td>
                            <select id="sexe" name="sexe">`;
    if (c.Sexe === "Homme") {
        temp += `<option value="homme" selected>Homme</option>
        <option value="femme">Femme</option>`;
    } else {
        temp += `<option value="homme">Homme</option>
         <option value="femme" selected>Femme</option>`;
    }
    temp += `</select>
                        </td>

                        <td>
                            <button class="btn btn-primary" type="submit" onclick='console.log("TOTO"); return false;'><i class="icon-save"></i></button>
                        </td>
                    </form>`;

    row.innerHTML = temp;
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

    let row_empty = document.createElement('tr');
    row_empty.innerHTML = `<form method="POST">
                        <td>
                            
                        </td>
                        <td>
                            <input type="text" id="nom" name="nom" />
                        </td>
                        <td>
                            <input type="text" id="prenom" name="prenom"/>
                        </td>
                        <td>
                            <input type="date" id="date" name="date"/>
                        </td>
                        <td>
                            <input type="number" id="argent" name="argent" step="0.01" min="0"/>
                        </td>
                        <td>
                            <select id="sexe" name="sexe">
                                <option value="homme">Homme</option>
                                <option value="femme">Femme</option>
                            </select>
                        </td>

                        <td>
                            <button class="btn btn-primary" type="submit" onclick='console.log(document.querySelector("#argent").value); return false;'><i class="icon-save"></i></button>
                        </td>
                        <td/>
                    </form>`;
    document.querySelector("tbody").append(row_empty);

    for (let c of entities[idx]) {
        let row = document.createElement('tr');
        rowCitoyen(row, c);
        addDelBtn(row);
        document.querySelector("tbody").append(row);
    }
}

