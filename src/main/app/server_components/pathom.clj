(ns app.server-components.pathom
  (:require
    [mount.core :refer [defstate]]
    [taoensso.timbre :as log]
    [com.wsscode.pathom.connect :as pc]
    [com.wsscode.pathom.core :as p]
    [com.wsscode.common.async-clj :refer [let-chan]]
    [clojure.core.async :as async]
    [app.backend.data :as bd]
    [app.server-components.config :refer [config]]
    [app.backend.mock-database :as db]
    [com.fulcrologic.fulcro.components :as comp]
    [app.backend.helpers.core :as hc]
    [app.backend.resolvers.timebox :as rt]))

;; server
(def all-resolvers [rt/resolvers])

(defn preprocess-parser-plugin
  "Helper to create a plugin that can view/modify the env/tx of a top-level request.

  f - (fn [{:keys [env tx]}] {:env new-env :tx new-tx})

  If the function returns no env or tx, then the parser will not be called (aborts the parse)"
  [f]
  {::p/wrap-parser
   (fn transform-parser-out-plugin-external [parser]
     (fn transform-parser-out-plugin-internal [env tx]
       (let [{:keys [env tx] :as req} (f {:env env :tx tx})]
         (if (and (map? env) (seq tx))
           (parser env tx)
           {}))))})

(defn log-requests [{:keys [env tx] :as req}]
  (log/debug "Pathom transaction:" (pr-str tx))
  req)

(defn build-parser [db-connection]
  (let [real-parser
        (p/parallel-parser
          {::p/mutate  pc/mutate-async
           ::p/env     {::p/reader               [p/map-reader pc/parallel-reader
                                                  pc/open-ident-reader p/env-placeholder-reader]
                        ::p/placeholder-prefixes #{">"}
                        ::pc/mutation-join-globals [:tempids]}
           ::p/plugins [(pc/connect-plugin {::pc/register all-resolvers})
                        (p/env-wrap-plugin (fn [env]
                                             ;; Here is where you can dynamically add things to the resolver/mutation
                                             ;; environment, like the server config, database connections, etc.
                                             (assoc env
                                               :db @db-connection ; real datomic would use (d/db db-connection)
                                               :connection db-connection
                                               :config config)))
                        (preprocess-parser-plugin log-requests)
                        p/error-handler-plugin
                        p/request-cache-plugin
                        (p/post-process-parser-plugin p/elide-not-found)
                        p/trace-plugin]})
        ;; NOTE: Add -Dtrace to the server JVM to enable Fulcro Inspect query performance traces to the network tab.
        ;; Understand that this makes the network responses much larger and should not be used in production.
        trace? (not (nil? (System/getProperty "trace")))]
    (fn wrapped-parser [env tx]
      (async/<!! (real-parser
                   env (if trace?
                         (conj tx :com.wsscode.pathom/trace)
                         tx))))))

(defstate parser
          :start (build-parser db/conn))

