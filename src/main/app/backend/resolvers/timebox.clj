(ns app.backend.resolvers.timebox
	(:require
		[com.wsscode.pathom.connect :as pc
		 :refer [defresolver]]
		[app.backend.helpers.core :as bhc]
		[app.backend.data :as bd]))

(defresolver load-href
	[env input]
	{::pc/input #{:href/id}
	 ::pc/output [:href/link
								{:href/image
								 [:image/id
									:image/src
									:image/alt]}]}
	;(let [id (:href/id input)] (bhc/build-href (bd/request id )))
	)

(defresolver load-gallery
	[env input]
	{::pc/input #{:gallery/id}
	 ::pc/output [{:gallery/photos [:href/id]}]}
	(let [id (:gallery/id input)] (bd/request id bd/galleries)))

(defresolver load-timebox
	[env input]
	{::pc/input #{:timebox/id}
	 ::pc/output [{:timebox/left [:gallery/id]}
								{:timebox/middle [:image/id :image/src :image/alt]}
								{:timebox/right [:gallery/id]}]}
	(let [id (:timebox/id input)] (bd/request id bd/get-timebox)))

(def resolvers [load-href load-gallery load-timebox])
