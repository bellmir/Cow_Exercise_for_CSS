const WeatherAPI = 'd6c96e009985e669f8e94ebf46c89fb0';
const COORDS = 'coords'
const js_weather = document.querySelector(".js_weather");

function saveCoords(coordsObj) {
    localStorage.setItem(COORDS, JSON.stringify(coordsObj))
}

function handleGeoSuccess(position) {
    const latitude =  position.coords.latitude;
    const longitude = position.coords.longitude;
    const coordsObj = {
        latitude,       //latitude: latitude 와 같음
        longitude,       //longitude: longitude 와 같음
    }
    saveCoords(coordsObj);
    getWeather(latitude, longitude);
}
function handleGeoError() {
    console.log("Can't access geo location");
}

function askForCoords() {
    navigator.geolocation.getCurrentPosition(handleGeoSuccess, handleGeoError);
}

function getWeather(latitude, longitude) {
    //fetch를 이용해 라이브러리를 사용하지 않고도 api를 사용할 수 있다.
    //fetch는 Promise객체를 반환하기 때문에 .then으로 받아줘야 한다. 이는 .json()도 마찬가지이다.
    fetch(`https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=${WeatherAPI}&units=metric`)
    .then(response => {
        response.json().then((json)=>{
            const temperature = json.main.temp;
            const place = json.name;
            const weather = json.weather[0].main;
            js_weather.innerText = `기온: ${temperature} / 지역: ${place} / 날씨: ${weather}`;
        })
    });
    //await로 바꿔서 구현도 해보기
}

function loadCoords() {
    const loadedCoords = localStorage.getItem(COORDS);
    if(loadedCoords===null){
        askForCoords();
    }else{
        const parseCoords = JSON.parse(localStorage.getItem(COORDS));
        getWeather(parseCoords.latitude, parseCoords.longitude);
    }
}

(function () {
    loadCoords();
})();