(ns fights.core
  (:require
   [fights.db :as db]
   [fights.competitions :as competitions]
   [fights.persons :as persons]))

(db/create-contests nil)

;; id_competition and name
;; name reset-competitions?
(db/create-competitions nil)
(competitions/insert-competitions)
;;"Elapsed time: 2114.854625 msecs"

;; persons

(db/create-persons nil)
(persons/insert-from-competition 2281)

;;(persons/insert-persons 2022)
;;"Elapsed time: 65392.6335 msecs"
;; (time (persons/insert-persons 2022))
;; "Elapsed time: 60548.435875 msecs"
(time (persons/insert-persons 2022))
;; file sort, uniq
;;"Elapsed time: 1068.413292 msecs"
;; atom set
;;"Elapsed time: 671.008708 msecs"
(doseq [year [2022 2021 2020 2019]]
  (persons/insert-persons year))

