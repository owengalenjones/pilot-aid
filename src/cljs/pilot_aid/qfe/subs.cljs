(ns pilot-aid.qfe.subs
  (:require [re-frame.core :as rf]))

(def default-val "")

(rf/reg-sub
  :qfe/p0
  (fn [db _]
    (or (:qfe/p0 db)
        default-val)))

(rf/reg-sub
  :qfe/p1
  (fn [db _]
    (or (:qfe/p1 db)
        default-val)))

(rf/reg-sub
  :qfe/h0
  (fn [db _]
    (or (:qfe/h0 db)
        default-val)))

(rf/reg-sub
  :qfe/h1
  (fn [db _]
    (or (:qfe/h1 db)
        default-val)))

(rf/reg-sub
  :qfe/t
  (fn [db _]
    (or (:qfe/t db)
        default-val)))

(defn calculate
  "P1 = P0 * (1 - (0.0065 * (h1 - h0)) / 237.15 + T)  ^ 5.255"
  [p0 h0 h1 t]
  (* p0
     (Math/pow (- 1
                  (/ (* 0.0065
                        (- h1
                           h0))
                     (+ 237.15
                        t)))
               5.255)))

(rf/reg-sub
  :qfe/p1
  (fn [_ _]
    [(rf/subscribe [:qfe/p0])
     (rf/subscribe [:qfe/h0])
     (rf/subscribe [:qfe/h1])
     (rf/subscribe [:qfe/t])])

  (fn [v]
    (if (some empty? v)
      default-val
      (apply calculate v))))
