(defproject pilot-aid "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 [reagent "0.7.0"]
                 [re-frame "0.10.5"]
                 [secretary "1.2.3"]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-less "1.7.5"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj" "src/cljs"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "test/js"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :less {:source-paths ["less"]
         :target-path  "resources/public/css"}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.9.10"]
                   [day8.re-frame/re-frame-10x "0.3.3"]
                   [day8.re-frame/tracing "0.5.1"]]

    :plugins      [[lein-figwheel "0.5.16"]
                   [lein-doo "0.1.8"]]}
   :prod { :dependencies [[day8.re-frame/tracing-stubs "0.5.1"]]}
   }

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "pilot-aid.core/mount-root"}
     :compiler     {:main                 pilot-aid.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :infer-externs true
                    :npm-deps false
                    :foreign-libs [{:file "dist/index_bundle.js"
                                    :provides ["$" "bootstrap"]
                                    :global-exports {$ $ bootstrap bootstrap}}]
                    :preloads             [devtools.preload
                                           day8.re-frame-10x.preload]
                    :closure-defines      {"re_frame.trace.trace_enabled_QMARK_" true
                                           "day8.re_frame.tracing.trace_enabled_QMARK_" true}
                    :external-config      {:devtools/config {:features-to-install :all}}}}

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            pilot-aid.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced

                    :infer-externs true
                    :npm-deps false
                    :foreign-libs [{:file "dist/index_bundle.js"
                                    :provides ["$" "bootstrap"]
                                    :global-exports {$ $ bootstrap bootstrap}}]
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    {:id           "test"
     :source-paths ["src/cljs" "test/cljs"]
     :compiler     {:main          pilot-aid.runner
                    :output-to     "resources/public/js/compiled/test.js"
                    :output-dir    "resources/public/js/compiled/test/out"

                    :infer-externs true
                    :npm-deps false
                    :foreign-libs [{:file "dist/index_bundle.js"
                                    :provides ["$" "bootstrap"]
                                    :global-exports {$ $ bootstrap bootstrap}}]
                    :optimizations :none}}
    ]}
  )
