# poly-timing

Minimal library to benchmark performance of value and type based dispatch.

## Tests

* Value-based dispatch - tests dispatching by value to both the first and fifth case based on case, cond, multimethods, and core.match. Multimethods do a linear search through the cases for the best match, so you will see that timings are about the same regardless. Other examples will bail out on the first match, so have faster first match timings.
* Type-based dispatch - tests dispatching by type via multimethods and protocols. Protocols are definitely faster - while this gap has narrowed over the years, it's always been significantly faster which is why protocols are preferred for type-based dispatch. The default case timings are also included (multimethod default was really bad in older Clojure versions).
* Bimorphic type dispatch - tests dispatching when there are two common cases that are active   

## To run

```lein do clean, run```

## Example timings

Clojure 1.8, Java 1.8, 2013-era Macbook Pro

Value-based dispatch

* case 1st : 5.40 ns
* case 5th : 18.0 ns
* cond 1st : 4.89 ns
* cond 5th : 35.6 ns
* multi 1st : 40.2 ns
* multi 5th : 40.8 ns
* match 1st : 4.83 ns
* match 5th : 20.6 ns

Type-based dispatch

* multi : 41.0 ns
* multi default : 43.9 ns
* proto : 6.27 ns
* proto default : 7.80 ns

Bimorphic distribution

* multi bi : 78.3 ns
* proto bi : 22.7 ns
