(ns app.ui.pages.contact
  (:require
    [com.fulcrologic.fulcro.components :as comp
     :refer [defsc get-query get-initial-state]]
    [com.fulcrologic.fulcro.dom :as dom
     :refer [div]]
    [app.ui.components :as c
     :refer [Image ui-image]]
    [app.config :refer [image-url]]))

(defsc Contact [this props]
  {:ident         (fn [] [:component/id :contact])
   :query         ['*]
   :initial-state {}
   :route-segment ["contact"]}
  (div {:id "contact-container"}
    (ui-image {:image/id  "mail-big"
               :image/src (image-url "mailV2.PNG")
               :image/alt "email"})
       (ui-image {:image/id  "mail-small"
                  :image/src (image-url "mail_secure.PNG")
                  :image/alt "for security reasons"})))