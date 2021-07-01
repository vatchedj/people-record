(ns people-record.core
  (:require [clojure.java.io :as io]
            [people-record.person :as p]
            [people-record.print :as prnt]
            [people-record.sorting :as sorting])
  (:gen-class))

(defn- people-from-file
  [file-path]
  (with-open [rdr (io/reader file-path)]
    (->> (line-seq rdr)
         (mapv p/person)
         (reduce conj []))))

(defn- people-from-files
  [file-paths]
  (->> file-paths
       (mapcat people-from-file)))

(defn run [& file-paths]
  (let [people (people-from-files file-paths)]
    (prnt/people-with-title! "People sorted by gender (females first), then by last name, ascending:"
                             (sorting/by-gender-then-last-name-asc people))
    (println "\n------------------------\n")
    (prnt/people-with-title! "People sorted by birth date, ascending:"
                             (sorting/by-birth-date-asc people))
    (println "\n------------------------\n")
    (prnt/people-with-title! "People sorted by last name, descending:"
                             (sorting/by-last-name-desc people))))
