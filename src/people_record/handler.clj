(ns people-record.handler
  (:require [compojure.api.sweet :refer :all]
            [people-record.person :as p]
            [people-record.sorting :as sorting]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.util.http-response :refer :all]))

(def people (atom []))

(def app
  (api
    (POST "/records" [line]
      (try
        (swap! people conj (p/person line))
        {:status 201}
        (catch Exception _
          {:status 500 :body "There was an error"})))

    (GET "/records/gender" []
      {:status 200
       :header {:content-type "application/json"}
       :body   (->> (sorting/by-gender-then-last-name-asc @people)
                    (mapv p/person-map))})

    (GET "/records/birthdate" []
      {:status 200
       :header {:content-type "application/json"}
       :body   (->> (sorting/by-birth-date-asc @people)
                    (mapv p/person-map))})

    (GET "/records/name" []
      {:status 200
       :header {:content-type "application/json"}
       :body   (->> (sorting/by-last-name-desc @people)
                    (mapv p/person-map))})))
