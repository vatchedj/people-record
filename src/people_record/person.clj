(ns people-record.person
  (:require [clojure.string :as str]
            [people-record.core :as core]
            [java-time :as t]))

(defrecord Person [LastName
                   FirstName
                   Gender
                   FavoriteColor
                   DateOfBirth])

(defn person-with-date-string
  "Returns a person record with the DateOfBirth
  field formatted to a string using the provided
  date-format."
  [person date-format]
  (update person :DateOfBirth
          #(t/format date-format %)))

(defn separator
  "Returns the separator used in the provided input line (string)."
  [record-string]
  (cond (str/includes? record-string " | ") " | "
        (str/includes? record-string ", ") ", "
        :else " "))

(defn update-last
  "Updates the last item in the collection
  using the provided function."
  [coll f]
  (let [last-index (dec (count coll))]
    (update coll last-index f)))

(defn person
  "Returns a Person record for the provided input line (string)."
  [record-string]
  (let [pattern (-> (separator record-string)
                    (re-pattern))
        values (-> (str/split record-string pattern)
                   (update-last #(t/local-date core/input-date-format %)))]
    (apply ->Person values)))
