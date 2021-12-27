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

(defn load-next-timebox-on-scroll [last-loaded-timebox-id on-about-page this sc]
  (fn [e]
    (let [target (.-target e)
          position-on-page (- (.-scrollHeight target) (.-scrollTop target))
          bottom-of-page-threshold (+ (.-clientHeight target) 6)
          scrolled-to-bottom-on-about-page-with-more-timeboxes-to-load
          (and
            (<= position-on-page bottom-of-page-threshold)
            (> last-loaded-timebox-id 1)
            (true? on-about-page))]
      (when scrolled-to-bottom-on-about-page-with-more-timeboxes-to-load
        (df/load! this [:timebox/id (dec last-loaded-timebox-id)]
                  sc
                  {:target
                   (t/append-to
                     [:component/id
                      :about
                      :about/timebox])})))))