(ns pilot-aid.qfe.events
  (:require [re-frame.core :as rf]
            [pilot-aid.db :as db]
            [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))

(rf/reg-event-db
 :qfe/reset
 (fn-traced [db _]
   (assoc db
          :qfe/p0 nil
          :qfe/p1 nil
          :qfe/h0 nil
          :qfe/h1 nil
          :qfe/t  nil)))

(rf/reg-event-db
 :qfe/set-p0
 (fn-traced [db [_ v]]
   (assoc db :qfe/p0 v)))

(rf/reg-event-db
 :qfe/set-p1
 (fn-traced [db [_ v]]
   (assoc db :qfe/p1 v)))

(rf/reg-event-db
 :qfe/set-h0
 (fn-traced [db [_ v]]
   (assoc db :qfe/h0 v)))

(rf/reg-event-db
 :qfe/set-h1
 (fn-traced [db [_ v]]
   (assoc db :qfe/h1 v)))

(rf/reg-event-db
 :qfe/set-t
 (fn-traced [db [_ v]]
   (assoc db :qfe/t v)))

(rf/reg-event-db
  :qfe/calculate
  (fn-traced [db _]
             ))
