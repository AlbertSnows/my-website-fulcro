(ns app.setup.server-main
  (:require
    [mount.core :as mount]
    app.server-components.http-server)
  (:gen-class))
; npx shadow-cljs release main
; prod command: clojure -X:depstar uberjar :aot true :jar fulcro.jar :main-class app.setup.server-main
; java -jar fulcro.jar
; This is a separate file for the uberjar only. We control the server in dev mode from src/dev/user.clj
(defn -main [& args]
  (mount/start-with-args {:config "config/prod.edn"}))
