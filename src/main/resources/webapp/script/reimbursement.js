var hasReceipt;

window.onload = async () => {
    hasReceipt = false;
    const response = await fetch(`${domain}/api/type/view`);
    const data = await response.json();

    let types = document.createElement("select");
    types.className = "form-select";
    types.name = "reimbursement-type"
    types.id = "reimbursement-type"

    data.data.forEach( x => {
        let option = document.createElement("option");
        option.text = x.type;
        option.value = x.id;
        types.add(option);
    });

    document.getElementById("type").appendChild(types);
}

document.getElementById("create-form").onsubmit = async (e) => {
    e.preventDefault();

    const response = await fetch(`${domain}/api/reimbursement/create`,{
        method: "POST",
        body: JSON.stringify({
            amount : Math.floor(100 * document.getElementById("amount").value),
            description : document.getElementById("description").value,
            type : document.getElementsByName("reimbursement-type")[0].value,
            hasReceipt : hasReceipt
        })
    });

    const data = await response.json();
    document.getElementById("output-box").innerHTML = data.message;
    if (data.status) setTimeout(() => {window.location.replace(`${domain}/dashboard`)}, 3000);
}

document.getElementById("upload").onclick = async (e) => {
    e.preventDefault();

    let file = new FormData();
    file.append(document.getElementById("receipt").files[0].name, document.getElementById("receipt").files[0])

    const response = await fetch(`${domain}/api/upload`, {
        method: "POST",
        body : file
    });

    const data = await response.json();
    if (data.status) {
        document.getElementById("output-box").innerHTML = data.message;   
        hasReceipt = true;
    }
}