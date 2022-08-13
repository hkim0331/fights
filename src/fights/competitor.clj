(ns fights.competitor
  (:require
   [cheshire.core :refer :all]
   [hato.client :as hc]))

(def ^:private base "https://data.ijf.org/api/get_json")

(defn get-ijf
  [kind id]
  (let [url (str base "?"
                 "params[action]=" kind "&"
                 "params[id_person]=" id)]
    (-> (hc/get url {:as :json})
        :body)))

(defn info
  [id]
  (get-ijf "competitor.info" id))

(defn wrl-current
  [id]
  (get-ijf "competitor.wrl_current" id))

(defn fights-statistics
  [id]
  (get-ijf "competitor.fights_statistics" id))

(spit "data/abe-2.json" (fights-statistics 13208))

;;13208
(defn abe [{:keys [id]}]
  (println (fights-statistics id)))

(abe {:id 13208})

(defn contests
  [id]
  (-> (get-ijf "competitor.contests" id)
      :contests))

(spit "data/contests.json" (contests 13208))

(defn id-fight
  [id]
  (->> (contests id)
       (map :id_fight)))

(defn contests-statistics
  [id]
  (get-ijf "competitor.contests_statistics" id))