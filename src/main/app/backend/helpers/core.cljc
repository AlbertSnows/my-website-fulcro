(ns app.backend.helpers.core)

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