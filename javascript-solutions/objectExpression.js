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


const operations = {
    '+': [(...args) => new Add(...args), 2], // Operation and count of the arguments
    '-': [(...args) => new Subtract(...args), 2],
    '*': [(...args) => new Multiply(...args), 2],
    '/': [(...args) => new Divide(...args), 2],
    'negate': [(...args) => new Negate(...args), 1],
    'sin': [(...args) => new Sin(...args), 1],
    'cos': [(...args) => new Cos(...args), 1],
    'mean': [(...args) => new Mean(...args), Number.MAX_VALUE],
    'var': [(...args) => new Var(...args), Number.MAX_VALUE]
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
    return new Error(message)
}
const IncorrectBracketsSequenceException = (message) => ParseException('incorrect brackets sequence' + message)
const NotEnoughArgumentsException = (message) => ParseException('not enough arguments' + message)
const NotEnoughOperatorsException = (message) => ParseException('not enough operators' + message)
const IllegalArgumentException = (message) => ParseException('unexpected item' + message)
const EmptyExpressionException = () => ParseException('empty expression')

function parsePrefix(expression) {
    expression = expression.replaceAll('(', ' ( ').replaceAll(')', ' ) ')
    let tokens = expression.split(' ').filter(s => s)
    let bracketsBalance = 0
    let index = 0

    let parseToken = function () {
        if (tokens.length === 0) throw new EmptyExpressionException()
        let token = tokens.shift()
        index += token.length
        if (token === '(') {
            token = tokens.shift()
            bracketsBalance++
            const opSign = token;
            const op = operations[token]
            const items = []
            token = parseToken()
            while (token !== ')' && (items.length < op[1])) {
                items.push(token)
                token = parseToken()
            }
            if (items.length < op[1] && op[1] !== Number.MAX_VALUE) throw NotEnoughArgumentsException(' for operation: ' + opSign + ' at position: ' + index)
            index += opSign.length
            return op[0](...items)
        } else if (isNumber(token)) {
            return new Const(token)
        } else if (isVar(token)) {
            return new Variable(token)
        } else if (token === ')') {
            bracketsBalance--
            return token
        } else {
            throw IllegalArgumentException(': ' + token + ' at position: ' + index)
        }
    }

    let ans = parseToken()
    if (bracketsBalance !== 0) {
        throw IncorrectBracketsSequenceException(', balance != 0')
    }
    if (tokens.length > 0) {
        throw NotEnoughOperatorsException(' for this arguments: ' + tokens.join(' '))
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
        } else if (elem in operations) {
            let operands = []
            while (operands.length < operations[elem][1]) operands.unshift(stack.pop())
            stack.push(operations[elem][0](...operands))
        } else if (isNumber(elem)) {
            stack.push(new Const(elem))
        }
    }
    return stack.pop()
}
