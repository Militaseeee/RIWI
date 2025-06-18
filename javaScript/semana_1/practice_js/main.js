function printInConsole() {
    console.log("CLick in the botton")
}

// function ifPositive() {

//     // let num = prompt("Enter a number:"); 
//     num = Number(num); 

//     if (num >= 0) {
//         console.log(num + " is a positive number"); 
//     } else {
//         alert(num + " is not a positive number"); 
//     }
// }

// console.log(ifPositive())


let counter = 0

const incrementar = () => {
    counter = counter + 1
    console.log(counter)
    /* console.log(document.getElementById("myCounter")) */
    document.getElementById("myCounter").textContent = counter
}

const drecrementar = () => {
    counter = counter - 1
    console.log(counter)
    document.getElementById("myCounter").textContent = counter
}

const reset = () => {
    counter = 0
    console.log(counter)
    document.getElementById("myCounter").textContent = 0
}

const show = () => {
    console.log(document.getElementById("miInput").value)
}