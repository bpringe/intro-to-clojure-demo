(ns intro-to-clojure-demo.core)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;; Parallelism with pmap ;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; A function that simulates a long-running process by calling Thread/sleep:
(defn long-running-job [n]
  (Thread/sleep 3000) ; wait for 3 seconds
  (+ n 10))

;; Use `doall` to eagerly evaluate `map`, which evaluates lazily by default.
;; With `map`, the total elapsed time is ~ 4 * 3 seconds:
(time (doall (map long-running-job (range 4))))

;; With `pmap`, the total elapsed time is just over 3 seconds:
(time (doall (pmap long-running-job (range 4))))







;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;; Threading macros ;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Thread-last
(->> (range 4)
     (pmap long-running-job)
     doall
     time)







(def person
  {:name "Mark Volkmann"
   :address {:street "644 Glen Summit"
             :city "Alexandria"
             :state "Louisiana"
             :zip 71302}
   :employer {:name "Object Computing, Inc."
              :address {:street "12140 Woodcrest Dr."
                        :city "Baton Rouge"
                        :state "Louisiana"
                        :zip 70809}}})

;; Thread-first
(-> person :employer :address :city)





(-> 5 (+ 3) (/ 2) (- 1))

(macroexpand '(-> c (+ 3) (- 2) (- 1)))










;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;; Re-defining code ;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn print-and-wait
  [x]
  (throw (Exception. "done"))
  (println x "!!!")
  (Thread/sleep 1000))

(future
  (run!
   #'print-and-wait
   (range)))

