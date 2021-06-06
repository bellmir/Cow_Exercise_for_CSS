const shop_title = document.querySelector(".shop_title");
const shop_intro = document.querySelector(".shop_intro");
const shop_img = document.querySelector(".shop_img");
const products_list = document.querySelector(".product_list");

let currentStore =null;

window.onload = () =>{
    let role = getCookie("userRole");
    if(role==null){
        alert('로그인해주세요');
        location.replace('./MyPage.html');
    } else{
        if (role =="seller") {
            alert('판매자는 구매가 불가능합니다.');
            location.replace('./marketShop.html');
        }
    }
    currentStore = getCookie("clickedStore");
    fetch('../part/marketShop.json')
    .then(res => res.json())
    .then(data => {
        data = data.market;
        for(let i=0;i<data.length;i++){
            if(data[i].storeName == currentStore){
                fetchShopPage(data[i]);
                break;
            }
        }
    })
    .catch((error)=>{
        products_list.innerHTML = "불러오기에 실패했습니다.";
    })
    .finally(()=>{
        if(currentStore==null){
            products_list.innerHTML = "불러오기에 실패했습니다.";
        }
    });
}

function fetchShopPage(store){
    products_list.innerHTML = "";
    shop_title.innerHTML = store.storeName;
    shop_intro.innerHTML = `소개: ${store.storeInfo}`;
    shop_img.innerHTML = `<img src="${store.image}"/>`;
    for(let product of store.products){
        const li = document.createElement("li");
        li.innerHTML = `<input type="checkbox" name="productName" value="${product.productName}" id="${product.productName}"><label for="${product.productName}"><span>${product.productName} (${product.productInfo})</span> <span>${product.productCost}</span> </label>`;
        products_list.appendChild(li);
    }
}

function getCookie(name) {
    let value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return value ? value[2] : null;
}
function setCookie(name, value) {
    let date = new Date();
    date.setTime(date.getTime() + 60*60*1000);
    document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
};


function getChecked(){
    const checked = document.querySelectorAll('input[type="submit"]:checked');
    checked.forEach(e=>{
        shop_intro.innerHTML = "dsfadffas";
        setCookie("productList",e.value);
    })
}