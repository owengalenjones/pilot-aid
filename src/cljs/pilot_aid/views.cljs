(ns pilot-aid.views
  (:require [re-frame.core :as re-frame]
            [pilot-aid.subs :as subs]
            [pilot-aid.qfe.views :as qfe]))

(defn header
  []
  [:div
   [:a {:href "#/"} "home"]
   " | "
   [:a {:href "#/qfe"} "qfe"]])

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div "home"]))

(defn- panels
  [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :qfe-panel [qfe/panel]
    [:div]))

(defn show-panel
  [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [:div.container
     ;[header]
     ;[show-panel @active-panel]
     [show-panel :qfe-panel] ; just qfe for now!
     ]))
