[![en](https://img.shields.io/badge/lang-en-red.svg)](README.md) [![en](https://img.shields.io/badge/lang-ru-blue.svg)](README.ru.md)

# Paradigms of Programming

Course was prepared and taught by [Georgiy Korneev](https://github.com/kgeorgiy) at ITMO University in 2018 (year2017, 2nd semester).

## Project setting in IntelliJ IDEA

1. Open whole repository as a project.
2. Mark `src` directory as Sources Root for each homework directory.
3. Mark `test/java`, `test/javascript` and `test/clojure` as Test Sources Root.
4. You can also mark `examples` as Excluded.
5. Be sure that all Maven-dependencies configured correctly and the project is building.

## Homeworks

1. Hashing ([statements (ru)](hw01-hash/tasks.md), [solution](hw01-hash))
    * running tests for simple modification: `CalcSHA256`
2. Binary search ([statements (ru)](hw02-search/tasks.md), [solution](hw02-search))
    * running tests for basic modification: `BinarySearchTest`
    * running tests for advanced modification: `BinarySearchSpanTest`
3. Queue on array ([statements (ru)](hw03-array-queue/tasks.md), [solution](hw03-array-queue))
    * running tests for basic modification: `ArrayQueueTest`
    * running tests for advanced modification: `ArrayQueueDequeTest`
4. Queues ([statements (ru)](hw04-queues/tasks.md), [solution](hw04-queues))
    * running tests for basic modification: `QueueTest`
    * running tests for advanced modification: `QueueFunctionsTest`
5. Expression evaluating ([statements (ru)](hw05-expression/tasks.md), [solution](hw05-expression))
    * running tests for basic modification: `ExpressionTest`
    * running tests for simple modification: `DoubleExpressionTest`
    * running tests for advanced modification: `TripleExpressionTest`
6. Expression parsing ([statements (ru)](hw06-parser/tasks.md), [solution](hw06-parser))
    * running tests for basic modification: `ParserTest`
    * running tests for simple modification: `ParserNotCountTest`
    * running tests for advanced modification: `ParserBitwiseTest`
7. Exception handling ([statements (ru)](hw07-exception/tasks.md), [solution](hw07-exception))
    * running tests for basic modification: `ExceptionsTest`
    * running tests for simple modification: `ExceptionsPowLog10Test`
    * running tests for advanced modification: `ExceptionsPowLogTest`
8. Evaluatings in different types ([statements (ru)](hw08-generics/tasks.md), [solution](hw08-generics))
    * running tests for basic modification: `GenericTest`
    * running tests for simple modification: `GenericUlsTest`
    * running tests for advanced modification: `GenericCmmUlsTest`
9. Functional expressions on JavaScript ([statements (ru)](hw09-js-functional/tasks.md), [solution](hw09-js-functional))
    * running tests for basic modification: `FunctionalExpressionTest easy` or `FunctionalExpressionTest hard`
    * running tests for advanced modification: `FunctionalPieMinMaxTest easy` or `FunctionalPieMinMaxTest hard`
    * specify `hw09-js-functional/src` as a working directory during running tests
10. Object expressions on JavaScript ([statements (ru)](hw10-js-object/tasks.md), [solution](hw10-js-object))
    * running tests for basic modification: `ObjectExpressionTest easy` or `ObjectExpressionTest hard`
    * running tests for first modification: `ObjectSquareTest easy` or `ObjectSquareTest hard`
    * running tests for second modification: `ObjectPowLogTest easy` or `ObjectPowLogTest hard`
    * specify `hw10-js-object/src` as a working directory during running tests
11. Exception handling on JavaScript ([statements (ru)](hw11-js-error/tasks.md), [solution](hw11-js-error))
    * running tests for basic modification: `PrefixParserTest easy` or `PrefixParserTest hard`
    * running tests for simple modification: `PrefixAtanExpTest easy` or `PrefixAtanExpTest hard`
    * running tests for advanced modification: `PostfixAtanExpTest easy` or `PostfixAtanExpTest hard`
    * specify `hw11-js-error/src` as a working directory during running tests
12. Linear algebra on Clojure ([statements (ru)](hw12-clj-linear/tasks.md), [solution](hw12-clj-linear))
    * running tests for basic modification: `LinearBinaryTest`
    * running tests for simple modification: `LinearShapelessTest`
    * running tests for advanced modification: `LinearBroadcastTest`
    * specify `hw12-clj-linear/src` as a working directory during running tests
13. Functional expressions on Clojur ([statements (ru)](hw13-clj-functional/tasks.md), [solution](hw13-clj-functional))
    * running tests for basic modification: `ClojureFunctionalExpressionTest easy` or `ClojureFunctionalExpressionTest hard`
    * running tests for modification: `ClojureFunctionalSinhCoshTest easy` or `ClojureFunctionalSinhCoshTest hard`
    * specify `hw13-clj-functional/src` as a working directory during running tests
14. Object expressions on Clojur ([statements (ru)](hw14-clj-object/tasks.md), [solution](hw14-clj-object))
    * running tests for basic modification: `ClojureObjectExpressionTest easy` or `ClojureObjectExpressionTest hard`
    * running tests for modification: `ClojureObjectSinCosTest easy` or `ClojureObjectSinCosTest hard`
    * specify `hw14-clj-object/src` as a working directory during running tests
    
## Useful links

* [Course page "Paradigms of programming" on the site of G. Korneev (ru)](http://www.kgeorgiy.info/courses/paradigms/)
