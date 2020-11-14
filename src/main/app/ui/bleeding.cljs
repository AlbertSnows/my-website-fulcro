(ns app.ui.bleeding
  (:require
    [taoensso.timbre :as log]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr :refer [defrouter]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p h4 ul a img]]
    [com.fulcrologic.fulcro-css.css :as css]
    [app.ui.css :as uicss]
    [com.fulcrologic.fulcro-css.css-injection :as inj]))

(defsc Image [this {:image/keys [id src alt]}]
  {:query [:image/id :image/src :image/alt]
   :initial-state
          (fn [{:keys [src id alt]}]
            {:image/src src
             :image/id  (str id "-img")
             :image/alt alt})}
  (img {:src src :id id :alt alt}))
(def ui-image (comp/factory Image {:keyfn :image/id}))

(defsc Href [this {:href/keys [id link image]}]
  {:query [:href/id
           :href/link
           {:href/image (comp/get-query Image)}]
   :ident :href/id
   :initial-state
          (fn [{:keys [id link src alt]}]
            {:href/id   (str id "-href")
             :href/link link
             :href/image
                        (comp/get-initial-state
                          Image
                          {:src src
                           :id  id
                           :alt alt})})}
  (a {:href   link
      :target "__blank"
      :rel    "noopener noreferrer"}
     (ui-image image)))
(def ui-href (comp/factory Href {:keyfn :href/id}))
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



(comp/get-initial-state
  LeftSide
  {:content
   [{:ui ui-top
     :state
      (comp/get-initial-state
        Top
        {:ui ui-href
         :state
          (comp/get-initial-state
            Href
            {:link "https://en.wikipedia.org/wiki/Gaming"
             :id   "gamin"
             :src  "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
             :alt  "I play games I KNOW I'M SORRY"}
                                  )
         }
        {:ui ui-href
         :factory Href
         :state
         {:link "https://en.wikipedia.org/wiki/Gaming"
          :id   "gamin"
          :src  "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
          :alt  "I play games I KNOW I'M SORRY"}}
                              )}
    {:ui ui-top
     :factory Top
     :state
     {:ui ui-href
      :factory Href
      :state
      {:link "https://en.wikipedia.org/wiki/Gaming"
       :id   "gamin"
       :src  "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
       :alt  "I play games I KNOW I'M SORRY"}}}
    {:ui ui-bottom
     :factory Bottom
     :state
     {:ui ui-href
      :factory Href
      :state
      {:link "https://www.whatisitliketobeaphilosopher.com/"
       :id   "pho"
       :src  "../images/the-thinker.png"
       :alt  "But really, what even IS a rock anyways???"}}}]})