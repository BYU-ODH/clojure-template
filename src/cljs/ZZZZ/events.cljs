(ns ZZZZ.events
  (:require 
   [day8.re-frame.http-fx]
   [re-frame.core :refer [reg-event-db after reg-event-fx dispatch] :as rfc]
   [ZZZZ.views.dashboard :as dash]
   [ZZZZ.db :as db :refer [app-db]]
   [clojure.spec.alpha :as s]))


;; -- Interceptors ------------------------------------------------------------
;;
;; See https://github.com/Day8/re-frame/blob/master/docs/Interceptors.md
;;
(defn check-and-throw
  "Throw an exception if db doesn't have a valid spec."
  [spec db [event]]
  (when-not (s/valid? spec db)
    (let [explain-data (s/explain-data spec db)]
      (throw
       (ex-info
        (str "Spec check after " event " failed: " explain-data)
        explain-data)))))

(defn validate-spec
  [spec]
  (if goog.DEBUG
    (after (partial check-and-throw spec))
    []))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; INITIALIZE THE RE-FRAME DB ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn ^{:private true} init-view
  "Initialize the default view `home-page`"
  [db & rest]
  (let [home-page-view #'dash/home-page]
    (-> db
        (assoc-in [:current-page :data]
                  {:name :init
                   :view home-page-view}))))

(rfc/reg-event-db
 :initialize-db
 (fn [_ _]
   (init-view app-db)))

(rfc/reg-event-db
 :set-current-page
 (validate-spec ::db/app-db)
 (fn [db [_ m]]
   (assoc db :current-page m)))

(rfc/reg-event-fx
 :handler-with-http
 (fn handler-with-http [db [_ {:keys [uri params on-success-kvec]}]]
   {:http-xhrio {:method          :get
                 :uri             uri
             ;    :headers         {"Access-Control-Allow-Origin" "http://localhost:3030"}
                 :params          params
                 :timeout         8000
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success      on-success-kvec
                 :on-failure      [:on-failure]}}))

(rfc/reg-event-fx
 :http-post
 (fn http-post [db [_ {:keys [uri params on-success-kvec]}]]
   {:http-xhrio {:method          :post
                 :uri             uri
                 :params          params
                 :headers         {"X-CSRF-Token" js/csrfToken}
                 :timeout         5000
                 :format          (ajax/json-request-format)
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success      on-success-kvec
                 :on-failure      [:on-failure]}}))

(rfc/reg-event-db
 :on-success
 (validate-spec ::db/api-result)
 (fn on-success [db [_ result]]
   (assoc db :api-result result)))

(rfc/reg-event-db
 :on-failure
 (validate-spec ::db/api-error-result)
 (fn on-failure [db [_ result]]
   (assoc db :api-error-result result)))
