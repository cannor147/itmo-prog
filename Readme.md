# Общая информация о курсе

Данный репозиторий содержит все мои работы по курсу "Парадигмы программирования" в Университете ИТМО (2018, 2 семестр). Здесь представлены решения домашних работ по курсу. Все условия и мои решения заданий хранятся в открытом доступе.

# Настройка проекта

1. В папке каждого домашнего задания пометьте директорию `src` как Sources Root
2. Пометьте директории `test/java`, `test/javascript` и `test/clojure` как Test Sources Root
4. Директорию `examples` можно пометить как Excluded
5. Убедитесь, что все Maven-зависимости настроены и проект собирается

# Домашние работы

1. Хэширование

   * **примерные темы:** _основы программирования_
   * [условия](hw01-hash/tasks.md), [решение](hw01-hash)
   * для запуска тестов использовать класс:
     `CalcMD5Test`
   * для запуска тестов использовать класс:
     `CalcSHA256`
2. Бинарный поиск

   * **примерные темы:** _программирование по контракту_
   * условия и решение пока ещё хранятся в локальном репозитории
3. Очередь на массиве

   * **примерные темы:** _введение в ООП_
   * условия и решение пока ещё хранятся в локальном репозитории
4. Очереди

   * **примерные темы:** _введение в ООП_
   * условия и решение пока ещё хранятся в локальном репозитории
5. Вычисление выражений

   * **примерные темы:** _введение в ООП_
   * [условия](hw05-expression/tasks.md), [решение](hw05-expression)
   * запуск тестов для базовой модификации:
     `ExpressionTest`
   * запуск тестов для простой модификации:
     `DoubleExpressionTest`
   * запуск тестов для сложной модификации:
     `TripleExpressionTest`
6. Разбор выражений

   * **примерные темы:** _синтаксис Java_
   * [условия](hw06-parser/tasks.md), [решение](hw06-parser)
   * запуск тестов для базовой модификации:
     `ParserTest`
   * запуск тестов для простой модификации:
     `ParserNotCountTest`
   * запуск тестов для сложной модификации:
     `ParserBitwiseTest`
7. Обработка ошибок

   * **примерные темы:** _обработка ошибок, exceptions_
   * [условия](hw07-exception/tasks.md), [решение](hw07-exception)
   * запуск тестов для базовой модификации:
     `ExceptionsTest`
   * запуск тестов для простой модификации:
     `ExceptionsPowLog10Test`
   * запуск тестов для сложной модификации:
     `ExceptionsPowLogTest`
8. Вычисление в различных типах
   * **примерные темы:** _generics_
   * [условия](hw08-generics/tasks.md), [решение](hw08-generics)
   * запуск тестов для базовой модификации:
     `GenericTest`
   * запуск тестов для простой модификации:
     `GenericUlsTest`
   * запуск тестов для сложной модификации:
     `GenericCmmUlsTest`
9. Функциональные выражения на JavaScript

   * **примерные темы:** _функции в JavaScript_
   * [условия](hw09-js-functional/tasks.md), [решение](hw09-js-functional)
   * запуск тестов для базовой модификации:
     `FunctionalExpressionTest easy` или `FunctionalExpressionTest hard`
   * запуск тестов для сложной модификации:
     `FunctionalPieMinMaxTest easy` или `FunctionalPieMinMaxTest hard`
   * в качестве рабочей директории при запуске тестов указывать `hw09-js-functional/src`
10. Объектные выражения на JavaScript

    * **примерные темы:** _объекты и методы в JavaScript_
    * [условия](hw10-js-object/tasks.md), [решение](hw10-js-object)
    * запуск тестов для базовой модификации:
      `ObjectExpressionTest easy` или `ObjectExpressionTest hard`
    * запуск тестов для первой модификации:
      `ObjectSquareTest easy` или `ObjectSquareTest hard`
    * запуск тестов для второй модификации:
      `ObjectPowLogTest easy` или `ObjectPowLogTest hard`
    * в качестве рабочей директории при запуске тестов указывать `hw10-js-object/src`
11. Обработка ошибок на JavaScript

    * **примерные темы:** _обработка ошибок в JavaScript_
    * [условия](hw11-js-error/tasks.md), [решение](hw11-js-error)
    * запуск тестов для базовой модификации:
      `PrefixParserTest easy` или `PrefixParserTest hard`
    * запуск тестов для простой модификации:
      `PrefixAtanExpTest easy` или `PrefixAtanExpTest hard`
    * запуск тестов для сложной модификации:
      `PostfixAtanExpTest easy` или `PostfixAtanExpTest hard`
    * в качестве рабочей директории при запуске тестов указывать `hw11-js-error/src`
12. Линейная алгебра на Clojure

    * **примерные темы:** _функции в Clojure_
    * [условия](hw12-clj-linear/tasks.md), [решение](hw12-clj-linear)
    * запуск тестов для базовой модификации:
      `LinearBinaryTest`
    * запуск тестов для простой модификации:
      `LinearShapelessTest`
    * запуск тестов для сложной модификации:
      `LinearBroadcastTest`
    * в качестве рабочей директории при запуске тестов указывать `hw12-clj-linear/src`
13. Функциональные выражения на Clojure
    * **примерные темы:** _внешний мир в Clojure_
    * [условия](hw13-clj-functional/tasks.md), [решение](hw13-clj-functional)
    * запуск тестов для базовой модификации:
      `ClojureFunctionalExpressionTest easy` или `ClojureFunctionalExpressionTest hard`
    * запуск тестов для первой модификации:
      `ClojureFunctionalSinhCoshTest easy` или `ClojureFunctionalSinhCoshTest hard`
    * в качестве рабочей директории при запуске тестов указывать `hw13-clj-functional/src`
14. Объектные выражения на Clojure
    * **примерные темы:** _объекты в Clojure_
    * [условия](hw14-clj-object/tasks.md), [решение](hw14-clj-object)
    * запуск тестов для базовой модификации:
      `ClojureObjectExpressionTest easy` или `ClojureObjectExpressionTest hard`
    * запуск тестов для модификации:
      `ClojureObjectSinCosTest easy` или `ClojureObjectSinCosTest hard`
    * в качестве рабочей директории при запуске тестов указывать `hw14-clj-object/src`


# Полезные ссылки

* [Страница курса](http://www.kgeorgiy.info/courses/paradigms/)