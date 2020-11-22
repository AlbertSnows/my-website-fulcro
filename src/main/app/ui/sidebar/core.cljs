(ns app.ui.sidebar.core
  (:require
    [com.fulcrologic.fulcro.components :as comp
     :refer [defsc factory get-query get-initial-state transact!]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr
     :refer [defrouter change-route!]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div li a ul p]]
    [app.ui.mutations :as uim]))

(defsc ListItem [this {:list-item/keys [name classes] :as props}]
  {:query         [:list-item/name :list-item/classes]
   :initial-state (fn [{:list-item/keys [name classes] :as props}]
                    {:list-item/name    name
                     :list-item/classes classes})}
  (li {:className classes
       :onClick   (fn [] (change-route! this [name]))}
      (a name)))
(def ui-list-item (factory ListItem {:keyfn :list-item/name}))

(defsc SidebarContents
  [this {:sidebar-contents/keys [id home about contact projects] :as props}]
  {:query [:sidebar-contents/id
           {:sidebar-contents/home (get-query ListItem)}
           {:sidebar-contents/about (get-query ListItem)}
           {:sidebar-contents/contact (get-query ListItem)}
           {:sidebar-contents/projects (get-query ListItem)}]
   :ident :sidebar-contents/id
   :initial-state
          (fn [_]
            {:sidebar-contents/id 1
             :sidebar-contents/home
                                  (get-initial-state
                                    ListItem
                                    {:list-item/name    "home"
                                     :list-item/classes "sidebar-brand"})
             :sidebar-contents/about
                                  (get-initial-state
                                    ListItem
                                    {:list-item/name    "about"
                                     :list-item/classes ""})
             :sidebar-contents/contact
                                  (get-initial-state
                                    ListItem
                                    {:list-item/name    "contact"
                                     :list-item/classes ""})
             :sidebar-contents/projects
                                  (get-initial-state
                                    ListItem
                                    {:list-item/name    "projects"
                                     :list-item/classes ""})})}
  (ul {:className "sidebar-entries sidebar-nav"}
      (ui-list-item home)
      (ui-list-item about)
      (ui-list-item contact)
      (ui-list-item projects)))
(def ui-sidebar-contents (factory SidebarContents))

(defsc SidebarButton [this {:button/keys [id] :as props}]
  {:query [:button/id]
   :ident :button/id
   :initial-state
          (fn [{:button/keys [id] :as params}]
            {:button/id id})}
  (div {:id "sidebar-toggle-button"
        :onClick
            #(transact! this
                        `[(uim/toggle ~{:id id})])}
       (p "[")))
(def ui-button (factory SidebarButton))

(defsc Sidebar [this {:sidebar/keys [id state button contents] :as props}]
  {:query [:sidebar/id
           :sidebar/state
           {:sidebar/button (get-query SidebarButton)}
           {:sidebar/contents (get-query SidebarContents)}]
   :ident :sidebar/id
   :initial-state
          (fn [{:sidebar/keys [id] :as props}]
            {:sidebar/id    id
             :sidebar/state "closed"
             :sidebar/button
                            (get-initial-state
                              SidebarButton
                              {:button/id 1})
             :sidebar/contents
                            (get-initial-state
                              SidebarContents)})}
  (div {:className (str state " sidebar-wrapper")}
       (ui-button button)
       (ui-sidebar-contents contents)))
(def ui-sidebar (factory Sidebar))