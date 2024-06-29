package Utils;

import java.util.Stack;

public class InfixToPostfix {

    public static String infixToPostfix(String exp) throws IllegalArgumentException {
        if (exp == null || exp.trim().isEmpty()) {
            throw new IllegalArgumentException("La expresión no puede estar vacía.");
        }

        String result = "";
        Stack<Character> stack = new Stack<>();
        StringBuilder number = new StringBuilder();
        boolean lastWasOperator = true; // Flag to check if last character was an operator

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (Character.isWhitespace(c)) {
                continue; // Skip whitespaces
            }

            if (Character.isDigit(c) || c == '.') {
                if (c == '.' && number.indexOf(".") != -1) {
                    throw new IllegalArgumentException("Número inválido con múltiples puntos decimales.");
                }
                number.append(c);
                lastWasOperator = false;
            } else {
                if (number.length() > 0) {
                    result += number.toString() + " ";
                    number.setLength(0);
                }
                if (c == '(') {
                    stack.push(c);
                    lastWasOperator = true;
                } else if (c == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(')
                        result += stack.pop() + " ";
                    if (!stack.isEmpty() && stack.peek() != '(')
                        throw new IllegalArgumentException("Expresión inválida: paréntesis desbalanceados.");
                    else
                        stack.pop();
                    lastWasOperator = false;
                } else if (isOperator(c)) {
                    if (lastWasOperator) {
                        throw new IllegalArgumentException("Expresión inválida: operadores consecutivos.");
                    }
                    while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                        if (stack.peek() == '(')
                            throw new IllegalArgumentException("Expresión inválida: paréntesis mal colocados.");
                        result += stack.pop() + " ";
                    }
                    stack.push(c);
                    lastWasOperator = true;
                } else {
                    throw new IllegalArgumentException("Carácter no permitido en la expresión: " + c);
                }
            }
        }

        if (number.length() > 0) {
            result += number.toString() + " ";
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                throw new IllegalArgumentException("Expresión inválida: paréntesis desbalanceados.");
            result += stack.pop() + " ";
        }
        return result.trim();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public void inorder(ExpressionNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }
    }
    
    public void preorder(ExpressionNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }
    
    public void postorder(ExpressionNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.value + " ");
        }
    }
    
}
