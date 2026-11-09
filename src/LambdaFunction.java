import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class LambdaFunction {

    public static void main(String[] args) {
        demonstrateFunction();
        demonstrateBiFunction();
        demonstratePredicate();
        demonstrateBiPredicate();
        demonstrateUnaryOperator();
        demonstrateBinaryOperator();
    }

    // 1. Function<T, R>
    // Accepts one argument of type T and produces a result of type R.
    // Method: R apply(T t)
    private static void demonstrateFunction() {

        // Example A: Convert a String to its length (Integer)
        Function<String, Integer> stringLength = str -> str.length();
        String sampleText = "Lambda Expressions";
        System.out.println("Input: \"" + sampleText + "\"");
        System.out.println("String Length (str -> str.length()): " + stringLength.apply(sampleText));

        // Example B: Calculate total price with 15% tax
        Function<Double, Double> addTax = price -> price * 1.15;
        double basePrice = 200.0;
        System.out.printf("Base price: $%.2f | Price with 15%% tax: $%.2f%n", basePrice, addTax.apply(basePrice));

        // Example C: Function Chaining with .andThen() and .compose()
        Function<Integer, Integer> multiplyByTwo = n -> n * 2;
        Function<Integer, Integer> addTen = n -> n + 10;

        // andThen: first multiplyByTwo, then addTen -> (5 * 2) + 10 = 20
        Function<Integer, Integer> multiplyThenAdd = multiplyByTwo.andThen(addTen);
        System.out.println("Chaining .andThen() -> (5 * 2) + 10 = " + multiplyThenAdd.apply(5));

        // compose: first addTen, then multiplyByTwo -> (5 + 10) * 2 = 30
        Function<Integer, Integer> addThenMultiply = multiplyByTwo.compose(addTen);
        System.out.println("Chaining .compose() -> (5 + 10) * 2 = " + addThenMultiply.apply(5));
    }

    // 2. BiFunction<T, U, R>
    // Accepts two arguments of types T and U and produces a result of type R.
    // Method: R apply(T t, U u)

    private static void demonstrateBiFunction() {

        // Example A: Calculate total price from quantity and unit price
        BiFunction<Integer, Double, Double> calculateTotal = (quantity, unitPrice) -> quantity * unitPrice;
        int qty = 4;
        double unitCost = 24.50;
        System.out.printf("Qty: %d, Unit Cost: $%.2f -> Total: $%.2f%n",
                qty, unitCost, calculateTotal.apply(qty, unitCost));

        // Example B: Combine first and last name into full formatted name
        BiFunction<String, String, String> formatFullName = (first, last) -> last.toUpperCase() + ", " + first;
        System.out.println("Full Name: " + formatFullName.apply("David", "Sofuyi"));

        // Example C: BiFunction chained with Function using .andThen()
        BiFunction<Integer, Integer, Integer> power = (base, exponent) -> (int) Math.pow(base, exponent);
        Function<Integer, String> formatResult = result -> "Result: " + result;
        BiFunction<Integer, Integer, String> powerAndFormat = power.andThen(formatResult);

        System.out.println("BiFunction + andThen: 2^8 -> " + powerAndFormat.apply(2, 8));
    }

    // 3. Predicate<T>
    // Accepts one argument of type T and returns a boolean (true/false).
    // Method: boolean test(T t)
    private static void demonstratePredicate() {

        // Example A: Check if a number is even
        Predicate<Integer> isEven = n -> n % 2 == 0;
        int num1 = 14;
        int num2 = 19;
        System.out.println("Is " + num1 + " even? " + isEven.test(num1));
        System.out.println("Is " + num2 + " even? " + isEven.test(num2));

        // Example B: Non-empty string check
        Predicate<String> isNotEmpty = s -> s != null && !s.trim().isEmpty();
        System.out.println("Is \"Hello\" valid non-empty? " + isNotEmpty.test("Hello"));
        System.out.println("Is \"   \" valid non-empty? " + isNotEmpty.test("   "));

        // Example C: Logical composition with .and(), .or(), .negate()
        Predicate<Integer> isGreaterThanTen = n -> n > 10;
        Predicate<Integer> isLessThanFifty = n -> n < 50;

        Predicate<Integer> isBetween10And50 = isGreaterThanTen.and(isLessThanFifty);
        Predicate<Integer> isOdd = isEven.negate();

        System.out.println("Is 25 between 10 and 50? " + isBetween10And50.test(25));
        System.out.println("Is 7 odd (negate even)? " + isOdd.test(7));
    }

    // 4. BiPredicate<T, U>
    // Accepts two arguments of types T and U and returns a boolean (true/false).
    // Method: boolean test(T t, U u)

    private static void demonstrateBiPredicate() {

        // Example A: Check if string length is greater than or equal to a minimum length
        BiPredicate<String, Integer> hasMinLength = (str, min) -> str != null && str.length() >= min;
        String password = "SecretPassword123";
        System.out.println("Does password meet min length 8? " + hasMinLength.test(password, 8));
        System.out.println("Does password meet min length 25? " + hasMinLength.test(password, 25));

        // Example B: Check if sentence contains keyword (case-insensitive)
        BiPredicate<String, String> containsIgnoreCase = (sentence, keyword) ->
                sentence.toLowerCase().contains(keyword.toLowerCase());

        String text = "Lambda expressions in Java simplify functional programming.";
        System.out.println("Text contains 'java'? " + containsIgnoreCase.test(text, "java"));
        System.out.println("Text contains 'python'? " + containsIgnoreCase.test(text, "python"));

        // Example C: Composition with .and(), .or(), .negate()
        BiPredicate<Integer, Integer> isDivisibleBy = (number, divisor) -> divisor != 0 && number % divisor == 0;
        BiPredicate<Integer, Integer> bothPositive = (a, b) -> a > 0 && b > 0;

        BiPredicate<Integer, Integer> validDivisorPair = bothPositive.and(isDivisibleBy);
        System.out.println("Are (20, 4) positive and divisible? " + validDivisorPair.test(20, 4));
        System.out.println("Are (20, -4) positive and divisible? " + validDivisorPair.test(20, -4));
    }

    // 5. UnaryOperator<T>
    // A specialized Function<T, T> where the operand and result are the SAME type.
    // Method: T apply(T t)
    private static void demonstrateUnaryOperator() {

        // Example A: Convert text to uppercase
        UnaryOperator<String> toUpper = str -> str.toUpperCase();
        System.out.println("Uppercase: " + toUpper.apply("my name is lankydave sofuyi"));

        // Example B: Square a number
        UnaryOperator<Integer> square = n -> n * n;
        System.out.println("Square of 9: " + square.apply(9));

        // Example C: Sanitizing user input string
        UnaryOperator<String> cleanInput = str -> str.trim().replaceAll("\\s+", " ");
        String messyInput = "   working  on    Python   Framework    Django   ";
        System.out.println("Cleaned input: \"" + cleanInput.apply(messyInput) + "\"");

        // Example D: Used with List.replaceAll()
        List<String> frameworkList = Arrays.asList("fastapi", "spring boot", "django", "react");
        frameworkList.replaceAll(toUpper);
        System.out.println("List after replaceAll with UnaryOperator: " + frameworkList);
    }

    // 6. BinaryOperator<T>
    // A specialized BiFunction<T, T, T> where both operands and the result
    // are of the SAME type.
    // Method: T apply(T t1, T t2)
    
    private static void demonstrateBinaryOperator() {

        // Example A: Addition of two integers
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("Addition (15 + 27): " + add.apply(15, 27));

        // Example B: Concatenate strings with a delimiter
        BinaryOperator<String> joinWithHyphen = (s1, s2) -> s1 + " - " + s2;
        System.out.println("Join Strings: " + joinWithHyphen.apply("Java", "Lambdas"));

        // Example C: Find max using BinaryOperator.maxBy()
        Comparator<Integer> integerComparator = Integer::compareTo;
        BinaryOperator<Integer> maxOperator = BinaryOperator.maxBy(integerComparator);
        BinaryOperator<Integer> minOperator = BinaryOperator.minBy(integerComparator);

        System.out.println("Max of 42 and 99: " + maxOperator.apply(42, 99));
        System.out.println("Min of 42 and 99: " + minOperator.apply(42, 99));

        // Example D: Stream reduce with BinaryOperator
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10);
        int sum = numbers.stream().reduce(0, add);
        System.out.println("Stream sum using BinaryOperator: " + sum);
    }
}


