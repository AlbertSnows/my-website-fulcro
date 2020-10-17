(ns app.entrance
  (:require
    [app.application :refer [SPA]]
    [app.ui.root :as root]
    [com.fulcrologic.fulcro.application :as app]
    ;[com.fulcrologic.fulcro.networking.http-remote :as net]
    ;[com.fulcrologic.fulcro.data-fetch :as df]
    ;[com.fulcrologic.fulcro.ui-state-machines :as uism]
    [com.fulcrologic.fulcro.components :as comp]
    ;[com.fulcrologic.fulcro-css.css-injection :as cssi]
    ;[app.model.session :as session]
    [taoensso.timbre :as log]
    ;[com.fulcrologic.fulcro.algorithms.denormalize :as fdn]
    ;[com.fulcrologic.fulcro.algorithms.merge :as merge]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr]
    ;[com.fulcrologic.fulcro.inspect.inspect-client :as inspect]
    ))

(defn ^:export refresh []
  ;(log/info "Hot code Remount")
  ;(cssi/upsert-css "componentcss" {:component root/Root})
  ;(comp/refresh-dynamic-queries! root/Root)
  (app/mount! SPA root/Root "app")
  (comp/refresh-dynamic-queries! SPA))

(defn ^:export init []
  ;(log/info "Application starting.")
  ;(cssi/upsert-css "componentcss" {:component root/Root})
  ;(inspect/app-started! SPA)
  (app/set-root! SPA root/Root {:initialize-state? true})
  (dr/initialize! SPA)
  ; (log/info "Starting session machine.")
  ;(uism/begin! SPA session/session-machine ::session/session
    ;{
     ;:actor/login-form      root/Login
     ;:actor/current-session root/Session})
  (dr/change-route! SPA ["test"])
  (app/mount! SPA root/Root "app" {:initialize-state? false})
  ; (dr/change-route! SPA ["Home"])
  )
