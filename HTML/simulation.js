
let table = document.querySelector("#simulation-area table");

let stringTab = "";
for (let x=0; x<100; x++){
    let classLine = x%2;
    stringTab += `<tr class="line-${classLine}">`;
    for (let y=0; y<100; y++){
        let classColumn = y%2;
        stringTab += `<th class="column-${classColumn}" ></th>`;
    }
    stringTab += "</tr>";
}
table.innerHTML = stringTab;

let cases = document.querySelectorAll("th");
/*for (case of cases){
    case.addEventListener('hover', () => {
        document.querySelector("#sidebar").textContent = this.className;
    });
}*/

table.addEventListener('hover', () => {
    document.querySelector("#sidebar").textContent = "test";
});