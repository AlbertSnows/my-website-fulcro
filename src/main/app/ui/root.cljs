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
     :refer [div label button span p]]))

;
;(defsc Home [this {:inner-box/keys [id] :as props}]
;       {:query []
;        :ident :inner-box/keys
;        :initial-state (fn [{:keys [id] :as params}]
;                           {:inner-box/keys id})}
;       )
;
;(defsc ContainerHeader)
;
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



;(defn get-header [request]
;      (match [request]
;             ["home"] (dom/p "What am I up to?")
;             ;["about"] about/about-page-header
;             ;["projects"] projects/project-page-header
;             ;["contact"] contact/contact-page-header
;             ))
;(defn get-inner-content [request]
;      (match [request]
;             ["home"] (ui-)
;             ;["about"] about/about-page-body
;             ;["projects"] projects/project-page-body
;             ;["contact"] contact/contact-page-body
;             ))

;(defn get-inner [req]
;      (dom/div
;        (match [req]
;               ["home"] (
;                         (ui-container-header header)
;                         (ui-container-body body)
;                          )
;               )
;        )
;)

(defsc Container [this {:container/keys [id content] :as props}]
       {:query [:container/id :container/content]
        :ident :container/id
        :initial-state (fn [{:keys [id content] :as params}]
                           {:container/id id
                            :container/content content})}
        )
(def ui-container (comp/factory Container))

(defsc ContainerRight [this {:container-right/keys [id content] :as props}]
       {:query [:container-right/id :container-right/content]
        :ident :container-right/id
        :initial-state (fn [{:keys [id content] :as params}]
                           {:container-right/id id
                            :container-right/container
                              (comp/get-initial-state Container
                                {:container/id id :container/content content})})}
       (dom/div :.container-right "right-side" content))
(def ui-container-right (comp/factory ContainerRight))

(defsc ContainerHorizontalMiddle [this {:container-hoz-middle/keys [id content] :as props}]
       {:query [:container-hoz-middle/id :container-hoz-middle/content]
        :ident :container-hoz-middle/id
        :initial-state (fn [{:keys [id content] :as params}]
                           {:container-hoz-middle/id id
                            :container-hoz-middle/container
                              (comp/get-initial-state Container
                                {:container/id id :container/content content})})}
       (dom/div :.container-hoz-middle  "hoz-middle" content))
(def ui-container-hoz-middle (comp/factory ContainerHorizontalMiddle))

(defsc ContainerLeft [this {:container-left/keys [id content] :as props}]
       {:query [:container-left/id :container-left/content]
        :ident :container-left/id
        :initial-state (fn [{:keys [id content] :as params}]
                           {:container-left/id id
                            :container-left/container
                            (comp/get-initial-state Container
                              {:container/id id :container/content content})})}
       (dom/div :.container-left  "left-side" content))
(def ui-container-left (comp/factory ContainerLeft))

(defsc ContainerTop [this {:container-top/keys [id content] :as props}]
       {:query [:container-top/id :container-top/content]
        :ident :container-top/id
        :initial-state (fn [{:keys [id content] :as params}]
                           {:container-top/id id
                            :container-top/container
                              (comp/get-initial-state Container
                                {:container/id id :container/content content})})}
       (dom/div :.container-top  "top" content))
(def ui-container-top (comp/factory ContainerTop))

(defsc ContainerVerticalMiddle [this {:container-vert-middle/keys [id content] :as props}]
       {:query [:container-vert-middle/id :container-vert-middle/content]
        :ident :container-vert-middle/id
        :initial-state (fn [{:keys [id content] :as params}]
                           {:container-vert-middle/id id
                            :container-vert-middle/container
                              (comp/get-initial-state Container
                                {:container/id id :container/content content})})}
       (dom/div :.container-vert-middle  "top" content))
(def ui-container-vert-middle (comp/factory ContainerTop))

(defsc ContainerBottom [this {:container-bottom/keys [id content] :as props}]
       {:query [:container-bottom/id :container-bottom/content]
        :ident :container-bottom/id
        :initial-state (fn [{:keys [id content] :as params}]
                           {:container-bottom/id id
                            :container-bottom/container
                              (comp/get-initial-state Container
                                {:container/id id :container/content content})})
        }
       (dom/div :.container-bottom  "bottom" content))
(def ui-container-bottom (comp/factory ContainerBottom))

(defsc ContainerBody [this {:container-body/keys [id body] :as props}]
       {:query [:container-body/id :container-body/body]
        :ident :container-body/id
        :initial-state (fn [{:keys [id body] :as params}]
                           {:container-body/id id
                            :container-body/body body})
        :css [[:.outer-text
               {:font-size "2em"
                :color "white"
                :text-align "center"
                :font-family "MINIMAL"
                :margin "0 auto"
                :padding "0 auto"
                :vertical-align "top"}]
              [:.home
               {:border "1px"
                :border-color "white"
                :border-style "solid"
                :border-radius "1%"
                :position "relative"
                :width "inherit"
                :overflow-wrap "anywhere"
                :word-wrap "anywhere"
                :hyphens "auto"
                :display "flex"
                :flex-direction "row"
                :justify-content "center"
                :align-items "center"}]]}
(dom/div ":class \"box general-container\""
         "hey look it's content + " body)
       )
(def ui-container-body (comp/factory ContainerBody))

(defsc Home [this {:home/keys [id body] :as props}]
       {:query [:home/id :home/body]
        :ident :home/id
        :initial-state (fn [{:keys [id] :as params}]
                           {:home/id id
                            :home/body
                            (comp/get-initial-state
                              ContainerBody
                              {:container/id id
                               :container/content
                               {(comp/get-initial-state
                                  ContainerLeft
                                  ;{:container-left/id id
                                  ; :container-left/content
                                  ; {
                                  ;  ;(comp/get-initial-state
                                  ;  ;  ContainerTop
                                  ;  ;{:container-top/id id
                                  ;  ; :container-top/content
                                  ;  ; {(dom/div ":class table-role-video left-top href-image-container"
                                  ;  ;    (dom/a
                                  ;  ;      ":href \"https://en.wikipedia.org/wiki/Gaming\"
                                  ;  ;       :target \"__blank\" :rel \"noopener noreferrer\""
                                  ;  ;           (dom/img
                                  ;  ;             ":src \"../images/WITH_OUR_THREE_POWERS_COMBINED.png\"
                                  ;  ;             :alt \"I play games I KNOW I'M SORRY\"
                                  ;  ;             "))
                                  ;  ;           )}})
                                  ;  ;(comp/get-initial-state
                                  ;  ;  ContainerBottom
                                  ;  ;  {:container-bottom/id id
                                  ;  ;   :container-bottom/body
                                  ;  ;   {(dom/div ":class thonker left-bottom href-image-container"
                                  ;  ;             (dom/a
                                  ;  ;               ":href \"https://www.whatisitliketobeaphilosopher.com/\"
                                  ;  ;                :target \"__blank\" :rel \"noopener noreferrer\""
                                  ;  ;               (dom/img
                                  ;  ;                 ":src \"../images/the-thinker.png\"
                                  ;  ;                  :alt \"But really, what even IS a rock anyways???")))}})
                                  ;  }}
                                  )
                                (comp/get-initial-state
                                  ContainerHorizontalMiddle
                                  {
                                   ;(dom/div ":class middle-main-page padding-bottom"
                                   ;         (dom/p ":class enlarge-text" "Mostly this stuff")
                                   ;         (dom/p ":class small-text" "(check out my project for novel things"))
                                   })
                                (comp/get-initial-state
                                  ContainerRight
                                  {
                                   ;(dom/div ":class right"
                                   ;   (comp/get-initial-state
                                   ;     ContainerTop
                                   ;     {(dom/div ":class tuber right-top href-image-container"
                                   ;         (dom/a ":href \"https://www.youtube.com/\" :target \"__blank\" :rel \"noopener noreferrer"
                                   ;            (dom/img ":src \"../images/tubes.png\" :alt \"Youtube is my Netflix, sadly")))})
                                   ;         (comp/get-initial-state
                                   ;           ContainerMiddle
                                   ;           {(dom/div ":class sudo-apt-get-gf right-bottom href-image-container"
                                   ;                     (dom/a ":href \"https://en.wikipedia.org/wiki/Programmer\" :target \"__blank\" :rel \"noopener noreferrer\""
                                   ;                            (dom/img ":src \"../images/meirl.png\" :alt \"g! 'How to print newline in cljs'" )))}))
                                   })
                                }}
                              )})}
       (ui-container-body
         body
        )
       )

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

(defsc OuterBox [this {:outer-box/keys [id route header body] :as props}
                 {:keys [outer]}]
       {:query [:outer-box/id :outer-box/route :outer-box/header :outer-box/body]
        :ident :outer-box/id
        :initial-state (fn [{:keys [id _] :as params}]
                           {:outer-box/keys id
                            :outer-box/route "home"
                            :outer-box/header
                            (comp/get-initial-state
                              ContainerHeader {:container-header/id id
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

                     (ui-container-header header)
                     (ui-home body)
                     ;(ui-container-body body)
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
