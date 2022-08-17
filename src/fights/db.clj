(ns fights.db
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]
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
          {:builder-fn rs/as-unqualified-lower-maps
           :return-keys true}))

(defn begin-transaction
  [t]
  (sql/query ds ["begin transaction ?" t]))

(defn end-transaction
  [t]
  (sql/query ds ["commit transaction ?" t]))

;;;;;;;;;;;;
;; persons
;;;;;;;;;;;;

(defn drop-persons []
  (jdbc/execute! ds ["drop table if exists persons"]))

(defn create-persons
  [opts]
  (let [sql "create table persons (
             id integer  primary key autoincrement,
             id_person   int not null unique,
             family_name VARCHAR(30) not null,
             given_name  VARCHAR(30) not null,
             country     VARCHAR(10) not null)"]
    (drop-persons)
    (sql/query ds [sql])))

(defn insert-person
  [[id_person family_name given_name country]]
  (try
    (sql/insert! ds :persons {:id_person   id_person
                              :family_name family_name
                              :given_name  given_name
                              :country     country})
    (catch Exception e
      (let [msg (.getMessage e)]
       (if (str/starts-with? msg "[SQLITE_CONSTRAINT_UNIQUE")
        (print ".")
        (println msg id_person family_name given_name country))))))


;;;;;;;;;;;;;
;; contests
;;;;;;;;;;;;;

(defn drop-contests []
  (sql/query ds ["drop table if exists contests"]))

(defn create-contests [opts]
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

(defn create-competitions [opts]
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
      ret)
    (catch Exception e (println (.getMessage e)))))

(defn competition-id-year
  [year]
  (sql/query
   ds
   ["select id_competition from competitions where comp_year=?" year]))

;;(competition-id-year 2022)
