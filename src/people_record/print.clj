(ns people-record.print
  (:require [java-time :as t]
            [people-record.person :as p]))

(defn person-row!
  [person]
  (let [{:keys [LastName FirstName Gender
                FavoriteColor DateOfBirth]} person
        dob-string (t/format p/input-date-format DateOfBirth)]
    (println LastName
             FirstName
             Gender
             FavoriteColor
             dob-string)))

(defn people-with-title!
  [title people]
  (println title)
  (doseq [person people]
    (person-row! person)))
