(ns main
  (:require
    [reagent.core :as r]
    [reagent.dom :as rdom]))

(defonce state (r/atom nil))

(defn app []
  [:main
   [:h1 "Hellos"]
   [:pre "State: " (pr-str @state)]
   [:button
    {:on-click #(swap! state update :counter inc)}
    "Incrementos"]])

(rdom/render [app] (.getElementById js/document "app"))

(+ 3 4)
