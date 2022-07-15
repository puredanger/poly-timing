# poly-timing

Minimal library to benchmark performance of value and type based dispatch.

Also see: http://insideclojure.org/2015/04/27/poly-perf/

## Tests

* Value-based dispatch - tests dispatching by value to both the first and fifth case based on case, cond, multimethods, and core.match. Multimethods do a linear search through the cases for the best match, so you will see that timings are about the same regardless. Other examples will bail out on the first match, so have faster first match timings.
* Type-based dispatch - tests dispatching by type via multimethods and protocols. Protocols are definitely faster - while this gap has narrowed over the years, it's always been significantly faster which is why protocols are preferred for type-based dispatch. The default case timings are also included (multimethod default was really bad in older Clojure versions).
* Bimorphic type dispatch - tests dispatching when there are two common cases that are active   

## To run

```lein do clean, run```

## Example timings

Clojure 1.11.1, Temurin Java 17.0.3, 2020-era Macbook Pro (Intel)

Value-based dispatch

|          | case   | cond    | multimethod | core.match |
| -------- | ------ | ------- | ----------- | ---------- |
| 1st case | 2.6 ns | 2.3 ns  | 35.8 ns     | 2.4 ns     |
| 5th case | 2.3 ns | 16.1 ns | 35.6 ns     | 18.9 ns     |

Type-based dispatch

|              | multimethod | protocol |
| ------------ | ----------- | -------- |
| match case   | 37.0 ns     | 3.5 ns  |
| default case | 35.2 ns     | 3.9 ns  |
| bimorphic    | 68.0 ns     | 14.4 ns  |
