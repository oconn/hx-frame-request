(ns hx-frame-request.core-test
  (:require
   [cljs.test :refer-macros [deftest is testing]]

   [hx-frame.registrar :as hx-frame-registrar]
   [hx-frame-request.core :as c]))

(def hx-frame-request-events #{:hx-frame-request/start
                               :hx-frame-request/done
                               :hx-frame-request/reset})

(def hx-frame-request-effects #{:request
                                :request-mock})

(def hx-frame-request-subscriptions #{:request/track-request
                                      :request/is-dispatching})

(deftest register-events
  (c/register-events {})

  (testing "Registers hx-frame-request events with hx-frame's registry"
    (is (->> hx-frame-request-events
             (map (partial hx-frame-registrar/get-handler :event))
             (remove some?)
             (empty?))))

  (testing "Registers hx-frame-request effects with hx-frame's registry"
    (is (->> hx-frame-request-effects
             (map (partial hx-frame-registrar/get-handler :effect))
             (remove some?)
             (empty?)))))

(deftest register-subscriptions
  (c/register-subscriptions)

  (testing "Registers hx-frame-request subscriptions with hx-frame's registry"
    (is (->> hx-frame-request-subscriptions
             (map (partial hx-frame-registrar/get-handler :subscription))
             (remove some?)
             (empty?)))))

(deftest register-all
  (c/register-all {})

  (testing "Registers hx-frame-request events with hx-frame's registry"
    (is (->> hx-frame-request-events
             (map (partial hx-frame-registrar/get-handler :event))
             (remove some?)
             (empty?))))

  (testing "Registers hx-frame-request effects with hx-frame's registry"
    (is (->> hx-frame-request-effects
             (map (partial hx-frame-registrar/get-handler :effect))
             (remove some?)
             (empty?))))

  (testing "Registers hx-frame-request subscriptions with hx-frame's registry"
    (is (->> hx-frame-request-subscriptions
             (map (partial hx-frame-registrar/get-handler :subscription))
             (remove some?)
             (empty?)))))
