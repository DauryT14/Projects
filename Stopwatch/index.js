//Changing Stopwatch (title) to Hello, User based on click event listener
document.addEventListener("DOMContentLoaded", () => {
    const title = document.getElementById("myH1");
    title.addEventListener("click", () => {
        if(title.textContent == "Stopwatch"){
            title.textContent = "Hello, User";
        }
        else{
            title.textContent = "Stopwatch";
        }
    })
})

let startTime = 0;
let elapsedTime = 0;
let timer = null;
const display = document.getElementById("display");
let isRunning = false;

//Functions start, stop, reset, update, etc -> To make the stopwatch work properly
function start(){
    if(!isRunning){
        startTime = Date.now()-elapsedTime;
        timer = setInterval(update, 10);
        isRunning = true;
    }
}

function stop(){
    if(isRunning){
        clearInterval(timer);
        elapsedTime = Date.now()-startTime;
        isRunning = false;
    }
}

function reset(){
    clearInterval(timer);
    startTime = 0;
    elapsedTime = 0;
    isRunning = false;
    display.textContent = "00:00:00:00";
}

function update(){
    const currentTime = Date.now();
    elapsedTime = currentTime - startTime;

    let hours = Math.floor(elapsedTime / (1000 * 60 * 60));
    let minutes = Math.floor(elapsedTime / (1000 * 60) % 60);
    let seconds = Math.floor(elapsedTime / 1000 % 60);
    let milliseconds = Math.floor(elapsedTime % 1000 / 10);

    hours = String(hours).padStart(2,0);
    minutes = String(minutes).padStart(2,0);
    seconds = String(seconds).padStart(2,0);
    milliseconds = String(milliseconds).padStart(2,0);

    display.textContent = `${hours}:${minutes}:${seconds}:${milliseconds}`;

}

const lapDisplay = document.getElementById("lapDisplay");
lapDisplay.textContent = "Lap 1: 00:00:00";
let index = 1;
let lapTime = 1;

function lapUpdate(){
    if(!isRunning){
        lapTime = startTime - elapsedTime;
        let hours = Math.floor(elapsedTime / (1000 * 60 * 60));
        let minutes = Math.floor(elapsedTime / (1000 * 60) % 60);
        let seconds = Math.floor(elapsedTime / 1000 % 60);
        let milliseconds = Math.floor(elapsedTime % 1000 / 10);

        hours = String(hours).padStart(2,0);
        minutes = String(minutes).padStart(2,0);
        seconds = String(seconds).padStart(2,0);
        milliseconds = String(milliseconds).padStart(2,0);

        lapDisplay.textContent = `Lap ${index}: ${hours}:${minutes}:${seconds}:${milliseconds}`;
        index++;
    }
}






