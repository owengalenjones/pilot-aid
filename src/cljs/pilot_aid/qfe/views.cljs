(ns pilot-aid.qfe.views
  (:require [re-frame.core :as rf]))

(defn panel
  []
  [:div
   [:h2 "QFE Calculator"]

   [:form
    [:div.form-group
     [:label {:for "P0"} "Starting pressure"]
     [:div.input-group
      [:input.form-control {:type "text"
                            :id "P0"
                            :value @(rf/subscribe [:qfe/p0])
                            :on-change #(rf/dispatch [:qfe/set-p0 (-> % .-target .-value)])}]
      [:div.input-group-append [:div.input-group-text "hPa"]]]]

    [:div.form-group
     [:label {:for "H0"} "Starting height"]
     [:div.input-group
      [:input.form-control {:type "text"
                            :id "H0"
                            :value @(rf/subscribe [:qfe/h0])
                            :on-change #(rf/dispatch [:qfe/set-h0 (-> % .-target .-value)])}]
      [:div.input-group-append [:div.input-group-text "meters"]]]]

    [:div.form-group
     [:label {:for "H1"} "Target height"]
     [:div.input-group
      [:input.form-control {:type "text"
                            :id "H1"
                            :value @(rf/subscribe [:qfe/h1])
                            :on-change #(rf/dispatch [:qfe/set-h1 (-> % .-target .-value)])}]
      [:div.input-group-append [:div.input-group-text "meters"]]]]

    [:div.form-group
     [:label {:for "T"} "Temperature"]
     [:div.input-group
      [:input.form-control {:type "text"
                            :id "T"
                            :value @(rf/subscribe [:qfe/t])
                            :on-change #(rf/dispatch [:qfe/set-t (-> % .-target .-value)])}]
      [:div.input-group-append [:div.input-group-text "celsius"]]]]


    [:h3 "Target pressue: " @(rf/subscribe [:qfe/p1])]
    [:button.btn.btn-primary {:type "button"} "Calculate"]
    " "
    [:button.btn.btn-danger {:type "button" :on-click #(rf/dispatch [:qfe/reset])} "Reset"]]])


; P1 = P0 * (1 - (0.0065 * (h1 - h0)) / 237.15 + T)  ^ 5.255
