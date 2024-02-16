(ns playground
  (:require
   #_[goog.dom :as gdom]
   [reagent.core :as r]
   [reagent.dom :as rdom]
   [ajax.core :as ajax]))



(defonce app-state (r/atom {:title "WhichWeather"
                            :postal-code ""
                            :temperatures {:today {:label "Today"
                                                   :value nil}
                                           :tomorrow {:label "Tomorrow"
                                                      :value nil}}}))

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
            :value (:postal-code @app-state)}]
   [:button "Go"]])

(defn app []
  [:div {:class "app"}
   [title]                                                 ;; <2>
   [:div {:class "temperatures"}
    (for [temp (vals (:temperatures @app-state))]          ;; <3>
      [temperature temp])]
   [postal-code]])

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
