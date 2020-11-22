(ns app.ui.pages.about
  (:require
    [app.ui.components :as c
     :refer [ui-image Image Left Middle Right ui-left
             ui-middle ui-right]]
    [com.fulcrologic.fulcro.components :as comp
     :refer [defsc factory get-query get-initial-state]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div]]
    [com.fulcrologic.fulcro-css.css-injection :as inj
     :refer [style-element]]
    [com.fulcrologic.fulcro-css.css :as css
     :refer [get-classnames]]
    [app.ui.css :as uicss]))

(defsc Gallery
  [this {:gallery/keys [photos id] :as props}]
  {:id    :gallery/id
   :query [:gallery/photos :gallery/id]
   :initial-state
          (fn [gallery]
            {:gallery/photos gallery
             :gallery/id     (:id (first gallery))})}
  (div {:id        (str id "-gallery")
        :className "gallery"}
       (mapv
         (fn [photo]
           (ui-image (get-initial-state Image photo)))
         photos)))
(def ui-gallery (factory Gallery {:keyfn :gallery/id}))



(def node-options
  {:first
   {:id  "end-node"
    :alt "The future is yet to come"
    :src "../images/end-node.PNG"}
   :middle
   {:id  "middle-node"
    :alt "arbitrary point in timeline"
    :src "../images/middle-node.PNG"}
   :end
   {:id  "end-of-the-road"
    :alt "end of the node, cowboy"
    :src "../images/end-of-the-road.PNG"}})

(defsc Timebox [this {:timebox/keys [id left middle right]}]
  {:query
          [:timebox/id
           {:timebox/left (get-query Left)}
           {:timebox/middle (get-query Middle)}
           {:timebox/right (get-query Right)}]
   :ident :timebox/id
   :initial-state
          (fn [{:keys [id left middle right]}]
            {:timebox/id id
             :timebox/left
                         (get-initial-state
                           Left {:id id :data left})
             :timebox/middle
                         (get-initial-state
                           Middle {:id id :data middle})
             :timebox/right
                         (get-initial-state
                           Right {:id id :data right})})
   :css   (:css uicss/Timebox)}
  (let [{:keys [timebox]} (get-classnames Timebox)]
    (div {:classes [timebox]
          :id      id}
         (ui-left left)
         (ui-middle middle)
         (ui-right right))))
(def ui-timebox (factory Timebox {:keyfn :timebox/id}))

(def about-initial-state
  {:about/timebox
   [(get-initial-state
      Timebox
      {:id     "first"
       :left   [{:id      "gallery1"
                 :ui      ui-gallery
                 :factory Gallery
                 :data    [{:id  "paycom"
                            :src "../images/paycom.PNG"
                            :alt "I work here rn"}
                           {:id  "okcity"
                            :src "../images/okcity.PNG"
                            :alt "I live here rn"}]}
                {:id      "arrow1"
                 :ui      ui-image
                 :factory Image
                 :data    {:id  "pr"
                           :alt "point to the right from left"
                           :src "../images/left-side-arrow.PNG"}}]
       :middle [{:ui      ui-image
                 :factory Image
                 :data    (get node-options :first)}]
       :right  [{:id      "gallery2"
                 :ui      ui-gallery
                 :factory Gallery
                 :data    [{:id  "twbb"
                            :src "../images/twbb.jpg"
                            :alt "There Will Be Blood"}]}
                {:id      "arrow2"
                 :ui      ui-image
                 :factory Image
                 :data    {:id  "pl"
                           :alt "point to the left from the right"
                           :src "../images/right-side-arrow.PNG"}}]})
    (get-initial-state
      Timebox
      {:id     "second"
       :middle [{:ui      ui-image
                 :factory Image
                 :data    (get node-options :middle)}]})
    (get-initial-state
      Timebox
      {:id     "third"
       :middle [{:ui      ui-image
                 :factory Image
                 :data    (get node-options :end)}]})]})
(defsc About [this {:about/keys [timebox] :as props}]
  {:ident         (fn [] [:component/id :about])
   :route-segment ["about"]
   :query         [{:about/timebox (get-query Timebox)}]
   :initial-state (fn [_] about-initial-state)}
  (div {:id "about-page-body"}
       (style-element {:component Timebox})
       (mapv ui-timebox timebox)))