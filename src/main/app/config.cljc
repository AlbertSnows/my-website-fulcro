(ns app.config
  "Single source of truth for values shared between the backend and frontend
  that aren't secrets (those live in src/main/config/*.edn instead).")

(def image-base-url "https://morning-river-8203.fly.storage.tigris.dev/images/")

(defn image-url [filename]
  (str image-base-url filename))
