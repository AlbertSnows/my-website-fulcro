(ns app.ui.working
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr :refer [defrouter]]
    [app.ui.bleeding :as b]
    [taoensso.timbre :as log]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p h4 ul a img]]
    [com.fulcrologic.fulcro.ui-state-machines :as uism
     :refer [defstatemachine]]
    [com.fulcrologic.fulcro-css.css :as css]
    [app.ui.mutations :as uim]
    [app.ui.css :as uicss]
    [com.fulcrologic.fulcro-css.css-injection :as inj]

    ))

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
(defn apply-to-components [mutator data components]
  (mapv (fn [component] (mutator data component)) components))
(defn add-id [comp-id sub-comp]
  (if (nil? (:id sub-comp))
    (merge sub-comp {:id comp-id})
    (merge sub-comp {:id (str comp-id "-" (:id sub-comp))})))
(defn add-id-to-components [new-id components]
  (apply-to-components add-id new-id components))
(defn apply-contained-component [{:keys [id ui factory data]}]
  (ui
    (comp/get-initial-state factory
                            (if (vector? data)
                              (add-id-to-components id data)
                              (add-id id data)))))
(defn get-first-id [components]
  (:id (first components)))


(defsc Image [this {:image/keys [id src alt]}]
  {:query [:image/id :image/src :image/alt]
   :initial-state
          (fn [{:keys [src id alt]}]
            {:image/src src
             :image/id  (str id "-img")
             :image/alt alt})}
  (img {:id id  :src src :alt alt}))
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
                           :id  (str id "-href")
                           :alt alt})})}
  (a {:id id
      :href   link
      :target "__blank"
      :rel    "noopener noreferrer"}
     (ui-image image)))
(def ui-href (comp/factory Href {:keyfn :href/id}))





(defsc Top
  [this {:top/keys [components id]}]
  {:ident :top/id
   :query [:top/components :top/id]
   :initial-state
    (fn [components]
      {:top/components
        (add-id-to-components
          (str (get-first-id components) "-top")
          components)
       :top/id (str (get-first-id components) "-top")})}
  (div-with-classes-and-id id "top-div"
    (mapv apply-contained-component components)))
(def ui-top (comp/factory Top {:keyfn :top/id}))

(defsc Bottom
  [this {:bottom/keys [components id]}]
  {:ident :bottom/id
   :query [:bottom/components :bottom/id]
   :initial-state
          (fn [components]
            {:bottom/components
                        (add-id-to-components
                          (str (get-first-id components) "-bottom")
                          components)
             :bottom/id (str (get-first-id components) "-bottom")})}
  (div-with-classes-and-id id "bottom-div"
    (mapv apply-contained-component components)))
(def ui-bottom (comp/factory Bottom {:keyfn :bottom/id}))


(defsc Left
  [this {:left/keys [components id]}]
  {:query [:left/components :left/id]
   :initial-state
          (fn [{:keys [id data]}]
            {:left/id (str id "-left")
             :left/components
              (add-id-to-components (str id "-left") data)})}
  (div-with-classes-and-id id "left-side"
    (mapv apply-contained-component components)))
(def ui-left (comp/factory Left {:keyfn :left/id}))

(defsc Middle [this {:middle/keys [id components] :as props}]
  {:ident :middle/id
   :query [:middle/id :middle/components]
   :initial-state
          (fn [{:keys [id data]}]
            {:middle/id (str id "-middle")
             :middle/components
              (add-id-to-components (str id "-middle") data)})
   :css   (:css uicss/Middle)}
  (let [{:keys [middle-main-page padding-bottom]} (css/get-classnames Middle)]
    (div-with-classes-and-id id "middle"
      (mapv apply-contained-component components))))
(def ui-middle (comp/factory Middle {:keyfn :middle/id}))

(defsc Right
  [this {:right/keys [components id] :as props}]
  {:query [:right/components :right/id]
   :initial-state
          (fn [{:keys [id data]}]
            {:right/id (str id "-right")
             :right/components
              (add-id-to-components (str id "-right") data)})
   :css   [[:.right
            {:display        "flex"                         ;
             :flex-direction "column"
             :align-items    "center"
             :padding-right  "1.5em"
             :width          "100%"}]
           [:.right>a+a
            {:padding-top "6em"}]]}
  (div-with-classes-and-id id "right-side"
    (mapv apply-contained-component components)))
(def ui-right (comp/factory Right {:keyfn :right/id}))

(defsc Text [this {:text/keys [id text]}]
  {:query         [:text/id :text/text]
   :initial-state (fn [{:keys [id data]}]
                    {:text/id   id
                     :text/text data})}
  (p {:id id} text))
(def ui-text (comp/factory Text {:keyfn :text/id}))

(def contact-initial-state
  {:contact/image
   (comp/get-initial-state
     Image
     {:id  "mail-big"
      :src "../images/mailV2.png"
      :alt "email"})
   :contact/span
   (comp/get-initial-state
     Image
     {:id  "mail-small"
      :src "../images/mail_secure.PNG"
      :alt "for security reasons"})})
(defsc Contact [this {:contact/keys [image span] :as props}]
  {:ident         (fn [] [:component/id :contact])
   :query         [{:contact/image (comp/get-query Image)}
                   {:contact/span (comp/get-query Image)}]
   :initial-state (fn [_] contact-initial-state)
   :route-segment ["contact"]}
  (div
    (ui-image image)
    (ui-image span)))

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

(def projects-initial-state
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
                                  :src  "../images/first_app.PNG"}})]})
(defsc Projects [this {:projects/keys [contents] :as props}]
  {:ident         (fn [] [:component/id :projects])
   :route-segment ["projects"]
   :query         [:projects/contents]
   :initial-state (fn [_] projects-initial-state)}
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
            {:timebox/id id
             :timebox/left
               (comp/get-initial-state
                 Left {:id id :data left})
             :timebox/middle
               (comp/get-initial-state
                 Middle {:id id :data middle})
             :timebox/right
               (comp/get-initial-state
                 Right {:id id :data right})})
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

               [{:id      "gallery1"
                 :ui      ui-gallery
                 :factory Gallery
                 :data
                          [{:id  "paycom"
                            :src "../images/paycom.PNG"
                            :alt "I work here rn"}
                           {:id  "okcity"
                            :src "../images/okcity.PNG"
                            :alt "I live here rn"}]}
                {:id      "arrow1"
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
               [{:id      "gallery2"
                 :ui      ui-gallery
                 :factory Gallery
                 :data
                          [{:id  "twbb"
                            :src "../images/twbb.jpg"
                            :alt "There Will Be Blood"}]}
                {:id      "arrow2"
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
  {:ident (fn [] [:component/id :about])
   :route-segment ["about"]
   :query [{:about/timebox (comp/get-query Timebox)}]
   :initial-state (fn [_] about-initial-state)}
  (div {:id "project-page-body"}
       (inj/style-element {:component Timebox})
       (mapv ui-timebox timebox)))

(def home-initial-state
  {:home/left
   (comp/get-initial-state
     Left
     {:id "home"
      :data
          [{:ui ui-top
            :factory Top
            :data
            [{:ui ui-href
              :factory Href
              :data
              {:link "https://en.wikipedia.org/wiki/Gaming"
               :id   "gamin"
               :src  "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
               :alt  "I play games I KNOW I'M SORRY"}}]}
           {:ui ui-bottom
            :factory Bottom
            :data
            [{:ui ui-href
              :factory Href
              :data
              {:link "https://www.whatisitliketobeaphilosopher.com/"
               :id   "pho"
               :src  "../images/the-thinker.png"
               :alt  "But really, what even IS a rock anyways???"}}]}]})
   :home/middle
    (comp/get-initial-state
      Middle
      {:id "home"
       :data
           [{:id "large-text"
             :ui ui-text
             :factory Text
             :data
             {:id "large-text"
              :data "Mostly this stuff"}}
            {:id "small-text"
             :ui ui-text
             :factory Text
             :data
             {:id "small-text"
              :data "(check out my projects for novel things)"}}]})
   :home/right
    (comp/get-initial-state
      Right
      {:id "home"
       :data
           [{:id "home3"
             :ui ui-top
             :factory Top
             :data
             [{:ui ui-href
               :factory Href
               :data
               {:link "https://www.youtube.com/"
                :id   "Tube"
                :src  "../images/tubes.png"
                :alt  "Youtube is my Netflix, sadly"}}]}
            {:id "home4"
             :ui ui-bottom
             :factory Bottom
             :data
             [{:ui ui-href
               :factory Href
               :data
               {:link "https://en.wikipedia.org/wiki/Programmer"
                :id   "debug"
                :src  "../images/meirl.png"
                :alt  "g! 'How to print newline in cljs'"}}]}]})})

(defsc Home [this {:home/keys [left middle right]}]
  {:query [{:home/left (comp/get-query Left)}
           {:home/middle (comp/get-query Middle)}
           {:home/right (comp/get-query Right)}]
   :route-segment ["home"]
   :ident (fn [] [:component/id :home])
   :initial-state (fn [_] home-initial-state)}
  (div {:className "home"}
       (ui-left left)
       (ui-middle middle)
       (ui-right right)))

(defrouter RootRouter
  [this {:keys [current-state pending-path-segment]}]
  {:router-targets [Home About Contact Projects]})
(def ui-root-router (comp/factory RootRouter))

(defsc ContainerHeader [this {:container-header/keys [id route] :as props}
                        {:keys [outer-text]}]
  {:query         [:container-header/id :container-header/route]
   :ident         :container-header/id
   :initial-state (fn [{:container-header/keys [id _] :as params}]
                    {:container-header/id    id
                     :container-header/route "home"})
   :css           [[:.outer-text
                    {:font-size      "2em"
                     :color          "white"
                     :text-align     "center"
                     :font-family    "MINIMAL"
                     :margin         "0 auto"
                     :padding        "0 auto"
                     :vertical-align "top"}]]}
  (let [{:keys [outer-text]} (css/get-classnames ContainerHeader)]

    (case route
      "" (dom/p "empty")
      nil (dom/p "nil")
      :nil (dom/p "nil key")
      :contact (dom/p "home key")
      "home" (dom/p {:classes [outer-text]}
                    "What Am I Up To?")
      (dom/p
        "nothing matched"))))
(def ui-container-header (comp/factory ContainerHeader))

(defsc OuterBox [this {:outer/keys
                       [id route header router] :as props}
                 {:keys [outer]}]
  {:query [:outer/id
           :outer/route
           :outer/header
           {:outer/router (comp/get-query RootRouter)}
           [::uism/asm-id ::RootRouter]]
   :pre-merge
          (fn [{:keys [data-tree]}]
            (merge (comp/get-initial-state OuterBox)
                   data-tree))
   :ident :outer/id
   :initial-state
          (fn [{:outer/keys [id route] :as params}]
            {:outer/id    id
             :outer/route "contact"
             :outer/header
                          (comp/get-initial-state
                            ContainerHeader
                            {:container-header/id    id
                             :container-header/route "home"})
             :outer/router
                          (comp/get-initial-state RootRouter {})})
   :css
          [[:.outer
            {:background-color "black"
             ;                  :width "50%"
             :display          "flex"
             :flex-direction   "column"
             :justify-content  "center"
             :align-items      "center"
             :padding          "0em 0.5em 1em 0.5em"
             :margin           "7% 1% 1% 1%"
             :border-radius    "2.5%"
             }]
           [:.box
            {
             :border-color  "white"
             :border-style  "solid"
             :border-radius "1%"
             :position      "relative"
             :width         "98%"
             :overflow-wrap "anywhere"
             :word-wrap     "anywhere"
             :border-width  "0.2em"
             :color         "white"}]]}
  (let [{:keys [outer box]} (css/get-classnames OuterBox)]
    (dom/div
      {:nonsense "TURN BACK, YE WHO ENTER THE DOMAIN OF HTML"
       :classes  [outer]}
      (ui-container-header header)
      (dom/div
        {:classes [box]}
        (ui-root-router router))))
  )
(def ui-outer (comp/factory OuterBox))

(defsc ListItem [this {:list-item/keys [name classes] :as props}]
  {:query         [:list-item/name :list-item/classes]
   :initial-state (fn [{:list-item/keys [name classes] :as props}]
                    {:list-item/name    name
                     :list-item/classes classes})}
  (dom/li {:className classes
           :onClick   (fn [] (dr/change-route! this [name]))}
          (dom/a name)))
(def ui-list-item (comp/factory ListItem {:keyfn :list-item/name}))

(defsc SidebarContents
  [this {:sidebar-contents/keys [id home about contact projects] :as props}]
  {:query [:sidebar-contents/id
           {:sidebar-contents/home (comp/get-query ListItem)}
           {:sidebar-contents/about (comp/get-query ListItem)}
           {:sidebar-contents/contact (comp/get-query ListItem)}
           {:sidebar-contents/projects (comp/get-query ListItem)}]
   :ident :sidebar-contents/id
   :initial-state
          (fn [_]
            {:sidebar-contents/id 1
             :sidebar-contents/home
                                  (comp/get-initial-state
                                    ListItem
                                    {:list-item/name    "home"
                                     :list-item/classes "sidebar-brand"})
             :sidebar-contents/about
                                  (comp/get-initial-state
                                    ListItem
                                    {:list-item/name    "about"
                                     :list-item/classes ""})
             :sidebar-contents/contact
                                  (comp/get-initial-state
                                    ListItem
                                    {:list-item/name    "contact"
                                     :list-item/classes ""})
             :sidebar-contents/projects
                                  (comp/get-initial-state
                                    ListItem
                                    {:list-item/name "projects" :list-item/classes ""})})}
  (dom/ul {:className "sidebar-entries sidebar-nav"}
          (ui-list-item home)
          (ui-list-item about)
          (ui-list-item contact)
          (ui-list-item projects)))
(def ui-sidebar-contents (comp/factory SidebarContents))

(defsc SidebarButton [this {:button/keys [id] :as props}]
  {:query [:button/id]
   :ident :button/id
   :initial-state
          (fn [{:button/keys [id] :as params}]
            {:button/id id})}
  (dom/div {:className ""
            :id        "sidebar-toggle-button"
            :onClick   #(comp/transact! this
                                        `[(uim/toggle ~{:id id})])}
           (dom/p "[")))
(def ui-button (comp/factory SidebarButton))

(defsc Sidebar [this {:sidebar/keys [id state button contents] :as props}]
  {:query [:sidebar/id
           :sidebar/state
           {:sidebar/button (comp/get-query SidebarButton)}
           {:sidebar/contents (comp/get-query SidebarContents)}]
   :ident :sidebar/id
   :initial-state
          (fn [{:sidebar/keys [id] :as props}]
            {:sidebar/id    id
             :sidebar/state "closed"
             :sidebar/button
                            (comp/get-initial-state SidebarButton
                                                    {:button/id 1})
             :sidebar/contents
                            (comp/get-initial-state SidebarContents)})}
  (dom/div {:className (str state " sidebar-wrapper")}
           (ui-button button)
           (ui-sidebar-contents contents)))
(def ui-sidebar (comp/factory Sidebar))