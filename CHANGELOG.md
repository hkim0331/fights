# Change Log

## Unreleased
- web の GUI で包めないか。
- map で db を置き換える。
- ファイルに書き出し、sort, uniq でフィルタしては？
- ログがうるさい。
```
[23931 Alexandra MILOVZOROVA RUS]
[SQLITE_CONSTRAINT_UNIQUE] A UNIQUE constraint failed (UNIQUE constraint failed: persons.id_person)
```
- competitions の一覧表示
- comtetition 追加
- person 戦績


## 0.4.0-SNAPSHOT
- make realclean
- make persons

## 0.3.0 - 2022-08-13
- 例外を出さない。upsert
- 遅いのはネットワークではなく、insert. 複数の insert をまとめて実行するなどの改良が望まれる。
- make names で 2022, 2021, 2020 の対戦表から persons テーブルができる。

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

