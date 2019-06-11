(ns hx-frame-request.core
  (:require
   [hx-frame-request.events :as hxr-events]
   [hx-frame-request.subscriptions :as hxr-subscriptions]))

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
