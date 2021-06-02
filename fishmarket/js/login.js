let ID;
let Role;

(function() {
    let isLogged = getCookie("userID");
    if(isLogged==null){
        signIn();
    } else{
        alert(`로그인 되어 있습니다.\n ID: ${isLogged}`);
    }
})();

function signIn(){
    ID = prompt("아이디 입력\n(defualt:bellmir)");
    Role = prompt("역할 입력\nseller or customer (defualt:seller)")
    
    if(ID==null){
        ID="bellmir";
    }
    if(Role!="seller" && Role!="customer"){
        Role="seller";
    }
    setCookie("userID", ID, 1);
    setCookie("userRole", Role, 1);
}

function setCookie(name, value, exp) {
    let date = new Date();
    date.setTime(date.getTime() + exp*24*60*60*1000);
    document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
};

function getCookie(name) {
    let value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return value ? value[2] : null;
}

function deleteCookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
}
