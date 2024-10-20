    var ws;

function connect() {
    var username = document.getElementById("username").value;
    var host = "localhost:8080";
    var pathname = "/";

    ws = new WebSocket("ws://" +host  + pathname + "chat/" + username);
    ws.onmessage = function(event) {
    var log = document.getElementById("log");

    console.log(event.data);
    message = JSON.parse(event.data);
    if(message.content !=undefined)        log.innerHTML +=  message.from + " : " + message.content + "\n"
    var users = document.getElementById("users");
    var options = "";

    if(message.item != undefined) {
    var len = message.item.length;
    while (len > 0) {
        options = options + "<option>" + message.item[len - 1] + "</option>";
        users.innerHTML = options;
        len--;
    }
    }

    var imageSelected = document.getElementById("imageSelected");
    if(message.img != undefined){
        var baseStr64 = message.img;
        var image = new Image();
        imageSelected.setAttribute('src',baseStr64)
    }


    };

}
function processMessage(){
    var json = createMessage();
    send(json);

}
function send(msg) {
    ws.send(msg);
}
var imageFile;
function  createMessage(){
    var users = document.getElementById("users");
    var options = users.selectedOptions;
    var userSelected = Array.from(options).map(({ value }) => value);
    var content = document.getElementById("msg").value;

    var json = JSON.stringify({
        "content":content,
        "to":userSelected,
        "img":imageFile
    });
    return json;
}
function closeConnection(){
    document.location.reload();
    var content = document.getElementById("log");
    content.innerHTML="Disconnected!";
    content.reload();
    ws.close();
}
function readFile(input) {
    var imageSelected = document.getElementById("imageSelected");

    imageSelected.style.display = "inline";
    imageSelected.setAttribute('src',URL.createObjectURL(input.files[0]));
    let file = input.files[0];

    let fileReader = new FileReader();
    fileReader.readAsDataURL(file);

    fileReader.onload = function () {

        imageFile=fileReader.result;
        //document.getElementById("result").innerText= fileReader.result;
    };
    fileReader.onerror = function () {
        alert(fileReader.error);
    };
}
function selectUser(){
    var users = document.getElementById("users");
    var options = users.selectedOptions;
    if (options[i].selectedOld == true) {
        options[i].selected = false;
        options[i].selectedOld = false;
    } else {
        options[i].selectedOld = true;
    }
}
function clear(option){
    var i;
    var select = document.getElementById(option.parentNode.id);
    for(i=1;i<select.options.length;i++)
    {
        select.options[i].selected=false;
    }
}
function clearImage(){
    var imageSelected = document.getElementById("imageSelected");
    var fileChooser = document.getElementById("fileChooser");
    imageSelected.removeAttribute('src')
    imageFile=null;
    imageSelected.style.display="none";
    fileChooser.value=null;

    }