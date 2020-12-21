(ns app.ui.mutations
  (:require
    [com.fulcrologic.fulcro.algorithms.normalized-state :refer [swap!->]]
    [com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]))

(defmutation toggle [{:keys [id]}]
  (action
    [{:keys [state] :as env}]
    (swap!->
      state
      (update-in [:sidebar-contents/id id :sidebar-contents/state]
                 #(if (= %1 "closed") "open" "closed"))
      (update-in [:sidebar/id id :sidebar/state]
                 #(if (= %1 "closed") "open" "closed")))))

;(defmutation load-text [{:keys [id]}]
;  ())
;
;(defmutation load-all [{:keys [id]}]
;  ())

;What do you mean? Maybe I'm being naive, but could you not just use fulcro load!s triggered when hitting the bottom of the page? Maybe have them parameterized so the server knows how many segment's you've already loaded?
;The initial load is relatively standard for fulcro. Depending on your app, that would be something that happens on page load, dynamic router events, or state machine events. Running raw load! s with segments-loaded parameter and post-mutation might do the trick for the rest of your needs.
;Of course, building a custom mutation for loading this would also not be very hard. Especially with built in ok-action and error-action

;const adjustPaddings = isScrollDown => {
;                                        const container = document.querySelector(".cat-list");
;                                              const currentPaddingTop = getNumFromStyle(container.style.paddingTop);
;                                        const currentPaddingBottom = getNumFromStyle(container.style.paddingBottom);
;                                              const remPaddingsVal = 170 * (listSize / 2);
;                                              if (isScrollDown) {
;                                                                 container.style.paddingTop = currentPaddingTop + remPaddingsVal + "px";
;                                                                                            container.style.paddingBottom = currentPaddingBottom === 0 ? "0px" : currentPaddingBottom - remPaddingsVal + "px";
;                                                                 } else {
;                                                                         container.style.paddingBottom = currentPaddingBottom + remPaddingsVal + "px";
;                                                                                                       container.style.paddingTop = currentPaddingTop === 0 ? "0px" : currentPaddingTop - remPaddingsVal + "px";
;                                                                         }
;                                        }

;const recycleDOM = firstIndex => {
;                                  for (let i = 0; i < listSize; i++) {
;                                             const tile = document.querySelector("#cat-tile-" + i);
;                                             tile.firstElementChild.innerText = DB[i + firstIndex].title;
;                                             tile.lastChild.setAttribute("src", DB[i + firstIndex].imgSrc);
;                                  }
;}

;const initIntersectionObserver = () => {
;                                        const options = {
;                                                         /* root: document.querySelector(".cat-list") */
;  }
;
;const callback = entries => {
;                             entries.forEach(entry => {
;                                                       if (entry.target.id === 'cat-tile-0') {
;                                                                                              topSentCallback(entry);
;                                                                                              } else if (entry.target.id === `cat-tile-${listSize - 1}`) {
;                                                                                                                                                          botSentCallback(entry);
;                                                                                                                                                          }
;                                                       });
;                             }
;
;var observer = new IntersectionObserver(callback, options);
;observer.observe(document.querySelector("#cat-tile-0"));
;observer.observe(document.querySelector(`#cat-tile-${listSize - 1}`));
;}