"use strict";

var println = function(str) { console.log(str) };

var cnst = function(first) {
    return function() {
        return first;
    }
};

var variable = function(first) {
    return function(arg_x, arg_y, arg_z) {
        switch(first) {
            case "x": return arg_x;
            case "y": return arg_y;
            case "z": return arg_z;
        }
    }
};

var operation = function(maths) {
    return function() {
        var operands = arguments;
        return function () {
            var myOperands = [];
            for (var i = 0; i < operands.length; i++) {
                myOperands.push(operands[i].apply(null, arguments));
            }
            return maths.apply(null, myOperands);
        }
    }
};

var e = cnst(Math.E);
var pi = cnst(Math.PI);
var x = variable("x");
var y = variable("y");
var z = variable("z");

var add = operation(function(first, second) { return first + second; });
var subtract = operation(function(first, second) { return first - second; });
var multiply = operation(function(first, second) { return first * second; });
var divide = operation(function(first, second) { return first / second; });
var negate = operation(function(first) { return -first; });
var min3 = operation(function (first, second, third) { return Math.min(first, second, third); });
var max5 = operation(function (first, second, third, fourth, fifth) { return Math.max(first, second, third, fourth, fifth); });

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
        "+": add,
        "-": subtract,
        "*": multiply,
        "/": divide,
        "negate": negate,
        "max5": max5,
        "min3": min3
    };
    var ARGUMENTS = {
        "+": 2,
        "-": 2,
        "*": 2,
        "/": 2,
        "negate": 1,
        "min3": 3,
        "max5": 5
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
            stack.push(cnst(parseInt(tokens[i])));
        }
    }
    return stack.pop();
};

// println(parse("5 6 7 +")(3));