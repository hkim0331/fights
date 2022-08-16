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

;; FIXME: 追加もあるだろう。
(defn insert-competitions
  "cometitions テーブルを初期化し、ijf の competions リストから
   id_competion, comp_year, name, has_results を読み込む。"
  []
  (db/create-competitions nil)
  (doseq [c (get-competitions)]
    (db/insert-competition c)))

