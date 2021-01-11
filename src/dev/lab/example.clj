(ns lab.example)
;;This is a code graveyard. Careful not to wake any ghosts!

; {:image/id :side :gallery}
;(def side
;	{18 {:side left :gallery 11}
;	 17 {:side middle :gallery first-k}
;	 16 {:side right :gallery 10}
;	 15 {:side left :gallery 9}
;	 14 {:side middle :gallery middle}
;	 13 {:side right :gallery 8}
;	 12 {:side left :gallery 7}
;	 11 {:side middle :gallery middle}
;	 10 {:side right :gallery 6}
;	 9 {:side left :gallery 5}
;	 8 {:side middle :gallery middle}
;	 7 {:side right :gallery 4}
;	 6 {:side middle :gallery middle}
;	 5 {:side right :gallery nil}
;	 4 {:side left :gallery 3}
;	 3 {:side middle :gallery end}
;	 2 {:side left :gallery 2}
;	 1 {:side right :gallery 1}
;	 -1 {:side left :gallery nil}
;	 -2 {:side right :gallery nil}
;	 -3 {:side middle :gallery middle}})

;(defresolver load-gallery
;	[env input]
;	{::pc/input #{:gallery/id}
;	 ::pc/output [:gallery/id
;								{:gallery/photos
;								 [:link
;									{:image
;									 [:id :src :alt]}]}]}
;	(let [id (get :gallery/id input)]
;		{:gallery/id id
;		 :gallery/photos (get bd/galleries id)}))

;(defresolver index-explorer [env _]
;	{::pc/input  #{:com.wsscode.pathom.viz.index-explorer/id}
;	 ::pc/output [:com.wsscode.pathom.viz.index-explorer/index]}
;	{:com.wsscode.pathom.viz.index-explorer/index
;	 (-> (get env ::pc/indexes)
;		 (update ::pc/index-resolvers #(into {} (map (fn [[k v]] [k (dissoc v ::pc/resolve)])) %))
;		 (update ::pc/index-mutations #(into {} (map (fn [[k v]] [k (dissoc v ::pc/mutate)])) %)))})
;
;(defresolver infinite-pages
;	[env input]
;	{::pc/output [{:paginate/items [:item/id]}]}
;	(let [params (-> env :ast :params)
;				{:keys [start end]} params]
;		{:paginate/items (mapv (fn [id]
;														 {:item/id id})
;											 (range start end))}))
;
;(defresolver load-tbox-left
;	[env input]
;	{::pc/input #{:timebox/left}
;	 ::pc/output [{:timebox/left
;								 [:gallery/id
;									:gallery/photos]}]}
;	(let [id (get :timebox/left input)]
;		{:gallery/id id
;		 :gallery/photos (get bd/galleries id)}))
;
;(defresolver load-tbox-middle
;	[env input]
;	{::pc/input #{:timebox/middle}
;	 ::pc/output [{:timebox/middle
;								 [:gallery/id
;									:gallery/photos]}]}
;	(let [id (get :timebox/middle input)
;				side-data (get bd/side id)
;				{:keys [side gallery]} side-data]
;		(get bd/node-options id)))

;(defresolver load-tbox-right
;	[env input]
;	{::pc/input #{:timebox/right}
;	 ::pc/output [{:timebox/right
;								 [:gallery/id
;									:gallery/photos]}]}
;	(let [id (get :timebox/right input)]
;		{:gallery/id id
;		 :gallery/photos (get bd/galleries id)}))
;
;(defresolver load-timebox
;	[env input]
;	{::pc/input #{:timebox/id}
;	 ::pc/output [{:list/timeboxes
;								 [:timebox/id
;									:timebox/left
;									:timebox/middle
;									:timebox/right]}]}
;	(let [id (get :timebox/id input)
;				timebox (get bd/timebox-entries id)
;				{:keys [left middle right]} timebox]
;		{:list/timeboxes
;		 [{:timebox/id id}
;			{:timebox/right right}
;			{:timebox/middle middle}
;			{:timebox/left middle}]}))

; original working Gallery
;(defsc Gallery
;	[this {:gallery/keys [photos id] :as props}]
;	{:ident    :gallery/id
;	 :query [:gallery/id
;					 {:gallery/photos (get-query Href)}]}
;	(div {:id        (str id "-gallery")
;				:className "gallery"}
;		(mapv
;			(fn [photo]
;				(ui-href
;					(get-initial-state Href photo)))
;			photos)))
;(def ui-gallery (factory Gallery {:keyfn :gallery/id}))

; original working Timebox
;(defsc Timebox
;	[this {:timebox/keys [id left middle right]}]
;	{:query
;					[:timebox/id
;					 {:timebox/left (get-query Gallery)}
;					 {:timebox/middle (get-query Image)}
;					 {:timebox/right (get-query Gallery)}
;					 ]
;	 :ident [:timebox/by-id :timebox/id]
;	 :css   (:css uicss/Timebox)}
;	(let [{:keys [timebox]} (get-classnames Timebox)]
;		(div {:classes [timebox]
;					:id      id}
;			(build-left-element id
;				(fn [new-id]
;					[(ui-image bd/left-arrow)
;					 (ui-gallery
;						 (update-id-in-data new-id left :gallery/id))]))
;			(ui-image middle)
;			(build-right-element id
;				(fn [new-id]
;					[(ui-image bd/right-arrow)
;					 (ui-gallery
;						 (update-id-in-data new-id right :gallery/id))])))))
;(def ui-timebox (factory Timebox {:keyfn :timebox/id}))

;(defsc About [this {:about/keys [timebox] :as props}]
;  {:ident         (fn [] [:component/id :about])
;   :route-segment ["about"]
;   :query         [{:about/timebox (get-query Timebox)}]
;   :initial-state (fn [_] {:about/timebox about-initial-state})}
;  (div {:id "about-page-body"}
;       (style-element {:component Timebox})
;       (mapv ui-timebox timebox)))
;
;(defsc TimeboxList [this {:keys [list/timebox-item]}]
;  {:initial-state (fn [params]
;                    {:list/timebox-item (comp/get-initial-state b/ListPage ??)})
;   :query [{:list/current-page (comp/get-query b/ListPage)}]
;   :ident (fn [] [:list/by-id 1])}
;  (let [{:keys [page/number]} current-page]
;    (dom/div
;      (dom/button
;        {:disabled (= 1 number)
;         :onClick #(comp/transact! this
;                     [(b/goto-page {:page-number (dec number)})])}
;        "Prior Page")
;      (dom/button
;        {:onClick #(comp/transact! this
;                     [(b/goto-page {:page-number (inc number)})])}
;        "next page")
;      (b/ui-list-page current-page))))
;
;(def ui-list (comp/factory TimeboxList))

;
;(defsc TimeboxList
;	[this {:list/keys [timeboxes] :as props}]
;	{:query         [{:list/timeboxes (comp/get-query Timebox)}]
;	 :ident         (fn [] [:list/by-id 1])
;	 :initial-state (fn [_]
;										{:list/timeboxes
;										 [
;											about-initial-state
;											]})}
;	(map ui-timebox timeboxes))
;(def ui-timebox-list (factory TimeboxList))

;In terms of implementation, you would just need to create an event listener, that listens for the scrolling of the window or body. Then, you just need to check if they are at the bottom of the window / body, and call a function that updates the state of the component, when that happens. The function would call the remote to fetch more items.

;https://gist.github.com/nberger/b5e316a43ffc3b7d5e084b228bd83899
;https://github.com/joakimmohn/crudurama/blob/master/src/cljs/crudurama/infinite-scroll.cljs
;$(window).scroll(function () {
;   if ($(window).scrollTop() >= $(document).height() - $(window).height() - 10) {
;      //Add something at the end of the page
;   }
;});

;(behavior "Triggering an event from state b"
;      (uism/trigger! app ::id :event/bang!)
;      (assertions
;        "Moves to the correct state"
;        (uism/get-active-state app ::id) => :state/a
;        "Applies the expected actions"
;        (fget-in [:WOW]) => 2)))

;(defsc LeftT
;	[this {:left-t/keys [components id]}]
;	{:query [:left-t/components :left-t/id]}
;	(div-with-classes-and-id
;		(str id "-left")
;		"left-side"
;		(add-id-to-components (str id "-left") components)
;		;(mapv apply-contained-component
;		;  (append-id-to-components (str id "-left") components))
;		))
;(def ui-leftt (factory LeftT {:keyfn :left-t/id}))


;(get-initial-state
;	Timebox
;	{:id     1
;	 (get side 1)
;
;					 :middle [{:ui      ui-image
;										 :factory Image
;										 :data    (get node-options :end)}]
;
;	 })

;(defn div-with-classes [classes contents]
;  (div {:className classes} contents))
;(defn rand-str [len]
;  (apply str (take len (repeatedly #(char (+ (rand 26) 65))))))
;(mapv
;  (fn [component]
;    (merge
;      component
;      {:id (str id "-" "node")}))
;  middle)
;(div {:id        (create-div-id id "right")
;      :className "right-side"}
;     (mapv (fn [component]
;             (apply-contained-component
;               (append-id component "right")))
;           components))
;(dom/div
;  {:id (create-div-id id "middle") :className "middle"}
;  (mapv (fn [component]
;          (apply-contained-component
;            (append-id component "middle")))
;        components))
;(div {:id id :className "left-side"}
;     (mapv (fn [component]
;             (apply-contained-component component))
;           components))
;(defsc Left
;  [this {:left/keys [components id]}]
;  {:query [:left/components
;           :left/id]
;   :initial-state
;          (fn [components]
;            {:left/components components
;             :left/id (:id (first components))})}
;  (div {:id (create-div-id (first components) "left")
;        :className "left-side"}
;       (mapv (fn [component]
;               (apply-contained-component
;                 (append-id component "left")))
;             components)))
;(def ui-left (comp/factory Left {:keyfn :left/id}))
;
;(defsc Middle [this {:middle/keys [id components] :as props}]
;  {:ident :middle/id
;   :query [:middle/id :middle/components]
;   :initial-state
;          (fn [components]
;            {:middle/id (:id (first components))
;             :middle/components components})
;   :css   (:css uicss/Middle)}
;  (let [{:keys [middle-main-page padding-bottom]} (css/get-classnames Middle)]
;    (dom/div
;      {:id (create-div-id id "middle") :className "middle"}
;      (mapv (fn [component]
;              (apply-contained-component
;                (append-id component "middle")))
;            components))))
;(def ui-middle (comp/factory Middle {:keyfn :middle/id}))
;
;(defsc Right
;  [this {:right/keys [components id] :as props}]
;  {:query [:right/components :right/id]
;   :initial-state
;          (fn [components]
;            {:right/components components
;             :right/id (:id (first components))})
;   :css   [[:.right
;            {:display        "flex"                         ;
;             :flex-direction "column"
;             :align-items    "center"
;             :padding-right  "1.5em"
;             :width          "100%"}]
;           [:.right>a+a
;            {:padding-top "6em"}]]}
;  (div {:id (create-div-id (first components) "right")
;        :className "right-side"}
;       (mapv (fn [component]
;               (apply-contained-component
;                 (append-id component "right")))
;             components)))
;(def ui-right (comp/factory Right {:keyfn :right/id}))

;(defsc AboutMiddle [this {:middle/keys [id] :as props}]
;  {:query [:middle/id]
;   :ident :middle/id
;   :initial-state
;          (fn [{:keys [id]}]
;            {:middle/id id})
;   :css   [[:.middle-main-page
;            {:display         "flex"
;             :flex-direction  "column"
;             :font-size       "4vw"
;             :margin          "0 auto"
;             :justify-content "center"
;             :min-width       "6em"
;             :height          "auto"}]
;           [:.padding-bottom
;            {:padding-bottom "1em"}]
;           [:.enlarge-text
;            {:font-size "larger"
;             :overflow  "hidden"}]
;           [:.small-text
;            {:font-size  "initial"
;             :margin     "0 auto"
;             :text-align "center"}]]}
;  (let [{:keys [middle-main-page padding-bottom]} (css/get-classnames AboutMiddle)]
;    (dom/div
;      (ui-image
;        (comp/get-initial-state Image
;                                (get node-options id))))))
;(def ui-about-middle (comp/factory AboutMiddle {:keyfn :middle/id}))
;
;(defsc AboutRightSide [this {:right-side/keys [gallery] :as props}]
;  {:query [:right-side/gallery
;           ]
;   :initial-state
;          (fn [gallery]
;            {:right-side/gallery
;             (comp/get-initial-state Gallery gallery)}
;            )
;   :css
;          [[:.right-side
;            {:display        "flex"                         ;
;             :flex-direction "column"
;             :align-items    "center"
;             :padding-right  "1.5em"
;             :width          "100%"}]
;           [:.right-side>a+a
;            {:padding-top "6em"}]]
;   }
;  (dom/div {:className "about-right"}
;           (dom/div
;             (ui-image
;               (comp/get-initial-state
;                 Image
;                 {:id  "right-side-arrow"
;                  :alt "point to the left from right"
;                  :src "../images/right-side-arrow.PNG"})
;               ))
;
;           (ui-gallery gallery)
;           )
;  )
;(def ui-about-right (comp/factory AboutRightSide))

;(defsc Middle [this {:middle/keys [id ui factory data] :as props}]
;  {:ident :middle/id
;   :query [:middle/id
;           :middle/ui
;           :middle/factory
;           :middle/data]
;   :initial-state
;          (fn [{:keys [id data ui factory]}]
;            {:middle/id      id
;             :middle/ui      ui
;             :middle/factory factory
;             :middle/data    data})
;   :css   (:css uicss/Middle)}
;  (let [{:keys [middle-main-page padding-bottom]} (css/get-classnames Middle)]
;    (dom/div {:id id :className "middle"}
;             (mapv (fn [thing]
;                     (ui (comp/get-initial-state
;                           factory thing))) data))))

;(defsc Right
;  [this {:right/keys [components]}]
;  {:query [:right/components]
;   :initial-state
;          (fn [components]
;            {:right/components components})}
;  ;((GenericPTHtml "right") components)
;  (dom/div)
;  )


;((GenericPTHtml "left-side") components)
;(div {:className "left-side"}
;     (mapv apply-contained-component components)
;     )
;(div-with-classes
;  "left-side"
;  (mapv apply-contained-component components))

;(defn GenericPTQuery [name]
;  {:query [:left/components]
;   :initial-state
;    (fn [components]
;      {:left/components components})}
;(let [kwcomp (keyword (str name "/components"))]
;  {:query [(keyword (str name "/components"))]
;   :initial-state
;          (fn [components]
;            {(keyword (str name "/components")) components})}
;)
;)
;(defn GenericEQ [name]
;(let [ui (keyword (str name "/ui"))
;      data (keyword (str name "/data"))
;      factory (keyword (str name "/factory"))]
;  {:query [
;           (keyword (str name "/ui"))
;           (keyword (str name "/data"))
;           (keyword (str name "/factory"))
;           ]
;   :initial-state
;    (fn [pui pdata pfactory]
;      {(keyword (str name "/ui")) pui
;       (keyword (str name "/data")) pdata
;       (keyword (str name "/factory")) pfactory})}
;  ;)
;)

;(defsc Top
;  [this {:top/keys [ui state factory]}]
;  (GenericEQ "top")
;  ((GenericEHtml "top-div") ui state factory))
;(defsc Top [this {:top-left/keys [state ui] :as props}]
;  {:query [:top-left/ui
;           :top-left/state]
;   :initial-state
;          (fn [{:keys [ui factory state]}]
;            {:top-left/state
;                          (comp/get-initial-state factory state)
;             :top-left/ui ui}
;            )}
;  (dom/div {:className "top"} (ui state)))
;(defsc Top

;(defsc Bottom [this {:bottom-left/keys [ui data] :as props}]
;  {:query [:bottom-left/ui :bottom-left/data]
;   :initial-state
;          (fn [{:keys [state factory ui]}]
;            {:bottom-left/ui ui
;             :bottom-left/data
;                             (comp/get-initial-state factory state)
;             })}
;  (dom/div {:className "bottom"}
;           (ui data)))
;(defsc LeftSide
;  [this {:left-side/keys [ui
;                          data
;                          factory
;                          ] :as props}]
;  {:query [:left-side/data
;           :left-side/ui
;           :left-side/factory]
;   :initial-state
;          (fn [{:keys [ui factory data]}]
;            {:left-side/data data
;             :left-side/ui ui
;             :left-side/factory factory
;             }
;            )}
;  (dom/div {:className "left-side"}
;           (mapv
;             (fn [item]
;               (ui (comp/get-initial-state factory item))) data)
;           ))

;(defn GenericQuerySetup [name]
;  {:query [:name/components name]})
;
;(defsc CreatePassThroughGeneric
;  [this {:generic/keys [components]}]
;  {:query [:generic/components]
;   :initial-state
;          (fn [collection]
;            {:name/components collection})}
;  (GenericHtmlSetup components "example"))

;(defn call [this & that]
;  (apply (resolve (symbol this)) that))

;(defn CreateGenericEndComponent [name classes]
;  (defn name [this [{:name/keys [ui state factory]}]]
;    {:query [:name/ui
;             :name/factory
;             :name/state]
;     :initial-state
;            (fn [{:keys [ui factory state]}]
;              {:name/ui ui
;               :name/state state
;               :name/factory factory})}
;    (dom/div
;      {:className name}
;      (ui (comp/get-initial-state
;            factory
;            state)))))

;(defsc Top [this {:top-left/keys [state ui] :as props}]
;  {:query [:top-left/ui
;           :top-left/state]
;   :initial-state
;          (fn [{:keys [ui factory state]}]
;            {:top-left/state
;                          (comp/get-initial-state factory state)
;             :top-left/ui ui}
;            )}
;  (dom/div {:className "top"} (ui state)))
;(def ui-top (comp/factory Top))
;
;(defsc Bottom [this {:bottom-left/keys [ui data] :as props}]
;  {:query [:bottom-left/ui :bottom-left/data]
;   :initial-state
;          (fn [{:keys [state factory ui]}]
;            {:bottom-left/ui ui
;             :bottom-left/data
;                             (comp/get-initial-state factory state)
;             })}
;  (dom/div {:className "bottom"}
;           (ui data)))
;(def ui-bottom (comp/factory Bottom))

;
;(defn string-keys [name]
;  (keyword (str name "/keys")))

;(defsc "Generic" [this {(string-keys name) [components]}]
;  (GenericHtmlSetup components classes))
;
;(defsc AboutLeftSide [this {:left-side/keys [
;                                             gallery
;                                             ] :as props}]
;  {:query [:left-side/gallery]
;   :initial-state
;          (fn [gallery]
;            {:left-side/gallery
;             (comp/get-initial-state Gallery gallery)}
;            )}
;  (dom/div {:className "about-left"}
;           (ui-gallery gallery)
;           (dom/div
;             (ui-image
;               (comp/get-initial-state
;                 Image
;                 {:id  "left-side-arrow"
;                  :alt "point to the right from left"
;                  :src "../images/left-side-arrow.PNG"}))
;             )))
;(def ui-about-left (comp/factory AboutLeftSide))
;
;(defsc LeftSide [this {:left-side/keys [
;                                        ui
;                                        data
;                                        factory
;                                        ] :as props}]
;  {:query [:left-side/data
;           :left-side/ui
;           :left-side/factory]
;   :initial-state
;          (fn [{:keys [ui factory data]}]
;            {:left-side/data data
;             :left-side/ui ui
;             :left-side/factory factory
;             }
;            )}
;  (dom/div {:className "left-side"}
;           (mapv
;             (fn [item]
;               (ui (comp/get-initial-state factory item))) data)
;           ))
;(def ui-left-side (comp/factory LeftSide))



;(comp/get-initial-state
;  LeftSide
;  {:content
;   [{:ui ui-top
;     :state
;      (comp/get-initial-state
;        Top
;        {:ui ui-href
;         :state
;          (comp/get-initial-state
;            Href
;            {:link "https://en.wikipedia.org/wiki/Gaming"
;             :id   "gamin"
;             :src  "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
;             :alt  "I play games I KNOW I'M SORRY"}
;                                  )
;         }
;        {:ui ui-href
;         :factory Href
;         :state
;         {:link "https://en.wikipedia.org/wiki/Gaming"
;          :id   "gamin"
;          :src  "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
;          :alt  "I play games I KNOW I'M SORRY"}}
;                              )}
;    {:ui ui-top
;     :factory Top
;     :state
;     {:ui ui-href
;      :factory Href
;      :state
;      {:link "https://en.wikipedia.org/wiki/Gaming"
;       :id   "gamin"
;       :src  "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
;       :alt  "I play games I KNOW I'M SORRY"}}}
;    {:ui ui-bottom
;     :factory Bottom
;     :state
;     {:ui ui-href
;      :factory Href
;      :state
;      {:link "https://www.whatisitliketobeaphilosopher.com/"
;       :id   "pho"
;       :src  "../images/the-thinker.png"
;       :alt  "But really, what even IS a rock anyways???"}}}]})

;
;(defn apply-ui [ui factory data]
;  (ui (comp/get-initial-state factory data)))
;(defn apply-uis [ui factory states]
;  (mapv (fn [state] (apply-ui ui factory state)) states))
;
;(defn ConvertCompState [{:keys [ui factory state]}]
;  (ui (comp/get-initial-state factory data)))
;
;(defn GenericComponent [name classes]
;  (defsc name [this {:name/keys [states ui factory]}]
;    {:query [:name/states
;             :name/factory
;             :name/ui]
;     :initial-state
;      (fn [{:keys [ui state factory]}]
;        {:name/content content})}
;    (dom/div {:className classes}
;      (mapv apply-uis states)
;             )))

;(defsc LeftSide [this {:left-side/keys [
;                                        top
;                                        bottom
;                                        ] :as props}]
;  {:query [{:left-side/top (comp/get-query Top)}
;           {:left-side/bottom (comp/get-query Bottom)}]
;   :initial-state
;          (fn [{:keys [top bottom]}]
;            {:left-side/top
;             (comp/get-initial-state
;               Top
;               top)
;             :left-side/bottom
;             (comp/get-initial-state
;               Bottom
;               bottom)
;             })}
;  (dom/div {:className "left-side"}
;           (ui-top-left top)
;           (ui-bottom-left bottom)
;           ))
;(def ui-left-side (comp/factory LeftSide))

;(let [{:keys [right-side]} (css/get-classnames RightSide)]
;  (dom/div {:classes [right-side]}
;           (ui-top-left top)
;           (ui-bottom-left bottom))))

;{:classes [middle-main-page padding-bottom]}
;:.general-container
;       {
;:.general-container
;:.middle-main-page
;:.padding-bottom
;        :className (doall [middle-main-page padding-bottom])
;}

;(defsc Gallery
;  [this {:gallery/keys [photos] :as props}]
;  {:query [:gallery/photos]
;   :initial-state
;          (fn [gallery]
;            {:gallery/photos gallery})}
;  (dom/div {:className "gallery"}
;           (mapv
;             (fn [photo]
;               (ui-image
;                 (comp/get-initial-state
;                   Image
;                   photo)
;                 ))
;             photos)))
;(def ui-gallery (comp/factory Gallery))
;
;(defsc AboutLeftSide [this {:left-side/keys   [
;                                               gallery
;                                               ] :as props}]
;  {:query [:left-side/gallery]
;   :initial-state
;          (fn [gallery]
;            {:left-side/gallery
;             (comp/get-initial-state Gallery gallery)}
;            )}
;  (dom/div {:className "about-left"}
;           (ui-gallery gallery)
;           (dom/div
;             (ui-image
;               (comp/get-initial-state
;                 Image
;                 {:id "left-side-arrow"
;                  :alt "point to the right from left"
;                  :src "../images/left-side-arrow.PNG"}))
;             )))
;(def ui-left-side (comp/factory AboutLeftSide))
;
;(def node-options
;  {:first
;   {:id "end-node"
;    :alt "The future is yet to come"
;    :src "../images/end-node.PNG"}
;   :middle
;   {:id "middle-node"
;    :alt "arbitrary point in timeline"
;    :src "../images/middle-node.PNG"}
;   :end
;   {:id "end-of-the-road"
;    :alt "end of the node, cowboy"
;    :src "../images/end-of-the-road.PNG"}})
;(defsc AboutMiddle [this {:middle/keys [id] :as props}]
;  {:query [:middle/id]
;   :ident :middle/id
;   :initial-state
;          (fn [{:keys [id]}]
;            {:middle/id id})
;   :css [[:.middle-main-page
;          {:display "flex"
;           :flex-direction "column"
;           :font-size "4vw"
;           :margin "0 auto"
;           :justify-content "center"
;           :min-width "6em"
;           :height "auto"}]
;         [:.padding-bottom
;          {:padding-bottom "1em"}]
;         [:.enlarge-text
;          {:font-size "larger"
;           :overflow "hidden"}]
;         [:.small-text
;          {:font-size "initial"
;           :margin "0 auto"
;           :text-align "center"}]]}
;  (let [{:keys [middle-main-page padding-bottom]} (fcss/get-classnames AboutMiddle)]
;    (dom/div
;      (ui-image
;        (comp/get-initial-state Image
;                                (get node-options id))))))
;(def ui-middle (comp/factory AboutMiddle {:keyfn :middle/id}))
;
;(defsc AboutRightSide [this {:right-side/keys [gallery] :as props}]
;  {:query [:right-side/gallery
;           ]
;   :initial-state
;          (fn [gallery]
;            {:right-side/gallery
;             (comp/get-initial-state Gallery gallery)}
;            )
;   :css
;          [[:.right-side
;            {:display "flex";
;             :flex-direction "column"
;             :align-items "center"
;             :padding-right "1.5em"
;             :width "100%"}]
;           [:.right-side>a+a
;            {:padding-top "6em"}]]
;   }
;  (dom/div {:className "about-right"}
;           (dom/div
;             (ui-image
;               (comp/get-initial-state
;                 Image
;                 {:id "right-side-arrow"
;                  :alt "point to the left from right"
;                  :src "../images/right-side-arrow.PNG"})
;               ))
;
;           (ui-gallery gallery)
;           )
;  )
;(def ui-right-side (comp/factory AboutRightSide))
;
;(defsc Timebox [this {:timebox/keys [id
;                                     left
;                                     middle
;                                     right
;                                     ] :as props}]
;  {:query [:timebox/id
;           {:timebox/left (comp/get-query AboutLeftSide)}
;           {:timebox/middle (comp/get-query AboutMiddle)}
;           {:timebox/right (comp/get-query AboutRightSide)}
;           ]
;   :ident :timebox/id
;   :initial-state
;          (fn [{:keys [id left middle right]}]
;            {:timebox/id id
;             :timebox/left
;                         (comp/get-initial-state
;                           AboutLeftSide
;                           left)
;             :timebox/middle
;                         (comp/get-initial-state
;                           AboutMiddle middle)
;             :timebox/right
;                         (comp/get-initial-state
;                           AboutRightSide
;                           right
;                           )})
;   :css (:css uicss/Timebox)}
;  (let [{:keys [timebox]} (fcss/get-classnames Timebox)]
;    (div {:classes [timebox]
;          :id id}
;         (ui-left-side left)
;         (ui-middle middle)
;         (ui-right-side right)
;         )))
;(def ui-timebox (comp/factory Timebox {:keyfn :timebox/id}))
;
;(defsc About [this {:about/keys [timebox] :as props}]
;  {:ident (fn [] [:component/id :about])
;   :route-segment ["about"]
;   :query [{:about/timebox (comp/get-query Timebox)}]
;   :initial-state
;   (fn [_]
;     {:about/timebox
;      [(comp/get-initial-state
;         Timebox
;         {:id "first-box"
;          :left
;              [{:id "paycom"
;                :src "../images/paycom.PNG"
;                :alt "I work here rn"}
;               {:id "okcity"
;                :src "../images/okcity.PNG"
;                :alt "I live here rn"}]
;          :middle {:id :first}
;          :right
;          [{:id "twbb"
;            :src "../images/twbb.jpg"
;            :alt "There Will Be Blood"}]
;          }
;         )
;       (comp/get-initial-state
;         Timebox
;         {:id "second"
;          :middle {:id :middle}
;          }
;         )
;       (comp/get-initial-state
;         Timebox
;         {:id "third"
;          :middle {:id :end}
;          }
;         )
;       ]
;
;      }
;     )}
;  (div {:id "project-page-body"}
;       (inj/style-element {:component Timebox})
;       (mapv ui-timebox timebox)
;       ))

;[
; (comp/get-initial-state
;   Timebox
;   {:id "first"
;    :middle
;
;     {:content
;      (comp/get-initial-state
;        Image
;        {:id "end-node"
;         :alt "The future is yet to come"
;         :src "../images/end-node.PNG"})
;      }
;    })
; (comp/get-initial-state
;   Timebox
;   {:id "first"
;    :middle
;
;      {:content
;       (comp/get-initial-state
;         Image
;         {:id "middle-node"
;          :alt "arbitrary point in timeline"
;          :src "../images/middle-node.PNG"})
;       }
;    })
; (comp/get-initial-state
;   Timebox
;   {:id "first"
;    :middle
;
;      {:content
;       (comp/get-initial-state
;         Image
;         {:id "end-of-the-road"
;          :alt "end of the road, cowboy"
;          :src "../images/end-of-the-road.PNG"})
;       }
;    })
; ]

;(def image-dir "../images/")
;
;(def classes "brief-border href-item-container")
;
;(def project-page-header
;  [:p {:id "project-header"} "Projects"])
;
;(defn box-container [contents]
;  [:div {:class "flex box align-horizontal"} contents])
;
;(defn image-container [src alt]
;  [:img {:src src :alt alt}])
;
;(defn image-href-container [id class href src alt]
;  [:div {:id id :class class}
;   [:a {:href href :target "__blank" :rel "noopener noreferrer"}
;    (image-container src alt)]])
;
;(defn project-description [title description]
;  [:div {:class "description-container"}
;   [:div {:class "text-align-left"} title]
;   [:div {:class "description text-align-left"} description]])
;
;(def project-page-body
;  [:div {:id "project-body"}
;   (box-container
;     [:div (image-href-container
;             "my-website"
;             classes
;             "https://github.com/AlbertSnows/my-website"
;             (str image-dir "this_website.PNG")
;             "This Website")
;      (project-description
;        "This Website"
;        "The website you're perusing")])
;   (box-container
;     [:div (image-href-container
;             "first-website"
;             classes
;             "https://github.com/AlbertSnows/FWRcljs"
;             (str image-dir "kistners_flowers.PNG")
;             "My First Website")
;      (project-description
;        "My First website"
;        "First website for class.
;         Written from Javascript
;         -> Typescript
;         -> Clojurescript over the semester")])
;   (box-container
;     [:div (image-href-container
;             "snake-game"
;             classes
;             "https://github.com/AlbertSnows/snake_game"
;             (str image-dir "snake_rust.PNG")
;             "Snake Game")
;      (project-description "Snake Game" "Wrote Snake in Rust")])
;   (box-container
;     [:div (image-href-container
;             "game-jam-2018"
;             classes
;             "https://github.com/AlbertSnows/To-Change-A-Lightbulb"
;             (str image-dir "lightbulb.PNG")
;             "To Change A Light Bulb")
;      (project-description
;        "To Change A Light Bulb"
;        "A beautiful disaster written over a weekend during a game jam")])
;   (box-container
;     [:div (image-href-container
;             "thermal-modeling"
;             classes
;             "https://github.com/AlbertSnows/HumanThermalModeling"
;             (str image-dir "thermal_modeling.PNG")
;             "Human Thermal Modeling")
;      (project-description
;        "Human Thermal Modeling"
;        "Multi-threading graduate student's research code")])
;   (box-container
;     [:div (image-href-container
;             "roguelike"
;             classes
;             "https://github.com/AlbertSnows/2DRogueLike"
;             (str image-dir "2Drouge.PNG")
;             "2D Rogue Like")
;      (project-description "2D Rogue Like" "First game made in Unity")])
;   (box-container
;     [:div (image-href-container
;             "edgesweeper"
;             classes
;             "https://github.com/AlbertSnows/python-tkinter-minesweeper"
;             (str image-dir "minesweeper.PNG")
;             "Edgesweeper")
;      (project-description
;        "Edgesweeper"
;        "Took Minesweeper, written in python, and added a feature")])
;   (box-container
;     [:div (image-href-container
;             "first-unity"
;             classes
;             "https://github.com/AlbertSnows/RollABall"
;             (str image-dir "rollaball.PNG")
;             "Roll A Ball")
;      (project-description "Roll A Ball" "First tutorial exposure to Unity")])
;   (box-container
;     [:div (image-href-container
;             "mobile-app-game"
;             classes
;             "https://github.com/AlbertSnows/SimpleMobileGame"
;             (str image-dir "first_app.PNG")
;             "Simple App")
;      (project-description
;        "Simple App"
;        "Playing with Java/Kotlin in Android Studio")])])

;(defsc Projects [this {:projects/keys [id] :as props}]
;  {:query [:projects/id]
;   :route-segment ["projects"]
;   :ident (fn [] [:projects/id :projects])
;   :initial-state {}}
;  (dom/div "Hello!"))
;(def ui-projects (comp/factory Projects))
;
;(defsc About [this {:about/keys [id] :as props}]
;  {:query [:about/id]
;   :route-segment ["about"]
;   :ident (fn [] [:component/id :about])
;   :initial-state {}}
;  (dom/div "Hello!"))
;(def ui-about (comp/factory About))

;(defsc Image [this {:second/keys [id src alt]}]
;  {:query [:second/id :second/src :second/alt]
;   :initial-state
;          (fn [{:keys [src id alt]}]
;            {:second/src src
;             :second/id id
;             :second/alt alt})}
;  (img
;    {:src src
;     :id id
;     :alt alt}
;    ))
;(def ui-image (comp/factory Image))
;
;(defsc Href [this {:test/keys [id link thing]}]
;  {:query [:test/id
;           :test/link
;           {:test/thing (comp/get-query Image)}]
;   :ident :test/id
;   :initial-state
;          (fn [{:keys [id link src alt]}]
;            {:test/id id
;             :test/thing
;                      (comp/get-initial-state
;                        Image
;                        {:src src
;                         :id (str id "-img")
;                         :alt alt})})}
;  (a {:href link
;      :target "__blank"
;      :rel "noopener noreferrer"}
;     (ui-image thing)))
;(def ui-href (comp/factory Href))

;(defsc Contact [this {:contact/keys [
;                                     ;image
;                                     ;span-image
;                                     ] :as props}]
;  {:query [
;           ;{:contact/image (comp/get-query Image)}
;           ;{:contact/span-image (comp/get-query Image)}
;           ]
;   :route-segment ["contact"]
;   :ident (fn [] [:component/id :contact])
;   :initial-state
;   (fn [_] {})
;   :css uicss/Contact}
;  ;(let [{:keys [contact]} (fcss/get-classnames Contact)]
;  ;  (dom/div
;  ;    {:classes [contact
;  ;               ;           contact>big-mail-boi
;  ;               ;           contact>small-mail-boi
;  ;               ]
;  ;     }
;      ;(inj/style-element {:component Contact})
;      ;(ui-image image)
;      ;(ui-image span-image)
;
;      ;(:span {:className "popup"})
;
;      ;(dom/div
;      ;(dom/div {:className "mail"} )
;      ;)
;      (dom/div {:className "very-small-text"}
;               "(email for social media)")
;  ;)
;    ;)
;  )


;(defsc Image [this {:image/keys [src alt classes]} {:keys [image]}]
;  {:query         [:image/src
;                   :image/alt
;                   :image/classes]
;   :ident (fn [] [:component/id :image])
;   :initial-state
;    (fn [{:image/keys [src alt classes]}]
;      {:image/src src
;       :image/alt alt
;       :image/classes classes})}
;       (dom/img {:src src
;                 :alt alt
;                 :className classes}))
;(def ui-image (comp/factory Image))
;
;(defsc Href
;       [this {:href/keys [id link image]}]
;       {:query [:href/id
;                :href/link
;                {:href/image (comp/get-query Image)}]
;        :initial-state
;               (fn [{:keys [link id src alt] :as props}]
;                 (log/info "Props: " props)
;                 {:href/id   id
;                  :href/link link
;                  :href/image
;                             (comp/get-initial-state
;                               Image
;                               {:id  (str id "-img")
;                                :src src
;                                :alt alt})})}
;       (dom/a
;    {:href link
;     :target "__blank"
;     :rel "noopener noreferrer"}
;    (ui-image image)))
;(def ui-href (comp/factory Href))

;(defsc Image [this {:second/keys [id src alt]}]
;  {:query [:second/id :second/src :second/alt]
;   :initial-state
;          (fn [{:keys [src id alt]}]
;            {:second/src src
;             :second/id id
;             :second/alt alt})}
;  (img
;    {:src src
;     :id id
;     :alt alt}
;    ))
;(def ui-second (comp/factory Image))
;
;(defsc Href [this {:test/keys [id link thing]}]
;  {:query [:test/id
;           :test/link
;           {:test/thing (comp/get-query Image)}]
;   :ident :test/id
;   :initial-state
;          (fn [{:keys [id link src alt]}]
;            {:test/id id
;             :test/thing
;                      (comp/get-initial-state
;                        Image
;                        {:src src
;                         :id (str id "-img")
;                         :alt alt})})}
;  (a {:href link
;      :target "__blank"
;      :rel "noopener noreferrer"}
;     (ui-second thing)))
;(def ui-href (comp/factory Href))
;
;(defsc TopLeft [this {:top-left/keys [contents] :as props}]
;  {:query [{:top-left/contents (comp/get-query Href)}]
;   :initial-state
;          (fn [{:keys [link id src alt]}]
;            {:top-left/contents
;             (comp/get-initial-state Href
;                                     {:link link :id id :src src :alt alt})})}
;  (ui-href contents))
;(def ui-top-left (comp/factory TopLeft))
;
;(defsc BottomLeft [this {:bottom-left/keys [contents] :as props}]
;  {:query [{:bottom-left/contents (comp/get-query Href)}]
;   :initial-state
;          (fn [{:keys [link id src alt]}]
;            {:bottom-left/contents
;             (comp/get-initial-state Href
;                                     {:link link :id id :src src :alt alt})})}
;  (ui-href contents))
;(def ui-bottom-left (comp/factory BottomLeft))
;
;(defsc LeftSide [this {:left-side/keys [top bottom] :as props}]
;  {:query [{:left-side/top (comp/get-query TopLeft)}
;           {:left-side/bottom (comp/get-query BottomLeft)}]
;   :initial-state
;          (fn [{:keys [top bottom] :as params}]
;            {:left-side/top
;             (comp/get-initial-state TopLeft
;                                     {:link (:link top)
;                                      :id (:id top)
;                                      :src (:src top)
;                                      :alt (:alt top)})
;             :left-side/bottom
;             (comp/get-initial-state
;               BottomLeft
;               {:link (:link bottom)
;                :id (:id bottom)
;                :src (:src bottom)
;                :alt (:alt bottom)})})
;   :css [[:.left-side
;          {:display "flex"
;           :flex-direction "column"
;           :align-items "center"
;           :padding-left "1.5em"
;           :width "100%"}]
;         [:.left-side>div
;          {:padding-top "1em"
;           :padding-bottom "1em"}]
;         [:.left-side>a+a
;          {:padding-top "6em"}]]}
;  (let [{:keys [left-side]} (css/get-classnames LeftSide)]
;    (dom/div {:classes [left-side]}
;             (ui-top-left top)
;             (ui-bottom-left bottom))))
;(def ui-left-side (comp/factory LeftSide))
;
;(defsc TopRight [this {:top-right/keys [contents] :as props}]
;  {:query [{:top-right/contents (comp/get-query Href)}]
;   :initial-state
;          (fn [{:keys [link id src alt]}]
;            {:top-right/contents
;             (comp/get-initial-state Href
;                                     {:link link :id id :src src :alt alt})})
;   :css [:.right-top []]}
;  (dom/div
;    (ui-href contents)))
;(def ui-top-right (comp/factory TopRight))
;
;(defsc BottomRight [this {:bottom-right/keys [contents] :as props}]
;  {:query [{:bottom-right/contents (comp/get-query Href)}]
;   :initial-state
;          (fn [{:keys [link id src alt]}]
;            {:bottom-right/contents
;             (comp/get-initial-state Href
;                                     {:link link :id id :src src :alt alt})})}
;  (dom/div (ui-href contents)))
;(def ui-bottom-right (comp/factory BottomRight))
;
;(defsc RightSide [this {:right-side/keys [top bottom] :as props}
;                  {:keys [right-side]}]
;  {:query [{:right-side/top (comp/get-query TopLeft)}
;           {:right-side/bottom (comp/get-query BottomLeft)}]
;   :initial-state
;          (fn [{:keys [top bottom] :as params}]
;            {:right-side/top
;             (comp/get-initial-state TopLeft
;                                     {:link (:link top)
;                                      :id (:id top)
;                                      :src (:src top)
;                                      :alt (:alt top)})
;             :right-side/bottom
;             (comp/get-initial-state BottomLeft
;                                     {:link (:link bottom)
;                                      :id (:id bottom)
;                                      :src (:src bottom)
;                                      :alt (:alt bottom)})})
;   :css [[:.right-side
;          {:display "flex";
;           :flex-direction "column"
;           :align-items "center"
;           :padding-right "1.5em"
;           :width "100%"}]
;         [:.right-side>a+a
;          {:padding-top "6em"}]]}
;  (let [{:keys [right-side]} (css/get-classnames RightSide)]
;    (dom/div {:classes [right-side]}
;             (ui-top-left top)
;             (ui-bottom-left bottom))))
;(def ui-right-side (comp/factory RightSide))
;
;(defsc Middle [this {:middle/keys [id content] :as props}]
;  {:query [:middle/id
;           {:middle/content (comp/get-query Href)}]
;   :initial-state
;          (fn [{:keys [content]}]
;            {:middle/id 1
;             :middle/content content})
;   :css [[:.middle-main-page
;          {:display "flex"
;           :flex-direction "column"
;           :font-size "4vw"
;           :margin "0 auto"
;           :justify-content "center"
;           :min-width "6em"
;           :height "auto"}]
;         [:.padding-bottom
;          {:padding-bottom "1em"}]
;         [:.enlarge-text
;          {:font-size "larger"
;           :overflow "hidden"}]
;         [:.small-text
;          {:font-size "initial"
;           :margin "0 auto"
;           :text-align "center"}]]}
;  (let [{:keys [middle-main-page padding-bottom]} (css/get-classnames Middle)]
;    (dom/div
;      {:classes [middle-main-page padding-bottom]}
;      ;:.general-container
;      ;       {
;      ;:.general-container
;      ;:.middle-main-page
;      ;:.padding-bottom
;      ;        :className (doall [middle-main-page padding-bottom])
;      ;}
;      (doall content)
;      )))
;(def ui-middle (comp/factory Middle {:keyfn :middle/id}))



;[app.ui.mutations :as uim]
;[cljs.core.match :refer-macros [match]]
;[com.fulcrologic.fulcro.routing.dynamic-routing :as dr]
;[com.fulcrologic.fulcro.ui-state-machines :as uism
; :refer [defstatemachine]]
;[com.fulcrologic.fulcro.algorithms.normalized-state :refer [swap!->]]
;[com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]
;[com.fulcrologic.fulcro.data-fetch :as df]
;[com.fulcrologic.fulcro.routing.dynamic-routing :as dr :refer [defrouter]]
;[taoensso.timbre :as log] ;e.g. (log/info "Props: " props)

;
;(defsc Image [this {:image/keys [src alt classes]} {:keys [image]}]
  ;  {:query         [:image/src
  ;                   :image/alt
  ;                   :image/classes]
  ;   :ident (fn [] [:component/id :image])
  ;   :initial-state
  ;                  (fn [{:keys [src alt]}]
  ;                    {:image/src src
  ;                     :image/alt alt})}
  ;  (dom/img {:src src
  ;            :alt alt
  ;            :className classes}))
  ;(def ui-image (comp/factory Image))
  ;
  ;(defsc Href
  ;  [this {:href/keys [id link image]}]
  ;  {:query [:href/id
  ;           :href/link
  ;           {:href/image (comp/get-query Image)}]
  ;   :initial-state
  ;          (fn [{:keys [link id src alt] :as props}]
  ;            (log/info "Props: " props)
  ;            {:href/id   id
  ;             :href/link link
  ;             :href/image
  ;                        (comp/get-initial-state
  ;                          Image
  ;                          {:id  (str id "-img")
  ;                           :src src
  ;                           :alt alt})})}
  ;  (dom/a
  ;    {:href link
  ;     :target "__blank"
  ;     :rel "noopener noreferrer"}
  ;    (ui-image image)))
  ;(def ui-href (comp/factory Href))

  ;(defsc Image [this {:image/keys [src alt classes]} {:keys [image]}]
  ;  {:query         [:image/src
  ;                   :image/alt
  ;                   :image/classes]
  ;   :ident (fn [] [:component/id :image])
  ;   :initial-state
  ;    (fn [{:image/keys [src alt classes]}]
  ;      {:image/src src
  ;       :image/alt alt
  ;       :image/classes classes})}
  ;       (dom/img {:src src
  ;                 :alt alt
  ;                 :className classes}))
  ;(def ui-image (comp/factory Image))
  ;
  ;(defsc Href [this {:href/keys [link image]}]
  ;  {:query [:href/link
  ;           {:href/image (comp/get-query Image)}]
  ;   :ident (fn [] [:component/id :href])
  ;   :initial-state
  ;    (fn [{:href/keys [link image]}]
  ;      {:href/link link
  ;       :href/image (comp/get-initial-state Image image)})}
  ;    (a
  ;      {:href link
  ;       :target "__blank"
  ;       :rel "noopener noreferrer"}
  ;      (ui-image image)))
  ;(def ui-href (comp/factory Href))
  ;
  ;(defsc Home [this {:home/keys [first
  ;                               second]}]
  ;  {:query [{:home/first (comp/get-query Href)}
  ;           {:home/second (comp/get-query Href)}]
  ;   :route-segment ["home"]
  ;   :ident (fn [] [:component/id :home])
  ;   :initial-state
  ;     (fn [_]
  ;       {:home/first
  ;          (comp/get-initial-state
  ;            Href
  ;            {:href/link "https://en.wikipedia.org/wiki/Gaming"
  ;             :href/image {:image/src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;                          :image/alt "I play games I KNOW I'M SORRY"}})
  ;        :home/second
  ;          (comp/get-initial-state
  ;            Href
  ;            {:href/link "https://www.whatisitliketobeaphilosopher.com"
  ;             :href/image {:image/src "../images/the-thinker.png"
  ;                          :image/alt "But really, what even IS a rock anyways???"}})
  ;        ;       {:top
  ;        ;        {:link "https://en.wikipedia.org/wiki/Gaming"
  ;        ;         :id "gamin"
  ;        ;         :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;        ;         :alt "I play games I KNOW I'M SORRY"}
  ;        ;        :bottom
  ;        ;        {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;        ;         :id "pho"
  ;        ;         :src "../images/the-thinker.png"
  ;        ;         :alt "But really, what even IS a rock anyways???"}}
  ;        })}
  ;  (div
  ;    (str "First: " first)
  ;    (str "Second: " second)))

  ;;; works i guess maybe idk we'll see?
  ;(defsc Test [this {:test/keys [first second]}]
  ;  {:query [:test/id
  ;           :test/first
  ;           :test/second]
  ;   :ident :test/id
  ;   :initial-state
  ;          (fn [{:keys [id first second]}]
  ;            {:test/id id
  ;             :test/first first
  ;             :test/second second})}
  ;  (div
  ;    (str "First: " first)
  ;    (str "Second: " second)))
  ;(def ui-test (comp/factory Test))
  ;
  ;(defsc Home [this {:home/keys [first
  ;                               second]}]
  ;  {:query [{:home/first (comp/get-query Test)}
  ;           {:home/second (comp/get-query Test)}]
  ;   :route-segment ["home"]
  ;   :ident (fn [] [:component/id :home])
  ;   :initial-state
  ;   (fn [_]
  ;     {:home/first
  ;      (comp/get-initial-state
  ;        Test
  ;        {:id 1
  ;         :first "home first first"
  ;         :second "home first second"})
  ;      :home/second
  ;      (comp/get-initial-state
  ;        Test
  ;        {:id 2
  ;         :first "home second first"
  ;         :second "home second second"})})}
  ;  (div
  ;    (div (str "First: " first))
  ;    (div (str "Second: " second))))
  ;
  ;(defrouter RootRouter
  ;           [this {:keys [current-state pending-path-segment]}]
  ;           {:router-targets [Home]})
  ;(def ui-root-router (comp/factory RootRouter))
  ;;;;




  ;(def home-initial-state
  ;  {:home/left-side
  ;    (comp/get-initial-state
  ;      LeftSide
  ;      {:top
  ;       {:link "https://en.wikipedia.org/wiki/Gaming"
  ;        :id "gamin"
  ;        :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;        :alt "I play games I KNOW I'M SORRY"}
  ;       :bottom
  ;       {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;        :id "pho"
  ;        :src "../images/the-thinker.png"
  ;        :alt "But really, what even IS a rock anyways???"}})
  ;   :home/middle
  ;    (comp/get-initial-state
  ;      Middle
  ;      {:content
  ;       [(dom/p {:key 1
  ;                :className "enlarge-text"}
  ;               ;{:class "enlarge-text"}
  ;               "Mostly this stuff")
  ;        (dom/p
  ;          {:key 2
  ;           :className "small-text"}
  ;          "(check out my projects for novel things)")]
  ;       })
  ;   :home/right-side
  ;    (comp/get-initial-state
  ;      RightSide
  ;      {:top
  ;       {:link "https://www.youtube.com/"
  ;        :id "Tube"
  ;        :src "../images/tubes.png"
  ;        :alt "Youtube is my Netflix, sadly"}
  ;       :bottom
  ;       {:link "https://en.wikipedia.org/wiki/Programmer"
  ;        :id "debug"
  ;        :src "../images/meirl.png"
  ;        :alt "g! 'How to print newline in cljs'"}})
  ;   })

  ;(def home-initial-state
  ;  {:home/left
  ;     (comp/get-initial-state
  ;       LeftSide
  ;       {:top
  ;        {:link "https://en.wikipedia.org/wiki/Gaming"
  ;         :id "gamin"
  ;         :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;         :alt "I play games I KNOW I'M SORRY"}
  ;        :bottom
  ;        {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;         :id "pho"
  ;         :src "../images/the-thinker.png"
  ;         :alt "But really, what even IS a rock anyways???"}})
  ;   :home/middle
  ;     (comp/get-initial-state
  ;       Middle
  ;       {:content
  ;        [(dom/p {:key 1
  ;                 :className "enlarge-text"}
  ;                ;{:class "enlarge-text"}
  ;                "Mostly this stuff")
  ;         (dom/p
  ;           {:key 2
  ;            :className "small-text"}
  ;           "(check out my projects for novel things)")]
  ;        })
  ;   :home/right
  ;     (comp/get-initial-state
  ;       RightSide
  ;       {:top
  ;        {:link "https://www.youtube.com/"
  ;         :id "Tube"
  ;         :src "../images/tubes.png"
  ;         :alt "Youtube is my Netflix, sadly"}
  ;        :bottom
  ;        {:link "https://en.wikipedia.org/wiki/Programmer"
  ;         :id "debug"
  ;         :src "../images/meirl.png"
  ;         :alt "g! 'How to print newline in cljs'"}})
  ;   })

  ;(defsc Home [this {:test/keys [
  ;                               left
  ;                               ;middle
  ;                               ;right
  ;                               ] :as props}]
  ;  {:query [
  ;           {:test/left (comp/get-query LeftSide)}
  ;           ;{:test/middle (comp/get-query Middle)}
  ;           ;{:test/right (comp/get-query RightSide)}
  ;           ]
  ;   :ident (fn [] [:component/id :home])
  ;   :route-segment ["home"]
  ;   :initial-state
  ;     (fn [_]
  ;       {:test/left
  ;          (comp/get-initial-state
  ;            LeftSide
  ;            {:top
  ;             {:link "https://en.wikipedia.org/wiki/Gaming"
  ;              :id "gamin"
  ;              :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;              :alt "I play games I KNOW I'M SORRY"}
  ;             :bottom
  ;             {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;              :id "pho"
  ;              :src "../images/the-thinker.png"
  ;              :alt "But really, what even IS a rock anyways???"}})
  ;        ;:home/middle
  ;        ;  (comp/get-initial-state
  ;        ;    Middle
  ;        ;    {:content
  ;        ;     [(dom/p {:key 1
  ;        ;              :className "enlarge-text"}
  ;        ;             ;{:class "enlarge-text"}
  ;        ;             "Mostly this stuff")
  ;        ;      (dom/p
  ;        ;        {:key 2
  ;        ;         :className "small-text"}
  ;        ;        "(check out my projects for novel things)")]
  ;        ;     })
  ;        ;:home/right
  ;        ;  (comp/get-initial-state
  ;        ;    RightSide
  ;        ;    {:top
  ;        ;     {:link "https://www.youtube.com/"
  ;        ;      :id "Tube"
  ;        ;      :src "../images/tubes.png"
  ;        ;      :alt "Youtube is my Netflix, sadly"}
  ;        ;     :bottom
  ;        ;     {:link "https://en.wikipedia.org/wiki/Programmer"
  ;        ;      :id "debug"
  ;        ;      :src "../images/meirl.png"
  ;        ;      :alt "g! 'How to print newline in cljs'"}})
  ;          }
  ;         )
  ;   }
  ;  (dom/div
  ;    (str "Left: " left)
  ;    (str "-----------------------------------")
  ;    (str "bottom: "
  ;         (comp/get-initial-state
  ;           LeftSide
  ;           {:top
  ;            {:link "https://en.wikipedia.org/wiki/Gaming"
  ;             :id "gamin"
  ;             :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;             :alt "I play games I KNOW I'M SORRY"}
  ;            :bottom
  ;            {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;             :id "pho"
  ;             :src "../images/the-thinker.png"
  ;             :alt "But really, what even IS a rock anyways???"}})
  ;         )
  ;    )
  ;  ;(ui-left-side left)
  ;  )
  ;
  ;(defsc Home [this {:home/keys [left] :as props}]
  ;  {:query [:home/left]
  ;   :route-segment ["home"]
  ;   :ident (fn [] [:component/id :home])
  ;   :initial-state
  ;   (fn [_]
  ;     {:home/left
  ;      (comp/get-initial-state
  ;        LeftSide
  ;
  ;        {:top
  ;         {:link "https://en.wikipedia.org/wiki/Gaming"
  ;          :id "gamin"
  ;          :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;          :alt "I play games I KNOW I'M SORRY"}
  ;         :bottom
  ;         {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;          :id "pho"
  ;          :src "../images/the-thinker.png"
  ;          :alt "But really, what even IS a rock anyways???"}}
  ;
  ;        )}
  ;     )}
  ;  ;(let [{:keys [general-container]} (css/get-classnames Home)]
  ;  (dom/div
  ;    ;(str left)
  ;    ;{:classes [general-container]}
  ;    (ui-left-side
  ;    left
  ;    )
  ;    )
  ;  ;)
  ;  )

  ;(defsc Home [this {:home/keys [imageA imageB]}]
  ;  {:query [{:home/imageA (comp/get-query Href)}
  ;           {:home/imageB (comp/get-query Href)}]
  ;   :route-segment ["home"]
  ;   :ident (fn [] [:component/id :home])
  ;   :initial-state
  ;   (fn [_]
  ;     {:home/imageA
  ;        (comp/get-initial-state
  ;          Href
  ;          {:link "https://en.wikipedia.org/wiki/Gaming"
  ;           :id "gamin"
  ;           :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;           :alt "I play games I KNOW I'M SORRY"})
  ;      :home/imageB
  ;      (comp/get-initial-state
  ;        Href
  ;       {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;        :id "pho"
  ;        :src "../images/the-thinker.png"
  ;        :alt "But really, what even IS a rock anyways???"}
  ;        )
  ;      }
  ;     ;{:home/left
  ;     ; (comp/get-initial-state
  ;     ;   LeftSide
  ;     ;   {:top
  ;     ;     {:link "https://en.wikipedia.org/wiki/Gaming"
  ;     ;      :id "gamin"
  ;     ;      :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;     ;      :alt "I play games I KNOW I'M SORRY"}
  ;     ;     :bottom
  ;     ;     {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;     ;      :id "pho"
  ;     ;      :src "../images/the-thinker.png"
  ;     ;      :alt "But really, what even IS a rock anyways???"}}
  ;     ;   )}
  ;     )}
  ;  (dom/div
  ;    (ui-href imageA)
  ;    (ui-href imageB)))
  ;
  ;(defsc Home [this {:home/keys [
  ;                               imagea
  ;                               imageb
  ;                               ]}]
  ;  {:query [{:home/imagea (comp/get-query Image)}
  ;           {:home/imageb (comp/get-query Image)}]
  ;   :route-segment ["home"]
  ;   :ident (fn [] [:component/id :home])
  ;   :initial-state
  ;   (fn [_]
  ;     {:home/imagea
  ;      (comp/get-initial-state
  ;        Image {:id 1
  ;               :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;               :alt "home"
  ;               :classes "none"})
  ;      :home/imageb
  ;        (comp/get-initial-state
  ;          Image
  ;         {:id 2
  ;          :src "../images/the-thinker.png"
  ;          :alt "But really, what even IS a rock anyways???"
  ;          :classes "none"}
  ;          )
  ;      })}
  ;  (dom/div
  ;    (ui-image imagea)
  ;    (ui-image imageb)
  ;    ))

  ;(defsc Href [this {:href/keys [id link image]}]
  ;  {:query [:href/id
  ;           :href/link
  ;           {:href/image (comp/get-query Image)}]
  ;   :initial-state
  ;          (fn [{:keys [link id src alt]}]
  ;            {:href/id id
  ;             :href/link link
  ;             :href/image
  ;             (comp/get-initial-state
  ;               Image {:id (str id "-img") :src src :alt alt})})
  ;   }
  ;  (let [{:keys [href-container]} (css/get-classnames Href)]
  ;    (dom/a {:href link
  ;            :target "__blank"
  ;            :rel "noopener noreferrer"
  ;            :classes [href-container]}            ;IT HAS TO MATCH THE CSS CLASS NAME
  ;           (ui-image image))))

  ;(defsc Home [this {:home/keys [left-side] :as props}]
  ;  {:query [{:home/left-side (comp/get-query LeftSide)}]
  ;   :ident (fn [] [:component/id :home])
  ;   :route-segment ["home"]
  ;   :initial-state
  ;   (fn [_]
  ;     {:home/left-side
  ;      (comp/get-initial-state
  ;        LeftSide
  ;        {:top
  ;         {:link "https://en.wikipedia.org/wiki/Gaming"
  ;          :id "gamin"
  ;          :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;          :alt "I play games I KNOW I'M SORRY"}
  ;         :bottom
  ;         {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;          :id "pho"
  ;          :src "../images/the-thinker.png"
  ;          :alt "But really, what even IS a rock anyways???"}})
  ;      })}
  ;  ;(let [{:keys [general-container]} (css/get-classnames Home)]
  ;  (dom/div
  ;    (str "First: "
  ;         (comp/get-initial-state
  ;           LeftSide
  ;           {:top
  ;            {:link "https://en.wikipedia.org/wiki/Gaming"
  ;             :id "gamin"
  ;             :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;             :alt "I play games I KNOW I'M SORRY"}
  ;            :bottom
  ;            {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;             :id "pho"
  ;             :src "../images/the-thinker.png"
  ;             :alt "But really, what even IS a rock anyways???"}})
  ;         )
  ;    (str "Second: "
  ;         left-side)
  ;    ;{:classes [general-container]}
  ;    ;(ui-left-side
  ;    ;left-side
  ;    ;)
  ;    )
  ;  ;)
  ;  )


  ;(def home-test
  ;  {
  ;   :test/right
  ;   (comp/get-initial-state
  ;     LeftSide
  ;     {:top
  ;      {:link "https://en.wikipedia.org/wiki/Gaming"
  ;       :id "gamin"
  ;       :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;       :alt "I play games I KNOW I'M SORRY"}
  ;      :bottom
  ;      {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;       :id "pho"
  ;       :src "../images/the-thinker.png"
  ;       :alt "But really, what even IS a rock anyways???"}})
  ;   :test/left
  ;   (comp/get-initial-state
  ;     LeftSide
  ;     {:top
  ;      {:link "https://en.wikipedia.org/wiki/Gaming"
  ;       :id "gamin"
  ;       :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;       :alt "I play games I KNOW I'M SORRY"}
  ;      :bottom
  ;      {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;       :id "pho"
  ;       :src "../images/the-thinker.png"
  ;       :alt "But really, what even IS a rock anyways???"}})
  ;
  ;   })



  ;(defsc Home [this {:test/keys [right
  ;                               left
  ;                               ] :as props}]
  ;  {:query [
  ;           ;{:test/left (comp/get-query LeftSide)}
  ;           :test/right
  ;           :test/left
  ;           ]
  ;   :ident (fn [] [:component/id :home])
  ;   :route-segment ["home"]
  ;   :initial-state
  ;   (fn [_]
  ;     {
  ;      :test/right
  ;      (comp/get-initial-state
  ;        LeftSide
  ;        {:top
  ;         {:link "https://en.wikipedia.org/wiki/Gaming"
  ;          :id "gamin"
  ;          :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;          :alt "I play games I KNOW I'M SORRY"}
  ;         :bottom
  ;         {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;          :id "pho"
  ;          :src "../images/the-thinker.png"
  ;          :alt "But really, what even IS a rock anyways???"}})
  ;      :test/left
  ;      (comp/get-initial-state
  ;        LeftSide
  ;        {:top
  ;         {:link "https://en.wikipedia.org/wiki/Gaming"
  ;          :id "gamin"
  ;          :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;          :alt "I play games I KNOW I'M SORRY"}
  ;         :bottom
  ;         {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;          :id "pho"
  ;          :src "../images/the-thinker.png"
  ;          :alt "But really, what even IS a rock anyways???"}})
  ;
  ;      }
  ;     )
  ;   }
  ;  ;(dom/div
  ;  ;  (str "Right: " right)
  ;  ;  (str "--------")
  ;  ;  (str "Left: " left))
  ;  ;(str "aaa: " right)
  ;  ;(dom/div
  ;  ;  (ui-left-side right)
  ;  ;  )
  ;  (dom/div
  ;    (ui-left-side right)
  ;    (ui-left-side left)
  ;    ))


  ;First:
  ;{:left-side/top
  ; {:top-left/contents
  ;  {:href/id "gamin", :href/link "https://en.wikipedia.org/wiki/Gaming",
  ;   :href/image
  ;            {:image/id "gamin",
  ;             :image/src "../images/WITH_OUR_THREE_POWERS_COMBINED.png",
  ;             :image/alt "I play games I KNOW I'M SORRY",
  ;             :image/classes nil}}},
  ; :left-side/bottom
  ; {:bottom-left/contents
  ;  {:href/id "pho",
  ;   :href/link "https://www.whatisitliketobeaphilosopher.com/",
  ;   :href/image
  ;   {:image/id "pho",
  ;    :image/src "../images/the-thinker.png",
  ;    :image/alt "But really, what even IS a rock anyways???",
  ;    :image/classes nil}}}}
  ;Second:
  ;{:left-side/top
  ; {:top-left/contents
  ;  {:href/id "gamin",
  ;   :href/link "https://en.wikipedia.org/wiki/Gaming",
  ;   :href/image
  ;            {:image/id "pho",
  ;             :image/src "../images/the-thinker.png",
  ;             :image/alt "But really, what even IS a rock anyways???",
  ;             :image/classes nil}}},
  ; :left-side/bottom
  ;{:bottom-left/contents
  ; {:href/id "pho", :href/link "https://www.whatisitliketobeaphilosopher.com/", :href/image
  ;           {:image/id      "pho", :image/src "../images/the-thinker.png", :image/alt "But really, what even IS a rock anyways???",
  ;            :image/classes nil}}} }


  ;{
  ; :home/left
  ; (comp/get-initial-state
  ;   LeftSide
  ;   {:top
  ;    {:link "https://en.wikipedia.org/wiki/Gaming"
  ;     :id "gamin"
  ;     :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;     :alt "I play games I KNOW I'M SORRY"}
  ;    :bottom
  ;    {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;     :id "pho"
  ;     :src "../images/the-thinker.png"
  ;     :alt "But really, what even IS a rock anyways???"}})
  ; }


  ;(defsc Home [this {:test/keys [imagea]}]
  ;  {:query [{:test/imagea (comp/get-query Image)}]
  ;   :route-segment ["home"]
  ;   :ident (fn [] [:component/id :home])
  ;   :initial-state
  ;   (fn [_]
  ;     {:test/imagea
  ;      (comp/get-initial-state
  ;        Image {:id "yo"
  ;               :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;               :alt "test"
  ;               :classes "none"})
  ;      })}
  ;  (dom/div
  ;    "words? "
  ;    (ui-image imagea)))

  ;(ui-left-side                                             ;left
  ;  (comp/get-initial-state
  ;    LeftSide
  ;    {:top
  ;     {:link "https://en.wikipedia.org/wiki/Gaming"
  ;      :id "gamin"
  ;      :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;      :alt "I play games I KNOW I'M SORRY"}
  ;     :bottom
  ;     {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;      :id "pho"
  ;      :src "../images/the-thinker.png"
  ;      :alt "But really, what even IS a rock anyways???"}}))

  ;(def teste
  ;  {:home/left
  ;   (comp/get-initial-state
  ;     LeftSide
  ;     {:top
  ;      {:link "https://en.wikipedia.org/wiki/Gaming"
  ;       :id "gamin"
  ;       :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;       :alt "I play games I KNOW I'M SORRY"}
  ;      :bottom
  ;      {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;       :id "pho"
  ;       :src "../images/the-thinker.png"
  ;       :alt "But really, what even IS a rock anyways???"}})})

  ;(defsc Home [this {:home/keys [left]}]
  ;  {:query [:home/left]
  ;   :route-segment ["home"]
  ;   :ident (fn [] [:component/id :home])
  ;   :initial-state
  ;    (fn [_] teste)}
  ;  (dom/div
  ;    (ui-left-side
  ;      left
  ;    ;              (comp/get-initial-state
  ;    ;                LeftSide
  ;    ;                {:top
  ;    ;                 {:link "https://en.wikipedia.org/wiki/Gaming"
  ;    ;                  :id "gamin"
  ;    ;                  :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;    ;                  :alt "I play games I KNOW I'M SORRY"}
  ;    ;                 :bottom
  ;    ;                 {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;    ;                  :id "pho"
  ;    ;                  :src "../images/the-thinker.png"
  ;    ;                  :alt "But really, what even IS a rock anyways???"}})
  ;                  )
  ;    ))


  ;(defsc Test [this {:test/keys [left
  ;                               ] :as props}]
  ;  {:query [:test/left
  ;           ]
  ;   :ident (fn [] [:component/id :test])
  ;   :route-segment ["test"]
  ;   :initial-state
  ;   (fn [_]
  ;     {
  ;      :test/left
  ;        (comp/get-initial-state
  ;          LeftSide
  ;          {:top
  ;           {:link "https://en.wikipedia.org/wiki/Gaming"
  ;            :id "gamin"
  ;            :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;            :alt "I play games I KNOW I'M SORRY"}
  ;           :bottom
  ;           {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;            :id "pho"
  ;            :src "../images/the-thinker.png\\"
  ;            :alt "But really, what even IS a rock anyways???"}})}
  ;     )
  ;   }
  ;  (dom/div
  ;    (ui-left-side left
  ;      ;(comp/get-initial-state
  ;      ;  LeftSide
  ;      ;  {:top
  ;      ;   {:link "https://en.wikipedia.org/wiki/Gaming"
  ;      ;    :id "gamin"
  ;      ;    :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;      ;    :alt "I play games I KNOW I'M SORRY"}
  ;      ;   :bottom
  ;      ;   {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;      ;    :id "pho"
  ;      ;    :src "../images/the-thinker.png\\"
  ;      ;    :alt "But really, what even IS a rock anyways???"}})
  ;      )
  ;    ))

  ;(defsc Test [this {:test/keys [
  ;                               imagea
  ;                               imagex
  ;                               ] :as props}]
  ;  {:query [
  ;           :test/imagea
  ;           :test/imagex
  ;           ]
  ;   :ident (fn [] [:component/id :test])
  ;   :route-segment ["test"]
  ;   :initial-state
  ;   (fn [_]
  ;     {:test/imagea
  ;     (comp/get-initial-state
  ;       Image {:id "yo"
  ;              :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;              :alt "test"
  ;              :classes "none"})
  ;
  ;     :test/imagex
  ;     (comp/get-initial-state
  ;       Image {:id "yooo"
  ;              :src "../images/the-thinker.png"
  ;              :alt "teste"
  ;              :classes "none"}
  ;      )
  ;      }
  ;
  ;     )
  ;   }
  ;  (dom/div
  ;    (ui-image imagea)
  ;    (ui-image imagex)
  ;    ))


  ;; Stuff I probs want idk

  ;(defsc Test [this {:test/keys [image]}]
  ;  {:query [:test/image]
  ;   :initial-state
  ;   (fn [_]
  ;     {:test/image
  ;      (comp/get-initial-state
  ;        Image
  ;        {:id "yo"
  ;         :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;         :alt "test"
  ;         :classes "none"})})}
  ;  (dom/div
  ;    "Thing: " (ui-image image)))

  ;(defsc About [this {:test/keys [words left] :as props}]
  ;  {:query [:test/words
  ;           {:test/left (comp/get-query LeftSide)}]
  ;   :ident (fn [] [:component/id :home])
  ;   :route-segment ["about"]
  ;   :initial-state
  ;   (fn [_]
  ;     {:test/words "Here are some words"
  ;      :test/left (comp/get-initial-state
  ;                   LeftSide
  ;                   {:top
  ;                    {:link "https://en.wikipedia.org/wiki/Gaming"
  ;                     :id 3
  ;                     :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;                     :alt "I play games I KNOW I'M SORRY"}
  ;                    :bottom
  ;                    {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;                     :id 2
  ;                     :src "../images/the-thinker.png\\"
  ;                     :alt "But really, what even IS a rock anyways???"}})})}
  ;  (dom/div
  ;    (str "Tis about: " words)
  ;    (str "Left side: ")
  ;    (ui-left-side left)))

  ;(defsc Contact [this {:contact/keys [id image span-image] :as props}]
  ;  {:query [:contact/id
  ;           {:contact/image (comp/get-query Image)}
  ;           {:contact/span-image (comp/get-query Image)}]
  ;   :route-segment ["contact"]
  ;   :ident (fn [] [:contact/id :contact])
  ;   ;:will-enter (dr/route-immediate [:contact/id ::contact])
  ;   :initial-state
  ;   (fn [{:keys [_] :as params}]
  ;     {:contact/id 1
  ;      :contact/image
  ;                  (comp/get-initial-state Image
  ;                                          {:id "mail-big"
  ;                                           :src "../images/mailV2.png"
  ;                                           :alt "email"
  ;                                           :classes "big-email-boi"})
  ;      :contact/span-image
  ;                  (comp/get-initial-state Image
  ;                                          {:id "mail-small"
  ;                                           :src "../images/mail_secure.PNG"
  ;                                           :alt "for security reasons"
  ;                                           :classes "small-email-boi"})})
  ;   :css [[:.general-container
  ;          {:display "flex"
  ;           :flex-direction "row"
  ;           :justify-content "center"
  ;           :align-items "center"}]
  ;         [:.general-container>div>.href-image-container
  ;          {:width "50%"
  ;           :height "50%"}]
  ;         [:.contact {:color "white"
  ;                     :position "relative"
  ;                     :top "0"
  ;                     :left "0"
  ;                     :transform "scale(0.7)"
  ;                     :display "flex"
  ;                     :flex-direction "column"
  ;                     :align-items "center"
  ;                     }]
  ;         [:.contact>img {:width "100%"
  ;                         :height "auto"
  ;                         :position "relative"
  ;                         :top "0"
  ;                         :left "0"}]
  ;         [:.contact>.big-email-boi {:position "relative"
  ;                                    :top "0"
  ;                                    :left "0"
  ;                                    :border-radius "1.5em"}]
  ;         [:.contact>.small-email-boi {:position "absolute"
  ;                                      :top "38%"
  ;                                      :left "0"
  ;                                      :visibility "hidden"
  ;                                      :transform "scale(0.8)"
  ;                                      }]
  ;         [:.contact>.big-email-boi:hover+.small-email-boi {
  ;                                                           :visibility "visible"
  ;                                                           }]
  ;         [:.very-small-text {
  ;
  ;
  ;                             }]
  ;         [:.contact>.small-email-boi:hover {:visibility "visible"}]
  ;         ]}
  ;  (let [{:keys [contact]} (css/get-classnames Contact)]
  ;    (dom/div
  ;      {:classes [contact
  ;                 ;           contact>big-mail-boi
  ;                 ;           contact>small-mail-boi
  ;                 ]
  ;       }
  ;      ;(inj/style-element {:component Contact})
  ;      (ui-image image)
  ;      (ui-image span-image)
  ;
  ;      ;(:span {:className "popup"})
  ;
  ;      ;(dom/div
  ;      ;(dom/div {:className "mail"} )
  ;      ;)
  ;      (dom/div {:className "very-small-text"}
  ;               "(email for social media)"))))

  ;(defsc Projects [this {:test/keys [words left] :as props}]
  ;  {:query [:test/words
  ;           {:test/left (comp/get-query LeftSide)}]
  ;   :ident (fn [] [:component/id :home])
  ;   :route-segment ["projects"]
  ;   :initial-state
  ;     (fn [_]
  ;       {:test/words "Here are some words"
  ;        :test/left (comp/get-initial-state LeftSide
  ;         {:top
  ;          {:link "https://en.wikipedia.org/wiki/Gaming"
  ;           :id 4
  ;           :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;           :alt "I play games I KNOW I'M SORRY"}
  ;          :bottom
  ;          {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;           :id 4
  ;           :src "../images/the-thinker.png\\"
  ;           :alt "But really, what even IS a rock anyways???"}})})}
  ;  (dom/div (ui-left-side left)))
  ;;;;;

  ;[com.fulcrologic.fulcro.networking.http-remote :as net]
  ;[com.fulcrologic.fulcro.data-fetch :as df]
  ;[com.fulcrologic.fulcro.ui-state-machines :as uism]
  ;[com.fulcrologic.fulcro-css.css-injection :as cssi]
  ;[app.model.session :as session]
  ;[com.fulcrologic.fulcro.algorithms.denormalize :as fdn]
  ;[com.fulcrologic.fulcro.algorithms.merge :as merge]
  ;[com.fulcrologic.fulcro.inspect.inspect-client :as inspect]

  ;(defn ^:export refresh []
  ;  ;(log/info "Hot code Remount")
  ;  ;(cssi/upsert-css "componentcss" {:component root/Root})
  ;  ;(comp/refresh-dynamic-queries! root/Root)
  ;  (app/mount! SPA root/Root "app")
  ;  (comp/refresh-dynamic-queries! SPA))
  ;
  ;(defn ^:export init []
  ;  ;(log/info "Application starting.")
  ;  ;(cssi/upsert-css "componentcss" {:component root/Root})
  ;  ;(inspect/app-started! SPA)
  ;  (app/set-root! SPA root/Root {:initialize-state? true})
  ;  (dr/initialize! SPA)
  ;  ; (log/info "Starting session machine.")
  ;  ;(uism/begin! SPA session/session-machine ::session/session
  ;  ;{
  ;  ;:actor/login-form      root/Login
  ;  ;:actor/current-session root/Session})
  ;  (dr/change-route! SPA ["home"])
  ;  (app/mount! SPA root/Root "app" {:initialize-state? false})
  ;  ; (dr/change-route! SPA ["Home"])
  ;  )

  ;(fn [_]
  ;  {:outer/router
  ;   (comp/get-initial-state RootRouter {})})


  ;(defsc Test [this {:test/keys [
  ;                               words
  ;                               left
  ;                               ] :as props}]
  ;  {:query [:test/words
  ;           {:test/left (comp/get-query LeftSide)}
  ;           ]
  ;   :ident (fn [] [:component/id :test])
  ;   :route-segment ["test"]
  ;   :initial-state
  ;   (fn [_]
  ;     {:test/words "Here are some words"
  ;      :test/left
  ;        (comp/get-initial-state LeftSide
  ;         {:top
  ;          {:link "https://en.wikipedia.org/wiki/Gaming"
  ;           :id 1
  ;           :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;           :alt "I play games I KNOW I'M SORRY"}
  ;          :bottom
  ;          {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;           :id 2
  ;           :src "../images/the-thinker.png\\"
  ;           :alt "But really, what even IS a rock anyways???"}})
  ;      }
  ;     )
  ;   }
  ;  (dom/div
  ;    (str "Tis a test: " words)
  ;    (str "Left side: ")
  ;    (ui-left-side left)
  ;    ))

  ;(defn client-did-mount
  ;  "Must be used as :client-did-mount parameter of app creation, or called just after you mount the app."
  ;  [app]
  ;  (dr/change-route app ["main"]))

  ;(fn [_] {                                                ;:home/left-side
  ;         ;(comp/get-initial-state LeftSide)
  ;         ;:home/middle
  ;         ;(comp/get-initial-state LeftSide)
  ;         ;:home/right-side
  ;         ;(comp/get-initial-state LeftSide)
  ;         })
  ;:css [[:.general-container
  ;       {:display "flex"
  ;        :flex-direction "row"
  ;        :justify-content "center"
  ;        :align-items "center"}]
  ;      [:.general-container>div>.href-image-container
  ;       {:width "50%"
  ;        :height "50%"}]]
  ;:initial-state (fn [])
  ;(fn [{:keys [_] :as params}] home-initial-state)
  ;:will-enter
  ;(fn [app {:home/keys [id] :as route-params}]
  ;  (log/info "will enter user with route params: " route-params)
  ;  (log/info "Id: " (:home/keys route-params))
  ;  (let [id (if (string? id) (js/parseInt id) id)]
  ;    (dr/route-deferred
  ;      [:home/id 1]
  ;      (fn [] (df/load!
  ;               app
  ;               [:home/id id]
  ;               Home
  ;               {:post-mutation `dr/target-ready
  ;                :post-mutation-params
  ;                {:target [:home/id id]}})))))
  ;:will-enter (fn [app {:home/keys [id] :as route-params}]
  ;              (dr/route-deferred [:home/id id]
  ;                #(df/load app [:home/id id] Home
  ;                    {:post-mutation `dr/target-ready
  ;                     :post-mutation-params
  ;                     {:target [:home/id id]}})))
  ;(dr/route-immediate [:home/id ::home])
  ;:will-enter
  ;(fn [app {:home/keys [id] :as route-params}]
  ; (log/info "Will enter user with route params " route-params)
  ; ;; be sure to convert strings to int for this case
  ; (let [id (if (string? id) (js/parseInt id) id)]
  ;    #(df/load app [:home/id id] Home
  ;      {:post-mutation `dr/target-ready
  ;       :post-mutation-params
  ;        {:target [:home/id id]}}))
  ;  )




  ;(defsc Home [this {:test/keys [words left] :as props}]
  ;  {:query [:test/words
  ;           {:test/left (comp/get-query LeftSide)}]
  ;   :ident (fn [] [:component/id :home])
  ;   :route-segment ["home"]
  ;   :initial-state
  ;   (fn [_]
  ;     {:test/words "Here are some words"
  ;      :test/left (comp/get-initial-state LeftSide
  ;       {:top
  ;        {:link "https://en.wikipedia.org/wiki/Gaming"
  ;         :id 1
  ;         :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;         :alt "I play games I KNOW I'M SORRY"}
  ;        :bottom
  ;        {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;         :id 2
  ;         :src "../images/the-thinker.png\\"
  ;         :alt "But really, what even IS a rock anyways???"}})})}
  ;  (dom/div
  ;    (str "Tis a test: " words)
  ;    (str "Left side: ")
  ;    (ui-left-side left)))

  ;(defsc Home [this {:home/keys [words] :as props}]
  ;  {:query [:home/words]
  ;      :ident (fn [] [:component/id :home])
  ;
  ;   :route-segment ["home"]
  ;   :initial-state (fn [_] {:home/words "words"})}
  ;  (dom/div "home" words))

  ;HOME JUNK

  ;(defsc Home [this {:home/keys
  ;                   [
  ;                    ;left-side
  ;                    ;middle
  ;                    ;right-side
  ;                    ] :as props}]
  ;  {:query [
  ;           ;{:home/left-side (comp/get-query LeftSide)}
  ;           ;{:home/middle (comp/get-query Middle)}
  ;           ;{:home/right-side (comp/get-query RightSide)}
  ;           ]
  ;   :ident (fn [] :component/id :home)
  ;   :route-segment ["home"]
  ;   :initial-state
  ;   (fn [_] {                                                ;:home/left-side
  ;            ;(comp/get-initial-state LeftSide)
  ;            ;:home/middle
  ;            ;(comp/get-initial-state LeftSide)
  ;            ;:home/right-side
  ;            ;(comp/get-initial-state LeftSide)
  ;            })
  ;   ;:css [[:.general-container
  ;   ;       {:display "flex"
  ;   ;        :flex-direction "row"
  ;   ;        :justify-content "center"
  ;   ;        :align-items "center"}]
  ;   ;      [:.general-container>div>.href-image-container
  ;   ;       {:width "50%"
  ;   ;        :height "50%"}]]
  ;   ;:initial-state (fn [])
  ;   ;(fn [{:keys [_] :as params}] home-initial-state)
  ;   ;:will-enter
  ;   ;(fn [app {:home/keys [id] :as route-params}]
  ;   ;  (log/info "will enter user with route params: " route-params)
  ;   ;  (log/info "Id: " (:home/keys route-params))
  ;   ;  (let [id (if (string? id) (js/parseInt id) id)]
  ;   ;    (dr/route-deferred
  ;   ;      [:home/id 1]
  ;   ;      (fn [] (df/load!
  ;   ;               app
  ;   ;               [:home/id id]
  ;   ;               Home
  ;   ;               {:post-mutation `dr/target-ready
  ;   ;                :post-mutation-params
  ;   ;                {:target [:home/id id]}})))))
  ;   ;:will-enter (fn [app {:home/keys [id] :as route-params}]
  ;   ;              (dr/route-deferred [:home/id id]
  ;   ;                #(df/load app [:home/id id] Home
  ;   ;                    {:post-mutation `dr/target-ready
  ;   ;                     :post-mutation-params
  ;   ;                     {:target [:home/id id]}})))
  ;   ;(dr/route-immediate [:home/id ::home])
  ;   ;:will-enter
  ;   ;(fn [app {:home/keys [id] :as route-params}]
  ;   ; (log/info "Will enter user with route params " route-params)
  ;   ; ;; be sure to convert strings to int for this case
  ;   ; (let [id (if (string? id) (js/parseInt id) id)]
  ;   ;    #(df/load app [:home/id id] Home
  ;   ;      {:post-mutation `dr/target-ready
  ;   ;       :post-mutation-params
  ;   ;        {:target [:home/id id]}}))
  ;   ;  )
  ;   }
  ;  ;(let [{:keys [general-container]} (css/get-classnames Home)]
  ;    (dom/div "home"
  ;  ;    ;{:classes [general-container]}
  ;  ;           (ui-left-side left-side)
  ;  ;           ;(ui-middle middle)
  ;  ;           ;            (ui-right-side right-side)
  ;  ;           )
  ;    )
  ;
  ;  )
  ;(def ui-home (comp/factory Home {:keyfn :home/id}))


  ;(def home-initial-state
  ;  {:home/id 1
  ;   :home/left-side
  ;            (comp/get-initial-state LeftSide
  ;                                    {:top
  ;                                     {:link "https://en.wikipedia.org/wiki/Gaming"
  ;                                      :id 1
  ;                                      :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
  ;                                      :alt "I play games I KNOW I'M SORRY"}
  ;                                     :bottom
  ;                                     {:link "https://www.whatisitliketobeaphilosopher.com/"
  ;                                      :id 2
  ;                                      :src "../images/the-thinker.png\\"
  ;                                      :alt "But really, what even IS a rock anyways???"}})
  ;   :home/middle
  ;            (comp/get-initial-state Middle
  ;                                    {:content
  ;                                     [(dom/p {:key 1
  ;                                              :className "enlarge-text"}
  ;                                             ;{:class "enlarge-text"}
  ;                                             "Mostly this stuff")
  ;                                      (dom/p
  ;                                        {:key 2
  ;                                         :className "small-text"}
  ;                                        "(check out my projects for novel things)")]
  ;                                     })
  ;   :home/right-side
  ;            (comp/get-initial-state RightSide
  ;                                    {:top
  ;                                     {:link "https://www.youtube.com/"
  ;                                      :id 3
  ;                                      :src "../images/tubes.png"
  ;                                      :alt "Youtube is my Netflix, sadly"}
  ;                                     :bottom
  ;                                     {:link "https://en.wikipedia.org/wiki/Programmer"
  ;                                      :id 4
  ;                                      :src "../images/meirl.png"
  ;                                      :alt "g! 'How to print newline in cljs'"}})})

  ;;;;









  ;(defrouter PageOptions [this {:keys [current-state] :as props}]
  ;  {:router-targets [Home]}
  ;  (case current-state
  ;    :pending (dom/div "...")
  ;    :failed (dom/div "Failed")
  ;    (dom/div "No Route Selected")))

  ;(update-in [:button/id id :button/num]
  ;           #(if (= %1 1) 0 1))

  ; also useful
  ;(defn style [& info]
  ;  {:style (.trim (apply str (map #(let [[kwd val] %]
  ;                                    (str (name kwd) ":" val "; "))
  ;                                 (apply hash-map info))))})
  ;(def body-style
  ;  (style
  ;    :background-image "url(/images/background.png)"
  ;    :background-color "black"
  ;    :background-position "center"
  ;    :background-attachment "fixed"
  ;    :background-repeat "no-repeat"
  ;    :background-size "cover"
  ;    :width "100%"
  ;    :height "100%"))
  ;;:html [                  height:100%;
  ;;       display: flex;
  ;;       flex-direction: column;
  ;;       overflow-x: hidden;
  ;;       ]
  ;;:body>* [flex-shrink 0]

  ; VERY HELPFUL
  ;(defsc LeftSide [this {:left-side/keys [
  ;                                        ;top
  ;                                        contents
  ;                                        ] :as props}]
  ;       {:query [
  ;                {:left-side/contents (comp/get-query TopLeft)}
  ;                ;{:left-side/bottom (comp/get-query BottomLeft)}
  ;                ]
  ;        :initial-state
  ;               (fn [{:keys [top
  ;                            ;bottom
  ;                            ] :as params}]
  ;                 (let [link (:link top)
  ;                       id (:id top)
  ;                       src (:src top)
  ;                       alt (:alt top)
  ;                       ]
  ;                   {:left-side/contents
  ;                    (comp/get-initial-state TopLeft
  ;                                            {:link link :id id :src src :alt alt}
  ;
  ;
  ;                                            )
  ;                    }
  ;                   )
  ;
  ;
  ;                 ;(comp/get-initial-state BottomLeft bottom)
  ;                 )}
  ;       (dom/div
  ;         (println "contents" contents)
  ;         (ui-top-left contents)
  ;         ;(ui-bottom-left bottom)
  ;         ))
  ;(def ui-left-side (comp/factory LeftSide))


  ;(defsc Test [this {:keys [image]}]
  ;       {:query [:type :id {:image (comp/get-query Image)}]
  ;        :initial-state
  ;               (fn [{:keys [id src alt]}]
  ;                 {:id id :type :test
  ;                  :image (comp/get-initial-state
  ;                           Image {:id 1 :src src :alt alt})})}
  ;       (dom/div
  ;         (ui-image image)))
  ;(def ui-test (comp/factory Test {:keyfn :id}))



  ;;; ENTRANCE CODE ;;;

  ;(comment
  ;  (inspect/app-started! SPA)
  ;  (app/mounted? SPA)
  ;  (app/set-root! SPA root/Root {:initialize-state? true})
  ;  (uism/begin! SPA session/session-machine ::session/session
  ;    {:actor/login-form      root/Login
  ;     :actor/current-session root/Session})
  ;
  ;  (reset! (::app/state-atom SPA) {})
  ;
  ;  (merge/merge-component! SPA root/Settings {:account/time-zone "America/Los_Angeles"
  ;                                             :account/real-name "Joe Schmoe"})
  ;  (dr/initialize! SPA)
  ;  (app/current-state SPA)
  ;  (dr/change-route SPA ["settings"])
  ;  (app/mount! SPA root/Root "app")
  ;  (comp/get-query root/Root {})
  ;  (comp/get-query root/Root (app/current-state SPA))
  ;
  ;  (-> SPA ::app/runtime-atom deref ::app/indexes)
  ;  (comp/class->any SPA root/Root)
  ;  (let [s (app/current-state SPA)]
  ;    (fdn/db->tree [{[:component/id :login] [:ui/open? :ui/error :account/email
  ;                                            {[:root/current-session '_] (comp/get-query root/Session)}
  ;                                            [::uism/asm-id ::session/session]]}] {} s)))

  ;(defsc Home [this {:inner-box/keys [id] :as props}]
  ;       {:query []
  ;        :ident :inner-box/keys
  ;        :initial-state (fn [{:keys [id] :as params}]
  ;                           {:inner-box/keys id})}
  ;       )
  ;
  ;(defsc ContainerHeader)
  ;
  ;
  ;(defsc InnerBox [this {:inner-box/keys [id] :as props}]
  ;       {:query []
  ;        :ident :inner-box/keys
  ;        :initial-state (fn [{:keys [id] :as params}]
  ;                           {:inner-box/keys id})}
  ;       (dom/div {:id "inner-box"}
  ;         (match [request]
  ;                ["home"] home/home-page-body
  ;                ["about"] about/about-page-body
  ;                ["projects"] projects/project-page-body
  ;                ["contact"] contact/contact-page-body)
  ;         ))
  ;
  ;(def ui-container (comp/factory Container))



  ;(defn get-header [request]
  ;      (match [request]
  ;             ["home"] (dom/p "What am I up to?")
  ;             ;["about"] about/about-page-header
  ;             ;["projects"] projects/project-page-header
  ;             ;["contact"] contact/contact-page-header
  ;             ))
  ;(defn get-inner-content [request]
  ;      (match [request]
  ;             ["home"] (ui-)
  ;             ;["about"] about/about-page-body
  ;             ;["projects"] projects/project-page-body
  ;             ;["contact"] contact/contact-page-body
  ;             ))

  ;(defn get-inner [req]
  ;      (dom/div
  ;        (match [req]
  ;               ["home"] (
  ;                         (ui-container-header header)
  ;                         (ui-container-body body)
  ;                          )
  ;               )
  ;        )
  ;)

  ;(defsc ImageContainer [this {:image-container/keys [id src alt] :as props}]
  ;  {:query [:image-container/id :image-container/src :image-container/alt]
  ;   :ident :image-container/id
  ;   :initial-state (fn [{:keys [id src alt] :as params}]
  ;                    {:image-container/id id
  ;                     :image-container/src src
  ;                     :image-container/alt alt})}
  ;  ;css/context
  ;  (dom/div
  ;    "ID: " id
  ;    "Src: " src
  ;    "Alt: " alt
  ;    )
  ;  ;(dom/img {:src src :alt alt}
  ;  ;  "Id: " id
  ;  ;  )
  ;  )
  ;(def ui-image-container (comp/factory ImageContainer))

  ;(defsc Container [this {:container/keys [id content] :as props}]
  ;       {:query [:container/id :container/content]
  ;        :ident :container/id
  ;        :initial-state (fn [{:keys [id content] :as params}]
  ;                           {:container/id id
  ;                            :container/content content})}
  ;        )
  ;(def ui-container (comp/factory Container))
  ;
  ;(defsc ContainerRight [this {:container-right/keys [id content] :as props}]
  ;       {:query [:container-right/id :container-right/content]
  ;        :ident :container-right/id
  ;        :initial-state (fn [{:keys [id content] :as params}]
  ;                           {:container-right/id id
  ;                            :container-right/container
  ;                              (comp/get-initial-state Container
  ;                                {:container/id id
  ;                                 :container/content content})})}
  ;       (dom/div :.container-right "right-side" content))
  ;(def ui-container-right (comp/factory ContainerRight))
  ;
  ;(defsc ContainerHorizontalMiddle [this {:container-hoz-middle/keys [id content] :as props}]
  ;       {:query [:container-hoz-middle/id :container-hoz-middle/content]
  ;        :ident :container-hoz-middle/id
  ;        :initial-state (fn [{:keys [id content] :as params}]
  ;                           {:container-hoz-middle/id id
  ;                            :container-hoz-middle/container
  ;                              (comp/get-initial-state Container
  ;                                {:container/id id
  ;                                 :container/content content})})}
  ;       (dom/div :.container-hoz-middle  "hoz-middle" content))
  ;(def ui-container-hoz-middle (comp/factory ContainerHorizontalMiddle))
  ;
  ;(defsc ContainerLeft [this {:container-left/keys [id content] :as props}]
  ;       {:query [:container-left/id :container-left/content]
  ;        :ident :container-left/id
  ;        :initial-state (fn [{:keys [id content] :as params}]
  ;                           {:container-left/id id
  ;                            :container-left/container
  ;                            (comp/get-initial-state Container
  ;                              {:container/id id
  ;                               :container/content content})})}
  ;       (dom/div :.container-left  "left-side" content))
  ;(def ui-container-left (comp/factory ContainerLeft))
  ;
  ;(defsc ContainerTop [this {:container-top/keys [id content] :as props}]
  ;       {:query [:container-top/id :container-top/content]
  ;        :ident :container-top/id
  ;        :initial-state (fn [{:keys [id content] :as params}]
  ;                           {:container-top/id id
  ;                            :container-top/container
  ;                              (comp/get-initial-state Container
  ;                                {:container/id id
  ;                                 :container/content content})})}
  ;       (dom/div :.container-top  "top" content))
  ;(def ui-container-top (comp/factory ContainerTop))
  ;
  ;(defsc ContainerVerticalMiddle [this {:container-vert-middle/keys [id content] :as props}]
  ;       {:query [:container-vert-middle/id :container-vert-middle/content]
  ;        :ident :container-vert-middle/id
  ;        :initial-state (fn [{:keys [id content] :as params}]
  ;                           {:container-vert-middle/id id
  ;                            :container-vert-middle/container
  ;                              (comp/get-initial-state Container
  ;                                {:container/id id
  ;                                 :container/content content})})}
  ;       (dom/div :.container-vert-middle  "top" content))
  ;(def ui-container-vert-middle (comp/factory ContainerTop))
  ;
  ;(defsc ContainerBottom [this {:container-bottom/keys [id content] :as props}]
  ;       {:query [:container-bottom/id :container-bottom/content]
  ;        :ident :container-bottom/id
  ;        :initial-state (fn [{:keys [id content] :as params}]
  ;                           {:container-bottom/id id
  ;                            :container-bottom/container
  ;                              (comp/get-initial-state Container
  ;                                {:container/id id
  ;                                 :container/content content})})
  ;        }
  ;       (dom/div :.container-bottom  "bottom" content))
  ;(def ui-container-bottom (comp/factory ContainerBottom))
  ;
  ;(defsc ContainerBody [this {:container-body/keys [id body] :as props}]
  ;       {:query [:container-body/id :container-body/body]
  ;        :ident :container-body/id
  ;        :initial-state (fn [{:keys [id body] :as params}]
  ;                           {:container-body/id id
  ;                            :container-body/body body})
  ;        :css [[:.outer-text
  ;               {:font-size "2em"
  ;                :color "white"
  ;                :text-align "center"
  ;                :font-family "MINIMAL"
  ;                :margin "0 auto"
  ;                :padding "0 auto"
  ;                :vertical-align "top"}]
  ;              [:.home
  ;               {:border "1px"
  ;                :border-color "white"
  ;                :border-style "solid"
  ;                :border-radius "1%"
  ;                :position "relative"
  ;                :width "inherit"
  ;                :overflow-wrap "anywhere"
  ;                :word-wrap "anywhere"
  ;                :hyphens "auto"
  ;                :display "flex"
  ;                :flex-direction "row"
  ;                :justify-content "center"
  ;                :align-items "center"}]]}
  ;(dom/div ":class \"box general-container\""
  ;         "hey look it's content + " body)
  ;       )
  ;(def ui-container-body (comp/factory ContainerBody))

  ;(defsc Test [this {:test/keys [name age]}]
  ;  {:query [:test/name :test/age]
  ;   :ident :test/name
  ;   :initial-state (fn [{:keys [name age] :as params}]
  ;                    {:test/name name :test/age age})}
  ;  (dom/li
  ;    (dom/h5 (str "Name: " name " | Age: " age))))
  ;
  ;(def ui-test (comp/factory Test {:keyfn :person/name}))

  ;(defsc TestList [this {:list/keys [label content]}]
  ;  {:query [:list/label :list/content]
  ;   :ident  :list/label
  ;   :initial-state (fn [{:keys [label]}]
  ;                    {:list/label label
  ;                     :list/people (if (= label "Hot")
  ;                        [(comp/get-initial-state Test
  ;                           {:name "tamali" :age "22"})
  ;                         (comp/get-initial-state Test
  ;                           {:name "Joe" :age 22})]
  ;                        [(comp/get-initial-state Test
  ;                           {:name "as ice" :age "99"})
  ;                           (comp/get-initial-state Test
  ;                             {:name "Bobby" :age 55})])})}
  ;  (dom/div
  ;    (dom/h4 label)
  ;    (dom/ul (str ui-test content))))
  ;(def ui-test-list (comp/factory TestList))




  ;{:home/id id
  ; :home/body
  ; (comp/get-initial-state
  ;   ContainerBody
  ;   {:container/id id
  ;    :container/content
  ;    ;{(comp/get-initial-state
  ;    ;   ContainerLeft
  ;    ;   ;{:container-left/id id
  ;    ;   ; :container-left/content
  ;    ;   ; {
  ;    ;   ;  ;(comp/get-initial-state
  ;    ;   ;  ;  ContainerTop
  ;    ;   ;  ;{:container-top/id id
  ;    ;   ;  ; :container-top/content
  ;    ;   ;  ; {(dom/div ":class table-role-video left-top href-image-container"
  ;    ;   ;  ;    (dom/a
  ;    ;   ;  ;      ":href \"https://en.wikipedia.org/wiki/Gaming\"
  ;    ;   ;  ;       :target \"__blank\" :rel \"noopener noreferrer\""
  ;    ;   ;  ;           (dom/img
  ;    ;   ;  ;             ":src \"../images/WITH_OUR_THREE_POWERS_COMBINED.png\"
  ;    ;   ;  ;             :alt "I play games I KNOW I'M SORRY"
  ;    ;   ;  ;             "))
  ;    ;   ;  ;           )}})
  ;    ;   ;  ;(comp/get-initial-state
  ;    ;   ;  ;  ContainerBottom
  ;    ;   ;  ;  {:container-bottom/id id
  ;    ;   ;  ;   :container-bottom/body
  ;    ;   ;  ;   {(dom/div ":class thonker left-bottom href-image-container"
  ;    ;   ;  ;             (dom/a
  ;    ;   ;  ;               ":href \"https://www.whatisitliketobeaphilosopher.com/\"
  ;    ;   ;  ;                :target \"__blank\" :rel \"noopener noreferrer\""
  ;    ;   ;  ;               (dom/img
  ;    ;   ;  ;                 ":src \"../images/the-thinker.png\"
  ;    ;   ;  ;                  :alt \"But really, what even IS a rock anyways???")))}})
  ;    ;   ;  }}
  ;    ;   )
  ;    ; (comp/get-initial-state
  ;    ;   ContainerHorizontalMiddle
  ;    ;   {
  ;    ;    ;(dom/div ":class middle-main-page padding-bottom"
  ;    ;    ;         (dom/p ":class enlarge-text" "Mostly this stuff")
  ;    ;    ;         (dom/p ":class small-text" "(check out my project for novel things"))
  ;    ;    })
  ;    ; (comp/get-initial-state
  ;    ;   ContainerRight
  ;    ;   {
  ;    ;    ;(dom/div ":class right"
  ;    ;    ;   (comp/get-initial-state
  ;    ;    ;     ContainerTop
  ;    ;    ;     {(dom/div ":class tuber right-top href-image-container"
  ;    ;    ;         (dom/a ":href \"https://www.youtube.com/\" :target \"__blank\" :rel \"noopener noreferrer"
  ;    ;    ;            (dom/img ":src \"../images/tubes.png\" :alt \"Youtube is my Netflix, sadly")))})
  ;    ;    ;         (comp/get-initial-state
  ;    ;    ;           ContainerMiddle
  ;    ;    ;           {(dom/div ":class sudo-apt-get-gf right-bottom href-image-container"
  ;    ;    ;                     (dom/a ":href \"https://en.wikipedia.org/wiki/Programmer\" :target \"__blank\" :rel \"noopener noreferrer\""
  ;    ;    ;                            (dom/img ":src \"../images/meirl.png\" :alt \"g! 'How to print newline in cljs'" )))}))
  ;    ;    })
  ;    ; }
  ; }
  ;   )}
  ;)}

  ;(dom/div
  ;  "id: " id
  ;  (ui-image-container img1 {:image-container/id 1
  ;                            :image-container/src "example"
  ;                            :image-container/alt "test"}))
  ;(ui-container-body
  ;  (ui-container-left
  ;    (ui-container-top ...)
  ;    (ui-container-bottom ...)
  ;    )
  ;  (ui-container-hoz-middle
  ;    ...)
  ;  (ui-container-right
  ;   (ui-container-top ...)
  ;   (ui-container-bottom ...)
  ;    ...)
  ;  body
  ;)

  ;
  ;(defmutation toggle-item
  ;  [{:keys [item]}]
  ;  (action [{:keys [state]}] (str "wahhhh")))

  ;(def sidebar-state (r/atom {:state "closed"}))
  ;:class (@sidebar-state :state)
  ;(menu-toggle sidebar-state)

  ;(defmutation toggle-value [ignored]
  ;  (action [{:keys [state]}]
  ;    ;(if (= state "on")
  ;    ;  (swap! state update :sidebar/num "off")
  ;    ;  (swap! state update :sidebar/num "on"))
  ;    (swap! state update :sidebar/num inc)))

  ;(defmutation toggle-value [ignored]
  ;             (action [{:keys [state]}]
  ;                     (swap! state update :sidebar/num inc)))
  ;
  ;(defsc SidebarContainer [this {:sidebar/keys [num]}]
  ;  {:query         [:sidebar/num]
  ;   :initial-state {:sidebar/num 0}
  ;   :css           [[:.green {:color "red"
  ;                             :margin "0px"}]]}
  ;    (dom/div
  ;      (dom/button
  ;        {:onClick #(comp/transact! this `[(toggle-value {})])}
  ;        "Times: " num)))
  ;
  ;(def ui-sidebar-container (comp/factory SidebarContainer))

  ;(defsc Root [this {:root/keys [;primary-container
  ;                                sidebar-container
  ;                               ;num
  ;                               ]}
  ;             {:keys [background]}
  ;             ]
  ;  {:query [;{:root/primary-container (comp/get-query PersonList)}
  ;           {:root/sidebar-container (comp/get-query SidebarContainer)}
  ;           ;:root/num
  ;           ]
  ;  :initial-state
  ;          (fn [params]
  ;            {;:root/friends
  ;             ;(comp/get-initial-state PersonList
  ;             ; {:id :friends :label "Friends"})
  ;             :root/sidebar-container
  ;             (comp/get-initial-state SidebarContainer)
  ;             ;:root/num 0
  ;             }
  ;              )
  ;   :css   [[:.background
  ;            {:width "100%"
  ;             :height "100%"
  ;             :color "blue"
  ;             :background-image "url('/images/background.png')"
  ;             :background-color "black"
  ;             :background-position "center"
  ;             :background-attachment "fixed"
  ;             :background-repeat "no-repeat"
  ;             :background-size "cover"}]]}
  ;  (let [{:keys [background]} (css/get-classnames Root)]
  ;    (dom/div :.background {:className [background]}
  ;      (inj/style-element {:component Root})
  ;      ;(ui-primary-container primary-container)
  ;      (ui-sidebar-container sidebar-container)
  ;
  ;      ;  (dom/button {:onClick #(comp/transact! this `[(toggle-value {})])}
  ;
  ;             )))

  ;(defsc Person [this {:person/keys [num]} {:keys [toggle-value]}]
  ;       {:query         [:person/num]
  ;        :initial-state (fn [{:keys [num] :as params}]
  ;                           {:person/num num})}
  ;       (dom/li
  ;         (dom/h5
  ;           (str num " (age: " num ")")
  ;           (dom/button
  ;             {:onClick #(toggle-value num)}
  ;             "X"))))
  ;
  ;(def ui-person (comp/factory Person {:keyfn :person/name}))
  ;
  ;(defsc Root [this {:list/keys [num]}]
  ;       {:query [:list/num (comp/get-query Person)]
  ;        :initial-state
  ;               (fn [{:keys [num]}]
  ;                   {:list/num (comp/get-initial-state Person)})}
  ;       (let [delete-person
  ;             (fn [name]
  ;                 (println label "asked to delete" name))]
  ;            (dom/div
  ;              (dom/h4 num)
  ;              (dom/ul (map
  ;                        (fn [p]
  ;                            (ui-person
  ;                              (comp/computed
  ;                                p {:toggle-value delete-person})))
  ;                        label)))))

  ;(defmutation toggle-value
  ;  [{sidebar-id :sidebar/id}]
  ;  (action [{:keys [state]}]
  ;          (println "State: " state)
  ;          (swap! state update
  ;                 [:sidebar/id sidebar-id :sidebar/num]
  ;                 inc)))
  ;
  ;(defsc SidebarContainer
  ;  [this {:sidebar/keys [num] :as props}]
  ;  {:query         [:sidebar/id :sidebar/num]
  ;   :ident         (fn []
  ;                    [:sidebar/id (:sidebar/id props)])
  ;   :initial-state (fn
  ;                    [{:keys [id] :as params}]
  ;                    {:sidebar/id {id
  ;                      {:sidebar/num 0}}})
  ;   :css           [[:.green {:color "red"
  ;                             :margin "0px"}]]}
  ;  (dom/div
  ;    (println "num " num)
  ;    (dom/button
  ;      {:onClick #(comp/transact! this
  ;                   [(toggle-value {})])}
  ;      "Times: " num)))
  ;
  ;(def ui-sidebar-container
  ;  (comp/factory SidebarContainer))

  ;(defmutation increase
  ;  [{sidebar-id :sidebar/id}]
  ;  (action [{:keys [state]}]
  ;          (println "State: " state)
  ;          (swap! state update
  ;                 [:sidebar/id sidebar-id :sidebar/num]
  ;                 inc)))

  ;(fn
  ;                    [{:keys [id] :as params}]
  ;                    {:sidebar/id {id
  ;                      {:sidebar/num 0}}})



  ;:root/sidebar-container
  ;(comp/get-initial-state SidebarContainer {:list-name "on"})})
  ;vvv
  ;:initial-state (fn [{:keys [list-name] :as params}] {:sidebar/list-name list-name})



  ;;some person stuff
  ;(defmutation delete-person
  ;  ;"Mutation: Delete the person with `name` from the list with `list-name`"
  ;  [{:keys [list-name name]}] ; (1)
  ;  (action [{:keys [state]}] ; (2)
  ;    (let [path     (if (= "Friends" list-name)
  ;                     [:friends :list/people]
  ;                     [:enemies :list/people])
  ;          old-list (get-in @state path)
  ;          new-list (vec (filter #(not= (:person/name %) name) old-list))]
  ;      (swap! state assoc-in path new-list))))
  ;
  ;(defsc Person [this {:person/keys [name age] :as props} {:keys [onDelete]}]
  ;  {:query         [:person/id :person/name :person/age] ; (2)
  ;   :ident         (fn [] [:person/id (:person/id props)]) ; (1)
  ;   :initial-state (fn [{:keys [id name age] :as params}]
  ;                    {:person/id id :person/name name :person/age age})} ; (3)
  ;  ;(dom/li
  ;  ;  (dom/h5
  ;      ;(str name " (age: " age ")")
  ;      ;(dom/button {:onClick #(onDelete name)} "X")
  ;      ;)
  ;    ;)
  ;  )

  ;(def ui-person (comp/factory Person {:keyfn :person/id}))
  ;
  ;(defsc PersonList [this {:list/keys [id label people] :as props}]
  ;  {:query [:list/id :list/label {:list/people (comp/get-query Person)}]
  ;   :ident (fn [] [:list/id (:list/id props)])
  ;   :initial-state
  ;          (fn [{:keys [id label]}]
  ;            {:list/id     id
  ;             :list/label  label
  ;             :list/people (if (= id :friends)
  ;                            [(comp/get-initial-state Person {:id 1 :name "Sally" :age 32})
  ;                             (comp/get-initial-state Person {:id 2 :name "Joe" :age 22})]
  ;                            [(comp/get-initial-state Person {:id 3 :name "Fred" :age 11})
  ;                             (comp/get-initial-state Person {:id 4 :name "Bobby" :age 55})])})}
  ;  (let [delete-person
  ;        (fn [item-id]
  ;          (comp/transact! this
  ;            [(delete-person {:list id :item item-id})]))]
  ;    ;(dom/div (dom/h4 label)
  ;    ;  (dom/ul (map #(ui-person
  ;    ;                  (comp/computed %
  ;    ;                    {:onDelete delete-person})) people)))
  ;    ))
  ;
  ;(def ui-person-list (comp/factory PersonList))
  ;
  ;;; OPTION 1: 4th arg destructing (requires adding props middleware)
  ;(defsc PageContainer [this props computed {:keys [green]}]
  ;  {:query [:text]
  ;   :initial-state (fn [{:keys [text] :as params}] {:text text})
  ;   :css   [[:.green {:color "green"}]]}
  ;
  ;  ; OPTION 2: Destructure them explicitly
  ;  (let [{:keys [green]} (css/get-classnames PageContainer)]
  ;    ; OPTION 3:
  ;    ; Use `localized-dom` keyword classes instead of `dom` for elements
  ;    ;(dom/div :.green
  ;    ;  (dom/li                                          ;{:classes [green]}
  ;    ;    "bahhh"))
  ;    ))
  ;
  ;(def ui-test-element (comp/factory PageContainer))
  ;
  ;(defsc Root [this {:root/keys [test-element friends enemies]} {:keys [background]}]
  ;  {:query [{:root/test-element (comp/get-query PageContainer)}
  ;           {:root/friends (comp/get-query PersonList)}
  ;           {:root/enemies (comp/get-query PersonList)}]
  ;   :initial-state
  ;          (fn [params]
  ;            {:root/test-element
  ;             (comp/get-initial-state PageContainer)
  ;             :root/friends
  ;             (comp/get-initial-state PersonList
  ;               {:id :friends :label "Friends"})
  ;             :root/enemies
  ;             (comp/get-initial-state PersonList
  ;               {:id :enemies :label "Enemies"})})
  ;   :css   [[:.background
  ;            {:width "100%"
  ;             :height "100%"
  ;             :background-image "url('images/background.png')"
  ;             :background-position "center"
  ;             :background-attachment "fixed"
  ;             :background-repeat "no-repeat"
  ;             :background-size "cover"}]]}
  ;  (let [{:keys [background]} (css/get-classnames Root)]
  ;    ;(dom/div :.background
  ;    ;  (inj/style-element {:component Root})
  ;    ;  (ui-test-element test-element)
  ;    ;  ;(ui-primary-container primary-container)
  ;    ;  ;(ui-sidebar-container sidebar-container)
  ;    ;  (ui-person-list friends)
  ;    ;  (ui-person-list enemies)
  ;    ;  )
  ;    ))