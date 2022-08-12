(ns fights.core
  (:require
   [fights.db :as db]
   [fights.competitions :as competitions]
   #_[fights.competitor :as competitor]))

(db/create-competitions)
(db/create-contests)
(db/create-persons)

;; id_competition and name
;; name reset-competitions?
(competitions/insert-competitions)

;; persons

