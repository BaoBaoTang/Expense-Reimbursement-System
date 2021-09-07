const urlParams = new URLSearchParams(window.location.search)
const id = urlParams.get("id")

window.onload = async () => {
    const response = await fetch(`${domain}/api/reimbursement/detail-view?id=${id}`);
    const data = await response.json();
    
    if (!data.status) window.location.replace(`${domain}/dashboard`);
    const x = data.data;
    reimbursementDetails = `
        <table class="table-primary">
            <tbody>
                <tr><th>ID</th><td>${x.id}</td></tr>
                <tr><th>Amount</th><td>${x.amount/100}</td></tr>
                <tr><th>Description</th><td>${x.description}</td></tr>
                <tr><th>Tpye</th><td>${x.type}</td></tr>
                <tr><th>Author</th><td>${x.author}</td></tr>
                <tr><th>Status</th><td>${x.status}</td></tr>
                <tr><th>Submitted</th><td>${x.submitted}</td></tr>
    `
    if (x.status != "Pending") 
        reimbursementDetails += `
            <tr><th>Resolved Time</th><td>${x.resolved}</td></tr>
            <tr><th>Resolver</th><td>${x.resolver}</td></tr>
        `

    if (x.receipt != null) {
        const link = getDisplayLink(x.receipt);
        reimbursementDetails += `<tr><th>Receipt</th><td><img src=${link}></td></tr>`
    }
    
    reimbursementDetails += `
        </tbody>
        </table>
    `

    document.getElementById("reimbursement-container").innerHTML = reimbursementDetails;


}

function getDisplayLink(receipt) {
    const binString = window.atob(receipt);
    const len = binString.length;
    let bytes = new Uint8Array(len);
    for (i=0; i<len; i++) {
        bytes[i] = binString.charCodeAt(i);
    }

    const blob = new Blob([bytes], {type : "image/jepg"});
    return window.URL.createObjectURL(blob);
}