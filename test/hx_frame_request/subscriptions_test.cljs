(ns hx-frame-request.subscriptions-test
  (:require
   [cljs.test :refer-macros [deftest is testing]]

   [hx-frame-request.core :as hx-frame-request]
   [hx-frame-request.subscriptions :as s]))

(def app-db (merge {} hx-frame-request/initial-state))

(def request-name-mock :some-request)

(deftest is-dispatching
  (testing "Returns false the request has not been made"
    (is
     (false?
      (s/is-dispatching
       app-db
       [:hx-frame-request/is-dispatching request-name-mock]))))

  (testing "Returns true when the request is dispatching"
    (is
     (true?
      (s/is-dispatching
       (assoc-in app-db
                 [:hx-frame-request request-name-mock]
                 {:status :loading})
       [:hx-frame-request/is-dispatching request-name-mock]))))

  (testing "Returns false after the request has finished"
    (is
     (false?
      (s/is-dispatching
       (assoc-in app-db
                 [:hx-frame-request request-name-mock]
                 {:status :success})
       [:hx-frame-request/is-dispatching request-name-mock])))))
