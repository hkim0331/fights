(ns fights.core
  (:require
   [fights.db :as db]
   [fights.competitions :as competitions]
   [fights.persons :as persons]
   #_[fights.competitor :as competitor]))


(db/create-contests)


;; id_competition and name
;; name reset-competitions?
(db/create-competitions)
(competitions/insert-competitions)
;;"Elapsed time: 2114.854625 msecs"

;; persons
;;(persons/insert-persons-id 2281)
(db/create-persons)
;; (time (persons/insert-persons 2021))
;; "Elapsed time: 61317.613375 msecs"
