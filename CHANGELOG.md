# Change Log

## Unreleased
- map で db を置き換える。
- ファイルに書き出し、sort, uniq でフィルタしては？


## 0.3.0-SNAPSHOT
- 例外を出さない。upsert
- 遅いのはネットワークではなく、insert. 複数の insert をまとめて実行するなどの改良が望まれる。

## 0.2.0 - 2022-08-13
- src/fights/db.clj create tables. `persons`, `contests` and `competitions`.
- test/fights/db_test.clj
- src/fights/competitor.clj
- src/fights/core.clj
- (insert-person-id id_competition)
- (insert-pserons year)
- transaction でスピードアップできないか？
- persons: country_short_blue, country_short_white を入れた。


## 0.1.0 - 2022-08-12
- project (re)started.

