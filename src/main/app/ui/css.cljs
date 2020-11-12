(ns app.ui.css)

(def Timebox
  {:css
  [[:.about-left
    {:display "flex"
     :justify-content "flex-end"
     :margin-top "12em"}]
   [:.about-right
    {:display "flex"
     :justify-content "flex-start"
     :align-content "space-evenly"
     :align-self "flex-end"
     :margin-bottom "12em"}]
   [:.div>img
    {:width "10%"
     :height "8%"}]
   [:.timebox
    {:display "flex"}]]})

(def Href
  {        :css [[:.href-container
                  {:display "flex"
                   :justify-content "center"
                   :margin "1em 0em"}]
                 [:.href-container>img
                  {:height "auto"
                   :max-width "100%"
                   }]]})

(def Contact
  [[:.general-container
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
   ])