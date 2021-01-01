(ns lab.bleeding
  (:require
    [taoensso.timbre :as log]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr :refer [defrouter]]
    [com.fulcrologic.fulcro-css.localized-dom :as ldom
     :refer [div label button span p h4 ul a img]]
    [com.fulcrologic.fulcro-css.css :as css]
    [app.ui.css :as uicss]
    [com.fulcrologic.fulcro-css.css-injection :as inj]
    [com.wsscode.pathom.connect :as pc]
    [com.fulcrologic.fulcro.mutations :as m]
    [com.fulcrologic.fulcro.dom :as dom]
    [com.fulcrologic.fulcro.data-fetch :as df]
    [com.fulcrologic.fulcro.components :as comp]))
;;Use this file for new, experimental code.
;; client

(defn page-exists? [state-map page-number]
  (let [page-items (get-in
                     state-map
                     [:page/by-number page-number :page/items])]
    (boolean (seq page-items))))

(defn init-page
  [state-map page-number]
  (assoc-in
    state-map
    [:page/by-number page-number :page/number]
    page-number))

(defn set-current-page
  [state-map page-number]
  (assoc-in state-map
    [:list/by-id 1 :list/current-page]
    [:page/by-number page-number]))

(defn clear-item
  [state-map item-id]
  (update state-map :items/by-id dissoc item-id))

(defn clear-page
  [state-map page-number]
  (let [page (get-in state-map [:page/by-number page-number])
        item-idents (:page/items page)
        item-ids (map second item-idents)]
    (as-> state-map s
      (update s :page-by-number dissoc page-number)
      (reduce (fn [acc id]
                (update acc :items/by-id dissoc id)) s item-ids))))

(defn gc-distant-pages
  [state-map page-number]
  (reduce (fn [s n]
            (if (< 4 (Math/abs (- page-number n)))
              (clear-page s n) s))
    state-map
    (keys (:page/by-number state-map))))

(declare ListItem)

(defn load-if-missing [{:keys [app state] :as env} page-number]
  (when-not (page-exists? @state page-number)
    (let [start (inc (* 10 (dec page-number)))
          end (+ start 9)]
      (df/load! app :paginate/items ListItem
        {:params {:start start :end end}
         :marker :page
         :target [:page/by-number page-number :page/items]}))))

(m/defmutation goto-page [{:keys [page-number]}]
  (action [{:keys [state] :as env}]
    (load-if-missing env page-number)
    (swap! state (fn [s]
                   (-> s
                     (init-page page-number)
                     (set-current-page page-number)
                     (gc-distant-pages page-number))))))

(defsc ListItem [this {:keys [item/id]}]
  {:query [:item/id :ui/fetch-state]
   :ident [:items/by-id :item/id]}
  (dom/li (str "Item " id)))

(def ui-list-item (comp/factory ListItem {:keyfn :item/id}))

(defsc ListPage [this {:keys [page/number page/items] :as props}]
  {:initial-state {:page/number 1 :page/items []}
   :query [:page/number {:page/items (comp/get-query ListItem)}
           [df/marker-table :page]]
   :ident [:page/by-number :page/number]}
  (let [status (get props [df/marker-table :page])]
    (dom/div
      (log/info "items: " items)
      (dom/p "page number " number)
      ;(if (df/loading? status)
      ;  (dom/div "loading...")
        (dom/ul (mapv ui-list-item items)
          ;)
        ))))

(def ui-list-page (comp/factory ListPage {:keyfn :page/number}))



;(defsc Root [this {:keys [pagination/list]}]
;  {:initial-state (fn [params]
;                    {:pagination/list (comp/get-initial-state LargeList {})})
;   :query [{:pagination/list (comp/get-query LargeList)}]}
;  (dom/div (ui-list list)))

(defn initialize
  [{:keys [app]}]
  (comp/transact! app [(goto-page {:page-number 1})]))
