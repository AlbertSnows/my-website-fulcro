(ns app.ui.bleeding
  (:require
    [taoensso.timbre :as log]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr :refer [defrouter]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p h4 ul a]]
    ))

;            {:top
;             {:link "https://en.wikipedia.org/wiki/Gaming"
;              :id "gamin"
;              :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
;              :alt "I play games I KNOW I'M SORRY"}
;             :bottom
;             {:link "https://www.whatisitliketobeaphilosopher.com/"
;              :id "pho"
;              :src "../images/the-thinker.png"
;              :alt "But really, what even IS a rock anyways???"}}

(defsc Second [this {:second/keys [second]}]
       {:query [:second/second]
        :initial-state
               (fn [{:keys [second]}]
                 {:second/second second})}
       (div
         (str "big secondo: " second)))
(def ui-second (comp/factory Second))

(defsc Test [this {:test/keys [id link src alt]}]
       {:query [:test/id
                :test/link
                {:test/src (comp/get-query Second)}
                :test/alt]
        :ident :test/id
        :initial-state
               (fn [{:keys [id link src alt]}]
                 {:test/id id
                  :test/link link
                  :test/src
                  (comp/get-initial-state Second {:second src})
                  :test/alt alt})}
       (div
         (str "Id: " id)
         (str "Link: " link)
         (ui-second src)
         (str "Alt: " alt)))
(def ui-test (comp/factory Test))

(defsc Home [this {:home/keys [first
                               second
                               third]}]
       {:query [{:home/first (comp/get-query Test)}
                {:home/second (comp/get-query Test)}
                {:home/third (comp/get-query Test)}]
        :route-segment ["home"]
        :ident (fn [] [:component/id :home])
        :initial-state
        (fn [_]
          {:home/first
           (comp/get-initial-state
             Test
             {:link "https://en.wikipedia.org/wiki/Gaming"
              :id "first"
              :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
              :alt "I play games I KNOW I'M SORRY"
              })
           :home/second
           (comp/get-initial-state
             Test
             {:link "https://en.wikipedia.org/wiki/Gaming"
              :id "second"
              :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
              :alt "I play games I KNOW I'M SORRY"
              })
           :home/third
           (comp/get-initial-state
             Test
             {:link "https://en.wikipedia.org/wiki/Gaming"
              :id "third"
              :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
              :alt "I play games I KNOW I'M SORRY"
              })})}
       (div
         (div "First: " (ui-test first))
         (div "Second: " (ui-test second))
         (div "Third: " (ui-test third))))