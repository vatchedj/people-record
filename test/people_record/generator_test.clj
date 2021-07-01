(ns people-record.generator-test
  (:require [clojure.string :as str]
            [people-record.person :refer [->Person] :as p]
            [talltale.core :as gen]))

(def max-random-record-count 1000)

(defn- generate-color
  "Generates a random color."
  []
  (-> (gen/color)
      (str/capitalize)
      (str/replace #" " "-")))

(defn sample-person
  "Generates a sample Person record."
  []
  (let [{:keys [last-name first-name sex date-of-birth]} (gen/person)]
    (->Person last-name
              first-name
              (-> sex name (str/capitalize))
              (generate-color)
              date-of-birth)))

(defn sample-people
  ([]
   (sample-people (rand max-random-record-count)))
  ([num]
   (repeatedly num sample-person)))

(defn sample-record-input-string
  "Returns a string that represents a single record
  of an input file."
  ([separator]
   (sample-record-input-string separator (sample-person)))
  ([separator person]
   (->> (p/person-with-date-string person p/input-date-format)
        (vals)
        (str/join separator))))

(defn create-input-file
  "Creates an input file with randomly generated data."
  ([file-path separator]
   (create-input-file file-path
                      separator
                      (rand-int max-random-record-count)))
  ([file-path separator person-count]
   (doseq [line (repeatedly person-count #(sample-record-input-string separator))]
     (spit file-path (str line "\n") :append true))))
