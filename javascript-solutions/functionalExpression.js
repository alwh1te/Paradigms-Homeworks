"use strict"

let subtract = function () {
    let result = 0;
    for (const i of arguments) {
        result -= i;
    }
    return result;
}

let cnst = function () {
    return arguments[0];
}
let variable = function () {
    return arguments[0];
}
let multiply = function () {
    let result = 1;
    for (const i of arguments) {
        result *= i;
    }
    return result;
}

let add = function () {
    let result = 0;
    for (const i of arguments) {
        result += i;
    }
    return result;
}

let divide = function () {
    let result = 0;
    for (const i of arguments) {
        result /= i;
    }
    return result;
}
