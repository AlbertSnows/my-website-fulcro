(ns app.ui.pages.home
  (:require
    [app.ui.components :as c
     :refer [Left ui-top Top ui-href Href ui-bottom Bottom
             Middle ui-text Text Right ui-left ui-middle ui-right]]
    [com.fulcrologic.fulcro.components :as comp
     :refer [defsc factory get-query get-initial-state]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div]]
    [lab.bleeding :as b]))

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
                       :data    {:link  "https://www.youtube.com/watch?v=DSJvCffJCzE"
                                 :image {:id  "gamin"
                                         :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
                                         :alt "I play games I KNOW I'M SORRY"}}}]}
           {:ui      ui-bottom
            :factory Bottom
            :data    [{:ui      ui-href
                       :factory Href
                       :data    {:link  "https://www.youtube.com/watch?v=Et6itTuJSYY"
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
                       :data    {:link  "https://www.youtube.com/watch?v=HluANRwPyNo"
                                 :image {:id  "debug"
                                         :src "../images/meirl.png"
                                         :alt "g! 'How to print newline in cljs'"}}}]}]})})
;
;(defsc Home [this {:home/keys [left middle right]}]
;  {:query         [{:home/left (get-query Left)}
;                   {:home/middle (get-query Middle)}
;                   {:home/right (get-query Right)}]
;   :route-segment ["home"]
;   :ident         (fn [] [:component/id :home])
;   :initial-state (fn [_] home-initial-state)}
;  (div {:id "home" :style "font-color: white"}
;       (ui-left left)
;       (ui-middle middle)
;       (ui-right right)
;    ))

(defsc LargeList [this {:keys [list/current-page]}]
  {:initial-state (fn [params]
                    {:list/current-page (comp/get-initial-state b/ListPage {})})
   :query [{:list/current-page (comp/get-query b/ListPage)}]
   :ident (fn [] [:list/by-id 1])}
  (let [{:keys [page/number]} current-page]
    (dom/div
      (dom/button
        {:disabled (= 1 number)
         :onClick #(comp/transact! this
                     [(b/goto-page {:page-number (dec number)})])}
        "Prior Page")
      (dom/button
        {:onClick #(comp/transact! this
                     [(b/goto-page {:page-number (inc number)})])}
        "next page")
      (b/ui-list-page current-page))))

(def ui-list (comp/factory LargeList))

(defsc Home [this {:keys [pagination/list]}]
  {:query         [{:pagination/list (comp/get-query LargeList)}]
   :route-segment ["home"]
   :ident         (fn [] [:component/id :home])
   :initial-state (fn [params]
                    {:pagination/list (comp/get-initial-state LargeList {})})}
  (let [{:keys [page/number]} (:current-page list)]
    (div {:id "home"}
      (ui-list list))))

;In terms of implementation, you would just need to create an event listener, that listens for the scrolling of the window or body. Then, you just need to check if they are at the bottom of the window / body, and call a function that updates the state of the component, when that happens. The function would call the remote to fetch more items.

;https://gist.github.com/nberger/b5e316a43ffc3b7d5e084b228bd83899
;https://github.com/joakimmohn/crudurama/blob/master/src/cljs/crudurama/infinite-scroll.cljs
;$(window).scroll(function () {
;   if ($(window).scrollTop() >= $(document).height() - $(window).height() - 10) {
;      //Add something at the end of the page
;   }
;});

;(behavior "Triggering an event from state b"
;      (uism/trigger! app ::id :event/bang!)
;      (assertions
;        "Moves to the correct state"
;        (uism/get-active-state app ::id) => :state/a
;        "Applies the expected actions"
;        (fget-in [:WOW]) => 2)))