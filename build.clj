(ns build
  (:require [clojure.tools.build.api :as b]))

(def build-folder "target")
(def jar-content (str build-folder "/classes"))

(def basis (b/create-basis {:project "deps.edn"}))
(def version "0.0.1")
(def app-name "fulcro")

; path for result uber file
(def uber-file-name "prod_build.jar")                       ;(format "%s/%s-%s-standalone.jar" build-folder app-name version))


(defn clean [_]
      (b/delete {:path "target"})
      (println (format "Build folder \"%s\" removed" build-folder)))

(defn uber [_]
      (clean nil)

      (b/copy-dir {:src-dirs   ["src/main" "resources"]         ; copy resources
                   :target-dir jar-content})

      (b/compile-clj {:basis     basis               ; compile clojure code
                      :src-dirs  ["src/main"]
                      :class-dir jar-content})

      (b/uber {:class-dir jar-content                ; create uber file
               :uber-file uber-file-name
               :basis     basis
               :main      'app.setup.server-main})                ; here we specify the entry point for uberjar

      (println (format "Uber file created: \"%s\"" uber-file-name)))