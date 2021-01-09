(ns app.ui.pages.about
  (:require
    [app.ui.components :as c
     :refer [ui-image Image Left Middle Right ui-left
             ui-middle ui-right ui-href Href]]
    [com.fulcrologic.fulcro.components :as comp
     :refer [defsc factory get-query get-initial-state]]
    [com.fulcrologic.fulcro-css.localized-dom :as dom
     :refer [div]]
    [com.fulcrologic.fulcro-css.css-injection :as inj
     :refer [style-element]]
    [com.fulcrologic.fulcro-css.css :as css
     :refer [get-classnames]]
    [app.ui.css :as uicss]
    [lab.bleeding :as b]))

(defsc Gallery
  [this {:gallery/keys [photos id] :as props}]
  {:id    :gallery/id
   :query [:gallery/photos :gallery/id]
   :initial-state
          (fn [gallery]
            {:gallery/photos gallery
             :gallery/id     (:id (first gallery))})}
  (div {:id        (str id "-gallery")
        :className "gallery"}
       (mapv
         (fn [photo]
           (ui-href (get-initial-state Href photo)))
         photos)))
(def ui-gallery (factory Gallery {:keyfn :gallery/id}))

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

(defsc Timebox [this {:timebox/keys [id left middle right]}]
  {:query
          [:timebox/id
           {:timebox/left (get-query Left)}
           {:timebox/middle (get-query Middle)}
           {:timebox/right (get-query Right)}]
   :ident :timebox/id
   :initial-state
          (fn [{:keys [id left middle right]}]
            {:timebox/id id
             :timebox/left
                         (get-initial-state
                           Left {:id id :data left})
             :timebox/middle
                         (get-initial-state
                           Middle {:id id :data middle})
             :timebox/right
                         (get-initial-state
                           Right {:id id :data right})})
   :css   (:css uicss/Timebox)}
  (let [{:keys [timebox]} (get-classnames Timebox)]
    (div {:classes [timebox]
          :id      id}
         (ui-left left)
         (ui-middle middle)
         (ui-right right))))
(def ui-timebox (factory Timebox {:keyfn :timebox/id}))

(def about-timeboxes
  [
   (get-initial-state
     Timebox
     {:id     5
      :left   [{:id      "arrow"
                :ui      ui-image
                :factory Image
                :data    {:id  "pr"
                          :alt "point to the right from left"
                          :src "../images/left-side-arrow.PNG"}}
               {:ui      ui-gallery
                :factory Gallery
                :data    [
                          {:link  "www.google.com"
                           :image {:id  "paycom"
                                   :src "../images/paycom-icon.PNG"
                                   :alt "I work here rn"}}
                          {:link  "www.google.com"
                           :image {:id  "okcity"
                                   :src "../images/okcity.PNG"
                                   :alt "I live here rn"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/APDTBB.jpg"
                                   :alt "There Will Be Blood"}}




                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/denzel-curry-taboo.jpg"
                                   :alt "There Will Be Blood"}}











                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/HTIF.jpg"
                                   :alt "There Will Be Blood"}}



                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/JWWYP.jpg"
                                   :alt "There Will Be Blood"}}



                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/LLHB.jpg"
                                   :alt "There Will Be Blood"}}












                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/ruinerOST.jpg"
                                   :alt "There Will Be Blood"}}

                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/SAM.jpg"
                                   :alt "There Will Be Blood"}}


                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/SMAQF.jpg"
                                   :alt "There Will Be Blood"}}









                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/evangelion.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/GITSM.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/GITSSAC.jpg"
                                   :alt "There Will Be Blood"}}

                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/GITSSACOST2-1.jpg"
                                   :alt "There Will Be Blood"}}

                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/GITSSACOST1.webp"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/GITSSACOST2.webp"
                                   :alt "There Will Be Blood"}}

                          ]}]
      :middle [{:ui      ui-image
                :factory Image
                :data    (get node-options :middle)}]
      :right  [{:id      "arrow"
                :ui      ui-image
                :factory Image
                :data    {:id  "pl"
                          :alt "point to the left from the right"
                          :src "../images/right-side-arrow.PNG"}}
               {:id      "gallery"
                :ui      ui-gallery
                :factory Gallery
                :data    [

                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/CXCXC.png"
                                   :alt "There Will Be Blood"}}


                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/exmilitary.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/TER.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/the-money-store.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/off-the-wall.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/FWMR.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/FLC.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/ETAD.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/blond.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/bottomless-pit.jpg"
                                   :alt "There Will Be Blood"}}
                          ]}]})

   (get-initial-state
     Timebox
     {:id     4
      :left   [{:id      "arrow"
                :ui      ui-image
                :factory Image
                :data    {:id  "pr"
                          :alt "point to the right from left"
                          :src "../images/left-side-arrow.PNG"}}
               {:ui      ui-gallery
                :factory Gallery
                :data    [

                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/COF.webp"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/XCOM2.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/thriller.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/NYWNBOU.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/JRW.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/HABYHOD.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/ASATT.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/TGBH.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/TR.jpg"
                                   :alt "There Will Be Blood"}}
                          ]}]
      :middle [{:ui      ui-image
                :factory Image
                :data    (get node-options :middle)}]
      :right  [{:id      "arrow"
                :ui      ui-image
                :factory Image
                :data    {:id  "pl"
                          :alt "point to the left from the right"
                          :src "../images/right-side-arrow.PNG"}}
               {:id      "gallery"
                :ui      ui-gallery
                :factory Gallery
                :data    [
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/cbbb.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/MIA.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/saturation.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/TB.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/NCMM.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/GKMC.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/FLYD.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/ERAFW.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/ATCQWGIFH.jpg"
                                   :alt "There Will Be Blood"}}
                          ]}]})

   (get-initial-state
     Timebox
     {:id     3
      :left   [{:id      "arrow"
                :ui      ui-image
                :factory Image
                :data    {:id  "pr"
                          :alt "point to the right from left"
                          :src "../images/left-side-arrow.PNG"}}
               {:ui      ui-gallery
                :factory Gallery
                :data    [

                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/shaun.webp"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/contrapoints.webp"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/clojure.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/YMS.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/vaporwave.jpg"
                                   :alt "There Will Be Blood"}}

                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/willschoder.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/tourist-history.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/TTCFB.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/threearrows.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/street-epist.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/potholer54.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/primer.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/philoverdose.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/philtube.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/myles-power.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/nakeyjakey.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/KUC.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/KWKSG.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/KWTLP.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/KLTPAB.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/jack-saint.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/graduation.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/anthony-fantano.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/4EIAMLT.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/AAO.jpg"
                                   :alt "There Will Be Blood"}}

                          ]}

               ]
      :middle [{:ui      ui-image
                :factory Image
                :data    (get node-options :middle)}]
      :right  [{:id      "arrow"
                :ui      ui-image
                :factory Image
                :data    {:id  "pl"
                          :alt "point to the left from the right"
                          :src "../images/right-side-arrow.PNG"}}
               {:id      "gallery"
                :ui      ui-gallery
                :factory Gallery
                :data    [

                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/nujabes.webp"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/computer-science.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/undertale.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/twocents.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/tierzoo.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/tomscott.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/self-titled.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/saint-pepsi.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/lofi-hiphop.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/knowing-better.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/knowledge-hub.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/KS.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/john-stewart.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/furi.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/future-bounces.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/exurb1a.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/BSR.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/bunnyhop.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/adam-conover.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/Dofus.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/KS.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/M2033.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/MGSV.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/soma.png"
                                   :alt "There Will Be Blood"}}

                          ]}]})

   (get-initial-state
     Timebox
     {:id     2
      :left   [{:id      "arrow"
                :ui      ui-image
                :factory Image
                :data    {:id  "pr"
                          :alt "point to the right from left"
                          :src "../images/left-side-arrow.PNG"}}
               {:ui      ui-gallery
                :factory Gallery
                :data    [
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/NCFOM.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/AWGB.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/NNB.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/RE4.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/TEC.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/ESO.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/DD.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/adventuretime.webp"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/PDRK.webp"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/EP.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/mononoke.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/wolf-children.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/SAW.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/RSV2.webp"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/minutephysics.webp"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/adventuretime.webp"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/scishow.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/monstercat.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/numberphile.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/kansas.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/ESO.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/DD.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/crash-course.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/blu-jays.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/wisecrack.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/ve.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/vsauce.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/total-biscuit.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/TGT.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/TEC.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/sark.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/seananners.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/SED.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/RL.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/pheonix-wolfgang.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/nox.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/MCRDD.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/MCRTBP.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/MCRTCFSR.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/FF13OST1.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/FF13OST-A1.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/cgp-grey.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/pho-for-dummies.gif"
                                   :alt "There Will Be Blood"}}
                          ]}]
      :middle [{:ui      ui-image
                :factory Image
                :data    (get node-options :middle)}]
      :right  [{:id      "arrow"
                :ui      ui-image
                :factory Image
                :data    {:id  "pl"
                          :alt "point to the left from the right"
                          :src "../images/right-side-arrow.PNG"}}
               {:id      "gallery"
                :ui      ui-gallery
                :factory Gallery
                :data    [

                          ]}]})

   (get-initial-state
     Timebox
     {:id     1
      :left   [{:id      "arrow"
                :ui      ui-image
                :factory Image
                :data    {:id  "pr"
                          :alt "point to the right from left"
                          :src "../images/left-side-arrow.PNG"}}
               {:ui      ui-gallery
                :factory Gallery
                :data    [
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/ATLA.jpg"
                                   :alt "There Will Be Blood"}}

                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/Chowder.jpeg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/STH.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/ZTP.png"
                                   :alt "There Will Be Blood"}}
                          ]}]
      :middle [{:ui      ui-image
                :factory Image
                :data    (get node-options :end)}]
      :right  [{:id      "arrow"
                :ui      ui-image
                :factory Image
                :data    {:id  "pl"
                          :alt "point to the left from the right"
                          :src "../images/right-side-arrow.PNG"}}
               {:id      "gallery"
                :ui      ui-gallery
                :factory Gallery
                :data    [{:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/aristocat.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/BAM.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/E65.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/EENE.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/GDL.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/LZWW.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/MB.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/Melee.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/MP.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/ocarina.webp"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/SM64.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/TT.jpg"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/AR.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/BLU.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/CTCD.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/KND.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/MM.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/SA2.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/SJ.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/SEQ.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/yugioh.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/soccer.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/nebraska.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/KND.png"
                                   :alt "There Will Be Blood"}}
                          {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                           :image {:id  "twbb"
                                   :src "../images/football.png"
                                   :alt "There Will Be Blood"}}]}]})])

(def about-initial-state {}
	;(bhc/build-timebox (bd/get-timebox 6))
  )

                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/VBBB.webp"
                                  :alt "There Will Be Blood"}}
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/LLHST.jpg"
                                  :alt "There Will Be Blood"}}
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/brigador.jpg"
                                  :alt "There Will Be Blood"}}


                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/crouching-tiger-hidden-sun.jpg"
                                  :alt "There Will Be Blood"}}
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/windswept.jpg"
                                  :alt "There Will Be Blood"}}

                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/wolf-girl.jpg"
                                  :alt "There Will Be Blood"}}
                         ]}
              ]
     :middle [{:ui      ui-image
               :factory Image
               :data    (get node-options :first)}]
     :right  [{:id      "arrow"
               :ui      ui-image
               :factory Image
               :data    {:id  "pl"
                         :alt "point to the left from the right"
                         :src "../images/right-side-arrow.PNG"}}
              {:id      "gallery"
               :ui      ui-gallery
               :factory Gallery
               :data    [
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/DOS2.jpg"
                                  :alt "There Will Be Blood"}}
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/VAHALLA.png"
                                  :alt "There Will Be Blood"}}
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/SOTL.webp"
                                  :alt "There Will Be Blood"}}

                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/the-powers-that-b.jpg"
                                  :alt "There Will Be Blood"}}






                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/ultra-mono.jpg"
                                  :alt "There Will Be Blood"}}



                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/FN.png"
                                  :alt "There Will Be Blood"}}




                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/synecdoche.jpg"
                                  :alt "There Will Be Blood"}}
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/the-hunt.jpg"
                                  :alt "There Will Be Blood"}}
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/her.jpg"
                                  :alt "There Will Be Blood"}}
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/taxidriver.jpg"
                                  :alt "There Will Be Blood"}}
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/TBB.jpg"
                                  :alt "There Will Be Blood"}}
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/parasite.jpg"
                                  :alt "There Will Be Blood"}}
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/lighthouse.jpg"
                                  :alt "There Will Be Blood"}}
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/PF.png"
                                  :alt "There Will Be Blood"}}
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/oldboy.jpg"
                                  :alt "There Will Be Blood"}}
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/a-taxidriver.jpg"
                                  :alt "There Will Be Blood"}}
                         {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                          :image {:id  "twbb"
                                  :src "../images/burning.jpg"
                                  :alt "There Will Be Blood"}}]}]}))

;(defsc About [this {:about/keys [timebox] :as props}]
;  {:ident         (fn [] [:component/id :about])
;   :route-segment ["about"]
;   :query         [{:about/timebox (get-query Timebox)}]
;   :initial-state (fn [_] {:about/timebox about-initial-state})}
;  (div {:id "about-page-body"}
;       (style-element {:component Timebox})
;       (mapv ui-timebox timebox)))
;
;(defsc TimeboxList [this {:keys [list/timebox-item]}]
;  {:initial-state (fn [params]
;                    {:list/timebox-item (comp/get-initial-state b/ListPage ??)})
;   :query [{:list/current-page (comp/get-query b/ListPage)}]
;   :ident (fn [] [:list/by-id 1])}
;  (let [{:keys [page/number]} current-page]
;    (dom/div
;      (dom/button
;        {:disabled (= 1 number)
;         :onClick #(comp/transact! this
;                     [(b/goto-page {:page-number (dec number)})])}
;        "Prior Page")
;      (dom/button
;        {:onClick #(comp/transact! this
;                     [(b/goto-page {:page-number (inc number)})])}
;        "next page")
;      (b/ui-list-page current-page))))
;
;(def ui-list (comp/factory TimeboxList))

(defsc About [this {:about/keys [timeboxes]}]
  {:query         [{:about/timeboxes (comp/get-query Timebox)}]
   :route-segment ["about"]
   :ident         (fn [] [:component/id :about])
   :initial-state (fn [_]
                    {:about/timeboxes
                     [about-initial-state]})}
  ;(let [{:keys [page/number]} (:current-page list)]
    (div
      ;{:id "aboot"}
      (mapv ui-timebox timeboxes))
    ;)
  )

;In terms of implementation, you would just need to create an event listener, that listens for the scrolling of the window or body. Then, you just need to check if they are at the bottom of the window / body, and call a function that updates the state of the component, when that happens. The function would call the remote to fetch more items.

;https://gist.github.com/nberger/b5e316a43ffc3b7d5e084b228bd83899
;https://github.com/joakimmohn/crudurama/blob/master/src/cljs/crudurama/infinite-scroll.cljs
;$(window).scroll(function () {
;   if ($(window).scrollTop() >= $(document).height() - $(window).height() - 10) {
;      //Add something at the end of the page
;   }
;});

;(behavior "Triggering an event from state b"
;      (uism/trigger! app ::id :event/bang!)
;      (assertions
;        "Moves to the correct state"
;        (uism/get-active-state app ::id) => :state/a
;        "Applies the expected actions"
;        (fget-in [:WOW]) => 2)))
