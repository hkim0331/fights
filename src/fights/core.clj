(ns fights.core
  (:require
   [fights.db :as db]
   [fights.competitions :as competitions]
   [fights.persons :as persons]))


(defn main
 [opts]
;; (db/create-contests nil)
;; (db/create-competitions nil)
;; (db/create-persons nil)
 (competitions/insert-competitions)
 (doseq [year (:year opts)]
   (persons/insert-persons year)))

