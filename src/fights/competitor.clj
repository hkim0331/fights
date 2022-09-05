(ns fights.competitor
  (:require
   [cheshire.core :refer :all]
   [hato.client :as hc]))

(def ^:private base "https://data.ijf.org/api/get_json?")

(defn get-ijf
  [action id]
  (let [url (str base "params[action]=" action "&"
                      "params[id_person]=" id)]
    (-> (hc/get url {:as :json})
        :body)))

(defn info
  [id]
  (get-ijf "competitor.info" id))

(defn wrl-current
  "current world lanking"
  [id]
  (get-ijf "competitor.wrl_current" id))

;; unsuitable for ishii analysis
(defn fights-statistics
  [id]
  (get-ijf "competitor.fights_statistics" id))

(defn contests
  [id]
  (-> (get-ijf "competitor.contests" id)
      :contests))

(comment)
;;; ABE 13208
(spit "data/abe.json" (fights-statistics 13208))
(spit "data/contests.json" (contests 13208))
(defn abe [{:keys [id]}]
  (println (fights-statistics id)))
;; (abe {:id 13208})

(def c (-> (slurp "data/contests.json") read-string))

(defn id-fight
  [id]
  (->> (contests id)
       (map :id_fight)))

(defn contests-statistics
  [id]
  (get-ijf "competitor.contests_statistics" id))
