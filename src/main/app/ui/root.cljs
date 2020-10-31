(ns app.ui.root
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro-css.css-injection :as inj]
    [com.fulcrologic.fulcro-css.css :as css]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p h4 ul a]]
    [app.ui.working :as w :refer [OuterBox
                                  Sidebar
                                  ui-outer
                                  ui-sidebar
                                  ContainerHeader
                                  Home]]))

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
            (div {:classes [page]}
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
            (div
              {:classes [container]}
              (inj/style-element {:component Root})
              (inj/style-element {:component Home})
              (inj/style-element {:component ContainerHeader})
              (ui-page page))))
