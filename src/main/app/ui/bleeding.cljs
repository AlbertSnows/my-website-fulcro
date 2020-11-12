(ns app.ui.bleeding
  (:require
    [taoensso.timbre :as log]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr :refer [defrouter]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p h4 ul a img]]
    [com.fulcrologic.fulcro-css.css :as fcss]
    [app.ui.css :as uicss]
    [com.fulcrologic.fulcro-css.css-injection :as inj]

    ))

(defsc Image [this {:image/keys [id src alt]}]
  {:query [:image/id :image/src :image/alt]
   :initial-state
          (fn [{:keys [src id alt]}]
            {:image/src src
             :image/id (str id "-img")
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
            {:href/id (str id "-href")
             :href/link link
             :href/image
                      (comp/get-initial-state
                        Image
                        {:src src
                         :id id
                         :alt alt})})}
  (a {:href link
      :target "__blank"
      :rel "noopener noreferrer"}
     (ui-image image)))
(def ui-href (comp/factory Href {:keyfn :href/id}))

(defsc Gallery
  [this {:gallery/keys [image-list] :as props}]
  {:query [:gallery/image-list]
   :initial-state (fn [gallery] {:gallery/image-list gallery})}
  (dom/div {:className "gallery"}
    (mapv ui-image image-list)))
(def ui-gallery (comp/factory Gallery))

(defsc AboutLeftSide [this {:left-side/keys   [
                                        gallery
                                        ] :as props}]
  {:query [:left-side/gallery]
   :initial-state
          (fn [{:keys [gallery]}]
            {:left-side/gallery
             (comp/get-initial-state Gallery gallery)}
            )}
  (dom/div {:className "about-left"}
    (ui-gallery gallery)
     (dom/div
       (ui-image
         (comp/get-initial-state
           Image
           {:id "left-side-arrow"
            :alt "point to the right from left"
            :src "../images/left-side-arrow.PNG"}))
             )))
(def ui-left-side (comp/factory AboutLeftSide))

(def node-options
  {:first
   {:id "end-node"
    :alt "The future is yet to come"
    :src "../images/end-node.PNG"}
   :middle
   {:id "middle-node"
    :alt "arbitrary point in timeline"
    :src "../images/middle-node.PNG"}
   :end
   {:id "end-of-the-road"
    :alt "end of the node, cowboy"
    :src "../images/end-of-the-road.PNG"}})
(defsc Middle [this {:middle/keys [id] :as props}]
  {:query [:middle/id]
   :ident :middle/id
   :initial-state
          (fn [{:keys [id]}]
            {:middle/id id})
   :css [[:.middle-main-page
          {:display "flex"
           :flex-direction "column"
           :font-size "4vw"
           :margin "0 auto"
           :justify-content "center"
           :min-width "6em"
           :height "auto"}]
         [:.padding-bottom
          {:padding-bottom "1em"}]
         [:.enlarge-text
          {:font-size "larger"
           :overflow "hidden"}]
         [:.small-text
          {:font-size "initial"
           :margin "0 auto"
           :text-align "center"}]]}
  (let [{:keys [middle-main-page padding-bottom]} (fcss/get-classnames Middle)]
    (log/info "Id: " id)
    (log/info "map thing: " (get node-options id))
    (dom/div
      (ui-image
        (comp/get-initial-state Image
          (get node-options id))))))
(def ui-middle (comp/factory Middle {:keyfn :middle/id}))

(defsc RightSide [this {:right-side/keys [gallery] :as props}]
  {:query [:right-side/gallery
           ]
   :initial-state
          (fn [{:keys [gallery] :as params}]
            {:right-side/gallery
             (comp/get-initial-state Gallery gallery)}
            )
   :css
          [[:.right-side
          {:display "flex";
           :flex-direction "column"
           :align-items "center"
           :padding-right "1.5em"
           :width "100%"}]
         [:.right-side>a+a
          {:padding-top "6em"}]]
   }
  (dom/div
    (dom/div {:className "right-side"}
             (ui-image
               (comp/get-initial-state
                 Image
                 {:id "right-side-arrow"
                  :alt "point to the left from right"
                  :src "../images/right-side-arrow.PNG"})
               ))

    (ui-gallery gallery)
    )
  )
(def ui-right-side (comp/factory RightSide))

(defsc Timebox [this {:timebox/keys [id
                                     left
                                     middle
                                     right
                                     ] :as props}]
  {:query [:timebox/id
           {:timebox/left (comp/get-query AboutLeftSide)}
           {:timebox/middle (comp/get-query Middle)}
           {:timebox/right (comp/get-query RightSide)}
           ]
   :ident :timebox/id
   :initial-state
    (fn [{:keys [id left middle right]}]
      {:timebox/id id
       :timebox/left
        (comp/get-initial-state
          AboutLeftSide
          left)
       :timebox/middle
        (comp/get-initial-state
          Middle middle)
       :timebox/right
        (comp/get-initial-state
          RightSide
          right
          )})
   :css (:css uicss/Timebox)}
  (let [{:keys [timebox]} (fcss/get-classnames Timebox)]
    (div {:classes [timebox]}
      (ui-left-side left)
      (ui-middle middle)
      (ui-right-side right)
      )))
(def ui-timebox (comp/factory Timebox))

(defsc About [this {:about/keys [timebox] :as props}]
  {:ident (fn [] [:component/id :about])
   :route-segment ["about"]
   :query [{:about/timebox (comp/get-query Timebox)}]
   :initial-state
     (fn [_]
       {:about/timebox
          [(comp/get-initial-state
            Timebox
            {:id "first-box"
             :left
               {:gallery
                [{:id "paycom"
                  :src "../images/end-node.PNG"
                  :alt "I work here rn"}]}
             :middle {:id :first}
             :right
              {:gallery
               [{:id "okcity"
                 :src "../images/okcity.PNG"
                 :alt "I live here rn"}]}
             }
            )
           (comp/get-initial-state
             Timebox
             {:id "second"
              :middle {:id :middle}
              }
             )
           (comp/get-initial-state
             Timebox
             {:id "third"
              :middle {:id :end}
              }
             )
           ]
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
        }
       )}
  (div {:id "project-page-body"}
       (inj/style-element {:component Timebox})
       (mapv ui-timebox timebox)
       ))

