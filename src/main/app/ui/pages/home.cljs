(ns app.ui.pages.home
  (:require
    [app.ui.helpers.core :as hc
     :refer [get-first-id div-with-classes-and-id
             add-id-to-components  build-left-element
             build-right-element update-id-in-data
             append-id]]
    [app.ui.components :as c
     :refer [Left ui-top Top ui-href Href ui-bottom Bottom
             Middle ui-text Text Right ui-left ui-middle ui-right
             build-href]]
    [com.fulcrologic.fulcro.components :as comp
     :refer [defsc factory get-query get-initial-state]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div]]
    [lab.bleeding :as b]))

(defsc Home [this props]
  {:query         []
   :route-segment ["home"]
   :ident         (fn [] [:component/id :home])}
  (div {:id "home"}
    (div-with-classes-and-id "home-left" "home-left-side"
      [(ui-href
         (build-href
           (append-id "home-left"
             {:href/id "pho"
              :href/link  "https://www.youtube.com/watch?v=Et6itTuJSYY"
              :href/image {:image/src "../images/the-thinker.png"
                           :image/alt "But really, what even IS a rock anyways???"}}
             :href/id)))
       (ui-href
         (build-href
           (append-id "home-left"
             {:href/id "gamin"
              :href/link  "https://www.youtube.com/watch?v=DSJvCffJCzE"
              :href/image {:image/src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
                           :image/alt "I play games I KNOW I'M SORRY"}}
             :href/id)))])
    (div-with-classes-and-id "home-middle" "middle"
      [(ui-text {:text/id   "main"
                 :text/text "Mostly this stuff"})
       (ui-text {:text/id   "minor"
                 :text/text "(check out my projects for novel things)"})])
    (div-with-classes-and-id "home-right" "home-right-side"
      [(ui-href
         (build-href
           (append-id "home-right"
             {:href/id "Tube"
              :href/link  "https://www.youtube.com/"
              :href/image {:image/src "../images/tubes.png"
                           :image/alt "Youtube is my Netflix, sadly"}}
             :href/id)))
       (ui-href
         (build-href
           (append-id "home-right"
             {:href/id "debug"
              :href/link  "https://www.youtube.com/watch?v=HluANRwPyNo"
              :href/image {:image/src "../images/meirl.png"
                           :image/alt "g! 'How to print newline in cljs'"}}
             :href/id)))])))
