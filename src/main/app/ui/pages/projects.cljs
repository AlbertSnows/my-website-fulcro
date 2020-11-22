(ns app.ui.pages.projects
  (:require
    [com.fulcrologic.fulcro.components :as comp
     :refer [defsc factory get-query get-initial-state]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div]]
    [app.ui.components :as c
     :refer [Href ui-href]]))
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
  (factory ProjectDescription {:keyfn :description/id}))

(defsc ProjectBox [this {:box/keys [id description href] :as props}]
  {:ident :box/id
   :query [:box/id
           {:box/description (get-query ProjectDescription)}
           {:box/href (get-query Href)}]
   :initial-state
          (fn [{:keys [id description href]}]
            {:box/id id
             :box/description
                     (get-initial-state
                       ProjectDescription
                       (merge description {:id id}))
             :box/href
                     (get-initial-state
                       Href
                       (merge href {:id id}))})}
  (div
    (ui-project-description description)
    (ui-href href)))
(def ui-project-box (factory ProjectBox {:keyfn :box/id}))

(def projects-initial-state
  {:projects/contents
   [(get-initial-state
      ProjectBox
      {:id "my-website"
       :description
           {:header "This Website"
            :body   "The website you're perusing."}
       :href
           {:link  "https://github.com/AlbertSnows/my-website"
            :image {:alt "This Website"
                    :src "../images/this_website.PNG"}}})
    (get-initial-state
      ProjectBox
      {:id "first-website"
       :description
           {:header "My First Website"
            :body   "First website for class.\n                  Written from Javascript\n                  to-> Typescript\n                  to-> Clojurescript over the semester"}
       :href
           {:link  "https://github.com/AlbertSnows/FWRcljs"
            :image {:alt "My First Website"
                    :src "../images/kistners_flowers.PNG"}}})
    (get-initial-state
      ProjectBox
      {:id "snake-game"
       :description
           {:header "Snake Game"
            :body   "Walkthrough re-write of Snake in Rust"}
       :href
           {:link  "https://github.com/AlbertSnows/snake_game"
            :image {:alt "Snake Game"
                    :src "../images/snake_rust.PNG"}}})
    (get-initial-state
      ProjectBox
      {:id "game-jam-2018"
       :description
           {:header "To Change A Light Bulb"
            :body   "A beautiful disaster written over a weekend during a game jam"}
       :href
           {:link  "https://github.com/AlbertSnows/To-Change-A-Lightbulb"
            :image {:alt "To Change A Light Bulb"
                    :src "../images/lightbulb.PNG"}}})
    (get-initial-state
      ProjectBox
      {:id "thermal-modeling"
       :description
           {:header "Human Thermal Modeling"
            :body   "Multi-threading graduate student's research code"}
       :href
           {:link  "https://github.com/AlbertSnows/HumanThermalModeling"
            :image {:alt "Human Thermal Modeling"
                    :src "../images/thermal_modeling.PNG"}}})
    (get-initial-state
      ProjectBox
      {:id "roguelike"
       :description
           {:header "2D Rogue Like"
            :body   "Tutorial game made in Unity"}
       :href
           {:link  "https://github.com/AlbertSnows/2DRogueLike"
            :image {:alt "2D Rogue Like"
                    :src "../images/2Drouge.PNG"}}})
    (get-initial-state
      ProjectBox
      {:id "edgesweeper"
       :description
           {:header "Edgesweeper"
            :body   "Took Minesweeper, written in python, and added a feature"}
       :href
           {:link  "https://github.com/AlbertSnows/python-tkinter-minesweeper"
            :image {:alt "Edgesweeper"
                    :src "../images/minesweeper.PNG"}}})
    (get-initial-state
      ProjectBox
      {:id "first-unity"
       :description
           {:header "Roll A Ball"
            :body   "First tutorial exposure to Unity"}
       :href
           {:link  "https://github.com/AlbertSnows/RollABall"
            :image {:alt "Roll A Ball"
                    :src "../images/rollaball.PNG"}}})
    (get-initial-state
      ProjectBox
      {:id "mobile-app-game"
       :description
           {:header "Simple App"
            :body   "Exposure to app development in Java/Kotlin via Android Studio"}
       :href
           {:link  "https://github.com/AlbertSnows/SimpleMobileGame"
            :image {:alt "Simple App"
                    :src "../images/first_app.PNG"}}})]})
(defsc Projects [this {:projects/keys [contents] :as props}]
  {:ident         (fn [] [:component/id :projects])
   :route-segment ["projects"]
   :query         [:projects/contents]
   :initial-state (fn [_] projects-initial-state)}
  (div {:id "project-page-body"}
       (mapv ui-project-box contents)))