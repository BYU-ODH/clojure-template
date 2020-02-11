(ns ZZZZ.core
  (:require [reagent.core :as r]
            [ZZZZ.ajax :refer [load-interceptors!]]
            [ZZZZ.routes :as routes]
            [re-frame.core :as rfc]
            [ZZZZ.views.core :as views]
            [ZZZZ.views.navbar :refer [navbar]])
  (:import goog.History))

;; -------------------------
;; Initialize app

(defn mount-components []
  (r/render [#'navbar] (.getElementById js/document "nav"))
  (r/render [#'views/main-panel] (.getElementById js/document "app")))

(defn init! []
  (load-interceptors!)
  (routes/init-routes!)
  (rfc/dispatch-sync [:initialize-db])
  (mount-components))
