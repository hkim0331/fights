(ns fights.core
  (:require
   [fights.db :as db]
   [fights.competitions :as competitions]
   [fights.persons :as persons]
   #_[fights.competitor :as competitor]))

(db/create-competitions)
(db/create-contests)
(db/create-persons)

;; id_competition and name
;; name reset-competitions?
(competitions/insert-competitions)

;; persons
;;(persons/insert-persons-id 2281)
(persons/insert-persons 2021)
