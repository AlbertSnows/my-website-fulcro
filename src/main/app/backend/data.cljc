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
    :image/src "../images/middle-node.png"}
   :end
   {:image/id  "end-of-the-road"
    :image/alt "end of the node, cowboy"
    :image/src "../images/end-of-the-road.png"}})

(def left-arrow
  {:image/id  "LPR"
   :image/alt "left, point right"
   :image/src "../images/left-side-arrow.png"})
(def right-arrow
  {:image/id  "RPL"
   :image/alt "right, point left"
   :image/src "../images/right-side-arrow.png"})

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
                   [{:href/id    "how-now-brown-cow"
                     :href/link  "https://static.thenounproject.com/png/196644-200.png"
                     :href/image {:image/src "../images/what-do.png"
                                  :image/alt "I'm probably napping."}}]}
   15 {:gallery/id 15
       :gallery/photos
                   [{:href/id    "llhst"
                     :href/link  "https://www.youtube.com/watch?v=XY8IVZ3_F9Q"
                     :href/image {:image/src "../images/LLHST.jpg"
                                  :image/alt "Havas - Self-Titled"}}]}
   14 {:gallery/id 14
       :gallery/photos
                   [{:href/id    "cthg"
                     :href/link "https://youtu.be/bOADHQciDtE"
                     :href/image {:image/src "../images/crouching-tiger-hidden-sun.jpg"
                                  :image/alt "Crouching Tiger Hidden Gabber"}}
                    {:href/id    "wolf-girl"
                     :href/link "https://youtu.be/3R2XGxbpPNs"
                     :href/image {:image/src "../images/wolf-girl.jpg"
                                  :image/alt "wlfgrl"}}]}
   13 {:gallery/id 13
       :gallery/photos
                   [{:href/id    "DOS2"
                     :href/link  "https://youtu.be/qFmV2WA64tQ"
                     :href/image {:image/src "../images/DOS2.jpg"
                                  :image/alt "barrels OP"}}
                    {:href/id    "val"
                     :href/link  "https://youtu.be/H8w_Q57RQJc"
                     :href/image {:image/src "../images/VAHALLA.png"
                                  :image/alt "Time to mix drinks and change lives"}}
                    {:href/id    "sotl"
                     :href/link  "https://www.youtube.com/watch?v=8dzstxE_5Rc"
                     :href/image {:image/src "../images/SOTL.webp"
                                  :image/alt "You will always make the wrong choice"}}]}
   12 {:gallery/id 12
       :gallery/photos
                   [{:href/id    "tptb"
                     :href/link "https://youtu.be/v0qAIMzJ43E"
                     :href/image {:image/src "../images/the-powers-that-b.jpg"
                                  :image/alt "Inanimate Sensation"}}
                    {:href/id    "syn"
                     :href/link "https://youtu.be/j1LZ9EOMqsY"
                     :href/image {:image/src "../images/synecdoche.jpg"
                                  :image/alt "Can I put my head on your shoulder?"}}
                    {:href/id    "her"
                     :href/link "https://www.youtube.com/watch?v=m3RRuhxu0Z4"
                     :href/image {:image/src "../images/her.jpg"
                                  :image/alt "Your past is just a story you tell yourself."}}
                    {:href/id    "td"
                     :href/link "https://www.youtube.com/watch?v=8se9Zxy1Njo"
                     :href/image {:image/src "../images/taxidriver.jpg"
                                  :image/alt "You talkin to me?"}}
                    {:href/id    "lh"
                     :href/link "https://www.youtube.com/watch?v=WBZwUdJFnhw"
                     :href/image {:image/src "../images/lighthouse.jpg"
                                  :image/alt "Alright, have it your way. I like yer cookin."}}
                    {:href/id    "pf"
                     :href/link "https://youtu.be/Y6YBKdmOlM8"
                     :href/image {:image/src "../images/PF.png"
                                  :image/alt "SAY WHAT AGAIN"}}
                    {:href/id    "ob"
                     :href/link "https://youtu.be/VwIIDzrVVdc"
                     :href/image {:image/src "../images/oldboy.jpg"
                                  :image/alt "Laugh and the world laughs with you."}}
                    {:href/id    "atd"
                     :href/link "https://youtu.be/Gensf74KWVs"
                     :href/image {:image/src "../images/a-taxidriver.jpg"
                                  :image/alt "I taxi driver, you taxi customer."}}]}
   11  {:gallery/id 11
       :gallery/photos
                   [{:href/id    "paycom"
                     :href/link "https://www.paycom.com/"
                     :href/image {:image/src "../images/paycom-icon.PNG"
                                  :image/alt "I work here rn"}}
                    {:href/id    "okcity"
                     :href/link "https://www.okc.gov/"
                     :href/image {:image/src "../images/okcity.PNG"
                                  :image/alt "I live here rn"}}
                    {:href/id    "apdtbb"
                     :href/link "https://youtu.be/zXQTJ4VzcRM"
                     :href/image {:image/src "../images/APDTBB.jpg"
                                  :image/alt "Acting like James Dean"}}
                    {:href/id    "taboo"
                     :href/link "https://youtu.be/cr6vtUb4r5s"
                     :href/image {:image/src "../images/denzel-curry-taboo.jpg"
                                  :image/alt "Gorgeous, beautiful taboo"}}
                    {:href/id    "whats-your-pleasure"
                     :href/link "https://youtu.be/QwhkvO_hCOM"
                     :href/image {:image/src "../images/JWWYP.jpg"
                                  :image/alt "What's your pleasure?"}}
                    {:href/id    "clpping-space"
                     :href/link "https://youtu.be/PUR_ChqUJbc"
                     :href/image {:image/src "../images/SAM.jpg"
                                  :image/alt "Splendor & Misery"}}
                    {:href/id    "evangelion"
                     :href/link "https://youtu.be/Gkj0dpR4A0s"
                     :href/image {:image/src "../images/evangelion.jpg"
                                  :image/alt "Get in the robot shenji"}}
                    {:href/id    "gits-movie"
                     :href/link "https://youtu.be/YZX58fDhebc"
                     :href/image {:image/src "../images/GITSM.jpg"
                                  :image/alt "I feel confined, only free to expand myself within boundaries."}}
                    {:href/id    "gits-show"
                     :href/link "https://youtu.be/lNY53tZ2geg"
                     :href/image {:image/src "../images/GITSSAC.jpg"
                                  :image/alt "Stand Alone Compex"}}
                    {:href/id    "gits-ost-1"
                     :href/link "https://youtu.be/mfUpn6pJFrs"
                     :href/image {:image/src "../images/GITSSACOST2-1.jpg"
                                  :image/alt "OST1"}}
                    {:href/id    "gits-ost-2"
                     :href/link "https://youtu.be/lWhM_x_g6p4"
                     :href/image {:image/src "../images/GITSSACOST1.webp"
                                  :image/alt "OST2"}}
                    {:href/id    "gits-ost-3"
                     :href/link "https://youtu.be/YtqMOKuUNHs"
                     :href/image {:image/src "../images/GITSSACOST2.webp"
                                  :image/alt "OST3"}}]}
   10  {:gallery/id 10
       :gallery/photos
                   [{:href/id    "charlie"
                     :href/link "https://youtu.be/6-v1b9waHWY"
                     :href/image {:image/id  "CXCXC"
                                  :image/src "../images/CXCXC.png"
                                  :image/alt "There Will Be Blood"}}
                    {:href/id    "money-store"
                     :href/link "https://youtu.be/mWNdjEafof8"
                     :href/image {:image/src "../images/the-money-store.jpg"
                                  :image/alt "AYE AYE"}}
                    {:href/id    "flc"
                     :href/link "https://youtu.be/V_VeIP_l7iA"
                     :href/image {:image/src "../images/FLC.jpg"
                                  :image/alt "cosmogramma"}}]}
   9  {:gallery/id 9
       :gallery/photos
                   [{:href/id    "nails"
                     :href/link "https://youtu.be/jpBkQCNauvk"
                     :href/image {:image/src "../images/NYWNBOU.jpg"
                                  :image/alt "You Will Never Be One of Us"}}
                    {:href/id    "jr-worry"
                     :href/link "https://youtu.be/H16fdKfDv60"
                     :href/image {:image/src "../images/JRW.jpg"
                                  :image/alt "Worry"}}
                    {:href/id    "hop-along-byhod"
                     :href/link "https://youtu.be/QOZx5T1zDf0"
                     :href/image {:image/src "../images/HABYHOD.jpg"
                                  :image/alt "How Simple My Heart can be"}}]}
   8  {:gallery/id 8
       :gallery/photos
                   [{:href/id    "cowboy-bebop"
                     :href/link "https://youtu.be/utLWiscq8d4"
                     :href/image {:image/src "../images/cbbb.jpg"
                                  :image/alt "See you space cowboy"}}
                    {:href/id    "made-in-abyss"
                     :href/link "https://youtu.be/91lrTMy8AIM"
                     :href/image {:image/src "../images/MIA.jpg"
                                  :image/alt "To know? No cost is too high."}}
                    {:href/id    "technical-bovine"
                     :href/link "https://youtu.be/8iCtpM4AK3I"
                     :href/image {:image/src "../images/TB.jpg"
                                  :image/alt "Pls no bully"}}
                    {:href/id    "elders-rafw"
                     :href/link "https://youtu.be/AyDAig8Ucb0"
                     :href/image {:image/src "../images/ERAFW.jpg"
                                  :image/alt "Staving Off Truth"}}
                    {:href/id    "atcq-wgifh-tyfys"
                     :href/link "https://youtu.be/Fbuf6VbVQBQ"
                     :href/image {:image/src "../images/ATCQWGIFH.jpg"
                                  :image/alt "Thank you 4 your service"}}]}
   7  {:gallery/id 7
       :gallery/photos
                   [{:href/id    "shaun"
                     :href/link "https://youtu.be/r9qiCN7CcB8"
                     :href/image {:image/src "../images/shaun.webp"
                                  :image/alt "Skeleton senpai"}}
                    {:href/id    "conntra"
                     :href/link "https://youtu.be/fD2briZ6fB0"
                     :href/image {:image/src "../images/contrapoints.webp"
                                  :image/alt "Feminine Benis"}}
                    {:href/id    "clojure"
                     :href/link "https://youtu.be/BThkk5zv0DE"
                     :href/image {:image/src "../images/clojure.png"
                                  :image/alt "DATA"}}
                    {:href/id    "yms"
                     :href/link "https://youtu.be/zR8sRjBJgQ8"
                     :href/image {:image/src "../images/YMS.jpg"
                                  :image/alt "Your Movie Sucks"}}
                    {:href/id    "ttc-flowerboy"
                     :href/link "https://youtu.be/khMb3k-Wwvg"
                     :href/image {:image/src "../images/TTCFB.jpg"
                                  :image/alt "911"}}
                    {:href/id    "street-epist"
                     :href/link "https://youtu.be/Lvz5fcuw9j0"
                     :href/image {:image/src "../images/street-epist.jpg"
                                  :image/alt "Ask The Right Questions"}}
                    {:href/id    "potholer"
                     :href/link "https://youtu.be/EhWpP-vPUcQ"
                     :href/image {:image/src "../images/potholer54.jpg"
                                  :image/alt "Quackery!"}}
                    {:href/id    "phil-overdose"
                     :href/link "https://youtu.be/wY9Xm5xgiEE"
                     :href/image {:image/src "../images/philoverdose.jpg"
                                  :image/alt "Thanks grandpa"}}
                    {:href/id    "phil-tube"
                     :href/link "https://youtu.be/wO6uD3c2qMo"
                     :href/image {:image/src "../images/philtube.jpg"
                                  :image/alt "Phil tube"}}
                    {:href/id    "k-uchis-c"
                     :href/link "https://youtu.be/9f5zD7ZSNpQ"
                     :href/image {:image/src "../images/KUC.jpg"
                                  :image/alt "Nothing good ever comes easy"}}
                    {:href/id    "kids-see-ghosts"
                     :href/link "https://youtu.be/hQC8COGQ4BM"
                     :href/image {:image/src "../images/KWKSG.jpg"
                                  :image/alt "Keep moving forward"}}
                    {:href/id    "tpab"
                     :href/link "https://youtu.be/Wt4dUK4uce0"
                     :href/image {:image/src "../images/KLTPAB.jpg"
                                  :image/alt "don't mean a thing"}}
                    {:href/id    "jack-saint"
                     :href/link "https://youtu.be/MF--2o71w44"
                     :href/image {:image/src "../images/jack-saint.jpg"
                                  :image/alt "Jack Saint"}}
                    {:href/id    "graduation"
                     :href/link "https://youtu.be/hLRUVURf4NU"
                     :href/image {:image/src "../images/graduation.jpg"
                                  :image/alt "Flashing Lights"}}
                    {:href/id    "the-melon"
                     :href/link "https://youtu.be/f53eFH-tYxQ"
                     :href/image {:image/src "../images/anthony-fantano.jpg"
                                  :image/alt "MELON MAN"}}
                    {:href/id    "4evaIAMLT"
                     :href/link "https://youtu.be/047nimeOEAw"
                     :href/image {:image/src "../images/4EIAMLT.jpg"
                                  :image/alt "I'm coming home"}}]}
   6  {:gallery/id 6
       :gallery/photos
                   [{:href/id    "nujabes"
                     :href/link "https://youtu.be/Z-tTmSY4m4M"
                     :href/image {:image/src "../images/nujabes.webp"
                                  :image/alt "RIP a really cool dude"}}
                    {:href/id    "computer-science"
                     :href/link "https://youtu.be/AnVBCyzNgaU"
                     :href/image {:image/src "../images/computer-science.png"
                                  :image/alt "sudo apt get life"}}
                    {:href/id    "undertale"
                     :href/link "https://youtu.be/Vr4IYjeplJA"
                     :href/image {:image/src "../images/undertale.jpg"
                                  :image/alt "havin a bad time"}}
                    {:href/id    "two-cents"
                     :href/link "https://youtu.be/TtJXl6pk0Z4"
                     :href/image {:image/src "../images/twocents.jpg"
                                  :image/alt "FIRE spells free"}}
                    {:href/id    "flume-st"
                     :href/link "https://youtu.be/V7-yAX9ijuM"
                     :href/image {:image/src "../images/self-titled.jpg"
                                  :image/alt "sleeping"}}
                    {:href/id    "sp-cherry-pepsi"
                     :href/link "https://www.youtube.com/watch?v=OrR1TGQY20Y"
                     :href/image {:image/src "../images/saint-pepsi.jpg"
                                  :image/alt "cherry pepsi"}}
                    {:href/id    "lofi-hh"
                     :href/link "https://youtu.be/GjDd6wmvZ2I"
                     :href/image {:image/src "../images/lofi-hiphop.jpg"
                                  :image/alt "STEEZY AF"}}
                    {:href/id    "k-state"
                     :href/link "https://www.k-state.edu/"
                     :href/image {:image/src "../images/KS.jpg"
                                  :image/alt "KSU"}}
                    {:href/id    "john-stewart"
                     :href/link "https://youtu.be/_uYpDC3SRpM"
                     :href/image {:image/src "../images/john-stewart.jpg"
                                  :image/alt "John Stewart"}}
                    {:href/id    "exurb1a"
                     :href/link "https://youtu.be/lstXHHlLrKo"
                     :href/image {:image/src "../images/exurb1a.jpg"
                                  :image/alt "you twat"}}
                    {:href/id    "blu-swing-r"
                     :href/link "https://youtu.be/oONwQniVDjY"
                     :href/image {:image/src "../images/BSR.jpg"
                                  :image/alt "childish"}}
                    {:href/id    "bunny-hop"
                     :href/link "https://youtu.be/N_-jyF987MQ"
                     :href/image {:image/src "../images/bunnyhop.jpg"
                                  :image/alt "bunny hop"}}
                    {:href/id    "adam-conover"
                     :href/link "https://youtu.be/q4pV8cKXKnU"
                     :href/image {:image/src "../images/adam-conover.jpg"
                                  :image/alt "ARE"}}
                    {:href/id    "metro"
                     :href/link "https://youtu.be/o3BmubIQklU"
                     :href/image {:image/src "../images/M2033.jpg"
                                  :image/alt "metro"}}
                    {:href/id    "mgs5"
                     :href/link "https://youtu.be/KU98NsI6JV4"
                     :href/image {:image/src "../images/MGSV.png"
                                  :image/alt "phantom pain"}}
                    {:href/id    "soma"
                     :href/link "https://youtu.be/UnJ09VPnG-Y"
                     :href/image {:image/src "../images/soma.png"
                                  :image/alt "who am i?"}}]}
   5  {:gallery/id 5
       :gallery/photos
        [{:href/id    "dofus"
          :href/link "https://youtu.be/t5hmfnlwGGM"
          :href/image {:image/src "../images/Dofus.jpg"
                       :image/alt "dofus"}}
         {:href/id    "pixel-dungeon"
          :href/link "https://youtu.be/qpG5a5aLKNY"
          :href/image {:image/src "../images/PDRK.webp"
                       :image/alt "fukin crabs"}}
         {:href/id    "wolf-children"
          :href/link "https://youtu.be/go4puCMW0kg"
          :href/image {:image/src "../images/wolf-children.jpg"
                       :image/alt "wolf children"}}
         {:href/id    "s&w"
          :href/link "https://youtu.be/YaODUqK1UEw"
          :href/image {:image/src "../images/SAW.jpg"
                       :image/alt "you fool"}}
         {:href/id    "redline"
          :href/link "https://youtu.be/rRLPdgcGPRg"
          :href/image {:image/src "../images/RL.jpg"
                       :image/alt "redline day"}}
         {:href/id    "wakfu"
          :href/link "https://youtu.be/-KOH2Ocy778"
          :href/image {:image/src "../images/nox.jpg"
                       :image/alt "copyright fucking sucks"}}
         {:href/id    "phil-for-dummies"
          :href/link "https://youtu.be/QkP4g9e86qA"
          :href/image {:image/src "../images/pho-for-dummies.gif"
                       :image/alt "philosophy for dummies"}}

         ]}
   4  {:gallery/id 4
       :gallery/photos
                   [{:href/id    "re-4"
                     :href/link "https://youtu.be/9ZKCFq6rkzU"
                     :href/image {:image/src "../images/RE4.jpg"
                                  :image/alt "dammit ashley"}}
                    {:href/id    "terraria"
                     :href/link "https://youtu.be/0rI6cIlAs-k"
                     :href/image {:image/src "../images/TEC.jpg"
                                  :image/alt "them fukin dart traps man"}}
                    {:href/id    "oblivion"
                     :href/link "https://youtu.be/bliWxOtesF0"
                     :href/image {:image/src "../images/ESO.png"
                                  :image/alt "STOP. YOU'VE VIOLATED THE LAW."}}
                    {:href/id    "dragon's dogma"
                     :href/link "https://youtu.be/pHUJhBv5nWI"
                     :href/image {:image/src "../images/DD.png"
                                  :image/alt "stabstabstabstab"}}
                    {:href/id    "adventure-time"
                     :href/link "https://youtu.be/bNnfuvC1LlU"
                     :href/image {:image/src "../images/adventuretime.webp"
                                  :image/alt "adventure time"}}
                    {:href/id    "rsv2"
                     :href/link "https://youtu.be/Rp7-HRGkU-o"
                     :href/image {:image/src "../images/RSV2.webp"
                                  :image/alt "it's always that last dude"}}
                    {:href/id    "kansas"
                     :href/link "https://portal.kansas.gov/"
                     :href/image {:image/src "../images/kansas.png"
                                  :image/alt "we are actually in kansas...actually"}}
                    {:href/id    "phs"
                     :href/link "https://www.usd344.org/"
                     :href/image {:image/src "../images/blu-jays.png"
                                  :image/alt "pleasanton high school"}}
                    {:href/id    "wise-crack"
                     :href/link "https://youtu.be/cPNDMeDN07U"
                     :href/image {:image/src "../images/wisecrack.jpg"
                                  :image/alt "sup y'all?"}}
                    {:href/id    "veritasium"
                     :href/link "https://youtu.be/pTn6Ewhb27k"
                     :href/image {:image/src "../images/ve.jpg"
                                  :image/alt "veritasium"}}
                    {:href/id    "vsauce"
                     :href/link "https://youtu.be/s86-Z-CbaHA"
                     :href/image {:image/src "../images/vsauce.jpg"
                                  :image/alt "Hey, Vsauce! Michael here."}}
                    {:href/id    "total-buscuit"
                     :href/link "https://youtu.be/k69PbHoSWm4"
                     :href/image {:image/src "../images/total-biscuit.jpg"
                                  :image/alt "See you next time..."}}
                    {:href/id    "game-theory"
                     :href/link "https://youtu.be/LplSnXQMf38"
                     :href/image {:image/src "../images/TGT.jpg"
                                  :image/alt "a game theory"}}
                    {:href/id    "sark"
                     :href/link "https://youtu.be/sV455bHFxno"
                     :href/image {:image/src "../images/sark.jpg"
                                  :image/alt "oh shit"}}
                    {:href/id    "seananers"
                     :href/link "https://youtu.be/FDQx-guzx2s"
                     :href/image {:image/src "../images/seananners.jpg"
                                  :image/alt "i can smell you"}}
                    {:href/id    "smarter-every-day"
                     :href/link "https://youtu.be/ywBV6M7VOFU"
                     :href/image {:image/src "../images/SED.jpg"
                                  :image/alt "welcome back!"}}
                    {:href/id    "black-parade"
                     :href/link "https://youtu.be/lXB58kb6-1k"
                     :href/image {:image/src "../images/MCRTBP.jpg"
                                  :image/alt "would you be, savoir of the broken?"}}
                    {:href/id    "ff13-ost"
                     :href/link "https://youtu.be/SnvzYXtdeTo"
                     :href/image {:image/src "../images/FF13OST1.jpg"
                                  :image/alt "overrrrrrrrrrrr"}}
                    {:href/id    "cgp-grey"
                     :href/link "https://youtu.be/s7tWHJfhiyo"
                     :href/image {:image/src "../images/cgp-grey.jpg"
                                  :image/alt "hexagons are the bestagons"}}]}
   3  {:gallery/id 3
       :gallery/photos
                   [{:href/id    "avatar"
                     :href/link "https://youtu.be/P7jCcUEEcgo"
                     :href/image {:image/src "../images/ATLA.jpg"
                                  :image/alt "the last airbender"}}
                    {:href/id    "chowder"
                     :href/link "https://youtu.be/nGb-2oVDWXk"
                     :href/image {:image/src "../images/Chowder.jpeg"
                                  :image/alt "rada rada"}}
                    {:href/id    "shadow-the-hedgehog"
                     :href/link "https://youtu.be/Jl26fda_1PE"
                     :href/image {:image/src "../images/STH.png"
                                  :image/alt "NOTHING MATTERS. THERE IS ONLY DARKNESS."}}
                    {:href/id    "zelda-tlpc"
                     :href/link "https://youtu.be/NoBRdekWKxI"
                     :href/image {:image/src "../images/ZTP.png"
                                  :image/alt "midna's lament"}}
                    {:href/id    "nebraska"
                     :href/link "https://www.nebraska.gov/"
                     :href/image {:image/src "../images/nebraska.png"
                                  :image/alt "lots of snow"}}]}
   2 {:gallery/id 2
      :gallery/photos
        [
         {:href/id    "billy-and-mandy"
          :href/link "https://youtu.be/os36Sl56z3I"
          :href/image {:image/src "../images/BAM.jpg"
                       :image/alt "just around us all"}}
         {:href/id    "wind-waker"
          :href/link "https://youtu.be/VJsviRJ4voc"
          :href/image {:image/src "../images/LZWW.jpg"
                       :image/alt "HYA!"}}
         {:href/id    "teen-titans"
          :href/link "https://youtu.be/wTfY27KS8-8"
          :href/image {:image/src "../images/TT.jpg"
                       :image/alt "teen titans go!"}}]}
   1  {:gallery/id 1
       :gallery/photos
                   [{:href/id    "aristocat"
                     :href/link "https://www.youtube.com/watch?v=yRET1vsfiJM"
                     :href/image {:image/src "../images/aristocat.jpg"
                                  :image/alt "because a cat's the only cat"}}
                    {:href/id    "eiffel-65"
                     :href/link "https://youtu.be/68ugkg9RePc"
                     :href/image {:image/src "../images/E65.jpg"
                                  :image/alt "DABA DE DABA DI"}}
                    {:href/id    "ed-edd-n-eddy"
                     :href/link "https://youtu.be/gKKdTlvZ5kI"
                     :href/image {:image/src "../images/EENE.jpg"
                                  :image/alt "jawbreaker"}}
                    {:href/id    "gauntlet"
                     :href/link "https://youtu.be/Fq9gF9Zfx28"
                     :href/image {:image/src "../images/GDL.jpg"
                                  :image/alt "BLUE KNIGHT, NEEDS FOOD BADLY"}}
                    {:href/id    "monkey-ball"
                     :href/link "https://youtu.be/v4gddHNCqG8"
                     :href/image {:image/src "../images/MB.jpg"
                                  :image/alt "WUAAAHHHHH"}}
                    {:href/id    "melee"
                     :href/link "https://youtu.be/cU_QuldpVG0"
                     :href/image {:image/src "../images/Melee.jpg"
                                  :image/alt "GAME!"}}
                    {:href/id    "metroid-prime"
                     :href/link "https://youtu.be/7APf0XSdVhY"
                     :href/image {:image/src "../images/MP.jpg"
                                  :image/alt "metroid prime"}}
                    {:href/id    "ocarina"
                     :href/link "https://youtu.be/qh0nq2WDexA"
                     :href/image {:image/src "../images/ocarina.webp"
                                  :image/alt "hyuh"}}
                    {:href/id    "mario-64"
                     :href/link "https://youtu.be/Gb4Klzo9MuU"
                     :href/image {:image/src "../images/SM64.jpg"
                                  :image/alt "YAHOO"}}
                    {:href/id    "arkansas"
                     :href/link "https://portal.arkansas.gov/"
                     :href/image {:image/src "../images/AR.png"
                                  :image/alt "used to live here"}}
                    {:href/id    "ctcd"
                     :href/link "https://youtu.be/fKIrTD1nXZ0"
                     :href/image {:image/src "../images/CTCD.png"
                                  :image/alt "RETURN THE SLAB"}}
                    {:href/id    "cn:knd"
                     :href/link "https://youtu.be/rhT7k2U-LdM"
                     :href/image {:image/src "../images/KND.png"
                                  :image/alt "battlestations!"}}
                    {:href/id    "majora"
                     :href/link "https://youtu.be/lwplhlgCWBU"
                     :href/image {:image/src "../images/MM.png"
                                  :image/alt "NUT"}}
                    {:href/id    "sanic"
                     :href/link "https://youtu.be/D1jHA2Itfbw"
                     :href/image {:image/src "../images/SA2.png"
                                  :image/alt "GOT PLACES TO GO, GOTTA FOLLOW MY RAINBOW"}}
                    {:href/id    "sam-jack"
                     :href/link "https://youtu.be/sMqQOGTshak"
                     :href/image {:image/src "../images/SJ.png"
                                  :image/alt "extra thick"}}
                    {:href/id    "sequoyah"
                     :href/link "https://www.russellvilleschools.net/o/seq"
                     :href/image {:image/src "../images/SEQ.png"
                                  :image/alt "sequoyah"}}
                    {:href/id    "yu-gi-oh"
                     :href/link "https://youtu.be/Qp-7Bp3W9f0"
                     :href/image {:image/src "../images/yugioh.png"
                                  :image/alt "heart of the cards yugi"}}
                    {:href/id    "soccer"
                     :href/link "https://youtu.be/TcHZksIBR1M"
                     :href/image {:image/src "../images/soccer.png"
                                  :image/alt "idk what you were expecting, but neither did i"}}
                    {:href/id    "football"
                     :href/link "https://youtu.be/461rkyBR19s"
                     :href/image {:image/src "../images/football.png"
                                  :image/alt "It's 1AM I don't have anything else for you why are you here?"}}]}})

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
