const userInfo = document.querySelector(".userInfo");
const show = document.querySelector(".userActivity_show");
const currentUser = document.querySelector(".userInfo .currentUser");
const privacyBtn = document.querySelector(".privacyChange_btn");
const logoutBtn = document.querySelector(".logout_btn");
const userActivity_list = document.querySelector(".userActivity_list");

let userRole;
let userID;

window.onload = ()=>{
    userRole = getCookie("userRole");
    userID = getCookie("userID");
    if(userRole=="seller"){
        fetchSellerPage();
    } else{
        fetchCustomerPage();
    }
}

privacyBtn.onclick = ()=>{
    deleteCookie("userID");
    deleteCookie("userRole");
    signIn();   //login.js의 function.
    userRole = getCookie("userRole");
    userID = getCookie("userID");
    if(userRole=="seller"){
        fetchSellerPage();
    } else{
        fetchCustomerPage();
    }
    alert('정보가 수정되었습니다.');
};
logoutBtn.onclick = ()=>{
    deleteCookie("userID");
    deleteCookie("userRole");
    location.replace("./index.html");
    alert('로그아웃 되었습니다.');
};

function fetchSellerPage(){
    userActivity_list.innerHTML = "";
    currentUser.innerHTML = `${userID} / ${userRole}`;
    const list = ["store", "product", "review", "deal"];
    const texts = ["내 가게", "상품관리", "최근리뷰", "거래알림"];
    for(i=0;i<4;i++){
        const li = document.createElement('li');
        const tags = `<button class="${list[i]}Button" onclick="handle_${list[i]}Cliked()"><img src="../images/${list[i]}_icon.png" alt="${list[i]}"> ${texts[i]}</button>`;
        li.innerHTML = tags;
        userActivity_list.appendChild(li);
    }
}

function fetchCustomerPage(){
    userActivity_list.innerHTML = "";
    currentUser.innerHTML = `${userID} / ${userRole}`;
    const list = ["buyList", "favorite", "review", "price"];
    const texts = ["구매내역", "즐겨찾기", "나의리뷰", "관심시세"];
    for(i=0;i<4;i++){
        const li = document.createElement('li');
        const tags = `<button class="${list[i]}Button" onclick="unImplemented()"><img src="../images/${list[i]}_icon.png" alt="${list[i]}"> ${texts[i]}</button>`;
        li.innerHTML = tags;
        userActivity_list.appendChild(li);
    }
}
function unImplemented(){
    alert('구현되지 않았습니다.');
}

function handle_dealCliked() {
    fetch(`../part/deal.json`)
    .then((response) => response.json())
    .then((data) => {
        data = data.log;
        let tags ='<ul>';

        for(let i=0;i<data.length && i<10;i++){
            let item = data[i];
            let tag = '<li>';
            for(list in item){
                tag=tag+`${item[list]} `;
            }
            tag = tag + '</li>';
            tags = tags + tag;
        }
        tags = tags+ '</ul> <a href="../pages/deal.html" class="more_btn">더보기</a>'
        show.innerHTML = tags;
    })
    .catch(error=>{show.innerHTML = "불러오기에 실패했습니다."});
}

function handle_reviewCliked() {
    fetch(`../part/review.json`)
    .then((response) => response.json())
    .then((data) => {
        data = data.log;
        let tags ='<ul>';

        for(let i=0;i<data.length && i<10;i++){
            let item = data[i];
            let tag = '<li>';
            for(list in item){
                tag=tag+`${item[list]} `;
            }
            tag = tag + '</li>';
            tags = tags + tag;
        }
        tags = tags+ '</ul> <a href="#" class="more_btn">더보기</a>'
        show.innerHTML = tags;
    })
    .catch(error=>{show.innerHTML = "불러오기에 실패했습니다."});
}

function handle_storeCliked() {
    fetch(`../part/#.json`)
    .then((response) => response.json())
    .then((data) => {
        data = data.log;
    })
    .catch(error=>{show.innerHTML = "불러오기에 실패했습니다."});
}

function handle_productCliked() {
    fetch(`../part/#.json`)
    .then((response) => response.json())
    .then((data) => {
        data = data.log;
    })
    .catch(error=>{show.innerHTML = "불러오기에 실패했습니다."});
}
