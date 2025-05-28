//package Model;
//
//import org.matheclipse.core.eval.ExprEvaluator;
//import org.matheclipse.core.expression.F;
//import org.matheclipse.core.interfaces.IExpr;
//
//public class Calculadora {
//
//        private String funcao;
//        private double numero;
//        private final ExprEvaluator evaluator;
//
//        public Calculadora() {
//            F.initSymbols();
//            evaluator = new ExprEvaluator();
//        }
//
//        public void setFuncao(String funcao) {
//            this.funcao = funcao;
//        }
//
//        public void setNumero(double numero) {
//            this.numero = numero;
//        }
//
//        public String calcularDerivada() {
//            String expressao = "D(" + funcao + ", x)";
//            IExpr resultado = evaluator.evaluate(expressao);
//            return resultado.toString();
//        }
//
//        public String calcularIntegral() {
//            String expressao = "Integrate(" + funcao + ", x)";
//            IExpr resultado = evaluator.evaluate(expressao);
//            return resultado.toString();
//        }
//}
package Model;

public class Calculadora {

    //realiza os cálculos
    private CalculadoraPolinomial polinomial;

    // Define a função polinomial a ser usada nos cálculos
    public void setFuncao(String funcao) {
        // Cria uma nova instância de CalculadoraPolinomial com a função fornecida
        this.polinomial = new CalculadoraPolinomial(funcao);
    }

    // Calcula a primeira derivada da função definida
    public String calcularDerivada() {
        return polinomial.calculateDerivative();
    }

    // Calcula a integral
    public String calcularIntegral() {
        return polinomial.calculateIntegral();
    }

    // Calcula a segunda derivada da função definida
    public String calcularD2(){
        return polinomial.calculateDerivative2();
    }
}
