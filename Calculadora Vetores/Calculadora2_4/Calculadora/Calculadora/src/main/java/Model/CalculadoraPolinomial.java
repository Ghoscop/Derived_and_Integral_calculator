package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculadoraPolinomial {
    private static final String VARIABLE_X = "x";
    private String function;


    public CalculadoraPolinomial(String function) {
        this.function = function;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String calculateDerivative() {
        try {
            return computeDerivative(function);
        } catch (UnsupportedOperationException e) {
            return e.getMessage();
        }
    }

    public String calculateIntegral() {
        try {
            return computeIntegral(function);
        } catch (UnsupportedOperationException e) {
            return e.getMessage();
        }
    }

    private String computeDerivative(String expression) {
        Pattern pattern = Pattern.compile("([+-]?\\d*)\\*?x\\^?(\\d*)");
        Matcher matcher = pattern.matcher(expression);

        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String coefficientStr = matcher.group(1);
            String exponentStr = matcher.group(2);

            int coefficient = coefficientStr.isEmpty() || coefficientStr.equals("+") || coefficientStr.equals("-")
                    ? (coefficientStr.equals("-") ? -1 : 1)
                    : Integer.parseInt(coefficientStr);

            int exponent = exponentStr.isEmpty() ? 1 : Integer.parseInt(exponentStr);

            if (exponent == 0) continue;

            int newCoefficient = coefficient * exponent;
            int newExponent = exponent - 1;

            result.append(newCoefficient > 0 && result.length() > 0 ? "+" : "")
                    .append(newCoefficient);

            if (newExponent > 0) {
                result.append("*x");
                if (newExponent > 1) {
                    result.append("^").append(newExponent);
                }
            }
        }

        return result.length() == 0 ? "0" : result.toString();
    }

    private String computeIntegral(String expression) {
        Pattern pattern = Pattern.compile("([+-]?\\d*)\\*?x\\^?(\\d*)");
        Matcher matcher = pattern.matcher(expression);

        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String coefficientStr = matcher.group(1);
            String exponentStr = matcher.group(2);

            int coefficient = coefficientStr.isEmpty() || coefficientStr.equals("+") || coefficientStr.equals("-")
                    ? (coefficientStr.equals("-") ? -1 : 1)
                    : Integer.parseInt(coefficientStr);

            int exponent = exponentStr.isEmpty() ? 1 : Integer.parseInt(exponentStr);

            int newExponent = exponent + 1;
            double newCoefficient = (double) coefficient / newExponent;

            String formattedCoeff = String.format("%.2f", newCoefficient);

            result.append(newCoefficient > 0 && result.length() > 0 ? "+" : "")
                    .append(formattedCoeff).append("*x");

            if (newExponent > 1) {
                result.append("^").append(newExponent);
            }
        }

        if (result.length() == 0) return expression + "*x+C";
        result.append("+C");

        return result.toString();
    }
}
