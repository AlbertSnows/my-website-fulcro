(ns app.ui.root
  (:require
    [app.ui.mutations :as uim]
    [cljs.core.match :refer-macros [match]]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr]
    [com.fulcrologic.fulcro.ui-state-machines :as uism
     :refer [defstatemachine]]
    [com.fulcrologic.fulcro-css.css-injection :as inj]
    [com.fulcrologic.fulcro-css.css :as css]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p h4 ul]]))

(defsc Image [this {:keys [src alt]} {:keys [image]}]
  {:initial-state (fn [{:keys [id src alt]}]
                    {:src src :alt alt})
   :query         [:id :src :alt]
   :ident         :id}
       (dom/img {:src src :alt alt}))
(def ui-image (comp/factory Image {:keyfn :id}))

(defsc Href [this {:href/keys [link image] :as props}]
       {:query [:href/link {:href/image (comp/get-query Image)}]
        :initial-state
          (fn [{:keys [link id src alt]}]
              {:href/link link
               :href/image
                (comp/get-initial-state
                  Image {:id id :src src :alt alt})})
        :css [[:.href-container
               {:max-width "50%"
                :height "auto"
                :display "flex"
                :justify-content "center"
                :margin "3em 0em"}]
              [:.href-container>img
               {:height "auto"
                :max-width "100%"
                }]]}
       (let [{:keys [href-container]} (css/get-classnames Href)]
            (dom/a {:href link
                    :target "__blank"
                    :rel "noopener noreferrer"
                    :classes [href-container]}            ;IT HAS TO MATCH THE CSS CLASS NAME
                   (ui-image image))))
(def ui-href (comp/factory Href))

(defsc TopLeft [this {:top-left/keys [contents] :as props}]
       {:query [{:top-left/contents (comp/get-query Href)}]
        :initial-state
         (fn [{:keys [link id src alt]}]
             {:top-left/contents
              (comp/get-initial-state Href
                {:link link :id id :src src :alt alt})})}
         (ui-href contents))
(def ui-top-left (comp/factory TopLeft))

(defsc BottomLeft [this {:bottom-left/keys [contents] :as props}]
       {:query [{:bottom-left/contents (comp/get-query Href)}]
        :initial-state
         (fn [{:keys [link id src alt]}]
             {:bottom-left/contents
              (comp/get-initial-state Href
                {:link link :id id :src src :alt alt})})}
         (ui-href contents))
(def ui-bottom-left (comp/factory BottomLeft))

(defsc LeftSide [this {:left-side/keys [top bottom] :as props}
                 {:keys [left-side]}]
       {:query [{:left-side/top (comp/get-query TopLeft)}
                {:left-side/bottom (comp/get-query BottomLeft)}]
        :initial-state
          (fn [{:keys [top bottom] :as params}]
               {:left-side/top
                (comp/get-initial-state TopLeft
                  {:link (:link top)
                   :id (:id top)
                   :src (:src top)
                   :alt (:alt top)})
                :left-side/bottom
                (comp/get-initial-state
                  BottomLeft
                  {:link (:link bottom)
                   :id (:id bottom)
                   :src (:src bottom)
                   :alt (:alt bottom)})})
        :css [[:.left-side
               {:display "flex"
                :flex-direction "column"
                :align-items "center"
                :padding-left "0.5em"
                :width "100%"}]
              [:.left-side>div
               {:padding-top "1em"
                :padding-bottom "1em"}]
              [:.left-side>a+a
               {:padding-top "6em"}]]}
       (let [{:keys [left-side]} (css/get-classnames LeftSide)]
            (dom/div {:classes [left-side]}
                     (ui-top-left top)
                     (ui-bottom-left bottom))))
(def ui-left-side (comp/factory LeftSide))

(defsc TopRight [this {:top-right/keys [contents] :as props}]
       {:query [{:top-right/contents (comp/get-query Href)}]
        :initial-state
         (fn [{:keys [link id src alt]}]
             {:top-right/contents
              (comp/get-initial-state Href
                {:link link :id id :src src :alt alt})})
        :css [:.right-top []]}
       (dom/div
         (ui-href contents)))
(def ui-top-right (comp/factory TopRight))

(defsc BottomRight [this {:bottom-right/keys [contents] :as props}]
       {:query [{:bottom-right/contents (comp/get-query Href)}]
        :initial-state
               (fn [{:keys [link id src alt]}]
                   {:bottom-right/contents
                    (comp/get-initial-state Href
                      {:link link :id id :src src :alt alt})})}
       (dom/div (ui-href contents)))
(def ui-bottom-right (comp/factory BottomRight))

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
                :padding-right "0.5em"
                :width "99%"}]
              [:.right-side>a+a
               {:padding-top "6em"}]]}
       (let [{:keys [right-side]} (css/get-classnames RightSide)]
         (dom/div {:classes [right-side]}
           (ui-top-left top)
           (ui-bottom-left bottom))))
(def ui-right-side (comp/factory RightSide))

(defsc Middle [this {:middle/keys [content] :as props}]
       {:query [{:middle/content (comp/get-query Href)}]
        :initial-state
         (fn [{:keys [content]}]
             {:middle/content content})
        :css [[:.middle-main-page
               {:display "flex"
                :flex-direction "column"
                :font-size "4vw"
                :margin "0 auto"
                :justify-content "center"
                :min-width "30%"
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
         {:classes [middle-main-page padding-bottom]}
         ;:.general-container
         ;       {
         ;:.general-container
         ;:.middle-main-page
         ;:.padding-bottom
         ;        :className (doall [middle-main-page padding-bottom])
                 ;}
                (doall content)
                )))
(def ui-middle (comp/factory Middle))

(defsc Home [this {:home/keys [left-side middle right-side] :as props}]
       {:query [{:home/left-side (comp/get-query LeftSide)}
                {:home/middle (comp/get-query Middle)}
                {:home/right-side (comp/get-query RightSide)}]
        :initial-state
           (fn [{:keys [_] :as params}]
               {:home/left-side
                 (comp/get-initial-state LeftSide
                   {:top
                    {:link "https://en.wikipedia.org/wiki/Gaming"
                     :id "playin-games"
                     :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
                     :alt "I play games I KNOW I'M SORRY"}
                    :bottom
                    {:link "https://www.whatisitliketobeaphilosopher.com/"
                     :id "doin-pho"
                     :src "../images/the-thinker.png\\"
                     :alt "But really, what even IS a rock anyways???"}})
                :home/middle
                 (comp/get-initial-state Middle
                   {:content
                      [(dom/p {:className "enlarge-text"}
                         ;{:class "enlarge-text"}
                         "Mostly this stuff")
                       (dom/p
                         {:className "small-text"}
                         "(check out my projects for novel things)")]
                    })
                :home/right-side
                (comp/get-initial-state RightSide
                  {:top
                   {:link "https://www.youtube.com/"
                    :id "tuber"
                    :src "../images/tubes.png"
                    :alt "Youtube is my Netflix, sadly"}
                   :bottom
                   {:link "https://en.wikipedia.org/wiki/Programmer"
                    :id "sudo-apt-get-gf"
                    :src "../images/meirl.png"
                    :alt "g! 'How to print newline in cljs'"}})})
        :css [[:.general-container
               {:display "flex"
                :flex-direction "row"
                :justify-content "center"
                :align-items "center"}]
              [:.general-container>div>.href-image-container
               {:width "50%"
                :height "50%"}]]}
       (let [{:keys [general-container]} (css/get-classnames Home)]
            (dom/div {:classes [general-container]}
              (ui-left-side left-side)
              (ui-middle middle)
              (ui-right-side right-side))))
(def ui-home (comp/factory Home))

(defsc ContainerHeader [this {:container-header/keys [id route] :as props}
                        {:keys [outer-text]}]
       {:query [:container-header/id :container-header/route]
        :ident :container-header/id
        :initial-state (fn [{:keys [id _] :as params}]
                           {:container-header/id id
                            :container-header/route "home"})
        :css   [[:.outer-text
                 {:font-size "2em"
                  :color "white"
                  :text-align "center"
                  :font-family "MINIMAL"
                  :margin "0 auto"
                  :padding "0 auto"
                  :vertical-align "top"}]]}
       (let [{:keys [outer-text]} (css/get-classnames ContainerHeader)]

            (case route
             "" (dom/p "empty")
             nil (dom/p "nil")
             :nil (dom/p "nil key")
             :home (dom/p "home key")
             "home" (dom/p {:classes [outer-text]}
                           "What Am I Up To?")
             (dom/p
               "nothing matched"))))
(def ui-container-header (comp/factory ContainerHeader))

(dr/defrouter PageOptions [this props]
  {:router-targets [Home]})

(defsc OuterBox [this {:outer-box/keys [id route header body] :as props}
                 {:keys [outer]}]
       {:query [:outer-box/id :outer-box/route :outer-box/header :outer-box/body]
        :ident :outer-box/id
        :initial-state (fn [{:keys [id _] :as params}]
                           {:outer-box/keys id
                            :outer-box/route "home"
                            :outer-box/header
                            (comp/get-initial-state
                              ContainerHeader
                              {:container-header/id id
                               :container-header/route "home"})
                            :outer-box/body
                            (comp/get-initial-state Home)})
        :css   [[:.outer
                 {:background-color "black"
                  :width "75%"
                  :display "flex"
                  :flex-direction "column"
                  :justify-content "center"
                  :align-items "center"
                  :padding-bottom "1em"
                  :margin "10% 1% 1% 1%"
                  :border-radius "2.5%"
                  }]
                [:.box
                 {
                  :border-color "white"
                  :border-style "solid"
                  :border-radius "1%"
                  :position "relative"
                  :width "98%"
                  :overflow-wrap "anywhere"
                  :word-wrap "anywhere"
                  :hyphens "auto"
                  :border-width "0.2em"
                       }]]}
       (let [{:keys [outer box]} (css/get-classnames OuterBox)]
            (dom/div {:nonsense "TURN BACK, YE WHO ENTER THE DOMAIN OF HTML"
                              :classes [outer]}
                     (ui-container-header header)
                     (dom/div {:classes [box]}
                       (ui-home body))
                     )))
(def ui-outer-box (comp/factory OuterBox))

(defsc Button [this {:button/keys [id num] :as props}]
  {:query [:button/id :button/num]
   :ident :button/id
   :initial-state (fn [{:keys [id num] :as params}]
                      {:button/id id :button/num 0})}
  (dom/button
    {:onClick #(comp/transact! this
                 [(uim/toggle {:button/id id})])} num))
(def ui-button (comp/factory Button))

(defsc Page [this {:page/keys [button outer-box]}]
  {:query [:page/button {:button (comp/get-query Button)}
           :page/outer-box {:outer-box (comp/get-query OuterBox)}]
   :ident (fn [] [:page/id :page])
   :initial-state
          (fn [_] {:page/button
                   (comp/get-initial-state
                     Button {:button/id 1 :button/num 0})
                   :page/outer-box
                   (comp/get-initial-state
                     OuterBox {:outer-box/id 1
                               :outer-box/route "home"})})
   ;:css [:* [padding: 0
   ;          margin: 0]
   ;
   ;      :app [width: 100% height: 100%]
   ;      :page [width: 100%
   ;             height: 100%
   ;             display: flex;
   ;             flex-direction: row;
   ;             ]
   ;      :f                                                 ;@font-face
   ;          [
   ;           font-family: MINIMAL;
   ;           src: url(../fonts/minimal/Minimal.ttf);
   ;           ]]
   }
  ;(dom/div
    (ui-button button)
    (ui-outer-box outer-box)
    ;)
       )

(def ui-page (comp/factory Page))

(defsc Root [this {:root/keys [page] :as props}]
  {:query [{:root/page (comp/get-query Page)}]
   :initial-state
          (fn [_] {:root/page (comp/get-initial-state Page)})
   :css   [[:.container
            {:display "flex"
             :align-items "center"
             :justify-content "center"}]]}
       (let [{:keys [container]} (css/get-classnames Root)]
            (dom/div {:classes [container]}
              (inj/style-element {:component Root})
              (inj/style-element {:component Home})
              (inj/style-element {:component ContainerHeader})
              (ui-page page))))
