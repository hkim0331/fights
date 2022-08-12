(ns fights.competitor-test
  (:require
   [clojure.test :refer :all]
   [fights.competitor :refer :all]))

(deftest test-info
  (testing "info"
    (let [ret (info 30)]
      (is (= "HIRAOKA" (:family_name ret)))
      (is (= "Hiroaki" (:given_name ret))))))

(deftest test-contests
  (testing "contests"
    (let [ret (contests 30)]
      (is (= 55 (count ret))))))

;; string?
(deftest test-fights-statistics
 (testing "fights-statistics"
  (let [ret (fights-statistics 30)]
   (is (= "53" (:num_contests ret)))
   (is (= "41" (:num_win ret)))
   (is (= "12" (:num_lost ret))))))

(deftest test-wrl-current
 (testing "wrl-current"
  (let [ret (wrl-current 30)]
    (is (= [] ret)))))

(deftest test-id-fight
 (testing "id-fight"
  (let [ret (id-fight 30)]
   (is (= 55 (count ret))))))