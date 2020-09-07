(ns example
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

;(fn
;                    [{:keys [id] :as params}]
;                    {:sidebar/id {id
;                      {:sidebar/num 0}}})



;:root/sidebar-container
;(comp/get-initial-state SidebarContainer {:list-name "on"})})
;vvv
;:initial-state (fn [{:keys [list-name] :as params}] {:sidebar/list-name list-name})



;;some person stuff
(defmutation delete-person
  ;"Mutation: Delete the person with `name` from the list with `list-name`"
  [{:keys [list-name name]}] ; (1)
  (action [{:keys [state]}] ; (2)
    (let [path     (if (= "Friends" list-name)
                     [:friends :list/people]
                     [:enemies :list/people])
          old-list (get-in @state path)
          new-list (vec (filter #(not= (:person/name %) name) old-list))]
      (swap! state assoc-in path new-list))))

(defsc Person [this {:person/keys [name age] :as props} {:keys [onDelete]}]
  {:query         [:person/id :person/name :person/age] ; (2)
   :ident         (fn [] [:person/id (:person/id props)]) ; (1)
   :initial-state (fn [{:keys [id name age] :as params}]
                    {:person/id id :person/name name :person/age age})} ; (3)
  ;(dom/li
  ;  (dom/h5
      ;(str name " (age: " age ")")
      ;(dom/button {:onClick #(onDelete name)} "X")
      ;)
    ;)
  )

(def ui-person (comp/factory Person {:keyfn :person/id}))

(defsc PersonList [this {:list/keys [id label people] :as props}]
  {:query [:list/id :list/label {:list/people (comp/get-query Person)}]
   :ident (fn [] [:list/id (:list/id props)])
   :initial-state
          (fn [{:keys [id label]}]
            {:list/id     id
             :list/label  label
             :list/people (if (= id :friends)
                            [(comp/get-initial-state Person {:id 1 :name "Sally" :age 32})
                             (comp/get-initial-state Person {:id 2 :name "Joe" :age 22})]
                            [(comp/get-initial-state Person {:id 3 :name "Fred" :age 11})
                             (comp/get-initial-state Person {:id 4 :name "Bobby" :age 55})])})}
  (let [delete-person
        (fn [item-id]
          (comp/transact! this
            [(delete-person {:list id :item item-id})]))]
    ;(dom/div (dom/h4 label)
    ;  (dom/ul (map #(ui-person
    ;                  (comp/computed %
    ;                    {:onDelete delete-person})) people)))
    ))

(def ui-person-list (comp/factory PersonList))

;; OPTION 1: 4th arg destructing (requires adding props middleware)
(defsc PageContainer [this props computed {:keys [green]}]
  {:query [:text]
   :initial-state (fn [{:keys [text] :as params}] {:text text})
   :css   [[:.green {:color "green"}]]}

  ; OPTION 2: Destructure them explicitly
  (let [{:keys [green]} (css/get-classnames PageContainer)]
    ; OPTION 3:
    ; Use `localized-dom` keyword classes instead of `dom` for elements
    ;(dom/div :.green
    ;  (dom/li                                          ;{:classes [green]}
    ;    "bahhh"))
    ))

(def ui-test-element (comp/factory PageContainer))

(defsc Root [this {:root/keys [test-element friends enemies]} {:keys [background]}]
  {:query [{:root/test-element (comp/get-query PageContainer)}
           {:root/friends (comp/get-query PersonList)}
           {:root/enemies (comp/get-query PersonList)}]
   :initial-state
          (fn [params]
            {:root/test-element
             (comp/get-initial-state PageContainer)
             :root/friends
             (comp/get-initial-state PersonList
               {:id :friends :label "Friends"})
             :root/enemies
             (comp/get-initial-state PersonList
               {:id :enemies :label "Enemies"})})
   :css   [[:.background
            {:width "100%"
             :height "100%"
             :background-image "url('images/background.png')"
             :background-position "center"
             :background-attachment "fixed"
             :background-repeat "no-repeat"
             :background-size "cover"}]]}
  (let [{:keys [background]} (css/get-classnames Root)]
    ;(dom/div :.background
    ;  (inj/style-element {:component Root})
    ;  (ui-test-element test-element)
    ;  ;(ui-primary-container primary-container)
    ;  ;(ui-sidebar-container sidebar-container)
    ;  (ui-person-list friends)
    ;  (ui-person-list enemies)
    ;  )
    ))