(defn v-op [operand]
      (fn [& arguments]
          (apply mapv operand arguments)))

(def v+ (v-op +))

(def v- (v-op -))

(def v* (v-op *))

(def vd (v-op /))

(defn v*s [v s]
      (mapv #(* s %) v))

(defn scalar [v1 v2]
      (reduce + (map * v1 v2)))

(defn vect [v1 v2]
      (let [x1 (nth v1 0) y1 (nth v1 1) z1 (nth v1 2)
            x2 (nth v2 0) y2 (nth v2 1) z2 (nth v2 2)]
           [( - (* y1 z2) (* z1 y2))
            ( - (* z1 x2) (* x1 z2))
            ( - (* x1 y2) (* y1 x2))]))


(defn m*m [m1 m2]
      (let [num-rows1 (count m1)
            num-cols1 (count (first m1))
            num-cols2 (count (first m2))]
           (->> (for [i (range num-rows1)]
                     (for [j (range num-cols2)]
                          (apply + (map * (nth m1 i) (mapv #(nth % j) m2)))))
                (mapv vec))))

(defn m-op [f m1 m2]
      (mapv (fn [row1 row2] (mapv f row1 row2)) m1 m2))

(defn s-m-op [f m s]
      (mapv (fn [row] (mapv #(f % s) row)) m))

(defn m-v-op [f m v additional]
      (mapv #(apply f (map additional % v)) m))

(defn transpose [matrix]
      (apply mapv vector matrix))

(defn m+ [m1 m2]
      (m-op + m1 m2))

(defn m- [m1 m2]
      (m-op - m1 m2))

(defn m* [m1 m2]
      (m-op * m1 m2))

(defn md [m1 m2]
      (m-op / m1 m2))

(defn m*s [m s]
      (s-m-op * m s))

(defn m*v [m v]
      (m-v-op + m v *))

(defn c+ [a b]
     (mapv m+ a b))

(defn c- [a b]
      (mapv m- a b))
(defn c* [a b]
      (mapv m* a b))

(defn cd [a b]
      (mapv md a b))