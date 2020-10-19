(ns app.ui.root
  (:require
    [app.ui.mutations :as uim]
    [cljs.core.match :refer-macros [match]]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr]
    [com.fulcrologic.fulcro.ui-state-machines :as uism
     :refer [defstatemachine]]
    [com.fulcrologic.fulcro.algorithms.normalized-state :refer [swap!->]]
    [com.fulcrologic.fulcro-css.css-injection :as inj]
    [com.fulcrologic.fulcro-css.css :as css]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div label button span p h4 ul]]
    [com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]
    [com.fulcrologic.fulcro.data-fetch :as df]
    [com.fulcrologic.fulcro.routing.dynamic-routing :as dr :refer [defrouter]]
    [taoensso.timbre :as log]))

(defsc Image [this {:image/keys [id src alt classes]} {:keys [image]}]
  {:query         [:image/id
                   :image/src
                   :image/alt
                   :image/classes]
   :ident (fn [_] [:image/id :image])
   :initial-state
    (fn [{:keys [id src alt classes]}]
      {:image/id id
       :image/src src
       :image/alt alt
       :image/classes classes})}
       (dom/img {:src src :alt alt :className (doall classes)}))
(def ui-image (comp/factory Image {:keyfn :image/id}))

(defsc Href [this {:href/keys [id link image] :as props}]
       {:query [:href/id
                :href/link
                {:href/image (comp/get-query Image)}]
        :initial-state
          (fn [{:keys [link id src alt]}]
              {:href/id id
               :href/link link
               :href/image
                (comp/get-initial-state
                  Image {:id id :src src :alt alt})})
        :css [[:.href-container
               {:display "flex"
                :justify-content "center"
                :margin "1em 0em"}]
              [:.href-container>img
               {:height "auto"
                :max-width "100%"
                }]]}
       (let [{:keys [href-container]} (css/get-classnames Href)]
            (dom/a {:href link
                    :target "__blank"
                    :rel "noopener noreferrer"
                    :classes [href-container]}            ;IT HAS TO MATCH THE CSS CLASS NAME
                   (ui-image image))))
(def ui-href (comp/factory Href {:keyfn :href/id}))

(defsc TopLeft [this {:top-left/keys [contents] :as props}]
       {:query [{:top-left/contents (comp/get-query Href)}]
        :initial-state
         (fn [{:keys [link id src alt]}]
             {:top-left/contents
              (comp/get-initial-state Href
                {:link link :id id :src src :alt alt})})}
         (ui-href contents))
(def ui-top-left (comp/factory TopLeft))

(defsc BottomLeft [this {:bottom-left/keys [contents] :as props}]
       {:query [{:bottom-left/contents (comp/get-query Href)}]
        :initial-state
         (fn [{:keys [link id src alt]}]
             {:bottom-left/contents
              (comp/get-initial-state Href
                {:link link :id id :src src :alt alt})})}
         (ui-href contents))
(def ui-bottom-left (comp/factory BottomLeft))

(defsc LeftSide [this {:left-side/keys [top bottom] :as props}
                 {:keys [left-side]}]
       {:query [{:left-side/top (comp/get-query TopLeft)}
                {:left-side/bottom (comp/get-query BottomLeft)}]
        :initial-state
          (fn [{:keys [top bottom] :as params}]
               {:left-side/top
                (comp/get-initial-state TopLeft
                  {:link (:link top)
                   :id (:id top)
                   :src (:src top)
                   :alt (:alt top)})
                :left-side/bottom
                (comp/get-initial-state
                  BottomLeft
                  {:link (:link bottom)
                   :id (:id bottom)
                   :src (:src bottom)
                   :alt (:alt bottom)})})
        :css [[:.left-side
               {:display "flex"
                :flex-direction "column"
                :align-items "center"
                :padding-left "1.5em"
                :width "100%"}]
              [:.left-side>div
               {:padding-top "1em"
                :padding-bottom "1em"}]
              [:.left-side>a+a
               {:padding-top "6em"}]]}
       (let [{:keys [left-side]} (css/get-classnames LeftSide)]
            (dom/div {:classes [left-side]}
                     (ui-top-left top)
                     (ui-bottom-left bottom))))
(def ui-left-side (comp/factory LeftSide))

(defsc TopRight [this {:top-right/keys [contents] :as props}]
       {:query [{:top-right/contents (comp/get-query Href)}]
        :initial-state
         (fn [{:keys [link id src alt]}]
             {:top-right/contents
              (comp/get-initial-state Href
                {:link link :id id :src src :alt alt})})
        :css [:.right-top []]}
       (dom/div
         (ui-href contents)))
(def ui-top-right (comp/factory TopRight))

(defsc BottomRight [this {:bottom-right/keys [contents] :as props}]
       {:query [{:bottom-right/contents (comp/get-query Href)}]
        :initial-state
               (fn [{:keys [link id src alt]}]
                   {:bottom-right/contents
                    (comp/get-initial-state Href
                      {:link link :id id :src src :alt alt})})}
       (dom/div (ui-href contents)))
(def ui-bottom-right (comp/factory BottomRight))

(defsc RightSide [this {:right-side/keys [top bottom] :as props}
                  {:keys [right-side]}]
       {:query [{:right-side/top (comp/get-query TopLeft)}
                {:right-side/bottom (comp/get-query BottomLeft)}]
        :initial-state
         (fn [{:keys [top bottom] :as params}]
             {:right-side/top
               (comp/get-initial-state TopLeft
                 {:link (:link top)
                  :id (:id top)
                  :src (:src top)
                  :alt (:alt top)})
              :right-side/bottom
               (comp/get-initial-state BottomLeft
                 {:link (:link bottom)
                  :id (:id bottom)
                  :src (:src bottom)
                  :alt (:alt bottom)})})
        :css [[:.right-side
               {:display "flex";
                :flex-direction "column"
                :align-items "center"
                :padding-right "1.5em"
                :width "100%"}]
              [:.right-side>a+a
               {:padding-top "6em"}]]}
       (let [{:keys [right-side]} (css/get-classnames RightSide)]
         (dom/div {:classes [right-side]}
           (ui-top-left top)
           (ui-bottom-left bottom))))
(def ui-right-side (comp/factory RightSide))

(defsc Middle [this {:middle/keys [id content] :as props}]
       {:query [:middle/id
                {:middle/content (comp/get-query Href)}]
        :initial-state
         (fn [{:keys [content]}]
             {:middle/id 1
              :middle/content content})
        :css [[:.middle-main-page
               {:display "flex"
                :flex-direction "column"
                :font-size "4vw"
                :margin "0 auto"
                :justify-content "center"
                :min-width "6em"
                :height "auto"}]
              [:.padding-bottom
               {:padding-bottom "1em"}]
              [:.enlarge-text
               {:font-size "larger"
                :overflow "hidden"}]
              [:.small-text
               {:font-size "initial"
                :margin "0 auto"
                :text-align "center"}]]}
       (let [{:keys [middle-main-page padding-bottom]} (css/get-classnames Middle)]
       (dom/div
         {:classes [middle-main-page padding-bottom]}
         ;:.general-container
         ;       {
         ;:.general-container
         ;:.middle-main-page
         ;:.padding-bottom
         ;        :className (doall [middle-main-page padding-bottom])
                 ;}
                (doall content)
                )))
(def ui-middle (comp/factory Middle {:keyfn :middle/id}))

(defsc Projects [this {:projects/keys [id] :as props}]
  {:query [:projects/id]
   :route-segment ["projects"]
   :ident (fn [] [:projects/id :projects])
   :initial-state {}}
  (dom/div "Hello!"))
(def ui-projects (comp/factory Projects))

(defsc About [this {:about/keys [id] :as props}]
  {:query [:about/id]
   :route-segment ["about"]
   :ident (fn [] [:component/id :about])
   :initial-state {}}
  (dom/div "Hello!"))
(def ui-about (comp/factory About))

(defsc ContainerHeader [this {:container-header/keys [id route] :as props}
                        {:keys [outer-text]}]
  {:query [:container-header/id :container-header/route]
   :ident :container-header/id
   :initial-state (fn [{:container-header/keys [id _] :as params}]
                    {:container-header/id id
                     :container-header/route "home"})
   :css   [[:.outer-text
            {:font-size "2em"
             :color "white"
             :text-align "center"
             :font-family "MINIMAL"
             :margin "0 auto"
             :padding "0 auto"
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

(def home-initial-state
  {:home/left-side
            (comp/get-initial-state LeftSide
                                    {:top
                                     {:link "https://en.wikipedia.org/wiki/Gaming"
                                      :id 1
                                      :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
                                      :alt "I play games I KNOW I'M SORRY"}
                                     :bottom
                                     {:link "https://www.whatisitliketobeaphilosopher.com/"
                                      :id 2
                                      :src "../images/the-thinker.png\\"
                                      :alt "But really, what even IS a rock anyways???"}})
   :home/middle
            (comp/get-initial-state Middle
                                    {:content
                                     [(dom/p {:key 1
                                              :className "enlarge-text"}
                                             ;{:class "enlarge-text"}
                                             "Mostly this stuff")
                                      (dom/p
                                        {:key 2
                                         :className "small-text"}
                                        "(check out my projects for novel things)")]
                                     })
   :home/right-side
            (comp/get-initial-state RightSide
                                    {:top
                                     {:link "https://www.youtube.com/"
                                      :id 3
                                      :src "../images/tubes.png"
                                      :alt "Youtube is my Netflix, sadly"}
                                     :bottom
                                     {:link "https://en.wikipedia.org/wiki/Programmer"
                                      :id 4
                                      :src "../images/meirl.png"
                                      :alt "g! 'How to print newline in cljs'"}})})

(defsc Home [this {:home/keys
                   [
                    left-side
                    middle
                    right-side
                    ] :as props}]
  {:query [
           {:home/left-side (comp/get-query LeftSide)}
           {:home/middle (comp/get-query Middle)}
           {:home/right-side (comp/get-query RightSide)}
           ]
   :ident (fn [] [:component/id :home])
   :route-segment ["home"]
   :initial-state (fn [_] home-initial-state)
   }
  (let [{:keys [general-container]} (css/get-classnames Home)]
    (dom/div {:classes [general-container]}
             (ui-left-side left-side)
             (ui-middle middle)
             (ui-right-side right-side)
             )
    )
  )
(def ui-home (comp/factory Home))

(defsc About [this {:test/keys [words left] :as props}]
  {:query [:test/words
           {:test/left (comp/get-query LeftSide)}]
   :ident (fn [] [:component/id :home])
   :route-segment ["about"]
   :initial-state
   (fn [_]
     {:test/words "Here are some words"
      :test/left (comp/get-initial-state LeftSide
                                         {:top
                                          {:link "https://en.wikipedia.org/wiki/Gaming"
                                           :id 1
                                           :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
                                           :alt "I play games I KNOW I'M SORRY"}
                                          :bottom
                                          {:link "https://www.whatisitliketobeaphilosopher.com/"
                                           :id 2
                                           :src "../images/the-thinker.png\\"
                                           :alt "But really, what even IS a rock anyways???"}})})}
  (dom/div
    (str "Tis about: " words)
    (str "Left side: ")
    (ui-left-side left)))

(defsc Contact [this {:contact/keys [id image span-image] :as props}]
  {:query [:contact/id
           {:contact/image (comp/get-query Image)}
           {:contact/span-image (comp/get-query Image)}]
   :route-segment ["contact"]
   :ident (fn [] [:contact/id :contact])
   ;:will-enter (dr/route-immediate [:contact/id ::contact])
   :initial-state
     (fn [{:keys [_] :as params}]
       {:contact/id 1
        :contact/image
        (comp/get-initial-state Image
          {:id "mail-big"
           :src "../images/mailV2.png"
           :alt "email"
           :classes "big-email-boi"})
        :contact/span-image
        (comp/get-initial-state Image
          {:id "mail-small"
           :src "../images/mail_secure.PNG"
           :alt "for security reasons"
           :classes "small-email-boi"})})
   :css [[:.general-container
          {:display "flex"
           :flex-direction "row"
           :justify-content "center"
           :align-items "center"}]
         [:.general-container>div>.href-image-container
          {:width "50%"
           :height "50%"}]
         [:.contact {:color "white"
                     :position "relative"
                     :top "0"
                     :left "0"
                     :transform "scale(0.7)"
                     :display "flex"
                     :flex-direction "column"
                     :align-items "center"
                     }]
         [:.contact>img {:width "100%"
                         :height "auto"
                         :position "relative"
                         :top "0"
                         :left "0"}]
         [:.contact>.big-email-boi {:position "relative"
                                   :top "0"
                                   :left "0"
                                    :border-radius "1.5em"}]
         [:.contact>.small-email-boi {:position "absolute"
                                     :top "38%"
                                     :left "0"
                                     :visibility "hidden"
                                     :transform "scale(0.8)"
                                      }]
         [:.contact>.big-email-boi:hover+.small-email-boi {
                                                           :visibility "visible"
                                                           }]
         [:.very-small-text {


                             }]
         [:.contact>.small-email-boi:hover {:visibility "visible"}]
         ]}
  (let [{:keys [contact]} (css/get-classnames Contact)]
    (dom/div
      {:classes [contact
      ;           contact>big-mail-boi
      ;           contact>small-mail-boi
       ]
       }
             ;(inj/style-element {:component Contact})
             (ui-image image)
      (ui-image span-image)

             ;(:span {:className "popup"})

      ;(dom/div
       ;(dom/div {:className "mail"} )
       ;)
     (dom/div {:className "very-small-text"}
       "(email for social media)"))))

(defsc Projects [this {:test/keys [words left] :as props}]
  {:query [:test/words
           {:test/left (comp/get-query LeftSide)}]
   :ident (fn [] [:component/id :home])
   :route-segment ["projects"]
   :initial-state
   (fn [_]
     {:test/words "Here are some words"
      :test/left (comp/get-initial-state LeftSide
       {:top
        {:link "https://en.wikipedia.org/wiki/Gaming"
         :id 1
         :src "../images/WITH_OUR_THREE_POWERS_COMBINED.png"
         :alt "I play games I KNOW I'M SORRY"}
        :bottom
        {:link "https://www.whatisitliketobeaphilosopher.com/"
         :id 2
         :src "../images/the-thinker.png\\"
         :alt "But really, what even IS a rock anyways???"}})})}
  (dom/div
    (str "Tis projects: " words)
    (str "Left side: ")
    (ui-left-side left)))

(defrouter RootRouter [this {:keys [current-state pending-path-segment] :as props}]
  {:router-targets
   [
    Home
    About
    Contact
    Projects
    ]})
(def ui-root-router (comp/factory RootRouter))

(defsc OuterBox [this {:outer/keys
                       [id
                        route
                        header
                        ;body
                        router
                        ] :as props}
                 {:keys [outer]}]
       {:query [:outer/id
                :outer/route
                :outer/header
                ;:outer/body
                {:outer/router (comp/get-query RootRouter)}
                [::uism/asm-id ::RootRouter]
                ]
        ;:initial-state
        ;{:outer/id 1
        ; :outer/router {}}
        :pre-merge
        (fn [{:keys [data-tree]}]
          (merge (comp/get-initial-state OuterBox)
                 data-tree))
        :ident :outer/id
        :initial-state
         (fn [{:outer/keys [id route] :as params}]
           {
            :outer/id id
            :outer/route "contact"
            :outer/header
            (comp/get-initial-state
              ContainerHeader
              {:container-header/id id
               :container-header/route "home"})
            ;:outer/body (comp/get-initial-state Home)                                ;(comp/get-initial-state Contact)
            :outer/router {}
            })
        :css
        [[:.outer
          {:background-color "black"
           ;                  :width "50%"
           :display "flex"
           :flex-direction "column"
           :justify-content "center"
           :align-items "center"
           :padding "0em 0.5em 1em 0.5em"
           :margin "7% 1% 1% 1%"
           :border-radius "2.5%"
           }]
         [:.box
          {
           :border-color "white"
           :border-style "solid"
           :border-radius "1%"
           :position "relative"
           :width "98%"
           :overflow-wrap "anywhere"
           :word-wrap "anywhere"
           :border-width "0.2em"
           :color "white"
           ;:min-width "1em"
           }]]
        }
       (let [{:keys [outer box]} (css/get-classnames OuterBox)]
            (dom/div
              {:nonsense "TURN BACK, YE WHO ENTER THE DOMAIN OF HTML"
               :classes [outer]}
              (ui-container-header header)
              (dom/div {:classes [box]}
                    (ui-root-router router)))))
(def ui-outer (comp/factory OuterBox))

(defsc ListItem [this {:list-item/keys [name classes] :as props}]
  {:query [:list-item/name :list-item/classes]
   :initial-state (fn [{:list-item/keys [name classes] :as props}]
                    {:list-item/name name
                     :list-item/classes classes})}
  (dom/li {:className classes
           :onClick (fn [] (dr/change-route! this [name]))}
          ;(log/info "Name: " name)
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
       (comp/get-initial-state ListItem
         {:list-item/name "home" :list-item/classes "sidebar-brand"})
       :sidebar-contents/about
       (comp/get-initial-state ListItem
         {:list-item/name "about" :list-item/classes ""})
       :sidebar-contents/contact
       (comp/get-initial-state ListItem
         {:list-item/name "contact" :list-item/classes ""})
       :sidebar-contents/projects
       (comp/get-initial-state ListItem
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
            :id "sidebar-toggle-button"
            :onClick #(comp/transact! this
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
      {:sidebar/id id
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

(defsc Page [this {:page/keys [outer sidebar
                               ;router
                               ]}]
  {:query [{:page/outer (comp/get-query OuterBox)}
           {:page/sidebar (comp/get-query Sidebar)}
           ;{:page/router (comp/get-query RootRouter)}
           ]
   :initial-state
          (fn [_] {:page/outer
                   (comp/get-initial-state
                     OuterBox {:outer/id    1
                               :outer/route "home"})
                   :page/sidebar
                   (comp/get-initial-state
                     Sidebar {:sidebar/id 1})
                   ;:page/router {}
                   })
   :css   [[:.page
            {:display         "flex"
             :align-items     "center"
             :justify-content "center"}]]}
       (let [{:keys [page]} (css/get-classnames Page)]
            (dom/div {:classes [page]}
                     ;(ui-root-router router)
                                   (ui-outer outer)
                     (ui-sidebar sidebar)
                     )
            ))
(def ui-page (comp/factory Page))

(defsc Root [this {:root/keys [page
                               ;router
                               ] :as props}]
  {:query [{:root/page (comp/get-query Page)}
           ;{:root/router (comp/get-query RootRouter)}
           ]
   :initial-state
          (fn [_] {:root/page
                    (comp/get-initial-state Page)
                   ;:root/router {}
                   })
   :css   [[:.container
            {:display "flex"
             :align-items "center"
             :justify-content "center"}]]}
       (let [{:keys [container]} (css/get-classnames Root)]
            (dom/div {:classes [container]}
              (inj/style-element {:component Root})
                     ;(inj/style-element {:component Home})
              (inj/style-element {:component ContainerHeader})

              (ui-page page)
              ;       (ui-root-router router)
                     )))

(defn client-did-mount
  "Must be used as :client-did-mount parameter of app creation, or called just after you mount the app."
  [app]
  (dr/change-route app ["main"]))