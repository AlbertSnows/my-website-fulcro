(ns app.backend.data)

(def right :right)
(def middle :middle)
(def left :left)
(def end :end)
(def first-k :first)

(def node-options
  {:first
   {:image/id  "end-node"
    :image/alt "The future is yet to come"
    :image/src "../images/end-node.PNG"}
   :middle
   {:image/id  "middle-node"
    :image/alt "arbitrary point in timeline"
    :image/src "../images/middle-node.PNG"}
   :end
   {:image/id  "end-of-the-road"
    :image/alt "end of the node, cowboy"
    :image/src "../images/end-of-the-road.PNG"}})

(def left-arrow
  {:image/id  "LPR"
   :image/alt "left, point right"
   :image/src "../images/left-side-arrow.PNG"})
(def right-arrow
  {:image/id  "RPL"
   :image/alt "right, point left"
   :image/src "../images/right-side-arrow.PNG"})

; {:id timebox{:id :left :middle :right}}
(def timebox-entries
  {8  {:timebox/id     8
       :timebox/left   16
       :timebox/middle first-k
       :timebox/right  15}
   7  {:timebox/id     7
       :timebox/left   14
       :timebox/middle middle
       :timebox/right  13}
   6  {:timebox/id     6
       :timebox/left   12
       :timebox/middle middle
       :timebox/right  11}
   5  {:timebox/id     5
       :timebox/left   10
       :timebox/middle middle
       :timebox/right  9}
   4  {:timebox/id     4
       :timebox/left   8
       :timebox/middle middle
       :timebox/right  7}
   3  {:timebox/id     3
       :timebox/left   6
       :timebox/middle middle
       :timebox/right  5}
   2  {:timebox/id     2
       :timebox/left   4
       :timebox/middle middle
       :timebox/right  3}
   1  {:timebox/id     1
       :timebox/left   2
       :timebox/middle end
       :timebox/right  1}
   -1 {:timebox/id     -1
       :timebox/left   nil
       :timebox/middle middle
       :timebox/right  nil}})

; {:id gallery{:id {:photos [href{:link image{:id :src :alt}}]}}}
(def galleries
  {16 {:gallery/id 16
       :gallery/photos
                   [
                    {:href/id    "how-now-brown-cow"
                     :href/link  "https://static.thenounproject.com/png/196644-200.png"
                     :href/image {:image/src "../images/what-do.png"
                                  :image/alt "I'm probably napping."}}
                    ]}
   15 {:gallery/id 15
       :gallery/photos
                   [
                    {:href/id    "llhst"
                     :href/link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {:image/src "../images/LLHST.jpg"
                                  :image/alt "There Will Be Blood"}}
                    ]}
   14 {:gallery/id 14
       :gallery/photos
                   [
                    {:href/id    "cths" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {:image/src "../images/crouching-tiger-hidden-sun.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "wolf-girl" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {:image/src "../images/wolf-girl.jpg"
                                  :image/alt "There Will Be Blood"}}
                    ]}
   13 {:gallery/id 13
       :gallery/photos
                   [
                    {:href/id    "DOS2"
                     :href/link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {:image/src "../images/DOS2.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "val"
                     :href/link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {:image/src "../images/VAHALLA.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "sotl"
                     :href/link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {:image/src "../images/SOTL.webp"
                                  :image/alt "There Will Be Blood"}}
                    ]}
   12 {:gallery/id 12
       :gallery/photos
                   [

                    {:href/id    "tptb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {:image/src "../images/the-powers-that-b.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "syn" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {:image/src "../images/synecdoche.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "her" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {:image/src "../images/her.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "td" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {:image/src "../images/taxidriver.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "lh" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {:image/src "../images/lighthouse.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "pf" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {:image/src "../images/PF.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "ob" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {:image/src "../images/oldboy.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "atd" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {:image/src "../images/a-taxidriver.jpg"
                                  :image/alt "There Will Be Blood"}}
                    ]}
   11  {:gallery/id 11
       :gallery/photos
                   [{:href/id    "paycom" :href/link "www.google.com"
                     :href/image {:image/src "../images/paycom-icon.PNG"
                                  :image/alt "I work here rn"}}
                    {:href/id    "okcity" :href/link "www.google.com"
                     :href/image {:image/src "../images/okcity.PNG"
                                  :image/alt "I live here rn"}}
                    {:href/id    "apdtbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/APDTBB.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "taboo" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/denzel-curry-taboo.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "whats-your-pleasure" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/JWWYP.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "clpping-space" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/SAM.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "evangelion" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/evangelion.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "gits-movie" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/GITSM.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "gits-show" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/GITSSAC.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "gits-ost-1" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/GITSSACOST2-1.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "gits-ost-2" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/GITSSACOST1.webp"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "gits-ost-3" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/GITSSACOST2.webp"
                                  :image/alt "There Will Be Blood"}}]}
   10  {:gallery/id 10
       :gallery/photos
                   [{:href/id    "charlie" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {:image/id  "CXCXC"
                                  :image/src "../images/CXCXC.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "money-store" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/the-money-store.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "flc" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/FLC.jpg"
                                  :image/alt "There Will Be Blood"}}]}
   9  {:gallery/id 9
       :gallery/photos
                   [
                    {:href/id    "xcom-2" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/XCOM2.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "nails" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/NYWNBOU.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "jr-worries" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/JRW.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "hop-along-byhod" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/HABYHOD.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "remnant" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/TR.jpg"
                                  :image/alt "There Will Be Blood"}}]}
   8  {:gallery/id 8
       :gallery/photos
                   [{:href/id    "cowboy-bebop" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/cbbb.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "made-in-abyss" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/MIA.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "technical-bovine" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/TB.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "elders-rafw" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/ERAFW.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "atcq-wgifh-tyfys" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/ATCQWGIFH.jpg"
                                  :image/alt "There Will Be Blood"}}]}
   7  {:gallery/id 7
       :gallery/photos
                   [{:href/id    "shaun" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/shaun.webp"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "conntra" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/contrapoints.webp"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "clojure" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/clojure.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "yms" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/YMS.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "ttc-flowerboy" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/TTCFB.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "street-epist" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/street-epist.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "potholer" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/potholer54.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "phil-overdose" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/philoverdose.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "phil-tube" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/philtube.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "k-uchis-c" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/KUC.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "kids-see-ghosts" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/KWKSG.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "tpab" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/KLTPAB.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "jack-saint" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/jack-saint.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "graduation" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/graduation.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "the-melon" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/anthony-fantano.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "4evaIAMLT" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/4EIAMLT.jpg"
                                  :image/alt "There Will Be Blood"}}]}
   6  {:gallery/id 6
       :gallery/photos
                   [
                    {:href/id    "nujabes" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/nujabes.webp"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "computer-science" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/computer-science.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "undertale" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/undertale.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "two-cents" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/twocents.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "flume-st" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/self-titled.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "sp-cherry-pepsi" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/saint-pepsi.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "lofi-hh" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/lofi-hiphop.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "k-state" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/KS.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "john-stewart" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/john-stewart.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "exurb1a" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/exurb1a.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "blu-swing-r" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/BSR.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "bunny-hop" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/bunnyhop.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "adam-conover" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/adam-conover.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "metro" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/M2033.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "mgs5" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/MGSV.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "soma" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/soma.png"
                                  :image/alt "There Will Be Blood"}}
                    ]}
   5  {:gallery/id 5
       :gallery/photos
        [
         {:href/id    "dofus" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
          :href/image {
                       :image/src "../images/Dofus.jpg"
                       :image/alt "There Will Be Blood"}}
         {:href/id    "pixel-dungeon" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
          :href/image {
                       :image/src "../images/PDRK.webp"
                       :image/alt "There Will Be Blood"}}
         {:href/id    "wolf-children" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
          :href/image {
                       :image/src "../images/wolf-children.jpg"
                       :image/alt "There Will Be Blood"}}
         {:href/id    "s&w" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
          :href/image {
                       :image/src "../images/SAW.jpg"
                       :image/alt "There Will Be Blood"}}
         {:href/id    "redline" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
          :href/image {
                       :image/src "../images/RL.jpg"
                       :image/alt "There Will Be Blood"}}
         {:href/id    "wakfu" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
          :href/image {
                       :image/src "../images/nox.jpg"
                       :image/alt "There Will Be Blood"}}
         {:href/id    "phil-for-dummies" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
          :href/image {
                       :image/src "../images/pho-for-dummies.gif"
                       :image/alt "There Will Be Blood"}}

         ]}
   4  {:gallery/id 4
       :gallery/photos
                   [
                    {:href/id    "re-4" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/RE4.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "terraria" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/TEC.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "oblivion" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/ESO.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "dragon's dogma" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/DD.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "adventure-time" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/adventuretime.webp"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "rsv2" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/RSV2.webp"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "kansas" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/kansas.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "phs" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/blu-jays.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "wise-crack" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/wisecrack.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "veritasium" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/ve.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "vsauce" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/vsauce.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "total-buscuit" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/total-biscuit.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "game-theory" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/TGT.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "sark" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/sark.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "seananers" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/seananners.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "smarter-every-day" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/SED.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "black-parade" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/MCRTBP.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "ff13-ost" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/FF13OST1.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "cgp-grey" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/cgp-grey.jpg"
                                  :image/alt "There Will Be Blood"}}
                    ]}
   3  {:gallery/id 3
       :gallery/photos
                   [
                    {:href/id    "avatar" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/ATLA.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "chowder" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/Chowder.jpeg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "shadow-the-hedgehog" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/STH.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "zelda-tlpc" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/ZTP.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "nebraska" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/nebraska.png"
                                  :image/alt "There Will Be Blood"}}
                    ]}
   2 {:gallery/id 2
      :gallery/photos
        [
         {:href/id    "billy-and-mandy" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
          :href/image {
                       :image/src "../images/BAM.jpg"
                       :image/alt "There Will Be Blood"}}
         {:href/id    "fhfif" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
          :href/image {
                       :image/src "../images/BLU.png"
                       :image/alt "There Will Be Blood"}}
         {:href/id    "wind-waker" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
          :href/image {
                       :image/src "../images/LZWW.jpg"
                       :image/alt "There Will Be Blood"}}
         {:href/id    "teen-titans" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
          :href/image {
                       :image/src "../images/TT.jpg"
                       :image/alt "There Will Be Blood"}}
         ]}
   1  {:gallery/id 1
       :gallery/photos
                   [
                    {:href/id    "aristocat" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/aristocat.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "eiffel-65" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/E65.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "ed-edd-n-eddy" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/EENE.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "gauntlet" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/GDL.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "monkey-ball" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/MB.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "melee" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/Melee.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "metroid-prime" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/MP.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "ocarina" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/ocarina.webp"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "mario-64" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/SM64.jpg"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "arkansas" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/AR.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "ctcd" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/CTCD.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "cn:knd" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/KND.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "majora" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/MM.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "sanic" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/SA2.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "sam-jack" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/SJ.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "seqoyah" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/SEQ.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "yu-gi-oh" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/yugioh.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "soccer" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/soccer.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "football" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
                     :href/image {
                                  :image/src "../images/football.png"
                                  :image/alt "There Will Be Blood"}}

                    ]}})

(defn build-image [{:image/keys [id alt src]}]
  {:image/id  (str id "-img")
   :image/alt alt
   :image/src src})

(defn add-id [id map key]
  (assoc map key id))

(defn build-href [{:href/keys [id link image]}]
  {:href/id    (str id "-href")
   :href/link  link
   :href/image (build-image (add-id (str id "-href") image :image/id))})

(defn build-gallery [id]
  (let [{:gallery/keys [id photos]} (get galleries id)]
    {:gallery/id id
     :gallery/photos
                 (mapv
                   (fn [{:href/keys [id link image]}]
                     (build-href
                       {:href/id   (str id "-href")
                        :href/link link
                        :href/image
                                   (build-image (add-id (str id "-href") image :image/id))}))
                   photos)}))

(defn get-timebox [id]
  (let [timebox (get timebox-entries id)
        {:timebox/keys [id left middle right]} timebox]
    {:timebox/id     id
     :timebox/left   (build-gallery left)
     :timebox/middle (node-options middle)
     :timebox/right  (build-gallery right)}))

(defn request [id table] (table id))
