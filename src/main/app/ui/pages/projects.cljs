(ns app.ui.pages.projects
	(:require
		[com.fulcrologic.fulcro.components :as comp
		 :refer [defsc factory get-query get-initial-state]]
		[com.fulcrologic.fulcro-css.localized-dom :as dom
		 :refer [div h3 p]]
		[app.ui.components :as c
		 :refer [Href ui-href build-href]]
		[app.ui.helpers.core :as hc
		 :refer [add-id]]))

(defsc ProjectDescription [this {:description/keys [id header body]}]
	{:ident :description/id
	 :query [:description/id :description/header :description/body]		 }
	(div {:id id :className "description-holder"}
		(h3 {:className "title"} header)
		(p {:className "description"} body)))
(def ui-project-description
	(factory ProjectDescription {:keyfn :description/id}))
(defn build-description [{:description/keys [id header body]}]
	{:description/id     (str id "-description")
	 :description/header header
	 :description/body   body})

(defsc ProjectBox [this {:box/keys [id description href] :as props}]
	{:ident :box/id
	 :query [:box/id
					 {:box/description (get-query ProjectDescription)}
					 {:box/href (get-query Href)}]}
	(div {:id id :className "project-box"}
		(ui-href href)
		(ui-project-description description)))
(def ui-project-box (factory ProjectBox {:keyfn :box/id}))

(defn build-project-box [{:box/keys [id description href]}]
	{:box/id (str id "-box")
	 :box/description
					 (build-description (add-id (str id "-box") description :description/id))
	 :box/href (build-href (add-id (str id "-box") href :href/id))})

(def projects-initial-state
	[(build-project-box
		 {:box/id "my-website-v2"
			:box/description
							(build-description
								{:description/header "This Website...Again"
								 :description/body   "I redid the website! Sort of."})

			:box/href
							{:href/link  "https://github.com/AlbertSnows/my-website-fulcro"
							 :href/image {:image/alt "This Website...In Fulcro"
														:image/src "../images/this_website.PNG"}}})

	 (build-project-box
		 {:box/id "my-website"
			:box/description
							(build-description
								{:description/header "This Website"
								 :description/body   "The website you're perusing."})

			:box/href
							{:href/link  "https://github.com/AlbertSnows/my-website"
							 :href/image {:image/alt "This Website"
														:image/src "../images/this_website.PNG"}}})

	 (build-project-box
		 {:box/id "first-website"
			:box/description
							{:description/header "My First Website"
							 :description/body   "First website for class.\n                  Written from Javascript\n                  to-> Typescript\n                  to-> Clojurescript over the semester"}
			:box/href
							{:href/link  "https://github.com/AlbertSnows/FWRcljs"
							 :href/image {:image/alt "My First Website"
														:image/src "../images/kistners_flowers.PNG"}}})
	 (build-project-box
		 {:box/id "snake-game"
			:box/description
							{:description/header "Snake Game"
							 :description/body   "Walkthrough re-write of Snake in Rust"}
			:box/href
							{:href/link  "https://github.com/AlbertSnows/snake_game"
							 :href/image {:image/alt "Snake Game"
														:image/src "../images/snake_rust.PNG"}}})
	 (build-project-box
		 {:box/id "game-jam-2018"
			:box/description
							{:description/header "To Change A Light Bulb"
							 :description/body   "A beautiful disaster written over a weekend during a game jam"}
			:box/href
							{:href/link  "https://github.com/AlbertSnows/To-Change-A-Lightbulb"
							 :href/image {:image/alt "To Change A Light Bulb"
														:image/src "../images/lightbulb.PNG"}}})
	 (build-project-box
		 {:box/id "thermal-modeling"
			:box/description
							{:description/header "Human Thermal Modeling"
							 :description/body   "Multi-threading graduate student's research code"}
			:box/href
							{:href/link  "https://github.com/AlbertSnows/HumanThermalModeling"
							 :href/image {:image/alt "Human Thermal Modeling"
														:image/src "../images/thermal_modeling.PNG"}}})
	 (build-project-box
		 {:box/id "roguelike"
			:box/description
							{:description/header "2D Rogue Like"
							 :description/body   "Tutorial game made in Unity"}
			:box/href
							{:href/link  "https://github.com/AlbertSnows/2DRogueLike"
							 :href/image {:image/alt "2D Rogue Like"
														:image/src "../images/2Drouge.PNG"}}})
	 (build-project-box
		 {:box/id "edgesweeper"
			:box/description
							{:description/header "Edgesweeper"
							 :description/body   "Took Minesweeper, written in python, and added a feature"}
			:box/href
							{:href/link  "https://github.com/AlbertSnows/python-tkinter-minesweeper"
							 :href/image {:image/alt "Edgesweeper"
														:image/src "../images/minesweeper.PNG"}}})
	 (build-project-box
		 {:box/id "first-unity"
			:box/description
							{:description/header "Roll A Ball"
							 :description/body   "First tutorial exposure to Unity"}
			:box/href
							{:href/link  "https://github.com/AlbertSnows/RollABall"
							 :href/image {:image/alt "Roll A Ball"
														:image/src "../images/rollaball.PNG"}}})
	 (build-project-box
		 {:box/id "mobile-app-game"
			:box/description
							{:description/header "Simple App"
							 :description/body   "Exposure to app development in Java/Kotlin via Android Studio"}
			:box/href
							{:href/link  "https://github.com/AlbertSnows/SimpleMobileGame"
							 :href/image {:image/alt "Simple App"
														:image/src "../images/first_app.PNG"}}})])
(defsc Projects [this props]
	{:query         []
	 :route-segment ["projects"]
	 :ident         (fn [] [:component/id :projects])}
	(div {:id "project-page-body"}
		(mapv ui-project-box projects-initial-state)))