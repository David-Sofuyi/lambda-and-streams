# Lambda and Streams

A Java project demonstrating core functional programming concepts using **Lambda Expressions**, **Functional Interfaces**, and **Streams** (`java.util.function` and `java.util.stream`).

---

## 📌 Functional Interfaces Covered

| Interface | Method Signature | Description | Example in Project |
|---|---|---|---|
| **`Function<T, R>`** | `R apply(T t)` | Transforms input of type `T` to output of type `R`. Supports `.andThen()` and `.compose()`. | String length calculation, price tax calculation. |
| **`BiFunction<T, U, R>`** | `R apply(T t, U u)` | Takes two arguments (`T`, `U`) and produces result `R`. | Quantity × unit price total, formatting full name. |
| **`Predicate<T>`** | `boolean test(T t)` | Evaluates a boolean condition on input `T`. Supports `.and()`, `.or()`, and `.negate()`. | Even/odd check, non-empty validation. |
| **`BiPredicate<T, U>`** | `boolean test(T t, U u)` | Evaluates a boolean condition on two inputs (`T`, `U`). | Minimum length check, substring/keyword search. |
| **`UnaryOperator<T>`** | `T apply(T t)` | Specialized `Function<T, T>` where argument and return types are identical. | Uppercase transformation, squaring numbers, `List.replaceAll()`. |
| **`BinaryOperator<T>`** | `T apply(T a, T b)` | Specialized `BiFunction<T, T, T>` where both arguments and return type are identical. | Addition, string joining, `BinaryOperator.maxBy()`, stream `reduce()`. |

---

## 🚀 How to Run

### Option 1: IntelliJ IDEA
Open the project directory in IntelliJ IDEA, open `src/Main.java` or `src/LambdaFunction.java`, and click the green **Run** arrow.

### Option 2: Command Line

```powershell
# Compile classes
javac -d out src/*.java

# Run Main
java -cp out Main
```
