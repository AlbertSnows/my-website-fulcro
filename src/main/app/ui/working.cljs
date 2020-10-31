(ns app.ui.working
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr :refer [defrouter]]
    [app.ui.bleeding :as b]
    [taoensso.timbre :as log]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p h4 ul a img]]
    [com.fulcrologic.fulcro.ui-state-machines :as uism
     :refer [defstatemachine]]
    [com.fulcrologic.fulcro-css.css :as css]
    [app.ui.mutations :as uim]
    ))

;(defsc Projects [this {:projects/keys [id] :as props}]
;  {:query [:projects/id]
;   :route-segment ["projects"]
;   :ident (fn [] [:projects/id :projects])
;   :initial-state {}}
;  (dom/div "Hello!"))
;(def ui-projects (comp/factory Projects))
;
;(defsc About [this {:about/keys [id] :as props}]
;  {:query [:about/id]
;   :route-segment ["about"]
;   :ident (fn [] [:component/id :about])
;   :initial-state {}}
;  (dom/div "Hello!"))
;(def ui-about (comp/factory About))

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
(def ui-image (comp/factory Image))

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
     (ui-image thing)))
(def ui-href (comp/factory Href))

(defsc TopLeft [this {:top-left/keys [contents] :as props}]
  {:query [{:top-left/contents (comp/get-query Href)}]
   :initial-state
          (fn [{:keys [link id src alt]}]
            {:top-left/contents
             (comp/get-initial-state
               Href
               {:link link :id id :src src :alt alt})})}
  (ui-href contents))
(def ui-top-left (comp/factory TopLeft))

(defsc BottomLeft [this {:bottom-left/keys [contents] :as props}]
  {:query [{:bottom-left/contents (comp/get-query Href)}]
   :initial-state
          (fn [{:keys [link id src alt]}]
            {:bottom-left/contents
             (comp/get-initial-state
               Href
               {:link link :id id :src src :alt alt})})}
  (ui-href contents))
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
   (fn [_] home-initial-state)}
  (div
    (ui-left-side left)
    (ui-middle middle)
    (ui-right-side right)))

(def contact-initial-state
  {:contact/image
   (comp/get-initial-state
     Image
     {:id "mail-big"
      :src "../images/mailV2.png"
      :alt "email"})
   :contact/span
   (comp/get-initial-state
     Image
     {:id "mail-small"
      :src "../images/mail_secure.PNG"
      :alt "for security reasons"})})
(defsc Contact [this {:contact/keys [image span] :as props}]
  {:ident (fn [] [:component/id :contact])
   :query [{:contact/image (comp/get-query Image)}
           {:contact/span (comp/get-query Image)}]
   :initial-state (fn [_] contact-initial-state)
   :route-segment ["contact"]}
  (div
    (ui-image image)
    (ui-image span)))

(defrouter RootRouter
           [this {:keys [current-state pending-path-segment]}]
           {:router-targets [Home Contact b/Projects]})
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
               (log/info "Name: " name)
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
                                       (comp/get-initial-state
                                         ListItem
                                         {:list-item/name "home"
                                          :list-item/classes "sidebar-brand"})
                  :sidebar-contents/about
                                       (comp/get-initial-state
                                         ListItem
                                         {:list-item/name "about"
                                          :list-item/classes ""})
                  :sidebar-contents/contact
                                       (comp/get-initial-state
                                         ListItem
                                         {:list-item/name "contact"
                                          :list-item/classes ""})
                  :sidebar-contents/projects
                                       (comp/get-initial-state
                                         ListItem
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