package pk.org.cas.calculater;

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
