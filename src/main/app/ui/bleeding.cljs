(ns app.ui.bleeding
  (:require
    [taoensso.timbre :as log]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr :refer [defrouter]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p h4 ul a img]]
    [com.fulcrologic.fulcro-css.css :as fcss]
    [app.ui.css :as uicss]
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

(defsc LeftSide [this {:left-side/keys [
                                        ;top
                                        ;bottom
                                        ] :as props}]
  {:query [
           ;{:left-side/top (comp/get-query TopLeft)}
           ;{:left-side/bottom (comp/get-query BottomLeft)}
           ]
   :initial-state
          (fn [{:keys [top bottom]}]
            (let [mappy
                  (fn [dir]
                    {:link (:link dir)
                     :id (:id dir)
                     :src (:src dir)
                     :alt (:alt dir)})]
              {
               ;:left-side/top
               ;(comp/get-initial-state
               ;  TopLeft
               ;  top)
               ;:left-side/bottom
               ;(comp/get-initial-state
               ;  BottomLeft
               ;  bottom)
               }))}
  (dom/div
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
      (ui-image content))))
(def ui-middle (comp/factory Middle {:keyfn :middle/id}))

(defsc RightSide [this {:right-side/keys [
                                          ;top
                                          ;bottom
                                          ] :as props}]
  {:query [
           ;{:right-side/top (comp/get-query TopLeft)}
           ;{:right-side/bottom (comp/get-query BottomLeft)}
           ]
   :initial-state
          (fn [{:keys [top bottom] :as params}]
            {
             }
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
   :css [[:.right-side
          {:display "flex";
           :flex-direction "column"
           :align-items "center"
           :padding-right "1.5em"
           :width "100%"}]
         [:.right-side>a+a
          {:padding-top "6em"}]]}
  (let [{:keys [right-side]} (fcss/get-classnames RightSide)]
    (dom/div {:classes [right-side]}
             ;(ui-top-left top)
             ;(ui-bottom-left bottom)
             )))
(def ui-right-side (comp/factory RightSide))

(defsc Timebox [this {:timebox/keys [id
                                     ;left
                                     middle
                                     ;right
                                     ] :as props}]
  {:query [:timebox/id
           ;{:timebox/left (comp/get-query LeftSide)}
           {:timebox/middle (comp/get-query Middle)}
           ;{:timebox/right (comp/get-query RightSide)}
           ]
   :ident :timebox/id
   :initial-state
          (fn [{:keys [id left middle right]}]
            {:timebox/left
              (comp/get-initial-state
                LeftSide
                {:content
                 []})
             :timebox/middle
              (comp/get-initial-state
                Middle
                {:content
                 (comp/get-initial-state
                    Image
                    {:id "end-node"
                     :alt "The future is yet to come"
                     :src "../images/end-node.PNG"})
                  })
             :timebox/right
              (comp/get-initial-state
                RightSide
                {:content
                 []})

             })}
  (div
    ;(ui-left-side left)
    (ui-middle middle)
    ;(ui-right-side right)
    ))
(def ui-timebox (comp/factory Timebox))

(defsc About [this {:about/keys [timebox] :as props}]
  {:ident (fn [] [:component/id :about])
   :route-segment ["about"]
   :query [{:about/timebox (comp/get-query Timebox)}]
   :initial-state
     (fn [_]
       [(comp/get-initial-state
          Timebox)]
       )}
  (div {:id "project-page-body"}
    (mapv ui-timebox timebox)))

