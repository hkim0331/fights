(ns fights.core
  (:require
   #_[fights.db :as db]
   [fights.competitions :as competitions]
   [fights.persons :as persons]))


(defn main
  "clojure -X fights.core/main :year '[2022 2021 2020 2019]'
   のように呼び出し、2022 2021 2020 2019 年の competitions から
   contest.json をダウンロード、data/<contest>.josn としてセーブ。
   json ファイルはキャッシュとして利用する。"
  [opts]
  (competitions/insert-competitions)
  (doseq [year (:year opts)]
    (persons/insert-persons year)))

