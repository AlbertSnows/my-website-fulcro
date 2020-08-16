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

;
;(defmutation toggle-item
;  [{:keys [item]}]
;  (action [{:keys [state]}] (str "wahhhh")))

;(def sidebar-state (r/atom {:state "closed"}))
;:class (@sidebar-state :state)
;(menu-toggle sidebar-state)

;(defmutation toggle-value [ignored]
;  (action [{:keys [state]}]
;    ;(if (= state "on")
;    ;  (swap! state update :sidebar/num "off")
;    ;  (swap! state update :sidebar/num "on"))
;    (swap! state update :sidebar/num inc)))

;(defmutation toggle-value [ignored]
;             (action [{:keys [state]}]
;                     (swap! state update :sidebar/num inc)))
;
;(defsc SidebarContainer [this {:sidebar/keys [num]}]
;  {:query         [:sidebar/num]
;   :initial-state {:sidebar/num 0}
;   :css           [[:.green {:color "red"
;                             :margin "0px"}]]}
;    (dom/div
;      (dom/button
;        {:onClick #(comp/transact! this `[(toggle-value {})])}
;        "Times: " num)))
;
;(def ui-sidebar-container (comp/factory SidebarContainer))

;(defsc Root [this {:root/keys [;primary-container
;                                sidebar-container
;                               ;num
;                               ]}
;             {:keys [background]}
;             ]
;  {:query [;{:root/primary-container (comp/get-query PersonList)}
;           {:root/sidebar-container (comp/get-query SidebarContainer)}
;           ;:root/num
;           ]
;  :initial-state
;          (fn [params]
;            {;:root/friends
;             ;(comp/get-initial-state PersonList
;             ; {:id :friends :label "Friends"})
;             :root/sidebar-container
;             (comp/get-initial-state SidebarContainer)
;             ;:root/num 0
;             }
;              )
;   :css   [[:.background
;            {:width "100%"
;             :height "100%"
;             :color "blue"
;             :background-image "url('/images/background.png')"
;             :background-color "black"
;             :background-position "center"
;             :background-attachment "fixed"
;             :background-repeat "no-repeat"
;             :background-size "cover"}]]}
;  (let [{:keys [background]} (css/get-classnames Root)]
;    (dom/div :.background {:className [background]}
;      (inj/style-element {:component Root})
;      ;(ui-primary-container primary-container)
;      (ui-sidebar-container sidebar-container)
;
;      ;  (dom/button {:onClick #(comp/transact! this `[(toggle-value {})])}
;
;             )))

;(defsc Person [this {:person/keys [num]} {:keys [toggle-value]}]
;       {:query         [:person/num]
;        :initial-state (fn [{:keys [num] :as params}]
;                           {:person/num num})}
;       (dom/li
;         (dom/h5
;           (str num " (age: " num ")")
;           (dom/button
;             {:onClick #(toggle-value num)}
;             "X"))))
;
;(def ui-person (comp/factory Person {:keyfn :person/name}))
;
;(defsc Root [this {:list/keys [num]}]
;       {:query [:list/num (comp/get-query Person)]
;        :initial-state
;               (fn [{:keys [num]}]
;                   {:list/num (comp/get-initial-state Person)})}
;       (let [delete-person
;             (fn [name]
;                 (println label "asked to delete" name))]
;            (dom/div
;              (dom/h4 num)
;              (dom/ul (map
;                        (fn [p]
;                            (ui-person
;                              (comp/computed
;                                p {:toggle-value delete-person})))
;                        label)))))

;(defmutation toggle-value
;  [{sidebar-id :sidebar/id}]
;  (action [{:keys [state]}]
;          (println "State: " state)
;          (swap! state update
;                 [:sidebar/id sidebar-id :sidebar/num]
;                 inc)))
;
;(defsc SidebarContainer
;  [this {:sidebar/keys [num] :as props}]
;  {:query         [:sidebar/id :sidebar/num]
;   :ident         (fn []
;                    [:sidebar/id (:sidebar/id props)])
;   :initial-state (fn
;                    [{:keys [id] :as params}]
;                    {:sidebar/id {id
;                      {:sidebar/num 0}}})
;   :css           [[:.green {:color "red"
;                             :margin "0px"}]]}
;  (dom/div
;    (println "num " num)
;    (dom/button
;      {:onClick #(comp/transact! this
;                   [(toggle-value {})])}
;      "Times: " num)))
;
;(def ui-sidebar-container
;  (comp/factory SidebarContainer))

;(defmutation increase
;  [{sidebar-id :sidebar/id}]
;  (action [{:keys [state]}]
;          (println "State: " state)
;          (swap! state update
;                 [:sidebar/id sidebar-id :sidebar/num]
;                 inc)))

(defmutation increase
  [{:button/keys [id]}]
  (action
    [{:keys [state]}]
    (swap! state update-in
           [:button/id id :button/num] inc)))

(defsc Button
  [this {:button/keys [id num] :as props}]
  {:query [:button/id :button/num]
   :ident :button/id
   :initial-state {:button/id :param/id :button/num 0}}
  (dom/button
    {:onClick
     #(comp/transact! this
        [(increase {:button/id id})])}
    "Times: " num))

(def ui-button (comp/factory Button))

(defsc Page
  [this {:page/keys [button]}]
  {:query [:page/button (comp/get-query Button)]
   :ident (fn [] [:page/id :page])
   :initial-state {:page/button {:button/id 1}}}
  (dom/div
    (ui-button button)))

(def ui-page (comp/factory Page))

(defsc Root
  [this {:root/keys [page] :as props}]
  {:query [{:root/page (comp/get-query Page)}]
   :initial-state {:root/page {:root/button 0}}}
   (ui-page page))