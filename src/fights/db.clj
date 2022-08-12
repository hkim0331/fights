(ns fights.db
  (:require
   [clojure.java.io :as io]
   [next.jdbc :as jdbc]
   [next.jdbc.sql :as sql]
   [next.jdbc.result-set :as rs]))

(def db {:dbtype "sqlite"
         :dbname "db/fights.sqlite"})

(def ds (jdbc/with-options
          (jdbc/get-datasource db)
          {:builder-fn rs/as-unqualified-lower-maps
           :return-keys true}))

(defn drop-table []
  (jdbc/execute! ds ["drop table if exists fights"]))

;;(drop-table)

(defn create-table []
  (let [sql "create table fights (
            id integer primary key autoincrement,
            id_person  int not null,
            name text not null)"]
    (drop-table)
    (jdbc/execute! ds [sql])))

;;(create-table)

;; csv からじゃなく、json からの方が効率はいいだろう。
(defn seed
  [csv]
  (with-open [r (io/reader csv)]
    (binding [*in* r]
      (loop [line (read-line)]
        (println line)
        (recur (read-line))))))

;;(seed "data/year-2022.csv")