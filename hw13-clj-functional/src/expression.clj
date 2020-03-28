(defn constant [value]
  (fn [x] value))
(defn variable [name]
  (fn [vars] (get vars name)))
(defn operator [func]
  (fn [& operands] (fn [args] (apply func (map (fn [op] (op args)) operands)))))

(def negate (operator -))
(def add (operator +))
(def subtract (operator -))
(def multiply (operator *))
(def divide (operator (fn ([a b] (/ (double a) (double b)))
                        ([a] (/ 1 (double a)))
                        ([] 1)
                        )))
(def sinh (operator (fn [x] (Math/sinh x))))
(def cosh (operator (fn [x](Math/cosh x))))
(def square (operator (fn [a] (* a a))))
(def sqrt (operator (fn [a] (Math/sqrt a))))

; parser ;

(defn parseFunction [strExpr]
  ((fn parse [expr]
     (cond
       (symbol? expr) (variable (str expr))
       (number? expr) (constant expr)
       (seq? expr) (apply ({'negate negate,
                            '+ add,
                            '- subtract,
                            '* multiply,
                            '/ divide,
                            'sinh sinh,
                            'cosh cosh,
                            'square square,
                            'sqrt sqrt
                            } (first expr)) (map parse (rest expr))))
     ) (read-string strExpr)))

; testing ;

;(def expr (divide (variable "v") (constant 5)))
;(def expr2 (divide (variable "v")))
;(def expr3 (divide))
;(def expr4 (multiply))
;
;(println (expr {"v" 10}))
;(println (expr2 {"v" 10}))
;(println (expr3 {"v" 10}))
;(println (expr4 {"v" 10}))