[![en](https://img.shields.io/badge/lang-en-red.svg)](README.md) [![en](https://img.shields.io/badge/lang-ru-blue.svg)](README.ru.md)

# Парадигмы Программирования

Курс подготовил и прочитал [Георгий Корнеев](https://github.com/kgeorgiy) в Университете ИТМО в 2018 году (year2017, 2 семестр).

## Настройка проекта в IntelliJ IDEA

1. Откройте весь репозиторий как один проект.
2. В папке каждого домашнего задания пометьте директорию `src` как Sources Root.
3. Пометьте директории `test/java`, `test/javascript` и `test/clojure` как Test Sources Root.
4. Директорию `examples` можно пометить как Excluded.
5. Убедитесь, что все Maven-зависимости настроены и проект собирается.

## Домашние работы

1. Хэширование ([условия](hw01-hash/tasks.md), [решение](hw01-hash))
    * запуск тестов для простой модификации: `CalcSHA256`
2. Бинарный поиск ([условия](hw02-search/tasks.md), [решение](hw02-search))
    * запуск тестов для базовой модификации: `BinarySearchTest`
    * запуск тестов для сложной модификации: `BinarySearchSpanTest`
3. Очередь на массиве ([условия](hw03-array-queue/tasks.md), [решение](hw03-array-queue))
    * запуск тестов для базовой модификации: `ArrayQueueTest`
    * запуск тестов для сложной модификации: `ArrayQueueDequeTest`
4. Очереди ([условия](hw04-queues/tasks.md), [решение](hw04-queues))
    * запуск тестов для базовой модификации: `QueueTest`
    * запуск тестов для сложной модификации: `QueueFunctionsTest`
5. Вычисление выражений ([условия](hw05-expression/tasks.md), [решение](hw05-expression))
    * запуск тестов для базовой модификации: `ExpressionTest`
    * запуск тестов для простой модификации: `DoubleExpressionTest`
    * запуск тестов для сложной модификации: `TripleExpressionTest`
6. Разбор выражений ([условия](hw06-parser/tasks.md), [решение](hw06-parser))
    * запуск тестов для базовой модификации: `ParserTest`
    * запуск тестов для простой модификации: `ParserNotCountTest`
    * запуск тестов для сложной модификации: `ParserBitwiseTest`
7. Обработка ошибок ([условия](hw07-exception/tasks.md), [решение](hw07-exception))
    * запуск тестов для базовой модификации: `ExceptionsTest`
    * запуск тестов для простой модификации: `ExceptionsPowLog10Test`
    * запуск тестов для сложной модификации: `ExceptionsPowLogTest`
8. Вычисление в различных типах ([условия](hw08-generics/tasks.md), [решение](hw08-generics))
    * запуск тестов для базовой модификации: `GenericTest`
    * запуск тестов для простой модификации: `GenericUlsTest`
    * запуск тестов для сложной модификации: `GenericCmmUlsTest`
9. Функциональные выражения на JavaScript ([условия](hw09-js-functional/tasks.md), [решение](hw09-js-functional))
    * запуск тестов для базовой модификации: `FunctionalExpressionTest easy` или `FunctionalExpressionTest hard`
    * запуск тестов для сложной модификации: `FunctionalPieMinMaxTest easy` или `FunctionalPieMinMaxTest hard`
    * в качестве рабочей директории при запуске тестов указывать `hw09-js-functional/src`
10. Объектные выражения на JavaScript ([условия](hw10-js-object/tasks.md), [решение](hw10-js-object))
    * запуск тестов для базовой модификации: `ObjectExpressionTest easy` или `ObjectExpressionTest hard`
    * запуск тестов для первой модификации: `ObjectSquareTest easy` или `ObjectSquareTest hard`
    * запуск тестов для второй модификации: `ObjectPowLogTest easy` или `ObjectPowLogTest hard`
    * в качестве рабочей директории при запуске тестов указывать `hw10-js-object/src`
11. Обработка ошибок на JavaScript ([условия](hw11-js-error/tasks.md), [решение](hw11-js-error))
    * запуск тестов для базовой модификации: `PrefixParserTest easy` или `PrefixParserTest hard`
    * запуск тестов для простой модификации: `PrefixAtanExpTest easy` или `PrefixAtanExpTest hard`
    * запуск тестов для сложной модификации: `PostfixAtanExpTest easy` или `PostfixAtanExpTest hard`
    * в качестве рабочей директории при запуске тестов указывать `hw11-js-error/src`
12. Линейная алгебра на Clojure ([условия](hw12-clj-linear/tasks.md), [решение](hw12-clj-linear))
    * запуск тестов для базовой модификации: `LinearBinaryTest`
    * запуск тестов для простой модификации: `LinearShapelessTest`
    * запуск тестов для сложной модификации: `LinearBroadcastTest`
    * в качестве рабочей директории при запуске тестов указывать `hw12-clj-linear/src`
13. Функциональные выражения на Clojur ([условия](hw13-clj-functional/tasks.md), [решение](hw13-clj-functional))
    * запуск тестов для базовой модификации: `ClojureFunctionalExpressionTest easy` или `ClojureFunctionalExpressionTest hard`
    * запуск тестов для модификации: `ClojureFunctionalSinhCoshTest easy` или `ClojureFunctionalSinhCoshTest hard`
    * в качестве рабочей директории при запуске тестов указывать `hw13-clj-functional/src`
14. Объектные выражения на Clojur ([условия](hw14-clj-object/tasks.md), [решение](hw14-clj-object))
    * запуск тестов для базовой модификации: `ClojureObjectExpressionTest easy` или `ClojureObjectExpressionTest hard`
    * запуск тестов для модификации: `ClojureObjectSinCosTest easy` или `ClojureObjectSinCosTest hard`
    * в качестве рабочей директории при запуске тестов указывать `hw14-clj-object/src`
    
## Полезные ссылки

* [Страница курса "Парадигмы программирования" на сайте Г.А. Корнеева](http://www.kgeorgiy.info/courses/paradigms/)
