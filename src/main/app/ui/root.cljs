(ns app.ui.root
  (:require
    [app.ui.mutations :as uim]
    [cljs.core.match :refer-macros [match]]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr]
    [com.fulcrologic.fulcro.ui-state-machines :as uism :refer [defstatemachine]]

    [com.fulcrologic.fulcro-css.css-injection :as inj]
    [com.fulcrologic.fulcro-css.css :as css]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p]]))


;(defsc ContainerRight [this {:inner-box/keys [id] :as props}]
;       {:query []
;        :ident :inner-box/keys
;        :initial-state (fn [{:keys [id] :as params}]
;                           {:inner-box/keys id})})
;(defsc ContainerHorizontalMiddle [this {:inner-box/keys [id] :as props}]
;       {:query []
;        :ident :inner-box/keys
;        :initial-state (fn [{:keys [id] :as params}]
;                           {:inner-box/keys id})})
;(defsc ContainerLeft [this {:inner-box/keys [id] :as props}]
;       {:query []
;        :ident :inner-box/keys
;        :initial-state (fn [{:keys [id] :as params}]
;                           {:inner-box/keys id})})
;(defsc ContainerTop [this {:inner-box/keys [id] :as props}]
;       {:query []
;        :ident :inner-box/keys
;        :initial-state (fn [{:keys [id] :as params}]
;                           {:inner-box/keys id})})
;(defsc ContainerVerticalMiddle [this {:inner-box/keys [id] :as props}]
;       {:query []
;        :ident :inner-box/keys
;        :initial-state (fn [{:keys [id] :as params}]
;                           {:inner-box/keys id})})
;(defsc ContainerBottom [this {:inner-box/keys [id] :as props}]
;       {:query []
;        :ident :inner-box/keys
;        :initial-state (fn [{:keys [id] :as params}]
;                           {:inner-box/keys id})})
;
;(defsc Home [this {:inner-box/keys [id] :as props}]
;       {:query []
;        :ident :inner-box/keys
;        :initial-state (fn [{:keys [id] :as params}]
;                           {:inner-box/keys id})}
;       (def Header [:p {:id "home-header"} "What am I up to?"])
;       (def Body
;         [:div {:id "home-body"}
;          [:div {:id "25_01_2020" :class "box"}
;           [:div                                            ;{:class "general-container"}
;            [ContainerLeft {:class "left-side"}
;             [:div {:id "table-role-video" :class "left-top href-image-container"}
;              [:a {:href "https://en.wikipedia.org/wiki/Gaming" :target "__blank" :rel "noopener noreferrer"}
;               [:img {:src "../images/WITH_OUR_THREE_POWERS_COMBINED.png" :alt "I play games I KNOW I'M SORRY"}]]]
;             [:div {:id "thonker" :class "left-bottom href-image-container"}
;              [:a {:href "https://www.whatisitliketobeaphilosopher.com/" :target "__blank" :rel "noopener noreferrer"}
;               [:img {:src "../images/the-thinker.png" :alt "But really, what even IS a rock anyways!?!?!?!"}]]]]
;            [ContainerHorizontalMiddle {:class "middle-main-page padding-bottom"} [:p {:class "enlarge-text"} "Mostly this stuff"]
;             [:p {:class "small-text"} "(check out my projects for novel things)"]]
;            [ContainerRight {:class "right-side"}
;             [:div {:id "tuber" :class "right-top href-image-container"}
;              [:a {:href "https://www.youtube.com/" :target "__blank" :rel "noopener noreferrer"}
;               [:img {:src "../images/tubes.png" :alt "Youtube is my Netflix, sadly"}]]]
;             [:div {:id "sudo-apt-get-gf" :class "right-bottom href-image-container"}
;              [:a {:href "https://en.wikipedia.org/wiki/Programmer" :target "__blank" :rel "noopener noreferrer"}
;               [:img {:src "../images/meirl.png" :alt "g! 'How to print newline in cljs'"}]]]]]]])
;       )
;
;(defsc ContainerHeader)
;
;(defsc ContainerBody [this {:inner-box/keys [id] :as props}]
;       {:query []
;        :ident :inner-box/keys
;        :initial-state (fn [{:keys [id] :as params}]
;                           {:inner-box/keys id})}
;
;       )
;
;(defsc InnerBox [this {:inner-box/keys [id] :as props}]
;       {:query []
;        :ident :inner-box/keys
;        :initial-state (fn [{:keys [id] :as params}]
;                           {:inner-box/keys id})}
;       (dom/div {:id "inner-box"}
;         (match [request]
;                ["home"] home/home-page-body
;                ["about"] about/about-page-body
;                ["projects"] projects/project-page-body
;                ["contact"] contact/contact-page-body)
;         ))
;
;(def ui-container (comp/factory Container))

(defsc Container [this {:container/keys [id] :as props}]
       {:query [:container/id]
        :ident :container/id
        :initial-state (fn [{:keys [id] :as params}]
                           {:container/keys id})}
       (dom/div
         ))

(def ui-container (comp/factory Container))

(defn get-header [request]
      (match [request]
             ["home"] (dom/p "What am I up to?")
             ;["about"] about/about-page-header
             ;["projects"] projects/project-page-header
             ;["contact"] contact/contact-page-header
             ))

(defsc ContainerHeader [this {:container-header/keys [id route] :as props}
                        {:keys [outer-text]}]
       {:query [:container-header/id :container-header/route]
        :ident :container-header/id
        :initial-state (fn [{:keys [id _] :as params}]
                           {:container-header/keys id
                            :container-header/route "home"})
        :css   [[:.outer-text
                 {:font-size "2em"
                  :color "white"
                  :text-align "center"
                  :font-family "MINIMAL"
                  :margin "0 auto"
                  :padding "0 auto"
                  :vertical-align "top"}]]}

       ;(let [{:keys [outer-text]} (css/get-classnames ContainerHeader)]
       ;     (dom/div :.container-header
       ;              {:className [outer-text]}
         (get-header route)
       ; ))
       )

(def ui-container-header (comp/factory ContainerHeader))

(defsc OuterBox [this {:outer-box/keys [id header] :as props}
                 {:keys [outer]}]
       {:query [:outer-box/id :outer-box/header]
        :ident :outer-box/id
        :initial-state (fn [{:keys [id _] :as params}]
                           {:outer-box/keys id
                            :outer-box/header
                            (comp/get-initial-state
                              ContainerHeader {:container-header/id id
                                               :container-header/route "home"})})
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
                     ;(seq [:nonsense "HEY WHAT DO YOU THINK YOU'RE DOING LOOKING IN HERE. "])

                     (ui-container-header header)
                     ;(ContainerHeader (get-header request))
                     )))

(def ui-outer-box (comp/factory OuterBox))

(defsc Button
  [this {:button/keys [id num] :as props}]
  {:query [:button/id :button/num]
   :ident :button/id
   :initial-state (fn [{:keys [id _] :as params}]
                      {:button/id id :button/num 0})}
  (dom/button
    {:onClick #(comp/transact! this
                 [(uim/toggle {:button/id id})])} num))

(def ui-button (comp/factory Button))

(defsc Page [this {:page/keys [button outer-box]}]
  {:query [:page/button (comp/get-query Button)
           :page/outer-box (comp/get-query OuterBox)]
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
             :height "100%"
             :background-image "url('/images/background.png')"
             :background-color "black"
             :background-position "center"
             :background-attachment "fixed"
             :background-repeat "no-repeat"
             :background-size "cover"}]]}

       (let [{:keys [background]} (css/get-classnames Root)]
            (dom/div :.background {:className [background]}
                     (inj/style-element {:component Root})
                     (ui-page page))))
