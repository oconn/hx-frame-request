(ns hx-frame-request.subscriptions
  (:require [hx-frame.core :as hx-frame]))

(defn is-dispatching
  [{:keys [hx-frame-request]} [_ request-name]]
  (= :loading (get-in hx-frame-request [request-name :status])))

(defn track-request
  [{:keys [hx-frame-request]} [_ request-name]]
  (get hx-frame-request request-name {:status :never-requested}))

(defn register-subscriptions
  "Register re-frame-request subscriptions"
  []
  (hx-frame/register-subscription :request/track-request track-request)
  (hx-frame/register-subscription :request/is-dispatching is-dispatching))
