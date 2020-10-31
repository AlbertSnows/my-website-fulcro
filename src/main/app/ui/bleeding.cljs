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

(defsc ProjectDescription [this {:description/keys [header body]}]
  {:ident :description/id
   :query [:description/id :description/header :description/body]
   :initial-state
    (fn [{:keys [id header body] :as props}]
      {:description/id (str id "-description")
       :description/header header
       :description/body body})}
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
        (comp/get-initial-state
          Href
          (merge href {:id id}))
       }
      )}
  (div {:key id}
    (ui-project-description description)
    (ui-href href)))
(def ui-project-box (comp/factory ProjectBox {:keyfn :box/id}))

(defsc Projects [this {:projects/keys [contents] :as props}]
  {:ident (fn [] [:component/id :projects])
   :route-segment ["projects"]
   :query [:projects/contents]
   :initial-state
     (fn [_]
       {:projects/contents
        [
         (comp/get-initial-state
           ProjectBox
           {:key "my-website"
            :id "my-website"
            :description
                  {:header "This Website"
                   :body "The website you're perusing."}
            :href
                {:link "https://github.com/AlbertSnows/my-website"
                 :alt "This Website"
                 :src "../images/this_website.PNG"}
            })
         ]
        })}
  (div {:id "project-page-body" :key "papi"}
    (
      mapv
      ui-project-box
      contents)
       ))

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