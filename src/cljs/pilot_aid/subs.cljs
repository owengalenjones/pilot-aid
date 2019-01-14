(ns pilot-aid.subs
  (:require [re-frame.core :as re-frame]
            [pilot-aid.qfe.subs]))

(re-frame/reg-sub
  ::active-panel
  (fn [db _]
    (:active-panel db)))
