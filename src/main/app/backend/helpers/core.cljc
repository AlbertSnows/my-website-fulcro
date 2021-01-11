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

(defn outer-join [outer-id sub-comp]
	(add-id outer-id sub-comp
		(fn [sub-comp comp-id]
			(str comp-id "-" (:id sub-comp)))))

(defn add-id-to-components-and-apply [new-id components]
	(apply-to-components outer-join new-id components))

(defn append-id-to-components [new-id components]
	(apply-to-components inner-join new-id components))

(defn make-gallery [id gallery]
	{:gallery/id     id
	 :gallery/photos (append-id-to-components id gallery)})

(defn build-timebox
	[{:timebox/keys [id left middle right] :as timebox-data}]
	(assoc timebox-data
		:timebox/left (make-gallery id left)
		:timebox/middle (bd/node-options middle)
		:timebox/right (make-gallery id right)))

(defn build-img [data]
	(let [{:image/keys [id src alt]} data]
		{:image/src src
		 :image/id  (str id "-img")
		 :image/alt alt}))

(defn build-href [data]
	(let [{:href/keys [id link image]} data
				new-id (str id "-href")
				img-id (:image/id image)
				new-img-id (str new-id "-" img-id)]
		{:href/id   new-id
		 :href/link link
		 :href/image (build-img (assoc image :image/id new-img-id))}))