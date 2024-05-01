(def constant constantly)

(defn variable [var-name]
      (fn [values] (values var-name)))

(defn binaryOp [opSign]
      (fn [arg1 arg2] (fn [values] (opSign (arg1 values) (arg2 values)))))

(defn unaryOp [opSign]
      (fn [arg] (fn [values] (opSign (arg values)))))

(def add (binaryOp +))

(def subtract (binaryOp -))

(def multiply (binaryOp *))
(def div #(/ (double %1) (double %2)))
(def divide (binaryOp div))

(def negate (unaryOp -))
(def sin (unaryOp #(Math/sin %)))
(def cos (unaryOp #(Math/cos %)))

(def op-list {'constant constant,
              'variable variable,
              '+        add,
              '-        subtract,
              '*        multiply,
              '/        divide,
              'negate   negate,
              'sin      sin,
              'cos      cos
              })

(defn parseImpl [exp operations]
      (cond
        (number? exp) ((operations 'constant) exp)
        (seq? exp) (apply (operations (first exp)) (mapv #(parseImpl % operations) (rest exp)))
        :else ((operations 'variable) (str exp))))

(defn parseFunction [str] (parseImpl (read-string str) op-list))


;----------------------------- HW11 ------------------------------------


(definterface ArithmeticExpression
              (^Number evaluate [args])
              (^String toString [_]))

(deftype ConstantClass [value]
         ArithmeticExpression
         (evaluate [_ _] value)
         (toString [_] (str value)))

(defn Constant [value] (ConstantClass. value))

(deftype VariableClass [name]
         ArithmeticExpression
         (evaluate [_ operands] (operands name))
         (toString [_] name))

(defn Variable [name] (VariableClass. name))

(deftype Operation [opSign operation args]
         ArithmeticExpression
         (evaluate [_ map] (apply operation (mapv #(.evaluate % map) args)))
         (toString [_]
                   (str "(" opSign " " (clojure.string/join " " (map #(.toString %) args)) ")")))

(defn Add [& args] (Operation. "+" + args))
(defn Subtract [& args] (Operation. "-" - args))
(defn Divide [& args] (Operation. "/" div args))
(defn Multiply [& args] (Operation. "*" * args))
(defn Negate [& args] (Operation. "negate" - args))
(defn Exp [& args] (Operation. "exp" #(Math/exp %) args))
(defn Ln [& args] (Operation. "ln" #(Math/log %) args))

(def operationsObject
  {'constant Constant
   'variable Variable
   '+      Add
   '-      Subtract
   '*      Multiply
   '/      Divide
   'negate Negate
   'exp Exp
   'ln Ln
   })

(defn evaluate [expr map] (.evaluate expr map))
(defn toString [expr] (.toString expr))
(defn parseObject [str]
      (parseImpl (read-string str) operationsObject))