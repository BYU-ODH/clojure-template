(ns ZZZZ.layout
  (:require
   [hiccup.page :as hp]
   [hiccup.element :refer [javascript-tag]]
   [ring.util.http-response :refer [ok] :as ru]
   [ring.middleware.anti-forgery :refer [*anti-forgery-token*]]))

(declare ^:dynamic *app-context*)
(def style-path "/css/")
(def script-path "/js/")
(def assets-path "/assets/")

(defn context-path [& path]
  (apply str path))

(defn anti-forgery-element []
  [:input {:id "token" :value *anti-forgery-token* :type "hidden"}
   (javascript-tag (str  "var csrfToken = '" *anti-forgery-token* "'"))])

(defn include-byu-deps []
  (hp/include-css "https://cdn.byu.edu/byu-theme-components/latest/byu-theme-components.min.css")
  (hp/include-js  "https://cdn.byu.edu/byu-theme-components/latest/byu-theme-components.min.js"))

(defn top-matter
  "Topmatter including the `head` element and css calls"
  [& [_ #_userinfo]]
  [:head [:title "ZZZZ"]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1.0"}]                                        
   (javascript-tag (str "var context = ''"))
   (hp/include-css 
    (context-path assets-path "bulma/css/bulma.css")
    (context-path assets-path "font-awesome/css/all.min.css")
    (context-path style-path "style.css"))])

(defn cljs-app-modal []
  [:div#modal])

(defn cljs-app-base []
  [:div#app
   [:div.container]])

(defn cljs-app-footer []
  [:div#footer
   [:div.footer]])

(defn boiler-plate []
  [:div#boiler-wrapper
   ;; styles   
   ])

(defn cljs-includes []
  [:div
   (hp/include-js (context-path script-path "app.js?rnd=" (rand-int 1000)))
   [:script {:type "text/javascript"} "goog.require('ZZZZ.app')"]])

(defn cljs-app-navbar
  "The navbar"
  []
  [:navbar#nav])

(defn hiccup-render-cljs-base
  "Hiccup rendering (no traditional template)"
  [& [userinfo]]
  (ru/content-type
   (ok
    (hp/html5
     (top-matter userinfo)
                                        ;(anti-forgery-element)
     (cljs-app-modal)
     (cljs-app-navbar)
     (cljs-app-base)
     (cljs-app-footer)
     (cljs-includes))) ;; it makes a big difference to make sure the clojurescript is included last, so the DOM is rendered
   "text/html; charset=utf-8"))

(defn error-page
  "error-details should be a map containing the following keys:
   :status - error status
   :title - error title (optional)
   :message - detailed error message (optional)
   returns a response map with the error page as the body
   and the status specified by the status key"
  [error-details]
  {:status  (:status error-details)
   :headers {"Content-Type" "text/html; charset=utf-8"}
   :body (hp/html5
          (top-matter)
          (boiler-plate)
          [:div.alert.alert-warning
           [:h1 (or (:title error-details) (str "Error " (:status error-details)))]
           [:div.error-details (:message error-details)]])})

(defn receipt-page
  ([]
   (ru/content-type
    (ok
     (hp/html5
      [:div#receipt
       [:h1 "You submission was received"]]))
    "text/html; charset=utf-8"))
  ([application]
   (ru/content-type
    (ok
     (hp/html5
      [:div#receipt
       [:h1 "Your submission was received: "]
       [:div.content (str application) ]]))
    "text/html; charset=utf-8")))
