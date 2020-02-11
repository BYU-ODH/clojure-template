(ns user
  (:require [mount.core :as mount]
            [garden-gnome.watcher :as garden-gnome]
            [ZZZZ.figwheel :refer [start-fw stop-fw cljs]]
            [ZZZZ.core]
            ))

(mount/defstate garden
  :start (garden-gnome/start! (garden-gnome/default-config))
  :stop (garden-gnome/stop! garden))

(defn start []
  (mount/start))

(defn stop []
  (mount/stop))

(defn restart []
  (stop)
  (start))


