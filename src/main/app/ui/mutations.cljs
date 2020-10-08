(ns app.ui.mutations
  (:require
    [com.fulcrologic.fulcro.algorithms.normalized-state :refer [swap!->]]
    [com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]))

(defmutation toggle [{:keys [id]}]
  (action [{:keys [state] :as env}]
      (swap!-> state
         (update-in [:button/id id :button/num]
                    #(if (= %1 1) 0 1))
         (update-in [:sidebar-contents/id id :sidebar-contents/state]
                    #(if (= %1 1) 0 1)))
    ;(swap! state update-in [:button/id id :button/num]
    ;    #(if (= %1 1) 0 1))
          )
  )
