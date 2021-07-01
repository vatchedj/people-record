(ns people-record.sorting-test
  (:require [clojure.test :refer :all]
            [people-record.person :as p]
            [people-record.sorting :as sorting]))

(deftest by-gender-then-last-name-asc-test
  (is (= [(p/person "Perez | Kylie | Female | Orchid | 6/30/1915")
          (p/person "Barron | Gavin | Male | Green | 6/30/2004")
          (p/person "Mills | Grayson | Male | Plum | 6/30/2019")]
         (sorting/by-gender-then-last-name-asc
           [(p/person "Mills | Grayson | Male | Plum | 6/30/2019")
            (p/person "Perez | Kylie | Female | Orchid | 6/30/1915")
            (p/person "Barron | Gavin | Male | Green | 6/30/2004")]))))

(deftest by-birth-date-asc-test
  (is (= [(p/person "Perez | Kylie | Female | Orchid | 6/30/1915")
          (p/person "Barron | Gavin | Male | Green | 6/30/2004")
          (p/person "Mills | Grayson | Male | Plum | 6/30/2019")]
         (sorting/by-birth-date-asc
           [(p/person "Mills | Grayson | Male | Plum | 6/30/2019")
            (p/person "Perez | Kylie | Female | Orchid | 6/30/1915")
            (p/person "Barron | Gavin | Male | Green | 6/30/2004")]))))

(deftest by-last-name-desc-test
  (is (= [(p/person "Perez | Kylie | Female | Orchid | 6/30/1915")
          (p/person "Mills | Grayson | Male | Plum | 6/30/2019")
          (p/person "Barron | Gavin | Male | Green | 6/30/2004")]
         (sorting/by-last-name-desc
           [(p/person "Mills | Grayson | Male | Plum | 6/30/2019")
            (p/person "Perez | Kylie | Female | Orchid | 6/30/1915")
            (p/person "Barron | Gavin | Male | Green | 6/30/2004")]))))
