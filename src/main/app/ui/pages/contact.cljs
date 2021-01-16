(ns app.ui.pages.contact
  (:require
    [com.fulcrologic.fulcro.components :as comp
     :refer [defsc get-query get-initial-state]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div]]
    [app.ui.components :as c
     :refer [Image ui-image]]))

(defsc Contact [this props]
  {:ident         (fn [] [:component/id :contact])
   :query         []
   :route-segment ["contact"]}
  (div {:id "contact-container"}
    (ui-image {:image/id  "mail-big"
               :image/src "../images/mailV2.png"
               :image/alt "email"})
       (ui-image {:image/id  "mail-small"
                  :image/src "../images/mail_secure.PNG"
                  :image/alt "for security reasons"})))