(ns ZZZZ.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[ZZZZ started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[ZZZZ has shut down successfully]=-"))
   :middleware identity})
