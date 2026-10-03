package pk.org.cas.calculater;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Calculator {
    private double no1;
    private double no2;
    private char operator;

    public static final char ADDITION = '+';
    public static final char SUBTRACTION = '-';
    public static final char MULTIPLICATION = '*';
    public static final char DIVISION = '/';
    public static final char MODULES = '%';

    public double getNo1() {
        return no1;
    }

    public void setNo1(double no1) {
        this.no1 = no1;
    }

    public double getNo2() {
        return no2;
    }

    public void setNo2(double no2) {
        this.no2 = no2;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public Calculator() {
    }

    public Calculator(double no1, double no2, char operator) {
        this.no1 = no1;
        this.no2 = no2;
        this.operator = operator;
    }

    public Calculator(double no1, char operator, double no2) {
        this.no1 = no1;
        this.no2 = no2;
        this.operator = operator;
    }

    public double calculate() {
        if (operator == ADDITION) {
            return no1 + no2;
        } else if (operator == SUBTRACTION) {
            return no1 - no2;
        } else if (operator == DIVISION) {
            return no1 / no2;
        } else if (operator == MODULES) {
            return no1 % no2;
        } else if (operator == MULTIPLICATION) {
            return no1 * no2;
        } else {
            return 0;
        }
    }

    public static double evaluate(String expression) throws ArithmeticException, NumberFormatException {
        if (expression == null || expression.trim().isEmpty()) {
            return 0;
        }

        String expr = expression.trim();


        while (!expr.isEmpty() && isOperator(expr.charAt(expr.length() - 1))) {
            expr = expr.substring(0, expr.length() - 1);
        }

        if (expr.isEmpty()) {
            return 0;
        }


        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);


            if (c == '-' && (i == 0 || isOperator(expr.charAt(i - 1)))) {
                sb.append(c);
            } else if (isOperator(c)) {
                if (sb.length() > 0) {
                    numbers.add(Double.parseDouble(sb.toString()));
                    sb.setLength(0);
                }
                operators.add(c);
            } else {
                sb.append(c);
            }
        }

        if (sb.length() > 0) {
            numbers.add(Double.parseDouble(sb.toString()));
        }

        if (numbers.isEmpty()) {
            return 0;
        }

        if (numbers.size() == 1) {
            return numbers.get(0);
        }

        // First pass: *, /, %
        for (int i = 0; i < operators.size(); i++) {
            char op = operators.get(i);
            if (op == '*' || op == '/' || op == '%') {
                double num1 = numbers.get(i);
                double num2 = numbers.get(i + 1);
                double res;

                if (op == '*') {
                    res = num1 * num2;
                } else if (op == '/') {
                    if (num2 == 0) {
                        throw new ArithmeticException("Cannot divide by zero");
                    }
                    res = num1 / num2;
                } else {
                    if (num2 == 0) {
                        throw new ArithmeticException("Cannot divide by zero");
                    }
                    res = num1 % num2;
                }

                numbers.set(i, res);
                numbers.remove(i + 1);
                operators.remove(i);
                i--; // Step back to evaluate next operator at current position
            }
        }

        // Second pass: +, -
        for (int i = 0; i < operators.size(); i++) {
            char op = operators.get(i);
            double num1 = numbers.get(i);
            double num2 = numbers.get(i + 1);
            double res;

            if (op == '+') {
                res = num1 + num2;
            } else { // '-'
                res = num1 - num2;
            }

            numbers.set(i, res);
            numbers.remove(i + 1);
            operators.remove(i);
            i--;
        }

        return numbers.get(0);
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Calculator that = (Calculator) o;
        return Double.compare(no1, that.no1) == 0 && Double.compare(no2, that.no2) == 0 && operator == that.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(no1, no2, operator);
    }
}
