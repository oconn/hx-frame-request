(ns hx-frame-request.core-test
  (:require
   [cljs.test :refer-macros [deftest is testing]]
   [clojure.set :refer [difference]]

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

  (let [{:keys [event effect]} @hx-frame-registrar/registrar]

    (testing "Registers hx-frame-request events with hx-frame's registry"
      (is (empty? (difference (-> event keys set)
                              hx-frame-request-events))))

    (testing "Registers hx-frame-request effects with hx-frame's registry"
      (is (empty? (difference (-> effect keys set)
                              hx-frame-request-effects))))))

(deftest register-events
  (c/register-subscriptions)

  (let [{:keys [subscription]} @hx-frame-registrar/registrar]

    (testing "Registers hx-frame-request subscriptions with hx-frame's registry"
      (is (empty? (difference (-> subscription keys set)
                              hx-frame-request-subscriptions))))))

(deftest register-all
  (c/register-all {})

  (let [{:keys [event effect subscription]} @hx-frame-registrar/registrar]

    (testing "Registers hx-frame-request events with hx-frame's registry"
      (is (empty? (difference (-> event keys set)
                              hx-frame-request-events))))

    (testing "Registers hx-frame-request effects with hx-frame's registry"
      (is (empty? (difference (-> effect keys set)
                              hx-frame-request-effects))))

    (testing "Registers hx-frame-request subscriptions with hx-frame's registry"
      (is (empty? (difference (-> subscription keys set)
                              hx-frame-request-subscriptions))))))
