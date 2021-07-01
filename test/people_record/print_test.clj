(ns people-record.print-test
  (:require [clojure.test :refer :all]
            [java-time :as t]
            [people-record.common-test :refer [resource-path]]
            [people-record.generator-test :as gen]
            [people-record.person :as p]
            [people-record.print :as prnt]))

(deftest person-row-test
  (let [person (gen/sample-person)
        {:keys [LastName FirstName Gender FavoriteColor
                DateOfBirth]} person
        date-of-birth-string (t/format p/input-date-format DateOfBirth)]
    (with-redefs [println (fn [& [last-name first-name gender favorite-color dob-string]]
                            (is (= LastName last-name))
                            (is (= FirstName first-name))
                            (is (= Gender gender))
                            (is (= FavoriteColor favorite-color))
                            (is (= date-of-birth-string dob-string)))]
      (prnt/person-row! person))))

(deftest people-with-title!-test
  (let [title "Title"
        people (gen/sample-people 10)
        people-called (atom [])]
    (with-redefs [println (fn [title-in]
                            (is (= title title-in)))
                  prnt/person-row! (fn [person]
                                     (swap! people-called conj person))]
      (prnt/people-with-title! title people))
    (is (= people
           @people-called))))
