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
(defn apply-contained-component [{:keys [ui data id factory]}]
  (ui
    (comp/get-initial-state
      factory
      (if (vector? data)
        (mapv
          (fn [sub-component]
            (add-id id sub-component))
          data)
        (add-id id data)))))
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






(defsc ProjectDescription [this {:description/keys [header body]}]
  {:ident :description/id
   :query [:description/id :description/header :description/body]
   :initial-state
          (fn [{:keys [id header body] :as props}]
            {:description/id     (str id "-description")
             :description/header header
             :description/body   body})}
  (div
    (str "Header: " header)
    (str "Body: " body)))
(def ui-project-description
  (comp/factory ProjectDescription {:keyfn :description/id}))

(defsc ProjectBox [this {:box/keys [id description href] :as props}]
  {:ident :box/id
   :query [:box/id
           {:box/description (comp/get-query ProjectDescription)}
           {:box/href (comp/get-query Href)}]
   :initial-state
          (fn [{:keys [id description href]}]
            {:box/id id
             :box/description
                     (comp/get-initial-state
                       ProjectDescription
                       (merge description {:id id}))
             :box/href
                     (comp/get-initial-state Href
                                             (merge href {:id id}))})}
  (div
    (ui-project-description description)
    (ui-href href)))
(def ui-project-box (comp/factory ProjectBox {:keyfn :box/id}))

(defsc Projects [this {:projects/keys [contents] :as props}]
  {:ident         (fn [] [:component/id :projects])
   :route-segment ["projects"]
   :query         [:projects/contents]
   :initial-state
                  (fn [_]
                    {:projects/contents
                     [(comp/get-initial-state
                        ProjectBox
                        {:id "my-website"
                         :description
                             {:header "This Website"
                              :body   "The website you're perusing."}
                         :href
                             {:link "https://github.com/AlbertSnows/my-website"
                              :alt  "This Website"
                              :src  "../images/this_website.PNG"}})
                      (comp/get-initial-state
                        ProjectBox
                        {:id "first-website"
                         :description
                             {:header "My First Website"
                              :body   "First website for class.\n                  Written from Javascript\n                  to-> Typescript\n                  to-> Clojurescript over the semester"}
                         :href
                             {:link "https://github.com/AlbertSnows/FWRcljs"
                              :alt  "My First Website"
                              :src  "../images/kistners_flowers.PNG"}})
                      (comp/get-initial-state
                        ProjectBox
                        {:id "snake-game"
                         :description
                             {:header "Snake Game"
                              :body   "Walkthrough re-write of Snake in Rust"}
                         :href
                             {:link "https://github.com/AlbertSnows/snake_game"
                              :alt  "Snake Game"
                              :src  "../images/snake_rust.PNG"}})
                      (comp/get-initial-state
                        ProjectBox
                        {:id "game-jam-2018"
                         :description
                             {:header "To Change A Light Bulb"
                              :body   "A beautiful disaster written over a weekend during a game jam"}
                         :href
                             {:link "https://github.com/AlbertSnows/To-Change-A-Lightbulb"
                              :alt  "To Change A Light Bulb"
                              :src  "../images/lightbulb.PNG"}})
                      (comp/get-initial-state
                        ProjectBox
                        {:id "thermal-modeling"
                         :description
                             {:header "Human Thermal Modeling"
                              :body   "Multi-threading graduate student's research code"}
                         :href
                             {:link "https://github.com/AlbertSnows/HumanThermalModeling"
                              :alt  "Human Thermal Modeling"
                              :src  "../images/thermal_modeling.PNG"}})
                      (comp/get-initial-state
                        ProjectBox
                        {:id "roguelike"
                         :description
                             {:header "2D Rogue Like"
                              :body   "Tutorial game made in Unity"}
                         :href
                             {:link "https://github.com/AlbertSnows/2DRogueLike"
                              :alt  "2D Rogue Like"
                              :src  "../images/2Drouge.PNG"}})
                      (comp/get-initial-state
                        ProjectBox
                        {:id "edgesweeper"
                         :description
                             {:header "Edgesweeper"
                              :body   "Took Minesweeper, written in python, and added a feature"}
                         :href
                             {:link "https://github.com/AlbertSnows/python-tkinter-minesweeper"
                              :alt  "Edgesweeper"
                              :src  "../images/minesweeper.PNG"}})
                      (comp/get-initial-state ProjectBox
                                              {:id "first-unity"
                                               :description
                                                   {:header "Roll A Ball"
                                                    :body   "First tutorial exposure to Unity"}
                                               :href
                                                   {:link "https://github.com/AlbertSnows/RollABall"
                                                    :alt  "Roll A Ball"
                                                    :src  "../images/rollaball.PNG"}})
                      (comp/get-initial-state ProjectBox
                                              {:id "mobile-app-game"
                                               :description
                                                   {:header "Simple App"
                                                    :body   "Exposure to app development in Java/Kotlin via Android Studio"}
                                               :href
                                                   {:link "https://github.com/AlbertSnows/SimpleMobileGame"
                                                    :alt  "Simple App"
                                                    :src  "../images/first_app.PNG"}})]})}
  (div {:id "project-page-body"}
       (mapv ui-project-box contents)))

(defsc Gallery
  [this {:gallery/keys [photos id] :as props}]
  {:id    :gallery/id
   :query [:gallery/photos
           :gallery/id]
   :initial-state
          (fn [gallery]
            {:gallery/photos gallery
             :gallery/id     (:id (first gallery))})}
  (dom/div {:id        (str id "-gallery")
            :className "gallery"}
           (mapv
             (fn [photo]
               (ui-image
                 (comp/get-initial-state
                   Image
                   photo)))
             photos)))
(def ui-gallery (comp/factory Gallery {:keyfn :gallery/id}))

(defsc Left
  [this {:left/keys [components id]}]
  {:query [:left/components
           :left/id]
   :initial-state
          (fn [components]
            {:left/components components
             :left/id         (:id (first components))})}
  (div {:id        (str id "-left")
        :className "left-side"}
       (mapv (fn [component]
               (apply-contained-component
                 (append-id component "left")))
             components)))
(def ui-left (comp/factory Left {:keyfn :left/id}))


(defsc Middle [this {:middle/keys [id components] :as props}]
  {:ident :middle/id
   :query [:middle/id :middle/components]
   :initial-state
          (fn [components]
            {:middle/id (:id (first components))
             :middle/components components})
   :css   (:css uicss/Middle)}
  (let [{:keys [middle-main-page padding-bottom]} (css/get-classnames Middle)]
    (dom/div
      {:id (create-div-id id "middle") :className "middle"}
      (mapv (fn [component]
              (apply-contained-component
                (append-id component "middle")))
            components))))
(def ui-middle (comp/factory Middle {:keyfn :middle/id}))

(defsc Right
  [this {:right/keys [components id] :as props}]
  {:query [:right/components :right/id]
   :initial-state
          (fn [components]
            {:right/components components
             :right/id         (:id (first components))})
   :css   [[:.right
            {:display        "flex"                         ;
             :flex-direction "column"
             :align-items    "center"
             :padding-right  "1.5em"
             :width          "100%"}]
           [:.right>a+a
            {:padding-top "6em"}]]}
  (div {:id        (create-div-id id "right")
        :className "right-side"}
       (mapv (fn [component]
               (apply-contained-component
                 (append-id component "right")))
             components)))
(def ui-right (comp/factory Right {:keyfn :right/id}))


(def node-options
  {:first
   {:id  "end-node"
    :alt "The future is yet to come"
    :src "../images/end-node.PNG"}
   :middle
   {:id  "middle-node"
    :alt "arbitrary point in timeline"
    :src "../images/middle-node.PNG"}
   :end
   {:id  "end-of-the-road"
    :alt "end of the node, cowboy"
    :src "../images/end-of-the-road.PNG"}})
(defsc AboutMiddle [this {:middle/keys [id] :as props}]
  {:query [:middle/id]
   :ident :middle/id
   :initial-state
          (fn [{:keys [id]}]
            {:middle/id id})
   :css   [[:.middle-main-page
            {:display         "flex"
             :flex-direction  "column"
             :font-size       "4vw"
             :margin          "0 auto"
             :justify-content "center"
             :min-width       "6em"
             :height          "auto"}]
           [:.padding-bottom
            {:padding-bottom "1em"}]
           [:.enlarge-text
            {:font-size "larger"
             :overflow  "hidden"}]
           [:.small-text
            {:font-size  "initial"
             :margin     "0 auto"
             :text-align "center"}]]}
  (let [{:keys [middle-main-page padding-bottom]} (css/get-classnames AboutMiddle)]
    (dom/div
      (ui-image
        (comp/get-initial-state Image
                                (get node-options id))))))
(def ui-about-middle (comp/factory AboutMiddle {:keyfn :middle/id}))

(defsc AboutRightSide [this {:right-side/keys [gallery] :as props}]
  {:query [:right-side/gallery
           ]
   :initial-state
          (fn [gallery]
            {:right-side/gallery
             (comp/get-initial-state Gallery gallery)}
            )
   :css
          [[:.right-side
            {:display        "flex"                         ;
             :flex-direction "column"
             :align-items    "center"
             :padding-right  "1.5em"
             :width          "100%"}]
           [:.right-side>a+a
            {:padding-top "6em"}]]
   }
  (dom/div {:className "about-right"}
           (dom/div
             (ui-image
               (comp/get-initial-state
                 Image
                 {:id  "right-side-arrow"
                  :alt "point to the left from right"
                  :src "../images/right-side-arrow.PNG"})
               ))

           (ui-gallery gallery)
           )
  )
(def ui-about-right (comp/factory AboutRightSide))

(defsc Timebox [this {:timebox/keys [id
                                     left
                                     middle
                                     right
                                     ] :as props}]
  {:query
          [:timebox/id
           {:timebox/left (comp/get-query Left)}
           {:timebox/middle (comp/get-query Middle)}
           {:timebox/right (comp/get-query Right)}]
   :ident :timebox/id
   :initial-state
          (fn [{:keys [id left middle right]}]
            {:timebox/id
             id
             :timebox/left
                         (comp/get-initial-state
                           Left left)
             :timebox/middle
             (comp/get-initial-state
               Middle
               (mapv
                 (fn [component]
                   (merge
                     component
                     {:id (str id "-" "node")}))
                 middle))
             :timebox/right
                         (comp/get-initial-state
                           Right right)})
   :css   (:css uicss/Timebox)}
  (let [{:keys [timebox]} (css/get-classnames Timebox)]
    (div {:classes [timebox]
          :id      id}
         (ui-left left)
         (ui-middle middle)
         (ui-right right))))
(def ui-timebox (comp/factory Timebox {:keyfn :timebox/id}))

(def about-initial-state
  {:about/timebox
   [(comp/get-initial-state
      Timebox
      {:id     "first"
       :left
               [{:id      "first-gallery1"
                 :ui      ui-gallery
                 :factory Gallery
                 :data
                          [{:id  "paycom"
                            :src "../images/paycom.PNG"
                            :alt "I work here rn"}
                           {:id  "okcity"
                            :src "../images/okcity.PNG"
                            :alt "I live here rn"}]}
                {:id      "first-arrow1"
                 :ui      ui-image
                 :factory Image
                 :data
                          {:id  "pr"
                           :alt "point to the right from left"
                           :src "../images/left-side-arrow.PNG"}}]
       :middle [{:ui      ui-image
                 :factory Image
                 :data
                         (get node-options :first)}]
       :right
               [{:id      "first-gallery2"
                 :ui      ui-gallery
                 :factory Gallery
                 :data
                          [{:id  "twbb"
                            :src "../images/twbb.jpg"
                            :alt "There Will Be Blood"}]}
                {:id      "first-arrow2"
                 :ui      ui-image
                 :factory Image
                 :data
                          {:id  "pl"
                           :alt "point to the left from the right"
                           :src "../images/right-side-arrow.PNG"}}]})
    (comp/get-initial-state
      Timebox
      {:id     "second"
       :middle [{:ui      ui-image
                 :factory Image
                 :data
                          (get node-options :middle)}]})
    (comp/get-initial-state
      Timebox
      {:id     "third"
       :middle [{:ui      ui-image
                 :factory Image
                 :data
                          (get node-options :end)}]})]})

(defsc About [this {:about/keys [timebox] :as props}]
  {:ident         (fn [] [:component/id :about])
   :route-segment ["about"]
   :query         [{:about/timebox (comp/get-query Timebox)}]
   :initial-state
                  (fn [_]
                    about-initial-state
                    )}
  (div {:id "project-page-body"}
       (inj/style-element {:component Timebox})
       (mapv ui-timebox timebox)))