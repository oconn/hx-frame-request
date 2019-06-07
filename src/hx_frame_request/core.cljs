(ns hx-frame-request.core
  (:require
   [cljs.spec.alpha :as s]

   [hx-frame-request.events :as hxr-events]
   [hx-frame-request.subscriptions :as hxr-subscriptions]))

(def initial-state
  "Initial hx-frame-request state.

  All requests are associated into a map."
  {})

;; Specs for each individual request
(s/def ::status #{:loading :success :failure})
(s/def ::request-time number?)
(s/def ::error (s/nilable map?))
(s/def ::request (s/map-of keyword? (s/keys :req-un [::status
                                                     ::request-time
                                                     ::error])))

(defn register-events
  "Registers hx-frame-request events and request handler"
  [opts]
  (hxr-events/register-events opts))

(defn register-subscriptions
  "Registers hx-frame-request subscriptions"
  []
  (hxr-subscriptions/register-subscriptions))

(defn register-all
  "Registers both hx-frame-request events & subscriptions"
  [{:keys [event-options]}]
  (register-events event-options)
  (register-subscriptions))
