(ns ZZZZ.env
  (:require 
            [clojure.tools.logging :as log]
            [ZZZZ.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[ZZZZ started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[ZZZZ has shut down successfully]=-"))
   :middleware wrap-dev})
