package Model;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.interfaces.IExpr;

public class Calculadora {

        private String funcao;
        private double numero;
        private final ExprEvaluator evaluator;

        public Calculadora() {
            F.initSymbols();  // Inicializa os símbolos do Symja
            evaluator = new ExprEvaluator();
        }

        public void setFuncao(String funcao) {
            this.funcao = funcao;
        }

        public void setNumero(double numero) {
            this.numero = numero;
        }

        public String calcularDerivada() {
            String expressao = "D(" + funcao + ", x)";
            IExpr resultado = evaluator.evaluate(expressao);
            return resultado.toString();
        }

        public String calcularIntegral() {
            String expressao = "Integrate(" + funcao + ", x)";
            IExpr resultado = evaluator.evaluate(expressao);
            return resultado.toString();
        }

        public String avaliarEmPonto() {
            String expressao = funcao + " /. x -> " + numero;
            IExpr resultado = evaluator.evaluate(expressao);
            return resultado.toString();
        }


    // Método para atualizar as coordenadas
    public void setCoordenadas(double numero) {
        this.numero = numero;

    }
}
