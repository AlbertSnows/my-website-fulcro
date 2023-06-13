#!/usr/bin/env bash
yarn install # installs node packages
npx shadow-cljs release main # makes prod release of frontend cljs code
clojure -T:build uber # makes the uberjar
java -jar prod_build.jar # runs the uberjar, access should be at localhost:8080/index.html, refer to prod.edn