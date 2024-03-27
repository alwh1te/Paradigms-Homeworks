"use strict"
const Operation = function () {
    this.evaluate = (...arg) => this.solver(...this.args.map(op => op.evaluate(...arg)))
    this.prefix = () => ('(' + this.operand + ' ' + this.args.map(op => op.prefix()).join(' ') + ')')
    this.postfix = () => "(" + this.args.map(op => op.postfix()).join(" ") + " " + this.operand + ")";
    this.toString = () => (this.args.join(' ') + " " + this.operand)
}

function Const(value) {
    this.value = parseInt(value);
    this.evaluate = (...args) => this.value;
    this.toString = () => this.value.toString();
    this.prefix = () => this.toString()
    this.postfix = () => this.toString()
}

const varInd = ['x', 'y', 'z'];

function Variable(name) {
    this.name = name;
    this.evaluate = (...args) => args[varInd.indexOf(this.name)]
    this.toString = () => this.name;
    this.prefix = () => this.toString()
    this.postfix = () => this.toString()
}


const operator = {
    '+': [(...args) => new Add(...args), 2], // Operation and count of the arguments
    '-': [(...args) => new Subtract(...args), 2],
    '*': [(...args) => new Multiply(...args), 2],
    '/': [(...args) => new Divide(...args), 2],
    'negate': [(...args) => new Negate(...args), 1],
    'sin': [(...args) => new Sin(...args), 1],
    'cos': [(...args) => new Cos(...args), 1]
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
const isNumber = (a) => !isNaN(Number(a))

function ParseException(place, message) {
    this.message = message
    this.place = place
}
ParseException.prototype = Object.create(Error.prototype)


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



function parsePrefix(expression) {
    expression = expression.replaceAll('(', ' ( ')
    expression = expression.replaceAll(')', ' ) ')
    let tokens = expression.split(' ').filter(s => s)
    let brackeBalance = 0
    let parseToken = function () {
        if (tokens.length === 0) {
            throw new ParseException(tokens, 'not enough args')
        }
        if (brackeBalance < 0) {
            throw new ParseException(tokens, 'bracketBalance < 0')
        }
        let token = tokens.shift();
        if (token === '(') {
            brackeBalance++
            let operationToken = tokens.shift();
            let temp = []
            let lastOp = operator[operationToken]
            while (temp.length < lastOp[1]) temp.push(parseToken())
            return lastOp[0](...temp);
        }
        else if (token === ')') {
            brackeBalance--
            return parseToken()
        }
        else if (token in operator) {
            let temp = []
            let lastOp = operator[token]
            while (temp.length < lastOp[1]) temp.push(parseToken())
            return lastOp[0](...temp);
        } else if (isNumber(token)) {
            return new Const(parseFloat(token));
        } else if (isVar(token)) {
            return new Variable(token);
        } else {
            throw new ParseException(tokens, 'unexpected op')
        }
    }

    let ans = parseToken()
    while (tokens.shift() === ')') brackeBalance--
    if (brackeBalance !== 0) {
        throw new ParseException(expression, 'bracketBalance != 0')
    }
    if (tokens.length > 0) {
        throw new ParseException(expression, 'bullshit')
    }
    return ans
}

// console.log(parsePrefix('( / x ( * y z )  )').evaluate(1, 1, 1))
// console.log(parsePrefix('(/ (* x y) z)').prefix())
// let expr = parse('10')
// console.log(expr.prefix())