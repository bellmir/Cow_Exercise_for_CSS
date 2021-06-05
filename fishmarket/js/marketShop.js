const  marketShop_list = document.querySelector(".marketShop_list");

window.onload = () =>{
    fetch("../part/marketShop.json")
    .then(res => res.json())
    .then(data => {
        data = data.market;
        for(let i=0; i<data.length && i<20; i++){
            const li = document.createElement('li');
            const a = document.createElement('a');
            a.setAttribute('href', './shop.html');
            a.addEventListener("click", ()=> setClickedStoreCookie("clickedStore", data[i].storeName));
            const storeName = document.createElement('span');
            storeName.classList.add("storeName");
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
            marketShop_list.appendChild(li);
        }
    })
    .catch(error => {
        marketShop_list.innerHTML = "불러오기에 실패했습니다."
    });
}

function setClickedStoreCookie(name, value) {
    let date = new Date();
    date.setTime(date.getTime() + 60*60*1000);
    document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
};
