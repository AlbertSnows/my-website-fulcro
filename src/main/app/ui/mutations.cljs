(ns app.ui.mutations
  (:require
    [com.fulcrologic.fulcro.algorithms.normalized-state :refer [swap!->]]
    [com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]
    [com.fulcrologic.fulcro.components :as comp]
    [com.fulcrologic.fulcro.data-fetch :as df]
    [com.fulcrologic.fulcro.algorithms.data-targeting :as t]))

(defmutation swap [{:keys [name]}]
  (action
    [{:keys [state] :as env}]
    (swap!->
      state
      (update-in
        [:sidebar/id 1 :sidebar/state]
        (fn [_] name)))))

(defmutation toggle [{:keys [id]}]
  (action
    [{:keys [state] :as env}]
    (swap!->
      state
      (update-in [:sidebar/id id :sidebar/toggled]
                 #(if (= %1 "closed") "open" "closed")))))

(defn load-next-timebox [this last-loaded-timebox-id sc]
  (fn [e]
    (df/load!
      this
      [:timebox/id (dec last-loaded-timebox-id)]
      sc
      {:target
       (t/append-to
         [:component/id
          :about
          :about/timebox])})))