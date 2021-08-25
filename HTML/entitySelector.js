let entities_type = [
    { id: "0", type: "Citoyen" },
    { id: "1", type: "Habitation" },
    { id: "2", type: "Propriétaire" },
    { id: "3", type: "Service" },
    { id: "4", type: "Workplace" }
];

let entities = [
    [
        { nom: "TEST", prenom: "Test", dateNaissance: "01/01/01", sexe: "Homme", argent: "100" },
        { nom: "TRUC", prenom: "Truc", dateNaissance: "01/06/1356", sexe: "Homme", argent: "10000000000000000" }],
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

        // table videe
        document.querySelector("tbody").innerHTML = "";
        document.querySelector("thead tr").innerHTML = "";


        if (entities[entity_type.id].length > 0) {

            // creation des noms ds colonnes en fonction du nom des attributs
            for (let attribut in entities[entity_type.id][0]) {
                let colName = document.createElement('th');
                colName.textContent = attribut;

                document.querySelector("thead tr").append(colName);
            }
            document.querySelector("thead tr").append(document.createElement('th')); // colonne pour les bouttons

            // ajout des entites a la table
            for (let e of entities[entity_type.id]) {
                let row = document.createElement('tr');
                for (let attribut in e) {
                    let col = document.createElement('th');
                    col.textContent = e[attribut];
                    row.append(col);
                }
                button_edit = document.createElement('button');
                button_edit.setAttribute("class", "btn btn-primary")
                button_edit.innerHTML = '<i class="bi bi-pencil-fill"/>'

                button_del = document.createElement('button');
                button_del.setAttribute("class", "btn btn-danger")
                button_del.innerHTML = '<i class="bi bi-x-lg"/>'

                button_col = document.createElement('th');
                button_col.append(button_del, button_edit);
                row.append(button_col);
                document.querySelector("tbody").append(row);
            }
        }
    }
});
