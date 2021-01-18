(ns app.ui.mutations
	(:require
		[com.fulcrologic.fulcro.algorithms.normalized-state :refer [swap!->]]
		[com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]
		[com.fulcrologic.fulcro.components :as comp]))

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
