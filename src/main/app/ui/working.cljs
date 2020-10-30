(ns app.ui.working
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr :refer [defrouter]]
    [app.ui.bleeding :as b :refer [Home]]
    [taoensso.timbre :as log]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p h4 ul a]]
    [com.fulcrologic.fulcro.ui-state-machines :as uism
     :refer [defstatemachine]]
    [com.fulcrologic.fulcro-css.css :as css]

    ))

(defsc Image [this {:image/keys [src alt classes]} {:keys [image]}]
  {:query         [:image/src
                   :image/alt
                   :image/classes]
   :ident (fn [] [:component/id :image])
   :initial-state
    (fn [{:image/keys [src alt classes]}]
      {:image/src src
       :image/alt alt
       :image/classes classes})}
       (dom/img {:src src
                 :alt alt
                 :className classes}))
(def ui-image (comp/factory Image))

(defsc Href
       [this {:href/keys [id link image]}]
       {:query [:href/id
                :href/link
                {:href/image (comp/get-query Image)}]
        :initial-state
               (fn [{:keys [link id src alt] :as props}]
                 (log/info "Props: " props)
                 {:href/id   id
                  :href/link link
                  :href/image
                             (comp/get-initial-state
                               Image
                               {:id  (str id "-img")
                                :src src
                                :alt alt})})}
       (dom/a
    {:href link
     :target "__blank"
     :rel "noopener noreferrer"}
    (ui-image image)))
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
                   :alt (:alt bottom)})})
        :css [[:.left-side
               {:display "flex"
                :flex-direction "column"
                :align-items "center"
                :padding-left "1.5em"
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
                :padding-right "1.5em"
                :width "100%"}]
              [:.right-side>a+a
               {:padding-top "6em"}]]}
       (let [{:keys [right-side]} (css/get-classnames RightSide)]
         (dom/div {:classes [right-side]}
           (ui-top-left top)
           (ui-bottom-left bottom))))
(def ui-right-side (comp/factory RightSide))

(defsc Middle [this {:middle/keys [id content] :as props}]
       {:query [:middle/id
                {:middle/content (comp/get-query Href)}]
        :initial-state
         (fn [{:keys [content]}]
             {:middle/id 1
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
(def ui-middle (comp/factory Middle {:keyfn :middle/id}))

(defsc Projects [this {:projects/keys [id] :as props}]
  {:query [:projects/id]
   :route-segment ["projects"]
   :ident (fn [] [:projects/id :projects])
   :initial-state {}}
  (dom/div "Hello!"))
(def ui-projects (comp/factory Projects))

(defsc About [this {:about/keys [id] :as props}]
  {:query [:about/id]
   :route-segment ["about"]
   :ident (fn [] [:component/id :about])
   :initial-state {}}
  (dom/div "Hello!"))
(def ui-about (comp/factory About))

(defsc Test [this {:test/keys [topleft
                               bottomleft]}]
  {:query [{:test/topleft (comp/get-query Href)}
           {:test/bottomleft (comp/get-query Href)}]
   :route-segment ["test"]
   :ident (fn [] [:component/id :test])
   :initial-state
    (fn [item]
      {:test/topleft
        (comp/get-initial-state
          Href
          {:href/link "https://en.wikipedia.org/wiki/Gaming"
           :href/image {:image/src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
                        :image/alt "I play games I KNOW I'M SORRY"}})
       :test/bottomleft
        (comp/get-initial-state
          Href
          {:href/link "https://www.whatisitliketobeaphilosopher.com/"
           :href/image {:image/src "../images/the-thinker.png"
                        :image/alt "But really, what even IS a rock anyways???"}})}
      )}
  (dom/div
    (str "Topleft: " topleft)
    (newline)
    (str "Bottomleft: " bottomleft)
    ;(ui-top-left topleft)
    ;(ui-bottom-left bottomleft)
    ))

(defrouter RootRouter
           [this {:keys [current-state pending-path-segment]}]
           {:router-targets [Home]})
(def ui-root-router (comp/factory RootRouter))

(defsc ContainerHeader [this {:container-header/keys [id route] :as props}
                        {:keys [outer-text]}]
       {:query [:container-header/id :container-header/route]
        :ident :container-header/id
        :initial-state (fn [{:container-header/keys [id _] :as params}]
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
           :contact (dom/p "home key")
           "home" (dom/p {:classes [outer-text]}
                         "What Am I Up To?")
           (dom/p
             "nothing matched"))))
(def ui-container-header (comp/factory ContainerHeader))

(defsc OuterBox [this {:outer/keys
                       [id route header router] :as props}
                 {:keys [outer]}]
       {:query [:outer/id
                :outer/route
                :outer/header
                {:outer/router (comp/get-query RootRouter)}
                [::uism/asm-id ::RootRouter]]
        :pre-merge
               (fn [{:keys [data-tree]}]
                 (merge (comp/get-initial-state OuterBox)
                        data-tree))
        :ident :outer/id
        :initial-state
               (fn [{:outer/keys [id route] :as params}]
                 {:outer/id id
                  :outer/route "contact"
                  :outer/header
                  (comp/get-initial-state
                    ContainerHeader
                    {:container-header/id id
                     :container-header/route "home"})
                  :outer/router
                  (comp/get-initial-state RootRouter {})})
        :css
               [[:.outer
                 {:background-color "black"
                  ;                  :width "50%"
                  :display "flex"
                  :flex-direction "column"
                  :justify-content "center"
                  :align-items "center"
                  :padding "0em 0.5em 1em 0.5em"
                  :margin "7% 1% 1% 1%"
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
                  :border-width "0.2em"
                  :color "white"}]]}
       (let [{:keys [outer box]} (css/get-classnames OuterBox)]
         (dom/div
           {:nonsense "TURN BACK, YE WHO ENTER THE DOMAIN OF HTML"
            :classes [outer]}
           (ui-container-header header)
           (dom/div
             {:classes [box]}
             (ui-root-router router))))
       )
(def ui-outer (comp/factory OuterBox))

(defsc ListItem [this {:list-item/keys [name classes] :as props}]
       {:query [:list-item/name :list-item/classes]
        :initial-state (fn [{:list-item/keys [name classes] :as props}]
                         {:list-item/name name
                          :list-item/classes classes})}
       (dom/li {:className classes
                :onClick (fn [] (dr/change-route! this [name]))}
               ;(log/info "Name: " name)
               (dom/a name)))
(def ui-list-item (comp/factory ListItem {:keyfn :list-item/name}))

(defsc SidebarContents
       [this {:sidebar-contents/keys [id home about contact projects] :as props}]
       {:query [:sidebar-contents/id
                {:sidebar-contents/home (comp/get-query ListItem)}
                {:sidebar-contents/about (comp/get-query ListItem)}
                {:sidebar-contents/contact (comp/get-query ListItem)}
                {:sidebar-contents/projects (comp/get-query ListItem)}]
        :ident :sidebar-contents/id
        :initial-state
               (fn [_]
                 {:sidebar-contents/id 1
                  :sidebar-contents/home
                                       (comp/get-initial-state ListItem
                                                               {:list-item/name "home" :list-item/classes "sidebar-brand"})
                  :sidebar-contents/about
                                       (comp/get-initial-state ListItem
                                                               {:list-item/name "about" :list-item/classes ""})
                  :sidebar-contents/contact
                                       (comp/get-initial-state ListItem
                                                               {:list-item/name "contact" :list-item/classes ""})
                  :sidebar-contents/projects
                                       (comp/get-initial-state ListItem
                                                               {:list-item/name "projects" :list-item/classes ""})})}
       (dom/ul {:className "sidebar-entries sidebar-nav"}
               (ui-list-item home)
               (ui-list-item about)
               (ui-list-item contact)
               (ui-list-item projects)))
(def ui-sidebar-contents (comp/factory SidebarContents))

(defsc SidebarButton [this {:button/keys [id] :as props}]
       {:query [:button/id]
        :ident :button/id
        :initial-state
               (fn [{:button/keys [id] :as params}]
                 {:button/id id})}
       (dom/div {:className ""
                 :id "sidebar-toggle-button"
                 :onClick #(comp/transact! this
                                           `[(uim/toggle ~{:id id})])}
                (dom/p "[")))
(def ui-button (comp/factory SidebarButton))

(defsc Sidebar [this {:sidebar/keys [id state button contents] :as props}]
       {:query [:sidebar/id
                :sidebar/state
                {:sidebar/button (comp/get-query SidebarButton)}
                {:sidebar/contents (comp/get-query SidebarContents)}]
        :ident :sidebar/id
        :initial-state
               (fn [{:sidebar/keys [id] :as props}]
                 {:sidebar/id id
                  :sidebar/state "closed"
                  :sidebar/button
                  (comp/get-initial-state SidebarButton
                                          {:button/id 1})
                  :sidebar/contents
                  (comp/get-initial-state SidebarContents)})}
       (dom/div {:className (str state " sidebar-wrapper")}
                (ui-button button)
                (ui-sidebar-contents contents)))
(def ui-sidebar (comp/factory Sidebar))