(ns people-record.core-test
  (:require [clojure.string :as str]
            [clojure.test :refer :all]
            [people-record.core :refer :all]
            [people-record.person :refer [->Person] :as p]
            [people-record.person-test :as ptest]))

(def max-random-record-count 1000)

(defn sample-record-input-string
  "Returns a string that represents a single record
  of an input file."
  ([separator]
   (sample-record-input-string separator (ptest/sample-person)))
  ([separator person]
   (->> (p/person-with-date-string person input-date-format)
        (vals)
        (str/join separator))))

(defn create-input-file
  ([file-path separator]
   (create-input-file file-path
                      separator
                      (rand-int max-random-record-count)))
  ([file-path separator person-count]
   (doseq [line (repeatedly person-count #(sample-record-input-string separator))]
     (spit file-path (str line "\n") :append true))))
