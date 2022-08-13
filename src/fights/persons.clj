(ns fights.persons
  (:require
   ;;[cheshire.core :refer [parse-string]]
   [clojure.java.io :as io]
   [fights.db :as db]
   [hato.client :as hc]))

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

;;(get-contests 2289)

;; (defn insert-person-one
;;   [{:keys [id_person_blue given_name_blue family_name_blue country_short_blue
;;            id_person_white given_name_white family_name_white country_short_white]}]
;;   (println [id_person_blue given_name_blue family_name_blue country_short_blue
;;             id_person_white given_name_white family_name_white country_short_white])
;;   (db/insert-person  {:id_person   id_person_blue
;;                       :family_name family_name_blue
;;                       :given_name  given_name_blue
;;                       :country     country_short_blue})
;;   (db/insert-person  {:id_person   id_person_white
;;                       :family_name family_name_white
;;                       :given_name  given_name_white
;;                       :country     country_short_white}))


(def persons (atom #{}))

(defn insert-person-one
  [{:keys [id_person_blue given_name_blue family_name_blue country_short_blue
           id_person_white given_name_white family_name_white country_short_white]}]
  (swap! persons conj [id_person_blue given_name_blue family_name_blue country_short_blue])
  (swap! persons conj [id_person_white given_name_white family_name_white country_short_white]))

(defn insert-persons-id
  "data.ijf.org から id_competion を引いて
   id_person family_name given_name をとってくる。
   キャッシュを使っても高速化しない。遅いのは insert"
  [id_competition]
  ;;(reset! persons #{})
  ;;(insert-person-one (first (get-contests id_competition))))
  (doseq [competition (get-contests id_competition)]
    (insert-person-one competition)))

;;(insert-persons-id 2389)

(defn insert-persons-multi!
 []
 (sq/insert-multi! ds :))
 
(defn insert-persons
  [year]
  (reset! persons #{}) ;; not (reset! persons! (atom #{}))
  (doseq [{:keys [id_competition]} (db/competition-id-year year)]
    (insert-persons-id id_competition))
  (insert-psersons-multi!))
;;(insert-persons 2020)