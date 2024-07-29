const display = document.getElementById("display");
let cleared;

function appendToDisplay(input){
    if(input == '*'){
        display.value += 'x';
        cleared = false;
    }
    else if(input == '/'){
        display.value += '÷';
        cleared = false;
    }

    else if(cleared){
        display.value = input;
        cleared = false;
    }
    else if(input == '**'){
        display.value += '^';
        cleared = false;
    }

    else{
        display.value += input;
    }
}

function calculate(){
    try{
        const expression = display.value.replaceAll('x', '*').replaceAll('÷', '/').replaceAll('^', '**');
        display.value = eval(expression);
    }
    catch(error){
        display.value = "ERROR";
    }
}

function clearDisplay(){
    display.value = 0;
    cleared = true;
}

function squareRoot(){
    const currentNumber = display.value;
    if(currentNumber >= 0){
        display.value = Math.sqrt(currentNumber);
    }
    else{
        display.value = "ERROR";
    }
}

function log(){
    const currentNumber = display.value;
    display.value = Math.log(currentNumber);
}

