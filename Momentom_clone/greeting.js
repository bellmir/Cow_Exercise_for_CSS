const js_formUserName = document.querySelector(".js_formUserName"),
    js_inputUserName = js_formUserName.querySelector("input"),
    js_greeting = document.querySelector(".js_greeting");

const USER_LS = "currentUser",
    SHOWING_CN = "showing";

function paintGreeting(text) {
    js_formUserName.classList.remove(SHOWING_CN)
    js_greeting.classList.add(SHOWING_CN);
    js_greeting.innerHTML=`Welcome ${text}`;
}

function setName(event) {
    event.preventDefault(); //form의 기본 동작을 무효화함
    const currentValue = js_inputUserName.value;
    localStorage.setItem(USER_LS, currentValue);
    paintGreeting(currentValue);
}

function askForName() {
    js_formUserName.classList.add(SHOWING_CN);
    localStorage.removeItem(USER_LS);
    js_formUserName.addEventListener("submit",setName);
}

function loadName() {
    let currentUser = localStorage.getItem(USER_LS);    //localStorage는 URL을 기본으로 동작하는 작은 저장소다. 브라우저의 개발자도구에서 application에 가면 볼 수 있다.
    if(currentUser===null){  //when no user exist
        askForName();
    } else{         //when user exist
        paintGreeting(currentUser);
    }
}

(function () {
    loadName();
})();