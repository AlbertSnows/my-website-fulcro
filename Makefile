install:
	yarn install

release:
	npx shadow-cljs release main

build_lin:
	clj -T:build uber

# https://stackoverflow.com/questions/70613828/clojure-installed-but-clj-fails <- if you get errors related to this
# https://github.com/clojure/tools.deps.alpha/wiki/clj-on-Windows
# need to run as powershell command for it to be recognized
build_win:
	powershell -command clj -T:build uber

run:
	java -jar target/prod_build.jar

# runs all the commands at once
all_win: install release build_win run

all_lin: install release build_lin run
