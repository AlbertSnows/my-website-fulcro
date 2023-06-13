install:
	yarn install

release:
	npx shadow-cljs release main

build:
	clojure -T:build uber

run:
	java -jar prod_build.jar