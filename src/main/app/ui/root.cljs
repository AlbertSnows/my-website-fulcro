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

;(def sidebar-state (r/atom {:state "closed"}))
;:class (@sidebar-state :state)
;(menu-toggle sidebar-state)
(defmutation toggle-value [ignored]
  (action [{:keys [state]}]
    ;(if (= state "on")
    ;  (swap! state update :sidebar/num "off")
    ;  (swap! state update :sidebar/num "on"))
    (swap! state update :root/num inc)
    ))

;(defn toggle-value [atom key old-class new-class]
;  (if (= (@atom key) old-class)
;    (swap! atom assoc key new-class)
;    (swap! atom assoc key old-class)))

(defsc SidebarContainer [this {:sidebar/keys [num] :as props} {:keys [inner-thing]}]
  {:query         [:sidebar/num]
   :initial-state {:sidebar/num 0}
   :css           [[:.green {:color "red"
                             :margin "0px"}]]}
  (let [{:keys [inner-thing]} (css/get-classnames SidebarContainer)]
    (dom/div :.green {:className inner-thing}
      (dom/button
        {:onClick #(comp/transact! this `[(toggle-value {})])}
        "Times: " num))))

(def ui-sidebar-container (comp/factory SidebarContainer))

(defsc Root [this {:root/keys [;primary-container
                                ;sidebar-container
                               num
                               ]}
             {:keys [background]}
             ]
  {:query [;{:root/primary-container (comp/get-query PersonList)}
           ;{:root/sidebar-container (comp/get-query SidebarContainer)}
           :root/num
           ]
  :initial-state
          (fn [params]
            {;:root/friends
             ;(comp/get-initial-state PersonList
             ; {:id :friends :label "Friends"})
             ;:root/sidebar-container
             ;(comp/get-initial-state SidebarContainer)
             :root/num 0
             })
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
      ;(ui-sidebar-container sidebar-container)
      (dom/div
        (dom/h4 "This is an example.")
        (dom/button {:onClick #(comp/transact! this `[(toggle-value {})])}
          "you've clicked DISSSSS BUTOOOOOOOOOONNNNNNNNNNN about"
                    "...ohh, I dunno, " num " times maybe."
                    " Man this button text sure is unnecessarily long.")
        ))))
