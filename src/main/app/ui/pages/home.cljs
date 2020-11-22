(ns app.ui.pages.home
  (:require
    [app.ui.components :as c
     :refer [Left ui-top Top ui-href Href ui-bottom Bottom
             Middle ui-text Text Right ui-left ui-middle ui-right]]
    [com.fulcrologic.fulcro.components :as comp
     :refer [defsc factory get-query get-initial-state]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div]]))

(def home-initial-state
  {:home/left
   (get-initial-state
     Left
     {:id "home"
      :data
          [{:ui      ui-top
            :factory Top
            :data    [{:ui      ui-href
                       :factory Href
                       :data    {:link  "https://en.wikipedia.org/wiki/Gaming"
                                 :image {:id  "gamin"
                                         :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
                                         :alt "I play games I KNOW I'M SORRY"}}}]}
           {:ui      ui-bottom
            :factory Bottom
            :data    [{:ui      ui-href
                       :factory Href
                       :data    {:link  "https://www.whatisitliketobeaphilosopher.com/"
                                 :image {:id  "pho"
                                         :src "../images/the-thinker.png"
                                         :alt "But really, what even IS a rock anyways???"}}}]}]})
   :home/middle
   (get-initial-state
     Middle
     {:id "home"
      :data
          [{:ui      ui-text
            :factory Text
            :data    {:id   "main"
                      :data "Mostly this stuff"}}
           {:ui      ui-text
            :factory Text
            :data    {:id   "minor"
                      :data "(check out my projects for novel things)"}}]})
   :home/right
   (get-initial-state
     Right
     {:id "home"
      :data
          [{:ui      ui-top
            :factory Top
            :data    [{:ui      ui-href
                       :factory Href
                       :data    {:link  "https://www.youtube.com/"
                                 :image {:id  "Tube"
                                         :src "../images/tubes.png"
                                         :alt "Youtube is my Netflix, sadly"}}}]}
           {:ui      ui-bottom
            :factory Bottom
            :data    [{:ui      ui-href
                       :factory Href
                       :data    {:link  "https://en.wikipedia.org/wiki/Programmer"
                                 :image {:id  "debug"
                                         :src "../images/meirl.png"
                                         :alt "g! 'How to print newline in cljs'"}}}]}]})})

(defsc Home [this {:home/keys [left middle right]}]
  {:query         [{:home/left (get-query Left)}
                   {:home/middle (get-query Middle)}
                   {:home/right (get-query Right)}]
   :route-segment ["home"]
   :ident         (fn [] [:component/id :home])
   :initial-state (fn [_] home-initial-state)}
  (div (ui-left left)
       (ui-middle middle)
       (ui-right right)))