(ns app.ui.components
  (:require
    [app.ui.helpers.core :as hc
     :refer [div-with-classes-and-id
             add-id]]
    [com.fulcrologic.fulcro.components :as comp
     :refer [defsc factory get-query get-initial-state]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div img a p]]
    [com.fulcrologic.fulcro.ui-state-machines :as uism
     :refer [defstatemachine]]
    [com.fulcrologic.fulcro-css.css :as css
     :refer [get-classnames]]
    [taoensso.timbre :as log]
    [app.ui.css :as uicss]
    [app.backend.helpers.core :as bhc]))

(defsc Image [this {:image/keys [id src alt]}]
  {:query [:image/id :image/src :image/alt]}
  (img {:id id :src src :alt alt}))
(def ui-image (factory Image {:keyfn :image/id}))
(defn build-image [{:image/keys [id alt src]}]
  {:image/id  (str id "-img")
   :image/alt alt
   :image/src src})

(defsc Href [this {:href/keys [id link image]}]
  {:query [:href/id
           :href/link
           {:href/image (get-query Image)}]
   :ident :href/id}
  (a {:id        id
      :href      link
      :target    "__blank"
      :rel       "noopener noreferrer"
      :className "href"}
     (ui-image image)))
(def ui-href (factory Href {:keyfn :href/id}))
(defn build-href [{:href/keys [id link image]}]
  {:href/id    (str id "-href")
   :href/link  link
   :href/image (build-image (add-id (str id "-href") image :image/id))})

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
         "about" "My Vague Timeline"
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

