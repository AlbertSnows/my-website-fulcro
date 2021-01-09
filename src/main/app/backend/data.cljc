(ns app.backend.data
	(:require
		;[com.fulcrologic.fulcro.components :as comp
		; :refer [defsc factory get-query get-initial-state]]
		;[com.fulcrologic.fulcro-css.localized-dom :as dom
		; :refer [div img a p]]
		;[com.fulcrologic.fulcro.ui-state-machines :as uism
		; :refer [defstatemachine]]
		;[com.fulcrologic.fulcro-css.css :as css
		; :refer [get-classnames]]
		[taoensso.timbre :as log]
		;[app.backend.css :as uicss]
		)
	)

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


; {:id :left :middle :right}
(def timebox-entries
	{6 {:left 18
			:middle 17
			:right 16}
	 5 {:left 15
			:middle 14
			:right 13}
	 4 {:left 12
			:middle 11
			:right 10}
	 3 {:left 9
			:middle 8
			:right 7}
	 2 {:left 4
			:middle 6
			:right 5}
	 1 {:left 2
			:middle 3
			:right 1}
	 -1 {:left -1
			 :middle -3
			 :right -2}})

; {:id :side :gallery}
(def side
	{18 {:side left :gallery 11}
	 17 {:side middle :gallery first-k}
	 16 {:side right :gallery 10}
	 15 {:side left :gallery 9}
	 14 {:side middle :gallery middle}
	 13 {:side right :gallery 8}
	 12 {:side left :gallery 7}
	 11 {:side middle :gallery middle}
	 10 {:side right :gallery 6}
	 9 {:side left :gallery 5}
	 8 {:side middle :gallery middle}
	 7 {:side right :gallery 4}
	 6 {:side middle :gallery middle}
	 5 {:side right :gallery nil}
	 4 {:side left :gallery 3}
	 3 {:side middle :gallery end}
	 2 {:side left :gallery 2}
	 1 {:side right :gallery 1}
	 -1 {:side left :gallery nil}
	 -2 {:side right :gallery nil}
	 -3 {:side middle :gallery middle}})

; {:id :images}
(def galleries
	{11 [{:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
				:image {:id  "twbb"
								:src "../images/clpping.webp"
								:alt "There Will Be Blood"}}
			 {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
				:image {:id  "twbb"
								:src "../images/collapse.webp"
								:alt "There Will Be Blood"}}
			 {:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
				:image {:id  "twbb"
								:src "../images/SAW.webp"
								:alt "There Will Be Blood"}}
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
								:alt "There Will Be Blood"}}]
	 10 [{:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
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
								:alt "There Will Be Blood"}}]
	 9 [{:link  "www.google.com"
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
							 :alt "There Will Be Blood"}}]
	 8 [{:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
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
							 :alt "There Will Be Blood"}}]
	 7 [{:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
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
							 :alt "There Will Be Blood"}}]
	 6 [{:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
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
							 :alt "There Will Be Blood"}}]
	 5 [{:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
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
							 :alt "There Will Be Blood"}}]
	 4 [{:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
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
							 :alt "There Will Be Blood"}}]
	 3 [{:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
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
							 :alt "There Will Be Blood"}}]
	 2 [{:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
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
							 :alt "There Will Be Blood"}}]
	 1 [{:link  "https://www.youtube.com/watch?v=dOEYT0wZFNg"
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
							 :alt "There Will Be Blood"}}]})

(defn get-gallery [id]
	(get galleries id))

(defn get-side [id]
	(let [gallery (:gallery (get side id))]
		(if (or (= :middle gallery) (= :end gallery) (= :first gallery))
			gallery
			(get-gallery gallery))))

(defn setup-timebox-map [id left middle right]
	{:timebox/id id})

(defn get-timebox [id]
	(let [timebox (get timebox-entries id)]
		{:timebox/id id
		 :timebox/left (get-side (:left timebox))
		 :timebox/middle (get-side (:middle timebox))
		 :timebox/right (get-side (:right timebox))}))
