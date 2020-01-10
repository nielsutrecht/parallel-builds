function reqListener () {
    let ip = JSON.parse(this.responseText).ip
    document.getElementById("ipField").textContent=ip
}

let oReq = new XMLHttpRequest();
oReq.addEventListener("load", reqListener);
oReq.open("GET", "/api/ip");
oReq.send();