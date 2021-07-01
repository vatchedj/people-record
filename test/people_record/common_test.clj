(ns people-record.common-test
  (:require [clojure.java.io :as io]
            [clojure.test :refer :all]))

(defn resource-path
  [rel-path]
  (str (io/resource rel-path)))
