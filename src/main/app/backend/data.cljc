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
	{7  {:timebox/id     7
			 :timebox/left   13
			 :timebox/middle first-k
			 :timebox/right  12}
	 6  {:timebox/id     6
			 :timebox/left   11
			 :timebox/middle middle
			 :timebox/right  10}
	 5  {:timebox/id     5
			 :timebox/left   9
			 :timebox/middle middle
			 :timebox/right  8}
	 4  {:timebox/id     4
			 :timebox/left   7
			 :timebox/middle middle
			 :timebox/right  6}
	 3  {:timebox/id     3
			 :timebox/left   5
			 :timebox/middle middle
			 :timebox/right  4}
	 2  {:timebox/id     2
			 :timebox/left   3
			 :timebox/middle middle
			 :timebox/right  nil}
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
	{13 {:gallery/id 13
			 :gallery/photos
									 [
										{:href/id    "how-now-brown-cow"
										 :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/what-do.png"
																	:image/alt "What am I doing now?"}}
										]}
	 12 {:gallery/id 12
			 :gallery/photos
									 [
										{:href/id    "clpping"
										 :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/clpping.webp"
																	:image/alt "There Will Be Blood"}}
										]}
	 11 {:gallery/id 11
			 :gallery/photos
									 [
										{:href/id    "collapse"
										 :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/collapse.webp"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "saw"
										 :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/SAW.webp"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "vbbb"
										 :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/VBBB.webp"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "llhst"
										 :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/LLHST.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "brigador" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/brigador.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "cths" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/crouching-tiger-hidden-sun.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "ws" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/windswept.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "wolf-girl" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/wolf-girl.jpg"
																	:image/alt "There Will Be Blood"}}
										]}
	 10 {:gallery/id 10
			 :gallery/photos
									 [
										{:href/id    "DOS2"
										 :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/DOS2.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "val"
										 :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/VAHALLA.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "sotl"
										 :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/SOTL.webp"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "tptb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/the-powers-that-b.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "um" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/ultra-mono.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "fn" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/FN.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "syn" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/synecdoche.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "th" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/the-hunt.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "her" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/her.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "td" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/taxidriver.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "tbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/TBB.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "para" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/parasite.jpg"
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
										{:href/id    "burning" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/src "../images/burning.jpg"
																	:image/alt "There Will Be Blood"}}
										]}
	 9  {:gallery/id 9
			 :gallery/photos
									 [{:href/id    "paycom" :href/link "www.google.com"
										 :href/image {:image/src "../images/paycom-icon.PNG"
																	:image/alt "I work here rn"}}
										{:href/id    "okcity" :href/link "www.google.com"
										 :href/image {:image/src "../images/okcity.PNG"
																	:image/alt "I live here rn"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/APDTBB.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/denzel-curry-taboo.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/HTIF.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/JWWYP.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/LLHB.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/ruinerOST.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/SAM.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/SMAQF.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/evangelion.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/GITSM.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/GITSSAC.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/GITSSACOST2-1.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/GITSSACOST1.webp"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/GITSSACOST2.webp"
																	:image/alt "There Will Be Blood"}}]}
	 8  {:gallery/id 8
			 :gallery/photos
									 [{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {:image/id  "CXCXC"
																	:image/src "../images/CXCXC.png"
																	:image/alt "There Will Be Blood"}}
										;{:href/id "twbb" :href/link   "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										; :href/image {
										;							:image/src "../images/exmilitary.png"
										;							:image/alt "There Will Be Blood"}}
										;{:href/id "twbb" :href/link   "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										; :href/image {
										;							:image/src "../images/TER.jpg"
										;							:image/alt "There Will Be Blood"}}
										;{:href/id "twbb" :href/link   "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										; :href/image {
										;							:image/src "../images/the-money-store.jpg"
										;							:image/alt "There Will Be Blood"}}
										;{:href/id "twbb" :href/link   "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										; :href/image {
										;							:image/src "../images/off-the-wall.jpg"
										;							:image/alt "There Will Be Blood"}}
										;{:href/id "twbb" :href/link   "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										; :href/image {
										;							:image/src "../images/FWMR.jpg"
										;							:image/alt "There Will Be Blood"}}
										;{:href/id "twbb" :href/link   "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										; :href/image {
										;							:image/src "../images/FLC.jpg"
										;							:image/alt "There Will Be Blood"}}
										;{:href/id "twbb" :href/link   "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										; :href/image {
										;							:image/src "../images/ETAD.jpg"
										;							:image/alt "There Will Be Blood"}}
										;{:href/id "twbb" :href/link   "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										; :href/image {
										;							:image/src "../images/blond.jpg"
										;							:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/bottomless-pit.jpg"
																	:image/alt "There Will Be Blood"}}]}
	 7  {:gallery/id 7
			 :gallery/photos
									 [{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/COF.webp"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/XCOM2.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/thriller.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/NYWNBOU.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/JRW.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/HABYHOD.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/ASATT.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/TGBH.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/TR.jpg"
																	:image/alt "There Will Be Blood"}}]}
	 6  {:gallery/id 6
			 :gallery/photos
									 [{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/cbbb.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/MIA.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/saturation.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/TB.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/NCMM.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/GKMC.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/FLYD.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/ERAFW.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/ATCQWGIFH.jpg"
																	:image/alt "There Will Be Blood"}}]}
	 5  {:gallery/id 5
			 :gallery/photos
									 [{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/shaun.webp"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/contrapoints.webp"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/clojure.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/YMS.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/vaporwave.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/willschoder.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/tourist-history.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/TTCFB.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/threearrows.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/street-epist.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/potholer54.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/primer.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/philoverdose.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/philtube.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/myles-power.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/nakeyjakey.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/KUC.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/KWKSG.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/KWTLP.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/KLTPAB.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/jack-saint.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/graduation.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/anthony-fantano.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/4EIAMLT.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/AAO.jpg"
																	:image/alt "There Will Be Blood"}}]}
	 4  {:gallery/id 4
			 :gallery/photos
									 [{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/nujabes.webp"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/computer-science.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/undertale.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/twocents.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/tierzoo.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/tomscott.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/self-titled.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/saint-pepsi.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/lofi-hiphop.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/knowing-better.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/knowledge-hub.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/KS.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/john-stewart.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/furi.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/future-bounces.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/exurb1a.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/BSR.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/bunnyhop.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/adam-conover.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/Dofus.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/KS.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/M2033.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/MGSV.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/soma.png"
																	:image/alt "There Will Be Blood"}}]}
	 3  {:gallery/id 3
			 :gallery/photos
									 [{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/NCFOM.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/AWGB.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/NNB.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/RE4.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/TEC.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/ESO.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/DD.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/adventuretime.webp"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/PDRK.webp"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/EP.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/mononoke.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/wolf-children.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/SAW.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/RSV2.webp"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/minutephysics.webp"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/adventuretime.webp"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/scishow.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/monstercat.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/numberphile.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/kansas.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/ESO.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/DD.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/crash-course.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/blu-jays.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/wisecrack.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/ve.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/vsauce.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/total-biscuit.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/TGT.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/TEC.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/sark.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/seananners.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/SED.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/RL.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/pheonix-wolfgang.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/nox.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/MCRDD.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/MCRTBP.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/MCRTCFSR.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/FF13OST1.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/FF13OST-A1.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/cgp-grey.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/pho-for-dummies.gif"
																	:image/alt "There Will Be Blood"}}]}
	 2  {:gallery/id 2
			 :gallery/photos
									 [
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/ATLA.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/Chowder.jpeg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/STH.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/ZTP.png"
																	:image/alt "There Will Be Blood"}}
										]}
	 1  {:gallery/id 1
			 :gallery/photos
									 [
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/aristocat.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/BAM.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/E65.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/EENE.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/GDL.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/LZWW.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/MB.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/Melee.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/MP.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/ocarina.webp"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/SM64.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/TT.jpg"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/AR.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/BLU.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/CTCD.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/KND.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/MM.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/SA2.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/SJ.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/SEQ.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/yugioh.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/soccer.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/nebraska.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/KND.png"
																	:image/alt "There Will Be Blood"}}
										{:href/id    "twbb" :href/link "https://www.youtube.com/watch?v=dOEYT0wZFNg"
										 :href/image {
																	:image/src "../images/football.png"
																	:image/alt "There Will Be Blood"}}]}})

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

;(defn build-timebox
;	[{:timebox/keys [id left middle right] :as timebox-data}]
;	(assoc timebox-data
;		:timebox/left (make-gallery id left)
;		:timebox/middle (node-options middle)
;		:timebox/right (make-gallery id right)))

(defn request [id table] (table id))
