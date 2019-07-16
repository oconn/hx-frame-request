(ns hx-frame-request.db
  (:require [cljs.spec.alpha :as s]))

(def initial-state
  "Initial hx-frame-request state.

  All requests are associated into a map."
  {:hx-frame-request {}})

;; Specs for each individual request
(s/def ::status #{:loading :success :failure})
(s/def ::request-time number?)
(s/def ::error (s/nilable map?))

(s/def ::hx-frame-request (s/map-of keyword? (s/keys :req-un [::status
                                                              ::request-time
                                                              ::error])))
