# Local Development

This doc covers getting the app running locally and exercising the test
setup. Everything below was verified working directly (build, boot, curl) as of
2026-07-26.

## Prerequisites

The repo pins its toolchain via [mise](https://mise.jdx.dev) in `mise.toml`
(java, clojure, node, pnpm). From the repo root:

```
mise install
```

This scopes `java`, `clojure`/`clj`, `node`, and `pnpm` to this directory
without touching any global tool versions.

Then install JS deps:

```
pnpm install
```

## Running the app

Two processes, in two terminals.

**Terminal 1 — ClojureScript client (compiles + hot-reloads on save):**

```
pnpm run client/main
```

**Terminal 2 — Clojure backend (REPL):**

```
clojure -A:dev
```

Once at the REPL:

```clojure
(require 'development)
(in-ns 'development)
(start)
```

The app is then reachable at http://localhost:3000 — the backend serves the
HTML shell and the compiled JS from `resources/public/js/main`. Live-reload
for CLJS changes goes through shadow-cljs's own websocket; a browser refresh
is not required for most CLJS edits.

Stopping/restarting the backend from the REPL: `(stop)` / `(restart)`.

### One-terminal alternative

If you don't need REPL access to the backend (no `(stop)`/`(restart)`, just
"run the site"), `pnpm run dev` starts both processes with one command,
interleaved in one terminal:

```
pnpm run dev
```

This still runs two OS processes under the hood — the client watcher and the
backend are different tools with different jobs.

Note: the older `pnpm start` script (`run-p client/server server`) launches
the client watcher plus a bare `clojure -A:dev` REPL in parallel — the server
still needs to be started manually via `(require 'development) (in-ns
'development) (start)` in that REPL, since `pnpm start` does not auto-start
it. `pnpm run dev` is the same idea but actually auto-starts the server.

### Ports

- `3000` — backend in dev (`src/main/config/dev.edn` + `defaults.edn`)
- `8080` — backend in prod (`src/main/config/prod.edn`, also what Docker/`make run` expose)
- `8022` — shadow-cljs browser test runner (`:test` build)
- `8023` — workspaces (component dev cards)
- `9000` — shadow-cljs nREPL

### Sanity check without a browser

```
curl -i http://localhost:3000/
```

A 200 with the HTML shell is expected. Hitting `/api` directly with curl
correctly 403s ("Invalid anti-forgery token") — the CSRF token is embedded
in the served HTML for the real client to use, so that response is expected,
not a bug.

## Workspaces (component dev cards)

```
pnpm run client/workspaces
```

Then visit http://localhost:8023/workspaces.

## Tests

There are no test files checked in yet.

**Clojure tests (kaocha)** — note both aliases are required together, since
`fulcro-spec` (the configured reporter) lives under `:dev`:

```
clojure -M:dev:clj-tests
```

**ClojureScript tests, interactive (browser, via shadow-cljs):**

```
pnpm run client/test
```

Opens a runner at http://localhost:8022.

**ClojureScript tests, headless (Karma, what `:ci-tests` is for):**

```
pnpm exec shadow-cljs compile ci-tests
pnpm exec karma start karma.conf.js --single-run
```

Test namespaces are matched by `-test$` (see `tests.edn` and the `:ns-regexp`
in `shadow-cljs.edn`), and Clojure tests live under `src/test`.

## Building for production

```
make install       # pnpm install
make release        # shadow-cljs release main (advanced-optimized JS)
make build_lin       # clj -T:build uber (or build_win on Windows)
make run             # java -jar target/prod_build.jar
```

Or all at once: `make all_lin` / `make all_win`.
