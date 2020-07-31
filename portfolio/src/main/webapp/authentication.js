function initAuth(){
    fetchData("/login")
    .then((loginInfo) => authenticateView(loginInfo))
}

function authenticateView(loginInfo){
    if(loginInfo.isLoggedIn){
        $("#toggle-login").html("Logout").attr("href", loginInfo.logoutUrl);
        if(loginInfo.hasNick){
            validatedNick(loginInfo.nick);
        }else{
            askForNick();
        }
    }else{
        $("#toggle-login").html("Login").attr("href", loginInfo.loginUrl);
    }
}