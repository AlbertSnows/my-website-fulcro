(ns app.ui.pages.about
  (:require
    [app.ui.helpers.core :as hc
     :refer [div-with-classes-and-id
             build-left-element
             build-right-element update-id-in-data]]
    [app.ui.components :as c
     :refer [ui-image Image ui-href Href]]
    [com.fulcrologic.fulcro.components :as comp
     :refer [defsc factory get-query get-initial-state]]
    [com.fulcrologic.fulcro.dom :as dom
     :refer [div button p br]]
    [com.fulcrologic.fulcro-css.css-injection :as inj
     :refer [style-element]]
    [com.fulcrologic.fulcro-css.css :as css
     :refer [get-classnames]]
    [lab.bleeding :as b]
    [app.backend.data :as bd]
    [app.backend.helpers.core :as bhc]
    [app.ui.css :as uicss]
    [app.ui.mutations :as m]))

(defsc Gallery
  [this {:gallery/keys [id photos]}]
  {:ident :gallery/id
   :query [:gallery/id
           {:gallery/photos (get-query Href)}]}
  (div {:id        (str id "-gallery")
        :className "gallery"}
       (mapv ui-href photos)))
(def ui-gallery (factory Gallery {:keyfn :gallery/id}))

(defsc Timebox
  [this {:timebox/keys [id left middle right]}]
  {:ident :timebox/id
   :css   (:css uicss/Timebox)
   :query [:timebox/id
           {:timebox/left (get-query Gallery)}
           {:timebox/middle (get-query Image)}
           {:timebox/right (get-query Gallery)}]}
  (let [{:keys [timebox]} (get-classnames Timebox)]
    (div {:id      (str id "-timebox")
          :classes [timebox]}
         (build-left-element
           id
           (fn [new-id]
             [(ui-image bd/left-arrow)
              (ui-gallery
                (update-id-in-data new-id left :gallery/id))]))
         (ui-image middle)
         (build-right-element
           id
           (fn [new-id]
             [(ui-image bd/right-arrow)
              (ui-gallery
                (update-id-in-data new-id right :gallery/id))])))))
(def ui-timebox (factory Timebox {:keyfn :timebox/id}))

(defsc About
  [this {:about/keys [timebox]}]
  {:ident         (fn [] [:component/id :about])
   :query         [{:about/timebox (get-query Timebox)}]
   :route-segment ["about"]
   :initial-state (fn [_] {:about/timebox
                           [(bd/get-timebox 8)
                            (bd/get-timebox 7)
                            (bd/get-timebox 6)]})}
  (let [last-loaded-timebox-id (:timebox/id (last timebox))]
    (div {:id "about"}
         (div {:id "explination-box"}
              (div {:id "explination-header" :className "text"
                    :onClick (fn [e] (js/console.log "clicky"))} (p "What's this?"))
              (div {:id "explination-body" :className "text explination-body-closed"}
                   (p "About Me pages are tough." (br)
                      "It's hard to meaningfully encapsulate the kind of person someone is in a few short paragraphs." (br)
                      "Instead I decided to make a timeline of various people, places, events, and things I've experienced in my life." (br)
                      "Each bubble represents something that I spent time with." (br)
                      "More importanly, each bubble represents something that I valued my time with; enough to put on this website, anyways." (br)
                      "I figure that'll tell a lot more about me than any amount of words could accomplish." (br)
                      "If you see something that sticks out to you, tell me about it!")))
         (mapv ui-timebox timebox)
         (when (> last-loaded-timebox-id 1)
           (div {:id "load-more"
                 :onClick (m/load-next-timebox this last-loaded-timebox-id Timebox)}
                (p "V")
                (p "V"))))))

