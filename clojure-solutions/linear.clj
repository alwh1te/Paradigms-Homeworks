(defn opSign [f]
      (fn [& args] (apply mapv f args)))
(def v+ (opSign +))
(def v- (opSign -))
(def v* (opSign *))
(def vd (opSign /))
(defn v*s [v s]
      (mapv #(* s %) v))
(defn scalar [v1 v2]
      (reduce + (map * v1 v2)))
(defn vect [v1 v2]
      (let [x1 (nth v1 0) y1 (nth v1 1) z1 (nth v1 2)
            x2 (nth v2 0) y2 (nth v2 1) z2 (nth v2 2)]
           [(- (* y1 z2) (* z1 y2))
            (- (* z1 x2) (* x1 z2))
            (- (* x1 y2) (* y1 x2))]))
(def m+ (opSign v+))
(def m- (opSign v-))
(def m* (opSign v*))
(def md (opSign vd))
(defn m*s [m & args]
      (mapv (fn [v] (apply (partial v*s v) args)) m))
(defn m*v [m v] (mapv (fn [v1] (scalar v1 v)) m))

(defn transpose [matrix]
      (apply mapv vector matrix))


(defn m*m [m1 m2]
      (reduce (fn [x y]
                  (mapv (fn [z] (m*v (transpose y) z)) x))
              (list m1 m2)))
(def c+ (opSign m+))
(def c- (opSign m-))
(def c* (opSign m*))
(def cd (opSign md))