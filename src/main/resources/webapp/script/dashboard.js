var user;

window.onload = async () => {
    user = await getUser();

    if (!user.status) window.location.replace(`${domain}`);

    if (user.message != user.data.role) {
        document.getElementById("create-reimbursement").innerHTML = `<a class="btn btn-info" href="${domain}/reimbursement">Create</a>`;
    }

    popRows();
}

const popRows = async () => {
    const response = await fetch(`${domain}/api/reimbursement/view`);
    const data = await response.json();
    
    if (!data.status) window.location.replace(`${domain}`);

    statusFilter = document.getElementById("filter").value;

    if (statusFilter == "All") {
        data.data.sort((a, b) => {
            if (a.status < b.status) return 1;
            else if (a.status > b.status) return -1;
            else return a.id - b.id
        });    
    }

    let new_tbody = document.createElement('tbody');
    data.data.forEach( x => {
        if (statusFilter == "All" || x.status == statusFilter) {
            let row = new_tbody.insertRow();
            row.insertCell(0).innerHTML = `<a href="${domain}/details?id=${x.id}">${x.id}</a>`;
            row.insertCell(1).innerHTML = x.amount/100;
            row.insertCell(2).innerHTML = x.description;
            row.insertCell(3).innerHTML = x.author;
            row.insertCell(4).innerHTML = x.status;
            row.insertCell(5).innerHTML = x.type;
            if (user.data.role == user.message && x.status == "Pending") {
                row.insertCell(6).innerHTML = `<button type="button" class="btn btn-success" onclick="approve(${x.id})">Approve</button>`;
                row.insertCell(7).innerHTML = `<button type="button" class="btn btn-danger" onclick="deny(${x.id})">Deny</button>`;
            } else {
                row.insertCell(6);
                row.insertCell(7);
            }

            if (x.status == "Pending") {
                row.className = "table-primary";
            } else if (x.status == "Approved") {
                row.className = "table-success";
            } else {
            row.className = "table-warning"
            }
        }
    });

    const table = document.getElementById("reimbursement-table");
    const old_tbody = table.getElementsByTagName('tbody')[0];
    old_tbody.parentNode.replaceChild(new_tbody, old_tbody);
}

const getUser = async () => {
    const response = await fetch(`${domain}/api/user`);
    return await response.json();
}

const approve = async (id) => {
    const response = await fetch(`${domain}/api/reimbursement/approve?id=${id}`,{
        method: "PATCH"
    })
    const data = await response.json();
    document.getElementById("output-box").innerHTML = data.message;
    popRows()
}

const deny = async (id) => {
    const response = await fetch(`${domain}/api/reimbursement/deny?id=${id}`,{
        method: "PATCH"
    })
    const data = await response.json();
    document.getElementById("output-box").innerHTML = data.message;
    popRows()
}

const logout = async () => {
    const response = await fetch(`${domain}/api/user/logout`);
    const data = await response.json();
    document.getElementById("output-box").innerHTML = data.message;
    if (data.status) setTimeout(() => {window.location.replace(`${domain}`)}, 3000);
}

const changeFilter = () => {
    document.getElementById("output-box").innerHTML = "";
    popRows()
}