(ns app.ui.helpers.core
  (:require
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div]]
    [com.fulcrologic.fulcro.components :as comp
     :refer [get-initial-state]]))
(defn div-with-classes-and-id [id classes contents]
  (div {:id id :className classes} contents))
(defn create-div-id [component id]
  (str (:id component) "-" id))
(defn append-id [component id]
  (merge component {:id (create-div-id component id)}))
(defn apply-to-components [mutator data components]
  (mapv (fn [component] (mutator data component)) components))
(defn add-id [comp-id sub-comp]
  (if (nil? (:id sub-comp))
    (merge sub-comp {:id comp-id})
    (merge sub-comp {:id (str comp-id "-" (:id sub-comp))})))
(defn add-id-to-components [new-id components]
  (apply-to-components add-id new-id components))
(defn apply-contained-component [{:keys [id ui factory data]}]
  (ui
    (get-initial-state factory
                            (if (vector? data)
                              (add-id-to-components id data)
                              (add-id id data)))))
(defn get-first-id [components]
  (:id (first components)))