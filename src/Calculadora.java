import java.util.Stack;

public class Calculadora {

    public static double calcular(String expressao) {
        Stack<Double> numeros = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder numBuilder = new StringBuilder();
                numBuilder.append(c);

                while (i + 1 < expressao.length() &&
                        (Character.isDigit(expressao.charAt(i + 1)) || expressao.charAt(i + 1) == '.')) {
                    numBuilder.append(expressao.charAt(i + 1));
                    i++;
                }

                double num = Double.parseDouble(numBuilder.toString());
                numeros.push(num);
            } else if (c == '(') {
                operadores.push(c);
            } else if (c == ')') {
                while (operadores.peek() != '(') {
                    double num2 = numeros.pop();
                    double num1 = numeros.pop();
                    char op = operadores.pop();
                    numeros.push(calcular(num1, num2, op));
                }

                operadores.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operadores.isEmpty() && prioridade(operadores.peek()) >= prioridade(c)) {
                    double num2 = numeros.pop();
                    double num1 = numeros.pop();
                    char op = operadores.pop();
                    numeros.push(calcular(num1, num2, op));
                }

                operadores.push(c);
            }
        }

        while (!operadores.isEmpty()) {
            double num2 = numeros.pop();
            double num1 = numeros.pop();
            char op = operadores.pop();
            numeros.push(calcular(num1, num2, op));
        }

        return numeros.pop();
    }

    private static int prioridade(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
    }

    private static double calcular(double num1, double num2, char op) {
        return switch (op) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> throw new IllegalArgumentException("Operador inválido: " + op);
        };
    }
}
