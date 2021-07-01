(ns people-record.sorting)

(defn by-gender-then-last-name-asc
  "Returns a list of people sorted by
  gender (females first), then by
  last name, ascending."
  [people]
  (sort-by (juxt :Gender :LastName)
           people))

(defn by-birth-date-asc
  "Returns a list of people sorted by
  birth date, ascending."
  [people]
  (sort-by :DateOfBirth people))

(defn by-last-name-desc
  "Returns a list of people sorted by
  last name, descending."
  [people]
  (sort-by :LastName #(compare %2 %1)
           people))
