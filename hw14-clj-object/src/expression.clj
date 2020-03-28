(defn proto_get [this key]
  (cond (contains? this key) (this key)
    :else (proto_get (this :prototype) key)))

(defn diff [expr target]
  ((proto_get expr :diff) expr target))
(defn toString [expr]
  ((proto_get expr :toString) expr))
(defn evaluate [expr args]
  ((proto_get expr :evaluate) expr args))
(defn field [key]
  (fn [this] (proto_get this key)))
(def _operands (field :operands))

(def AbstractOpProto
  {:toString (fn [this]
               (str
                 (apply str "("
                        (this :sign)
                        (mapv (fn [x] (str " " (toString x))) (this :operands)))
                 ")"))
   :evaluate (fn [this args]
               (apply (this :f) (mapv (fn [x] (evaluate x args)) (this :operands))))})

(defn AbstractOp [f sign f_diff]
  (let [this {:prototype AbstractOpProto :f f :sign sign :diff f_diff}]
    (fn [& args] (assoc this :operands args))))

(defn Constant [x]
  {:evaluate (fn [this args] x)
   :toString (fn [& args]
               (format "%.01f" (double x)))
   :diff     (fn [this target]
               (Constant 0))})

(defn Variable [x]
  {:evaluate (fn [this args]
               (get args x))
   :toString (fn [& args]
               (str x))
   :diff     (fn [this target]
               (if (= target x) (Constant 1) (Constant 0)))})

; operations ;

(def Add) (def Subtract) (def Multiply) (def Divide) (def Negate) (def Sin) (def Cos)

(def Add
  (AbstractOp + "+"
              (fn [this target]
                (apply Add (map (fn [x] (diff x target)) (_operands this))))))

(def Subtract
  (AbstractOp - "-"
              (fn [this target]
                (apply Subtract (map (fn [x] (diff x target)) (_operands this))))))

(def Multiply
  (AbstractOp * "*"
              (fn [this target]
                (apply Add
                       (mapv
                         (fn [x]
                           (apply Multiply
                                  (mapv (fn [y] (if (identical? x y) ((proto_get x :diff) x target) y))
                                        (_operands this))))
                         (_operands this))))))

(def Divide
  (AbstractOp (fn [a b]
                (/ (double a) (double b))) "/"
              (fn [this target]
                (let [x  (nth (_operands this) 0)
                      y  (nth (_operands this) 1)]
                  (Divide (Subtract (Multiply y (diff x target)) (Multiply x (diff y target)))
                          (Multiply y y))))))

(def Negate
  (AbstractOp - "negate"
              (fn [this target]
                (let [x (nth (_operands this) 0)]
                  (Negate (diff x target))))))

(def Sin
  (AbstractOp (fn [a]
                (Math/sin a)) "sin"
              (fn [this target]
                (let [x (nth (_operands this) 0)]
                  (Multiply (Cos x) (diff x target))))))

(def Cos
  (AbstractOp (fn [a]
                (Math/cos a)) "cos"
              (fn [this target]
                (let [x (nth (_operands this) 0)]
                  (Multiply (Negate (Sin x)) (diff x target))))))

; parser ;

(def ops
  {'+      Add
   '-      Subtract
   '*      Multiply
   '/      Divide
   'negate Negate
   'sin    Sin
   'cos    Cos})

(defn parser [expr]
  (cond
    (seq? expr)    (apply (ops (first expr)) (map parser (rest expr)))
    (number? expr) (Constant (double expr))
    (symbol? expr) (Variable (str expr))))

(defn parseObject [expr] (parser (read-string expr)))