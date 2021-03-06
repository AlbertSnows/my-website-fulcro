(ns app.setup.entrance
  (:require
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr]
    [com.fulcrologic.fulcro.application :as app]
    [com.fulcrologic.fulcro.components :as comp]
    [app.setup.application :refer [SPA]]
    [taoensso.timbre :as log]
    [app.ui.page :as root]))

(defn ^:export refresh []
  (app/mount! SPA root/Root "app")
  (comp/refresh-dynamic-queries! SPA))

(defn ^:export init []
  (app/set-root! SPA root/Root {:initialize-state? true})
  (dr/initialize! SPA)
  (dr/change-route! SPA ["home"])
  (app/mount! SPA root/Root "app" {:initialize-state? false}))
