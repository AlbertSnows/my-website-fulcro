(ns app.ui.helpers.core
  (:require
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div]]
    [com.fulcrologic.fulcro.components :as comp
     :refer [get-initial-state]]
    [app.backend.data :as bd]))

(defn div-with-classes-and-id-old [id classes contents]
  (div {:id id :className classes} contents))

(defn div-with-classes-and-id [id classes contents]
  (div {:id id :className classes} (do contents)))

(defn create-div-id [component id]
  (str (:id component) "-" id))

(defn append-id [component id]
  (merge component {:id (create-div-id component id)}))








;(defn apply-contained-component [{:keys [id ui factory data]}]
;  (ui (get-initial-state
;        factory
;        (if (vector? data)
;          (add-id-to-components-and-apply id data)
;          (outer-join id data)))))

(defn get-first-id [components]
  (:id (first components)))

(defn add-id-to-components [id components]
  (mapv (fn [component]
          (assoc component :id id))
        components))


(defn update-id-in-data [new-id data id-key]
  (let [old-id (id-key data)]
    (assoc data id-key (str old-id "-" new-id))))

(defn build-element [id appendee classes get-data]
  (let [new-id (str id "-" appendee)]
    (div-with-classes-and-id new-id classes (get-data new-id))))

(defn build-left-element [id get-data]
  (build-element id "left" "left-side" get-data))
(defn build-right-element [id get-data]
  (build-element id "left" "left-side" get-data))

