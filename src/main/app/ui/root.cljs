(ns app.ui.root
  (:require
    ;[app.model.session :as session]
    ;[clojure.string :as str]
    ;[com.fulcrologic.fulcro.dom :as dom :refer [div ul li p h3 button b]]
    ;[com.fulcrologic.fulcro.dom.html-entities :as ent]
    ;[com.fulcrologic.fulcro.dom.events :as evt]
    ;[com.fulcrologic.fulcro.application :as app]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr]
    [com.fulcrologic.fulcro.ui-state-machines :as uism :refer [defstatemachine]]
    [com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]
    ;[com.fulcrologic.fulcro.algorithms.merge :as merge]
    ;[com.fulcrologic.fulcro-css.css :as css]
    ;[com.fulcrologic.fulcro.algorithms.form-state :as fs]
    ;[taoensso.timbre :as log]
    [com.fulcrologic.fulcro-css.css-injection :as inj]
    [com.fulcrologic.fulcro-css.css :as css]
    [com.fulcrologic.fulcro-css.localized-dom :as dom :refer [div label button span]]

    ))


(defmutation delete-person
             "Mutation: Delete the person with `name` from the list with `list-name`"
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
       (dom/li
         (dom/h5
           (str name " (age: " age ")")
           (dom/button {:onClick #(onDelete name)} "X")))) ; (4)

(def ui-person (comp/factory Person {:keyfn :person/id}))

(defsc PersonList [this {:list/keys [id label people] :as props}]
       {:query [:list/id :list/label {:list/people (comp/get-query Person)}] ; (5)
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
             (fn [item-id] (comp/transact! this [(delete-person {:list id :item item-id})]))] ; (4)
            (dom/div (dom/h4 label)
              (dom/ul (map #(ui-person
                        (comp/computed %
                          {:onDelete delete-person})) people)))))

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
         (dom/div :.green
           (dom/li                                          ;{:classes [green]}
             "bahhh"))))

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
             :background-size "cover"
             }]]}
  (let [{:keys [background]} (css/get-classnames Root)]
    (dom/div :.background
      (inj/style-element {:component Root})
      (ui-test-element test-element)
      ;(ui-primary-container primary-container)
      ;(ui-sidebar-container sidebar-container)
      (ui-person-list friends)
      (ui-person-list enemies)
      )))