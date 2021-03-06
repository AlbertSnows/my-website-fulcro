(ns app.backend.helpers.core
  (:require [app.backend.data :as bd]))

(defn apply-to-components [mutator data components]
  (mapv (fn [component] (mutator data component)) components))

(defn add-id [comp-id sub-comp join-type]
  (if (nil? (:id sub-comp))
    (merge sub-comp {:id comp-id})
    (merge sub-comp {:id (join-type sub-comp comp-id)})))

(defn inner-join [inner-id sub-comp]
  (add-id inner-id sub-comp
          (fn [sub-comp comp-id]
            (str (:id sub-comp) "-" comp-id))))

(defn append-id-to-components [new-id components]
  (apply-to-components inner-join new-id components))