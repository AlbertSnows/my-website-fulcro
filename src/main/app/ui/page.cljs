(ns app.ui.page
  (:require
    [cljs.pprint :as pp]
    [com.fulcrologic.fulcro.components :as comp
     :refer [defsc get-query
             get-initial-state factory]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr
     :refer [defrouter]]
    [com.fulcrologic.fulcro.ui-state-machines :as uism
     :refer [defstatemachine]]
    [com.fulcrologic.fulcro-css.css-injection :as inj
     :refer [style-element]]
    [com.fulcrologic.fulcro.dom :as dom
     :refer [div button]]
    [app.ui.components :as uic
     :refer [ContainerHeader ui-container-header]]
    [com.fulcrologic.fulcro-css.css :as css
     :refer [get-classnames]]
    [app.ui.sidebar.core :as sidebar
     :refer [Sidebar ui-sidebar]]
    [com.fulcrologic.fulcro.data-fetch :as df]
    [app.ui.pages.projects :as p]
    [app.ui.pages.contact :as c]
    [app.ui.pages.about :as a]
    [app.ui.pages.home :as h]
    [taoensso.timbre :as log]
    [lab.bleeding :as b]
    [com.fulcrologic.fulcro.algorithms.data-targeting :as t]
    [app.ui.css :as uicss]
    [app.ui.components :as uc
     :refer [Href ContainerHeader ui-container-header]]
    [app.ui.mutations :as m]))

(defrouter RootRouter
  [this {:keys [current-state route-factory route-props pending-path-segment]}]
  {:always-render-body? true
   :router-targets [h/Home a/About c/Contact p/Projects]}
  (dom/div :.ui.loader {:classes [(when-not (= :routed current-state) "active")]})
  (when route-factory (route-factory route-props)))
(def ui-root-router (factory RootRouter))

(defsc OuterBox [this {:outer/keys [id router] :as props}]
  {:query [:outer/id
           :outer/header
           {:outer/router (get-query RootRouter)}
           [::uism/asm-id ::RootRouter]]
   :pre-merge
          (fn [{:keys [data-tree]}]
            (merge (get-initial-state OuterBox)
                   data-tree))
   :ident :outer/id
   :initial-state
          (fn [{:outer/keys [id route] :as params}]
            {:outer/id     id
             :outer/router (get-initial-state RootRouter {})})
   :css   uicss/OuterBox}
  (let [{:keys [outer box]} (get-classnames OuterBox)]
    (div {:nonsense "TURN BACK, YE WHO ENTER THE DOMAIN OF HTML"
          :classes  [outer]}
         (ui-container-header
           (get-initial-state
             ContainerHeader
             {:container-header/id    id
              :container-header/route (first (dr/current-route this))}))
         (div {:classes [box]}
              (ui-root-router router)))))
(def ui-outer (factory OuterBox))

(defsc Page [this {:page/keys [outer sidebar]}]
  {:query [{:page/outer (get-query OuterBox)}
           {:page/sidebar (get-query Sidebar)}]
   :initial-state
          (fn [_] {:page/outer
                   (get-initial-state
                     OuterBox {:outer/id    1
                               :outer/route "home"})
                   :page/sidebar
                   (get-initial-state
                     Sidebar {:sidebar/id 1})})
   :css   [[:.page
            {:width           "100%"
             :height          "95%"
             :display         "flex"
             :justify-content "center"}]]}
  (let [{:keys [page]} (get-classnames Page)]
    (div {:classes [page]}
         (ui-outer outer)
         (ui-sidebar sidebar))))
(def ui-page (factory Page))

(defsc Root [this {:root/keys [page] :as props}]
  {:query [{:root/page (get-query Page)}]
   :initial-state
          (fn [_] {:root/page
                   (get-initial-state Page)})
   :css   [[:.container
            {:display         "flex"
             :align-items     "center"
             :justify-content "center"
             :height          "100%"
             :width           "100%"
             :overflow        "auto"}]]}
  (let [{:keys [container]} (get-classnames Root)
        about-timebox
        (:about/timebox
          (:com.fulcrologic.fulcro.routing.dynamic-routing/current-route
            (:outer/router
              (:page/outer page))))
        last-loaded-timebox-id (:timebox/id (last about-timebox))
        on-about-page (not (nil? about-timebox))]
    (div
      {:id       "root"
       :onScroll (m/load-next-timebox-on-scroll last-loaded-timebox-id on-about-page this a/Timebox)
       :classes  [container]}
      (style-element {:component Root})
      (style-element {:component h/Home})
      (style-element {:component ContainerHeader})
      (ui-page page))))
