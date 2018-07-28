(ns XD)
(import java.util.Random)

(defn add [x, y]
	(+ x y))
(println
	(add 1 2))


(def random (Random.))
(defn getRandom [] (.nextInt random 10))
(println (getRandom))

(def a (fn [] (println "XD")))
(a)

(dotimes [i 5]
	(print i))
(println)

(println
	(take 5
				(repeat 2)))