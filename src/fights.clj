(ns fights
  (:require
   [cheshire.core :refer :all]
   [hato.client :as hc]))

(def ^:private base "https://data.ijf.org/api/get_json")

(defn get-ijf
  [kind id]
  (let [url (str base
                 "?"
                 "params[action]=" kind
                 "&"
                 "params[id_person]=" id)]
    (-> (hc/get url {:as :json})
        :body)))

(defn info
  [id]
  (get-ijf "competitor.info" id))

(comment
  (def hiraoka (info 30))
  hiraoka)

(defn wrl-current
  [id]
  (get-ijf "competitor.wrl_current" id))

(comment
  (wrl-current 30))

(defn fights-statistics
  [id]
  (get-ijf "competitor.fights_statistics" id))

(comment
  (fights-statistics 30))

(defn contests
  [id]
  (get-ijf "competitor.contests" id))

(comment
  (contests 30))
