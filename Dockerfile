FROM clojure:latest

WORKDIR /app

# Using Debian, as root
RUN curl -fsSL https://deb.nodesource.com/setup_22.x | bash - \
    && apt-get update && apt-get install -y nodejs

# Install pnpm (matches packageManager pin in package.json)
RUN corepack enable && corepack prepare pnpm@11.17.0 --activate

COPY package.json pnpm-lock.yaml ./

RUN pnpm install --frozen-lockfile

CMD pnpm exec shadow-cljs release main

COPY . .

EXPOSE 8080

CMD clj -T:build uber
CMD java -jar target/prod_build.jar

# docker build -t backend-development-image -f Dockerfile.backend.development .
# docker run -p 8080:8080 -it backend-development-image
# Navigate to host.docker.internal:8080/index.html OR localhost:8080/index.html
