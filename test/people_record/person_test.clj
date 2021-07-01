(ns people-record.person-test
  (:require [clojure.test :refer :all]
            [java-time :as t]
            [people-record.generator-test :refer [sample-person]]
            [people-record.person :refer [->Person] :as p]))

(deftest person-with-date-string-test
  (let [person (sample-person)]
    (doseq [date-format ["MM/dd/yyyy" "yyyy-MM-dd"]]
      (is (= (update person :DateOfBirth #(t/format date-format %))
             (p/person-with-date-string person date-format))))))

(deftest separator-test
  (is (= " \\| "
         (str (p/separator "Townsend | Melanie | Female | Orchid | 6/29/2012"))))
  (is (= ", "
         (str (p/separator "Terrell, Gabriella, Female, Ivory, 6/29/1968"))))
  (is (= " "
         (str (p/separator "Jefferson Aaron Male Gray 6/29/2020")))))

(deftest person-test
  (testing "Pipe ' | ' separator"
    (is (= (->Person "Perez" "Kylie" "Female" "Orchid"
                     (t/local-date p/input-date-format "6/30/1915"))
           (p/person "Perez | Kylie | Female | Orchid | 6/30/1915"))))
  (testing "Comma ', ' separator"
    (is (= (->Person "Stanley" "Jack" "Male" "Indigo"
                     (t/local-date p/input-date-format "6/30/1929"))
           (p/person "Stanley, Jack, Male, Indigo, 6/30/1929"))))
  (testing "Space ' ' separator"
    (is (= (->Person "Jefferson" "Aaron" "Male" "Gray"
                     (t/local-date p/input-date-format "6/29/2020"))
           (p/person "Jefferson Aaron Male Gray 6/29/2020")))))
