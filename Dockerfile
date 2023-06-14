FROM clojure:latest

WORKDIR /app

# Using Debian, as root
RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash -

# Install yarn
RUN apt-get update && apt-get install -y curl gnupg2
RUN curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | apt-key add -
RUN echo "deb https://dl.yarnpkg.com/debian/ stable main" | tee /etc/apt/sources.list.d/yarn.list
RUN apt-get update && apt-get install -y yarn

COPY package.json ./package.json

RUN yarn install

CMD npx shadow-cljs release main

COPY . .

EXPOSE 8080

CMD clj -T:build uber
CMD java -jar target/prod_build.jar

# docker build -t backend-development-image -f Dockerfile.backend.development .
# docker run -p 8080:8080 -it backend-development-image
# Navigate to host.docker.internal:8080/index.html OR localhost:8080/index.html
