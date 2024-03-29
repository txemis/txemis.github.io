(ns test4_weather 
  (:require
   #_[goog.dom :as gdom]
;   [goog.dom :as gdom]
   [reagent.core :as r]
   [reagent.dom :as rdom]
   [ajax.core :as ajax]))


;Al menos con env acepta deps:
;org.clojure/clojurescript {:mvn/version "1.11.4"}
;(deps/add-deps '{:deps {org.clojars.askonomm/ruuter {:mvn/version "1.3.2"}}})
;(deps/add-deps '{:deps {org.clojure/clojurescript {:mvn/version "1.11.4"}}})

(defonce app-state (r/atom {:title "WhichWeather"
                            :postal-code ""
                            :api-key ""
                            :temperatures {:today {:label "Today"
                                                   :value nil}
                                           :tomorrow {:label "Tomorrow"
                                                      :value nil}}}))


;;(def api-key "93fa14a6073ddd1d4a6560d84e0a763a")

(defn handle-response [resp]
  (let [today (get-in resp ["list" 0 "main" "temp"])
        tomorrow (get-in resp ["list" 8 "main" "temp"])]
    (swap! app-state
           update-in [:temperatures :today :value] (constantly today))
    (swap! app-state 
           update-in [:temperatures :tomorrow :value] (constantly tomorrow))))


(defn get-forecast! []
  (let [postal-code (:postal-code @app-state)  api-key (:api-key @app-state)]
  (ajax/GET "http://api.openweathermap.org/data/2.5/forecast"
            {:params {"q" postal-code
                     ; "appid" api-key
                      "appid" api-key   
                      "units" "metric"}
             :handler handle-response})))




(defn title []
  [:h1 (:title @app-state)])

(defn temperature [temp]                                   ;; <1>
  [:div {:class "temperature"}
   [:div {:class "value"}
    (:value temp)]
   [:h2 (:label temp)]])

(defn postal-code []
  [:div {:class "postal-code"}
   [:h3 "Enter your postal code"]
   [:input {:type "text"
            :placeholder "Postal Code"
            :value (:postal-code @app-state)
            :on-change #(swap! app-state assoc :postal-code (-> % .-target .-value))}]
   [:button {:on-click get-forecast!} "Go"]])


(defn password []
  [:div {:class "postal-code"}
   [:h3 "Enter your password"]
   [:input {:type "text"
            :placeholder "password"
            :value (:api-key @app-state)
            :on-change #(swap! app-state assoc :api-key (-> % .-target .-value))
           ; :on-change #(swap! app-state assoc :postal-code (-> % .-target .-value))
            }]
   ;[:button {:on-click get-forecast!} "Go"]
   ])




(defn app []
  [:div {:class "app"}
   [title]                                                 ;; <2>
   [:div {:class "temperatures"}
    (for [temp (vals (:temperatures @app-state))]          ;; <3>
      [temperature temp])]
   [postal-code]
   [password]])

(defn mount-app-element []                                 ;; <4>
  (rdom/render [app] (js/document.getElementById "app")))

(mount-app-element)




(comment   ;;Esto funciona 

(defn hello-world []
  [:div
   [:h1 {:class "app-title"} "Hello, World"]])

(defn mount-app-element [] 
;  (rdom/render [hello-world] (.-getElementById "app" js/document)))
  (rdom/render [hello-world] (js/document.getElementById "app")))

(mount-app-element)


(->
 (js/document.getElementsByTagName "body")
;(js/document.getElementById "app")
 first
 (.append
  (doto (js/document.createElement "p")
    (.append
     (js/document.createTextNode "alguién ahí?")))))


(defn ^:after-load on-reload []     ;; Instruct Figwheel to re-mount the app whenever reloading code
  (mount-app-element))

)





(comment
(->
(js/document.getElementById "app")
 first
 (.append
  (doto (js/document.createElement "p")
    (.append
     (js/document.createTextNode "alguién más ahí?")))))

;(js/document.getElementById ("app").innerHTML = "Hola caracola")
)



(comment
  
  (ns example
  (:require [reagent.core :as r]))

(defn simple-component []
  [:div
   [:p "I am a component!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]])

(defn render-simple []
  (r/render [simple-component]
    (.-body js/document)))
)


(comment
(-> js/document
    (.getElementById "app")
    (.-innerHTML)) ; returns contents of element with id 'app'

(-> js/document
    (.getElementById "app")
    (.-innerHTML)
    (set! "Hello Clojure!")) ; Sets new content for element 
)



(comment

(+ 1 2 3)

(->
 (js/document.getElementsByTagName "body")
 first
 (.append
  (doto (js/document.createElement "p")
    (.append
     (js/document.createTextNode "alguién ahí?")))))

(defn foo [])

(js/alert "Isn't this cool? :)")

(+ 3 4)

;(js/alert "Hola..........")

(comment
(+ 1 2))

(.log js/console "Success!")


;; <------------et----------->
     (defn greet []
       (println "|hello world"))
  ;;            <-----ei----->
  ;;   <----------ee---------->


(comment
  (print (📍str "hello"))   ;; Execute `:IcedEvalOuterTopList`
  (print (str "world")))    ;; => hello

(comment
📍(print (str "hello"))     ;; Execute `:IcedEvalOuterTopList`
  (print (str "world")))    ;; => helloworld

(comment
  (print (str "hello"))   ;; Execute `:IcedEvalOuterTopList`
  (print (str "world")))    ;; => hello

(comment
(print (str "hello"))     ;; Execute `:IcedEvalOuterTopList`
  (print (str "world")))    ;; => hellowor

(js/alert "Caracola!") 

;:IcedConnect 1339
;:IcedRequire   -> recarga todo el fichero 
;  ei  ee   et  
; <Leader>  -> definido como "/"
;:map   -> todos los atajos
; Me funciona para .clj pero no para .cljs  (tendría que cambiarlo en html playground.clj"

)
