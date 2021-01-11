(ns app.ui.pages.about
	(:require
		[app.ui.helpers.core :as hc
		 :refer [  get-first-id
						 div-with-classes-and-id
						 add-id-to-components  build-left-element
						 build-right-element update-id-in-data]]
		[app.ui.components :as c
		 :refer [ui-image Image Left Middle Right ui-left
						 ui-middle ui-right ui-href Href]]
		[com.fulcrologic.fulcro.components :as comp
		 :refer [defsc factory get-query get-initial-state]]
		[com.fulcrologic.fulcro-css.localized-dom :as dom
		 :refer [div]]
		[com.fulcrologic.fulcro-css.css-injection :as inj
		 :refer [style-element]]
		[com.fulcrologic.fulcro-css.css :as css
		 :refer [get-classnames]]
		[lab.bleeding :as b]
		[app.backend.data :as bd]
		[app.backend.helpers.core :as bhc]
		[app.ui.css :as uicss]
		))

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









(def ex
	{2 {:href/link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
			:href/image {:image/id  "twbb"
									 :image/src "../images/collapse.webp"
									 :image/alt "There Will Be Blood"}}
	 1 {:href/link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
			:href/image {:image/id  "twbb"
									 :image/src "../images/clpping.webp"
									 :image/alt "There Will Be Blood"}}})
(defn get-href [id]
	(assoc (get ex id) :href/id id))

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







(defsc Gallery
	[this {:gallery/keys [id photos]}]
	{:ident :gallery/id
	 :query [:gallery/id
					 {:gallery/photos (get-query Href)}]}
	(div {:id (str id "-gallery")}
		(mapv ui-href photos)))
(def ui-gallery (factory Gallery))

(defsc Timebox
	[this {:timebox/keys [id
												left
												middle
												right
												]}]
	{:ident :timebox/id
	 :query [:timebox/id
					 {:timebox/left (get-query Gallery)}
					 {:timebox/middle (get-query Image)}
					 {:timebox/right (get-query Gallery)}
					 ]}
	(div {:id (str id "-timebox")}
		;surround
		(ui-gallery left)
		(ui-image middle)
		;surround
		(ui-gallery right)
		))
(def ui-timebox (factory Timebox))

(defsc About
	[this {:about/keys [timebox]}]
	{:ident (fn [] [:component/id :about])
	 :query [{:about/timebox (get-query Timebox)}]
	 :route-segment ["about"]
	 :initial-state (fn [_] {:about/timebox
													 [                                             ;initial timebox

														]})}
	(div {:id "about"}
		(mapv ui-timebox timebox)))

;(defsc Timebox [this {:timebox/keys [id left middle right]}]
;	{:query
;					[:timebox/id
;					 :ui/fetch-state
;					 {:timebox/left (get-query Gallery)}
;					 {:timebox/middle (get-query Image)}
;					 {:timebox/right (get-query Gallery)}
;					 ]
;	 :ident [:timebox/by-id :timebox/id]
;	 :css   (:css uicss/Timebox)}
;	(let [{:keys [timebox]} (get-classnames Timebox)]
;		(div {:classes [timebox]
;					:id      id}
;			(print "hello test")
;			(print "id: " id)
;			(print "l: " left)
;			(print "m: " middle)
;			(print "r: " right)
;			(build-left-element id
;				(fn [new-id]
;					[(ui-image bd/left-arrow)
;					 (ui-gallery
;						(update-id-in-data new-id left :gallery/id))]))
;			(ui-image middle)
;			(build-right-element id
;				(fn [new-id]
;					[(ui-image bd/right-arrow)
;					 (ui-gallery
;						(update-id-in-data new-id right :gallery/id))])))))
;(def ui-timebox (factory Timebox {:keyfn :timebox/id}))
;
;

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

