(ns intro-to-clojure-demo.core)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;; Parallelism with pmap ;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; A function that simulates a long-running process by calling Thread/sleep:
(defn long-running-job [n]
  (Thread/sleep 3000) ; wait for 3 seconds
  (+ n 10))

;; Use `doall` to eagerly evaluate `map`, which evaluates lazily by default.
;; With `map`, the total elapsed time is just under 4 * 3 seconds:
(time (doall (map long-running-job (range 4))))

;; With `pmap`, the total elapsed time is just over 3 seconds:
(time (doall (pmap long-running-job (range 4))))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;; Threading macros ;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(->> (range 4)
     (map long-running-job)
     doall
     time)




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;; Re-defining code ;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn print-number-and-wait
  [i]
  (println i ".............")
  (Thread/sleep 1000))

(future
  (run!
   #'print-number-and-wait ;; mind the #' - the expression evaluates to the #'print-number-and-wait Var, not its value.
   (range)))