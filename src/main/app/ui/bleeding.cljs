(ns app.ui.bleeding
  (:require
    [taoensso.timbre :as log]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr :refer [defrouter]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p h4 ul a img]]
    [com.fulcrologic.fulcro-css.css :as css]
    ))

(defsc Image [this {:second/keys [id src alt]}]
       {:query [:second/id :second/src :second/alt]
        :initial-state
         (fn [{:keys [src id alt]}]
           {:second/src src
            :second/id id
            :second/alt alt})}
       (img
         {:src src
          :id id
          :alt alt}
         ))
(def ui-second (comp/factory Image))

(defsc Href [this {:test/keys [id link thing]}]
       {:query [:test/id
                :test/link
                {:test/thing (comp/get-query Image)}]
        :ident :test/id
        :initial-state
         (fn [{:keys [id link src alt]}]
           {:test/id id
            :test/thing
              (comp/get-initial-state
                Image
                {:src src
                 :id (str id "-img")
                 :alt alt})})}
  (a {:href link
      :target "__blank"
      :rel "noopener noreferrer"}
    (ui-second thing)))
(def ui-test (comp/factory Href))

(defsc TopLeft [this {:top-left/keys [contents] :as props}]
  {:query [{:top-left/contents (comp/get-query Href)}]
   :initial-state
          (fn [{:keys [link id src alt]}]
            {:top-left/contents
             (comp/get-initial-state
               Href
               {:link link :id id :src src :alt alt})})}
  (ui-test contents))
(def ui-top-left (comp/factory TopLeft))

(defsc BottomLeft [this {:bottom-left/keys [contents] :as props}]
  {:query [{:bottom-left/contents (comp/get-query Href)}]
   :initial-state
          (fn [{:keys [link id src alt]}]
            {:bottom-left/contents
             (comp/get-initial-state
               Href
               {:link link :id id :src src :alt alt})})}
  (ui-test contents))
(def ui-bottom-left (comp/factory BottomLeft))

(defsc LeftSide [this {:left-side/keys [
                                        top
                                        bottom
                                        ] :as props}]
       {:query [{:left-side/top (comp/get-query TopLeft)}
                {:left-side/bottom (comp/get-query BottomLeft)}]
        :initial-state
         (fn [{:keys [top bottom]}]
           (let [mappy
                 (fn [dir]
                   {:link (:link dir)
                    :id (:id dir)
                    :src (:src dir)
                    :alt (:alt dir)})]
             {:left-side/top
              (comp/get-initial-state
                TopLeft
                top)
              :left-side/bottom
              (comp/get-initial-state
                BottomLeft
                bottom)
              }))}
       (dom/div
         (ui-top-left top)
         (ui-bottom-left bottom)
         ))
(def ui-left-side (comp/factory LeftSide))

(defsc Middle [this {:middle/keys [id content] :as props}]
  {:query [:middle/id
           :middle/content]
   :initial-state
          (fn [{:keys [id content]}]
            {:middle/id id
             :middle/content content})
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
  (let [{:keys [middle-main-page padding-bottom]} (css/get-classnames Middle)]
    (dom/div
      ;{:classes [middle-main-page padding-bottom]}
      ;:.general-container
      ;       {
      ;:.general-container
      ;:.middle-main-page
      ;:.padding-bottom
      ;        :className (doall [middle-main-page padding-bottom])
      ;}
      (doall content))))
(def ui-middle (comp/factory Middle {:keyfn :middle/id}))

(defsc RightSide [this {:right-side/keys [top bottom] :as props}
                  {:keys [right-side]}]
  {:query [{:right-side/top (comp/get-query TopLeft)}
           {:right-side/bottom (comp/get-query BottomLeft)}]
   :initial-state
          (fn [{:keys [top bottom] :as params}]
            {:right-side/top
             (comp/get-initial-state TopLeft
                                     {:link (:link top)
                                      :id (:id top)
                                      :src (:src top)
                                      :alt (:alt top)})
             :right-side/bottom
             (comp/get-initial-state BottomLeft
                                     {:link (:link bottom)
                                      :id (:id bottom)
                                      :src (:src bottom)
                                      :alt (:alt bottom)})})
   :css [[:.right-side
          {:display "flex";
           :flex-direction "column"
           :align-items "center"
           :padding-right "1.5em"
           :width "100%"}]
         [:.right-side>a+a
          {:padding-top "6em"}]]}
  (let [{:keys [right-side]} (css/get-classnames RightSide)]
    (dom/div {:classes [right-side]}
             (ui-top-left top)
             (ui-bottom-left bottom))))
(def ui-right-side (comp/factory RightSide))

(def home-initial-state
  {:home/left
   (comp/get-initial-state
     LeftSide
     {:top
      {:link "https://en.wikipedia.org/wiki/Gaming"
       :id "gamin"
       :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
       :alt "I play games I KNOW I'M SORRY"}
      :bottom
      {:link "https://www.whatisitliketobeaphilosopher.com/"
       :id "pho"
       :src "../images/the-thinker.png"
       :alt "But really, what even IS a rock anyways???"}})
   :home/middle
   (comp/get-initial-state
     Middle
     {:id "home-middle"
      :content
          [(p {:key 1 :className "enlarge-text"}
              "Mostly this stuff")
           (p {:key 2 :className "small-text"}
              "(check out my projects for novel things)")]})
   :home/right
    (comp/get-initial-state
      RightSide
      {:top
       {:link "https://www.youtube.com/"
        :id "Tube"
        :src "../images/tubes.png"
        :alt "Youtube is my Netflix, sadly"}
       :bottom
       {:link "https://en.wikipedia.org/wiki/Programmer"
        :id "debug"
        :src "../images/meirl.png"
        :alt "g! 'How to print newline in cljs'"}})})
(defsc Home [this {:home/keys [left middle right]}]
       {:query [{:home/left (comp/get-query LeftSide)}
                {:home/middle (comp/get-query Middle)}
                {:home/right (comp/get-query RightSide)}]
        :route-segment ["home"]
        :ident (fn [] [:component/id :home])
        :initial-state
          (fn [_]
            home-initial-state)}
       (div
         (ui-left-side left)
         (ui-middle middle)
         (ui-right-side right)))