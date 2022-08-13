(ns fights.core
  (:require
   #_[fights.db :as db]
   [fights.competitions :as competitions]
   [fights.persons :as persons]))


(defn main
 [opts]
 (competitions/insert-competitions)
 (doseq [year (:year opts)]
   (persons/insert-persons year)))

