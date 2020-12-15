(ns app.ui.pages.contact
  (:require
    [com.fulcrologic.fulcro.components :as comp
     :refer [defsc get-query get-initial-state]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div]]
    [app.ui.components :as c
     :refer [Image ui-image]]))

(def contact-initial-state
  {:contact/image
   (get-initial-state
     Image
     {:id  "mail-big"
      :src "../images/mailV2.png"
      :alt "email"})
   :contact/span
   (get-initial-state
     Image
     {:id  "mail-small"
      :src "../images/mail_secure.PNG"
      :alt "for security reasons"})})
(defsc Contact [this {:contact/keys [image span] :as props}]
  {:ident         (fn [] [:component/id :contact])
   :query         [{:contact/image (get-query Image)}
                   {:contact/span (get-query Image)}]
   :initial-state (fn [_] contact-initial-state)
   :route-segment ["contact"]}
  (div {:id "contact-container"} (ui-image image)
       (ui-image span)))