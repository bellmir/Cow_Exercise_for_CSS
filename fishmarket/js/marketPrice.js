const  marketPrice_list = document.querySelector(".marketPrice_list");

window.onload = () =>{
    fetch("https://raw.githubusercontent.com/bellmir/Practice/master/fishmarket/part/marketPrice.json")
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
}