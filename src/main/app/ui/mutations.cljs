(ns app.ui.mutations
	(:require
		[com.fulcrologic.fulcro.algorithms.normalized-state :refer [swap!->]]
		[com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]
		[com.fulcrologic.fulcro.components :as comp]))

(defmutation toggle [{:keys [id]}]
	(action
		[{:keys [state] :as env}]
		(swap!->
			state
			(update-in [:sidebar-contents/id id :sidebar-contents/state]
				#(if (= %1 "closed") "open" "closed"))
			(update-in [:sidebar/id id :sidebar/state]
				#(if (= %1 "closed") "open" "closed")))))

;(defsc ListItem [this {:keys [item/id]}]
;	{:query [:item/id :ui/fetch-state]
;	 :ident [:items/id :item/id]}
;	(dom/li (str "item " id)))
;
;(defsc ListPage [this {:keys [page/number page/items] :as props}]
;	{:initial-state {:page/number 1 :page/items []}
;	 :query         [:page/number {:page/items (comp/get-query ListItem)}]}
;	(let [status (get props [df/maker-table :page])]
;		(dom/div
;			(dom/p "Page number " number)
;			(if (df/loading? status)
;				(dom/div "loading...")
;				(dom/ul (mapv ui-list-item items))))))
;
;(defsc LargeList [this {:keys [list/current-page]}]
;	{:initial-state (fn [params] {:list/current-page (comp/get-initial-state ListPage {})})
;	 :query         [{:list/current-page (comp/get-query ListPage)}]
;	 :ident         (fn [] [:list/id 1])}
;	(let [{:keys [page/number]} current-page]
;		(dom/div
;			(dom/button {:disabled (= 1 number)
;									 :onClick  #(comp/transact! this `[(goto-page {:page-number ~(dec number)})])}) "Prior Page")
;		(dom/button {:onClick #(comp/transact! this `[(goto-page {:page-number ~(inc number)})])} "Next Page")
;		(ui-list-page current-page)))
;
;(defn airport-search [s]
;	(->> airports
;		(filter (fn [i] (str/includes?
;											(str/lower-case i)
;											(str/lower-case s))))
;		(take 10)
;		vec))
;
;(pc/defresolver list-resolver [env params]
;	{::pc/output [:autocomplete/airports]}
;	(let [search (get-in env [:ast :params :search])]
;		{:autocomplete/airports (airport-search search)}))
;
;(defn autocomplete-ident
;	[id-or-props]
;	(if (map? id-or-props)
;		[:autocomplete/bi-id (:db/id id-or-props)]
;		[:autocomplete/by-id id-or-props]))
;
;(defsc CompletionList [this {:keys [values onValueSelect]}]
;	(dom/ul nil
;		(map (fn [v]
;					 (dom/li {:key v}
;						 (dom/a {:href    "javascript:void(0)"
;										 :onClick #(onValueSelect v)}
;							 v))) values)))
;(def ui-completion-list (comp/factory CompletionList))
;
;(m/defmutation populate-loaded-suggestions
;  [{:keys [id]}]
;  (action [{:keys [state]}]
;    (let [autocomplete-path (autocomplete-ident id)
;          source-path (conj autocomplete-path :autocomplete/loaded-suggestions)
;          target-path (conj autocomplete-path :autocomplete/suggestions)]
;      (swap! state assoc-in target-path (get-in @state source-path)))))
;
;(def get-suggestions
;  (letfn [(load-suggestions [comp new-value id]
;            (df/load! comp :autocomplete/airports nil
;              {:params {:search new-value}
;               :marker false
;               :post-mutation `populate-loaded-suggestions
;               :post-mutation-params {:id id}
;               :target (conj (autocomplete-ident id)
;                         :autocomplete/loaded-suggestions)}))]
;    (gf/debounce load-suggestions 500)))
;
;(defsc Autocomplete
;  [this {:keys [db/id autocomplete/suggestions autocomplete/value] :as props}]
;  {:query [:db/id
;           :autocomplete/loaded-suggestions
;           :autocomplete/suggestions
;           :autocomplete/value]
;   :ident (fn [] (autocomplete-ident props))
;   :initial-state (fn [{:keys [id]}] {:db/id id
;                                      :autocomplete/suggestions []
;                                      :autocomplete/alue ""})}
;  (let [field-id (str "autocomplete-" id)
;        filtered-suggestions (when (vector? suggestions)
;                               (filter
;                                 #(str/includes?
;                                    (str/lower-case %)
;                                    (str/lower-case value)) suggestions))
;        exact-match? (and (= 1 (count filtered-suggestions)) (= value (first filtered-suggestions)))
;        onSelect (fn [v] (m/set-string! this :autocomplte/value :value v))]
;    (dom/div {:style {:height "600px"}}
;      (dom/label {:htmlFor field-id} "Airport: ")
;      (dom/input {:id field-id
;                  :value value
;                  :onChange
;                    (fn [evt]
;                      (let [new-value (.. evt -target -value)]
;                        (if (>= (.-length new-value) 2)
;                          (get-suggestions this new-value id)
;                          (m/set-value! this :autocomplete/suggestions []))
;                        (m/set-string! this :autocomplete/value :value new-value)))})
;      (when (and (vector? suggestions) (seq suggestions) (not exact-match?))
;        (ui-completion-list {:values filtered-suggestions
;                             :onValueSelect onSelect})))))
;(def ui-autocomplete (comp/factory Autocomplete))
;
;(defsc AutocompleteRoot [this {:keys [airport-input]}]
;  {:initial-state (fn [p] {:airport-input
;                           (comp/get-initial-state Autocomplete {:id :airports})})
;   :query [{:airport-input (comp/get-query Autocomplete)}]}
;  (dom/div
;    (dom/h4 "Airport Autocomplete")
;    (ui-autocomplete airport-input)))


;(defmutation load-text [{:keys [id]}]
;  ())
;
;(defmutation load-all [{:keys [id]}]
;  ())

;What do you mean? Maybe I'm being naive, but could you not just use fulcro load!s triggered when hitting the bottom of the page? Maybe have them parameterized so the server knows how many segment's you've already loaded?
;The initial load is relatively standard for fulcro. Depending on your app, that would be something that happens on page load, dynamic router events, or state machine events. Running raw load! s with segments-loaded parameter and post-mutation might do the trick for the rest of your needs.
;Of course, building a custom mutation for loading this would also not be very hard. Especially with built in ok-action and error-action

;const adjustPaddings = isScrollDown => {
;                                        const container = document.querySelector(".cat-list");
;                                              const currentPaddingTop = getNumFromStyle(container.style.paddingTop);
;                                        const currentPaddingBottom = getNumFromStyle(container.style.paddingBottom);
;                                              const remPaddingsVal = 170 * (listSize / 2);
;                                              if (isScrollDown) {
;                                                                 container.style.paddingTop = currentPaddingTop + remPaddingsVal + "px";
;                                                                                            container.style.paddingBottom = currentPaddingBottom === 0 ? "0px" : currentPaddingBottom - remPaddingsVal + "px";
;                                                                 } else {
;                                                                         container.style.paddingBottom = currentPaddingBottom + remPaddingsVal + "px";
;                                                                                                       container.style.paddingTop = currentPaddingTop === 0 ? "0px" : currentPaddingTop - remPaddingsVal + "px";
;                                                                         }
;                                        }

;const recycleDOM = firstIndex => {
;                                  for (let i = 0; i < listSize; i++) {
;                                             const tile = document.querySelector("#cat-tile-" + i);
;                                             tile.firstElementChild.innerText = DB[i + firstIndex].title;
;                                             tile.lastChild.setAttribute("src", DB[i + firstIndex].imgSrc);
;                                  }
;}

;const initIntersectionObserver = () => {
;                                        const options = {
;                                                         /* root: document.querySelector(".cat-list") */
;  }
;
;const callback = entries => {
;                             entries.forEach(entry => {
;                                                       if (entry.target.id === 'cat-tile-0') {
;                                                                                              topSentCallback(entry);
;                                                                                              } else if (entry.target.id === `cat-tile-${listSize - 1}`) {
;                                                                                                                                                          botSentCallback(entry);
;                                                                                                                                                          }
;                                                       });
;                             }
;
;var observer = new IntersectionObserver(callback, options);
;observer.observe(document.querySelector("#cat-tile-0"));
;observer.observe(document.querySelector(`#cat-tile-${listSize - 1}`));
;}