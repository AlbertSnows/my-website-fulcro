(ns app.ui.helpers.core
  (:require
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div]]
    [com.fulcrologic.fulcro.components :as comp
     :refer [get-initial-state]]
    [app.backend.data :as bd]))

(defn div-with-classes-and-id [id classes contents]
  (div {:id id :className classes} (do contents)))

(defn update-id-in-data [new-id data id-key]
  (let [old-id (id-key data)]
    (assoc data id-key (str old-id "-" new-id))))

(defn build-element [id appendee classes get-data]
  (let [new-id (str id "-" appendee)]
    (div-with-classes-and-id new-id classes (get-data new-id))))

(defn build-left-element [id get-data]
  (build-element id "left" "about-left-side" get-data))
(defn build-right-element [id get-data]
  (build-element id "right" "about-right-side" get-data))

(defn add-id [id map key]
  (assoc map key id))

(defn append-id [id map key]
  (assoc map key (str id "-" (key map))))
