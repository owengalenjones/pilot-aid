(ns pilot-aid.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [pilot-aid.events :as events]
            [pilot-aid.routes :as routes]
            [pilot-aid.views :as views]
            [pilot-aid.config :as config]
            [$]
            [bootstrap]))


(defn dev-setup
  []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root
  []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init
  []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
