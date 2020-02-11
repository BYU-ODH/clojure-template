 (ns ZZZZ.views.core
   (:require [re-frame.core :as re-frame]
             [ZZZZ.shared :as shared]
             [ZZZZ.events]	
             [ZZZZ.subs]))

(defn main-panel []
  (let [view (re-frame/subscribe [:current-view])]
    [@view])) 

(defn render-view [view]
  (shared/err-boundary
   [:div.app-main
    view]))
                                        



