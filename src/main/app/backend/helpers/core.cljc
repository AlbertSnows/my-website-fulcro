(ns app.backend.helpers.core)

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
(defn build-image [{:image/keys [id alt src]}]
  {:image/id  (str id "-img")
   :image/alt alt
   :image/src src})
(defn add-id-to-map [id map key]
  (assoc map key id))
(defn build-href [{:href/keys [id link image]}]
  {:href/id    (str id "-href")
   :href/link  link
   :href/image (build-image (add-id-to-map (str id "-href") image :image/id))})
(defn create-image-map [src alt]
  {:image/src src :image/alt alt})
(defn create-href-map [href-id link src alt]
  {:href/id href-id
   :href/link link
   :href/image (create-image-map src alt)})
(defn create-gallery-map [gallery-id photos-data]
  {:gallery/id gallery-id
   :gallery/photos (mapv #(apply create-href-map %) photos-data)})