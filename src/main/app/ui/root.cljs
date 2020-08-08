(ns app.ui.root
  (:require
    ;[app.model.session :as session]
    ;[clojure.string :as str]
    [com.fulcrologic.fulcro.dom :as dom :refer [div ul li p h3 button b]]
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

    ))

;(defn field [{:keys [label valid? error-message] :as props}]
;  (let [input-props (-> props (assoc :name label) (dissoc :label :valid? :error-message))]
;    (div :.ui.field
;      (dom/label {:htmlFor label} label)
;      (dom/input input-props)
;      (dom/div :.ui.error.message {:classes [(when valid? "hidden")]}
;        error-message))))

;(defsc SignupSuccess [this props]
;  {:query         ['*]
;   :initial-state {}
;   :ident         (fn [] [:component/id :signup-success])
;   :route-segment ["signup-success"]}
;  (div
;    (dom/h3 "Signup Complete!")
;    (dom/p "You can now log in!")))

;(defsc Signup [this {:account/keys [email password password-again] :as props}]
;  {:query             [:account/email :account/password :account/password-again fs/form-config-join]
;   :initial-state     (fn [_]
;                        (fs/add-form-config Signup
;                          {:account/email          ""
;                           :account/password       ""
;                           :account/password-again ""}))
;   :form-fields       #{:account/email :account/password :account/password-again}
;   :ident             (fn [] session/signup-ident)
;   :route-segment     ["signup"]
;   :componentDidMount (fn [this]
;                        (comp/transact! this [(session/clear-signup-form)]))}
;  (let [submit!  (fn [evt]
;                   (when (or (identical? true evt) (evt/enter-key? evt))
;                     (comp/transact! this [(session/signup! {:email email :password password})])
;                     (log/info "Sign up")))
;        checked? (fs/checked? props)]
;    (div
;      (dom/h3 "Signup")
;      (div :.ui.form {:classes [(when checked? "error")]}
;        (field {:label         "Email"
;                :value         (or email "")
;                :valid?        (session/valid-email? email)
;                :error-message "Must be an email address"
;                :autoComplete  "off"
;                :onKeyDown     submit!
;                :onChange      #(m/set-string! this :account/email :event %)})
;        (field {:label         "Password"
;                :type          "password"
;                :value         (or password "")
;                :valid?        (session/valid-password? password)
;                :error-message "Password must be at least 8 characters."
;                :onKeyDown     submit!
;                :autoComplete  "off"
;                :onChange      #(m/set-string! this :account/password :event %)})
;        (field {:label         "Repeat Password" :type "password" :value (or password-again "")
;                :autoComplete  "off"
;                :valid?        (= password password-again)
;                :error-message "Passwords do not match."
;                :onChange      #(m/set-string! this :account/password-again :event %)})
;        (dom/button :.ui.primary.button {:onClick #(submit! true)}
;          "Sign Up")))))

;(declare Session)

;(defsc Login [this {:account/keys [email]
;                    :ui/keys      [error open?] :as props}]
;  {:query         [:ui/open? :ui/error :account/email
;                   {[:component/id :session] (comp/get-query Session)}
;                   [::uism/asm-id ::session/session]]
;   :css           [[:.floating-menu {:position "absolute !important"
;                                     :z-index  1000
;                                     :width    "300px"
;                                     :right    "0px"
;                                     :top      "50px"}]]
;   :initial-state {:account/email "" :ui/error ""}
;   :ident         (fn [] [:component/id :login])}
;  (let [current-state (uism/get-active-state this ::session/session)
;        {current-user :account/name} (get props [:component/id :session])
;        initial?      (= :initial current-state)
;        loading?      (= :state/checking-session current-state)
;        logged-in?    (= :state/logged-in current-state)
;        {:keys [floating-menu]} (css/get-classnames Login)
;        password      (or (comp/get-state this :password) "")] ; c.l. state for security
;    (dom/div
;      (when-not initial?
;        (dom/div :.right.menu
;          (if logged-in?
;            (dom/button :.item
;              {:onClick #(uism/trigger! this ::session/session :event/logout)}
;              (dom/span current-user) ent/nbsp "Log out")
;            (dom/div :.item {:style   {:position "relative"}
;                             :onClick #(uism/trigger! this ::session/session :event/toggle-modal)}
;              "Login"
;              (when open?
;                (dom/div :.four.wide.ui.raised.teal.segment {:onClick (fn [e]
;                                                                        ;; Stop bubbling (would trigger the menu toggle)
;                                                                        (evt/stop-propagation! e))
;                                                             :classes [floating-menu]}
;                  (dom/h3 :.ui.header "Login")
;                  (div :.ui.form {:classes [(when (seq error) "error")]}
;                    (field {:label    "Email"
;                            :value    email
;                            :onChange #(m/set-string! this :account/email :event %)})
;                    (field {:label    "Password"
;                            :type     "password"
;                            :value    password
;                            :onChange #(comp/set-state! this {:password (evt/target-value %)})})
;                    (div :.ui.error.message error)
;                    (div :.ui.field
;                      (dom/button :.ui.button
;                        {:onClick (fn [] (uism/trigger! this ::session/session :event/login {:username email
;                                                                                             :password password}))
;                         :classes [(when loading? "loading")]} "Login"))
;                    (div :.ui.message
;                      (dom/p "Don't have an account?")
;                      (dom/a {:onClick (fn []
;                                         (uism/trigger! this ::session/session :event/toggle-modal {})
;                                         (dr/change-route this ["signup"]))}
;                        "Please sign up!"))))))))))))

;(def ui-login (comp/factory Login))

;(defsc Main [this props]
;  {:query         [:main/welcome-message]
;   :initial-state {:main/welcome-message "Hi!"}
;   :ident         (fn [] [:component/id :main])
;   :route-segment ["main"]}
;  (div :.ui.container.segment
;    (h3 "AYE WE IN THE MOTHAFUCKIN MAIN AREA BRO")))
;
;;(defsc Settings [this {:keys [:account/time-zone :account/real-name] :as props}]
;;  {:query         [:account/time-zone :account/real-name]
;;   :ident         (fn [] [:component/id :settings])
;;   :route-segment ["settings"]
;;   :initial-state {}}
;;  (div :.ui.container.segment
;;       (h3 "Settings")
;;       (div
;;        (p (b "Name: ") real-name)
;;        (p (b "Time Zone;;;s: ") time-zone))))
;
;(dr/defrouter TopRouter [this props]
;  {:router-targets [Main
;                    ;Signup
;                    ;SignupSuccess
;                    ;Settings
;                    ]})
;
;(def ui-top-router (comp/factory TopRouter))

;(defsc Session
;  "Session representation. Used primarily for server queries. On-screen representation happens in Login component."
;  [this {:keys [:session/valid? :account/name] :as props}]
;  {:query         [:session/valid? :account/name]
;   :ident         (fn [] [:component/id :session])
;   :pre-merge     (fn [{:keys [data-tree]}]
;                    (merge {:session/valid? false :account/name ""}
;                      data-tree))
;   :initial-state {:session/valid? false :account/name ""}})

;(def ui-session (comp/factory Session))

;(defsc TopChrome [this
;                  {:root/keys
;                   [router
;                    ;current-session
;                    ;login
;                    ]}]
;  {:query         [{:root/router (comp/get-query TopRouter)}
;                   ;{:root/current-session (comp/get-query Session)}
;                   [::uism/asm-id ::TopRouter]
;                   ;{:root/login (comp/get-query Login)}
;                   ]
;   :ident         (fn [] [:component/id :top-chrome])
;   :initial-state {:root/router          {}
;                   ;:root/login           {}
;                   ;:root/current-session {}
;                   }}
;  (let [current-tab
;        (some-> (dr/current-route this this) first keyword)]
;    (div :.ui.container
;      (div :.ui.secondary.pointing.menu
;        (dom/div :.item
;               ;{:classes [(when (= :main current-tab) "active")]
;               ; :onClick (fn [] (dr/change-route this ["main"]))}
;               "yo we up in the mothafuckin tabs up here yo")
;        ;(dom/a :.item {:classes [(when (= :settings current-tab) "active")]
;        ;               :onClick (fn [] (dr/change-route this ["settings"]))} "Settings")
;        ;(div :.right.menu
;        ;  ;(ui-login login)
;        ;     )
;           )
;      (div :.ui.grid
;        (div :.ui.row
;          (ui-top-router router))
;           )
;         )
;       ))

;(def ui-top-chrome (comp/factory TopChrome))

;(defsc Root [this {:root/keys [top-chrome]}]
;  {:query         [{:root/top-chrome (comp/get-query TopChrome)}]
;   :initial-state {:root/top-chrome {}}}
;  (ui-top-chrome top-chrome))

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
            (dom/div
              (dom/h4 label)
              (dom/ul
                (map #(ui-person (comp/computed % {:onDelete delete-person})) people)))))

(def ui-person-list (comp/factory PersonList))

(defsc Root [this {:root/keys [friends enemies]}]
       {:query         [{:root/friends (comp/get-query PersonList)}
                        {:root/enemies (comp/get-query PersonList)}]
        :initial-state (fn [params]
                           {:root/friends
                            (comp/get-initial-state PersonList {:id :friends :label "Friends"})
                            :root/enemies
                            (comp/get-initial-state PersonList {:id :enemies :label "Enemies"})})}
       (dom/div
         (ui-person-list friends)
         (ui-person-list enemies)))