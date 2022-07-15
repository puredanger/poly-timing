(defproject poly-timing "0.1.0-SNAPSHOT"
  :description "Timing comparisons of multimethods, protocols, case, and cond"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/core.match "1.0.0"]
                 [criterium "0.4.6"]]
  :aot :all
  :main poly-timing.core
  :jvm-opts ^:replace ["-server" "-Xmx256m"])
