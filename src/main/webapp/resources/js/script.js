function validateLogin(login){
    return (login.length > 4 && login.length < 16) || "Login must be more then 4 characters and less then 16 characters.";
}
