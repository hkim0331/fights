(ns fights.persons
  (:require
   ;;[cheshire.core :refer [parse-string]]
   [clojure.java.io :as io]
   [fights.db :as db]
   [hato.client :as hc]
   [next.jdbc.sql :as sql]))

(def ^:private base "https://data.ijf.org/api/get_json")

(defn- cache
  [id]
  (str "data/" id ".json"))

(defn get-contests
  "downloads contests from ijf,
   cache the downloads as `data/<id>.json`"
  [id]
  (let [json (cache id)]
    (if (.exists (-> json io/file))
      (-> (slurp json) read-string)
      (let [url (str base "?"
                     "params[action]=contest.find&"
                     "params[id_competition]=" id)
            ret (-> (hc/get url {:as :json})
                    :body
                    :contests)]
        (spit json ret)
        ret))))

(comment 
 (get-contests 2289))

(def ^:private persons (atom #{}))

(defn- insert-person-one
  [{:keys [id_person_blue given_name_blue family_name_blue country_short_blue
           id_person_white given_name_white family_name_white country_short_white]}]
  (swap! persons conj [id_person_blue given_name_blue family_name_blue country_short_blue])
  (swap! persons conj [id_person_white given_name_white family_name_white country_short_white]))

;; FIXME: function name
(defn insert-from-competition
  "data.ijf.org から id_competion を引いて
   id_person family_name given_name をとってくる。
   キャッシュを使っても高速化しない。遅いのは insert"
  [id_competition]
  (reset! persons #{})
  ;;(insert-person-one (first (get-contests id_competition))))
  (doseq [competition (get-contests id_competition)]
    (insert-person-one competition))
  (doseq [[id family given country] @persons]
    (db/insert-person id family given country)))

(comment
  (insert-from-competition 2381))


(defn insert-persons
  "yyyy 年の記録で persons テーブルを更新する"
  [year]
  (doseq [{:keys [id_competition]} (db/competition-id-year year)]
    (insert-from-competition id_competition)))

(comment
  (insert-persons 2022))
