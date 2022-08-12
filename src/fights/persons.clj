(ns fights.persons
 (:require
  [fights.db :as db]
  [hato.client :as hc]))

(def ^:private base "https://data.ijf.org/api/get_json")

(defn get-contests
  [id]
  (let [url (str base "?"
                 "params[action]=contest.find&"
                 "params[id_competition]=" id)]
    (-> (hc/get url {:as :json})
        :body
        :contests)))

;;(get-contests 2289)

(defn insert-person-one
  [{:keys [id_person_blue given_name_blue family_name_blue country_short_blue
           id_person_white given_name_white family_name_white country_short_white]}]
  (println [id_person_blue given_name_blue family_name_blue country_short_blue
            id_person_white given_name_white family_name_white country_short_white])
  (db/insert-person  {:id_person   id_person_blue
                      :family_name family_name_blue
                      :given_name  given_name_blue
                      :country     country_short_blue})
  (db/insert-person  {:id_person   id_person_white
                      :family_name family_name_white
                      :given_name  given_name_white
                      :country     country_short_white}))

(defn insert-persons-id
  "id_competion から
   id_person family_name given_name をとってくる。"
  [id_competition]
  ;;(insert-person-one (first (get-contests id_competition))))
  (doseq [competition (get-contests id_competition)]
    (insert-person-one competition)))

;;(insert-persons-id 2389)

(defn insert-persons
  [year]
  (doseq [{:keys [id_competition]} (db/competition-id-year year)]
    (insert-persons-id id_competition)))
