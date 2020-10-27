(ns app.ui.root
  (:require
    [app.ui.mutations :as uim]
    [cljs.core.match :refer-macros [match]]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr]
    [com.fulcrologic.fulcro.ui-state-machines :as uism
     :refer [defstatemachine]]
    [com.fulcrologic.fulcro.algorithms.normalized-state :refer [swap!->]]
    [com.fulcrologic.fulcro-css.css-injection :as inj]
    [com.fulcrologic.fulcro-css.css :as css]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p h4 ul a]]
    [com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]
    [com.fulcrologic.fulcro.data-fetch :as df]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr :refer [defrouter]]
    [taoensso.timbre :as log]))



(defsc Second [this {:second/keys [second]}]
  {:query [:second/second]
   :initial-state
    (fn [{:keys [second]}]
      {:second/second second})}
  (div
    (str "big secondo: " second)))
(def ui-second (comp/factory Second))

(defsc Test [this {:test/keys [first second]}]
  {:query [:test/id
           :test/first
           {:test/second (comp/get-query Second)}]
   :ident :test/id
   :initial-state
    (fn [{:keys [id first second]}]
      {:test/id id
       :test/first first
       :test/second
        (comp/get-initial-state Second {:second second})})}
  (div
    (str "First: " first)
    (ui-second second)))
(def ui-test (comp/factory Test))

(defsc Home [this {:home/keys [first
                               second
                               third]}]
  {:query [{:home/first (comp/get-query Test)}
           {:home/second (comp/get-query Test)}
           {:home/third (comp/get-query Test)}]
   :route-segment ["home"]
   :ident (fn [] [:component/id :home])
   :initial-state
   (fn [_]
     {:home/first
        (comp/get-initial-state
          Test
          {:id 1
           :first "home first first"
           :second "home first second"})
      :home/second
        (comp/get-initial-state
          Test
          {:id 2
           :first "home second first"
           :second "home second second"})
      :home/third
        (comp/get-initial-state
          Test
          {:id 3
           :first "home third first"
           :second "home third second"})})}
  (div
    (div "First: " (ui-test first))
    (div "Second: " (ui-test second))
    (div "Third: " (ui-test third))))

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

(defsc Page [this {:page/keys [outer sidebar]}]
  {:query [{:page/outer (comp/get-query OuterBox)}
           {:page/sidebar (comp/get-query Sidebar)}]
   :initial-state
    (fn [_] {:page/outer
             (comp/get-initial-state
               OuterBox {:outer/id    1
                         :outer/route "home"})
             :page/sidebar
             (comp/get-initial-state
               Sidebar {:sidebar/id 1})})
   :css [[:.page
          {:display         "flex"
           :align-items     "center"
           :justify-content "center"}]]}
       (let [{:keys [page]} (css/get-classnames Page)]
            (dom/div {:classes [page]}
                     (ui-outer outer)
                     (ui-sidebar sidebar))))
(def ui-page (comp/factory Page))

(defsc Root [this {:root/keys [page] :as props}]
  {:query [{:root/page (comp/get-query Page)}]
   :initial-state
    (fn [_] {:root/page
              (comp/get-initial-state Page)})
   :css [[:.container
          {:display "flex"
           :align-items "center"
           :justify-content "center"}]]}
       (let [{:keys [container]} (css/get-classnames Root)]
            (dom/div
              {:classes [container]}
              (inj/style-element {:component Root})
              ;(inj/style-element {:component Home})
              (inj/style-element {:component ContainerHeader})
              (ui-page page))))
