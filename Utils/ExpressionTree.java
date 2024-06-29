package Utils;

import java.util.Stack;

public class ExpressionTree {

    public ExpressionNode constructTree(String postfix) {
        Stack<ExpressionNode> stack = new Stack<>();
        ExpressionNode t, t1, t2;
        String[] tokens = postfix.split("\\s+");

        for (String token : tokens) {
            if (!isOperator(token)) {
                t = new ExpressionNode(token);
                stack.push(t);
            } else {
                t = new ExpressionNode(token);

                t1 = stack.pop();
                t2 = stack.pop();

                t.right = t1;
                t.left = t2;

                stack.push(t);
            }
        }

        t = stack.peek();
        stack.pop();

        return t;
    }

    private boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/");
    }

    public void inorder(ExpressionNode node, StringBuilder result) {
        if (node != null) {
            inorder(node.left, result);
            result.append(node.value).append(" ");
            inorder(node.right, result);
        }
    }

    public void preorder(ExpressionNode node, StringBuilder result) {
        if (node != null) {
            result.append(node.value).append(" ");
            preorder(node.left, result);
            preorder(node.right, result);
        }
    }

    public void postorder(ExpressionNode node, StringBuilder result) {
        if (node != null) {
            postorder(node.left, result);
            postorder(node.right, result);
            result.append(node.value).append(" ");
        }
    }

    public double evaluate(ExpressionNode root) {
        if (root == null)
            return 0;

        if (root.left == null && root.right == null)
            return Double.parseDouble(root.value);

        double leftEval = evaluate(root.left);
        double rightEval = evaluate(root.right);

        switch (root.value) {
            case "+":
                return leftEval + rightEval;
            case "-":
                return leftEval - rightEval;
            case "*":
                return leftEval * rightEval;
            case "/":
                return leftEval / rightEval;
        }
        return 0;
    }
}

