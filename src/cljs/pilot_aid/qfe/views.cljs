(ns pilot-aid.qfe.views
  (:require [re-frame.core :as rf]
            [goog.string :as gstring]
            [goog.string.format]))

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
      [:div.input-group-append
       [:button.btn.btn-outline-secondary.dropdown-toggle {:type "button"
                                                           :data-toggle "dropdown"
                                                           :aria-haspopup "true"
                                                           :aria-expanded "false"}
        @(rf/subscribe [:qfe/p0-in-display])]
       [:div.dropdown-menu
        [:a.dropdown-item {:href "#" :on-click #(do (.preventDefault %)
                                                    (rf/dispatch [:qfe/set-p0-in :hpa]))} "hPa | mBar"]
        [:a.dropdown-item {:href "#" :on-click #(do (.preventDefault %)
                                                    (rf/dispatch [:qfe/set-p0-in :hg]))} "hg"]]]]]

    [:div.form-group
     [:label {:for "T"} "Temperature"]
     [:div.input-group
      [:input.form-control {:type "text"
                            :id "T"
                            :value @(rf/subscribe [:qfe/t])
                            :on-change #(rf/dispatch [:qfe/set-t (-> % .-target .-value)])}]
      [:div.input-group-append [:div.input-group-text "celsius"]]]]

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

    [:div
     [:h3 "Target pressure " @(rf/subscribe [:qfe/p1-out-format])]]

    [:div
     [:div.btn-group
      [:div.dropdown
       [:button.btn.btn-outline-secondary.dropdown-toggle
        {:type "button"
         :id "p1OutPressureSetting"
         :data-toggle "dropdown"
         :aria-haspopup "true"
         :aria-expanded "false"}
        @(rf/subscribe [:qfe/p1-out-display])]
       [:div.dropdown-menu {:aria-labelledby "p1OutPressureSetting"}
        [:a.dropdown-item {:href "#" :on-click #(do (.preventDefault %)
                                                    (rf/dispatch [:qfe/set-p1-out :hpa]))} "hPa | mBar"]
        [:a.dropdown-item {:href "#" :on-click #(do (.preventDefault %)
                                                    (rf/dispatch [:qfe/set-p1-out :hg]))} "hg"]]]]

     " "
     [:div.btn-group
      [:button.btn.btn-danger {:type "button" :on-click #(rf/dispatch [:qfe/reset])} "Reset"]]]]])
