"use strict";

// PRINTLN:
var println = function(str) { console.log(str) };

//===========================================

function Void() {
}
Void.prototype = {
    isConst: function() { return false },
    evaluate: function() { return 0 },
    diff: function() { return 0 },
    toString: function() { return ""; },
    prefix: function() { return ""; },
    postfix: function() { return ""; },
    simplify: function () { return new Void() },
};

function Const(value) {
    this.value = value;
}
Const.prototype = {
    isConst: function() { return true },
    evaluate: function() { return this.value },
    diff: function() { return new Const(0) },
    toString: function() { return "" + this.value },
    prefix: function() { return "" + this.value },
    postfix: function() { return "" + this.value },
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
    prefix: function() { return this.name },
    postfix: function() { return this.name },
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
        if (OPERATIONS[this.word][1] == 1) {
            return a + " " + this.word;
        } else {
            return a + " " + b + " " + this.word;
        }
    },
    prefix: function() {
        var a = this.first.prefix();
        var b = this.second.prefix();
        if (OPERATIONS[this.word][1] == 1) {
            return "(" + this.word + " " + a + ")";
        } else {
            return "(" + this.word + " " + a + " " + b + ")";
        }
    },
    postfix: function() {
        var a = this.first.postfix();
        var b = this.second.postfix();
        if (OPERATIONS[this.word][1] == 1) {
            return "(" + a + " " + this.word + ")";
        } else {
            return "(" + a + " " + b + " " + this.word + ")";
        }
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

//===========================================

function isZero(a) { return (a.isConst() && a.isEqual(0)); }
function isOne(a) { return (a.isConst() && a.isEqual(1)); }

//===========================================

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
    function(a, b) { return new Divide(
        new Subtract(new Divide(new Multiply(new Log(e, first), b), second), new Divide(new Multiply(a, new Log(e, second)), first)),
        new Square(new Log(e, first))
    ) },
    function(a, b) { return (isOne(b)) ? new Const(0) : new Log(a, b); }
)}
function ArcTan(first) { return new Operation(
    first, new Void(), "atan", " ",
    function(a) { return Math.atan(a) },
    function(a) { return new Divide(a, new Add(new Multiply(first, first), new Const(1))) },
    function(a) { return (isZero(a) || isOne(a)) ? new Const(1) : (isOne(a)) ? a : (isZero(a)) ? new Const(0) : new ArcTan(a); }
)}
function Exp(first) { return new Operation(
    first, new Void(), "exp", " ",
    function(a) { return Math.exp(a) },
    function(a) { return new Multiply(new Exp(first), a) },
    function(a) { return (isOne(a)) ? new Const(0) : new Exp(a); }
)}

//===========================================

var e = new Const(Math.E);
var pi = new Const(Math.PI);
var x = new Variable("x");
var y = new Variable("y");
var z = new Variable("z");


var CONSTS = {
    "e": e,
    "pi": pi
};
var VARIABLES = {
    "x": x,
    "y": y,
    "z": z
};
var OPERATIONS = Object.freeze({
    "+": [Add, 2],
    "-": [Subtract, 2],
    "*": [Multiply, 2],
    "/": [Divide, 2],
    "negate": [Negate, 1],
    "square": [Square, 1],
    "sqrt": [Sqrt, 1],
    "pow": [Power, 2],
    "log": [Log, 2],
    "exp": [Exp, 1],
    "atan": [ArcTan, 1]
});

//===========================================

var vars = Object.freeze({
    'x': 1, 'y': 1, 'z': 1
});
var parse = function(expression) {
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
            for (var j = 0; j < OPERATIONS[tokens[i]][1]; j++) {
                args.push(stack.pop());
            }
            stack.push(OPERATIONS[tokens[i]][0].apply(null, args.reverse()));
        } else {
            stack.push(new Const(parseInt(tokens[i])));
        }
    }
    return stack.pop();
};
var parseError = function (str, message, pos) {
    var s = message + '\n' + str.substr(0, pos) + ' <--- ' + str.substr(pos, str.length);
    this.message = s;
    this.name = 'parseError';
};
var parsePrefix = function (str) {
    str = str.trim();
    var i = 0;
    var balance = 0;
    var opr;
    var stack = [];
    var next_opr = function () {
        if (str.length === i) opr = '';
        while (str[i] === ' ' && i < str.length)
            i++;
        if (str[i] === '(' || str[i] === ')') {
            opr = str[i];
            i++;
            return;
        }
        var tmp = '';
        while (str[i] !== ' ' && str[i] !== '(' && str[i] !== ')' && i < str.length) {
            tmp += str[i];
            i++;
        }
        if (tmp.length)
            opr = tmp;
    };
    var make_expr = function () {
        next_opr();
        if (opr === '(' || opr === ')') {
            balance += (opr === '(' ? 1 : -1);
            if (balance < 0)
                throw new parseError(str, 'Missed opening bracket', i).message;
            if (opr === '(') {
                make_expr()
            } else stack.push(undefined);
        } else if (OPERATIONS[opr] !== undefined) {
            var tmp = [];
            var curr_opr = opr;
            make_expr();
            var u = stack.pop();
            while (u !== undefined) {
                tmp.push(u);
                make_expr();
                u = stack.pop();
            }
            if (tmp.length !== OPERATIONS[curr_opr][1]) {
                throw new parseError(str, "Wrong number of arguments", i).message;
            }
            stack.push(OPERATIONS[curr_opr][0].apply(null, tmp));
        } else if (Number(opr)) {
            stack.push(new Const(parseInt(opr)));
        }
        else if (vars[opr]) {
            stack.push(new Variable(opr));
        } else if (opr === '') {
            return;
        }
        else throw new parseError(str, "Invalid argument", i).message;
    };
    make_expr();
    if (balance !== 0) throw new parseError(str, "Missed closing bracket:", i).message;
    if (i !== str.length || stack[stack.length - 1] === undefined) throw new parseError(str, "Invalid expression", i).message;
    return stack.pop();
};
var parsePostfix = function (str) {
    str = str.trim();
    var i = 0;
    var balance = 0;
    var opr;
    var stack = [];
    var next_opr = function () {
        if (str.length === i) opr = '';
        while (str[i] === ' ' && i < str.length)
            i++;
        if (str[i] === '(' || str[i] === ')') {
            opr = str[i];
            i++;
            return;
        }
        var tmp = '';
        while (str[i] !== ' ' && str[i] !== '(' && str[i] !== ')' && i < str.length) {
            tmp += str[i];
            i++;
        }
        if (tmp.length)
            opr = tmp;
    };
    var make_expr = function () {
        next_opr();
        if (opr === '(' || opr === ')') {
            balance += (opr === '(' ? 1 : -1);
            if (balance < 0)
                throw new parseError(str, 'Missed opening bracket', i).message;
            if (opr === '(') {
                stack.push(undefined);
                make_expr()
            } else make_expr();
        } else if (OPERATIONS[opr] !== undefined) {
            var tmp = [];
            var curr_opr = opr;
            var u = stack.pop();
            while (u !== undefined) {
                tmp.push(u);
                u = stack.pop();
            }
            if (tmp.length !== OPERATIONS[curr_opr][1]) {
                throw new parseError(str, "Wrong number of arguments", i).message;
            }
            stack.push(OPERATIONS[curr_opr][0].apply(null, tmp.reverse()));
            make_expr();
        } else if (Number(opr)) {
            stack.push(new Const(parseInt(opr)));
            make_expr();
        }
        else if (vars[opr]) {
            stack.push(new Variable(opr));
            make_expr();
        }
        else if (opr === '') {
            return;
        }
        else throw new parseError(str, "Invalid argument", i).message;
    };
    make_expr();
    if (balance !== 0) throw new parseError(str, "Missed closing bracket:", i).message;
    if (i !== str.length || stack[stack.length - 1] === undefined || stack.length !== 1) throw new parseError(str, "Invalid expression", i).message;
    return stack.pop();
};

// println(parsePostfix("(x 2 +)").prefix());