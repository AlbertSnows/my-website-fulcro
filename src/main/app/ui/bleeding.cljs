(ns app.ui.bleeding
  (:require
    [taoensso.timbre :as log]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr :refer [defrouter]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p h4 ul a img]]
    [com.fulcrologic.fulcro-css.css :as fcss]
    [app.ui.css :as uicss]
    ))

(defsc Image [this {:image/keys [id src alt]}]
  {:query [:image/id :image/src :image/alt]
   :initial-state
          (fn [{:keys [src id alt]}]
            {:image/src src
             :image/id (str id "-img")
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
            {:href/id (str id "-href")
             :href/link link
             :href/image
                      (comp/get-initial-state
                        Image
                        {:src src
                         :id id
                         :alt alt})})}
  (a {:href link
      :target "__blank"
      :rel "noopener noreferrer"}
     (ui-image image)))
(def ui-href (comp/factory Href {:keyfn :href/id}))

(defsc About [this {:projects/keys [contents] :as props}]
  {:ident (fn [] [:component/id :projects])
   :route-segment ["projects"]
   :query [:projects/contents]
   :initial-state
     (fn [_]
       {:projects/contents
        [(comp/get-initial-state
           ProjectBox
           {:id "my-website"
            :description
                {:header "This Website"
                 :body "The website you're perusing."}
            :href
                {:link "https://github.com/AlbertSnows/my-website"
                 :alt "This Website"
                 :src "../images/this_website.PNG"}})
            (comp/get-initial-state
              ProjectBox
              {:id "first-website"
               :description
                   {:header "My First Website"
                    :body "First website for class.\n                  Written from Javascript\n                  to-> Typescript\n                  to-> Clojurescript over the semester"}
               :href
                   {:link "https://github.com/AlbertSnows/FWRcljs"
                    :alt "My First Website"
                    :src "../images/kistners_flowers.PNG"}})
            (comp/get-initial-state
              ProjectBox
              {:id "snake-game"
               :description
                   {:header "Snake Game"
                    :body "Walkthrough re-write of Snake in Rust"}
               :href
                   {:link "https://github.com/AlbertSnows/snake_game"
                    :alt "Snake Game"
                    :src "../images/snake_rust.PNG"}})
            (comp/get-initial-state
              ProjectBox
              {:id "game-jam-2018"
               :description
                   {:header "To Change A Light Bulb"
                    :body "A beautiful disaster written over a weekend during a game jam"}
               :href
                   {:link "https://github.com/AlbertSnows/To-Change-A-Lightbulb"
                    :alt "To Change A Light Bulb"
                    :src "../images/lightbulb.PNG"}})
            (comp/get-initial-state
              ProjectBox
              {:id "thermal-modeling"
               :description
                   {:header "Human Thermal Modeling"
                    :body "Multi-threading graduate student's research code"}
               :href
                   {:link "https://github.com/AlbertSnows/HumanThermalModeling"
                    :alt "Human Thermal Modeling"
                    :src "../images/thermal_modeling.PNG"}})
            (comp/get-initial-state
              ProjectBox
              {:id "roguelike"
               :description
                   {:header "2D Rogue Like"
                    :body "Tutorial game made in Unity"}
               :href
                   {:link "https://github.com/AlbertSnows/2DRogueLike"
                    :alt "2D Rogue Like"
                    :src "../images/2Drouge.PNG"}})
            (comp/get-initial-state
              ProjectBox
              {:id "edgesweeper"
               :description
                   {:header "Edgesweeper"
                    :body "Took Minesweeper, written in python, and added a feature"}
               :href
                   {:link "https://github.com/AlbertSnows/python-tkinter-minesweeper"
                    :alt "Edgesweeper"
                    :src "../images/minesweeper.PNG"}})
            (comp/get-initial-state ProjectBox
              {:id "first-unity"
               :description
                   {:header "Roll A Ball"
                    :body "First tutorial exposure to Unity"}
               :href
                   {:link "https://github.com/AlbertSnows/RollABall"
                    :alt "Roll A Ball"
                    :src "../images/rollaball.PNG"}})
            (comp/get-initial-state ProjectBox
              {:id "mobile-app-game"
               :description
                   {:header "Simple App"
                    :body "Exposure to app development in Java/Kotlin via Android Studio"}
               :href
                   {:link "https://github.com/AlbertSnows/SimpleMobileGame"
                    :alt "Simple App"
                    :src "../images/first_app.PNG"}})]})}
  (div {:id "project-page-body"}
    (mapv ui-project-box contents)))

;(def image-dir "../images/")
;
;(def classes "brief-border href-item-container")
;
;(def project-page-header
;  [:p {:id "project-header"} "Projects"])
;
;(defn box-container [contents]
;  [:div {:class "flex box align-horizontal"} contents])
;
;(defn image-container [src alt]
;  [:img {:src src :alt alt}])
;
;(defn image-href-container [id class href src alt]
;  [:div {:id id :class class}
;   [:a {:href href :target "__blank" :rel "noopener noreferrer"}
;    (image-container src alt)]])
;
;(defn project-description [title description]
;  [:div {:class "description-container"}
;   [:div {:class "text-align-left"} title]
;   [:div {:class "description text-align-left"} description]])
;
;(def project-page-body
;  [:div {:id "project-body"}
;   (box-container
;     [:div (image-href-container
;             "my-website"
;             classes
;             "https://github.com/AlbertSnows/my-website"
;             (str image-dir "this_website.PNG")
;             "This Website")
;      (project-description
;        "This Website"
;        "The website you're perusing")])
;   (box-container
;     [:div (image-href-container
;             "first-website"
;             classes
;             "https://github.com/AlbertSnows/FWRcljs"
;             (str image-dir "kistners_flowers.PNG")
;             "My First Website")
;      (project-description
;        "My First website"
;        "First website for class.
;         Written from Javascript
;         -> Typescript
;         -> Clojurescript over the semester")])
;   (box-container
;     [:div (image-href-container
;             "snake-game"
;             classes
;             "https://github.com/AlbertSnows/snake_game"
;             (str image-dir "snake_rust.PNG")
;             "Snake Game")
;      (project-description "Snake Game" "Wrote Snake in Rust")])
;   (box-container
;     [:div (image-href-container
;             "game-jam-2018"
;             classes
;             "https://github.com/AlbertSnows/To-Change-A-Lightbulb"
;             (str image-dir "lightbulb.PNG")
;             "To Change A Light Bulb")
;      (project-description
;        "To Change A Light Bulb"
;        "A beautiful disaster written over a weekend during a game jam")])
;   (box-container
;     [:div (image-href-container
;             "thermal-modeling"
;             classes
;             "https://github.com/AlbertSnows/HumanThermalModeling"
;             (str image-dir "thermal_modeling.PNG")
;             "Human Thermal Modeling")
;      (project-description
;        "Human Thermal Modeling"
;        "Multi-threading graduate student's research code")])
;   (box-container
;     [:div (image-href-container
;             "roguelike"
;             classes
;             "https://github.com/AlbertSnows/2DRogueLike"
;             (str image-dir "2Drouge.PNG")
;             "2D Rogue Like")
;      (project-description "2D Rogue Like" "First game made in Unity")])
;   (box-container
;     [:div (image-href-container
;             "edgesweeper"
;             classes
;             "https://github.com/AlbertSnows/python-tkinter-minesweeper"
;             (str image-dir "minesweeper.PNG")
;             "Edgesweeper")
;      (project-description
;        "Edgesweeper"
;        "Took Minesweeper, written in python, and added a feature")])
;   (box-container
;     [:div (image-href-container
;             "first-unity"
;             classes
;             "https://github.com/AlbertSnows/RollABall"
;             (str image-dir "rollaball.PNG")
;             "Roll A Ball")
;      (project-description "Roll A Ball" "First tutorial exposure to Unity")])
;   (box-container
;     [:div (image-href-container
;             "mobile-app-game"
;             classes
;             "https://github.com/AlbertSnows/SimpleMobileGame"
;             (str image-dir "first_app.PNG")
;             "Simple App")
;      (project-description
;        "Simple App"
;        "Playing with Java/Kotlin in Android Studio")])])