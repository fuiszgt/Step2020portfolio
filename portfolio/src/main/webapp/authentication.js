function initAuth(){
    fetchData("/login")
    .then((loginInfo) => authenticateView(loginInfo))
}

function authenticateView(loginInfo){
    if(loginInfo.isLoggedIn){
        $("#new-comment-form").show();
        $("#toggle-login").html("Logout").attr("href", loginInfo.url);

    }else{
        $("#toggle-login").html("Login").attr("href", loginInfo.url);
    }
}