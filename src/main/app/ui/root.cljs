(ns app.ui.root
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr]
    [com.fulcrologic.fulcro.ui-state-machines :as uism :refer [defstatemachine]]
    [com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]
    [com.fulcrologic.fulcro-css.css-injection :as inj]
    [com.fulcrologic.fulcro-css.css :as css]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span]]))


(defmutation toggle-item
  [{:keys [item]}]
  (action [{:keys [state]}] (str "wahhhh")))

(defsc SidebarContainer [this {:sidebar/keys [list-name] :as props} {:keys [inner-thing]}]
  {:query         [:sidebar/list-name]
   :ident         :sidebar/list-name
   :initial-state (fn [{:keys [list-name] :as params}] {:sidebar/list-name list-name})
   :css           [[:.green {:color "red"
                             :margin "0px"}]]}
  (let [{:keys [inner-thing]} (css/get-classnames SidebarContainer)]
    (dom/div
      (dom/ul :.green {:className inner-thing}
        (inj/style-element {:component SidebarContainer})
        (dom/li list-name)))))

(def ui-sidebar-container (comp/factory SidebarContainer))

(defsc Root [this {:root/keys [;primary-container
                                sidebar-container]}
             {:keys [background]}
             ]
  {:query [;{:root/primary-container (comp/get-query PersonList)}
           {:root/sidebar-container (comp/get-query SidebarContainer)}]
  :initial-state
          (fn [params]
            {;:root/friends
             ;(comp/get-initial-state PersonList
             ; {:id :friends :label "Friends"})
             :root/sidebar-container
             (comp/get-initial-state SidebarContainer {:list-name "Testing"})})
   :css   [[:.background
            {:width "100%"
             :height "100%"
             :color "blue"
             :background-image "url('/images/background.png')"
             :background-color "black"
             :background-position "center"
             :background-attachment "fixed"
             :background-repeat "no-repeat"
             :background-size "cover"}]]}
  (let [{:keys [background]} (css/get-classnames Root)]
    (dom/div :.background {:className [background]}
      (inj/style-element {:component Root})
      ;(ui-primary-container primary-container)
      (ui-sidebar-container sidebar-container))))
