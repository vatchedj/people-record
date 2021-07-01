(defproject people-record "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[clojure.java-time "0.3.2"]
                 [metosin/compojure-api "2.0.0-alpha30"]
                 [org.clojure/clojure "1.10.0"]
                 [ring/ring-defaults "0.3.2"]
                 [talltale "0.5.4"]]
  :plugins [[lein-cloverage "1.2.2"]
            [lein-ring "0.12.5"]]
  :main people-record.core/run
  :ring {:handler people-record.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
