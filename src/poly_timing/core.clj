(ns poly-timing.core
  (:require [criterium.core :refer (quick-bench)]
            [clojure.core.match :refer (match)])
  (:gen-class))

(defmulti type-multi class)
(defmethod type-multi String [x] "string")
(defmethod type-multi Long [x] "long")
(defmethod type-multi :default [x] "default")

(defprotocol TypeProto
  (type-proto [_]))

(extend-protocol TypeProto
  String
  (type-proto [_] "string")
  Long
  (type-proto [_] "long")
  Object
  (type-proto [_] "default"))

(defn value-case [n]
  (case n
    1 "1"
    2 "2"
    3 "3"
    4 "4"
    5 "5"))

(defn value-cond [n]
  (cond
    (= n 1)
    "1"
    (= n 2)
    "2"
    (= n 3)
    "3"
    (= n 4)
    "4"
    (= n 5)
    "5"))


(defmulti value-multi identity)
(defmethod value-multi 1 [n] "1")
(defmethod value-multi 2 [n] "2")
(defmethod value-multi 3 [n] "3")
(defmethod value-multi 4 [n] "4")
(defmethod value-multi 5 [n] "5")

(defn value-match [n]
  (match [n]
         [1] "1"
         [2] "2"
         [3] "3"
         [4] "4"
         [5] "5"))

(defmacro bench [s expr]
  `(do
     (println "\nBenchmarking" ~s)
     (quick-bench ~expr)))

(defn -main [& args]
  (println "## Value-based dispatch")
  (bench "case 1st" (value-case 1))
  (bench "case 5th" (value-case 5))
  (bench "cond 1st" (value-cond 1))
  (bench "cond 5th" (value-cond 5))
  (bench "multi 1st" (value-multi 1))
  (bench "multi 5th" (value-multi 5))
  (bench "match 1st" (value-match 1))
  (bench "match 5th" (value-match 5))

  (println "\n## Type-based dispatch")
  (bench "multi" (type-multi "abc"))
  (bench "multi default" (type-multi 1/2))
  (bench "proto" (type-proto "abc"))
  (bench "proto default" (type-proto 1/2))

  (println "\n## Bimorphic distribution")
  (bench "multi bi" (do (type-multi "abc") (type-multi 5)))
  (bench "proto bi" (do (type-proto "abc") (type-proto 5))))
