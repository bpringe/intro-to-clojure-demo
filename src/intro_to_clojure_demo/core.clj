(ns intro-to-clojure-demo.core)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;; pmap demo ;;;;;;;;;;;;;;;;;;;;;;;;
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
;;;;;;;;;;;;;;; Threading macros demo ;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(->> (range 4)
     (map #'long-running-job)
     doall
     time)