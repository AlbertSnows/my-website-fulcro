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
   :ident         (fn [] [:image/src src
                          :image/alt alt])}
       (dom/img :.image-container {:className [image] :src src}))
(def ui-image (comp/factory Image {:keyfn :id}))

(defsc Href [this {:href/keys [link image] :as props} {:keys [href]}]
       {:query [:href/link {:href/image (comp/get-query Image)}]
        :initial-state
          (fn [{:keys [link id src alt]}]
              {:href/link link
               :href/image
                (comp/get-initial-state
                  Image {:id id :src src :alt alt})})
        :css [[:.href-container
               {:width "50%"
                :height "50%"}]
              [:.href-container>img
               {:width "50%"
                :height "100%"}]]}
       (let [{:keys [href]} (css/get-classnames Href)]
            (dom/a :.href-container
              {:href link
               :target "__blank"
               :rel "noopener noreferrer"
               :className [href]}
                   (inj/style-element {:component Href})
                   (ui-image image))))
(def ui-href (comp/factory Href))

(defsc TopLeft [this {:top-left/keys [contents] :as props}]
       {:query [{:top-left/contents (comp/get-query Href)}]
        :initial-state
         (fn [{:keys [link id src alt]}]
             {:top-left/contents
              (comp/get-initial-state Href
                {:link link :id id :src src :alt alt})})}
       (dom/div
         (ui-href contents)))
(def ui-top-left (comp/factory TopLeft))

(defsc BottomLeft [this {:bottom-left/keys [contents] :as props}]
       {:query [{:bottom-left/contents (comp/get-query Href)}]
        :initial-state
         (fn [{:keys [link id src alt]}]
             {:bottom-left/contents
              (comp/get-initial-state Href
                {:link link :id id :src src :alt alt})})}
       (dom/div
         (ui-href contents)))
(def ui-bottom-left (comp/factory BottomLeft))

(defsc LeftSide [this {:left-side/keys [top bottom] :as props}]
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
                   :alt (:alt bottom)})})}
       (dom/div
         (ui-top-left top)
         (ui-bottom-left bottom)))
(def ui-left-side (comp/factory LeftSide))

(defsc TopRight [this {:top-right/keys [contents] :as props}]
       {:query [{:top-right/contents (comp/get-query Href)}]
        :initial-state
         (fn [{:keys [link id src alt]}]
             {:top-right/contents
              (comp/get-initial-state Href
                {:link link :id id :src src :alt alt})})
        :css [:.right-top
              []]}
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

(defsc RightSide [this {:right-side/keys [top bottom] :as props}]
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
        ;:css [right-side {
        ;                  width: 30%;
        ;                         display: flex;
        ;                         height: 30%;
        ;                  overflow: visible;
        ;                  flex-direction: column;
        ;                  margin-top: 2em;
        ;                  margin-bottom: 2em;
        ;                  }]
        }
       (dom/div
         (ui-top-left top)
         (ui-bottom-left bottom)))
(def ui-right-side (comp/factory RightSide))

(defsc Home [this {:home/keys [left-side right-side] :as props}]
       {:query [{:home/left-side (comp/get-query LeftSide)}
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
                    :alt "g! 'How to print newline in cljs'"}})})}
       ;:css [.general-container {
       ;                          display: flex;
       ;                          flex-direction: row;
       ;                          justify-content: center;
       ;                          align-items: center;
       ;                          }
       ;
       ;      .general-container>div>.href-image-container {
       ;                                                    width: 50%;
       ;                                                           height: 50%;
       ;                                                    }]
  (dom/div
    (ui-left-side left-side)
    (ui-right-side right-side)))
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
         ;(get-header route)
       (dom/p "header")
       )
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
                 {:width "70%"
                  :color "white"
                  :background-color "black"
                  :padding-left  "1em"
                  :padding-right  "1em"
                  :margin "0 auto"
                  :margin-top "10%"
                  :height "max-content"
                  :border-radius "3%"
                  :min-width "1em"
                  :margin-bottom "10%"
                  :padding-bottom "1em"}]]}
       (let [{:keys [outer]} (css/get-classnames OuterBox)]
            (dom/div :.outer {:className [outer]}
                     (inj/style-element {:component OuterBox})
                     ;(seq [:nonsense
                     ; "HEY WHAT DO YOU THINK YOU'RE DOING LOOKING IN HERE. "])
              ;"id: " id " | "
              ;"route: " route " | "
              ;"header: " (str header) " | "
              ;"body: " body

                     (ui-container-header header)
                     (dom/div (ui-home body))
                     ;(dom/ul (mapv ui-home body))
                     ;(ui-container-body body)
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
                               :outer-box/route "home"})})}
  (dom/div (ui-button button)
           (ui-outer-box outer-box)))
(def ui-page (comp/factory Page))

(defsc Root [this {:root/keys [page] :as props}
             {:keys [background]}]
  {:query [{:root/page (comp/get-query Page)}]
   :initial-state
          (fn [_] {:root/page (comp/get-initial-state Page)})
   :css   [[:.background
            {:width "100%"
             :height "100%"}]]}
       (let [{:keys [background]} (css/get-classnames Root)]
            (dom/div :.background {:className [background]}
                     (inj/style-element {:component Root})
                     (ui-page page))))
