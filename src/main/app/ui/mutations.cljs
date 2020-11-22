(ns app.ui.mutations
  (:require
    [com.fulcrologic.fulcro.algorithms.normalized-state :refer [swap!->]]
    [com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]))

(defmutation toggle [{:keys [id]}]
  (action
    [{:keys [state] :as env}]
    (swap!->
      state
      (update-in [:sidebar-contents/id id :sidebar-contents/state]
                 #(if (= %1 "closed") "open" "closed"))
      (update-in [:sidebar/id id :sidebar/state]
                 #(if (= %1 "closed") "open" "closed")))))
