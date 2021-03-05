const js_clock = document.querySelector(".js_clock");
const js_clockTitle = js_clock.querySelector(".js_clockTitle");

function setClockTime() {
    const date = new Date();
    const minute = date.getMinutes();
    const hour = date.getHours();
    const second = date.getSeconds();
    js_clockTitle.innerHTML = `${hour<10?`0${hour}`: hour}:${minute<10?`0${minute}`: minute}:${second<10?`0${second}`: second}`;
}

(function() {
    setClockTime();
    setInterval(setClockTime, 1000); 
})()