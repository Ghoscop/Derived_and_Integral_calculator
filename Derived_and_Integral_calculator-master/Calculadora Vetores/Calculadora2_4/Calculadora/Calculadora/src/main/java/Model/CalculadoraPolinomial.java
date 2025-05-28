// Pacote onde a classe está localizada
package Model;

// Importa classes importantes
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Classe responsável por cálculos de polinômios (derivadas e integrais)
public class CalculadoraPolinomial {
    private static final String VARIABLE_X = "x"; // Constante para variável x
    private String function; // Função polinomial fornecida pelo usuário


    // Construtor: recebe uma função polinomial como string
    public CalculadoraPolinomial(String function) {
        this.function = function;
    }

    // Getter da função
    public String getFunction() {
        return function;
    }

    // Setter da função
    public void setFunction(String function) {
        this.function = function;
    }

    // Calcula a primeira derivada da função
    public String calculateDerivative() {
        try {
            return computeDerivative(function);  // Chama o método interno
        } catch (UnsupportedOperationException e) {
            return e.getMessage(); // Retorna a mensagem de erro, se houver
        }
    }

    // Calcula a integral da função
    public String calculateIntegral() {
        try {
            return computeIntegral(function); // Chama o método interno
        } catch (UnsupportedOperationException e) {
            return e.getMessage();
        }
    }

    // Calcula a segunda derivada da função
    public String calculateDerivative2() {
        try {
            return derivada2(function); // Chama o método interno
        } catch (UnsupportedOperationException e) {
            return e.getMessage();
        }
    }

    // Método privado para calcular a primeira derivada
    private String computeDerivative(String expression) {
        // Expressão regular para identificar termos do tipo ax^n
        Pattern pattern = Pattern.compile("([+-]?\\d*)\\*?x\\^?(\\d*)");
        Matcher matcher = pattern.matcher(expression);

        StringBuilder result = new StringBuilder();

        // Itera sobre todos os termos que correspondem à expressão regular
        while (matcher.find()) {
            String coefficientStr = matcher.group(1); // Coeficiente
            String exponentStr = matcher.group(2); // Expoente

            // Trata coeficientes vazios ou sinais isolados
            int coefficient = coefficientStr.isEmpty() || coefficientStr.equals("+") || coefficientStr.equals("-")
                    ? (coefficientStr.equals("-") ? -1 : 1)
                    : Integer.parseInt(coefficientStr);

            // Se não houver expoente, assume 1
            int exponent = exponentStr.isEmpty() ? 1 : Integer.parseInt(exponentStr);

            if (exponent == 0) continue; // Derivada de constante é zero

            int newCoefficient = coefficient * exponent;
            int newExponent = exponent - 1;

            // Concatena o termo derivado à string de resultado
            result.append(newCoefficient > 0 && result.length() > 0 ? "+" : "")
                    .append(newCoefficient);

            if (newExponent > 0) {
                result.append("x");
                if (newExponent > 1) {
                    result.append("^").append(newExponent);
                }
            }
        }

        // Se não houve termos válidos, retorna zero
        return result.length() == 0 ? "0" : result.toString();
    }

    // Método privado para calcular a integral indefinida
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
                    .append(formattedCoeff).append("x");

            if (newExponent > 1) {
                result.append("^").append(newExponent);
            }
        }

        // Caso não tenha nenhum termo identificado, assume que é uma constante
        if (result.length() == 0) return expression + "*x+C";
        // Adiciona constante de integração
        result.append("+C");

        return result.toString();
    }

    // Calcula a segunda derivada chamando duas vezes o método derivar
    private String derivada2(String expression) {
        String primeiraDerivada = computeDerivative(expression);  // Primeira derivada
        return computeDerivative(primeiraDerivada);  // Deriva novamente
    }

}

