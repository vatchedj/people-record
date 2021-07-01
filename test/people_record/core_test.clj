(ns people-record.core-test
  (:require [clojure.string :as str]
            [clojure.test :refer :all]
            [people-record.common-test :refer [resource-path]]
            [people-record.core :refer :all :as core]
            [people-record.person :refer [->Person] :as p]))

(def file-1-people
  [(->Person "Perez" "Kylie" "Female" "Orchid" (p/date-from-string "6/30/1915"))
   (->Person "Mills" "Grayson" "Male" "Plum" (p/date-from-string "6/30/2019"))
   (->Person "Barron" "Gavin" "Male" "Green" (p/date-from-string "6/30/2004"))])

(def file-2-people
  [(->Person "Stanley" "Jack" "Male" "Indigo" (p/date-from-string "6/30/1929"))
   (->Person "Hogan" "Logan" "Male" "Chocolate" (p/date-from-string "6/30/1987"))
   (->Person "Burt" "Levi" "Male" "Desert" (p/date-from-string "6/30/2008"))])

(def file-3-people
  [(->Person "Ruiz" "Nevaeh" "Female" "Spring-green" (p/date-from-string "6/30/1964"))
   (->Person "Blackwell" "Ellie" "Female" "Cobalt" (p/date-from-string "6/30/1984"))
   (->Person "Mosley" "Piper" "Female" "Mauve" (p/date-from-string "6/30/2006"))])

(def input-file-paths (mapv resource-path
                            ["test/input_1.txt"
                             "test/input_2.txt"
                             "test/input_3.txt"]))

(deftest people-from-files-test
  (is (= (concat file-1-people
                 file-2-people
                 file-3-people)
         (#'core/people-from-files input-file-paths))))

(deftest run-test
  (let [expected-output (slurp (resource-path "test/run_output.txt"))
        actual-output (atom "")]
    (with-redefs [println (fn [& args]
                            (swap! actual-output str
                                   (str (str/join " " args) "\n")))]
      (apply run input-file-paths))
    (swap! actual-output str/trim-newline)
    (is (= expected-output
           @actual-output))))
