(ns fights.db
  (:require
   [clojure.java.io :as io]
   [next.jdbc :as jdbc]
   [next.jdbc.sql :as sql]
   [next.jdbc.result-set :as rs]))

;;;;;;;;;;;;
;; SQLite3
;;;;;;;;;;;;
(def db {:dbtype "sqlite"
         :dbname "db/fights.sqlite"})

(def ds (jdbc/with-options
          (jdbc/get-datasource db)
          {:builder-fn rs/as-unqualified-lower-maps}))

;; persons

(defn drop-persons []
  (jdbc/execute! ds ["drop table if exists persons"]))

(defn create-persons []
  (let [sql "create table persons (
             id integer  primary key autoincrement,
             id_person   int not null,
             family_name text not null,
             given_name  text not null)"]
    (drop-persons)
    (sql/query ds [sql])))

(create-persons)
;; contests

(defn drop-contests []
  (sql/query ds ["drop table if exists contests"]))

(defn create-contests []
  (let [sql "create table contests (
             id integer primary key autoincrement,
             id_fight   int not null,
             id_person_blue   int not null,
             id_person_white  int not null,
             id_winner        int not null)"]
    (drop-contests)
    (sql/query ds [sql])))

;;;;;;;;;;;;;;;;;;
;; competitions

(defn drop-competitions []
  (sql/query ds ["drop table if exists competitions"]))

(defn create-competitions []
  (let [sql "create table competitions (
             id integer primary key autoincrement,
             id_competition  int unique,
             comp_year       int not null,
             name            text not null,
             has_results     int default 0)"]
    (drop-competitions)
    (sql/query ds [sql])))

(defn insert-competition
  [{:keys [name has_results comp_year id_competition]}]
  (try
    (let [ret (sql/insert!
               ds
               :competitions
               {:name name
                :has_results has_results
                :comp_year comp_year
                :id_competition id_competition})]
      (println "ret=" ret)
      ret)
    (catch Exception e (println (.getMessage e)))))
