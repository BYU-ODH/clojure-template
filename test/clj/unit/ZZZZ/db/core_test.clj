(ns ZZZZ.db.core-test
  "Entry database testing namespace, testing basic functions and providing functions for testing"
  (:require [ZZZZ.db.core :refer [*db*] :as db]
            [ZZZZ.db.test-util :as tcore]
            [ZZZZ.handler :refer [app]]
            [mount.core]
            [clojure.test :refer [deftest is testing]]))

(tcore/basic-transaction-fixtures
 (mount.core/start #'ZZZZ.handler/app))

