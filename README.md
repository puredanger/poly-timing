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

Clojure 1.12.0, Temurin Java 21.0.3, 2023-era Macbook Pro (M3 Max)

Value-based dispatch

|          | case   | cond    | multimethod | core.match |
| -------- | ------ | ------- | ----------- | ---------- |
| 1st case | 3.7 ns | 1.3 ns  | 8.9 ns     | 1.3 ns     |
| 5th case | 4.6 ns | 17.6 ns | 9.5 ns     | 21.3 ns     |

Type-based dispatch

|              | multimethod | protocol |
| ------------ | ----------- | -------- |
| match case   | 9.0 ns     | 2.5 ns  |
| default case | 8.8 ns     | 2.9 ns  |
| bimorphic    | 17.0 ns     | 9.3 ns  |
