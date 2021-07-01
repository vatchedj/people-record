(ns people-record.handler-test
  (:require [clojure.test :refer :all]
            [people-record.common-test :refer [resource-path]]
            [people-record.core :as core]
            [people-record.handler :refer :all :as handler]
            [people-record.person :as p]
            [ring.mock.request :as mock]))

(defn set-up-db-fixture [f]
  (reset! handler/people
          (#'core/people-from-file (resource-path "test/input_1.txt")))
  (f)
  (reset! handler/people []))

(use-fixtures :each set-up-db-fixture)

(deftest test-app
  (testing "POST /records - success"
    (let [response (app (mock/request :post "/records"
                                      {:line "Stanley, Jack, Male, Indigo, 6/30/1929"}))]
      (is (= (:status response) 201))))

  (testing "POST /records - failure"
    (let [response (app (mock/request :post "/records" {:line "INVALID LINE"}))]
      (is (= (:status response) 500))
      (is (= (:body response) "There was an error"))))

  (testing "GET /records/gender"
    (let [response (app (mock/request :get "/records/gender"))]
      (is (= (:status response) 200))
      (is (= (slurp (resource-path "test/get_gender.txt"))
             (-> response :body slurp)))))

  (testing "GET /records/birthdate"
    (let [response (app (mock/request :get "/records/birthdate"))]
      (is (= (:status response) 200))
      (is (= (slurp (resource-path "test/get_birthdate.txt"))
             (-> response :body slurp)))))

  (testing "GET /records/name"
    (let [response (app (mock/request :get "/records/name"))]
      (is (= (:status response) 200))
      (is (= (slurp (resource-path "test/get_name.txt"))
             (-> response :body slurp))))))
