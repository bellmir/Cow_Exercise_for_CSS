const body = document.querySelector("body");

const imageNum = 4;

function paintImage(num) {
    
    const image = new Image();  //const img = document.createElement("img")와 같다
    image.src = "./images/"+(num+1)+".jpg";
    image.classList.add("bgImage");
    body.appendChild(image);  //그리고 css에서 .bgImage{}로 설정
    /*
    body.style.backgroundImage = `url("./images/${(num+1)}.jpg"`;
    body.style.backgroundSize = "cover";
    body.style.backgroundRepeat = "no-repeat";
    */
}

function getRandom() {
    return parseInt(Math.random()*imageNum);
}

(function () {
   const randomNumber = getRandom();
   paintImage(randomNumber);
})();