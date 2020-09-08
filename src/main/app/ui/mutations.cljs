(ns app.ui.mutations
  (:require
    [com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]))

(defmutation toggle [{:button/keys [id]}]
  (action [{:keys [state]}]
    (swap! state update-in
           [:button/id id :button/num]
           #(if (= %1 1) 0 1))))
