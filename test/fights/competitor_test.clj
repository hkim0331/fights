(ns fights.competitor-test
 (:require
  [clojure.test :refer :all]
  [fights.competitor :refer :all]))

(deftest test-info
 (testing "info"
  (let [ret (info 30)]
   (is (= "HIRAOKA" (:family_name ret))))))

(deftest test-contests
 (testing "contests"
  (let [ret (contests 30)]
   (is (= 55 (count ret))))))