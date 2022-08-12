(ns fights.competitions
  (:require
   [fights.db :as db]
   [hato.client :as hc]))

(defn get-competitions
  []
  (let [url (str "https://data.ijf.org/api/get_json?"
                 "params[action]=competition.get_list")]
    (-> (hc/get url {:as :json})
        :body)))

(defn insert-competitions
  []
  (db/create-competitions)
  (doseq [c (get-competitions)]
    (println c)
    (db/insert-competition c)))

(insert-competitions)