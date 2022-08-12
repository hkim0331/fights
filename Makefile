prep:
	clojure -X fights.db/create-persons
	clojure -X fights.db/create-contests
	clojure -X fights.db/create-competitions


.PHONY: test
test:
	clojure -A:test

clean:
	${RM} db/fights.sqlite
	