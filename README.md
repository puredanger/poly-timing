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

Clojure 1.10.1, OpenJDK Java 12, 2018-era Macbook Pro

Value-based dispatch

|          | case   | cond    | multimethod | core.match |
| 1st case | 2.4 ns | 2.6 ns  | 35.3 ns     | 2.6 ns     |
| 5th case | 2.6 ns | 15.5 ns | 36.1 ns     | 6.8 ns     |

Type-based dispatch

|              | multimethod | protocol |
| match case   | 35.3 ns     | 3.65 ns  |
| default case | 35.4 ns     | 4.49 ns  |

Bimorphic distribution

* multi bi : 67.1 ns
* proto bi : 20.3 ns
