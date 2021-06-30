(ns people-record.person-test
  (:require [clojure.string :as str]
            [clojure.test :refer :all]
            [java-time :as t]
            [people-record.core :as core]
            [people-record.person :refer [->Person] :as p]
            [talltale.core :as gen]))

;TODO: Move this function?
(defn sample-person
  "Generates a sample Person record."
  ([]
   (let [{:keys [last-name first-name sex date-of-birth]} (gen/person)]
     (sample-person last-name first-name sex (gen/color) date-of-birth)))
  ([last-name first-name sex favorite-color date-of-birth]
   (->Person last-name
             first-name
             (-> sex name (str/capitalize))
             (str/capitalize favorite-color)
             date-of-birth)))

(deftest person-with-date-string-test
  (let [person (sample-person)]
    (doseq [date-format ["MM/dd/yyyy" "yyyy-MM-dd"]]
      (is (= (update person :DateOfBirth #(t/format date-format %))
             (p/person-with-date-string person date-format))))))

(deftest separator-test
  (is (= " | "
         (p/separator "Townsend | Melanie | Female | Orchid | 6/29/2012")))
  (is (= ", "
         (p/separator "Terrell, Gabriella, Female, Ivory, 6/29/1968")))
  (is (= " "
         (p/separator "Jefferson Aaron Male Gray 6/29/2020"))))

(deftest person-test
  (is (= (->Person "Jefferson"
                   "Aaron"
                   "Male"
                   "Gray"
                   (t/local-date core/input-date-format  "6/29/2020"))
         (p/person "Jefferson Aaron Male Gray 6/29/2020"))))
