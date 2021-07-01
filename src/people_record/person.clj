(ns people-record.person
  (:require [clojure.string :as str]
            [java-time :as t])
  (:import (java.time LocalDate)))

(def input-date-format "M/d/yyyy")
(def output-date-format "M/d/yyyy")

(defn date-from-string
  [date-string]
  (t/local-date input-date-format date-string))

(defrecord Person [LastName
                   FirstName
                   Gender
                   FavoriteColor
                   ^LocalDate DateOfBirth])

(defn person-with-date-string
  "Returns a person record with the DateOfBirth
  field formatted to a string using the provided
  date-format."
  [person date-format]
  (update person :DateOfBirth
          #(t/format date-format %)))

(defn person-map
  "Returns a simple representation of a person
  (map of strings) for easy transmission over HTTP."
  [person]
  (as-> person $
        (person-with-date-string $ output-date-format)
        (into {} $)))

(defn separator
  "Returns the separator used in the provided input line (string)."
  [record-string]
  (cond (str/includes? record-string " | ") #" \| "
        (str/includes? record-string ", ") #", "
        :else #" "))

(defn update-last
  "Updates the last item in the collection
  using the provided function."
  [coll f]
  (let [last-index (dec (count coll))]
    (update coll last-index f)))

(defn person
  "Returns a Person record for the provided input line (string)."
  [record-string]
  (let [pattern (separator record-string)
        values (-> (str/split record-string pattern)
                   (update-last date-from-string))]
    (apply ->Person values)))
