window.onload = async () => {
    const response = await fetch(`${domain}/api/role/view`);
    const data = await response.json();


    let roles = document.createElement("select");
    roles.className = "form-select";
    roles.name = "user-role";
    roles.id = "user-role";

    data.data.forEach( x => {
        let option = document.createElement("option");
        option.text = x.name;
        option.value = x.id;
        roles.add(option);
    });

    document.getElementById("role").appendChild(roles);

    let label = document.createElement("label");
    label.htmlFor = "user-role";
    label.innerText = "Role";
    document.getElementById("role").appendChild(label);   

}

document.getElementById("register-form").onsubmit = async (e) => {
    e.preventDefault();

    const response = await fetch(`${domain}/api/user/register`,{
        method: 'POST',
        body: JSON.stringify({
            username : document.getElementById("username").value,
            firstname : document.getElementById("firstname").value,
            lastname : document.getElementById("lastname").value,
            email : document.getElementById("email").value,
            role : document.getElementsByName("user-role")[0].value
        })
    });

    const data = await response.json();
    document.getElementById("output-box").innerHTML = data.message;
    if (data.status) setTimeout(() => {window.location.replace(`${domain}`)}, 3000);
}