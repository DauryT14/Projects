const result = document.getElementById("result");
const male = document.getElementById("male");
const female = document.getElementById("female");
const feetBox = document.getElementById("feet");
const inchesBox = document.getElementById("inches");
const weightBox = document.getElementById("weight");
const age = document.getElementById("age");
const calculate = document.getElementById("calculate");
const clear = document.getElementById("clear");
const myForm = document.getElementById("myForm");

let feet;
let inches;
let weight;
let height;
let bmi;

calculate.addEventListener("click", () =>{
    if(age.value == 0){
        result.textContent = "Please enter a valid age"
    }
    
    else{
        feet = Number(feetBox.value);
        inches = Number(inchesBox.value);
        weight = Number(weightBox.value);

        feet = feet*12;
        height = feet+inches;
        bmi = weight/Math.pow(height,2) * 703;
        if(bmi > 18.5 && bmi < 24.9){
            result.textContent = "BMI = " + bmi.toFixed(1) + " (Healthy Weight)";
        }

        else if(bmi < 18.5){
            result.textContent = "BMI = " + bmi.toFixed(1) + " (Underweight)";
        }

        else if(bmi > 25.0 && bmi < 29.9){
            result.textContent = "BMI = " + bmi.toFixed(1) + " (Overweight)";
        }

        else if(bmi > 30.0){
            result.textContent = "BMI = " + bmi.toFixed(1) + " (Obesity)";
        }
    }
})

clear.addEventListener("click", () =>{
    myForm.reset();
    result.textContent = "";
})