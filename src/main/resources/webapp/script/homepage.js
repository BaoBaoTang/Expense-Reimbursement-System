
document.getElementById("login-form").onsubmit = async (e) => {
    e.preventDefault();
    const response = await fetch(`${domain}/api/user/login`,{
        method: 'POST',
        body: JSON.stringify({
            username : document.getElementById("username").value,
            password : document.getElementById("password").value,
        })
    });

    const data = await response.json();

    document.getElementById("output-box").innerHTML = data.message;
    if (data.status) window.location.replace(`${domain}/dashboard`);
}