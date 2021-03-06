### Домашнее задание 11. Обработка ошибок на JavaScript

1. Добавьте в предыдущее домашнее задание функцию `parsePrefix(string)`, разбирающую выражения, задаваемые записью вида `(- (* 2 x) 3)`. Если разбираемое выражение некорректно, метод `parsePrefix` должен бросать человеко-читаемое сообщение об ошибке.
2. Добавьте в предыдущее домашнее задание метод `prefix()`, выдающий выражение в формате, ожидаемом функцией `parsePrefix`.
3. При выполнение задания следует обратить внимание на:
    * Применение инкапсуляции.
    * Выделение общего кода для бинарных операций.
    * Обработку ошибок.
    * Минимизацию необходимой памяти.

#### Простая модификация

* Дополнительно реализовать поддержку:
  - унарных операций:
    - `ArcTan` (`atan`) — арктангенс, `(atan 2)` примерно равно 1.1;
    - `Exp` (`Exp`) — экспонента, `(exp 3)` примерно равно 20;

#### Сложная модификация

* Дополнительно реализовать поддержку выражений в постфиксной записи:
  - `(2 3 +)` равно 5