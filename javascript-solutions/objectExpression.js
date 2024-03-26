"use strict"
const Operation = function () {
    this.evaluate = (...arg) => this.solver(...this.args.map(op => op.evaluate(...arg)))
    this.toString = () => (this.args.join(" ") + " " + this.operand)
}

function Const(value) {
    this.value = Number(value);
    this.evaluate = (...args) => this.value;
    this.toString = () => this.value.toString();
}

const varInd = ['x', 'y', 'z'];

function Variable(name) {
    this.name = name;
    this.evaluate = (...args) => args[varInd.indexOf(this.name)]
    this.toString = () => this.name;
}


const operator = {
    '+' : [(...args) => new Add(...args), 2], // Operation and count of the arguments
    '-' : [(...args) => new Subtract(...args), 2],
    '*' : [(...args) => new Multiply(...args), 2],
    '/' : [(...args) => new Divide(...args), 2],
    'negate' : [(...args) => new Negate(...args), 1],
    'sin' : [(...args) => new Sin(...args), 1],
    'cos' : [(...args) => new Cos(...args), 1]
}


const ExpressionConstructor = (solver, operand) => {
    const constructor = function (...args) {
        this.args = args;
        Operation.apply(this, ...args);
    }
    constructor.prototype = Object.create(Operation);
    constructor.prototype.solver = solver;
    constructor.prototype.operand = operand;

    return constructor;
}

const Add = ExpressionConstructor((a, b) => a + b, "+");
const Subtract = ExpressionConstructor((a, b) => a - b, "-");
const Multiply = ExpressionConstructor((a, b) => a * b, "*");
const Divide = ExpressionConstructor((a, b) => a / b, "/");
const Negate = ExpressionConstructor((a) => -a, "negate");

const Sin = ExpressionConstructor((a) => Math.sin(a), "sin")
const Cos = ExpressionConstructor((a) => Math.cos(a), "cos")

const isVar = (a) => a === 'x' || a === 'y' || a === 'z'
const isNumber = (a) => Number(a) !== undefined

const parse = (expression) => {
    let parsed = expression.split(' ').filter(str => str)
    let stack = []
    for (let i = 0; i < parsed.length; i++) {
        let elem = parsed[i]
        if (isVar(elem)) {
            stack.push(new Variable(elem))
        } else if (elem in operator) {
            let operands = []
            while (operands.length < operator[elem][1]) operands.unshift(stack.pop())
            stack.push(operator[elem][0](...operands))
        } else if (isNumber(elem)) {
            stack.push(new Const(elem))
        }
    }
    return stack.pop()
}
