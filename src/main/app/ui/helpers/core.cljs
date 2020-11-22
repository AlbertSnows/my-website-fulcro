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

(defn add-id [comp-id sub-comp join-type]
  (if (nil? (:id sub-comp))
    (merge sub-comp {:id comp-id})
    (merge sub-comp {:id (join-type sub-comp comp-id)})))

(defn outer-join [outer-id sub-comp]
  (add-id outer-id sub-comp
          (fn [sub-comp comp-id]
            (str comp-id "-" (:id sub-comp)))))

(defn inner-join [inner-id sub-comp]
  (add-id inner-id sub-comp
          (fn [sub-comp comp-id]
            (str (:id sub-comp) "-" comp-id))))

(defn add-id-to-components [new-id components]
  (apply-to-components outer-join new-id components))

(defn append-id-to-components [new-id components]
  (apply-to-components inner-join new-id components))

(defn apply-contained-component [{:keys [id ui factory data]}]
  (ui (get-initial-state
        factory
        (if (vector? data)
          (add-id-to-components id data)
          (outer-join id data)))))

(defn get-first-id [components]
  (:id (first components)))