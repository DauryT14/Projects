const myH2 = document.getElementById("myH2");
const checkAnswer = document.getElementById("checkAnswer");
const input = document.getElementById("textBox");
const myH3 = document.getElementById("myH3");
const playAgain = document.getElementById("playAgain");
let chances = 10;

input.focus();
//Random number generator
let random = Math.floor(Math.random()*100) + 1;
//Check button
checkAnswer.addEventListener("click", function(){
    const guess = input.value.trim();

    if(isNaN(guess) || guess === ""){
        myH2.textContent = "Please enter a valid number";
        return;
    }

    if(guess == random){
        myH2.textContent = "You got it!";
        checkAnswer.disabled = true;
        input.disabled = true;
        playAgain.style.display = "block";
    }

    if(guess < 1 || guess > 100){
        myH2.textContent = "Your number must be between 1 and 100";
    }
    
    else{
        if(guess < random){
            myH2.textContent = "Your number is low";
        }
        
        if(guess > random){
            myH2.textContent = "Your number is high";
        }
        if(chances == 0){
            checkAnswer.disabled = true;
            myH2.textContent = "You don't have any more guesses";
            input.disabled = true;
            playAgain.style.display = "block";
        }
        chances--;
        myH3.textContent = `You have ${chances} chances left`;
    }
    
    
})

playAgain.addEventListener("click", function(){
    const guess = Number(input.value);
    myH2.textContent = "Enter a number";
    document.getElementById("myForm").reset();
    playAgain.style.display = "none";
    input.disabled = false;
    checkAnswer.disabled = false;
    chances = 10;
    myH3.textContent = `You have ${chances} chances`;
    random = Math.floor(Math.random()*100) + 1;
})

const form = document.getElementById("myForm");

form.addEventListener("submit", function(event){
    event.preventDefault();
})