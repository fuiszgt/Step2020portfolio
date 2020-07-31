
function askForNick(){
    $("#nickname-form").show();
}

function set_nickname(){
    const formContent = new FormData($("#nickname-form")[0]);
    postData("/set_nickname", new URLSearchParams(formContent).toString())
    .then((response)=>isNickTaken(response));
}

function isNickTaken(response){
    if(response.success){
        validatedNick(response.nick);
    }else{
        indicateTakenNick(response.nick);
    }
}

function indicateTakenNick(nick){
    $("#nickname-form").append("The nickname " + nick + "is already taken");
}

function validatedNick(nick){
    $("#greeting").html("Hello " + nick);
    $("#nickname-form").hide();
}