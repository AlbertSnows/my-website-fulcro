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

(defsc LeftSide [this {:left-side/keys [content
                                        classes
                                        ] :as props}]
  {:query [:left-side/content
           :left-side/classes
           ]
   :initial-state
          (fn [{:keys [content classes]}]
            {:left-side/content content
             :left-side/classes classes}
            ;(let [mappy
            ;      (fn [dir]
            ;        {:link (:link dir)
            ;         :id (:id dir)
            ;         :src (:src dir)
            ;         :alt (:alt dir)})]
            ;  {
            ;   ;:left-side/top
            ;   ;(comp/get-initial-state
            ;   ;  TopLeft
            ;   ;  top)
            ;   ;:left-side/bottom
            ;   ;(comp/get-initial-state
            ;   ;  BottomLeft
            ;   ;  bottom)
            ;   })
            )}
  (dom/div {:className classes}
    (ui-image (comp/get-initial-state
                Image
                {:id "left-side-arrow"
                 :alt "point to the right from left"
                 :src "../images/left-side-arrow.PNG"}))
    ;(ui-top-left top)
    ;(ui-bottom-left bottom)
    ))
(def ui-left-side (comp/factory LeftSide))

(defsc Middle [this {:middle/keys [content] :as props}]
  {:query [:middle/content]
   :initial-state
          (fn [{:keys [content]}]
            {:middle/content content})
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
    (dom/div
      ;{:classes [middle-main-page padding-bottom]}
      ;:.general-container
      ;       {
      ;:.general-container
      ;:.middle-main-page
      ;:.padding-bottom
      ;        :className (doall [middle-main-page padding-bottom])
      ;}
      ;"Hello!"
      (ui-image content))))
(def ui-middle (comp/factory Middle {:keyfn :middle/id}))

(defsc RightSide [this {:right-side/keys [
                                          content
                                          classes
                                          ] :as props}]
  {:query [:right-side/content
           :right-side/classes
           ]
   :initial-state
          (fn [{:keys [content classes] :as params}]
            {:right-side/content content
             :right-side/classes classes}
            ;:right-side/top
             ;(comp/get-initial-state TopLeft
             ;                        {:link (:link top)
             ;                         :id (:id top)
             ;                         :src (:src top)
             ;                         :alt (:alt top)})
             ;:right-side/bottom
             ;(comp/get-initial-state BottomLeft
             ;                        {:link (:link bottom)
             ;                         :id (:id bottom)
             ;                         :src (:src bottom)
             ;                         :alt (:alt bottom)})}
            )
   :css
          ;(:css uicss/Timebox)

          [[:.right-side
          {:display "flex";
           :flex-direction "column"
           :align-items "center"
           :padding-right "1.5em"
           :width "100%"}]
         [:.right-side>a+a
          {:padding-top "6em"}]]
   }
  ;(let [{:keys [right-side]} (fcss/get-classnames RightSide)]
    (dom/div {:className classes}
      ;{:classes [right-side]}
      (ui-image
        ;content
        (comp/get-initial-state
          Image
          {:id "right-side-arrow"
           :alt "point to the left from right"
           :src "../images/right-side-arrow.PNG"})
        )
             ;(ui-top-left top)
             ;(ui-bottom-left bottom)
             )
    ;)
  )
(def ui-right-side (comp/factory RightSide))

(defsc Timebox [this {:timebox/keys [id
                                     left
                                     middle
                                     right
                                     ] :as props}]
  {:query [:timebox/id
           {:timebox/left (comp/get-query LeftSide)}
           {:timebox/middle (comp/get-query Middle)}
           {:timebox/right (comp/get-query RightSide)}
           ]
   :ident :timebox/id
   :initial-state
          (fn [{:keys [id left middle right]}]
            {:timebox/id id
             :timebox/left
              (comp/get-initial-state
                LeftSide
                (merge left
                  {:classes "about-left"}))
             :timebox/middle
              (comp/get-initial-state
                Middle middle)
             :timebox/right
              (comp/get-initial-state
                RightSide
                (merge right
                  {:classes "about-right"}))})
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
            {:id "first"
             :middle
             {:content
              (comp/get-initial-state
                Image
                {:id "end-node"
                 :alt "The future is yet to come"
                 :src "../images/end-node.PNG"})}
             :right
              {:content
               (comp/get-initial-state
                 Image
                 {:id "right-side-arrow"
                  :alt "point to the left from right"
                  :src "../images/right-side-arrow.PNG"})}
             }
            )
           (comp/get-initial-state
             Timebox
             {:id "second"
              :middle
                  {:content
                   (comp/get-initial-state
                     Image
                     {:id "middle-node"
                      :alt "arbitrary point in timeline"
                      :src "../images/middle-node.PNG"})}
              :left
              {:content
               (comp/get-initial-state
                 Image
                 {:id "left-side-arrow"
                  :alt "point to the right from left"
                  :src "../images/left-side-arrow.PNG"})}
              }
             )
           (comp/get-initial-state
             Timebox
             {:id "third"
              :middle
                  {:content
                   (comp/get-initial-state
                     Image
                     {:id "end-of-the-road"
                      :alt "end of the node, cowboy"
                      :src "../images/end-of-the-road.PNG"})}
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
    ;   (mapv
    ;     (fn [box]
    ;       (str "item: " box))
    ;     timebox)
       ))

