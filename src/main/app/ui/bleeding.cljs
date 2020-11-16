(ns app.ui.bleeding
  (:require
    [taoensso.timbre :as log]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr :refer [defrouter]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p h4 ul a img]]
    [com.fulcrologic.fulcro-css.css :as css]
    [app.ui.css :as uicss]
    [com.fulcrologic.fulcro-css.css-injection :as inj]))

(defsc Image [this {:image/keys [id src alt]}]
  {:query [:image/id :image/src :image/alt]
   :initial-state
          (fn [{:keys [src id alt]}]
            {:image/src src
             :image/id  (str id "-img")
             :image/alt alt})}
  (img {:src src :id id :alt alt}))
(def ui-image (comp/factory Image {:keyfn :image/id}))

(defsc Href [this {:href/keys [id link image]}]
  {:query [:href/id
           :href/link
           {:href/image (comp/get-query Image)}]
   :ident :href/id
   :initial-state
          (fn [{:keys [id link src alt]}]
            {:href/id   (str id "-href")
             :href/link link
             :href/image
                        (comp/get-initial-state
                          Image
                          {:src src
                           :id  id
                           :alt alt})})}
  (a {:href   link
      :target "__blank"
      :rel    "noopener noreferrer"}
     (ui-image image)))
(def ui-href (comp/factory Href {:keyfn :href/id}))

(defn add-id [comp-id sub-comp]
  (if (nil? (:id sub-comp))
    (merge sub-comp {:id comp-id})
    (merge sub-comp {:id (str comp-id "-" (:id sub-comp))})))
(defn apply-contained-component [component]
    ((:ui component)
     (comp/get-initial-state
       (:factory component)
       (if (vector? (:data component))
         (mapv
           (fn [sub-component]
             (add-id (:id component) sub-component))
           (:data component))
         (add-id (:id component) (:data component))))))
(defn div-with-classes [classes contents]
  (div {:className classes} contents))
(defn div-with-classes-and-id [id classes contents]
  (div {:id id :className classes} contents))
(defn create-div-id [component id]
  (str (:id component) "-" id))
(defn append-id [component id]
  (merge component {:id (create-div-id component id)}))
(defn rand-str [len]
  (apply str (take len (repeatedly #(char (+ (rand 26) 65))))))

(defn GenericPTHtml [classes]
  (div-with-classes classes
                    (fn [components]
                      (mapv apply-contained-component components))))
(defn GenericEHtml [classes]
  (div-with-classes classes
    (fn [ui factory state]
      (ui (comp/get-initial-state factory state)))))

(defsc Top
  [this {:top/keys [components id]}]
  {:ident :top/id
   :query [:top/components
           :top/id]
   :initial-state
          (fn [components]
            {:top/components components
             :top/id (:id (first components))})}
  ;(div-with-classes-and-id
    (div
      {:id (str id "-top")
       :className "top-div"
       }
      (mapv
        apply-contained-component
        components)
      )


  ;)
)
(def ui-top (comp/factory Top {:keyfn :top/id}))

(defsc Bottom
  [this {:bottom/keys [components]}]
  {:query [:bottom/components
           :bottom/id]
   :initial-state
          (fn [components]
            {:bottom/components components
             :bottom/id (:id (first components))})}
  (div-with-classes-and-id
    (create-div-id (first components) "bottom")
    "bottom-div"
    (mapv apply-contained-component components)))
(def ui-bottom (comp/factory Bottom {:keyfn :bottom/id}))



(defsc Left
  [this {:left/keys [components id]}]
  {:query [:left/components
           :left/id]
   :initial-state
    (fn [components]
      {:left/components components
       :left/id (:id (first components))})}
  ;((GenericPTHtml "left-side") components)
  ;(div {:className "left-side"}
  ;     (mapv apply-contained-component components)
  ;     )
  ;(div-with-classes
  ;  "left-side"
  ;  (mapv apply-contained-component components))
  (div {:id (create-div-id (first components) "left")
        :className "left-side"}
       (mapv (fn [component]
               (apply-contained-component
                 (append-id component "left")))
             components)))
(def ui-left-side (comp/factory Left {:keyfn :left/id}))

(defsc Middle [this {:middle/keys [id ui factory data] :as props}]
  {:ident :middle/id
   :query [:middle/id
           :middle/ui
           :middle/factory
           :middle/data]
   :initial-state
          (fn [{:keys [id state ui factory]}]
            {:middle/id      id
             :middle/ui      ui
             :middle/factory factory
             :middle/data    state})
   :css   (:css uicss/Middle)}
  (let [{:keys [middle-main-page padding-bottom]} (css/get-classnames Middle)]
    (dom/div {:id id :className "middle"}
             (mapv (fn [thing]
                     (ui (comp/get-initial-state
                           factory thing))) data))))
(def ui-middle (comp/factory Middle {:keyfn :middle/id}))

;(defsc Right [this {:right-side/keys [top bottom] :as props}
;                  ]
;  {:query [{:right-side/top (comp/get-query Top)}
;           {:right-side/bottom (comp/get-query Bottom)}]
;   :initial-state
;          (fn [{:keys [top bottom] :as params}]
;            {:right-side/top
;             (comp/get-initial-state
;               Top
;               top)
;             :right-side/bottom
;             (comp/get-initial-state
;               Bottom
;               bottom)})
;   :css   [[:.right-side
;            {:display        "flex"                         ;
;             :flex-direction "column"
;             :align-items    "center"
;             :padding-right  "1.5em"
;             :width          "100%"}]
;           [:.right-side>a+a
;            {:padding-top "6em"}]]}
;  (dom/div {:className "right-side"}
;           (ui-top top)
;           (ui-bottom bottom))
;  )
(defsc Right
  [this {:right/keys [components]}]
  {:query [:right/components]
   :initial-state
          (fn [components]
            {:right/components components})}
  ;((GenericPTHtml "right-side") components)
  (dom/div)
  )
(def ui-right-side (comp/factory Right))

(defsc Text [this {:text/keys [id text]}]
  {:query         [:text/id :text/text]
   :initial-state (fn [{:keys [id text]}]
                    {:text/id   id
                     :text/text text})}
  (p {:id id} text))
(def ui-text (comp/factory Text {:keyfn :text/id}))

(def home-initial-state
  {:home/left
   (comp/get-initial-state
     Left
     [{:id "home"
       :ui ui-top
       :factory Top
       :data
        [{:ui ui-href
          :factory Href
          :data
          {:link "https://en.wikipedia.org/wiki/Gaming"
           :id   "gamin"
           :src  "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
           :alt  "I play games I KNOW I'M SORRY"}}]}
      ;{:ui ui-bottom
      ; :factory Bottom
      ; :state
      ; [{:ui ui-href
      ;   :factory Href
      ;   :state
      ;   {:link "https://www.whatisitliketobeaphilosopher.com/"
      ;    :id   "pho"
      ;    :src  "../images/the-thinker.png"
      ;    :alt  "But really, what even IS a rock anyways???"}}]
      ; }
      ])
   :home/middle
   (comp/get-initial-state
     Middle
     {:id      "home-middle"
      :state
               [
                {:id   "large-text"
                 :text "Mostly this stuff"}
                {:id   "small-text"
                 :text "(check out my projects for novel things)"}
                ]
      :factory Text
      :ui      ui-text})
   ;:home/right
     ;(comp/get-initial-state
     ;  Right
     ;  [{:ui ui-top
     ;    :factory Top
     ;    :state
     ;        [{:ui ui-href
     ;          :factory Href
     ;          :state
     ;          {:link "https://www.youtube.com/"
     ;           :id   "Tube"
     ;           :src  "../images/tubes.png"
     ;           :alt  "Youtube is my Netflix, sadly"}}]}
     ;   {:ui ui-bottom
     ;    :factory Bottom
     ;    :state
     ;        [{:ui ui-href
     ;          :factory Href
     ;          :state
     ;          {:link "https://en.wikipedia.org/wiki/Programmer"
     ;           :id   "debug"
     ;           :src  "../images/meirl.png"
     ;           :alt  "g! 'How to print newline in cljs'"}}
     ;         ]}])
   })
(defsc Home [this {:home/keys [left middle right]}]
  {:query         [{:home/left (comp/get-query Left)}
                   {:home/middle (comp/get-query Middle)}
                   {:home/right (comp/get-query Right)}]
   :route-segment ["home"]
   :ident         (fn [] [:component/id :home])
   :initial-state
                  (fn [_] home-initial-state)}
  (div {:className "home"}
       (ui-left-side left)
       (ui-middle middle)
       (ui-right-side right)
       ))