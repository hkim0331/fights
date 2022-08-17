(ns fights.persons
  (:require
   ;;[cheshire.core :refer [parse-string]]
   [clojure.java.io :as io]
   [fights.db :as db]
   [hato.client :as hc]
   #_[next.jdbc.sql :as sql]))

(def ^:private base "https://data.ijf.org/api/get_json")

(defn- cache
  [id]
  (str "data/" id ".json"))

(defn get-contests
  "Downloads contests data from ijf,
   caches the downloads as `data/<id>.json`
   The argument id is IJF's id_competition.
   Returns json array, [{},{},...]"
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
 (get-contests 2281))

;; remove duplicates using set
(def ^:private persons (atom #{}))

(defn- add-persons
  "add person blue and white into set `persons`"
  [{:keys [id_person_blue given_name_blue family_name_blue country_short_blue
           id_person_white given_name_white family_name_white country_short_white]}]
  (swap! persons conj [id_person_blue family_name_blue given_name_blue country_short_blue])
  (swap! persons conj [id_person_white family_name_white given_name_white country_short_white]))

;; FIXME: function name
(defn insert-from-competition
  "data.ijf.org から id_competion を引いて
   id_person family_name given_name をとってくる。
   セットを利用して重複を解消する。
   キャッシュを使っても高速化しない。遅いのは insert"
  [id_competition]
  (reset! persons #{})
  (doseq [contest (get-contests id_competition)]
    (add-persons contest))
  (doseq [p @persons]
    ;;(println p)
    (db/insert-person p)))

(comment
 (insert-from-competition 2381))


(defn insert-persons
  "Update persons table with year's competitions."
  [year]
  (doseq [{:keys [id_competition]} (db/competition-id-year year)]
    (insert-from-competition id_competition)))

(comment
  (db/competition-id-year 2021)
  (insert-persons 2022))
