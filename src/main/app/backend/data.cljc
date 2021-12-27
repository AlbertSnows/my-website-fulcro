(ns app.backend.data
  (:require
    [app.backend.helpers.core :as h
     :refer [create-gallery-map build-href
             build-image add-id-to-map add-id]]))

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
(def hrefs
  {123 ["how-now-brown-cow"
        "https://static.thenounproject.com/png/196644-200.png"
        "../images/what-do.png"
        "I'm probably napping."]
   122 ["llhst"
        "https://www.youtube.com/watch?v=XY8IVZ3_F9Q"
        "../images/LLHST.jpg"
        "Havas - Self-Titled"]
   121 ["cthg"
        "https://youtu.be/bOADHQciDtE"
        "../images/crouching-tiger-hidden-sun.jpg"
        "Crouching Tiger Hidden Gabber"]
   120 ["wolf-girl"
        "https://youtu.be/3R2XGxbpPNs"
        "../images/wolf-girl.jpg"
        "wlfgrl"]
   119 ["DOS2"
        "https://youtu.be/qFmV2WA64tQ"
        "../images/DOS2.jpg"
        "barrels OP"]
   118 ["val"
        "https://youtu.be/H8w_Q57RQJc"
        "../images/VAHALLA.png"
        "Time to mix drinks and change lives"]
   117 ["sotl"
        "https://www.youtube.com/watch?v=8dzstxE_5Rc"
        "../images/SOTL.webp"
        "You will always make the wrong choice"]
   116 ["tptb"
        "https://youtu.be/v0qAIMzJ43E"
        "../images/the-powers-that-b.jpg"
        "Inanimate Sensation"]
   115 ["syn"
        "https://youtu.be/j1LZ9EOMqsY"
        "../images/synecdoche.jpg"
        "Can I put my head on your shoulder?"]
   114 ["her"
        "https://www.youtube.com/watch?v=m3RRuhxu0Z4"
        "../images/her.jpg"
        "Your past is just a story you tell yourself."]
   113 ["td"
        "https://www.youtube.com/watch?v=8se9Zxy1Njo"
        "../images/taxidriver.jpg"
        "You talkin to me?"]
   112 ["lh"
        "https://www.youtube.com/watch?v=WBZwUdJFnhw"
        "../images/lighthouse.jpg"
        "Alright, have it your way. I like yer cookin."]
   111 ["pf"
        "https://youtu.be/Y6YBKdmOlM8"
        "../images/PF.png"
        "SAY WHAT AGAIN"]
   110 ["ob"
        "https://youtu.be/VwIIDzrVVdc"
        "../images/oldboy.jpg"
        "Laugh and the world laughs with you."]
   109 ["atd"
        "https://youtu.be/Gensf74KWVs"
        "../images/a-taxidriver.jpg"
        "I taxi driver, you taxi customer."]
   108 ["paycom"
        "https://www.paycom.com/"
        "../images/paycom-icon.png"
        "I work here rn"]
   107 ["okcity"
        "https://www.okc.gov/"
        "../images/okcity.PNG"
        "I live here rn"]
   106 ["apdtbb"
        "https://youtu.be/zXQTJ4VzcRM"
        "../images/APDTBB.jpg"
        "Acting like James Dean"]
   105 ["taboo"
        "https://youtu.be/cr6vtUb4r5s"
        "../images/denzel-curry-taboo.jpg"
        "Gorgeous, beautiful taboo"]
   104 ["whats-your-pleasure"
        "https://youtu.be/QwhkvO_hCOM"
        "../images/JWWYP.jpg"
        "What's your pleasure?"]
   103 ["clpping-space"
        "https://youtu.be/PUR_ChqUJbc"
        "../images/SAM.jpg"
        "Splendor & Misery"]
   102 ["evangelion"
        "https://youtu.be/Gkj0dpR4A0s"
        "../images/evangelion.jpg"
        "Get in the robot shenji"]
   101 ["gits-movie"
        "https://youtu.be/YZX58fDhebc"
        "../images/GITSM.jpg"
        "I feel confined, only free to expand myself within boundaries."]
   100 ["gits-show"
        "https://youtu.be/lNY53tZ2geg"
        "../images/GITSSAC.jpg"
        "Stand Alone Compex"]
   99  ["gits-ost-1"
        "https://youtu.be/mfUpn6pJFrs"
        "../images/GITSSACOST2-1.jpg"
        "OST1"]
   98  ["gits-ost-2"
        "https://youtu.be/lWhM_x_g6p4"
        "../images/GITSSACOST1.webp"
        "OST2"]
   97  ["gits-ost-3"
        "https://youtu.be/YtqMOKuUNHs"
        "../images/GITSSACOST2.webp"
        "OST3"]
   96  ["charlie"
        "https://youtu.be/6-v1b9waHWY"
        "../images/CXCXC.png"
        "CXCXC"]
   95  ["money-store"
        "https://youtu.be/mWNdjEafof8"
        "../images/the-money-store.jpg"
        "AYE AYE"]
   94  ["flc"
        "https://youtu.be/V_VeIP_l7iA"
        "../images/FLC.jpg"
        "cosmogramma"]
   93  ["nails"
        "https://youtu.be/jpBkQCNauvk"
        "../images/NYWNBOU.jpg"
        "You Will Never Be One of Us"]
   92  ["jr-worry"
        "https://youtu.be/H16fdKfDv60"
        "../images/JRW.jpg"
        "Worry"]
   91  ["hop-along-byhod"
        "https://youtu.be/QOZx5T1zDf0"
        "../images/HABYHOD.jpg"
        "How Simple My Heart can be"]
   90  ["cowboy-bebop"
        "https://youtu.be/utLWiscq8d4"
        "../images/cbbb.jpg"
        "See you space cowboy"]
   89  ["made-in-abyss"
        "https://youtu.be/91lrTMy8AIM"
        "../images/MIA.jpg"
        "To know? No cost is too high."]
   88  ["technical-bovine"
        "https://youtu.be/8iCtpM4AK3I"
        "../images/TB.jpg"
        "Pls no bully"]
   87  ["elders-rafw"
        "https://youtu.be/AyDAig8Ucb0"
        "../images/ERAFW.jpg"
        "Staving Off Truth"]
   86  ["atcq-wgifh-tyfys"
        "https://youtu.be/Fbuf6VbVQBQ"
        "../images/ATCQWGIFH.jpg"
        "Thank you 4 your service"]
   85  ["shaun"
        "https://youtu.be/r9qiCN7CcB8"
        "../images/shaun.webp"
        "Skeleton senpai"]
   84  ["conntra"
        "https://youtu.be/fD2briZ6fB0"
        "../images/contrapoints.webp"
        "Feminine Benis"]
   83  ["clojure"
        "https://youtu.be/BThkk5zv0DE"
        "../images/clojure.png"
        "DATA"]
   82  ["yms"
        "https://youtu.be/zR8sRjBJgQ8"
        "../images/YMS.jpg"
        "Your Movie Sucks"]
   81  ["ttc-flowerboy"
        "https://youtu.be/khMb3k-Wwvg"
        "../images/TTCFB.jpg"
        "911"]
   80  ["street-epist"
        "https://youtu.be/Lvz5fcuw9j0"
        "../images/street-epist.jpg"
        "Ask The Right Questions"]
   79  ["potholer"
        "https://youtu.be/EhWpP-vPUcQ"
        "../images/potholer54.jpg"
        "Quackery!"]
   78  ["phil-overdose"
        "https://youtu.be/wY9Xm5xgiEE"
        "../images/philoverdose.jpg"
        "Thanks grandpa"]
   77  ["phil-tube"
        "https://youtu.be/wO6uD3c2qMo"
        "../images/philtube.jpg"
        "Phil tube"]
   76  ["k-uchis-c"
        "https://youtu.be/9f5zD7ZSNpQ"
        "../images/KUC.jpg"
        "Nothing good ever comes easy"]
   75  ["kids-see-ghosts"
        "https://youtu.be/hQC8COGQ4BM"
        "../images/KWKSG.jpg"
        "Keep moving forward"]
   74  ["tpab"
        "https://youtu.be/Wt4dUK4uce0"
        "../images/KLTPAB.jpg"
        "don't mean a thing"]
   73  ["jack-saint"
        "https://youtu.be/MF--2o71w44"
        "../images/jack-saint.jpg"
        "Jack Saint"]
   72  ["graduation"
        "https://youtu.be/hLRUVURf4NU"
        "../images/graduation.jpg"
        "Flashing Lights"]
   71  ["the-melon"
        "https://youtu.be/f53eFH-tYxQ"
        "../images/anthony-fantano.jpg"
        "MELON MAN"]
   70  ["4evaIAMLT"
        "https://youtu.be/047nimeOEAw"
        "../images/4EIAMLT.jpg"
        "I'm coming home"]
   69  ["nujabes"
        "https://youtu.be/Z-tTmSY4m4M"
        "../images/nujabes.webp"
        "RIP a really cool dude"]
   68  ["computer-science"
        "https://youtu.be/AnVBCyzNgaU"
        "../images/computer-science.png"
        "sudo apt get life"]
   67  ["undertale"
        "https://youtu.be/Vr4IYjeplJA"
        "../images/undertale.jpg"
        "havin a bad time"]
   66  ["two-cents"
        "https://youtu.be/TtJXl6pk0Z4"
        "../images/twocents.jpg"
        "FIRE spells free"]
   65  ["flume-st"
        "https://youtu.be/V7-yAX9ijuM"
        "../images/self-titled.jpg"
        "sleeping"]
   64  ["sp-cherry-pepsi"
        "https://www.youtube.com/watch?v=OrR1TGQY20Y"
        "../images/saint-pepsi.jpg"
        "cherry pepsi"]
   63  ["lofi-hh"
        "https://youtu.be/GjDd6wmvZ2I"
        "../images/lofi-hiphop.jpg"
        "STEEZY AF"]
   62  ["k-state"
        "https://www.k-state.edu/"
        "../images/KS.jpg"
        "KSU"]
   61  ["john-stewart"
        "https://youtu.be/_uYpDC3SRpM"
        "../images/john-stewart.jpg"
        "John Stewart"]
   60  ["exurb1a"
        "https://youtu.be/lstXHHlLrKo"
        "../images/exurb1a.jpg"
        "you twat"]
   59  ["blu-swing-r"
        "https://youtu.be/oONwQniVDjY"
        "../images/BSR.jpg"
        "childish"]
   58  ["bunny-hop"
        "https://youtu.be/N_-jyF987MQ"
        "../images/bunnyhop.jpg"
        "bunny hop"]
   57  ["adam-conover"
        "https://youtu.be/q4pV8cKXKnU"
        "../images/adam-conover.jpg"
        "ARE"]
   56  ["metro"
        "https://youtu.be/o3BmubIQklU"
        "../images/M2033.jpg"
        "metro"]
   55  ["mgs5"
        "https://youtu.be/KU98NsI6JV4"
        "../images/MGSV.png"
        "phantom pain"]
   54  ["soma"
        "https://youtu.be/UnJ09VPnG-Y"
        "../images/soma.png"
        "who am i?"]
   53  ["dofus"
        "https://youtu.be/t5hmfnlwGGM"
        "../images/Dofus.jpg"
        "dofus"]
   52  ["pixel-dungeon"
        "https://youtu.be/qpG5a5aLKNY"
        "../images/PDRK.webp"
        "fukin crabs"]
   51  ["wolf-children"
        "https://youtu.be/go4puCMW0kg"
        "../images/wolf-children.jpg"
        "wolf children"]
   50  ["s&w"
        "https://youtu.be/YaODUqK1UEw"
        "../images/SAW.jpg"
        "you fool"]
   49  ["redline"
        "https://youtu.be/rRLPdgcGPRg"
        "../images/RL.jpg"
        "redline day"]
   48  ["wakfu"
        "https://youtu.be/-KOH2Ocy778"
        "../images/nox.jpg"
        "copyright fucking sucks"]
   47  ["phil-for-dummies"
        "https://youtu.be/QkP4g9e86qA"
        "../images/pho-for-dummies.gif"
        "philosophy for dummies"]
   46  ["re-4"
        "https://youtu.be/9ZKCFq6rkzU"
        "../images/RE4.jpg"
        "dammit ashley"]
   45  ["terraria"
        "https://youtu.be/0rI6cIlAs-k"
        "../images/TEC.jpg"
        "them fukin dart traps man"]
   44  ["oblivion"
        "https://youtu.be/bliWxOtesF0"
        "../images/ESO.png"
        "STOP. YOU'VE VIOLATED THE LAW."]
   43  ["dragon's dogma"
        "https://youtu.be/pHUJhBv5nWI"
        "../images/DD.png"
        "stabstabstabstab"]
   42  ["adventure-time"
        "https://youtu.be/bNnfuvC1LlU"
        "../images/adventuretime.webp"
        "adventure time"]
   41  ["rsv2"
        "https://youtu.be/Rp7-HRGkU-o"
        "../images/RSV2.webp"
        "it's always that last dude"]
   40  ["kansas"
        "https://portal.kansas.gov/"
        "../images/kansas.png"
        "we are actually in kansas...actually"]
   39  ["phs"
        "https://www.usd344.org/"
        "../images/blu-jays.png"
        "pleasanton high school"]
   38  ["wise-crack"
        "https://youtu.be/cPNDMeDN07U"
        "../images/wisecrack.jpg"
        "sup y'all?"]
   37  ["veritasium"
        "https://youtu.be/pTn6Ewhb27k"
        "../images/ve.jpg"
        "veritasium"]
   36  ["vsauce"
        "https://youtu.be/s86-Z-CbaHA"
        "../images/vsauce.jpg"
        "Hey, Vsauce! Michael here."]
   35  ["total-buscuit"
        "https://youtu.be/k69PbHoSWm4"
        "../images/total-biscuit.jpg"
        "See you next time..."]
   34  ["game-theory"
        "https://youtu.be/LplSnXQMf38"
        "../images/TGT.jpg"
        "a game theory"]
   33  ["sark"
        "https://youtu.be/sV455bHFxno"
        "../images/sark.jpg"
        "oh shit"]
   32  ["seananers"
        "https://youtu.be/FDQx-guzx2s"
        "../images/seananners.jpg"
        "i can smell you"]
   31  ["smarter-every-day"
        "https://youtu.be/ywBV6M7VOFU"
        "../images/SED.jpg"
        "welcome back!"]
   30  ["black-parade"
        "https://youtu.be/lXB58kb6-1k"
        "../images/MCRTBP.jpg"
        "would you be, savoir of the broken?"]
   29  ["ff13-ost"
        "https://youtu.be/SnvzYXtdeTo"
        "../images/FF13OST1.jpg"
        "overrrrrrrrrrrr"]
   28  ["cgp-grey"
        "https://youtu.be/s7tWHJfhiyo"
        "../images/cgp-grey.jpg"
        "hexagons are the bestagons"]
   27  ["avatar"
        "https://youtu.be/P7jCcUEEcgo"
        "../images/ATLA.jpg"
        "the last airbender"]
   26  ["chowder"
        "https://youtu.be/nGb-2oVDWXk"
        "../images/Chowder.jpeg"
        "rada rada"]
   25  ["shadow-the-hedgehog"
        "https://youtu.be/Jl26fda_1PE"
        "../images/STH.png"
        "NOTHING MATTERS. THERE IS ONLY DARKNESS."]
   24  ["zelda-tlpc"
        "https://youtu.be/NoBRdekWKxI"
        "../images/ZTP.png"
        "midna's lament"]
   23  ["nebraska"
        "https://www.nebraska.gov/"
        "../images/nebraska.png"
        "lots of snow"]
   22  ["billy-and-mandy"
        "https://youtu.be/os36Sl56z3I"
        "../images/BAM.jpg"
        "just around us all"]
   21  ["wind-waker"
        "https://youtu.be/VJsviRJ4voc"
        "../images/LZWW.jpg"
        "HYA!"]
   20  ["teen-titans"
        "https://youtu.be/wTfY27KS8-8"
        "../images/TT.jpg"
        "teen titans go!"]
   19  ["aristocat"
        "https://www.youtube.com/watch?v=yRET1vsfiJM"
        "../images/aristocat.jpg"
        "because a cat's the only cat"]
   18  ["eiffel-65"
        "https://youtu.be/68ugkg9RePc"
        "../images/E65.jpg"
        "DABA DE DABA DI"]
   17  ["ed-edd-n-eddy"
        "https://youtu.be/gKKdTlvZ5kI"
        "../images/EENE.jpg"
        "jawbreaker"]
   16  ["gauntlet"
        "https://youtu.be/Fq9gF9Zfx28"
        "../images/GDL.jpg"
        "BLUE KNIGHT, NEEDS FOOD BADLY"]
   15  ["monkey-ball"
        "https://youtu.be/v4gddHNCqG8"
        "../images/MB.jpg"
        "WUAAAHHHHH"]
   14  ["melee"
        "https://youtu.be/cU_QuldpVG0"
        "../images/Melee.jpg"
        "GAME!"]
   13  ["metroid-prime"
        "https://youtu.be/7APf0XSdVhY"
        "../images/MP.jpg"
        "metroid prime"]
   12  ["ocarina"
        "https://youtu.be/qh0nq2WDexA"
        "../images/ocarina.webp"
        "hyuh"]
   11  ["mario-64"
        "https://youtu.be/Gb4Klzo9MuU"
        "../images/SM64.jpg"
        "YAHOO"]
   10  ["arkansas"
        "https://portal.arkansas.gov/"
        "../images/AR.png"
        "used to live here"]
   9   ["ctcd"
        "https://youtu.be/fKIrTD1nXZ0"
        "../images/CTCD.png"
        "RETURN THE SLAB"]
   8   ["cn:knd"
        "https://youtu.be/rhT7k2U-LdM"
        "../images/KND.png"
        "battlestations!"]
   7   ["majora"
        "https://youtu.be/lwplhlgCWBU"
        "../images/MM.png"
        "NUT"]
   6   ["sanic"
        "https://youtu.be/D1jHA2Itfbw"
        "../images/SA2.png"
        "GOT PLACES TO GO, GOTTA FOLLOW MY RAINBOW"]
   5   ["sam-jack"
        "https://youtu.be/sMqQOGTshak"
        "../images/SJ.png"
        "extra thick"]
   4   ["sequoyah"
        "https://www.russellvilleschools.net/o/seq"
        "../images/SEQ.png"
        "sequoyah"]
   3   ["yu-gi-oh"
        "https://youtu.be/Qp-7Bp3W9f0"
        "../images/yugioh.png"
        "heart of the cards yugi"]
   2   ["soccer"
        "https://youtu.be/TcHZksIBR1M"
        "../images/soccer.png"
        "idk what you were expecting, but neither did i"]
   1   ["football"
        "https://youtu.be/461rkyBR19s"
        "../images/football.png"
        "It's 1AM I don't have anything else for you why are you here?"]})
(defn grab-from-hrefs [ids] (mapv #(get hrefs %) ids))
(def galleries
  {16 (create-gallery-map 16 (grab-from-hrefs [123]))
   15 (create-gallery-map 15 (grab-from-hrefs [122 121 120 119 118 117]))
   14 (create-gallery-map 14 (grab-from-hrefs [116 115 114 113 112]))
   13 (create-gallery-map 13 (grab-from-hrefs [111 110 109 108 107]))
   12 (create-gallery-map 12 (grab-from-hrefs [106 105 104 103 102 101]))
   11 (create-gallery-map 11 (grab-from-hrefs [100 99 98 97 96 95]))
   10 (create-gallery-map 10 (grab-from-hrefs [94 93 92 91 90 89]))
   9  (create-gallery-map 9 (grab-from-hrefs [88 87 86 85 84]))
   8  (create-gallery-map 8 (grab-from-hrefs [83 82 81 80 79 78 77]))
   7  (create-gallery-map 7 (grab-from-hrefs [76 75 74 73 72 71 70]))
   6  (create-gallery-map 6 (grab-from-hrefs [69 68 67 66 65 64 63 62 61 60 59 58]))
   5  (create-gallery-map 5 (grab-from-hrefs [57 56 55 54 53 52 51 50 49 48 47]))
   4  (create-gallery-map 4 (grab-from-hrefs [46 45 44 43 42 41 40 39 38 37 36 35]))
   3  (create-gallery-map 3 (grab-from-hrefs [34 33 32 31 30 29 28 27 26 25 24 23]))
   2  (create-gallery-map 2 (grab-from-hrefs [22 21 20 19 18 17 16 15 14 13]))
   1  (create-gallery-map 1 (grab-from-hrefs [12 11 10 9 8 7 6 5 4 3 2 1]))})
(defn request [id table] (table id))
(defn build-gallery [id]
  (let [{:gallery/keys [id photos]} (get galleries id)]
    {:gallery/id id
     :gallery/photos
     (mapv
       (fn [{:href/keys [id link image]}]
         (build-href
           {:href/id   id
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
