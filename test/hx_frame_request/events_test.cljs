(ns hx-frame-request.events-test
  (:require
   [cljs.test :refer-macros [deftest is testing]]

   [hx-frame-request.core :as hx-frame-request]
   [hx-frame-request.events :as e]))

(def app-context {:db (merge {}
                             hx-frame-request/initial-state)})
