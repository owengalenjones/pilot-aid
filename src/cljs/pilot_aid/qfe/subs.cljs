(ns pilot-aid.qfe.subs
  (:require [re-frame.core :as rf]
            [goog.string :as gstring]
            [goog.string.format]))

(def default-val "")
(def math-error "Cannot compute")

(defn hg->hpa
  "To convert inches of mercury to millibars, multiply the inches value by 33.8637526"
  [x]
  (* x 33.8637526))

(defn hpa->hg
  "To convert millibars to inches of mercury, multiply the millibar value by 0.0295301"
  [x]
  (* x  0.0295301))

(defn two-decimals
  [x]
  (gstring/format "%.2f" x))

(rf/reg-sub
  :qfe/p0
  (fn [db _]
    (or (:qfe/p0 db)
        default-val)))

(rf/reg-sub
  :qfe/p0-hpa
  (fn [_ _]
    [(rf/subscribe [:qfe/p0])
     (rf/subscribe [:qfe/p0-in])])

  (fn [[p0 in]]
    (case in
      :hpa p0
      :hg (str (hg->hpa p0)))))

(rf/reg-sub
  :qfe/p0-in
  (fn [db _]
    (or (:qfe/p0-in db)
        :hpa)))

(rf/reg-sub
  :qfe/p0-in-display
  (fn [_ _]
    [(rf/subscribe [:qfe/p0-in])])

  (fn [[in]]
    (case in
      :hpa "hPa | mBar"
      :hg "hg")))

(rf/reg-sub
  :qfe/p1-out-format
  (fn [_ _]
    [(rf/subscribe [:qfe/p1])
     (rf/subscribe [:qfe/p1-out])])

  (fn [[p1 out]]
    (cond
      (nil? p1) default-val
      (and (string? p1)
           (empty? p1)) default-val
      (.isNaN js/Number p1) math-error
      (= out :hpa) (two-decimals p1)
      (= out :hg) (-> p1
                      hpa->hg
                      two-decimals))))

(rf/reg-sub
  :qfe/p1-out
  (fn [db _]
    (or (:qfe/p1-out db)
        :hpa)))

(rf/reg-sub
  :qfe/p1-out-display
  (fn [_ _]
    [(rf/subscribe [:qfe/p1-out])])

  (fn [[out]]
    (case out
      :hpa "hPa | mBar"
      :hg "hg")))

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
  [p0 h0 h1 t p0-in]
  (* p0
     (Math/pow (- 1 (/ (* 0.0065
                          (- h1 h0))
                       (+ 237.15 t))) 5.255)))

(rf/reg-sub
  :qfe/p1
  (fn [_ _]
    [(rf/subscribe [:qfe/p0-hpa])
     (rf/subscribe [:qfe/h0])
     (rf/subscribe [:qfe/h1])
     (rf/subscribe [:qfe/t])
     (rf/subscribe [:qfe/p0-in])])

  (fn [v]
    (if (some empty? (remove keyword? v))
      default-val
      (apply calculate v))))
