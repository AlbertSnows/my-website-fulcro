install:
	yarn install

release:
	npx shadow-cljs release main

build_lin:
	clj -T:build uber

# https://github.com/clojure/tools.deps.alpha/wiki/clj-on-Windows
# need to run as powershell command for it to be recognized
build_win:
	powershell -command clj -T:build uber

run:
	java -jar target/prod_build.jar

# runs all the commands at once
all_win:
	yarn install
	npx shadow-cljs release main
	powershell -command clj -T:build uber
	java -jar prod_build.jar

all_lin:
	yarn install
	npx shadow-cljs release main
	powershell -command clj -T:build uber
	java -jar prod_build.jar
