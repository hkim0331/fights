(ns fights.db-test
  (:require
    [clojure.test :refer :all]
    [fights.db :refer :all]))

(deftest test-insert-competiotion
  (testing "insert-competition..."
    (let [ret (insert-competition {:name "name"
                                   :has_results 10
                                   :comp_year 2022
                                   :id_competition -4})]
      (println "ret" ret))))