(ns lab.working
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr :refer [defrouter]]
    [app.ui.bleeding :as b]
    [taoensso.timbre :as log]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p h4 ul a img li]]
    [com.fulcrologic.fulcro.ui-state-machines :as uism
     :refer [defstatemachine]]
    [com.fulcrologic.fulcro-css.css :as css]
    [app.ui.mutations :as uim]
    [app.ui.css :as uicss]
    [com.fulcrologic.fulcro-css.css-injection :as inj]))
;;Use this for experimental code that works.





