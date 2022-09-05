all: realclean persons

prep:
	clojure -X fights.db/create-persons
	clojure -X fights.db/create-contests
	clojure -X fights.db/create-competitions

persons: prep
	clojure -X fights.core/main :year '[2019 2020 2021 2022]'

clean:
	${RM} db/fights.sqlite

realclean: clean
	${RM} -f data/*.json

.PHONY: test
test:
	clojure -A:test

abe:
	clojure -X fights.competitor/abe :id 13208
