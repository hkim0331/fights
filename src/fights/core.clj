(ns fights.core
  (:require
   [fights.db :as db]
   [fights.competitions :as competitions]
   [fights.persons :as persons]
   #_[fights.competitor :as competitor]))


(db/create-contests nil)

;; id_competition and name
;; name reset-competitions?
(db/create-competitions nil)
(competitions/insert-competitions)
;;"Elapsed time: 2114.854625 msecs"

;; persons
;;(persons/insert-persons-id 2281)
(db/create-persons nil)
