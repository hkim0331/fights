(ns fights.core
  (:require
   [fights.db :as db]
   #_[fights.competitor :as competitor]))

(db/create-competitions)
(db/create-contests)
(db/create-persons)
