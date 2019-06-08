(ns hx-frame-request.events-test
  (:require
   [cljs.test :refer-macros [deftest is testing]]

   [hx-frame-request.core :as hx-frame-request]
   [hx-frame-request.events :as e]))

(def app-context {:db (merge {}
                             hx-frame-request/initial-state)})

(def request-time (.getTime (js/Date.)))

(def on-start-db (e/request-start (:db app-context)
                                  [:hx-frame-request/start
                                   {:name :some-request
                                    :request-time request-time}]))

(deftest request-start
  (testing "Sets the request information is set on start"
    (is (= {:some-request {:status :loading
                           :request-time request-time
                           :error nil}}
           (:hx-frame-request on-start-db)))))

(deftest request-done
  (let [on-done-db (e/request-done on-start-db
                                   [:hx-frame-request/done
                                    {:name :some-request
                                     :request-time request-time
                                     :error nil
                                     :status :success}])]
    (testing "Updates request state when finished"
      (is (= {:some-request {:status :success
                             :request-time request-time
                             :error nil}}
             (:hx-frame-request on-done-db))))))

(deftest request-reset
  (let [on-reset-db (e/request-reset on-start-db
                                     [:hx-frame-request/reset :some-request])]
    (testing "Clears the request state when dispatched"
      (is
       (and (some? (get-in on-start-db [:hx-frame-request :some-request]))
            (nil? (get-in on-reset-db [:hx-frame-request :some-request])))))))
