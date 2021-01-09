(ns app.ui.components
  (:require
    [app.ui.helpers.core :as hc
     :refer [add-id append-id-to-components get-first-id
             div-with-classes-and-id apply-contained-component]]
    [com.fulcrologic.fulcro.components :as comp
     :refer [defsc factory get-query get-initial-state]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div img a p]]
    [com.fulcrologic.fulcro.ui-state-machines :as uism
     :refer [defstatemachine]]
    [com.fulcrologic.fulcro-css.css :as css
     :refer [get-classnames]]
    [taoensso.timbre :as log]
    [app.ui.css :as uicss]))

(defsc Image [this {:image/keys [id src alt]}]
  {:query [:image/id :image/src :image/alt]
   :initial-state
          (fn [{:keys [src id alt]}]
            {:image/src src
             :image/id  (str id "-img")
             :image/alt alt})}
  (img {:id id :src src :alt alt}))
(def ui-image (factory Image {:keyfn :image/id}))

(defsc Href [this {:href/keys [id link image]}]
  {:query [:href/id
           :href/link
           {:href/image (get-query Image)}]
   :ident :href/id
   :initial-state
          (fn [{:keys [id link image]}]
            {:href/id   (str id "-href-" (:id image))
             :href/link link
             :href/image
                        (get-initial-state
                          Image
                          (merge
                            image
                            {:id (str id "-href-" (:id image))}))})}
  (a {:id     id
      :href   link
      :target "__blank"
      :rel    "noopener noreferrer"
      :className "href"}
    (ui-image image)))
(def ui-href (factory Href {:keyfn :href/id}))

(defsc ContainerHeader [this {:container-header/keys [id route] :as props}]
  {:query         [:container-header/id
                   :container-header/route
                   [::uism/asm-id ::RootRouter]]
   :ident         :container-header/id
   :initial-state (fn [{:container-header/keys [id route] :as params}]
                    {:container-header/id    id
                     :container-header/route route})
   :css           [[:.outer-text
                    {:font-size      "2em"
                     :color          "white"
                     :text-align     "center"
                     :font-family    "MINIMAL"
                     :margin         "0 auto"
                     :padding        "0 auto"
                     :vertical-align "top"}]]}
  (let [{:keys [outer-text]} (get-classnames ContainerHeader)]
    (p {:classes [outer-text]}
      (case route
        "home" "What Am I Up To?"
        "about" "About Me"
        "projects" "Stuff I've Made"
        "contact" "Reach Out"
        ""))))
(def ui-container-header (factory ContainerHeader))

(defsc Text [this {:text/keys [id text]}]
  {:query         [:text/id :text/text]
   :initial-state (fn [{:keys [id data]}]
                    {:text/id   (str id "-text")
                     :text/text data})}
  (p {:id id} text))
(def ui-text (factory Text {:keyfn :text/id}))

(defsc Top
  [this {:top/keys [components id]}]
  {:ident :top/id
   :query [:top/components :top/id]
   :initial-state
          (fn [components]
            {:top/components
                     (append-id-to-components
                       "top"
                       components)
             :top/id (str (get-first-id components) "-top")})}
  (div-with-classes-and-id
    id
    "top-div"
    (mapv apply-contained-component components)))
(def ui-top (factory Top {:keyfn :top/id}))

(defsc Bottom
  [this {:bottom/keys [components id]}]
  {:ident :bottom/id
   :query [:bottom/components :bottom/id]
   :initial-state
          (fn [components]
            {:bottom/components
                        (append-id-to-components
                          "bottom"
                          components)
             :bottom/id (str (get-first-id components) "-bottom")})}
  (div-with-classes-and-id
    id
    "bottom-div"
    (mapv apply-contained-component components)))
(def ui-bottom (factory Bottom {:keyfn :bottom/id}))

(defsc Left
  [this {:left/keys [components id]}]
  {:query [:left/components :left/id]
   :initial-state
          (fn [{:keys [id data]}]
            {:left/id (str id "-left")
             :left/components
                      (append-id-to-components (str id "-left") data)})}
  (div-with-classes-and-id
    id
    "left-side"
    (mapv apply-contained-component components)))
(def ui-left (factory Left {:keyfn :left/id}))

(defsc Middle [this {:middle/keys [id components] :as props}]
  {:ident :middle/id
   :query [:middle/id :middle/components]
   :initial-state
          (fn [{:keys [id data]}]
            {:middle/id (str id "-middle")
             :middle/components
                        (append-id-to-components
                          (str id "-middle") data)})
   :css   (:css uicss/Middle)}
  (let [{:keys [middle-main-page padding-bottom]} (get-classnames Middle)]
    (div-with-classes-and-id
      id
      "middle"
      (mapv apply-contained-component components))))
(def ui-middle (factory Middle {:keyfn :middle/id}))

(defsc Right
  [this {:right/keys [components id] :as props}]
  {:query [:right/components :right/id]
   :initial-state
          (fn [{:keys [id data]}]
            {:right/id (str id "-right")
             :right/components
                       (append-id-to-components (str id "-right") data)})
   :css   [[:.right
            {:display        "flex"                         ;
             :flex-direction "column"
             :align-items    "center"
             :padding-right  "1.5em"
             :width          "100%"}]
           [:.right>a+a
            {:padding-top "6em"}]]}
  (div-with-classes-and-id
    id
    "right-side"
    (mapv apply-contained-component components)))
(def ui-right (factory Right {:keyfn :right/id}))
