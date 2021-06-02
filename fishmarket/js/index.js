const marketShop_show = document.querySelector(".marketShop_show ul");
const marketShop_more_btn = document.querySelector(".marketShop_show .more_btn");
const  marketPrice_list = document.querySelector(".marketPrice_list");

/*
market.json을 받아와서 marketShop_show안에
    <li><a href="#"> <span>가게이름</span> <ul><li>물건정보</li></ul> </a></li>
형태로 넣으려고 함. 가게는 9개, 가게별 품목은 3개씩만 display.
*/

fetch("../part/marketPrice.json")
    .then(res=> res.json())
    .then(data => {
        data = data.marketName;
        for(let i=0;i<data.length && i<20;i++){
            const li = document.createElement('li');
            li.innerHTML = `${data[i].productName} ${data[i].productPrice}`;
            marketPrice_list.appendChild(li);
        }
    }).catch(error => {
        marketPrice_list.innerHTML = "불러오기에 실패했습니다.";
    });
fetch("../part/market.json").then(res => res.json())
.then(data => {
    data = data.market;
    for(let i=0; i<data.length && i<9; i++){
        const li = document.createElement('li');
        const a = document.createElement('a');
        a.setAttribute('href', '#');
        const storeName = document.createElement('span');
        storeName.classList.add("storeName")
        const storeProducts = document.createElement('ul');
        storeProducts.classList.add("products");
        storeName.innerHTML = data[i].storeName;
        for(let j=0; j<data[i].products.length && j<3; j++){
            const product = document.createElement('li');
            product.innerHTML = `${data[i].products[j].productName} ${data[i].products[j].productCost}`;
            storeProducts.appendChild(product);
        }
        a.appendChild(storeName);
        a.appendChild(storeProducts);
        li.appendChild(a);
        marketShop_show.appendChild(li);
    }
})
.catch(error => {
    marketShop_show.innerHTML = "불러오기에 실패했습니다."
});
