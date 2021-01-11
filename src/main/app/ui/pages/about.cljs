(ns app.ui.pages.about
	(:require
		[app.ui.helpers.core :as hc
		 :refer [get-first-id div-with-classes-and-id
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
		[app.ui.css :as uicss]))

(defsc Gallery
	[this {:gallery/keys [id photos]}]
	{:ident :gallery/id
	 :query [:gallery/id
					 {:gallery/photos (get-query Href)}]}
	(div {:id (str id "-gallery")}
		(mapv ui-href photos)))
(def ui-gallery (factory Gallery))

(defsc Timebox
	[this {:timebox/keys [id left middle right]}]
	{:ident :timebox/id
	 :css (:css uicss/Timebox)
	 :query [:timebox/id
					 {:timebox/left (get-query Gallery)}
					 {:timebox/middle (get-query Image)}
					 {:timebox/right (get-query Gallery)}]}
	(let [{:keys [timebox]} (get-classnames Timebox)]
		(div {:id (str id "-timebox")}
			(build-left-element id
				(fn [new-id]
					[(ui-image bd/left-arrow)
					 (ui-gallery
						 (update-id-in-data new-id left :gallery/id))]))
			(ui-image middle)
			(build-right-element id
				(fn [new-id]
					[(ui-image bd/right-arrow)
					 (ui-gallery
						 (update-id-in-data new-id right :gallery/id))])))))
(def ui-timebox (factory Timebox))

(defsc About
	[this {:about/keys [timebox]}]
	{:ident (fn [] [:component/id :about])
	 :query [{:about/timebox (get-query Timebox)}]
	 :route-segment ["about"]
	 :initial-state (fn [_]
										{:about/timebox [(bd/get-timebox 6)]})}
	(div {:id "about"} (mapv ui-timebox timebox)))

