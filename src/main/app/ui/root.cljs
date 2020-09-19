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
                          :image/alt alt])
   :css [[:.image-container
          {:width "100%"
           :height "100%"}]]
   }
       (let [{:keys [image]} (css/get-classnames Image)]
            ;(dom/div
            (dom/img
              ;:.image-container {:className [image]}
              ;(inj/style-element {:component Image})

              {:src src
               :alt alt
               :.image-container {:className [image]}
              ;)
               }
                     )
            ))
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
                :height "50%"}]]
        }
       (let [{:keys [href]} (css/get-classnames Href)]
            (dom/a {:href link
                    :target "__blank"
                    :rel "noopener noreferrer"
                    :.href-container {:className [href]}
                    }
                   ;:.href-container {:className [href]}
                   ;(inj/style-element {:component Href})
                (ui-image image))))
(def ui-href (comp/factory Href))

(defsc Test [this {:keys [image]}]
  {:query [:type :id {:image (comp/get-query Image)}]
   :initial-state
          (fn [{:keys [id src alt]}]
              {:id id :type :test
               :image (comp/get-initial-state
                        Image {:id 1 :src src :alt alt})})}
  (dom/div
    (ui-image image)))
(def ui-test (comp/factory Test {:keyfn :id}))

(defsc Home [this {:home/keys [name images] :as props}]
       {:query [:home/name {:home/images (comp/get-query Href)}]
        :initial-state
           (fn [{:keys [_] :as params}]
               {:home/name "home"
                :home/images
                   [(comp/get-initial-state Href
                      {:link "https://en.wikipedia.org/wiki/Gaming"
                       :id 1
                       :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
                       :alt "I play games I KNOW I'M SORRY"})
                    (comp/get-initial-state Href
                      {:link "https://www.whatisitliketobeaphilosopher.com/"
                       :id 2
                       :src "../images/the-thinker.png\\"
                       :alt "But really, what even IS a rock anyways???"})]})}
  (dom/div
    ;(println "images" images)
    (mapv ui-href images)))
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
