"use strict"
const Operation = function () {
    this.evaluate = (...arg) => this.solver(...this.args.map(op => op.evaluate(...arg)))
    this.prefix = () => ('(' + this.operand + ' ' + this.args.map(op => op.prefix()).join(' ') + ')')
    this.postfix = () => "(" + this.args.map(op => op.postfix()).join(" ") + " " + this.operand + ")";
    this.toString = () => (this.args.join(' ') + " " + this.operand)
}

function Const(value) {
    this.value = parseInt(value);
    this.evaluate = () => this.value;
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
    'cos': [(...args) => new Cos(...args), 1],
    'mean': [(...args) => new Mean(...args), -1],
    'var': [(...args) => new Var(...args), -1]
}


const ExpressionConstructor = (solver, operand) => {

    const constructor = function (...args) {
        this.arg = args
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

const Mean = ExpressionConstructor((...args) => ((args.reduce((sum, a) => sum + a)) / args.length), 'mean')
const Var = ExpressionConstructor((...args) => MeanMath(...args.map(value => (value - MeanMath(...args)) ** 2)), 'var')
const MeanMath = (...args) => ((args.reduce((sum, a) => sum + a)) / args.length)

const isVar = (a) => a === 'x' || a === 'y' || a === 'z'
const isNumber = (a) => !isNaN(Number(a))

function ParseException(message) {
    this.message = message
}

const IncorrectBracketsSequence = () => new ParseException('incorrect brackets sequence')
const NotEnoughArguments = () => new ParseException('not enough arguments')
const NotEnoughOperators = () => new ParseException('not enough operators')

function parsePrefix(expression) {
    expression = expression.replaceAll('(', ' ( ')
    expression = expression.replaceAll(')', ' ) ')
    let tokens = expression.split(' ').filter(s => s)
    let bracketsBalance = 0

    let parseToken = function () {
        let token = tokens.shift()
        if (token === '(') {
            token = tokens.shift()
            bracketsBalance++
            const op = operator[token]
            const items = []
            token = parseToken()
            while (token !== ')' && (items.length < op[1] || op[1] < 0)) {
                items.push(token)
                token = parseToken()
            }
            if (items.length < op[1] && op[1] !== -1) throw new ParseException('malo')
            return op[0](...items)
        } else if (isNumber(token)) {
            return new Const(token)
        } else if (isVar(token)) {
            return new Variable(token)
        } else if (token === ')') {
            bracketsBalance--
            return token
        } else {
            throw new ParseException('huita')
        }
    }

    let ans = parseToken()
    if (bracketsBalance !== 0) {
        throw new IncorrectBracketsSequence()
    }
    if (tokens.length > 0) {
        throw new NotEnoughOperators()
    }
    return ans
}

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
