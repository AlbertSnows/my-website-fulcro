(ns app.backend.resolvers.timebox
	(:require
		[com.wsscode.pathom.connect :as pc :refer [defresolver]]
		[app.backend.data :as bd]))

(defresolver index-explorer [env _]
	{::pc/input  #{:com.wsscode.pathom.viz.index-explorer/id}
	 ::pc/output [:com.wsscode.pathom.viz.index-explorer/index]}
	{:com.wsscode.pathom.viz.index-explorer/index
	 (-> (get env ::pc/indexes)
		 (update ::pc/index-resolvers #(into {} (map (fn [[k v]] [k (dissoc v ::pc/resolve)])) %))
		 (update ::pc/index-mutations #(into {} (map (fn [[k v]] [k (dissoc v ::pc/mutate)])) %)))})

(defresolver infinite-pages
	[env input]
	{::pc/output [{:paginate/items [:item/id]}]}
	(let [params (-> env :ast :params)
				{:keys [start end]} params]
		{:paginate/items (mapv (fn [id]
														 {:item/id id})
											 (range start end))}))

(defresolver load-tbox-left
	[env input]
	{::pc/input #{:timebox/left}
	 ::pc/output [{:timebox/left
								 [:gallery/id
									:gallery/photos]}]}
	(let [id (get :timebox/left input)]
		{:gallery/id id
		 :gallery/photos (get bd/galleries id)}))

(defresolver load-tbox-middle
	[env input]
	{::pc/input #{:timebox/middle}
	 ::pc/output [{:timebox/middle
								 [:gallery/id
									:gallery/photos]}]}
	(let [id (get :timebox/middle input)
				side-data (get bd/side id)
				{:keys [side gallery]} side-data]
		(get bd/node-options id)))

(defresolver load-tbox-right
	[env input]
	{::pc/input #{:timebox/right}
	 ::pc/output [{:timebox/right
								 [:gallery/id
									:gallery/photos]}]}
	(let [id (get :timebox/right input)]
		{:gallery/id id
		 :gallery/photos (get bd/galleries id)}))

(defresolver load-timebox
	[env input]
	{::pc/input #{:timebox/id}
	 ::pc/output [{:list/timeboxes
								 [:timebox/id
									:timebox/left
									:timebox/middle
									:timebox/right]}]}
	(let [id (get :timebox/id input)
				timebox (get bd/timebox-entries id)
				{:keys [left middle right]} timebox]
		{:list/timeboxes
		 [{:timebox/id id}
			{:timebox/right right}
			{:timebox/middle middle}
			{:timebox/left middle}]}))

(defresolver load-gallery
	[env input]
	{::pc/input #{:gallery/id}
	 ::pc/output [:gallery/id
								{:gallery/photos
								 [:link
									{:image
									 [:id :src :alt]}]}]}
	(let [id (get :gallery/id input)]
		{:gallery/id id
		 :gallery/photos (get bd/galleries id)}))

(defresolver load-href
	[env input]
	{::pc/input #{:href/id}
	 ::pc/output [:href/link
								{:href/image
								 [:image/id
									:image/src
									:image/alt]}]}
	(let [id (:href/id input)] (bd/build-href (bd/get-href id))))

(def resolvers
	[
	 ;infinite-pages load-tbox-left load-tbox-middle
	 ;load-tbox-right load-timebox
	 ; load-gallery
	 load-href
	 ])
