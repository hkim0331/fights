prep:
	clojure -X fights.db/create-persons
	clojure -X fights.db/create-contests
	clojure -X fights.db/create-competitions

.PHONY: test
test:
	clojure -A:test

names:
	clojure -X fights.core/main :year '[2022 2021 2020 2019]'

clean:
	${RM} db/fights.sqlite
