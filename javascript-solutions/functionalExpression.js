"use strict"
const operation = operation => (...ops) => (...operands) => operation(...ops.map(op => op(...operands)))
const cnst = x => () => Number(x);
const variable = name => (...args) => args[(varInd.indexOf(name))]
const add = operation((a, b) => a + b)
const subtract = operation((a, b) => a - b)
const multiply = operation((a, b) => a * b)
const divide = operation((a, b) => a / b)
const negate = operation(a => -a)
const varInd = ['x', 'y', 'z']