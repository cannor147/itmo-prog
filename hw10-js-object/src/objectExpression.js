"use strict";

// PRINTLN:
var println = function(str) { console.log(str) };

function Void() {
}
Void.prototype = {
    isConst: function() { return false },
    evaluate: function() { return 0 },
    diff: function() { return 0 },
    toString: function() { return " "; },
    simplify: function () { return new Void() }
};

function Const(value) {
    this.value = value;
}
Const.prototype = {
    isConst: function() { return true },
    evaluate: function() { return this.value },
    diff: function() { return new Const(0) },
    toString: function() { return "" + this.value },
    simplify: function () { return new Const(this.value) },

    isEqual: function(number) { return this.value === number },
    getValue: function() { return this.value }
};

function Variable(value) {
    this.name = value;
}
Variable.prototype = {
    isConst: function() { return false },
    evaluate: function(arg_x, arg_y, arg_z) {
        switch (this.name) {
            case "x": return arg_x;
            case "y": return arg_y;
            case "z": return arg_z;
        }
    },
    diff: function(arg) { return (arg === this.name) ? new Const(1) : new Const(0) },
    toString: function() { return this.name },
    simplify: function () { return new Variable(this.name) }
};

function Operation(first, second, word, ws, operation, derivative, check) {
    this.first = first;
    this.second = second;
    this.word = word;
    this.ws = ws;
    this.operation = operation;
    this.derivative = derivative;
    this.check = check;
}
Operation.prototype = {
    isConst: function() {
        return false
    },
    evaluate: function(arg_x, arg_y, arg_z) {
        var a = this.first.evaluate(arg_x, arg_y, arg_z);
        var b = this.second.evaluate(arg_x, arg_y, arg_z);
        return this.operation(a, b);
    },
    diff: function(arg) {
        var a = this.first.diff(arg);
        var b = this.second.diff(arg);
        return this.derivative(a, b);
    },
    toString: function() {
        var a = this.first.toString();
        var b = this.second.toString();
        return a + this.ws + b + this.ws + this.word;
    },
    simplify: function() {
        var a = this.first.simplify();
        var b = this.second.simplify();
        if (a.isConst() && b.isConst()) {
            return new Const(this.operation(a.getValue(), b.getValue()));
        } else {
            return this.check(a, b);
        }
    }
};

var e = new Const(Math.E);
var pi = new Const(Math.PI);
var x = new Variable("x");
var y = new Variable("y");
var z = new Variable("z");

function isZero(a) { return (a.isConst() && a.isEqual(0)); }
function isOne(a) { return (a.isConst() && a.isEqual(1)); }

function Add(first, second) { return new Operation(
    first, second, "+", " ",
    function(a, b) { return a + b },
    function(a, b) { return new Add(a, b) },
    function(a, b) { return (isZero(a)) ? b : (isZero(b)) ? a : new Add(a, b); }
)}
function Subtract(first, second) { return new Operation(
    first, second, "-", " ",
    function(a, b) { return a - b },
    function(a, b) { return new Subtract(a, b) },
    function(a, b) { return (isZero(a)) ? new Negate(b) : (isZero(b)) ? a : new Subtract(a, b); }
)}
function Multiply(first, second) { return new Operation(
    first, second, "*", " ",
    function(a, b) { return a * b },
    function(a, b) { return new Add(new Multiply(a, second), new Multiply(first, b)) },
    function(a, b) { return (isZero(a) || isZero(b)) ? new Const(0) : (isOne(a)) ? b : (isOne(b)) ? a : new Multiply(a, b); }
)}
function Divide(first, second) { return new Operation(
    first, second, "/", " ",
    function(a, b) { return a / b },
    function(a, b) { return new Divide(new Subtract(new Multiply(a, second), new Multiply(first, b)), new Multiply(second, second)) },
    function(a, b) { return (isZero(a)) ? new Const(0) : (isOne(b)) ? a : new Divide(a, b); }
)}
function Square(first) { return new Operation(
    first, new Void(), "square", "",
    function(a) { return a * a },
    function(a) { return new Multiply(Multiply(new Const(2), first), a) },
    function(a) { return (a.isConst()) ? new Const(a.getValue() * a.getValue()) : new Square(a) }
)}
function Sqrt(first) { return new Operation(
    first, new Void(), "sqrt", "",
    function(a) { return Math.sqrt(Math.abs(a)) },
    function(a) { return new Divide(new Multiply(a, first), new Multiply(new Const(2), new Sqrt(Multiply(first, Multiply(first, first)))))},
    function(a) { return (a.isConst()) ? new Const(Math.sqrt(Math.abs(+a.getValue()))) : new Sqrt(a) }
)}
function Negate(first) { return new Operation(
    first, new Void(), "negate", "",
    function(a) { return -a },
    function(a) { return new Negate(a) },
    function(a) { return (a.isConst()) ? new Const(-a.getValue()) : new Negate(a) }
)}
function Power(first, second) { return new Operation(
    first, second, "pow", " ",
    function(a, b) { return Math.pow(a, b) },
    function(a, b) { return new Multiply(
        new Power(first, new Subtract(second, new Const(1))),
        new Add( new Multiply(second, a), new Multiply(first, new Multiply(new Log(e, first), b)))
    ) },
    function(a, b) { return (isZero(b) || isOne(a)) ? new Const(1) : (isOne(b)) ? a : (isZero(a)) ? new Const(0) : new Power(a, b); }
)}
function Log(first, second) { return new Operation(
    first, second, "log", " ",
    function(a, b) { return Math.log(Math.abs(b)) / Math.log(Math.abs(a)) },
    function(a, b) { return new Subtract(
        new Divide(b, new Multiply(second, new Log(e, first))),
        new Divide(new Multiply(a, new Log(first, second)), new Multiply(first, new Log(e, first)))
    ) },
    function(a, b) { return (isOne(b)) ? new Const(0) : new Log(a, b); }
)}

var parse = function(expression) {
    var CONSTS = {
        "e": e,
        "pi": pi
    };
    var VARIABLES = {
        "x": x,
        "y": y,
        "z": z
    };
    var OPERATIONS = {
        "+": Add,
        "-": Subtract,
        "*": Multiply,
        "/": Divide,
        "negate": Negate,
        "square": Square,
        "sqrt": Sqrt,
        "pow": Power,
        "log": Log
    };
    var ARGUMENTS = {
        "+": 2,
        "-": 2,
        "*": 2,
        "/": 2,
        "negate": 1,
        "square": 1,
        "sqrt": 1,
        "pow": 2,
        "log": 2
    };

    var stack = [];
    var tokens = expression.split(" ").filter(
        function (wh) {
            return wh.length > 0;
        }
    );
    for (var i = 0; i < tokens.length; i++) {
        if (tokens[i] in CONSTS) {
            stack.push(CONSTS[tokens[i]]);
        } else if (tokens[i] in VARIABLES) {
            stack.push(VARIABLES[tokens[i]]);
        } else if (tokens[i] in OPERATIONS) {
            var args = [];
            for (var j = 0; j < ARGUMENTS[tokens[i]]; j++) {
                args.push(stack.pop());
            }
            stack.push(OPERATIONS[tokens[i]].apply(null, args.reverse()));
        } else {
            stack.push(new Const(parseInt(tokens[i])));
        }
    }
    return stack.pop();
};

//println(new Log(new Variable('x'), new Variable('y')).diff('x').toString());